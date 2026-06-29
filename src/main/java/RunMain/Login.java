package RunMain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Login extends javax.swing.JPanel {

    // Componentes del Login declarados manualmente
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JButton btnIniciarSesion;

    public Login() {
        // 1. Configuramos el layout nulo para poder posicionar las cajas en coordenadas exactas
        this.setLayout(null);

        // 2. Inicializamos los componentes
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btnIniciarSesion = new javax.swing.JButton();

        // Configuración Campo Usuario (Crema)
        txtUsuario.setBackground(new java.awt.Color(250, 239, 241));
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setBorder(null);
        txtUsuario.setCaretColor(new java.awt.Color(245, 201, 201));
        txtUsuario.setOpaque(true);
        this.add(txtUsuario);

        // Configuración Campo Contraseña (Crema)
        txtPassword.setBackground(new java.awt.Color(249, 241, 241));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtPassword.setForeground(new java.awt.Color(51, 51, 51));
        txtPassword.setBorder(null);
        txtPassword.setCaretColor(new java.awt.Color(248, 199, 199));
        txtPassword.setOpaque(true);
        this.add(txtPassword);

        // Configuración Botón Iniciar Sesión (Azul)
        btnIniciarSesion.setText("Iniciar Sesión");
        btnIniciarSesion.setBackground(new java.awt.Color(24, 61, 100)); // Color aproximado de tu logo
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusable(false);
        this.add(btnIniciarSesion);
    }

    /**
     * 🌟 EL TRUCO MÁGICO RESPONSIVO:
     * Este método se ejecuta automáticamente cada vez que la ventana cambia de tamaño.
     * Aquí estiramos la imagen de fondo y recalculamos la posición de las cajas de texto en tiempo real.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Convertimos el objeto Graphics a Graphics2D para activar opciones avanzadas
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;

        // 🌟 TRUCO DE ALTA CALIDAD: Activamos la interpolación Bicúbica y el Antialiasing
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_INTERPOLATION, java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_RENDERING, java.awt.RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Dibujar la imagen de fondo con la nueva calidad premium al 100%
        URL imgUrl = getClass().getResource("/Image/login.png");
        if (imgUrl != null) {
            Image imagen = new ImageIcon(imgUrl).getImage();
            g2d.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
        }

        // 2. Calcular proporciones para las cajas (Mismo cálculo matemático tuyo)
        double escalaX = (double) this.getWidth() / 1410.0;
        double escalaY = (double) this.getHeight() / 770.0;

        // 3. Reposicionar dinámicamente los componentes
        txtUsuario.setBounds((int)(940 * escalaX), (int)(360 * escalaY), (int)(360 * escalaX), (int)(30 * escalaY));
        txtPassword.setBounds((int)(940 * escalaX), (int)(422 * escalaY), (int)(360 * escalaX), (int)(30 * escalaY));
        btnIniciarSesion.setBounds((int)(910 * escalaX), (int)(520 * escalaY), (int)(390 * escalaX), (int)(50 * escalaY));
    }
}