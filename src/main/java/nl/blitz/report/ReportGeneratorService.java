package nl.blitz.report;

/**
 * Contract for report generation operations.
 * 
 * IMPORTANT: This interface should remain UNTOUCHED during refactoring exercises.
 * It defines the public API that ReportGenerator must implement.
 */
public interface ReportGeneratorService {
    
    /**
     * Generates a report in the specified format.
     * 
     * @param request The report request containing data and format specification
     * @return The generated report as a string
     * @throws IllegalArgumentException if the format is not supported
     */
    String generate(ReportRequest request);
    
    /**
     * Generates a PDF report.
     * 
     * @param request The report request containing data
     * @return The generated PDF report as a string
     */
    String generatePDF(ReportRequest request);
    
    /**
     * Generates a CSV report.
     * 
     * @param request The report request containing data
     * @return The generated CSV report as a string
     */
    String generateCSV(ReportRequest request);
    
    /**
     * Generates an HTML report.
     * 
     * @param request The report request containing data
     * @return The generated HTML report as a string
     */
    String generateHTML(ReportRequest request);
    
    /**
     * Generates a JSON report.
     * 
     * @param request The report request containing data
     * @return The generated JSON report as a string
     */
    String generateJSON(ReportRequest request);
}

