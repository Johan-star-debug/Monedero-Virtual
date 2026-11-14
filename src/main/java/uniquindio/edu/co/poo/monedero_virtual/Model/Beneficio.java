package uniquindio.edu.co.poo.monedero_virtual.Model;

public class Beneficio {

    private String nombre;
    private String descripcion;
    private int costoPuntos;
    private TipoBeneficio tipo;

    public Beneficio(String nombre, String descripcion,
                     int costoPuntos, TipoBeneficio tipo) {

        if (costoPuntos <= 0) {
            throw new IllegalArgumentException(
                    "El costo debe ser positivo"
            );
        }

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoPuntos = costoPuntos;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCostoPuntos() {
        return costoPuntos;
    }

    public TipoBeneficio getTipo() {
        return tipo;
    }
}
