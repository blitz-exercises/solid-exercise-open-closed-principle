package nl.blitz.report.formatter;

import nl.blitz.report.generator.ReportGenerator;
import nl.blitz.report.request.ReportData;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVFormatter implements ReportFormatter {

    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());

    @Override
    public String format(ReportData data) {
        logger.log(Level.INFO, "Generating CSV report");

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
    public String getFormatType() {
        return "CSV";
    }

}
