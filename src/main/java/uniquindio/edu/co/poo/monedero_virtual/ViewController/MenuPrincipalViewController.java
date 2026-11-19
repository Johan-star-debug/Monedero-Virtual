package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipalViewController {

    @FXML
    private Label lblBienvenida;

    private Cliente cliente;

    public void establecerCliente(Cliente cliente) {

        this.cliente = cliente;

        if (lblBienvenida != null && cliente != null) {
            lblBienvenida.setText(
                    "Bienvenido, " + cliente.getNombre()
            );
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    @FXML
    private void abrirMonederos() {
        cargarVista("MonederoView.fxml", "Monederos");
    }

    @FXML
    private void abrirTransacciones() {
        cargarVista("TransaccionView.fxml", "Transacciones");
    }

    @FXML
    private void abrirPuntos() {
        cargarVista("PuntosView.fxml", "Sistema de puntos");
    }

    @FXML
    private void abrirHistorial() {
        cargarVista("HistorialView.fxml", "Historial");
    }

    @FXML
    private void abrirReportes() {
        cargarVista("ReportesView.fxml", "Reportes");
    }

    @FXML
    private void abrirNotificaciones() {
        cargarVista("NotificacionesView.fxml", "Notificaciones");
    }

    @FXML
    private void abrirProgramaciones() {
        cargarVista("ProgramacionesView.fxml", "Programaciones");
    }

    private void cargarVista(String archivo, String titulo) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/uniquindio/edu/co/poo/monedero_virtual/View/"
                                    + archivo
                    )
            );

            Parent root = loader.load();

            Object controlador = loader.getController();

            // Compartir el mismo cliente con las pantallas secundarias.
            if (controlador instanceof BaseViewController) {

                BaseViewController base =
                        (BaseViewController) controlador;

                base.establecerCliente(cliente);
            }

            Stage stage = (Stage) lblBienvenida
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

            System.out.println(
                    "Error al abrir la interfaz: " + archivo
            );
        }
    }
}