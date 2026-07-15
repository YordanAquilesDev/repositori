package Presentacion.GuiAdmin2;

import Aplicacion.Service.ClienteService;
import Aplicacion.Service.UsuarioService;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AdminClientesPanel extends JPanel {

    private final ClienteService clienteService = new ClienteService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID Cliente", "Nombre Cliente", "DNI", "Telefono", "Direccion"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public AdminClientesPanel() {
        initComponents();
        cargarClientes();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 16));
        setBackground(new Color(244, 247, 246));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel titulo = new JLabel("Clientes registrados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> cargarClientes());
        top.add(btnActualizar, BorderLayout.EAST);

        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarClientes() {
        modelo.setRowCount(0);
        try {
            for (Cliente cliente : clienteService.findAll()) {
                String nombreUsuario = usuarioService.findById(cliente.getIdUsuario())
                        .map(Usuario::getNombre)
                        .orElse("N/A");
                modelo.addRow(new Object[]{
                        cliente.getIdCliente(),
                        nombreUsuario,
                        cliente.getDni(),
                        cliente.getTelefono(),
                        cliente.getDireccion()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar los clientes.");
        }
    }
}
