package data;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.Document;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ReportGenerator {
    private static final String URL = "jdbc:sqlite:src/main/java/data/ahorcado.db";

    public static void generarInforme(String destino) {
        try {
            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(new File(destino));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Conectar con SQLite y obtener datos
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM palabras");

            // Agregar datos al PDF
            document.add(new Paragraph("Informe de datos"));
            while (rs.next()) {
                String linea = rs.getInt("id") + " - " + rs.getString("nombre");
                document.add(new Paragraph(linea));
            }

            // Cerrar recursos
            document.close();
            conn.close();
            System.out.println("Informe generado correctamente en: " + destino);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generarInforme("informe.pdf");
    }
}
