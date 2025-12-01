import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Platillo {
    private int id;
    private String nombrePlatillo;
    private int nivSalud;
    private float precio;
    private List<Ingrediente> ingredientes;
    private Stack<Ingrediente> pilaIngredientes; // ✔ PILA

    public Platillo() {
        this.pilaIngredientes = new Stack<>();  // ✔ PILA
    }

    public Platillo(int id, String nombrePlatillo, float precio) {
        this.id = id;
        this.nombrePlatillo = nombrePlatillo;
        this.precio = precio;
        this.ingredientes = new LinkedList<>();
        this.nivSalud = 0;

        this.pilaIngredientes = new Stack<>();  // ✔ PILA
        calcularNivel();
    }


    public void añadirIngrediente(Ingrediente ingrediente){
        for (Ingrediente ing : pilaIngredientes) {
            if (ing.getNombre().equalsIgnoreCase(ingrediente.getNombre())) {
                JOptionPane.showMessageDialog(null,
                        "El ingrediente '" + ingrediente.getNombre() + "' ya fue agregado a este plato.");
                return;
            }
        }

        pilaIngredientes.push(ingrediente);
    }

    public void añadirIngrerev(Stack<Ingrediente> ingredientesTemp){
        while (!ingredientesTemp.isEmpty()) {
            Ingrediente ing = ingredientesTemp.pop();  // saca último
            this.ingredientes.add(ing);                // agrega a lista ordenada
        }
    }


    public int calcularNivel() {
        int nivel = 0;
        for (Ingrediente ing : ingredientes) {
            nivel += ing.getNivelSalud();
        }
        return nivel;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombrePlatillo() { return nombrePlatillo; }
    public void setNombrePlatillo(String nombrePlatillo) { this.nombrePlatillo = nombrePlatillo; }

    public int getNivSalud() { return nivSalud; }
    public void setNivSalud(int nivSalud) { this.nivSalud = nivSalud; }

    public float getPrecio() { return precio; }
    public void setPrecio(float precio) { this.precio = precio; }


    public Stack<Ingrediente> getPilaIngredientes() { return pilaIngredientes; }

    public List<Ingrediente> getIngredientes() { return ingredientes; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("ID: ").append(id)
                .append("   ")
                .append(nombrePlatillo)
                .append(" - $")
                .append(precio)
                .append("   Ingredientes: ");

        if (ingredientes.isEmpty()) {
            sb.append("Ninguno");
        } else {
            for (Ingrediente ing : ingredientes) {
                sb.append(ing.getNombre())
                        .append(" (Salud: ")
                        .append(ing.getNivelSalud())
                        .append("), ");
            }
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }
}