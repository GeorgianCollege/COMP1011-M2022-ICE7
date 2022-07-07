module ca.georgiancollege.comp1011m2022ice7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ca.georgiancollege.comp1011m2022ice7 to javafx.fxml;
    exports ca.georgiancollege.comp1011m2022ice7;
}