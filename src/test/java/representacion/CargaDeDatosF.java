package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CargaDeDatosF extends JPanel {

    // Componentes renombrados profesionalmente
    private JComboBox<String> cmbTipoOperacion;
    private JComboBox<String> cmbProveedor;
    private JComboBox<String> cmbProducto;

    private JTextField txtNuevoProducto;
    private JTextField txtCantidad;
    private JTextField txtTipoMedida;
    private JTextField txtPrecioMedida;

    private JButton btnProcesar;
    private JButton btnRegistrarProveedor;

    public CargaDeDatosF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        // Fondo gris claro para contrastar con la "Tarjeta/Card" del formulario
        setBackground(new Color(245, 246, 250));
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void initComponents() {
        // Inicialización de componentes
        cmbTipoOperacion = new JComboBox<>(new String[] { "Registrar Compra", "Registrar Venta", "Registrar Consumo" });
        cmbProveedor = new JComboBox<>(new String[] { "Seleccionar Proveedor...", "Proveedor Juan", "Distribuidora Alimentos S.A." });
        cmbProducto = new JComboBox<>(new String[] { "Seleccionar Producto...", "Maíz Premium (Saco)", "Balanceado Crecimiento" });

        txtNuevoProducto = new JTextField();
        txtCantidad = new JTextField();
        txtTipoMedida = new JTextField();
        txtPrecioMedida = new JTextField();

        btnProcesar = new JButton("Procesar Transacción");
        btnRegistrarProveedor = new JButton("Nuevo Proveedor");

        // --- DISEÑO DE LA "CARD" CONTENEDORA ---
        JPanel cardFormulario = new JPanel(new GridBagLayout());
        cardFormulario.setBackground(Color.WHITE);
        cardFormulario.setBorder(new EmptyBorder(30, 35, 30, 35));

        // Efecto de arco o curvatura en la tarjeta usando propiedades de FlatLaf
        cardFormulario.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0); // Margen interno entre filas
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        // 1. Título General de la Acción
        JLabel lblTitulo = new JLabel("Formulario de Movimientos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(new Color(33, 33, 33));
        cardFormulario.add(lblTitulo, gbc);

        // 2. Selector de Operación Principal
        gbc.gridy++;
        cardFormulario.add(new JLabel("Tipo de Operación:"), gbc);
        gbc.gridy++;
        cardFormulario.add(cmbTipoOperacion, gbc);

        // Separador Visual Sutil
        gbc.gridy++;
        cardFormulario.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // 3. Bloque Proveedor
        gbc.gridy++;
        JPanel panelProv = new JPanel(new BorderLayout(10, 0));
        panelProv.setOpaque(false);
        panelProv.add(new JLabel("Proveedor:"), BorderLayout.WEST);
        panelProv.add(btnRegistrarProveedor, BorderLayout.EAST);
        cardFormulario.add(panelProv, gbc);

        gbc.gridy++;
        cardFormulario.add(cmbProveedor, gbc);

        // 4. Bloque Producto
        gbc.gridy++;
        cardFormulario.add(new JLabel("Producto Existente:"), gbc);
        gbc.gridy++;
        cardFormulario.add(cmbProducto, gbc);

        gbc.gridy++;
        cardFormulario.add(new JLabel("¿Es un producto nuevo? Regístralo aquí:"), gbc);
        gbc.gridy++;
        cardFormulario.add(txtNuevoProducto, gbc);

        // Separador Visual Sutil
        gbc.gridy++;
        cardFormulario.add(Box.createRigidArea(new Dimension(0, 10)), gbc);

        // 5. Grid de Tres Columnas para Cantidades y Precios
        gbc.gridy++;
        JPanel rowDetalles = new JPanel(new GridLayout(1, 3, 15, 0));
        rowDetalles.setOpaque(false);

        JPanel col1 = new JPanel(new BorderLayout(0, 5)); col1.setOpaque(false);
        col1.add(new JLabel("Cantidad:"), BorderLayout.NORTH); col1.add(txtCantidad, BorderLayout.CENTER);

        JPanel col2 = new JPanel(new BorderLayout(0, 5)); col2.setOpaque(false);
        col2.add(new JLabel("Tipo Medida (Kg/U):"), BorderLayout.NORTH); col2.add(txtTipoMedida, BorderLayout.CENTER);

        JPanel col3 = new JPanel(new BorderLayout(0, 5)); col3.setOpaque(false);
        col3.add(new JLabel("Precio Unitario:"), BorderLayout.NORTH); col3.add(txtPrecioMedida, BorderLayout.CENTER);

        rowDetalles.add(col1);
        rowDetalles.add(col2);
        rowDetalles.add(col3);
        cardFormulario.add(rowDetalles, gbc);

        // 6. Botón de Acción Principal (Procesar)
        gbc.gridy++;
        gbc.insets = new Insets(25, 0, 0, 0); // Más separación para el botón final
        cardFormulario.add(btnProcesar, gbc);

        // Añadir la tarjeta centrada en el panel principal
        GridBagConstraints gbcCentrar = new GridBagConstraints();
        gbcCentrar.gridx = 0;
        gbcCentrar.gridy = 0;
        gbcCentrar.weightx = 1.0;
        gbcCentrar.weighty = 1.0;
        gbcCentrar.fill = GridBagConstraints.NONE; // No se estira feamente
        add(cardFormulario, gbcCentrar);

        // Manejadores de Eventos (ActionListeners) incorporados limpiamente
        btnRegistrarProveedor.addActionListener(e -> btnRegistrarProveedorActionPerformed());
        btnProcesar.addActionListener(e -> btnProcesarActionPerformed());
    }

    private void aplicarEstilosModernos() {
        // Estilo FlatLaf para Inputs (Bordes redondeados y placeholders limpios)
        txtNuevoProducto.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej: Alimento balanceado premium");
        txtCantidad.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "0.00");
        txtTipoMedida.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Sacos / Kg");
        txtPrecioMedida.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "S/. 0.00");

        // Altura uniforme para componentes interactivos
        int height = 35;
        cmbTipoOperacion.setPreferredSize(new Dimension(cmbTipoOperacion.getPreferredSize().width, height));
        cmbProveedor.setPreferredSize(new Dimension(cmbProveedor.getPreferredSize().width, height));
        cmbProducto.setPreferredSize(new Dimension(cmbProducto.getPreferredSize().width, height));
        txtNuevoProducto.setPreferredSize(new Dimension(txtNuevoProducto.getPreferredSize().width, height));
        txtCantidad.setPreferredSize(new Dimension(txtCantidad.getPreferredSize().width, height));
        txtTipoMedida.setPreferredSize(new Dimension(txtTipoMedida.getPreferredSize().width, height));
        txtPrecioMedida.setPreferredSize(new Dimension(txtPrecioMedida.getPreferredSize().width, height));

        // Estilo del botón primario
        btnProcesar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnProcesar.setBackground(new Color(46, 125, 50)); // Verde Granja
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setPreferredSize(new Dimension(btnProcesar.getPreferredSize().width, 42));
        btnProcesar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Estilo del botón secundario
        btnRegistrarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 11));
        btnRegistrarProveedor.setForeground(new Color(46, 125, 50));
        btnRegistrarProveedor.setContentAreaFilled(false);
        btnRegistrarProveedor.setBorder(BorderFactory.createEmptyBorder());
        btnRegistrarProveedor.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // --- ACCIONES DE LOS BOTONES ---
    private void btnRegistrarProveedorActionPerformed() {
        System.out.println("Redirigiendo o abriendo diálogo para nuevo proveedor...");
        // Aquí puedes ejecutar lógica o llamadas a tu base de datos / controladores
    }

    private void btnProcesarActionPerformed() {
        String operacion = (String) cmbTipoOperacion.getSelectedItem();
        System.out.println("Procesando operación: " + operacion);
        // Validaciones e inserciones del negocio van aquí
    }
}