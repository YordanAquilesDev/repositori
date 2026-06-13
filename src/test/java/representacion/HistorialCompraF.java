package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HistorialCompraF extends JPanel {

    private JTable tblCompras;
    private DefaultTableModel tableModel;

    // Tarjetas de analítica (KPIs)
    private JPanel cardGastoFecha;
    private JPanel cardProductoEstrella;
    private JPanel cardProveedores;

    public HistorialCompraF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Fondo gris claro de la aplicación
        setLayout(new BorderLayout(0, 20));       // Separación vertical de 20px entre KPIs y Tabla
        setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    private void initComponents() {
        // 1. PANEL SUPERIOR: FILA DE TARJETAS ANALÍTICAS (KPIs)
        JPanel panelResumen = new JPanel(new GridLayout(1, 3, 20, 0)); // 1 fila, 3 columnas, 20px de separación
        panelResumen.setOpaque(false);

        // Tarjeta 1: Consumo/Gasto por Fecha
        cardGastoFecha = crearTarjetaKpi("Gasto del Mes (Fecha Actual)", "S/. 14,350.00", "Basado en compras recientes");
        // Tarjeta 2: Producto más comprado
        cardProductoEstrella = crearTarjetaKpi("Producto Más Comprado", "Maíz Premium", "65 Sacos importados");
        // Tarjeta 3: Proveedor top
        cardProveedores = crearTarjetaKpi("Proveedor Principal", "Alimentos Norte S.A.", "Mayor volumen de órdenes");

        panelResumen.add(cardGastoFecha);
        panelResumen.add(cardProductoEstrella);
        panelResumen.add(cardProveedores);

        // 2. PANEL CENTRAL: TABLA DE HISTORIAL DE COMPRAS
        JPanel panelTablaContenedor = new JPanel(new BorderLayout(0, 10));
        panelTablaContenedor.setOpaque(false);

        JLabel lblTablaTitulo = new JLabel("Historial Cronológico de Compras");
        lblTablaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTablaTitulo.setForeground(new Color(50, 50, 50));
        panelTablaContenedor.add(lblTablaTitulo, BorderLayout.NORTH);

        // Configuración de columnas
        String[] columnas = {"ID Compra", "Proveedor", "Producto", "Total Gastado", "Fecha Registro"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Bloquear edición directa
            }
        };

        tblCompras = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblCompras);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblCompras.getParent().setBackground(Color.WHITE);

        panelTablaContenedor.add(scrollPane, BorderLayout.CENTER);

        // 3. ENSAMBLAR AL PANEL PRINCIPAL
        add(panelResumen, BorderLayout.NORTH);
        add(panelTablaContenedor, BorderLayout.CENTER);
    }

    // Método helper/factoría para construir las tarjetas de métricas interactivas de forma limpia
    private JPanel crearTarjetaKpi(String titulo, String valor, String subtitulo) {
        JPanel card = new JPanel(new GridBagLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(new EmptyBorder(18, 20, 18, 20));
        card.putClientProperty(FlatClientProperties.STYLE, "arc: 12;"); // Bordes suavizados

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Etiqueta del título (Pequeña y grisácea)
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitulo.setForeground(new Color(120, 120, 120));
        gbc.gridy = 0;
        card.add(lblTitulo, gbc);

        // Etiqueta del valor principal (Grande y destacada en verde granja)
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblValor.setForeground(new Color(46, 125, 50));
        gbc.gridy = 1;
        gbc.insets = new Insets(6, 0, 4, 0);
        card.add(lblValor, gbc);

        // Subtítulo descriptivo (Muy ligero de leer)
        JLabel lblSub = new JLabel(subtitulo);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(new Color(160, 160, 160));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        card.add(lblSub, gbc);

        return card;
    }

    private void aplicarEstilosModernos() {
        // Estilización de la JTable
        tblCompras.setRowHeight(32); // Filas amplias para lectura ejecutiva
        tblCompras.setShowVerticalLines(false);
        tblCompras.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Cabecera estilizada
        tblCompras.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblCompras.getTableHeader().setBackground(new Color(230, 235, 230));
        tblCompras.getTableHeader().setForeground(new Color(46, 125, 50));

        // Datos de ejemplo para que la interfaz cobre vida inmediatamente al ejecutar
        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        tableModel.addRow(new Object[]{1001, "Distribuidora Alimentos S.A.", "Maíz Premium (Sacos)", "S/. 4,500.00", "2026-05-28"});
        tableModel.addRow(new Object[]{1002, "Fármacos Veterinarios Perú", "Vitaminas Crecimiento", "S/. 1,250.00", "2026-05-25"});
        tableModel.addRow(new Object[]{1003, "Inversiones Agropecuarias", "Concentrado Proteico", "S/. 8,600.00", "2026-05-20"});
    }
}