package Presentacion.GuiAdmin2;

import Aplicacion.Service.VentaService;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Usuario;
import Dominio.Modelo.Venta;
import Aplicacion.Service.ClienteService;
import Aplicacion.Service.UsuarioService;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminVentasPanel extends JPanel {

    private final ClienteService clienteService = new ClienteService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final VentaService ventaService = new VentaService();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID Venta", "Cliente", "Fecha", "Total"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);

    public AdminVentasPanel() {
        initComponents();
        cargarVentas();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 16));
        setBackground(new Color(244, 247, 246));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel titulo = new JLabel("Ventas registradas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> cargarVentas());
        top.add(btnActualizar, BorderLayout.EAST);

        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarVentas() {
        modelo.setRowCount(0);
        try {
            for (Venta venta : ventaService.findAll()) {
                String nombreCliente = "Desconocido";
                Cliente cliente = clienteService.findById(venta.getIdCliente()).orElse(null);
                if (cliente != null) {
                    Usuario usuario = usuarioService.findById(cliente.getIdUsuario()).orElse(null);
                    if (usuario != null) {
                        nombreCliente = usuario.getNombre();
                    }
                }
                modelo.addRow(new Object[]{
                    venta.getIdVenta(),
                    nombreCliente,
                    venta.getFecha(),
                    venta.getTotal()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las ventas.");
        }
    }
}
