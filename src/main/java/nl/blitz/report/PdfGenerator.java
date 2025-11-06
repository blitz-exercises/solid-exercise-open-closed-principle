package nl.blitz.report;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfGenerator implements ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    @Override
    public String generate(ReportRequest request) {
        logger.log(Level.INFO, "Generating PDF report");
        ReportData data = request.getData();

        StringBuilder pdf = new StringBuilder();
        pdf.append("%PDF-1.4\n");
        pdf.append("1 0 obj\n");
        pdf.append("<<\n");
        pdf.append("/Title (").append(data.getTitle()).append(")\n");
        pdf.append("/Creator (ReportGenerator)\n");
        pdf.append(">>\n");
        pdf.append("endobj\n");
        pdf.append("xref\n");
        pdf.append("trailer\n");
        pdf.append("<<\n");
        pdf.append("/Root 1 0 R\n");
        pdf.append(">>\n");
        pdf.append("startxref\n");
        pdf.append("0\n");
        pdf.append("%%EOF\n");

        // Add content
        pdf.append("\nContent:\n");
        pdf.append(data.getTitle()).append("\n\n");

        // Headers
        for (String header : data.getHeaders()) {
            pdf.append(header).append("\t");
        }
        pdf.append("\n");

        // Rows
        for (List<String> row : data.getRows()) {
            for (String cell : row) {
                pdf.append(cell).append("\t");
            }
            pdf.append("\n");
        }

        if (data.getMetadata() != null) {
            pdf.append("\nMetadata: ").append(data.getMetadata());
        }

        return pdf.toString();
    }
}
