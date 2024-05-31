package vanya9090.client;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import vanya9090.client.connection.UDPClient;
import vanya9090.client.controllers.AuthController;
import vanya9090.client.controllers.MainController;
import vanya9090.client.utils.Localizator;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.models.User;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

public final class Client extends Application {
    public static ILogger logger = new Logger();
    private static final int PORT = 17895;
    public static User user;
    public static UDPClient client;
    private Stage stage;
    public static Localizator localizator;
    public static HashMap<String, CommandArgument[]> commands;
    public static final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru", "RU"));
        put("Suomen", new Locale("fi", "FI"));
        put("Magyar", new Locale("hu", "HU"));
        put("Español (Guatemala)", new Locale("es", "GT"));
    }};

    public static void main(String[] args){
        try {
            client = new UDPClient(InetAddress.getByName("localhost"), PORT);
//            client = new UDPClient(InetAddress.getByName("192.168.10.80"), PORT);
            Response getCommands = client.request(new Request("get_commands", null));
            commands = (HashMap<String, CommandArgument[]>) getCommands.getBody()[0];
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
//        ResourceBundle resourceBundle = ResourceBundle.getBundle("locales/gui");
        localizator = new Localizator(ResourceBundle.getBundle("locales/gui", new Locale("ru", "RU")));
        authStage();
    }

    public void mainStage(){
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent mainRoot = loadFxml(mainLoader);
        MainController mainController = mainLoader.getController();
        stage.setScene(new Scene(mainRoot));
        stage.show();
    }

    public void authStage() {
        FXMLLoader authLoader = new FXMLLoader(getClass().getResource("/authScene.fxml"));
        Parent authRoot = loadFxml(authLoader);
        AuthController authController = authLoader.getController();
        authController.setCallback(this::mainStage);
        stage.setScene(new Scene(authRoot));
        stage.show();
    }

    private Parent loadFxml(FXMLLoader loader) {
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            logger.error("Can't load " + loader);
            System.exit(1);
        }
        return parent;
    }
}
