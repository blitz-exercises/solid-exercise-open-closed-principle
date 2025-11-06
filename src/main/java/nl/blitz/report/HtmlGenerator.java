package nl.blitz.report;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HtmlGenerator implements ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    @Override
    public String generate(ReportRequest request) {
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
    
}
