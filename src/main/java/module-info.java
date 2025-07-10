module com.exmple.school.scool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.exmple.school.scool to javafx.fxml;
    opens com.exmple.school.scool.models to javafx.base, javafx.fxml;

    exports com.exmple.school.scool;
    exports com.exmple.school.scool.Controllers;
    opens com.exmple.school.scool.Controllers to javafx.base, javafx.fxml;
    opens com.exmple.school.scool.database to javafx.base, javafx.fxml;
}
