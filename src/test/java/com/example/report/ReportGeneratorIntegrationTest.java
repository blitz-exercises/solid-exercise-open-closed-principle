package com.example.report;

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
    void testGeneratePDF() {
        ReportRequest request = new ReportRequest("PDF", sampleData, "/tmp/report.pdf");
        
        String result = reportGenerator.generatePDF(request);
        
        assertNotNull(result);
        assertTrue(result.contains("%PDF-1.4"));
        assertTrue(result.contains("Sales Report"));
        assertTrue(result.contains("Widget A"));
        assertTrue(result.contains("Widget B"));
        assertTrue(result.contains("Widget C"));
        assertTrue(result.contains("Product"));
        assertTrue(result.contains("Quantity"));
        assertTrue(result.contains("Price"));
    }
    
    @Test
    void testGenerateCSV() {
        ReportRequest request = new ReportRequest("CSV", sampleData, "/tmp/report.csv");
        
        String result = reportGenerator.generateCSV(request);
        
        assertNotNull(result);
        assertTrue(result.contains("Product,Quantity,Price"));
        assertTrue(result.contains("Widget A,10,29.99"));
        assertTrue(result.contains("Widget B,5,19.99"));
        assertTrue(result.contains("Widget C,15,39.99"));
    }
    
    @Test
    void testGenerateHTML() {
        ReportRequest request = new ReportRequest("HTML", sampleData, "/tmp/report.html");
        
        String result = reportGenerator.generateHTML(request);
        
        assertNotNull(result);
        assertTrue(result.contains("<!DOCTYPE html>"));
        assertTrue(result.contains("<title>Sales Report</title>"));
        assertTrue(result.contains("<h1>Sales Report</h1>"));
        assertTrue(result.contains("<table"));
        assertTrue(result.contains("<th>Product</th>"));
        assertTrue(result.contains("<td>Widget A</td>"));
    }
    
    @Test
    void testGenerateJSON() {
        ReportRequest request = new ReportRequest("JSON", sampleData, "/tmp/report.json");
        
        String result = reportGenerator.generateJSON(request);
        
        assertNotNull(result);
        assertTrue(result.contains("\"title\": \"Sales Report\""));
        assertTrue(result.contains("\"headers\":"));
        assertTrue(result.contains("\"Product\""));
        assertTrue(result.contains("\"rows\":"));
        assertTrue(result.contains("\"Widget A\""));
        assertTrue(result.contains("\"metadata\": \"Generated on 2024-01-15\""));
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
    
    @Test
    void testGenerateWithEmptyRows() {
        List<String> headers = Arrays.asList("Column1", "Column2");
        List<List<String>> rows = Arrays.asList();
        ReportData emptyData = new ReportData("Empty Report", headers, rows, null);
        ReportRequest request = new ReportRequest("CSV", emptyData, "/tmp/empty.csv");
        
        String result = reportGenerator.generateCSV(request);
        
        assertNotNull(result);
        assertTrue(result.contains("Column1,Column2"));
        assertFalse(result.contains("Column1,Column2\n\n"));
    }
    
    @Test
    void testGenerateWithNullMetadata() {
        List<String> headers = Arrays.asList("Name");
        List<List<String>> rows = Arrays.asList(Arrays.asList("Test"));
        ReportData dataWithoutMetadata = new ReportData("Test Report", headers, rows, null);
        ReportRequest request = new ReportRequest("JSON", dataWithoutMetadata, "/tmp/test.json");
        
        String result = reportGenerator.generateJSON(request);
        
        assertNotNull(result);
        assertTrue(result.contains("\"title\": \"Test Report\""));
        assertFalse(result.contains("\"metadata\""));
    }
}

