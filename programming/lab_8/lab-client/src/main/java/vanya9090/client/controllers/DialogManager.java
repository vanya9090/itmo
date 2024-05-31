package vanya9090.client.controllers;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import vanya9090.client.Client;
import vanya9090.client.utils.Localizator;
import vanya9090.common.commands.CommandArgument;
import vanya9090.common.commands.CommandType;
import vanya9090.common.exceptions.EmptyFieldException;
import vanya9090.common.exceptions.ParseException;
import vanya9090.common.handlers.HandleManager;
import vanya9090.common.handlers.Handler;
import vanya9090.common.models.HumanBeing;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.UnaryOperator;

public class DialogManager {
    public static void alert(String title, Localizator localizator) {
        DialogManager.createAlert(
                localizator.getKeyString("Error"), localizator.getKeyString(title), Alert.AlertType.ERROR, false
        );
    }

    public static void info(String title, Localizator localizator) {
        System.out.println(localizator.getKeyString("Info") + localizator.getKeyString(title));
        DialogManager.createAlert(localizator.getKeyString("Info"), localizator.getKeyString(title), Alert.AlertType.INFORMATION, false);
    }

    public static Optional<Map<String, Object>> createDialogCommand(String commandName) {
        Map<String, Handler<?>> handlers = new HandleManager().getHandlers();
        CommandArgument[] arguments = Client.commands.get(commandName);
        Dialog<Map<String, Object>> dialog = new Dialog<>();
        dialog.setTitle(commandName);
        dialog.setHeaderText(null);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(okButton);

        FlowPane pane = new FlowPane(Orientation.VERTICAL);

        Map<String, TextField> fieldMap = new HashMap<>();
        for (CommandArgument argument : arguments) {
            if (argument.getCommandType() != CommandType.CLIENT) continue;
            if (argument.getType() == HumanBeing.class) {
                for (Field humanField: HumanBeing.class.getDeclaredFields()) {
                    if (humanField.getName().equals("nextId") || humanField.getName().equals("id")) continue;
                    Label label = new Label(humanField.getName());
                    pane.getChildren().add(label);

                    TextField field = new TextField();
                    fieldMap.put(humanField.getName(), field);
                    pane.getChildren().add(field);
                }
            }

            Label label = new Label(argument.getName());
            pane.getChildren().add(label);

            TextField field = new TextField();
            fieldMap.put(argument.getName(), field);
            pane.getChildren().add(field);

        }


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                Map<String, Object> argMap = new HashMap<>();
                for (CommandArgument argument : arguments) {
                    if (argument.getCommandType() != CommandType.CLIENT) continue;
                    if (argument.getType() == HumanBeing.class) {
                        Map<String, Object> humanMap = new HashMap<>();
                        for (Field humanField: HumanBeing.class.getDeclaredFields()) {
                            if (humanField.getName().equals("nextId") || humanField.getName().equals("id")) continue;
                            humanMap.put(humanField.getName(), fieldMap.get(humanField.getName()).getText());
                        }
                        argMap.put(argument.getName(), new HumanBeing(humanMap));
                    }
                    try {
                        argMap.put(argument.getName(), handlers.get(argument.getType().getSimpleName())
                                .handle(fieldMap.get(argument.getName()).getText(), argument.getName()));
                    } catch (EmptyFieldException e) {
                        throw new RuntimeException(e);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
                return argMap;
            }
            return null;
        });

        dialog.getDialogPane().setContent(pane);

        return dialog.showAndWait();
    }

    public static void createAlert(String title, String content, Alert.AlertType type, boolean scrollable) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        if (!scrollable) {
            alert.setContentText(content);
        } else {
            TextArea area = new TextArea(content);
            area.setPrefWidth(600);
            area.setPrefHeight(400);
            alert.getDialogPane().setContent(area);
        }
        alert.setResizable(false);
        alert.showAndWait();
    }

    public static Optional<String> createDialog(String title, String content) {
        TextInputDialog dialog = new TextInputDialog();

        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(content);

        dialog.getEditor().textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) {
                dialog.getEditor().setText(oldValue);
            } else if (newValue.length() == 10 && Long.parseLong(newValue) > Integer.MAX_VALUE)
                dialog.getEditor().setText(oldValue);
        });
        return dialog.showAndWait();
    }
}
