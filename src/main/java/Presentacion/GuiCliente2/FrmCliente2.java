package Presentacion.GuiCliente2;
import ArchivoPDF.PDF;
import Aplicacion.Service.ClienteService;
import Aplicacion.Service.VentaService;
import Aplicacion.Service.UsuarioService;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.DetalleVenta;
import Dominio.Modelo.Usuario;
import Dominio.Modelo.Venta;
import RunMain.Main;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmCliente2 extends JFrame {

    private final Usuario usuario;
    private final Cliente cliente;
    private final ClienteService clienteService = new ClienteService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final VentaService ventaService = new VentaService();
    private final ClienteCatalogoPanel catalogoPanel;
    private final CarritoPanel carritoPanel;
    private final JLabel lblTitulo = new JLabel("Catalogo");

    public FrmCliente2(Usuario usuario) {
        this.usuario = usuario;
        this.cliente = buscarCliente(usuario.getIdUsuario());

        if (this.cliente == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontro un perfil de cliente asociado a este usuario.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            this.catalogoPanel = null;
            this.carritoPanel = null;
            return;
        }

        this.catalogoPanel = new ClienteCatalogoPanel();
        this.carritoPanel = new CarritoPanel();
        initComponents();
    }

    private Cliente buscarCliente(int idUsuario) {
        Optional<Cliente> opt = clienteService.findByUsuarioId(idUsuario);
        return opt.orElse(null);
    }

    private void initComponents() {
        setTitle("Cliente - Sistema de Granja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 720));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(crearHeader(), BorderLayout.NORTH);
        getContentPane().add(catalogoPanel, BorderLayout.CENTER);
        getContentPane().add(carritoPanel, BorderLayout.EAST);
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(225, 231, 229)),
                new EmptyBorder(18, 24, 18, 24)
        ));

        JPanel panelIzq = new JPanel(new BorderLayout(16, 0));
        panelIzq.setOpaque(false);

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(25, 40, 37));
        panelIzq.add(lblTitulo, BorderLayout.WEST);

        JLabel lblBienvenida = new JLabel("Bienvenido, " + usuario.getNombre());
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblBienvenida.setForeground(new Color(91, 105, 101));
        panelIzq.add(lblBienvenida, BorderLayout.CENTER);

        header.add(panelIzq, BorderLayout.WEST);

        javax.swing.JButton btnSalir = new javax.swing.JButton("Cerrar sesion");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnSalir.setBackground(new Color(220, 53, 69));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(evt -> cerrarSesion());
        header.add(btnSalir, BorderLayout.EAST);

        return header;
    }

    private void cerrarSesion() {
        new Main().setVisible(true);
        dispose();
    }

    public void agregarAlCarrito(Dominio.Modelo.Animal animal) {
        carritoPanel.agregarAnimal(animal);
    }

    public void procesarCompra() {
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "No hay perfil de cliente.");
            return;
        }
        if (carritoPanel.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio.");
            return;
        }

        try {
            Venta venta = new Venta();
            venta.setIdCliente(cliente.getIdCliente());

            for (CarritoPanel.CarritoItem item : carritoPanel.getItems()) {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setIdAnimal(item.animal.getIdAnimal());
                detalle.setCantidad(item.cantidad);
                detalle.setPrecio(item.animal.getPrecio());
                detalle.setSubtotal(item.getSubtotal());
                venta.addDetalle(detalle);
            }

            int resultado = ventaService.save(venta);
            

            if (resultado == 1) {

    PDF.generarBoleta(
            usuario,
            cliente,
            carritoPanel.getItems(),
            carritoPanel.getTotal()
    );

    carritoPanel.limpiar();
    catalogoPanel.recargar();

    JOptionPane.showMessageDialog(this,
            "Compra realizada correctamente.");

} else {
                JOptionPane.showMessageDialog(this,
                        "No se pudo completar la compra. Intente de nuevo.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this,
                    "Error al procesar la compra: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
}
