package Game.Login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginRegisterApp extends Application {

    private UserSystem userSystem = new UserSystem();
    private Stage primaryStage;
    private Font gotFont;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setResizable(false); // جلوگیری از resize
        primaryStage.setWidth(700);
        primaryStage.setHeight(525);
        primaryStage.setTitle("Game of Thrones Login");

        loadFont();
        showLoginScene();
        primaryStage.show();
    }

    private void loadFont() {
        try {
            gotFont = Font.loadFont(getClass().getResourceAsStream("/got.ttf"), 18);
        } catch (Exception e) {
            gotFont = Font.font("Serif", 18); // fallback
        }
    }

    private void showLoginScene() {
        Label titleLabel = new Label("Entering Westeros");
        titleLabel.setFont(gotFont);
        titleLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 22px;-fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setMaxWidth(300);
        usernameField.setPromptText("User Name");

        PasswordField passwordField = new PasswordField();
        passwordField.setMaxWidth(300);
        passwordField.setPromptText("Password (8 digits)");

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;-fx-font-family: Arial");

        Button loginButton = new Button("Enter");
        loginButton.setMaxWidth(90);
        Button switchToRegisterButton = new Button("Register");
        switchToRegisterButton.setMaxWidth(90);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (password.length() != 8) {
                statusLabel.setText("The password must be exactly 8 characters!");
                return;
            }

            GameUser user = userSystem.login(username, password);
            if (user != null) {
                statusLabel.setStyle("-fx-text-fill: green;-fx-font-family: Arial");
                statusLabel.setText("✅ Successful login، " + user.getUsername() + "!");
            } else {
                statusLabel.setText("Login failed ❌ Please check the information..");
            }
        });

        switchToRegisterButton.setOnAction(e -> showRegisterScene());

        VBox root = new VBox(10, titleLabel, usernameField, passwordField, loginButton, switchToRegisterButton, statusLabel);
        setupLayout(root);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    private void showRegisterScene() {
        Label titleLabel = new Label("Registration in the Westeros");
        titleLabel.setFont(gotFont);
        titleLabel.setStyle("-fx-text-fill: #000000; -fx-font-size: 22px;-fx-font-weight: bold;");

        TextField usernameField = new TextField();
        usernameField.setPromptText("User Name");
        usernameField.setMaxWidth(300);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password (8 characters)");
        passwordField.setMaxWidth(300);

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Password confirmation");
        confirmPasswordField.setMaxWidth(300);

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-text-fill: red;-fx-font-family: Arial");

        Button registerButton = new Button("Register");
        registerButton.setMaxWidth(90);
        Button backButton = new Button("Back");
        backButton.setMaxWidth(90);

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirm = confirmPasswordField.getText();

            if (password.length() != 8 || confirm.length() != 8) {
                statusLabel.setText("The password must be exactly 8 characters!");
                return;
            }

            boolean result = userSystem.register(username, password, confirm);

            if (userSystem.getUserCount() > 7) {
                statusLabel.setStyle("-fx-text-fill: red;-fx-font-family: Arial");
                statusLabel.setText("❌ Westeros is full. You cannot register more than 7 players.");
                return;
            }

            if (result) {
                statusLabel.setStyle("-fx-text-fill: green;");
                statusLabel.setText("✅ Registration was successful!");
            } else {
                statusLabel.setStyle("-fx-text-fill: red;-fx-font-family: Arial");
                statusLabel.setText("❌ Registration failed. Username might be taken or info invalid.");
            }
        });


        backButton.setOnAction(e -> showLoginScene());

        VBox root = new VBox(10, titleLabel, usernameField, passwordField, confirmPasswordField, registerButton, backButton, statusLabel);
        setupLayout(root);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
    }

    private void setupLayout(VBox root) {
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        root.setStyle("-fx-background-color: rgba(0,0,0,0.7);");

        // پس‌زمینه تصویری
        BackgroundImage bgImage = new BackgroundImage(
                new Image(getClass().getResource("/got_bg.jpg").toExternalForm(), 500, 400, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);

        root.setBackground(new Background(bgImage));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
