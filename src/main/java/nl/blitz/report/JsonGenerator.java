package nl.blitz.report;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonGenerator implements ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    @Override
    public String generate(ReportRequest request) {
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
