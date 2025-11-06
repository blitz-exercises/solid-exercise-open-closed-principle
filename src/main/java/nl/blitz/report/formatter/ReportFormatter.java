package nl.blitz.report.formatter;

import nl.blitz.report.request.ReportData;

public interface ReportFormatter {

    String format(ReportData data);

    String getFormatType();

}
