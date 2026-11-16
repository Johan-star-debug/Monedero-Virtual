package uniquindio.edu.co.poo.monedero_virtual.ViewController;

import uniquindio.edu.co.poo.monedero_virtual.Model.*;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.math.BigDecimal;
import java.util.List;

public class ReportesViewController {

    @FXML
    private TextArea txtReporte;

    private Cliente cliente;

    private final AnalizadorGastos analizador =
            new AnalizadorGastos();

    public void establecerCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private List<Transaccion> obtenerTransacciones() {

        if (cliente == null) {
            return List.of();
        }

        return cliente.getMonederos()
                .stream()
                .flatMap(m -> m.getHistorial().stream())
                .distinct()
                .toList();
    }

    @FXML
    public void calcularGastos() {

        if (cliente == null) {
            txtReporte.setText("No hay un cliente seleccionado.");
            return;
        }

        BigDecimal total = analizador.calcularTotalGastos(
                obtenerTransacciones()
        );

        txtReporte.setText(
                "REPORTE DE GASTOS\n\n"
                        + "Cliente: " + cliente.getNombre()
                        + "\nGastos totales: $" + total
        );
    }

    @FXML
    public void consultarPromedio() {

        if (cliente == null) {
            txtReporte.setText("No hay un cliente seleccionado.");
            return;
        }

        double promedio = analizador.calcularPromedio(
                obtenerTransacciones()
        );

        txtReporte.setText(
                "ANÁLISIS DE GASTOS\n\n"
                        + "Cliente: " + cliente.getNombre()
                        + "\nPromedio por transacción: $" + promedio
        );
    }
}
