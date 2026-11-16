package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import uniquindio.edu.co.poo.monedero_virtual.Model.Cliente;
import uniquindio.edu.co.poo.monedero_virtual.Model.Beneficio;
import uniquindio.edu.co.poo.monedero_virtual.Model.TipoBeneficio;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
public class PuntosViewController {

    @FXML
    private Label lblPuntos;

    @FXML
    private Label lblRango;

    @FXML
    private TableView<Beneficio> tablaBeneficios;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;
    private List<Beneficio> beneficios;

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;
        cargarBeneficios();
        actualizarDatos();
    }

    private void cargarBeneficios() {
        beneficios = new ArrayList<>();

        beneficios.add(new Beneficio(
                "Descuento en transferencias",
                "10% de descuento en comisiones",
                100,
                TipoBeneficio.DESCUENTO_TRANSFERENCIA
        ));

        beneficios.add(new Beneficio(
                "Retiros gratis",
                "Un mes sin cargos por retiros",
                500,
                TipoBeneficio.RETIROS_GRATIS
        ));

        beneficios.add(new Beneficio(
                "Bono de saldo",
                "Bono de 50 unidades",
                1000,
                TipoBeneficio.BONO_SALDO
        ));
    }

    private void actualizarDatos() {
        if (cliente == null) {
            return;
        }

        lblPuntos.setText(
                "Puntos disponibles: "
                        + cliente.getCuentaPuntos().getPuntos()
        );

        lblRango.setText(
                "Rango: " + cliente.calcularRango()
        );
    }

    @FXML
    public void canjearBeneficio() {
        if (cliente == null) {
            lblMensaje.setText("No hay un cliente seleccionado.");
            return;
        }

        Beneficio beneficio = tablaBeneficios.getSelectionModel()
                .getSelectedItem();

        if (beneficio == null) {
            lblMensaje.setText("Selecciona un beneficio.");
            return;
        }

        boolean canjeado = cliente.getCuentaPuntos()
                .descontarPuntos(
                        beneficio.getCostoPuntos(),
                        "Canje: " + beneficio.getNombre()
                );

        if (canjeado) {
            lblMensaje.setText("Canje realizado correctamente.");
            actualizarDatos();
        } else {
            lblMensaje.setText("No tienes suficientes puntos.");
        }
    }
}