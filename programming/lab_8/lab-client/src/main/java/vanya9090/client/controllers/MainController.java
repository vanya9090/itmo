package vanya9090.client.controllers;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import vanya9090.client.Client;
import vanya9090.client.auth.SessionManager;
import vanya9090.client.gui.EditDialog;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.User;

import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.DelayQueue;

import static vanya9090.client.Client.localeMap;
import static vanya9090.client.Client.localizator;

public class MainController {

    @FXML
    private Button add;

    @FXML
    private Button add_if_min;

    @FXML
    private TableColumn<HumanBeing, Boolean> carCoolColumn;

    @FXML
    private TableColumn<HumanBeing, String> carNameColumn;

    @FXML
    private Button clear;

    @FXML
    private TableColumn<HumanBeing, String> dateColumn;

    @FXML
    private Button execute_script;

    @FXML
    private Button exit;

    @FXML
    private Button filter_by_weapon_type;

    @FXML
    private Button help;

    @FXML
    private TableColumn<HumanBeing, Integer> idColumn;

    @FXML
    private TableColumn<HumanBeing, Integer> impactSpeedColumn;

    @FXML
    private Button info;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private Button logout;

    @FXML
    private TableColumn<HumanBeing, Float> minutesColumn;

    @FXML
    private TableColumn<HumanBeing, String> moodColumn;

    @FXML
    private TableColumn<HumanBeing, String> nameColumn;

    @FXML
    private TableColumn<HumanBeing, String> ownerColumn;

    @FXML
    private Button print_field_descending_impact_speed;

    @FXML
    private TableColumn<HumanBeing, Boolean> realHeroColumn;

    @FXML
    private Button remove_by_id;

    @FXML
    private Button remove_first;

    @FXML
    private Button remove_head;

    @FXML
    private Button sum_of_impact_speed;

    @FXML
    private Tab tableTab;

    @FXML
    private TableView<HumanBeing> tableTable;

    @FXML
    private TableColumn<HumanBeing, Boolean> toothPickColumn;

    @FXML
    private Button update;

    @FXML
    private Label userLabel;

    @FXML
    private AnchorPane visualPane;

    @FXML
    private Tab visualTab;

    @FXML
    private TableColumn<HumanBeing, String> weaponColumn;

    @FXML
    private TableColumn<HumanBeing, Integer> xColumn;

    @FXML
    private TableColumn<HumanBeing, Float> yColumn;


    @FXML
    void call(ActionEvent event) throws IOException, ClassNotFoundException {
        Optional<Map<String, Object>> args = Optional.of(new HashMap<>());
        String commandName = ((Button) event.getSource()).getId();
        CommandArgument[] commandArguments = Client.commands.get(commandName);
        if (commandArguments.length > 1) {
            EditDialog editDialog = new EditDialog(commandName);
            args = editDialog.show();
            if (args.isPresent()) {
                args.get().put("user", SessionManager.getCurrentUser());
                Response response = Client.client.request(new Request(commandName, args.get(), SessionManager.getCurrentUser()));
//                String message = (String) response.getBody()[0];
//                System.out.println(message);
//                DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
            }
        } else {
            args.get().put("user", SessionManager.getCurrentUser());
            Response response = Client.client.request(new Request(commandName, args.get(), SessionManager.getCurrentUser()));
            String message = (String) response.getBody()[0];
            System.out.println(message);
            DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
        }
        loadCollection();
    }


    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setStyle("-fx-font: 13px \"Sergoe UI\";");
        languageComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            localizator.setBundle(ResourceBundle.getBundle("locales/gui", localeMap.get(newValue)));
            changeLanguage();
        });

        languageComboBox.setValue(SessionManager.getCurrentLanguage());


        ownerColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty("0"));
        nameColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getName()));
        carNameColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getCar().getName()));
        weaponColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getWeaponType().toString()));
        moodColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getMood().toString()));
        carCoolColumn.setCellValueFactory(humanBeing -> new SimpleBooleanProperty(humanBeing.getValue().getCar().getCool()));
        xColumn.setCellValueFactory(humanBeing -> new SimpleIntegerProperty(humanBeing.getValue().getCoordinates().getX()).asObject());
        yColumn.setCellValueFactory(humanBeing -> new SimpleFloatProperty(humanBeing.getValue().getCoordinates().getY()).asObject());
        toothPickColumn.setCellValueFactory(humanBeing -> new SimpleBooleanProperty(humanBeing.getValue().getHasToothpick()));
        realHeroColumn.setCellValueFactory(humanBeing -> new SimpleBooleanProperty(humanBeing.getValue().getRealHero()));
        minutesColumn.setCellValueFactory(humanBeing -> new SimpleFloatProperty(humanBeing.getValue().getMinutesOfWaiting()).asObject());
        impactSpeedColumn.setCellValueFactory(humanBeing -> new SimpleIntegerProperty(humanBeing.getValue().getImpactSpeed()).asObject());
        idColumn.setCellValueFactory(humanBeing -> new SimpleIntegerProperty(humanBeing.getValue().getId()).asObject());
        dateColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty("0"));

        userLabel.setText("Пользователь: " + SessionManager.getCurrentUser().getLogin());

        tableTable.setRowFactory(tableView -> {
            TableRow<HumanBeing> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    doubleClickUpdate(row.getItem());
                }
            });
            return row;
        });
        loadCollection();
    }

    public void loadCollection() throws IOException, ClassNotFoundException {
        Map<String, Object> args = new HashMap<>();
        Response response = Client.client.request(new Request("get_collection", args, SessionManager.getCurrentUser()));
        List<HumanBeing> arrayDeque = new ArrayList<>();
        for (Object humanBeing : response.getBody()) {
            arrayDeque.add((HumanBeing) humanBeing);
        }
        setCollection(arrayDeque);
    }

    public void doubleClickUpdate(HumanBeing humanBeing) {
        System.out.println(humanBeing);
    }

    public void setCollection(List<HumanBeing> humanBeingDeque) {
        System.out.println(FXCollections.observableArrayList(humanBeingDeque));
        tableTable.setItems(FXCollections.observableArrayList(humanBeingDeque));
    }

    public void changeLanguage() {

    }

}
