/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ArchivoPDF;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Usuario;
import Presentacion.GuiCliente2.CarritoPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;/**
 *
 * @author user
 */
public class PDF {
    
    public static void generarBoleta(Usuario usuario,
                                     Cliente cliente,
                                     java.util.List<CarritoPanel.CarritoItem> items,
                                     double total) {

        try {

            File archivo = new File("Boleta_" + System.currentTimeMillis() + ".pdf");

            Document documento = new Document();

            PdfWriter.getInstance(documento,
                    new java.io.FileOutputStream(archivo));

            documento.open();

            Font titulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font texto = new Font(Font.FontFamily.HELVETICA, 12);

            Paragraph p = new Paragraph("GRANJA - BOLETA DE COMPRA", titulo);
            p.setAlignment(Element.ALIGN_CENTER);
            documento.add(p);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Cliente: " + usuario.getNombre(), texto));
            documento.add(new Paragraph("DNI: " + cliente.getDni(), texto));
            documento.add(new Paragraph("Direccion: " + cliente.getDireccion(), texto));
            documento.add(new Paragraph("Fecha: " + java.time.LocalDateTime.now(), texto));

            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(4);
            tabla.setWidthPercentage(100);

            tabla.addCell(crearCelda("Animal"));
            tabla.addCell(crearCelda("Cantidad"));
            tabla.addCell(crearCelda("Precio"));
            tabla.addCell(crearCelda("Subtotal"));

            for (CarritoPanel.CarritoItem item : items) {

                tabla.addCell(item.animal.getNombre());
                tabla.addCell(String.valueOf(item.cantidad));
                tabla.addCell(String.format("S/ %.2f", item.animal.getPrecio()));
                tabla.addCell(String.format("S/ %.2f", item.getSubtotal()));

            }

            documento.add(tabla);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("TOTAL: S/ " + String.format("%.2f", total), titulo));

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Gracias por su compra.", texto));

            documento.close();

            Desktop.getDesktop().open(archivo);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static PdfPCell crearCelda(String texto) {

        PdfPCell celda = new PdfPCell(new Phrase(texto));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);

        return celda;

    }
    
     
    
    
            
}
