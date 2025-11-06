package nl.blitz.report;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlGenerator implements ReportGenerator {
    private static final Logger logger = Logger.getLogger(ReportGenerator.class.getName());
    @Override
    public String generate(ReportRequest request) {
        logger.log(Level.INFO, "Generating XML report");
        ReportData data = request.getData();

        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<report>\n");

        // Title
        xml.append("  <title>").append(escapeXml(data.getTitle())).append("</title>\n");

        // Creator / metadata info
        xml.append("  <creator>ReportGenerator</creator>\n");

        // Headers
        xml.append("  <headers>\n");
        for (String header : data.getHeaders()) {
            xml.append("    <header>").append(escapeXml(header)).append("</header>\n");
        }
        xml.append("  </headers>\n");

        // Rows
        xml.append("  <rows>\n");
        for (List<String> row : data.getRows()) {
            xml.append("    <row>\n");
            for (String cell : row) {
                xml.append("      <cell>").append(escapeXml(cell)).append("</cell>\n");
            }
            xml.append("    </row>\n");
        }
        xml.append("  </rows>\n");

        // Metadata (optional)
        if (data.getMetadata() != null) {
            xml.append("  <metadata>").append(escapeXml(data.getMetadata()))
                    .append("</metadata>\n");
        }

        xml.append("</report>");
        return xml.toString();
    }

    /**
     * Escapes XML special characters: <, >, &, ', "
     */
    private String escapeXml(String input) {
        if (input == null)
            return "";
        return input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&apos;");
    }
    
}
