module de.gfn.dbdemo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens de.gfn.dbdemo to javafx.fxml;
    exports de.gfn.dbdemo;
}