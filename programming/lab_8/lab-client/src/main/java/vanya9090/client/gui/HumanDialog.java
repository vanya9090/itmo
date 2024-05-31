//package vanya9090.client.gui;
//
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import vanya9090.common.models.HumanBeing;
//
//import java.lang.reflect.Field;
//
//public class HumanDialog {
//    public HumanDialog() {
//
//    }
//
//    public void set() {
//        for (Field humanField: HumanBeing.class.getDeclaredFields()) {
//            if (humanField.getName().equals("nextId") || humanField.getName().equals("id")) continue;
//            Label label = new Label(humanField.getName());
//            pane.getChildren().add(label);
//
//            TextField field = new TextField();
//            fieldMap.put(humanField.getName(), field);
//            pane.getChildren().add(field);
//        }
//    }
//}
