package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HistorialVentasF extends JPanel {

    private JTable tblVentas;
    private DefaultTableModel tableModel;

    // Componentes de control y analítica
    private JTextField txtBuscarVenta;
    private JComboBox<String> cmbEstadoPago;
    private JPanel cardIngresosTotales;
    private JPanel cardClienteTop;

    public HistorialVentasF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Fondo gris claro consistente
        setLayout(new BorderLayout(0, 20));       // 20px de separación vertical entre bloques
        setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    private void initComponents() {
        // ==========================================
        // 1. ESTRUCTURA DEL NORTE: HEADER + METRICAS
        // ==========================================
        JPanel panelNorte = new JPanel(new BorderLayout(0, 15));
        panelNorte.setOpaque(false);

        // Fila de KPIs (2 Columnas de analítica rápida)
        JPanel panelKpis = new JPanel(new GridLayout(1, 2, 20, 0));
        panelKpis.setOpaque(false);

        cardIngresosTotales = crearTarjetaKpi("Ingresos por Ventas (Mes Actual)", "S/. 28,450.00", "Total recaudado en caja de movimientos");
        cardClienteTop = crearTarjetaKpi("Cliente Mayor Consumo", "Asociación Ganadera Lima", "Frecuencia: Semanal");

        panelKpis.add(cardIngresosTotales);
        panelKpis.add(cardClienteTop);
        panelNorte.add(panelKpis, BorderLayout.CENTER);

        // ==========================================
        // 2. BARRA DE HERRAMIENTAS (FILTROS)
        // ==========================================
        JPanel panelAcciones = new JPanel(new GridBagLayout());
        panelAcciones.setBackground(Color.WHITE);
        panelAcciones.setBorder(new EmptyBorder(12, 20, 12, 20));
        panelAcciones.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(0, 0, 0, 15);
        gbc.gridy = 0;

        // Título del buscador
        JLabel lblFiltrar = new JLabel("Monitoreo de Ventas");
        lblFiltrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblFiltrar.setForeground(new Color(33, 33, 33));
        gbc.gridx = 0;
        panelAcciones.add(lblFiltrar, gbc);

        // Buscador predictivo por texto
        txtBuscarVenta = new JTextField();
        txtBuscarVenta.setPreferredSize(new Dimension(300, 35));
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelAcciones.add(txtBuscarVenta, gbc);

        // Filtro por Estado de la Venta
        cmbEstadoPago = new JComboBox<>(new String[]{"Todos los Estados", "Completado", "Pendiente de Pago", "Anulado"});
        cmbEstadoPago.setPreferredSize(new Dimension(160, 35));
        gbc.gridx = 2;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        panelAcciones.add(cmbEstadoPago, gbc);

        panelNorte.add(panelAcciones, BorderLayout.SOUTH);

        // ==========================================
        // 3. ESTRUCTURA CENTRAL: TABLA DE VENTAS
        // ==========================================
        String[] columnas = {"N° Recibo", "Fecha Despacho", "Cliente", "Producto Vendido", "Cantidad", "Total Facturado", "Estado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Evita modificaciones accidentales de dinero facturado
            }
        };

        tblVentas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblVentas);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblVentas.getParent().setBackground(Color.WHITE);

        // Ensamblado final al contenedor raíz
        add(panelNorte, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Listeners de acción rápidos
        cmbEstadoPago.addActionListener(e -> filtrarVentas());
    }

    // Factoría modular para generar las tarjetas de analítica
    private JPanel crearTarjetaKpi(String titulo, String valor, String subtitulo) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(16, 20, 16, 20));
        card.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTit.setForeground(new Color(110, 110, 110));
        gbc.gridy = 0;
        card.add(lblTit, gbc);

        JLabel lblVal = new JLabel(valor);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblVal.setForeground(new Color(21, 101, 192)); // Azul Financiero para diferenciarlo de las compras
        gbc.gridy = 1;
        gbc.insets = new Insets(4, 0, 4, 0);
        card.add(lblVal, gbc);

        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(new Color(150, 150, 150));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        card.add(lblSub, gbc);

        return card;
    }

    private void aplicarEstilosModernos() {
        // Placeholders nativos de FlatLaf
        txtBuscarVenta.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "🔍 Buscar por cliente, N° recibo o producto...");
        txtBuscarVenta.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        // Personalización ejecutiva de la tabla
        tblVentas.setRowHeight(32);
        tblVentas.setShowVerticalLines(false);
        tblVentas.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Cabecera con identidad corporativa (Estilo Azul/Gris para Ventas)
        tblVentas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblVentas.getTableHeader().setBackground(new Color(225, 235, 245));
        tblVentas.getTableHeader().setForeground(new Color(21, 101, 192));

        cargarVentasDePrueba();
    }

    private void cargarVentasDePrueba() {
        tableModel.addRow(new Object[]{"#VTA-9954", "2026-05-30 14:20", "Carlos Mendoza", "Lechones Comerciales", "10 Cabezas", "S/. 4,800.00", "Completado"});
        tableModel.addRow(new Object[]{"#VTA-9955", "2026-05-29 10:10", "Asociación Ganadera Lima", "Sacos de Maíz Molido", "40 Unid", "S/. 3,200.00", "Completado"});
        tableModel.addRow(new Object[]{"#VTA-9956", "2026-05-28 16:45", "Distribuidora San Juan", "Cerdos en Pie (Engorde)", "5 Cabezas", "S/. 3,500.00", "Pendiente de Pago"});
    }

    private void filtrarVentas() {
        String estado = (String) cmbEstadoPago.getSelectedItem();
        System.out.println("Filtrando el registro de ventas por estado: " + estado);
    }
}