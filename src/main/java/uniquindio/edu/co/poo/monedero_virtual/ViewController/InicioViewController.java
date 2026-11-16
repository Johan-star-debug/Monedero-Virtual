package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InicioViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    @FXML
    public void registrarCliente() {

        String nombre = txtNombre.getText().trim();
        String correo = txtCorreo.getText().trim();

        if (nombre.isEmpty() || correo.isEmpty()) {
            lblMensaje.setText("Completa todos los campos.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            lblMensaje.setText("Correo electrónico inválido.");
            return;
        }

        cliente = new Cliente(
                "CLI-001",
                nombre,
                correo
        );

        cliente.crearMonedero("Monedero principal");

        lblMensaje.setText("Cliente registrado correctamente.");
    }

    public Cliente getCliente() {
        return cliente;
    }
}