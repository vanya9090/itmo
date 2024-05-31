package vanya9090.client.gui;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import vanya9090.client.Client;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.common.handlers.HandleManager;
import vanya9090.common.handlers.Handler;
import vanya9090.common.models.Car;
import vanya9090.common.models.Coordinates;
import vanya9090.common.models.HumanBeing;

import javax.swing.event.ChangeListener;
import java.lang.reflect.Field;
import java.util.*;

public class EditDialog {
    FlowPane root;
    Dialog<Map<String, Object>> dialog;
    Map<String, TextField> fieldMap;
    Map<String, Handler<?>> handlers;
    CommandArgument[] arguments;
    ButtonType okButtonType;
    Node okButton;
    Integer status;
    Map<String, String> regexMap;
    public EditDialog (String commandName) {
        handlers = new HandleManager().getHandlers();
        arguments = Client.commands.get(commandName);
        dialog = new Dialog<>();
        dialog.setTitle(commandName);
        dialog.setHeaderText(null);

        okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButtonType);

        okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);

        root = new FlowPane(Orientation.VERTICAL);
        fieldMap = new HashMap<>();
        status = 0;
        regexMap = new HashMap<>() {{
            put("HumanBeing name", ".*");
            put("HumanBeing realHero", "\\b(true|false)\\b");
            put("HumanBeing hasToothpick", "\\b(true|false)\\b");
            put("HumanBeing impactSpeed", "\\b\\d+\\b");
            put("HumanBeing minutesOfWaiting", "\\b\\d+(\\.\\d+)?\\b");
            put("HumanBeing weaponType", "(?i)\\b(HAMMER|AXE|SHOTGUN|RIFLE|KNIFE)\\b");
            put("HumanBeing mood", "(?i)\\b(SADNESS|SORROW|APATHY|CALM|RAGE)\\b");
            put("Coordinates x", "\\b\\d+\\b");
            put("Coordinates y", "\\b\\d+(\\.\\d+)?\\b");
            put("Car cool", "\\b(true|false)\\b");
            put("Car name", ".*");
        }};
    }

    public Optional<Map<String, Object>> show() {
        for (CommandArgument argument : arguments) {
            if (argument.getCommandType() != CommandType.CLIENT) continue;
            if (argument.getType() == HumanBeing.class) {
                setByFields(HumanBeing.class.getDeclaredFields(), HumanBeing.class.getSimpleName());
                setByFields(Coordinates.class.getDeclaredFields(), Coordinates.class.getSimpleName());
                setByFields(Car.class.getDeclaredFields(), Car.class.getSimpleName());
                System.out.println(fieldMap);
            } else {
                setField(argument);
            }
        }

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                Map<String, Object> argMap = new HashMap<>();
                for (CommandArgument argument : arguments) {
                    if (argument.getCommandType() != CommandType.CLIENT) continue;
                    if (argument.getType() == HumanBeing.class) argMap.put(argument.getName(), listenHuman());
                    else {
                        try {
                            argMap.put(argument.getName(), handlers.get(argument.getType().getSimpleName())
                                    .handle(fieldMap.get(argument.getName()).getText(), argument.getName()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                return argMap;
            }
            return null;
        });


        dialog.getDialogPane().setContent(root);
        return dialog.showAndWait();
    }

    public void setField (CommandArgument argument) {
        Label label = new Label(argument.getName());
        root.getChildren().add(label);

        TextField field = new TextField();
        fieldMap.put(argument.getName(), field);
        root.getChildren().add(field);
    }

    public void setByFields(Field[] fields, String className) {
        for (Field classField: fields) {
            if (classField.getName().equals("nextId")
                    || classField.getName().equals("id")
                    || classField.getName().equals("creationDate")
                    || classField.getType() == Coordinates.class
                    || classField.getType() == Car.class) continue;
            Label label = new Label(className + " " + classField.getName());
            root.getChildren().add(label);

            TextField field = new TextField();
            fieldMap.put(className + " " + classField.getName(), field);
            System.out.println(className + " " + classField.getName());

            field.textProperty().addListener((arg0, oldValue, newValue) -> {
                if (oldValue.trim().isEmpty() && !newValue.trim().isEmpty()) {
                    status ++;
                } else if (!oldValue.trim().isEmpty() && newValue.trim().isEmpty()) {
                    status --;
                }
                okButton.setDisable(status != 11);
            });

            field.focusedProperty().addListener((arg0, oldValue, newValue) -> {
                if (!newValue) { //when focus lost
                    if(!field.getText().matches(regexMap.get(className + " " + classField.getName()))){
                        field.setText("");
                    }
                }

            });
            root.getChildren().add(field);
        }
    }

    public HumanBeing listenHuman() {
        Map<String, Object> humanMap = new HashMap<>();
        for (Field humanField: HumanBeing.class.getDeclaredFields()) {
            System.out.println(HumanBeing.class.getSimpleName() + " " + humanField.getName());
            if (humanField.getName().equals("nextId") || humanField.getName().equals("id") || humanField.getName().equals("creationDate")) continue;
            if (humanField.getName().equals("coordinates")) {
                humanMap.put("coordinates", listenCoordinates());
            } else if (humanField.getName().equals("car")) {
                humanMap.put("car", listenCar());
            }else {
                try {
                    humanMap.put(humanField.getName(), handlers.get(humanField.getType().getSimpleName())
                            .handle(fieldMap.get(HumanBeing.class.getSimpleName() + " " + humanField.getName()).getText(), humanField.getName()));
                } catch (EmptyFieldException | ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return new HumanBeing(humanMap);
    }

    public Coordinates listenCoordinates() {
        Map<String, Object> coordinatesMap = new HashMap<>();
        coordinatesMap.put("x", Integer.parseInt(fieldMap.get("Coordinates x").getText()));
        coordinatesMap.put("y", Float.parseFloat(fieldMap.get("Coordinates y").getText()));
        return new Coordinates(coordinatesMap);
    }

    public Car listenCar() {
        Map<String, Object> carMap = new HashMap<>();
        carMap.put("cool", Boolean.parseBoolean(fieldMap.get("Car cool").getText()));
        carMap.put("name", fieldMap.get("Car name").getText());
        return new Car(carMap);
    }
}
