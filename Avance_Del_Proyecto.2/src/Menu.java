import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Platillo> platillos;

    public Menu() {
        platillos = new ArrayList<>();
    }

    public List<Platillo> getPlatillos() {
        return platillos;
    }

    public void agregarPlatillo(Platillo p) throws DatosInvalidosException {


        if (p.getId() <= 0) {
            throw new DatosInvalidosException("El ID del platillo debe ser mayor a 0.");
        }


        if (p.getPrecio() <= 0) {
            throw new DatosInvalidosException("El precio del platillo debe ser mayor a 0.");
        }


        if (p.getIngredientes().isEmpty()) {
            throw new DatosInvalidosException("El platillo debe tener al menos un ingrediente.");
        }

        for (Platillo pl : platillos) {
            if (pl.getId() == p.getId()) {
                throw new DatosInvalidosException("Este ID ya está añadido. Use otro ID.");
            }
        }

        for (Platillo pl : platillos) {
            if (pl.getNombrePlatillo().equalsIgnoreCase(p.getNombrePlatillo())) {
                throw new DatosInvalidosException("Este nombre ya está añadido. Use otro nombre.");
            }
        }

        platillos.add(p);
    }



    public void ordenarPorId() {
        int n = platillos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {

                if (platillos.get(j).getId() > platillos.get(j + 1).getId()) {
                    Platillo temp = platillos.get(j);
                    platillos.set(j, platillos.get(j + 1));
                    platillos.set(j + 1, temp);
                }

            }
        }
    }

    public void ordenarPorPrecio() {
        for (int i = 0; i < platillos.size() - 1; i++) {
            for (int j = 0; j < platillos.size() - i - 1; j++) {

                if (platillos.get(j).getPrecio() > platillos.get(j + 1).getPrecio()) {
                    Platillo temp = platillos.get(j);
                    platillos.set(j, platillos.get(j + 1));
                    platillos.set(j + 1, temp);
                }

            }
        }
    }

    public int buscarPorId(List<Platillo> lista, int idBuscado) {
        int inicio = 0, fin = lista.size() - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int idMedio = lista.get(medio).getId();

            if (idMedio == idBuscado) return medio;
            if (idMedio < idBuscado) inicio = medio + 1;
            else fin = medio - 1;
        }
        return -1;
    }

    public void modificarPlatillo(List<Platillo> lista, int idBuscado, String nuevoNombre, float nuevoPrecio) {
        int indice = buscarPorId(lista, idBuscado);
        if(indice == -1) {
            JOptionPane.showMessageDialog(null, "No existe un platillo con ese ID");
            return;
        }
        Platillo p = lista.get(indice);
        p.setNombrePlatillo(nuevoNombre);
        p.setPrecio(nuevoPrecio);
    }

    public void eliminarPlatillo(List<Platillo> lista, int idBuscado) {
        int indice = buscarPorId(lista, idBuscado);
        if(indice == -1) {
            JOptionPane.showMessageDialog(null, "No existe un platillo con ese ID");
            return;
        }
        lista.remove(indice);
    }
}
