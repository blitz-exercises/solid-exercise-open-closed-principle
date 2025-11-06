package nl.blitz.report;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvGenerator implements ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    @Override
    public String generate(ReportRequest request) {
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
    
}
