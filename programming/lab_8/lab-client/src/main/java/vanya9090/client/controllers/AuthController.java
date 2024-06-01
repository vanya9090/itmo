package vanya9090.client.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import vanya9090.client.App;
import vanya9090.client.auth.SessionManager;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.connection.Status;
import vanya9090.common.models.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static vanya9090.client.App.localeMap;
import static vanya9090.client.App.localizator;

public class AuthController {
    private Runnable callback;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private TextField loginField;

    @FXML
    private Button okButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox signUpButton;

    @FXML
    private Label titleLabel;

    @FXML
    void initialize() {
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));

        languageComboBox.setValue(SessionManager.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 13px \"Sergoe UI\";");

        languageComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            localizator.setBundle(ResourceBundle.getBundle("locales/gui", localeMap.get(newValue)));
            SessionManager.setCurrentLanguage(newValue);
            changeLanguage();
        });

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(".{0,40}")) {
                loginField.setText(oldValue);
            }
        });
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\S*")) {
                passwordField.setText(oldValue);
            }
        });
    }

    @FXML
    void ok(ActionEvent event) throws IOException, ClassNotFoundException {
        if (signUpButton.isSelected()) {
            register();
        } else {
            authenticate();
        }
    }

    public void register() throws IOException, ClassNotFoundException {
        if (loginField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || loginField.getText().length() > 40) {
            DialogManager.alert("InvalidCredentials", localizator);
        }
        User user = new User(loginField.getText(), passwordField.getText());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        Response response = App.client.request(new Request("register", args));
        if (response.getCode() == Status.CREATED) {
            SessionManager.setCurrentUser(user);
//            DialogManager.info("Registered", localizator);
            this.callback.run();
        } else if (response.getCode() == Status.FORBIDDEN){
            DialogManager.alert("LoginExists", localizator);
        }

    }

    public void authenticate() throws IOException, ClassNotFoundException {
        if (loginField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || loginField.getText().length() > 40) {
            DialogManager.alert("InvalidCredentials", localizator);
        }
        User user = new User(loginField.getText(), passwordField.getText());
        Map<String, Object> args = new HashMap<>();
        args.put("user", user);
        Response response = App.client.request(new Request("authenticate", args));
        if (response.getCode() == Status.FORBIDDEN){
            if (response.getMessage().equals("Неверный пароль")) {
                DialogManager.alert("PasswordIncorrect", localizator);
            } else {
                DialogManager.alert("UserIsNotRegistered", localizator);
            }
        }
        else {
            SessionManager.setCurrentUser(user);
//            DialogManager.info("Authenticated", localizator);
            this.callback.run();
        }
    }

    public void changeLanguage() {
        titleLabel.setText(localizator.getKeyString("AuthTitle"));
        loginField.setPromptText(localizator.getKeyString("LoginField"));
        passwordField.setPromptText(localizator.getKeyString("PasswordField"));
        signUpButton.setText(localizator.getKeyString("SignUpButton"));
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

}
