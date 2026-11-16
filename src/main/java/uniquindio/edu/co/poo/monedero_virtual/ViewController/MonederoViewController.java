package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;
import uniquindio.edu.co.poo.monedero_virtual.Model.Monedero;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MonederoViewController {

    @FXML
    private ComboBox<Monedero> cmbMonederos;

    @FXML
    private TextField txtNombreMonedero;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;
        actualizarMonederos();
    }

    private void actualizarMonederos() {
        if (cliente == null) {
            return;
        }

        cmbMonederos.getItems().setAll(cliente.getMonederos());

        cmbMonederos.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Monedero item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre());
            }
        });

        cmbMonederos.setCellFactory(list -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Monedero item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre());
            }
        });
    }

    @FXML
    public void crearMonedero() {

        if (cliente == null) {
            lblMensaje.setText("No hay un cliente seleccionado.");
            return;
        }

        String nombre = txtNombreMonedero.getText().trim();

        if (nombre.isEmpty()) {
            lblMensaje.setText("Escribe un nombre.");
            return;
        }

        cliente.crearMonedero(nombre);

        actualizarMonederos();

        txtNombreMonedero.clear();
        lblMensaje.setText("Monedero creado correctamente.");
    }

    @FXML
    public void consultarSaldo() {

        Monedero seleccionado = cmbMonederos.getValue();

        if (seleccionado == null) {
            lblMensaje.setText("Selecciona un monedero.");
            return;
        }

        lblSaldo.setText(
                "Saldo: $" + seleccionado.getSaldo()
        );
    }
}