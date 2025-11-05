package nl.blitz.report;

/**
 * Data class representing a report generation request.
 * This class remains unchanged during refactoring exercises.
 */
public final class ReportRequest {
    
    private final String format;
    private final ReportData data;
    private final String outputPath;
    
    public ReportRequest(String format, ReportData data, String outputPath) {
        this.format = format;
        this.data = data;
        this.outputPath = outputPath;
    }
    
    public String getFormat() {
        return format;
    }
    
    public ReportData getData() {
        return data;
    }
    
    public String getOutputPath() {
        return outputPath;
    }
}

