package com.example.report;

import java.util.List;

/**
 * Data class representing report data.
 * This class remains unchanged during refactoring exercises.
 */
public final class ReportData {
    
    private final String title;
    private final List<String> headers;
    private final List<List<String>> rows;
    private final String metadata;
    
    public ReportData(String title, List<String> headers, List<List<String>> rows, String metadata) {
        this.title = title;
        this.headers = headers;
        this.rows = rows;
        this.metadata = metadata;
    }
    
    public String getTitle() {
        return title;
    }
    
    public List<String> getHeaders() {
        return headers;
    }
    
    public List<List<String>> getRows() {
        return rows;
    }
    
    public String getMetadata() {
        return metadata;
    }
}

