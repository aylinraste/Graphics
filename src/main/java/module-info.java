module com.example.hw {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires com.google.gson;


    opens com.example.hw3 to javafx.fxml;
    exports com.example.hw3;
    exports com.example.hw3.View;
    opens com.example.hw3.View to javafx.fxml;
    opens com.example.hw3.Model to com.google.gson;
//    opens javafx.scene.image to com.google.gson;
}