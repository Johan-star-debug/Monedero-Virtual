package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class NotificacionesViewController extends BaseViewController{

    @FXML
    private ListView<String> listaNotificaciones;

    private GestorNotificaciones gestorNotificaciones;

    public void establecerGestor(GestorNotificaciones gestor) {
        this.gestorNotificaciones = gestor;
        actualizarNotificaciones();
    }

    @FXML
    public void actualizarNotificaciones() {

        if (gestorNotificaciones == null) {
            return;
        }

        listaNotificaciones.getItems().clear();

        gestorNotificaciones.getNotificaciones()
                .forEach(notificacion ->
                        listaNotificaciones.getItems().add(
                                notificacion.getFecha()
                                        + " - "
                                        + notificacion.getMensaje()
                        )
                );
    }
}