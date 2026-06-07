package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HistorialMovimientosF extends JPanel {

    private JTable tblMovimientos;
    private DefaultTableModel tableModel;

    // Componentes de la barra de filtros
    private JTextField txtBuscar;
    private JComboBox<String> cmbFiltroTipo;
    private JButton btnExportar;

    public HistorialMovimientosF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Fondo gris claro consistente
        setLayout(new BorderLayout(0, 15));       // Separación de 15px entre filtros y tabla
        setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    private void initComponents() {
        // 1. BARRA DE HERRAMIENTAS SUPERIOR (FILTROS DE BÚSQUEDA)
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBackground(Color.WHITE);
        panelFiltros.setBorder(new EmptyBorder(15, 20, 15, 20));
        panelFiltros.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 0, 15); // Separación horizontal entre filtros
        gbc.gridy = 0;

        // Título del Módulo
        JLabel lblTitulo = new JLabel("Historial de Movimientos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(33, 33, 33));
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        panelFiltros.add(lblTitulo, gbc);

        // Input de búsqueda por texto (Buscador general)
        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(280, 35));
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Este componente se expande para empujar los demás a la derecha
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelFiltros.add(txtBuscar, gbc);

        // Selector de tipo de movimiento (Filtro)
        cmbFiltroTipo = new JComboBox<>(new String[]{"Todos los Movimientos", "Entrada (Compra)", "Salida (Venta)", "Consumo Interno", "Traslado de Lote"});
        cmbFiltroTipo.setPreferredSize(new Dimension(180, 35));
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panelFiltros.add(cmbFiltroTipo, gbc);

        // Botón de acción secundaria (Exportar reporte / Reportes)
        btnExportar = new JButton("Exportar Excel");
        btnExportar.setPreferredSize(new Dimension(120, 35));
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 0, 0, 0); // Último elemento sin margen derecho
        panelFiltros.add(btnExportar, gbc);


        // 2. CONTENEDOR CENTRAL: TABLA DE AUDITORÍA
        String[] columnas = {"ID Registro", "Fecha / Hora", "Tipo Movimiento", "Detalle / Concepto", "Cantidad", "Encargado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Asegura la integridad del historial bloqueando la edición
            }
        };

        tblMovimientos = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblMovimientos);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblMovimientos.getParent().setBackground(Color.WHITE);


        // 3. ENSAMBLAR TODO EN EL PANEL
        add(panelFiltros, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Eventos básicos
        cmbFiltroTipo.addActionListener(e -> filtrarDatos());
        btnExportar.addActionListener(e -> exportarReporte());
    }

    private void aplicarEstilosModernos() {
        // Propiedades de diseño avanzado de FlatLaf para el buscador
        txtBuscar.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "🔍 Buscar por palabra clave, código o encargado...");
        txtBuscar.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true); // Botón "X" para borrar texto rápidamente

        // Botón Exportar con estilo FlatLaf sutil (Outline)
        btnExportar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnExportar.putClientProperty(FlatClientProperties.STYLE, "borderWidth: 1; outlineMargins: 0;");
        btnExportar.setForeground(new Color(46, 125, 50));
        btnExportar.setBackground(Color.WHITE);
        btnExportar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Estilo ejecutivo para la JTable
        tblMovimientos.setRowHeight(32);
        tblMovimientos.setShowVerticalLines(false);
        tblMovimientos.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Diseño específico de la cabecera
        tblMovimientos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblMovimientos.getTableHeader().setBackground(new Color(230, 235, 230));
        tblMovimientos.getTableHeader().setForeground(new Color(46, 125, 50));

        // Cargar datos ficticios para comprobar el comportamiento visual
        cargarHistorialDePrueba();
    }

    private void cargarHistorialDePrueba() {
        tableModel.addRow(new Object[]{"#MOV-0089", "2026-05-30 09:15", "Entrada (Compra)", "Ingreso de 50 sacos de alimento balanceado", "50 Unid", "Yordan"});
        tableModel.addRow(new Object[]{"#MOV-0090", "2026-05-30 11:30", "Traslado de Lote", "Movimiento de porcinos del Lote A al Lote C", "12 Cabezas", "Juan M."});
        tableModel.addRow(new Object[]{"#MOV-0091", "2026-05-29 16:00", "Consumo Interno", "Suministro de Maíz Premium a galpones de aves", "5 Sacos", "Sistema"});
        tableModel.addRow(new Object[]{"#MOV-0092", "2026-05-29 17:45", "Salida (Venta)", "Despacho de lote de ganado comercial", "4 Cabezas", "Yordan"});
    }

    // --- MÉTODOS DE CONTROLADOR ---
    private void filtrarDatos() {
        String seleccion = (String) cmbFiltroTipo.getSelectedItem();
        System.out.println("Filtrando el historial por: " + seleccion);
        // Aquí conectarías con tu query SQL filtrado o filter de streams en Java
    }

    private void exportarReporte() {
        System.out.println("Generando y descargando documento Excel (.xlsx) del historial...");
    }
}