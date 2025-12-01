import java.util.ArrayList;
import java.util.List;

public class Platillo {
    private int id;
    private String nombre;
    private float precio;
    private int cantidad;
    private String especificacion;

    public Platillo(int id, String nombre, float precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + nombre + " | $" + precio;
    }
}
