package vanya9090.client;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import vanya9090.client.connection.UDPClient;
import vanya9090.client.controllers.MainController;
import vanya9090.common.models.User;
import vanya9090.common.util.ILogger;
import vanya9090.common.util.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Locale;

public final class Client extends Application {
    public static ILogger logger = new Logger();
    private static final int PORT = 17895;
    public static User user;
    private Stage mainStage;
    private Stage authStage;
    public static final HashMap<String, Locale> localeMap = new HashMap<>() {{
        put("Русский", new Locale("ru", "RU"));
        put("Suomen", new Locale("fi", "FI"));
        put("Magyar", new Locale("hu", "HU"));
        put("Español (Guatemala)", new Locale("es", "GT"));
    }};

    public static void main(String[] args){
        try {
            UDPClient client = new UDPClient(InetAddress.getByName("192.168.10.80"), PORT);
            launch();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void mainStage(Stage stage){
        mainStage = stage;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("/mainScene.fxml"));
        Parent mainRoot = loadFxml(mainLoader);
        MainController mainController = mainLoader.getController();
        mainStage.setScene(new Scene(mainRoot));
        mainStage.show();
    }

    public void authStage(Stage stage) {
        authStage = stage;
        FXMLLoader authLoader = new FXMLLoader(getClass().getResource("/authScene.fxml"));
        Parent authRoot = loadFxml(authLoader);
        authStage.setScene(new Scene(authRoot));
        authStage.show();
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
