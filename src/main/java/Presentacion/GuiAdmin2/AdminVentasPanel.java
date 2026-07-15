package Presentacion.GuiAdmin2;

import Aplicacion.Service.ClienteService;
import Aplicacion.Service.UsuarioService;
import Aplicacion.Service.VentaService;

import Dominio.Modelo.Cliente;
import Dominio.Modelo.Usuario;
import Dominio.Modelo.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;

import java.io.File;
import java.io.FileOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class AdminVentasPanel extends JPanel {

    private final ClienteService clienteService = new ClienteService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final VentaService ventaService = new VentaService();
    private final DefaultTableModel modelo = new DefaultTableModel(
            new Object[]{"ID Venta", "Cliente", "Fecha", "Total"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modelo);
    private final JButton btnExportarPDF = new JButton("Exportar PDF");

    public AdminVentasPanel() {
        initComponents();
        cargarVentas();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 16));
        setBackground(new Color(244, 247, 246));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);

        JLabel titulo = new JLabel("Ventas registradas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setForeground(new Color(25, 40, 37));
        top.add(titulo, BorderLayout.WEST);
        
        
        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(evt -> cargarVentas());

        btnExportarPDF.addActionListener(evt -> exportarPDF());

        panelBotones.add(btnActualizar);
        panelBotones.add(btnExportarPDF);

        top.add(panelBotones, BorderLayout.EAST);


        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(225, 231, 229)));

        add(top, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarVentas() {
        modelo.setRowCount(0);
        try {
            for (Venta venta : ventaService.findAll()) {
                String nombreCliente = "Desconocido";
                Cliente cliente = clienteService.findById(venta.getIdCliente()).orElse(null);
                if (cliente != null) {
                    Usuario usuario = usuarioService.findById(cliente.getIdUsuario()).orElse(null);
                    if (usuario != null) {
                        nombreCliente = usuario.getNombre();
                    }
                }
                modelo.addRow(new Object[]{
                    venta.getIdVenta(),
                    nombreCliente,
                    venta.getFecha(),
                    venta.getTotal()
                });
            }
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(this, "No se pudieron cargar las ventas.");
        }
    }
    
    private void exportarPDF() {

    try {

        Document documento = new Document();

        String nombreArchivo = "Reporte_Ventas.pdf";

        PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));

        documento.open();

        com.itextpdf.text.Font titulo =
        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

        com.itextpdf.text.Font subtitulo =
        FontFactory.getFont(FontFactory.HELVETICA, 12);

        Paragraph p1 = new Paragraph("REPORTE DE VENTAS", titulo);
        p1.setAlignment(Paragraph.ALIGN_CENTER);

        documento.add(p1);
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Fecha de generación: "
                + java.time.LocalDateTime.now(), subtitulo));
        documento.add(new Paragraph(" "));

        PdfPTable pdfTable = new PdfPTable(4);
        pdfTable.setWidthPercentage(100);
        
        com.itextpdf.text.Font fuenteCabecera =
        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

       PdfPCell c1 = new PdfPCell(new Phrase("ID Venta", fuenteCabecera));
       PdfPCell c2 = new PdfPCell(new Phrase("Cliente", fuenteCabecera));
       PdfPCell c3 = new PdfPCell(new Phrase("Fecha", fuenteCabecera));
       PdfPCell c4 = new PdfPCell(new Phrase("Total", fuenteCabecera));

       c1.setBackgroundColor(new com.itextpdf.text.BaseColor(34, 139, 34));
       c2.setBackgroundColor(new com.itextpdf.text.BaseColor(34, 139, 34));
       c3.setBackgroundColor(new com.itextpdf.text.BaseColor(34, 139, 34));
       c4.setBackgroundColor(new com.itextpdf.text.BaseColor(34, 139, 34));

c1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
c2.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
c3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
c4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);


        pdfTable.addCell(c1);
        pdfTable.addCell(c2);
        pdfTable.addCell(c3);
        pdfTable.addCell(c4);
        
        double totalVentas = 0;

        for (int fila = 0; fila < tabla.getRowCount(); fila++) {

    pdfTable.addCell(tabla.getValueAt(fila, 0).toString());
    pdfTable.addCell(tabla.getValueAt(fila, 1).toString());
    pdfTable.addCell(tabla.getValueAt(fila, 2).toString());
    pdfTable.addCell(tabla.getValueAt(fila, 3).toString());

    totalVentas += Double.parseDouble(
            tabla.getValueAt(fila, 3).toString());

}

        documento.add(pdfTable);
        
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph(
        "TOTAL VENDIDO : S/ "
                + String.format("%.2f", totalVentas),
        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 13)));

        documento.close();

        JOptionPane.showMessageDialog(this,
                "PDF generado correctamente.");

        Desktop.getDesktop().open(new File(nombreArchivo));

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Error al generar PDF\n" + e.getMessage());

    }

}
}
