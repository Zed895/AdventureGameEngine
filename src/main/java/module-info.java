module com.mycompany.adventuregameengine {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    //requires java.naming;
    //requires java.management;

    opens com.mycompany.adventuregameengine to javafx.fxml, java.sql;
    exports com.mycompany.adventuregameengine;
}