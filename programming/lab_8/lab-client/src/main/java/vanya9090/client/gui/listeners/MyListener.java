package vanya9090.client.gui.listeners;

import java.awt.*;

public abstract class MyListener<T> {
    TextField textField;
    public MyListener(TextField textField) {
        this.textField = textField;
    }
    public abstract T listen(String observableValue, String oldValue, String newValue);
}
