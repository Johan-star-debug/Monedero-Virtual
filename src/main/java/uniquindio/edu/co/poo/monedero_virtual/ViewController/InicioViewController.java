package uniquindio.edu.co.poo.monedero_virtual.ViewController;
import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioViewController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    @FXML
    public <MenuPrincipalViewController> void registrarCliente() {

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

        cliente = new Cliente("CLI-001", nombre, correo);

        cliente.crearMonedero("Monedero principal");

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/uniquindio/edu/co/poo/monedero_virtual/View/MenuPrincipalView.fxml"
                    )
            );

            Parent root = loader.load();

            MenuPrincipalViewController controlador = loader.getController();

            controlador.getClass();

            Stage stage = (Stage) txtNombre.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú principal");
            stage.show();

        } catch (IOException e) {
            lblMensaje.setText(
                    "Error al abrir el menú principal: " + e.getMessage()
            );
            e.printStackTrace();
        }
    }

    public Cliente getCliente() {
        return cliente;
    }
}
