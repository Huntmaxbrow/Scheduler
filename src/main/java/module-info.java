module com.mycompany.scheduler {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.scheduler to javafx.fxml;
    exports com.mycompany.scheduler;
}
