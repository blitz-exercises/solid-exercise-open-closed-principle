package nl.blitz.report.generator;

import nl.blitz.report.request.ReportRequest;

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

}
