module uniquindio.edu.co.poo.monedero_virtual {
    requires javafx.controls;
    requires javafx.fxml;

    exports uniquindio.edu.co.poo.monedero_virtual.App;
    exports uniquindio.edu.co.poo.monedero_virtual.Model;

    opens uniquindio.edu.co.poo.monedero_virtual.viewcontroller
            to javafx.fxml;
}