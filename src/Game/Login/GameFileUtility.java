package Game.Login;
import java.io.*;
import java.util.*;
import Game.model.*;
public class GameFileUtility {

    private static final String MAIN_FILE = "games.txt";
    private static final String GAME_FOLDER = "GAMES";
    private static int currentId = 1;

    static {
        ensureMainFileAndFolder();
        initializeIdFromFile();
    }

    private static void ensureMainFileAndFolder() {
        try {
            File mainFile = new File(MAIN_FILE);
            if (!mainFile.exists()) mainFile.createNewFile();

            File folder = new File(GAME_FOLDER);
            if (!folder.exists()) folder.mkdir();

        } catch (IOException e) {
            System.err.println("خطا در ساخت فایل/پوشه: " + e.getMessage());
        }
    }

    private static void initializeIdFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(MAIN_FILE))) {
            String line;
            int maxId = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[2]);
                    if (id > maxId) maxId = id;
                }
            }
            currentId = maxId + 1;
        } catch (IOException e) {
            System.err.println("خطا در خواندن فایل برای تعیین ID: " + e.getMessage());
        }
    }

    public static void saveGame(GameOfThrones game) {
        int id = currentId++;
        String fileName = game.getName() + "_" + id + ".ser";
        String filePath = GAME_FOLDER + File.separator + fileName;

        game.setId(id); // اطمینان از ست شدن آیدی در خود شی
        game.setFileName(fileName); // اگر در شی هست

        // ذخیره در فایل متنی (با اضافه کردن تعداد بازیکن)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MAIN_FILE, true))) {
            writer.write(game.getName() + "," + game.getPassword() + "," + id + "," + filePath + "," + game.getMaxPlayer());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("خطا در نوشتن فایل متنی: " + e.getMessage());
        }

        // ذخیره شی کامل
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(game);
        } catch (IOException e) {
            System.err.println("خطا در ذخیره فایل بازی: " + e.getMessage());
        }
    }


    public static GameOfThrones loadGame(String name, int id) {
        String filePath = GAME_FOLDER + File.separator + name + "_" + id + ".ser";

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (GameOfThrones) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("خطا در بارگذاری فایل: " + e.getMessage());
            return null;
        }
    }

    public static LinkedList<String> searchGame(String name) {
        LinkedList<String> results = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MAIN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // اگر name خالی بود، همه را اضافه کن
                if (name.isEmpty() || line.startsWith(name + ",")) {

                    results.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("خطا در جستجو در فایل: " + e.getMessage());
        }

        return results;
    }


    public static void deleteGame(String name, int id) {
        String filePath = GAME_FOLDER + File.separator + name + "_" + id + ".ser";

        // حذف فایل شیء
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }

        // حذف از فایل متنی
        File inputFile = new File(MAIN_FILE);
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(name + ",") || !line.contains("," + id + ",")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("خطا در حذف بازی از لیست: " + e.getMessage());
        }

        // جایگزینی فایل جدید
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    public static void updateGame(GameOfThrones game) {
        String newPath = GAME_FOLDER + File.separator + game.getName() + "_" + game.getId() + ".ser";

        // ذخیره شیء بروز شده
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(newPath))) {
            out.writeObject(game);
        } catch (IOException e) {
            System.err.println("خطا در ذخیره فایل بازی: " + e.getMessage());
        }

        // به‌روز رسانی فایل متنی
        File inputFile = new File(MAIN_FILE);
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(game.getName()) && Integer.parseInt(parts[2]) == game.getId()) {
                    // خط جایگزین
                    writer.write(game.getName() + "," + game.getPassword() + "," + game.getId() + "," + newPath + "," + game.getMaxPlayer());
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("خطا در بروزرسانی فایل متنی: " + e.getMessage());
        }

        inputFile.delete();
        tempFile.renameTo(inputFile);
    }
}