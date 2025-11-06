package nl.blitz.report.generator;

import nl.blitz.report.formatter.*;
import nl.blitz.report.request.ReportData;
import nl.blitz.report.request.ReportRequest;

import java.util.List;

public class ReportGenerator implements ReportGeneratorService {

    private final List<ReportFormatter> reportFormatters = List.of(
            new PDFFormatter(),
            new CSVFormatter(),
            new HTMLFormatter(),
            new JSONFormatter()
    );
    
    @Override
    public String generate(ReportRequest request) {
        String format = request.getFormat();
        ReportData data = request.getData();

        return reportFormatters.stream()
                .filter(f -> f.getFormatType().equalsIgnoreCase(format))
                .findFirst()
                .map(f -> f.format(data))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported report format: " + format));
    }

}
