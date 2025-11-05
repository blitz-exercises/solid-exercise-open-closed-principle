package nl.blitz.report;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Report generator implementation that violates the Open/Closed Principle.
 * 
 * This class requires modification whenever a new report format needs to be added.
 * Refactor this to follow OCP by extracting format-specific logic into separate classes.
 */
public class ReportGenerator implements ReportGeneratorService {
    
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    
    @Override
    public String generate(ReportRequest request) {
        logger.log(Level.INFO, "Generating report in format: " + request.getFormat());
        
        // VIOLATION: Using if/else chain to determine format
        // Adding a new format requires modifying this method
        if ("PDF".equalsIgnoreCase(request.getFormat())) {
            return generatePDF(request);
        } else if ("CSV".equalsIgnoreCase(request.getFormat())) {
            return generateCSV(request);
        } else if ("HTML".equalsIgnoreCase(request.getFormat())) {
            return generateHTML(request);
        } else if ("JSON".equalsIgnoreCase(request.getFormat())) {
            return generateJSON(request);
        } else {
            throw new IllegalArgumentException("Unsupported format: " + request.getFormat());
        }
    }
    
    @Override
    public String generatePDF(ReportRequest request) {
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
    
    @Override
    public String generateCSV(ReportRequest request) {
        logger.log(Level.INFO, "Generating CSV report");
        ReportData data = request.getData();
        
        StringBuilder csv = new StringBuilder();
        
        // Headers
        List<String> headers = data.getHeaders();
        for (int i = 0; i < headers.size(); i++) {
            csv.append(headers.get(i));
            if (i < headers.size() - 1) {
                csv.append(",");
            }
        }
        csv.append("\n");
        
        // Rows
        for (List<String> row : data.getRows()) {
            for (int i = 0; i < row.size(); i++) {
                csv.append(row.get(i));
                if (i < row.size() - 1) {
                    csv.append(",");
                }
            }
            csv.append("\n");
        }
        
        return csv.toString();
    }
    
    @Override
    public String generateHTML(ReportRequest request) {
        logger.log(Level.INFO, "Generating HTML report");
        ReportData data = request.getData();
        
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("<title>").append(data.getTitle()).append("</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("<h1>").append(data.getTitle()).append("</h1>\n");
        html.append("<table border=\"1\">\n");
        
        // Headers
        html.append("<thead>\n<tr>\n");
        for (String header : data.getHeaders()) {
            html.append("<th>").append(header).append("</th>\n");
        }
        html.append("</tr>\n</thead>\n");
        
        // Rows
        html.append("<tbody>\n");
        for (List<String> row : data.getRows()) {
            html.append("<tr>\n");
            for (String cell : row) {
                html.append("<td>").append(cell).append("</td>\n");
            }
            html.append("</tr>\n");
        }
        html.append("</tbody>\n");
        html.append("</table>\n");
        
        if (data.getMetadata() != null) {
            html.append("<p><em>").append(data.getMetadata()).append("</em></p>\n");
        }
        
        html.append("</body>\n");
        html.append("</html>\n");
        
        return html.toString();
    }
    
    @Override
    public String generateJSON(ReportRequest request) {
        logger.log(Level.INFO, "Generating JSON report");
        ReportData data = request.getData();
        
        StringBuilder json = new StringBuilder();
        json.append("{\n");
        json.append("  \"title\": \"").append(data.getTitle()).append("\",\n");
        json.append("  \"headers\": [\n");
        
        // Headers
        List<String> headers = data.getHeaders();
        for (int i = 0; i < headers.size(); i++) {
            json.append("    \"").append(headers.get(i)).append("\"");
            if (i < headers.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ],\n");
        json.append("  \"rows\": [\n");
        
        // Rows
        List<List<String>> rows = data.getRows();
        for (int i = 0; i < rows.size(); i++) {
            json.append("    [\n");
            List<String> row = rows.get(i);
            for (int j = 0; j < row.size(); j++) {
                json.append("      \"").append(row.get(j)).append("\"");
                if (j < row.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
            json.append("    ]");
            if (i < rows.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }
        json.append("  ]");
        
        if (data.getMetadata() != null) {
            json.append(",\n");
            json.append("  \"metadata\": \"").append(data.getMetadata()).append("\"\n");
        } else {
            json.append("\n");
        }
        
        json.append("}\n");
        
        return json.toString();
    }
}

