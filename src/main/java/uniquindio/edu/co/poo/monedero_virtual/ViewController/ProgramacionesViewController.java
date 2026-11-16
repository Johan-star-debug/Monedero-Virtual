package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProgramacionesViewController {

    @FXML
    private ComboBox<TipoTransaccion> cmbTipo;

    @FXML
    private ComboBox<Periodicidad> cmbPeriodicidad;

    @FXML
    private ComboBox<Monedero> cmbOrigen;

    @FXML
    private ComboBox<Monedero> cmbDestino;

    @FXML
    private TextField txtMonto;

    @FXML
    private DatePicker fechaProgramada;

    @FXML
    private ListView<String> listaProgramaciones;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    private List<TransaccionProgramada> programaciones =
            new ArrayList<>();

    public void establecerCliente(Cliente cliente) {

        this.cliente = cliente;

        cmbTipo.getItems().setAll(
                TipoTransaccion.DEPOSITO,
                TipoTransaccion.TRANSFERENCIA
        );

        cmbPeriodicidad.getItems().setAll(
                Periodicidad.values()
        );

        if (cliente != null) {
            cmbOrigen.getItems().setAll(
                    cliente.getMonederos()
            );

            cmbDestino.getItems().setAll(
                    cliente.getMonederos()
            );
        }
    }

    @FXML
    public void programarTransaccion() {

        try {
            if (cliente == null) {
                lblMensaje.setText("No hay un cliente seleccionado.");
                return;
            }

            TipoTransaccion tipo = cmbTipo.getValue();
            Periodicidad periodicidad = cmbPeriodicidad.getValue();
            Monedero origen = cmbOrigen.getValue();
            Monedero destino = cmbDestino.getValue();

            if (tipo == null || periodicidad == null
                    || origen == null || fechaProgramada.getValue() == null) {

                lblMensaje.setText("Completa los campos obligatorios.");
                return;
            }

            if (tipo == TipoTransaccion.TRANSFERENCIA
                    && (destino == null || destino == origen)) {

                lblMensaje.setText("Selecciona un destino válido.");
                return;
            }

            BigDecimal monto = new BigDecimal(
                    txtMonto.getText().trim()
            );

            LocalDate fecha = fechaProgramada.getValue();

            LocalDateTime fechaHora = fecha.atStartOfDay();

            if (fechaHora.isBefore(LocalDateTime.now())) {
                lblMensaje.setText("La fecha debe ser futura.");
                return;
            }

            TransaccionProgramada programacion =
                    new TransaccionProgramada(
                            UUID.randomUUID().toString(),
                            origen,
                            tipo == TipoTransaccion.DEPOSITO
                                    ? null : destino,
                            monto,
                            periodicidad,
                            fechaHora,
                            cliente.getCuentaPuntos()
                    );

            programaciones.add(programacion);

            listaProgramaciones.getItems().add(
                    tipo + " - $" + monto
                            + " - " + periodicidad
                            + " - " + fecha
            );

            lblMensaje.setText("Programación registrada.");

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingresa un monto válido.");
        } catch (IllegalArgumentException e) {
            lblMensaje.setText(e.getMessage());
        }
    }
}