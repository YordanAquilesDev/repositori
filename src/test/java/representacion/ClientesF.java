package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ClientesF extends JPanel {

    // Variables renombradas con buenas prácticas
    private JTable tblClientes;
    private DefaultTableModel tableModel;

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDni;
    private JTextField txtDireccion;
    private JTextField txtCelular;
    private JButton btnProcesar;

    public ClientesF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Fondo gris tecnológico sutil
        setLayout(new BorderLayout(20, 0));       // Separación de 20px entre formulario y tabla
        setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    private void initComponents() {
        // 1. CONSTRUCCIÓN DEL FORMULARIO (PANEL IZQUIERDO)
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setPreferredSize(new Dimension(320, 0)); // Ancho fijo ideal para inputs
        panelFormulario.setBorder(new EmptyBorder(25, 20, 25, 20));
        panelFormulario.putClientProperty(FlatClientProperties.STYLE, "arc: 12;"); // Curvatura moderna

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        // Encabezado del Formulario
        JLabel lblRegTitulo = new JLabel("Registrar Cliente");
        lblRegTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblRegTitulo.setForeground(new Color(33, 33, 33));
        panelFormulario.add(lblRegTitulo, gbc);

        gbc.gridy++;
        panelFormulario.add(Box.createRigidArea(new Dimension(0, 10)), gbc); // Separador

        // Inputs con sus respectivas etiquetas de referencia arriba
        txtNombre = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridy++; panelFormulario.add(txtNombre, gbc);

        txtApellido = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Apellido:"), gbc);
        gbc.gridy++; panelFormulario.add(txtApellido, gbc);

        txtDni = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("DNI / Documento:"), gbc);
        gbc.gridy++; panelFormulario.add(txtDni, gbc);

        txtCelular = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Celular:"), gbc);
        gbc.gridy++; panelFormulario.add(txtCelular, gbc);

        txtDireccion = new JTextField();
        gbc.gridy++; panelFormulario.add(new JLabel("Dirección:"), gbc);
        gbc.gridy++; panelFormulario.add(txtDireccion, gbc);

        // Botón de acción al fondo del formulario
        btnProcesar = new JButton("Procesar Registro");
        gbc.gridy++;
        gbc.weighty = 1.0; // Empuja el botón hacia abajo si el panel se estira
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(20, 0, 0, 0);
        panelFormulario.add(btnProcesar, gbc);

        // 2. CONSTRUCCIÓN DE LA TABLA (PANEL DERECHO / CENTRAL)
        JPanel panelTablaContenedor = new JPanel(new BorderLayout(0, 10));
        panelTablaContenedor.setOpaque(false);

        JLabel lblTablaTitulo = new JLabel("Lista General de Clientes");
        lblTablaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTablaTitulo.setForeground(new Color(33, 33, 33));
        panelTablaContenedor.add(lblTablaTitulo, BorderLayout.NORTH);

        // Modelo de la tabla (no editable por defecto para evitar errores del usuario)
        String[] columnas = {"ID", "Nombre", "Apellido", "DNI", "Celular", "Dirección", "Consumo Total", "Estado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblClientes = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblClientes);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Quita el borde tosco del scroll

        // Hacer que el scroll table tenga esquinas redondeadas en FlatLaf
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblClientes.getParent().setBackground(Color.WHITE); // Fondo de la tabla detrás de las filas

        panelTablaContenedor.add(scrollPane, BorderLayout.CENTER);

        // 3. ENSAMBLADO FINAL EN EL PANEL
        add(panelFormulario, BorderLayout.WEST);
        add(panelTablaContenedor, BorderLayout.CENTER);

        // Eventos
        btnProcesar.addActionListener(e -> btnProcesarActionPerformed());
    }

    private void aplicarEstilosModernos() {
        // Placeholders nativos con FlatLaf (No requieren borrar texto guía)
        txtNombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej. Juan");
        txtApellido.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ej. Pérez");
        txtDni.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "8 dígitos");
        txtCelular.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "9XXXXXXXX");
        txtDireccion.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Dirección de entrega...");

        // Altura y look del botón principal (Estilo Verde BioGranja)
        btnProcesar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnProcesar.setBackground(new Color(46, 125, 50));
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.setPreferredSize(new Dimension(0, 40));
        btnProcesar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Estilización avanzada para la JTable con FlatLaf
        tblClientes.setRowHeight(30); // Filas más altas para que respiren los datos
        tblClientes.setShowVerticalLines(false); // Diseño limpio sin líneas verticales divisorias
        tblClientes.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Estilo específico de la cabecera
        tblClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblClientes.getTableHeader().setBackground(new Color(230, 235, 230));
        tblClientes.getTableHeader().setForeground(new Color(46, 125, 50));

        // Datos de prueba iniciales para validar el Look & Feel de la tabla
        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        tableModel.addRow(new Object[]{1, "Carlos", "Mendoza", "45781239", "987654321", "Av. Las Flores 123", 1540.50, "Activo"});
        tableModel.addRow(new Object[]{2, "Ana", "Gomez", "70123456", "912345678", "Jr. Trujillo 455", 890.00, "Activo"});
    }

    // Acción del botón registrar
    private void btnProcesarActionPerformed() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if(nombre.isEmpty() || apellido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa los campos mínimos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lógica de inserción (Ejemplo visual)
        int nuevoId = tableModel.getRowCount() + 1;
        tableModel.addRow(new Object[]{nuevoId, nombre, apellido, txtDni.getText(), txtCelular.getText(), txtDireccion.getText(), 0.0, "Activo"});

        // Limpiar campos
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtCelular.setText("");
        txtDireccion.setText("");
    }
}