public class Ingrediente {
    private String nombre;
    private int nivelSalud;

    public Ingrediente(String nombre, int nivelSalud) {
        this.nombre = nombre;
        this.nivelSalud = nivelSalud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivelSalud() {
        return nivelSalud;
    }

    public void setNivelSalud(int nivelSalud) {
        this.nivelSalud = nivelSalud;
    }

    @Override
    public String toString() {
        return nombre + " (Salud: " + nivelSalud + ")";
    }
}
