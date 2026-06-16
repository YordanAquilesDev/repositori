package Main;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class CrearPDF {

    public static void main(String[] args) {
        String rutaDestino = "src/main/resources/pdf/reporte_dinamico.pdf";

        // 1. Tus variables dinámicas (estas vendrían de tu interfaz gráfica o BD)
        String nombreGranja = "Granja " + "Yordan";
        String usuarioActivo = "Yordan";
        String fechaActual = LocalDate.now().toString();
        int totalAnimales = 150;
        double ingresosMes = 4550.50;

        // 2. Usamos %s en el HTML donde queremos que vayan los datos
        String plantillaHtml = """
                <html>
                    <head>
                        <style>
                            body { font-family: 'Segoe UI', sans-serif; margin: 30px; color: #1e293b; }
                            .header { background-color: #10b981; color: white; padding: 20px; border-radius: 8px; }
                            h1 { margin: 0; font-size: 24px; }
                            .meta-info { margin-top: 10px; font-size: 12px; opacity: 0.9; }
                            .card { background-color: #f8fafc; border: 1px solid #e2e8f0; padding: 15px; margin-top: 20px; border-radius: 6px; }
                            .monto { color: #059669; font-weight: bold; font-size: 18px; }
                        </style>
                    </head>
                    <body>
                        <div class="header">
                            <h1>Reporte Automatizado: %s</h1>
                            <div class="meta-info">
                                Generado por: <strong>%s</strong> | Fecha: %s
                            </div>
                        </div>                       
                        <div class="card">
                            <h3>Resumen de Inventario</h3>
                            <p>Cantidad total de animales RRRAAAAAAAAAAAAAAAAAAAAA registrados: <strong>%d</strong></p>
                            <p>Ingresos proyectados este mes: <span class="monto">S/. %.2f</span></p>
                        </div>
                    </body>
                </html>
                """;

        // 3. 🌟 LA MAGIA: Fusionamos la plantilla con las variables reales
        // %s para texto, %d para enteros, %.2f para decimales con 2 dígitos
        String htmlFinal = plantillaHtml.formatted(nombreGranja, usuarioActivo, fechaActual, totalAnimales, ingresosMes);

        try {
            File archivoDestino = new File(rutaDestino);
            if (archivoDestino.getParentFile() != null) archivoDestino.getParentFile().mkdirs();

            HtmlConverter.convertToPdf(htmlFinal, new FileOutputStream(archivoDestino));
            System.out.println("🚀 PDF Dinámico creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}