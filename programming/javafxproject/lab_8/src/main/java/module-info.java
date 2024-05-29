module com.vanya9090.lab_8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.vanya9090.lab_8 to javafx.fxml;
    exports com.vanya9090.lab_8;
}