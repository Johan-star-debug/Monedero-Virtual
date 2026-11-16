package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.List;

public class HistorialViewController {

    @FXML
    private ComboBox<Monedero> cmbMonederos;

    @FXML
    private TableView<Transaccion> tablaHistorial;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente != null) {
            cmbMonederos.getItems().setAll(
                    cliente.getMonederos()
            );
        }
    }

    @FXML
    public void consultarHistorial() {

        Monedero monedero = cmbMonederos.getValue();

        if (monedero == null) {
            lblMensaje.setText("Selecciona un monedero.");
            return;
        }

        List<Transaccion> historial = monedero.getHistorial();

        tablaHistorial.getItems().setAll(historial);

        lblMensaje.setText(
                "Transacciones encontradas: " + historial.size()
        );
    }
}