package vanya9090.client.controllers;

import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import org.apache.commons.lang3.concurrent.EventCountCircuitBreaker;
import vanya9090.client.App;
import vanya9090.client.auth.SessionManager;
import vanya9090.client.gui.EditDialog;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.connection.Request;
import vanya9090.common.connection.Response;
import vanya9090.common.exceptions.AccessException;
import vanya9090.common.exceptions.NotFoundException;
import vanya9090.common.models.HumanBeing;
import vanya9090.common.models.User;
import vanya9090.common.util.FakeLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static vanya9090.client.App.localeMap;
import static vanya9090.client.App.localizator;

public class MainController {
    private volatile boolean isRefreshing = false;
    private HashMap<Integer, Label> infoMap;
    Map<Integer, String> humanAuthor;

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

//    @FXML
//    private Button logout;

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


//    @FXML
//    void call(Event event) throws IOException, ClassNotFoundException {
//        Optional<Map<String, Object>> args = Optional.of(new HashMap<>());
//        String commandName = ((Button) event.getSource()).getId();
//        CommandArgument[] commandArguments = App.commands.get(commandName);
//        System.out.println(Arrays.stream(commandArguments).map(CommandArgument::getName).collect(Collectors.toList()));
//        if (commandName.equals("exit")) {
//            Platform.exit();
//            System.exit(0);
//        }
//
//        if (commandArguments.length > 1) {
//            EditDialog editDialog = new EditDialog(commandName);
//            args = editDialog.show(null);
//            if (args.isPresent()) {
//                if (commandName.equals("remove_by_id")) {
//                    if (!humanAuthor.get(args.get().get("id")).equals(SessionManager.getCurrentUser().getLogin())) {
//                        DialogManager.createAlert(localizator.getKeyString("Info"),
//                                "пользователь не может удалять записи другого пользователя",
//                                Alert.AlertType.WARNING, true);
//                    }
//                }
//
//                args.get().put("user", SessionManager.getCurrentUser());
//                Response response = App.client.request(new Request(commandName, args.get(), SessionManager.getCurrentUser()));
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//
//                if (response.getBody().length != 0) {
//                    String message;
//                    if (response.getBody().length == 1) message = (String) response.getBody()[0];
//                    else message = Arrays.toString(response.getBody());
//                    System.out.println(message);
//                    DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
//                }
//            }
//        } else {
//            if (commandName.equals("remove_first") || commandName.equals("remove_head")) {
//                args.get().put("id", tableTable.getItems().get(0).getId());
//                commandName = "remove_by_id";
//                if (!humanAuthor.get(args.get().get("id")).equals(SessionManager.getCurrentUser().getLogin())) {
//                    DialogManager.createAlert(localizator.getKeyString("Info"),
//                            "пользователь не может удалять записи другого пользователя",
//                            Alert.AlertType.WARNING, true);
//                    return;
//                }
//            }
//            args.get().put("user", SessionManager.getCurrentUser());
//            Response response = App.client.request(new Request(commandName, args.get(), SessionManager.getCurrentUser()));
//            if (response.getBody().length != 0) {
//                String message = (String) response.getBody()[0];
//                System.out.println(message);
//                DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
//            }
//        }
//        loadCollection();
//    }

    @FXML
    void call(Event event) throws IOException, ClassNotFoundException {
        String commandName = ((Button) event.getSource()).getId();
        CommandArgument[] commandArguments = App.commands.get(commandName);
        System.out.println(Arrays.stream(commandArguments).map(CommandArgument::getName).collect(Collectors.toList()));
        if (commandArguments.length > 1) {
            handleDialog(commandArguments, commandName);
        } else {
            handleSimple(commandName);
        }
    }

    private void handleDialog(CommandArgument[] commandArguments, String commandName) {
        EditDialog editDialog = new EditDialog(commandName);
        Optional<Map<String, Object>> args = editDialog.show(null);
        if (args.isPresent()) {
            if (commandName.equals("remove_by_id")) {
                if (!humanAuthor.get(args.get().get("id")).equals(SessionManager.getCurrentUser().getLogin())) {
                    DialogManager.createAlert(localizator.getKeyString("Info"),
                            "пользователь не может удалять записи другого пользователя",
                            Alert.AlertType.WARNING, true);
                }
            }
            new Thread(() -> {
                try {
                    handleDialogResult(commandName, args.get());
                    loadCollection();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }

    private void handleDialogResult(String commandName, Map<String, Object> args) throws IOException, ClassNotFoundException {
        args.put("user", SessionManager.getCurrentUser());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Response response = App.client.request(new Request(commandName, args, SessionManager.getCurrentUser()));

        if (response.getBody().length != 0) {
            String message;
            if (response.getBody().length == 1) message = (String) response.getBody()[0];
            else message = Arrays.toString(response.getBody());
            System.out.println(message);
            Platform.runLater(() -> DialogManager.createAlert(localizator.getKeyString("Info"),
                    message, Alert.AlertType.INFORMATION, true));
        }
    }

    private void handleSimple(String commandName) throws IOException, ClassNotFoundException {
        Map<String, Object> args = new HashMap<>();
        if (commandName.equals("remove_first") || commandName.equals("remove_head")) {
            args.put("id", tableTable.getItems().get(0).getId());
            commandName = "remove_by_id";
            if (!humanAuthor.get(args.get("id")).equals(SessionManager.getCurrentUser().getLogin())) {
                DialogManager.createAlert(localizator.getKeyString("Info"),
                        "пользователь не может удалять записи другого пользователя",
                        Alert.AlertType.WARNING, true);
                return;
            }
        }
        handleSimpleResult(commandName, args);
    }

    private void handleSimpleResult(String commandName, Map<String, Object> args) throws IOException, ClassNotFoundException {
        args.put("user", SessionManager.getCurrentUser());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Response response = App.client.request(new Request(commandName, args, SessionManager.getCurrentUser()));
        if (response.getBody().length != 0) {
            String message = (String) response.getBody()[0];
            System.out.println(message);
            DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
        }
    }


    @FXML
    void initialize() throws IOException, ClassNotFoundException {

        infoMap = new HashMap<>();
        languageComboBox.setItems(FXCollections.observableArrayList(localeMap.keySet()));
        languageComboBox.setStyle("-fx-font: 13px \"Sergoe UI\";");
        languageComboBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            localizator.setBundle(ResourceBundle.getBundle("locales/gui", localeMap.get(newValue)));
            changeLanguage();
        });

        languageComboBox.setValue(SessionManager.getCurrentLanguage());


        ownerColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanAuthor.get(humanBeing.getValue().getId())));
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
        dateColumn.setCellValueFactory(humanBeing -> new SimpleStringProperty(humanBeing.getValue().getCreationDate().toString()));

        userLabel.setText("Пользователь: " + SessionManager.getCurrentUser().getLogin());

        tableTable.setRowFactory(tableView -> {
            TableRow<HumanBeing> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    try {
                        doubleClickUpdate(row.getItem());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else if (event.getButton() == MouseButton.SECONDARY) {
                    try {
                        rightClickEvent(row.getItem());
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });

        visualTab.setOnSelectionChanged(event -> visualise(false));

        loadCollection();
    }

    public void visualise(boolean refresh) {
        if (tableTable.getItems().isEmpty()) {
            return;
        }
        visualPane.getChildren().clear();
        infoMap.clear();
        int paneMaxX = 1080;
        int paneMaxY = 463;
        int maxX = tableTable.getItems().stream().map(humanBeing -> humanBeing.getCoordinates().getX()).max(Integer::compareTo).get();
        float maxY = tableTable.getItems().stream().map(humanBeing -> humanBeing.getCoordinates().getY()).max(Float::compareTo).get();
        int maxImpactSpeed = tableTable.getItems().stream().map(HumanBeing::getImpactSpeed).max(Integer::compareTo).get();
        float maxMinutesOfWaiting = tableTable.getItems().stream().map(HumanBeing::getMinutesOfWaiting).max(Float::compareTo).get();

        for (HumanBeing human: tableTable.getItems()) {
            float minutesOfWaiting = human.getMinutesOfWaiting();
            int x = (int) (((float) human.getCoordinates().getX()) / maxX * paneMaxX * 0.8);
            float y = (float) (human.getCoordinates().getY() / maxY * paneMaxY * 0.6 + 80);
            double r = (double) Math.abs(humanAuthor.get(human.getId()).hashCode() % 100) / 100;
            double g = (double) Math.abs(humanAuthor.get(human.getId()).hashCode() % 50) / 50;
            double b = (double) Math.abs(humanAuthor.get(human.getId()).hashCode() % 70) / 70;
            float radius = (float) (Math.log(minutesOfWaiting));
            Circle circle = new Circle(radius, Color.color(r, g, b));


            Text id = new Text('#' + String.valueOf(human.getId()));
            Label info = new Label(human.toString());

            info.setVisible(false);
            circle.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getClickCount() == 2) {
                    try {
                        doubleClickUpdate(human);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else if (mouseEvent.getButton() == MouseButton.SECONDARY) {
                    try {
                        rightClickEvent(human);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });

            circle.setOnMouseEntered(mouseEvent -> {
                id.setVisible(false);
                info.setVisible(true);
//                circle.setFill(colorMap.get(creatorName).brighter());
            });
//
            circle.setOnMouseExited(mouseEvent -> {
                id.setVisible(true);
                info.setVisible(false);
//                circle.setFill(colorMap.get(creatorName));
            });

            id.setFont(Font.font("Segoe UI", radius*2));
            info.setStyle("-fx-background-color: white; -fx-border-color: #c0c0c0; -fx-border-width: 2");
            info.setFont(Font.font("Segoe UI", 12));

            visualPane.getChildren().add(id);
            visualPane.getChildren().add(circle);

            infoMap.put(human.getId(), info);
            if (!refresh) {
                var path = new Path();
                path.getElements().add(new MoveTo(paneMaxX/2, paneMaxY/2));
                path.getElements().add(new HLineTo(x));
                path.getElements().add(new VLineTo(y));

                id.translateXProperty().bind(circle.translateXProperty().subtract(id.getLayoutBounds().getWidth()));
                id.translateYProperty().bind(circle.translateYProperty().add(id.getLayoutBounds().getHeight()));
                info.translateXProperty().bind(circle.translateXProperty().add(circle.getRadius()));
                info.translateYProperty().bind(circle.translateYProperty().subtract(120));
                var transition = new PathTransition();
                transition.setDuration(Duration.millis(750));
                transition.setNode(circle);
                transition.setPath(path);
                transition.setOrientation(PathTransition.OrientationType.NONE);
                transition.play();
            } else {
                circle.setCenterX(x);
                circle.setCenterY(y);
                info.translateXProperty().bind(circle.centerXProperty().add(circle.getRadius()));
                info.translateYProperty().bind(circle.centerYProperty().subtract(120));
                id.translateXProperty().bind(circle.centerXProperty().subtract(id.getLayoutBounds().getWidth()));
                id.translateYProperty().bind(circle.centerYProperty().add(id.getLayoutBounds().getHeight()));
                var darker = new FillTransition(Duration.millis(750), circle);
                var brighter = new FillTransition(Duration.millis(750), circle);
                var transition = new SequentialTransition(darker, brighter);
                transition.play();
            }
        }
        for (var ids : infoMap.keySet()) {
            visualPane.getChildren().add(infoMap.get(ids));
        }
    }

    public void getAuthorOfHuman() {
        Map<String, Object> args = new HashMap<>();
        Response response = null;
        try {
            response = App.client.request(new Request("get_collection_users", args, SessionManager.getCurrentUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        humanAuthor = (Map<Integer, String>) response.getBody()[0];
    }


    public void loadCollection()  {
        this.getAuthorOfHuman();
        Map<String, Object> args = new HashMap<>();
        Response response = null;
        try {
            response = App.client.request(new Request("get_collection", args, SessionManager.getCurrentUser()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<HumanBeing> arrayDeque = new ArrayList<>();
        for (Object humanBeing : response.getBody()) {
            arrayDeque.add((HumanBeing) humanBeing);
        }
        setCollection(arrayDeque);
        Platform.runLater(() -> visualise(true));
//        visualise(true);
    }

    public void rightClickEvent(HumanBeing humanBeing) throws IOException, ClassNotFoundException {
        if (!humanAuthor.get(humanBeing.getId()).toString().equals(SessionManager.getCurrentUser().getLogin().toString())) {
            DialogManager.createAlert(localizator.getKeyString("Info"),
                    "пользователь не может удалять записи другого пользователья",
                    Alert.AlertType.WARNING, true);
        } else {
            Map<String, Object> args = new HashMap<>();
            args.put("id", humanBeing.getId());
            args.put("user", SessionManager.getCurrentUser());
            Response response = App.client.request(new Request("remove_by_id", args, SessionManager.getCurrentUser()));
            if (response.getBody().length != 0) {
                String message;
                if (response.getBody().length == 1) message = (String) response.getBody()[0];
                else message = Arrays.toString(response.getBody());
                DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
                loadCollection();
            }
        }
    }

    public void doubleClickUpdate(HumanBeing humanBeing) throws IOException, ClassNotFoundException {
        if (!humanAuthor.get(humanBeing.getId()).toString().equals(SessionManager.getCurrentUser().getLogin().toString())) {
            DialogManager.createAlert(localizator.getKeyString("Info"),
                    "пользователь не может редактировать записи другого пользователья",
                    Alert.AlertType.WARNING, true);
        } else {
            Optional<Map<String, Object>> args;
            EditDialog editDialog = new EditDialog("update");
            args = editDialog.show(humanBeing);
            if (args.isPresent()) {
                args.get().put("user", SessionManager.getCurrentUser());
                Response response = App.client.request(new Request("update", args.get(), SessionManager.getCurrentUser()));
                if (response.getBody().length != 0) {
                    String message;
                    if (response.getBody().length == 1) message = (String) response.getBody()[0];
                    else message = Arrays.toString(response.getBody());
                    DialogManager.createAlert(localizator.getKeyString("Info"), message, Alert.AlertType.INFORMATION, true);
                    loadCollection();
                }
            }
        }
    }

    public void setCollection(List<HumanBeing> humanBeingDeque) {
        List<? extends TableColumn<HumanBeing, ?>> sortingOrder = new ArrayList<>(tableTable.getSortOrder());
        tableTable.setItems(FXCollections.observableArrayList(humanBeingDeque));
        tableTable.getSortOrder().setAll(sortingOrder);
    }

    public void changeLanguage() {
        userLabel.setText(localizator.getKeyString("UserLabel") + " " + SessionManager.getCurrentUser().getLogin());

        exit.setText(localizator.getKeyString("Exit"));
        remove_by_id.setText(localizator.getKeyString("RemoveByID"));
        help.setText(localizator.getKeyString("Help"));
        info.setText(localizator.getKeyString("Info"));
        add.setText(localizator.getKeyString("Add"));
        update.setText(localizator.getKeyString("Update"));
        remove_first.setText(localizator.getKeyString("RemoveFirst"));
        clear.setText(localizator.getKeyString("Clear"));
        remove_head.setText(localizator.getKeyString("RemoveHead"));
        filter_by_weapon_type.setText(localizator.getKeyString("FilterByWeaponType"));
        add_if_min.setText(localizator.getKeyString("AddIfMin"));
        sum_of_impact_speed.setText(localizator.getKeyString("SumOfImpactSpeed"));
        print_field_descending_impact_speed.setText(localizator.getKeyString("PrintFieldDescendingImpactSpeed"));
        execute_script.setText(localizator.getKeyString("ExecuteScript"));

        tableTab.setText(localizator.getKeyString("TableTab"));
        visualTab.setText(localizator.getKeyString("VisualTab"));

        ownerColumn.setText(localizator.getKeyString("Owner"));
        nameColumn.setText(localizator.getKeyString("Name"));
        dateColumn.setText(localizator.getKeyString("CreationDate"));
        realHeroColumn.setText(localizator.getKeyString("RealHero"));
        toothPickColumn.setText(localizator.getKeyString("ToothPick"));
        impactSpeedColumn.setText(localizator.getKeyString("ImpactSpeed"));
        minutesColumn.setText(localizator.getKeyString("Minutes"));
        moodColumn.setText(localizator.getKeyString("Mood"));
        carNameColumn.setText(localizator.getKeyString("CarName"));
        carCoolColumn.setText(localizator.getKeyString("CarCool"));
        weaponColumn.setText(localizator.getKeyString("WeaponType"));
    }

    public void setRefreshing(boolean refreshing) {
        isRefreshing = refreshing;
    }

    public void refresh() {
        Thread threadRefresh = new Thread(() -> {
            while (isRefreshing) {
                Platform.runLater(this::loadCollection);
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        threadRefresh.start();
    }

}
