# Report Generator Exercise

## Overview
Learn the Open/Closed Principle by refactoring a rigid report generation system that violates OCP through conditional logic. The system requires modifications to add new output formats.

## Exercise Goals
- Identify OCP violations in conditional format handling
- Understand why modifying existing code for new formats is problematic
- Apply the Strategy pattern to make report generation extensible
- Write code that's open for extension (new formats) but closed for modification (core logic)

## What You'll Implement
The current `ReportGenerator` class uses if/else statements to handle different output formats. You need to:
1. Create Strategy implementations for each report format
2. Eliminate the conditional logic from the core generator
3. Make it possible to add new formats without modifying existing code
4. Maintain the existing `ReportGeneratorService` interface (UNTOUCHED)

## Key Concepts
- **Open/Closed Principle**: Open for extension, closed for modification
- **Strategy Pattern**: Encapsulate algorithms in separate classes
- **Dependency Injection**: Inject strategies instead of hardcoding them
- **Polymorphism**: Use interfaces to handle different implementations uniformly

## Important Notes
- The `ReportGeneratorService` interface cannot be modified (UNTOUCHED)
- Tests must pass before and after refactoring
- Add new strategy implementations in the same package
- Consider how the main service instantiates and uses strategies

## Testing
All tests should pass both before and after refactoring:
```bash
mvn test -Dtest=ReportGeneratorIntegrationTest
```
