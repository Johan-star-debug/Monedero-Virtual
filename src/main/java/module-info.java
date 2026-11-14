module uniquindio.edu.co.poo.monedero_virtual {
    requires javafx.controls;
    requires javafx.fxml;

    exports uniquindio.edu.co.poo.monedero_virtual.app;
    exports uniquindio.edu.co.poo.monedero_virtual.model;

    opens uniquindio.edu.co.poo.monedero_virtual.viewcontroller
            to javafx.fxml;
}