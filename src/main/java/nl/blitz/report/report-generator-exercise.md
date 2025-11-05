# Report Generator Exercise

## Overview

This exercise demonstrates a **Report Generator** system that violates the Open/Closed Principle. The current implementation requires modifying the `ReportGenerator` class whenever a new output format needs to be added (e.g., PDF, CSV, HTML, JSON). You will first experience the problem firsthand by adding a new format, then refactor the code to follow OCP, and finally add another format to see the difference.

## The Problem

The `ReportGenerator` class violates OCP in several ways:

1. **Format-Specific Methods**: The class has hardcoded methods like `generatePDF()`, `generateCSV()`, `generateHTML()`, and `generateJSON()`
2. **Conditional Logic**: The main `generate()` method uses if/else chains or switch statements to determine which format to use
3. **Modification Required**: Adding a new format (e.g., XML, Excel) requires modifying the `ReportGenerator` class
4. **Tight Coupling**: Format-specific logic is embedded directly in the generator class

### Example Violation

```java
public String generate(ReportRequest request) {
    if (request.getFormat().equals("PDF")) {
        return generatePDF(request);
    } else if (request.getFormat().equals("CSV")) {
        return generateCSV(request);
    } else if (request.getFormat().equals("HTML")) {
        return generateHTML(request);
    } else if (request.getFormat().equals("JSON")) {
        return generateJSON(request);
    }
    throw new IllegalArgumentException("Unsupported format");
}
```

## Why This Is a Problem

1. **Violation of OCP**: The class is not closed for modification - every new format requires changing existing code
2. **Risk of Breaking Changes**: Modifying the class could break existing functionality
3. **Testing Overhead**: Every modification requires retesting all existing formats
4. **Poor Separation of Concerns**: Format-specific logic is mixed with the generator logic
5. **Maintenance Difficulty**: As formats grow, the class becomes harder to maintain
6. **Limited Extensibility**: Cannot add new formats without access to the source code

## Your Task

This exercise is divided into three phases to help you understand the Open/Closed Principle:

### Phase 1: Experience the Problem (Before Refactoring)

**Add XML Format Support**

Add support for generating XML reports to the existing `ReportGenerator` class. This will help you experience firsthand what it takes to extend the system when it violates OCP.

**What you need to do:**

1. Add a new method `generateXML(ReportRequest request)` to the `ReportGenerator` class
2. Implement XML generation logic that formats the report data as XML
3. Update the `generate()` method to handle the "XML" format (add another else-if branch)
4. Add XML support to the `ReportGeneratorService` interface
5. Write a test to verify XML generation works

**Take note of:**
- How many places you need to modify
- How much code you need to add
- Whether you're touching existing, working code
- The risk of accidentally breaking existing functionality

**Expected XML Format:**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<report>
    <title>Sales Report</title>
    <headers>
        <header>Product</header>
        <header>Quantity</header>
        <header>Price</header>
    </headers>
    <rows>
        <row>
            <cell>Widget A</cell>
            <cell>10</cell>
            <cell>29.99</cell>
        </row>
        <!-- more rows -->
    </rows>
    <metadata>Generated on 2024-01-15</metadata>
</report>
```

**Note:** You'll need to modify the `ReportGeneratorService` interface for this phase. This is an exception to demonstrate the problem - normally we'd keep it unchanged, but for this learning exercise, we're showing what happens when you need to modify contracts.

### Phase 2: Refactor to Follow OCP

Now that you've experienced the problem, refactor the `ReportGenerator` class to follow the Open/Closed Principle:

1. **Extract Format Strategy**: Create a `ReportFormatter` interface that defines how to format reports
2. **Implement Format Strategies**: Create separate classes for each format (e.g., `PDFFormatter`, `CSVFormatter`, `HTMLFormatter`, `JSONFormatter`, `XMLFormatter`)
3. **Use Dependency Injection**: Inject formatters into the generator rather than hardcoding them
4. **Remove Conditional Logic**: Replace if/else chains with polymorphism
5. **Maintain Contracts**: Ensure the `ReportGeneratorService` interface contract is preserved (you can restore it to its original state if you modified it in Phase 1)

**Refactoring Steps:**

1. **Create `ReportFormatter` Interface**
   ```java
   public interface ReportFormatter {
       String format(ReportData data);
       String getFormatType();
   }
   ```

2. **Implement Format Strategies**
   - Create `PDFFormatter`, `CSVFormatter`, `HTMLFormatter`, `JSONFormatter`, `XMLFormatter` classes
   - Each implements `ReportFormatter` interface
   - Move format-specific logic from `ReportGenerator` to respective formatters

3. **Refactor `ReportGenerator`**
   - Remove format-specific methods (`generatePDF()`, `generateCSV()`, etc.)
   - Use a collection or map of formatters
   - Delegate formatting to the appropriate formatter based on request format
   - Accept formatters via constructor or setter

4. **Update Factory/Creation Logic**
   - Create formatters somewhere (factory, configuration, etc.)
   - Inject them into `ReportGenerator`

### Phase 3: Experience the Solution (After Refactoring)

**Add Markdown Format Support**

Now add support for generating Markdown reports. This time, you should be able to add the new format **without modifying** the `ReportGenerator` class.

**What you need to do:**

1. Create a new `MarkdownFormatter` class that implements `ReportFormatter`
2. Register the new formatter in your formatter collection/factory
3. Write a test to verify Markdown generation works

**Take note of:**
- How many places you need to modify (should be minimal!)
- Whether you touched any existing code in `ReportGenerator`
- How easy it is to add the new format
- The reduced risk of breaking existing functionality

**Expected Markdown Format:**
```markdown
# Sales Report

| Product | Quantity | Price |
|---------|----------|-------|
| Widget A | 10 | 29.99 |
| Widget B | 5 | 19.99 |
| Widget C | 15 | 39.99 |

*Generated on 2024-01-15*
```

## What Must Remain Unchanged

The following files define the public API contract and must **NOT** be modified during Phase 2 and Phase 3:

- `ReportGeneratorService.java` - Interface defining the public API (after Phase 1, restore to original)
- `ReportGeneratorIntegrationTest.java` - Integration tests verifying the contract
- `ReportData.java` - Data class structure
- `ReportRequest.java` - Data class structure

**Note:** During Phase 1, you may need to modify `ReportGeneratorService` to add XML support. In Phase 2, you should restore it to its original state and ensure your refactored solution maintains backward compatibility.

## Expected Structure After Refactoring

```
com.example.report/
├── ReportGeneratorService.java         # Interface (UNTOUCHED after Phase 2)
├── ReportGenerator.java                # Refactored - uses formatters
├── ReportFormatter.java                # NEW - Interface for formatters
├── PDFFormatter.java                   # NEW - PDF formatting strategy
├── CSVFormatter.java                   # NEW - CSV formatting strategy
├── HTMLFormatter.java                  # NEW - HTML formatting strategy
├── JSONFormatter.java                  # NEW - JSON formatting strategy
├── XMLFormatter.java                   # NEW - XML formatting strategy (from Phase 1)
├── MarkdownFormatter.java              # NEW - Markdown formatting strategy (from Phase 3)
├── ReportData.java                     # Data class (UNCHANGED)
└── ReportRequest.java                  # Data class (UNCHANGED)
```

## Success Criteria

Your refactoring is successful when:

1. ✅ All existing tests pass without modification
2. ✅ `ReportGenerator` can be extended with new formats without modification (demonstrated in Phase 3)
3. ✅ Format-specific logic is separated into dedicated classes
4. ✅ No if/else chains or switch statements for format selection
5. ✅ The `ReportGeneratorService` interface contract is preserved
6. ✅ Code follows clean code principles (single responsibility, meaningful names)

## Evaluation

### Phase 1: Before Refactoring
- Adding XML format: Requires modifying `ReportGenerator` class, `ReportGeneratorService` interface
- Testing: Must test all formats after any change
- Risk: High - modifications could break existing functionality
- Lines of code modified: Multiple files touched

### Phase 3: After Refactoring
- Adding Markdown format: Create `MarkdownFormatter` class, register it - no modification to `ReportGenerator`
- Testing: Only need to test new formatter
- Risk: Low - core generator logic unchanged
- Lines of code modified: Only new file created

## Benefits of Following OCP

After refactoring, you'll achieve:

1. **Extensibility**: Add new formats by creating new formatter classes
2. **Maintainability**: Format-specific changes isolated to respective formatters
3. **Testability**: Each formatter can be tested independently
4. **Flexibility**: Easy to swap implementations or add new ones
5. **Reduced Risk**: Core generator logic remains untouched when extending

## Getting Started

### Phase 1: Add XML Format (Experience the Problem)

1. **Review the Current Code**
   - Read `ReportGenerator.java` and understand how formats are currently handled
   - Run tests: `mvn test -Dtest=ReportGeneratorIntegrationTest`
   - Notice the if/else chain in the `generate()` method

2. **Add XML Support**
   - Implement `generateXML()` method in `ReportGenerator`
   - Add XML case to the `generate()` method
   - Add `generateXML()` to `ReportGeneratorService` interface
   - Write a test for XML generation
   - Run tests to ensure everything still works

3. **Reflect on the Experience**
   - How many files did you modify?
   - Did you have to touch existing, working code?
   - What's the risk of breaking something?

### Phase 2: Refactor to Follow OCP

1. **Plan Your Refactoring**
   - Identify all format-specific logic
   - Design the `ReportFormatter` interface
   - Plan how to inject formatters

2. **Implement Step by Step**
   - Create the interface first
   - Extract one formatter at a time (start with PDF)
   - Refactor `ReportGenerator` gradually
   - Run tests frequently to ensure nothing breaks
   - Restore `ReportGeneratorService` to original state if modified

3. **Verify Success**
   - All tests pass
   - Code is cleaner and more maintainable

### Phase 3: Add Markdown Format (Experience the Solution)

1. **Add Markdown Support**
   - Create `MarkdownFormatter` class implementing `ReportFormatter`
   - Register it in your formatter collection/factory
   - Write a test for Markdown generation
   - Run tests to verify

2. **Compare with Phase 1**
   - Did you modify `ReportGenerator`? (Should be NO!)
   - How many files did you create/modify?
   - How much easier was it?

## Running Tests

```bash
# Run all tests
mvn test

# Run only Report Generator tests
mvn test -Dtest=ReportGeneratorIntegrationTest

# Run with verbose output
mvn test -Dtest=ReportGeneratorIntegrationTest -X
```

## Notes

- The initial code works correctly - your goal is to improve design, not fix bugs
- Focus on OCP, but maintain good coding practices (naming, structure, etc.)
- Consider edge cases (unknown formats, null values, etc.)
- Use logging appropriately (avoid System.out.println)
- In Phase 1, you'll modify the interface - this is intentional to show the problem
- In Phase 2, restore the interface to maintain the contract
