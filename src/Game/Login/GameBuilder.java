package Game.Login;

import Game.model.GameOfThrones;

import java.util.LinkedList;
import java.util.Scanner;

public class GameBuilder {
    public static void main(GameUser user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- منوی اصلی ---");
            System.out.println("1. ایجاد بازی جدید");
            System.out.println("2. ورود به بازی دیگران");
            System.out.println("0. خروج");
            System.out.print("انتخاب شما: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewGame(scanner,user);
                    break;
                case "2":
                    joinExistingGame(scanner,user);
                    break;
                case "0":
                    System.out.println("خروج از برنامه.");
                    return;
                default:
                    System.out.println("گزینه نامعتبر.");
            }
        }
    }

    private static void createNewGame(Scanner scanner,GameUser user) {
        System.out.print("نام بازی: ");
        String newName = scanner.nextLine();

        System.out.print("رمز عبور عددی (یا فقط Enter برای بدون رمز): ");
        String passInput = scanner.nextLine();
        int newPassword = -1; // -1 یعنی بدون رمز

        if (!passInput.isEmpty()) {
            try {
                newPassword = Integer.parseInt(passInput);
            } catch (NumberFormatException e) {
                System.out.println("رمز عبور باید عدد باشد یا خالی رها شود.");
                return;
            }
        }

        GameOfThrones newGame = new GameOfThrones(newName, newPassword,user);
        try {
            GameFileUtility.saveGame(newGame);
            System.out.println("بازی با موفقیت ذخیره شد.");
Game.main(user,newGame);            //وزود به صفحه لودینگ بازی
        } catch (Exception e) {
            System.out.println("خطا در ذخیره فایل بازی: " + e.getMessage());
        }
    }

    private static void joinExistingGame(Scanner scanner,GameUser user) {
        LinkedList<String> games = GameFileUtility.searchGame("");
        if (games.isEmpty()) {
            System.out.println("هیچ بازی‌ای یافت نشد.");
            return;
        }

        System.out.println("\nلیست بازی‌های موجود:");
        int index = 1;
        for (String line : games) {
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                String lockStatus = parts[1].equals("-1") ? "آزاد" : "قفل شده";
                String playerCount = (parts.length >= 5) ? parts[4] : "?";
                System.out.println(index + ". " + parts[0] + " (" + lockStatus + ") - بازیکن‌ها: " + playerCount);
                index++;
            }
        }
        System.out.println("0. بازگشت به منو اصلی");
        System.out.print("شماره بازی مورد نظر را وارد کنید: ");

        int selection;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("عدد نامعتبر.");
            return;
        }

        if (selection == 0) return;
        if (selection < 1 || selection > games.size()) {
            System.out.println("شماره انتخابی نامعتبر است.");
            return;
        }

        String selectedGameLine = games.get(selection - 1);
        String[] parts = selectedGameLine.split(",");
        String name = parts[0];
        String password = parts[1];
        int id = Integer.parseInt(parts[2]);

        if (!password.equals("-1")) {
            System.out.print("رمز عبور: ");
            String inputPassword = scanner.nextLine();

            if (!inputPassword.equals(password)) {
                System.out.println("رمز عبور اشتباه است.");
                return;
            }
        }

        try {
            GameOfThrones loaded = GameFileUtility.loadGame(name, id);
            if(loaded.addPlayer(user)){
                System.out.println("ورود موفق. اطلاعات بازی:");
                 Game.main(user,loaded);
            }
            else                 System.out.println("ورود نا موفق. بازی تکمیل  استت");


        } catch (Exception e) {
            System.out.println("خطا در بارگذاری فایل بازی: " + e.getMessage());
        }
    }
}
