package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

import java.io.IOException;

public class MenuPrincipalViewController {

    @FXML
    private Label lblBienvenida;

    private void cambiarVentana(ActionEvent event, String archivo) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/uniquindio/edu/co/poo/monedero_virtual/view/" + archivo
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "No se pudo abrir la ventana: " + archivo, e
            );
        }
    }

    public void establecerBienvenida(String nombre) {
        lblBienvenida.setText("Bienvenido, " + nombre);
    }

    @FXML
    public void abrirMonederos(ActionEvent event) {
        cambiarVentana(event, "MonederoView.fxml");
    }

    @FXML
    public void abrirTransacciones(ActionEvent event) {
        cambiarVentana(event, "TransaccionView.fxml");
    }

    @FXML
    public void abrirPuntos(ActionEvent event) {
        cambiarVentana(event, "PuntosView.fxml");
    }

    @FXML
    public void abrirHistorial(ActionEvent event) {
        cambiarVentana(event, "HistorialView.fxml");
    }

    @FXML
    public void abrirReportes(ActionEvent event) {
        cambiarVentana(event, "ReportesView.fxml");
    }

    @FXML
    public void abrirNotificaciones(ActionEvent event) {
        cambiarVentana(event, "NotificacionesView.fxml");
    }

    @FXML
    public void abrirProgramaciones(ActionEvent event) {
        cambiarVentana(event, "ProgramacionesView.fxml");
    }
}