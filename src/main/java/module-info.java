module com.example.parthenopeairquality {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.parthenopeairquality to javafx.fxml;
    exports com.example.parthenopeairquality;
}