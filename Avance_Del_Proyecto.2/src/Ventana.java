import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Stack;

public class Ventana {
    private JPanel Principal;
    private JTabbedPane tabbedPane1;
    private JList<Platillo> lstMenu;
    private JList<Platillo> lstEditar;
    private JList<Ingrediente> lstMos;
    private JButton btnEliminarEle, btnAgregarIn, btnAgregar, btnEditar, btnOrdenarPrecio, btnordenarID, btnBusca;
    private JTextField txtNomIngre, txtNombrePla1, txtPrecio1, txtNomPla2, txtPrecio2, txtBusID;
    private JSpinner spnId, spnNivelSal;
    private JButton btnRetro;

    private Platillo seleccionado = null;
    private Platillo rev = new Platillo();
    private Menu menu = new Menu();

    private DefaultListModel<Platillo> modeloLista = new DefaultListModel<>();
    private DefaultListModel<Ingrediente> modeloIngredientes = new DefaultListModel<>();

    public Ventana() {

        SpinnerNumberModel modeloId = new SpinnerNumberModel(1, 1, 1_000_000, 1);
        spnId.setModel(modeloId);

        SpinnerNumberModel modeloSalud = new SpinnerNumberModel(1, 1, 10, 1);
        spnNivelSal.setModel(modeloSalud);

        lstMenu.setModel(modeloLista);
        lstEditar.setModel(modeloLista);
        lstMos.setModel(modeloIngredientes);

        actualizarListas();
        actualizarLista2();

        btnAgregarIn.addActionListener(e -> {
            String nombreIn = txtNomIngre.getText();
            int salud = (int) spnNivelSal.getValue();
            rev.añadirIngrediente(new Ingrediente(nombreIn, salud));
            actualizarLista2();
        });

        btnAgregar.addActionListener(e -> {
            if (rev.getPilaIngredientes().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Debe agregar al menos 1 ingrediente antes de crear el platillo.");
                return;
            }

            String nombre = txtNombrePla1.getText();
            float precio = Float.parseFloat(txtPrecio1.getText());
            int id = (int) spnId.getValue();

            Platillo platillo = new Platillo(id, nombre, precio);


            platillo.añadirIngrerev(rev.getPilaIngredientes());

            rev = new Platillo();

            try {
                menu.agregarPlatillo(platillo);
            } catch (DatosInvalidosException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

            actualizarListas();
            actualizarLista2();
        });

        btnordenarID.addActionListener(e -> {
            menu.ordenarPorId();
            actualizarListas();
        });

        btnOrdenarPrecio.addActionListener(e -> {
            menu.ordenarPorPrecio();
            actualizarListas();
        });

        lstMenu.addListSelectionListener(e -> {
            if (!lstMenu.getValueIsAdjusting()) {
                seleccionado = lstMenu.getSelectedValue();
            }
        });

        btnEliminarEle.addActionListener(e -> {
            if(seleccionado == null){
                JOptionPane.showMessageDialog(null,"Seleccione un platillo primero");
                return;
            }
            int opcion = JOptionPane.showConfirmDialog(null,
                    "¿Deseas eliminar el platillo: " + seleccionado.getNombrePlatillo() + "?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION);
            if(opcion == JOptionPane.YES_OPTION){
                menu.eliminarPlatillo(menu.getPlatillos(), seleccionado.getId());
                seleccionado = null;
                actualizarListas();
            }
        });

        btnEditar.addActionListener(e -> {
            int id = Integer.parseInt(txtBusID.getText());
            String nuevoNombre = txtNomPla2.getText();
            float nuevoPrecio = Float.parseFloat(txtPrecio2.getText());
            menu.modificarPlatillo(menu.getPlatillos(), id, nuevoNombre, nuevoPrecio);
            actualizarListas();
        });

        btnBusca.addActionListener(e -> {
            menu.ordenarPorId();

            int buscarID = Integer.parseInt(txtBusID.getText());
            int indice = menu.buscarPorId(menu.getPlatillos(), buscarID);

            if (indice != -1) {
                Platillo p = menu.getPlatillos().get(indice);
                txtNomPla2.setText(p.getNombrePlatillo());
                txtPrecio2.setText(String.valueOf(p.getPrecio()));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un platillo con ese ID");
            }
        });

        lstEditar.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    Platillo seleccionado = lstEditar.getSelectedValue();

                    if (seleccionado != null) {
                        txtBusID.setText(String.valueOf(seleccionado.getId()));
                    }
                }
            }
        });
        btnRetro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rev.getPilaIngredientes().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay ingredientes para eliminar.");
                    return;
                }


                Ingrediente eliminado = rev.getPilaIngredientes().pop();

                JOptionPane.showMessageDialog(null,
                        "Ingrediente eliminado: " + eliminado.getNombre());

                actualizarLista2();
            }
        });
    }

    private void actualizarListas(){
        modeloLista.clear();
        List<Platillo> platillos = menu.getPlatillos();
        for(Platillo p : platillos){
            modeloLista.addElement(p);
        }
    }

    private void actualizarLista2(){
        modeloIngredientes.clear();

        Stack<Ingrediente> ingredientes = rev.getPilaIngredientes();

        for (Ingrediente ingrediente : ingredientes) {
            modeloIngredientes.addElement(ingrediente);
        }
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Gestión de Platillos");
        frame.setContentPane(new Ventana().Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
