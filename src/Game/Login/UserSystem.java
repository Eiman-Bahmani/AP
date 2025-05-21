package Game.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserSystem {
    private List<GameUser> users = new ArrayList<>();
    private int nextId = 1;
    private final File userFile = new File("users.txt");

    public UserSystem() {
        loadUsersFromFile(); // موقع اجرا، لیست رو از فایل بخون
    }

    public GameUser login(String username, String password) {
        for (GameUser user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean register(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) return false;
        if (username == null || username.isEmpty()) return false;
        if (password == null || password.length() != 8) return false;
        if (users.size() >= 7) return false;

        for (GameUser user : users) {
            if (user.username.equals(username)) {
                return false; // نام تکراری
            }
        }

        GameUser newUser = new GameUser(username, password, nextId++);
        users.add(newUser);
        saveUsersToFile();
        return true;
    }

    private void saveUsersToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(userFile))) {
            for (GameUser user : users) {
                writer.println(user.id + "," + user.username + "," + user.password);
            }
        } catch (IOException e) {
            System.out.println("خطا در ذخیره‌سازی کاربران: " + e.getMessage());
        }
    }

    private void loadUsersFromFile() {
        if (!userFile.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String password = parts[2];
                    users.add(new GameUser(username, password, id));
                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("خطا در خواندن کاربران از فایل: " + e.getMessage());
        }
    }

    public int getUserCount() {
        return users.size();
    }
}
