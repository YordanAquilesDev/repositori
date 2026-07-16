package RunMain;

import Aplicacion.Service.UsuarioService;
import Dominio.Modelo.Usuario;
import Presentacion.GuiAdmin.FrmAdmin;
import Presentacion.GuiCliente.FrmCliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class LoginF2 extends JPanel {

    private final UsuarioService usuarioService = new UsuarioService();
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;

    public LoginF2() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        setBackground(new Color(244, 247, 246));

        RoundedPanel card = new RoundedPanel(28, Color.WHITE);
        card.setLayout(new GridBagLayout());
        card.setBorder(new EmptyBorder(36, 44, 36, 44));
        card.setPreferredSize(new Dimension(440, 560));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel logo = crearLogo();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 24, 0);
        card.add(logo, gbc);

        JLabel title = new JLabel("Iniciar sesion", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(25, 40, 37));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 8, 0);
        card.add(title, gbc);

        JLabel subtitle = new JLabel("Accede al sistema de gestion", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        subtitle.setForeground(new Color(105, 119, 115));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 34, 0);
        card.add(subtitle, gbc);

        JLabel lblCorreo = crearLabel("Correo");
        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 8, 0);
        card.add(lblCorreo, gbc);

        txtCorreo = crearTextField();
        txtCorreo.setText("juan@gmail.com");
        txtCorreo.addActionListener(evt -> txtPassword.requestFocusInWindow());
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 18, 0);
        card.add(txtCorreo, gbc);

        JLabel lblPassword = crearLabel("Contrasena");
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 8, 0);
        card.add(lblPassword, gbc);

        txtPassword = crearPasswordField();
        txtPassword.setText("123456");
        txtPassword.addActionListener(evt -> iniciarSesion());
        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 28, 0);
        card.add(txtPassword, gbc);

        btnIniciarSesion = crearBotonPrincipal();
        btnIniciarSesion.addActionListener(evt -> iniciarSesion());
        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 22, 0);
        card.add(btnIniciarSesion, gbc);

        JLabel footer = new JLabel("Sistema de gestion de granja", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        footer.setForeground(new Color(120, 132, 128));
        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 0);
        card.add(footer, gbc);

        JPanel shell = new JPanel(new BorderLayout());
        shell.setOpaque(false);
        shell.setBorder(new EmptyBorder(24, 24, 24, 24));
        shell.add(card, BorderLayout.CENTER);

        add(shell, new GridBagConstraints());
    }

    private JLabel crearLogo() {
        JLabel logo = new JLabel("GRANJA", SwingConstants.CENTER);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        logo.setForeground(new Color(35, 132, 94));

        java.net.URL logoUrl = getClass().getResource("/Image/logo.png");
        if (logoUrl != null) {
            Image image = new ImageIcon(logoUrl).getImage().getScaledInstance(78, 78, Image.SCALE_SMOOTH);
            logo.setIcon(new ImageIcon(image));
            logo.setText("");
        }

        return logo;
    }

    private JLabel crearLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(new Color(53, 66, 62));
        return label;
    }

    private JTextField crearTextField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(0, 46));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(new Color(30, 45, 41));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(218, 226, 223), 1),
                new EmptyBorder(0, 14, 0, 14)
        ));
        return field;
    }

    private JPasswordField crearPasswordField() {
        JPasswordField field = new JPasswordField();
        field.setPreferredSize(new Dimension(0, 46));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        field.setForeground(new Color(30, 45, 41));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(218, 226, 223), 1),
                new EmptyBorder(0, 14, 0, 14)
        ));
        return field;
    }

    private JButton crearBotonPrincipal() {
        JButton button = new JButton("Iniciar sesion");
        button.setPreferredSize(new Dimension(0, 48));
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(35, 132, 94));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void iniciarSesion() {
        String correo = txtCorreo.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese correo y contrasena.");
            return;
        }

        btnIniciarSesion.setEnabled(false);

        try {
            Usuario usuario = usuarioService.login(correo, password);

            if (usuario == null) {
                JOptionPane.showMessageDialog(this, "Usuario o contrasena incorrectos.");
                return;
            }

            abrirPantallaPorRol(usuario);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudo validar el usuario con la base de datos.");
        } finally {
            btnIniciarSesion.setEnabled(true);
        }
    }

    private void abrirPantallaPorRol(Usuario usuario) {
        if (usuario.getIdRol() == 1) {
            new FrmAdmin(usuario).setVisible(true);
            cerrarVentanaActual();
        } else if (usuario.getIdRol() == 2) {
            new FrmCliente(usuario).setVisible(true);
            cerrarVentanaActual();
        } else {
            JOptionPane.showMessageDialog(this, "Rol no reconocido.");
        }
    }

    private void cerrarVentanaActual() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose();
        }
    }

    private static class RoundedPanel extends JPanel {

        private final int radius;
        private final Color background;

        RoundedPanel(int radius, Color background) {
            this.radius = radius;
            this.background = background;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0, 0, 0, 22));
            g2.fillRoundRect(8, 10, getWidth() - 16, getHeight() - 16, radius, radius);
            g2.setColor(background);
            g2.fillRoundRect(0, 0, getWidth() - 16, getHeight() - 16, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
