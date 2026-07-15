package Presentacion.GuiCliente2;

import Aplicacion.Service.AnimalService;
import Aplicacion.Service.RazaService;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Raza;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ClienteCatalogoPanel extends JPanel {

    private final AnimalService animalService = new AnimalService();
    private final RazaService razaService = new RazaService();
    private Map<Integer, String> razasMap = new HashMap<>();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Raza", "Sexo", "Edad", "Precio", "Stock", "Estado"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public ClienteCatalogoPanel() {
        setLayout(new BorderLayout(0, 12));
        setBackground(new Color(244, 247, 246));
        setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel top = new JPanel(new BorderLayout(12, 0));
        top.setOpaque(false);

        JLabel titulo = new JLabel("Catalogo de Animales");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);

        JButton btnAgregar = new JButton("Agregar al Carrito");
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnAgregar.setBackground(new Color(35, 132, 94));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBorderPainted(false);
        btnAgregar.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(evt -> agregarAlCarrito());

        JPanel panelAcciones = new JPanel();
        panelAcciones.setOpaque(false);
        panelAcciones.add(btnAgregar);
        top.add(panelAcciones, BorderLayout.EAST);

        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabla.getTableHeader().setBackground(new Color(230, 237, 235));
        tabla.getTableHeader().setForeground(new Color(25, 40, 37));
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        cargarRazas();
        cargarAnimales();
    }

    private void cargarRazas() {
        razasMap.clear();
        for (Raza raza : razaService.findAll()) {
            razasMap.put(raza.getIdRaza(), raza.getNombre());
        }
    }

    private void cargarAnimales() {
        modelo.setRowCount(0);
        try {
            for (Animal animal : animalService.findAll()) {
                String nombreRaza = razasMap.getOrDefault(animal.getIdRaza(), "N/A");
                modelo.addRow(new Object[]{
                        animal.getIdAnimal(),
                        animal.getNombre(),
                        nombreRaza,
                        animal.getSexo(),
                        animal.getEdad(),
                        String.format("S/ %.2f", animal.getPrecio()),
                        animal.getStock(),
                        animal.getEstado()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar animales: " + e.getMessage());
        }
    }

    private void agregarAlCarrito() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona un animal de la tabla.");
            return;
        }
        int modeloFila = tabla.convertRowIndexToModel(fila);
        int idAnimal = (int) modelo.getValueAt(modeloFila, 0);

        Animal animal = animalService.findById(idAnimal).orElse(null);
        if (animal == null) {
            JOptionPane.showMessageDialog(this, "No se encontro el animal.");
            return;
        }
        if ("Vendido".equals(animal.getEstado())) {
            JOptionPane.showMessageDialog(this, "Este animal ya fue vendido.");
            return;
        }

        java.awt.Component parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (parent instanceof FrmCliente2) {
            ((FrmCliente2) parent).agregarAlCarrito(animal);
        }
    }

    public void recargar() {
        cargarRazas();
        cargarAnimales();
    }
}
