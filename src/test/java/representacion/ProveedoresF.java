package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ProveedoresF extends JPanel {

    // Componentes principales renombrados bajo buenas prácticas
    private JTable tblProveedores;
    private DefaultTableModel tableModel;

    // Campos del formulario (Panel Izquierdo)
    private JTextField txtNombre;
    private JTextField txtContacto; // Combina Nombre/Apellido o Razón Social
    private JTextField txtDniRuc;
    private JTextField txtProductos;
    private JButton btnProcesar;

    // Buscador (Panel Derecho)
    private JTextField txtBuscarProveedor;

    public ProveedoresF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Gris tecnológico sutil
        setLayout(new BorderLayout(20, 0));       // 20px de espacio entre el formulario y la tabla
        setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    private void initComponents() {
        // ========================================================
        // 1. PANEL IZQUIERDO: FORMULARIO DE REGISTRO
        // ========================================================
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setPreferredSize(new Dimension(320, 0)); // Ancho ideal para inputs
        panelFormulario.setBorder(new EmptyBorder(25, 20, 25, 20));
        panelFormulario.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        // Título del Formulario
        JLabel lblFormTitulo = new JLabel("Registrar Proveedor");
        lblFormTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblFormTitulo.setForeground(new Color(33, 33, 33));
        panelFormulario.add(lblFormTitulo, gbc);

        gbc.gridy++;
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 8)), gbc); // Separador

        // Inputs con etiquetas descriptivas arriba
        txtNombre = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Razón Social / Empresa:"), gbc);
        gbc.gridy++; panelFormulario.add(txtNombre, gbc);

        txtContacto = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Nombre del Contacto:"), gbc);
        gbc.gridy++; panelFormulario.add(txtContacto, gbc);

        txtDniRuc = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("RUC / DNI:"), gbc);
        gbc.gridy++; panelFormulario.add(txtDniRuc, gbc);

        txtProductos = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Categoría de Productos:"), gbc);
        gbc.gridy++; panelFormulario.add(txtProductos, gbc);

        // Botón de acción al fondo del formulario
        btnProcesar = new JButton("Guardar Proveedor");
        gbc.gridy++;
        gbc.weighty = 1.0; // Empuja el botón hacia el extremo inferior
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(20, 0, 0, 0);
        panelFormulario.add(btnProcesar, gbc);


        // ========================================================
        // 2. PANEL DERECHO: BUSCADOR + TABLA DE PROVEEDORES
        // ========================================================
        JPanel panelTablaContenedor = new JPanel(new BorderLayout(0, 15));
        panelTablaContenedor.setOpaque(false);

        // Barra superior de la tabla (Título + Buscador rápido)
        JPanel panelTablaHeader = new JPanel(new BorderLayout(15, 0));
        panelTablaHeader.setOpaque(false);

        JLabel lblTablaTitulo = new JLabel("Directorio de Proveedores");
        lblTablaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTablaTitulo.setForeground(new Color(33, 33, 33));
        panelTablaHeader.add(lblTablaTitulo, BorderLayout.WEST);

        txtBuscarProveedor = new JTextField();
        txtBuscarProveedor.setPreferredSize(new Dimension(280, 35));
        panelTablaHeader.add(txtBuscarProveedor, BorderLayout.EAST);

        panelTablaContenedor.add(panelTablaHeader, BorderLayout.NORTH);

        // Configuración y construcción de la JTable
        String[] columnas = {"ID", "Empresa / Razón Social", "Contacto", "RUC / DNI", "Productos Suministrados", "Total Comprado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloquear edición directa desde las celdas
            }
        };

        tblProveedores = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblProveedores);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblProveedores.getParent().setBackground(Color.WHITE);

        panelTablaContenedor.add(scrollPane, BorderLayout.CENTER);


        // ========================================================
        // 3. ENSAMBLADO EN EL CONTENEDOR PRINCIPAL
        // ========================================================
        add(panelFormulario, BorderLayout.WEST);
        add(panelTablaContenedor, BorderLayout.CENTER);

        // Eventos
        btnProcesar.addActionListener(e -> btnProcesarActionPerformed());
    }

    private void aplicarEstilosModernos() {
        // Placeholders dinámicos de FlatLaf
        txtNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej. Corporación Agropecuaria S.A.C.");
        txtContacto.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej. Ing. Carlos Mendoza");
        txtDniRuc.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "11 dígitos (RUC)");
        txtProductos.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej. Alimentos balanceados, Vacunas");
        txtBuscarProveedor.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "🔍 Buscar proveedor o insumo...");
        txtBuscarProveedor.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        // Estilización del botón principal (Mantenemos la paleta verde de insumos/producción)
        btnProcesar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnProcesar.setBackground(new Color(46, 125, 50));
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setPreferredSize(new Dimension(0, 40));
        btnProcesar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configuración de la JTable ejecutiva
        tblProveedores.setRowHeight(32);
        tblProveedores.setShowVerticalLines(false);
        tblProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Estilo de la cabecera
        tblProveedores.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblProveedores.getTableHeader().setBackground(new Color(230, 235, 230));
        tblProveedores.getTableHeader().setForeground(new Color(46, 125, 50));

        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        tableModel.addRow(new Object[]{1, "Molinos del Norte S.A.", "Juan Pérez", "20457812391", "Maíz Molido y Concentrados", "S/. 24,500.00"});
        tableModel.addRow(new Object[]{2, "Biovet Perú Laboratorios", "Dra. Ana Gómez", "20701234562", "Medicinas y Vacunas Aves", "S/. 8,900.00"});
    }

    private void btnProcesarActionPerformed() {
        String empresa = txtNombre.getText().trim();
        String contacto = txtContacto.getText().trim();

        if (empresa.isEmpty() || contacto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa los datos esenciales del proveedor.", "Campos Requeridos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int nuevoId = tableModel.getRowCount() + 1;
        tableModel.addRow(new Object[]{nuevoId, empresa, contacto, txtDniRuc.getText(), txtProductos.getText(), "S/. 0.00"});

        // Limpiar formulario de forma limpia
        txtNombre.setText("");
        txtContacto.setText("");
        txtDniRuc.setText("");
        txtProductos.setText("");
    }
}