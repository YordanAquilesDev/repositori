package Presentacion.GuiAdmin2;

import Aplicacion.Service.UsuarioService;
import Dominio.Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminUsuariosPanel extends JPanel {

    private final UsuarioService usuarioService = new UsuarioService();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID", "Rol", "Nombre", "Correo", "Estado"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final JTextField txtBuscar = new JTextField(24);
    private final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);

    public AdminUsuariosPanel() {
        initComponents();
        cargarUsuarios();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 16));
        setBackground(new Color(244, 247, 246));

        JPanel top = new JPanel(new BorderLayout(12, 0));
        top.setOpaque(false);

        JLabel titulo = new JLabel("Usuarios del sistema");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);

        JPanel acciones = new JPanel();
        acciones.setOpaque(false);
        txtBuscar.setToolTipText("Buscar por nombre o correo");
        txtBuscar.addActionListener(evt -> filtrar());
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(evt -> filtrar());
        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(evt -> editarUsuarioSeleccionado());
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> cargarUsuarios());
        acciones.add(txtBuscar);
        acciones.add(btnBuscar);
        acciones.add(btnEditar);
        acciones.add(btnActualizar);
        top.add(acciones, BorderLayout.EAST);

        tabla.setRowSorter(sorter);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0);
        try {
            for (Usuario usuario : usuarioService.findAll()) {
                modelo.addRow(new Object[]{
                        usuario.getIdUsuario(),
                        usuario.getIdRol(),
                        usuario.getNombre(),
                        usuario.getCorreo(),
                        usuario.isEstado() ? "Activo" : "Inactivo"
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los usuarios.");
        }
    }

    private void filtrar() {
        String texto = txtBuscar.getText().trim();
        if (texto.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto));
        }
    }

    private void editarUsuarioSeleccionado() {
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario.");
            return;
        }

        int modelRow = tabla.convertRowIndexToModel(selectedRow);
        int idUsuario = (int) modelo.getValueAt(modelRow, 0);
        Optional<Usuario> usuarioEncontrado = usuarioService.findById(idUsuario);

        if (usuarioEncontrado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontro el usuario seleccionado.");
            return;
        }

        Usuario usuario = usuarioEncontrado.get();
        JTextField txtNombre = new JTextField(usuario.getNombre());
        JTextField txtCorreo = new JTextField(usuario.getCorreo());
        JComboBox<String> cmbRol = new JComboBox<>(new String[]{"1 - ADMIN", "2 - CLIENTE"});
        cmbRol.setSelectedIndex(usuario.getIdRol() == 1 ? 0 : 1);
        JCheckBox chkActivo = new JCheckBox("Activo", usuario.isEstado());

        JPanel form = new JPanel(new GridLayout(0, 1, 0, 8));
        form.setBorder(new EmptyBorder(8, 8, 8, 8));
        form.add(new JLabel("Nombre"));
        form.add(txtNombre);
        form.add(new JLabel("Correo"));
        form.add(txtCorreo);
        form.add(new JLabel("Rol"));
        form.add(cmbRol);
        form.add(chkActivo);

        int result = JOptionPane.showConfirmDialog(this, form, "Editar usuario", JOptionPane.OK_CANCEL_OPTION);
        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        usuario.setNombre(txtNombre.getText().trim());
        usuario.setCorreo(txtCorreo.getText().trim());
        usuario.setIdRol(cmbRol.getSelectedIndex() == 0 ? 1 : 2);
        usuario.setEstado(chkActivo.isSelected());

        try {
            int filas = usuarioService.update(usuario);
            if (filas > 0) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado.");
                cargarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "No se actualizo el usuario.");
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar el usuario.");
        }
    }
}
