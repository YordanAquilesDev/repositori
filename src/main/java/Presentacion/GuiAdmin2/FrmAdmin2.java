package Presentacion.GuiAdmin2;

import Dominio.Modelo.Usuario;
import RunMain.Main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FrmAdmin2 extends JFrame {

    private final Usuario usuarioLogueado;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel contentPanel = new JPanel(cardLayout);
    private final JLabel lblTitulo = new JLabel("Dashboard");

    public FrmAdmin2(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
        initComponents();
    }

    private void initComponents() {
        setTitle("Administrador - Sistema de Granja");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 720));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(crearSidebar(), BorderLayout.WEST);
        getContentPane().add(crearAreaPrincipal(), BorderLayout.CENTER);
    }

    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(230, 0));
        sidebar.setBackground(new Color(31, 45, 42));
        sidebar.setBorder(new EmptyBorder(24, 16, 24, 16));

        JLabel titulo = new JLabel("GRANJA ADMIN", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(Color.WHITE);
        sidebar.add(titulo, BorderLayout.NORTH);

        JPanel menu = new JPanel(new GridLayout(0, 1, 0, 12));
        menu.setOpaque(false);
        menu.setBorder(new EmptyBorder(32, 0, 32, 0));

        menu.add(crearBotonMenu("Dashboard", "dashboard"));
        menu.add(crearBotonMenu("Animales / Stock", "animales"));
        menu.add(crearBotonMenu("Ventas", "ventas"));
        menu.add(crearBotonMenu("Clientes", "clientes"));
        menu.add(crearBotonMenu("Usuarios", "usuarios"));

        sidebar.add(menu, BorderLayout.CENTER);

        JButton btnSalir = crearBotonMenu("Cerrar sesion", "salir");
        btnSalir.addActionListener(evt -> cerrarSesion());
        sidebar.add(btnSalir, BorderLayout.SOUTH);

        return sidebar;
    }

    private JPanel crearAreaPrincipal() {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(new Color(244, 247, 246));

        wrapper.add(crearHeader(), BorderLayout.NORTH);

        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(new Color(244, 247, 246));
        contentPanel.add(new AdminDashboardPanel(), "dashboard");
        contentPanel.add(new AdminAnimalesPanel(), "animales");
        contentPanel.add(new AdminVentasPanel(), "ventas");
        contentPanel.add(new AdminClientesPanel(), "clientes");
        contentPanel.add(new AdminUsuariosPanel(), "usuarios");

        wrapper.add(contentPanel, BorderLayout.CENTER);
        return wrapper;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(225, 231, 229)),
                new EmptyBorder(18, 24, 18, 24)
        ));

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(new Color(25, 40, 37));
        header.add(lblTitulo, BorderLayout.WEST);

        String nombre = usuarioLogueado != null ? usuarioLogueado.getNombre() : "Admin";
        JLabel usuario = new JLabel("Admin: " + nombre);
        usuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usuario.setForeground(new Color(91, 105, 101));
        header.add(usuario, BorderLayout.EAST);

        return header;
    }

    private JButton crearBotonMenu(String texto, String pantalla) {
        JButton button = new JButton(texto);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(47, 66, 62));
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(12, 14, 12, 14));
        button.setHorizontalAlignment(SwingConstants.LEFT);

        if (!"salir".equals(pantalla)) {
            button.addActionListener(evt -> mostrarPantalla(pantalla, texto));
        }

        return button;
    }

    private void mostrarPantalla(String pantalla, String titulo) {
        lblTitulo.setText(titulo);
        cardLayout.show(contentPanel, pantalla);
    }

    private void cerrarSesion() {
        new Main().setVisible(true);
        dispose();
    }
}
