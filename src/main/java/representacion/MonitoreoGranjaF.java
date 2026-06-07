package representacion;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class MonitoreoGranjaF extends JPanel {

    // Tablas e indicadores
    private JTable tblLotes;
    private DefaultTableModel modelLotes;
    private JPanel panelAlertasContenedor;

    public MonitoreoGranjaF() {
        configurarPanel();
        initComponents();
        aplicarEstilosModernos();
    }

    private void configurarPanel() {
        setBackground(new Color(245, 246, 250)); // Fondo gris claro consistente
        setLayout(new BorderLayout(0, 20));       // Espaciado vertical de 20px
        setBorder(new EmptyBorder(25, 25, 25, 25));
    }

    private void initComponents() {
        // ========================================================
        // 1. PANEL SUPERIOR: SECCIÓN DE MÉTRICAS OPERATIVAS (KPIs)
        // ========================================================
        JPanel panelKPIs = new JPanel(new GridLayout(1, 3, 20, 0));
        panelKPIs.setOpaque(false);

        JPanel cardPoblacion = crearTarjetaKpi("Población Total", "420 Cabezas", "Animales activos en producción", new Color(46, 125, 50));
        JPanel cardAlimento = crearTarjetaKpi("Stock de Alimento", "1,240 Kg", "Suficiente para ~12 días", new Color(230, 126, 34));
        JPanel cardEficiencia = crearTarjetaKpi("Consumo Promedio Diario", "98.5 Kg / día", "Calculado esta semana", new Color(21, 101, 192));

        panelKPIs.add(cardPoblacion);
        panelKPIs.add(cardAlimento);
        panelKPIs.add(cardEficiencia);

        // ========================================================
        // 2. PANEL CENTRAL: DISTRIBUCIÓN EN DOS COLUMNAS (GRID)
        // ========================================================
        JPanel panelContenidoEstructural = new JPanel(new GridBagLayout());
        panelContenidoEstructural.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // --- COLUMNA IZQUIERDA: MONITOREO DE LOTES / GALPONES (65% del ancho) ---
        JPanel panelLotesContenedor = new JPanel(new BorderLayout(0, 10));
        panelLotesContenedor.setOpaque(false);

        JLabel lblTblTitulo = new JLabel("Estado Actual de Lotes y Galpones");
        lblTblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTblTitulo.setForeground(new Color(51, 51, 51));
        panelLotesContenedor.add(lblTblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Código", "Área / Galpón", "Etapa / Fase", "Capacidad", "Estado"};
        modelLotes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblLotes = new JTable(modelLotes);
        JScrollPane scrollLotes = new JScrollPane(tblLotes);
        scrollLotes.setBorder(BorderFactory.createEmptyBorder());
        scrollLotes.putClientProperty(FlatClientProperties.STYLE, "arc: 12;");
        tblLotes.getParent().setBackground(Color.WHITE);
        panelLotesContenedor.add(scrollLotes, BorderLayout.CENTER);

        gbc.gridx = 0;
        gbc.weightx = 0.65;
        gbc.insets = new Insets(0, 0, 0, 10); // Margen derecho antes de las alertas
        panelContenidoEstructural.add(panelLotesContenedor, gbc);

        // --- COLUMNA DERECHA: PANEL DE ALERTAS Y NOTIFICACIONES (35% del ancho) ---
        JPanel panelDerecho = new JPanel(new BorderLayout(0, 10));
        panelDerecho.setOpaque(false);

        JLabel lblAlertasTitulo = new JLabel("Centro de Alertas del Sistema");
        lblAlertasTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblAlertasTitulo.setForeground(new Color(51, 51, 51));
        panelDerecho.add(lblAlertasTitulo, BorderLayout.NORTH);

        panelAlertasContenedor = new JPanel();
        panelAlertasContenedor.setLayout(new BoxLayout(panelAlertasContenedor, BoxLayout.Y_AXIS));
        panelAlertasContenedor.setOpaque(false);

        // Agregar componentes de alerta de prueba
        panelAlertasContenedor.add(crearFilaAlerta("CRÍTICO", "Stock de Alimento balanceado inicial inferior al 15%.", new Color(231, 76, 60)));
        panelAlertasContenedor.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAlertasContenedor.add(crearFilaAlerta("RECORDATORIO", "Vacunación programada para el Lote B (Maternidad) mañana.", new Color(241, 196, 15)));
        panelAlertasContenedor.add(Box.createRigidArea(new Dimension(0, 10)));
        panelAlertasContenedor.add(crearFilaAlerta("NOTIFICACIÓN", "Registro de peso semanal de porcinos completado por el sistema.", new Color(52, 152, 219)));

        JScrollPane scrollAlertas = new JScrollPane(panelAlertasContenedor);
        scrollAlertas.setBorder(BorderFactory.createEmptyBorder());
        scrollAlertas.setOpaque(false);
        scrollAlertas.getViewport().setOpaque(false);
        panelDerecho.add(scrollAlertas, BorderLayout.CENTER);

        gbc.gridx = 1;
        gbc.weightx = 0.35;
        gbc.insets = new Insets(0, 10, 0, 0); // Margen izquierdo
        panelContenidoEstructural.add(panelDerecho, gbc);

        // ========================================================
        // 3. ENSAMBLAR TODO EN EL PANEL PRINCIPAL
        // ========================================================
        add(panelKPIs, BorderLayout.NORTH);
        add(panelContenidoEstructural, BorderLayout.CENTER);
    }

    // Método factoría para la creación de las tarjetas KPI de Monitoreo
    private JPanel crearTarjetaKpi(String titulo, String valor, String subtitulo, Color colorDestaque) {
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
        lblVal.setForeground(colorDestaque);
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

    // Componente personalizado para renderizar notificaciones/alertas dinámicas tipo Toast
    private JPanel crearFilaAlerta(String tag, String mensaje, Color colorTag) {
        JPanel alerta = new JPanel(new BorderLayout(15, 0));
        alerta.setBackground(Color.WHITE);
        alerta.setBorder(new EmptyBorder(12, 15, 12, 15));
        alerta.putClientProperty(FlatClientProperties.STYLE, "arc: 10;");
        alerta.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75)); // Evita estiramiento excesivo en BoxLayout

        // Tag de importancia destacado en color
        JLabel lblTag = new JLabel(tag);
        lblTag.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblTag.setForeground(colorTag);
        alerta.add(lblTag, BorderLayout.WEST);

        // Mensaje de la alerta en formato HTML para permitir saltos de línea automáticos
        JLabel lblMsg = new JLabel("<html><body>" + mensaje + "</body></html>");
        lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMsg.setForeground(new Color(70, 70, 70));
        alerta.add(lblMsg, BorderLayout.CENTER);

        return alerta;
    }

    private void aplicarEstilosModernos() {
        // Formato para la JTable de Lotes
        tblLotes.setRowHeight(32);
        tblLotes.setShowVerticalLines(false);
        tblLotes.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // Cabecera estilizada consistente con el look de producción (Verde Granja)
        tblLotes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tblLotes.getTableHeader().setBackground(new Color(230, 235, 230));
        tblLotes.getTableHeader().setForeground(new Color(46, 125, 50));

        cargarDatosDePrueba();
    }

    private void cargarDatosDePrueba() {
        modelLotes.addRow(new Object[]{"#LOTE-A", "Galpón Norte 01", "Engorde Inicial", "150 / 200 Cabezas", "Estable"});
        modelLotes.addRow(new Object[]{"#LOTE-B", "Área de Maternidad", "Gestación / Partos", "45 / 50 Cabezas", "Atención Requerida"});
        modelLotes.addRow(new Object[]{"#LOTE-C", "Galpón Sur 02", "Desarrollo Recién Nacidos", "225 / 300 Cabezas", "Estable"});
    }
}