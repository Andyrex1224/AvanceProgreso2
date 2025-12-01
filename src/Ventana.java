import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Ventana {
    private JPanel principal;
    private JTabbedPane tabbedPane1;
    private JList lstMenu;
    private JTextField txtOpcional;
    private JButton btnAgregar;
    private JList lstListar;
    private JButton btnActualizar;
    private JButton btnRetroceder;
    private JSpinner spnAgregar;
    private JSpinner spnActualizar;
    private JTextField txtActualizar;
    private JButton btnConfirmar;
    ColaProductos colaProductos = new ColaProductos();
    Menu menu = new Menu();
    SpinnerNumberModel modelo = new SpinnerNumberModel(1,0,100,1);

    public void listar(){
        DefaultListModel<Object> defaultListModel= new DefaultListModel<>();
        for(Platillo a: colaProductos.getPedidos()){
            String texto = "ID: "+ a.getId() +  " | " + a.getNombre() + " | " + a.getPrecio() + " | " + a.getCantidad() + " | " + a.getEspecificacion();
            defaultListModel.addElement(texto);
        }
        lstListar.setModel(defaultListModel);
    }
    public void listar2(){
        DefaultListModel<Platillo> defaultListModel= new DefaultListModel<>();
        for(Platillo a: menu.getPlatillos()){
            defaultListModel.addElement(a);
        }
        lstMenu.setModel(defaultListModel);
    }

    public Ventana() {
        listar2();
        spnAgregar.setModel(modelo);
        spnActualizar.setModel(modelo);
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstMenu.getSelectedValue() != null){
                    Platillo aux = (Platillo) lstMenu.getSelectedValue();
                    if(!Objects.equals(txtOpcional.getText(), "")){
                        String especificacion = txtOpcional.getText();
                        int cantidad = (int) spnAgregar.getValue();
                        aux.setCantidad(cantidad);
                        aux.setEspecificacion(especificacion);
                        colaProductos.agregar(aux);
                        listar();
                    }else{
                        String especificacion = "No hay especificaicones";
                        int cantidad = (int) spnAgregar.getValue();
                        aux.setCantidad(cantidad);
                        aux.setEspecificacion(especificacion);
                        colaProductos.agregar(aux);
                        listar();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Para esta operacion tiene que elegir un elemento");
                }


            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lstListar.getSelectedValue() != null){
                    Platillo aux = (Platillo) lstMenu.getSelectedValue();
                    aux.setCantidad((Integer) spnActualizar.getValue());
                    if(!Objects.equals(txtActualizar.getText(), "")){
                        aux.setEspecificacion(txtActualizar.getText());
                        listar();
                    }else{
                        aux.setEspecificacion("No hay especificaicones");
                        listar();
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Para esta operacion tiene que elegir un elemento");
                }
            }
        });
        btnRetroceder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colaProductos.eliminar();
                listar();
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcion= JOptionPane.showConfirmDialog(null,
                        "¿Deseas ENVIAR el platillo?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if(opcion == JOptionPane.YES_OPTION){
                    JOptionPane.showMessageDialog(null, "Espere a que su pedido este listo...");
                }
            }

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
