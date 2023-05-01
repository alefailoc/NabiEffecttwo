module com.example.nabieffect {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.nabieffect to javafx.fxml;
    exports com.example.nabieffect;
}