package nl.blitz.report;

import nl.blitz.report.generator.ReportGenerator;
import nl.blitz.report.generator.ReportGeneratorService;
import nl.blitz.report.request.ReportData;
import nl.blitz.report.request.ReportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the complete report generation flow.
 * 
 * IMPORTANT: This test class should remain UNTOUCHED during refactoring exercises.
 * The ReportGeneratorService interface defines the public API contract that must be maintained.
 */
class ReportGeneratorIntegrationTest {
    
    private ReportGeneratorService reportGenerator;
    private ReportData sampleData;
    
    @BeforeEach
    void setUp() {
        reportGenerator = new ReportGenerator();
        
        List<String> headers = Arrays.asList("Product", "Quantity", "Price");
        List<List<String>> rows = Arrays.asList(
            Arrays.asList("Widget A", "10", "29.99"),
            Arrays.asList("Widget B", "5", "19.99"),
            Arrays.asList("Widget C", "15", "39.99")
        );
        
        sampleData = new ReportData(
            "Sales Report",
            headers,
            rows,
            "Generated on 2024-01-15"
        );
    }
    
    @Test
    void testGenerateWithPDFFormat() {
        ReportRequest request = new ReportRequest("PDF", sampleData, "/tmp/report.pdf");
        
        String result = reportGenerator.generate(request);
        
        assertNotNull(result);
        assertTrue(result.contains("%PDF-1.4"));
        assertTrue(result.contains("Sales Report"));
    }
    
    @Test
    void testGenerateWithCSVFormat() {
        ReportRequest request = new ReportRequest("CSV", sampleData, "/tmp/report.csv");
        
        String result = reportGenerator.generate(request);
        
        assertNotNull(result);
        assertTrue(result.contains("Product,Quantity,Price"));
        assertTrue(result.contains("Widget A,10,29.99"));
    }
    
    @Test
    void testGenerateWithHTMLFormat() {
        ReportRequest request = new ReportRequest("HTML", sampleData, "/tmp/report.html");
        
        String result = reportGenerator.generate(request);
        
        assertNotNull(result);
        assertTrue(result.contains("<!DOCTYPE html>"));
        assertTrue(result.contains("<h1>Sales Report</h1>"));
    }
    
    @Test
    void testGenerateWithJSONFormat() {
        ReportRequest request = new ReportRequest("JSON", sampleData, "/tmp/report.json");
        
        String result = reportGenerator.generate(request);
        
        assertNotNull(result);
        assertTrue(result.contains("\"title\": \"Sales Report\""));
        assertTrue(result.contains("\"headers\":"));
    }
    
    @Test
    void testGenerateWithUnsupportedFormat() {
        ReportRequest request = new ReportRequest("XML", sampleData, "/tmp/report.xml");
        
        assertThrows(IllegalArgumentException.class, () -> {
            reportGenerator.generate(request);
        });
    }
    
    @Test
    void testGenerateWithCaseInsensitiveFormat() {
        ReportRequest request = new ReportRequest("pdf", sampleData, "/tmp/report.pdf");
        
        String result = reportGenerator.generate(request);
        
        assertNotNull(result);
        assertTrue(result.contains("%PDF-1.4"));
    }

}

