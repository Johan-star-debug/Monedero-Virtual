package uniquindio.edu.co.poo.monedero_virtual.Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

    private String id;
    private String nombre;
    private String correo;
    private List<Monedero> monederos;
    private CuentaPuntos cuentaPuntos;

    public Cliente(String id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.monederos = new ArrayList<>();
        this.cuentaPuntos = new CuentaPuntos();
    }

    public Monedero crearMonedero(String nombre) {
        Monedero monedero = new Monedero(
                "MON-" + (monederos.size() + 1),
                nombre
        );

        monederos.add(monedero);
        return monedero;
    }

    public RangoCliente calcularRango() {
        int puntos = cuentaPuntos.getPuntos();

        if (puntos <= 500) {
            return RangoCliente.BRONCE;
        } else if (puntos <= 1000) {
            return RangoCliente.PLATA;
        } else if (puntos <= 5000) {
            return RangoCliente.ORO;
        }

        return RangoCliente.PLATINO;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Monedero> getMonederos() {
        return new ArrayList<>(monederos);
    }

    public CuentaPuntos getCuentaPuntos() {
        return cuentaPuntos;
    }
}