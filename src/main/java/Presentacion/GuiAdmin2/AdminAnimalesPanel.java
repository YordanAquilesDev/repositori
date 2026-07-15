package Presentacion.GuiAdmin2;

import Aplicacion.Service.AnimalService;
import Dominio.Modelo.Animal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminAnimalesPanel extends JPanel {

    private final AnimalService animalService = new AnimalService();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Raza", "Nombre", "Sexo", "Edad", "Precio", "Stock", "Estado"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public AdminAnimalesPanel() {
        initComponents();
        cargarAnimales();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 16));
        setBackground(new Color(244, 247, 246));

        JPanel top = new JPanel(new BorderLayout(12, 0));
        top.setOpaque(false);

        JLabel titulo = new JLabel("Listado y stock de animales");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);

        JPanel acciones = new JPanel();
        acciones.setOpaque(false);
        JButton btnEditarPrecio = new JButton("Editar Precio");
        btnEditarPrecio.addActionListener(evt -> editarPrecio());
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> cargarAnimales());
        acciones.add(btnEditarPrecio);
        acciones.add(btnActualizar);
        top.add(acciones, BorderLayout.EAST);

        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarAnimales() {
        modelo.setRowCount(0);
        try {
            for (Animal animal : animalService.findAll()) {
                modelo.addRow(new Object[]{
                        animal.getIdAnimal(),
                        animal.getIdRaza(),
                        animal.getNombre(),
                        animal.getSexo(),
                        animal.getEdad(),
                        animal.getPrecio(),
                        animal.getStock(),
                        animal.getEstado()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los animales.");
        }
    }

    private void editarPrecio() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un animal de la tabla.");
            return;
        }

        int idAnimal = (int) modelo.getValueAt(fila, 0);
        String nombre = (String) modelo.getValueAt(fila, 2);
        double precioActual = (double) modelo.getValueAt(fila, 5);

        String input = JOptionPane.showInputDialog(
                this,
                "Nuevo precio para \"" + nombre + "\":",
                String.valueOf(precioActual)
        );

        if (input == null) return;

        try {
            double nuevoPrecio = Double.parseDouble(input.trim());
            if (nuevoPrecio <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.");
                return;
            }

            Animal animal = animalService.findById(idAnimal).orElse(null);
            if (animal == null) {
                JOptionPane.showMessageDialog(this, "Animal no encontrado.");
                return;
            }
            animal.setPrecio(nuevoPrecio);

            int result = animalService.update(animal);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Precio actualizado a S/ " + String.format("%.2f", nuevoPrecio));
                cargarAnimales();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el precio.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio valido.");
        }
    }
}
