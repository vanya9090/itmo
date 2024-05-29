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
import vanya9090.client.utils.Localizator;

import java.util.ResourceBundle;

import static vanya9090.client.Client.localeMap;

public class AuthController {
    private Localizator localizator;
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

        languageComboBox.setValue(SessionHandler.getCurrentLanguage());
        languageComboBox.setStyle("-fx-font: 13px \"Sergoe UI\";");

        languageComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            localizator.setBundle(ResourceBundle.getBundle("locales/gui", localeMap.get(newValue)));
            SessionHandler.setCurrentLanguage(newValue);
            changeLanguage();
        });

        loginField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches(".{0,40}")) {
                loginField.setText(oldValue);
            }
        });
        passwordField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                passwordField.setText(oldValue);
        });
    }

    @FXML
    void ok(ActionEvent event) {
        if (signUpButton.isSelected()) {
            register();
        } else {
            authenticate();
        }
    }

    public void register() {
        if (loginField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || loginField.getText().length() > 40) {
            DialogManager.alert("InvalidCredentials", localizator);
        }
    }

}
