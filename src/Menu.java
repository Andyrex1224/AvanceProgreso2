import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Menu {
    List<Platillo> platillos;
    public Menu(){
    platillos=new ArrayList<Platillo>();
    predefinido();
    }
    public void predefinido(){
    platillos.add(new Platillo(1,"encebollado", 5.99F));
    platillos.add(new Platillo(2,"Menestra", 7F));
    platillos.add(new Platillo(3,"Mocachino", 3.5F));
    platillos.add(new Platillo(4,"Capuccino", 2.4F));
    platillos.add(new Platillo(5,"Batido de mora", 1.4F));

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

    public List<Platillo> getPlatillos() {
        return platillos;
    }
}
