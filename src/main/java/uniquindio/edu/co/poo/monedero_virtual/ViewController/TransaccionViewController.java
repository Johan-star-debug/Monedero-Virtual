package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import java.math.BigDecimal;
import java.util.UUID;

public class TransaccionViewController {

    @FXML
    private ComboBox<TipoTransaccion> cmbTipoTransaccion;

    @FXML
    private ComboBox<Monedero> cmbOrigen;

    @FXML
    private ComboBox<Monedero> cmbDestino;

    @FXML
    private TextField txtMonto;

    @FXML
    private Label lblMensaje;

    private Cliente cliente;

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;

        cmbTipoTransaccion.getItems().setAll(
                TipoTransaccion.values()
        );

        if (cliente != null) {
            cmbOrigen.getItems().setAll(cliente.getMonederos());
            cmbDestino.getItems().setAll(cliente.getMonederos());
        }
    }

    @FXML
    public void procesarTransaccion() {

        try {
            if (cliente == null) {
                lblMensaje.setText("No hay un cliente seleccionado.");
                return;
            }

            TipoTransaccion tipo = cmbTipoTransaccion.getValue();
            Monedero origen = cmbOrigen.getValue();

            if (tipo == null || origen == null) {
                lblMensaje.setText("Selecciona los datos necesarios.");
                return;
            }

            BigDecimal monto = new BigDecimal(
                    txtMonto.getText().trim()
            );

            String id = UUID.randomUUID().toString();

            switch (tipo) {

                case DEPOSITO:
                    new Deposito(
                            id, monto, origen,
                            cliente.getCuentaPuntos()
                    ).ejecutar();
                    break;

                case RETIRO:
                    new Retiro(
                            id, monto, origen,
                            cliente.getCuentaPuntos()
                    ).ejecutar();
                    break;

                case TRANSFERENCIA:
                    Monedero destino = cmbDestino.getValue();

                    if (destino == null || destino == origen) {
                        lblMensaje.setText(
                                "Selecciona un destino diferente."
                        );
                        return;
                    }

                    new Transferencia(
                            id, monto, origen, destino,
                            cliente.getCuentaPuntos()
                    ).ejecutar();
                    break;
            }

            lblMensaje.setText("Transacción realizada correctamente.");

        } catch (NumberFormatException e) {
            lblMensaje.setText("Ingresa un monto válido.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            lblMensaje.setText(e.getMessage());
        }
    }
}