package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BaseViewController {

    protected Cliente cliente;

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @FXML
    protected void volverMenu(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/uniquindio/edu/co/poo/monedero_virtual/View/MenuPrincipalView.fxml"
                    )
            );

            Parent root = loader.load();

            MenuPrincipalViewController controlador = loader.getController();
            controlador.establecerCliente(cliente);

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Menú principal");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}