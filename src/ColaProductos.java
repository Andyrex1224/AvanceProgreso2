import java.util.LinkedList;
import java.util.Queue;

public class ColaProductos {
    Queue<Platillo> pedidos;

public ColaProductos(){
    pedidos=new LinkedList<Platillo>();
}
public  void agregar(Platillo a){
    pedidos.add(a);
}
public void eliminar(){
    pedidos.poll();
}


    public Queue<Platillo> getPedidos() {
        return pedidos;
    }

}
