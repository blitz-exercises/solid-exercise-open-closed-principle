# SOLID Exercise Repository - Open/Closed Principle

## Overview

This repository contains exercises designed to help you understand and practice the **Open/Closed Principle (OCP)** through hands-on refactoring tasks. Each exercise includes working code that intentionally violates OCP, along with clear instructions for refactoring.

## What is Open/Closed Principle?

The **Open/Closed Principle** states that:

> Software entities (classes, modules, functions, etc.) should be **open for extension** but **closed for modification**.

This means:
- **Open for extension**: You should be able to add new functionality without breaking existing code
- **Closed for modification**: Once written and tested, the core functionality should not be modified

### Why OCP Matters

- **Maintainability**: Prevents unintended side effects when adding features
- **Testability**: Existing tests remain valid when extending functionality
- **Flexibility**: New features can be added through composition rather than modification
- **Risk Reduction**: Changes to core code are minimized, reducing bugs

### Common Violations

- Using `if/else` or `switch` statements to handle different types
- Hardcoding behavior for specific cases
- Modifying existing classes to add new features
- Tight coupling between classes and concrete implementations

## Exercises

This repository contains the following exercises:

### [Report Generator Exercise](./report-generator-exercise.md)

A report generation system that violates OCP by requiring modifications to add new output formats. Learn how to refactor using the Strategy pattern to make the system extensible.

### [Shipping Calculator Exercise](./shipping-calculator-exercise.md)

A shipping cost calculator that violates OCP by using conditional logic for different shipping methods. Refactor to use interfaces and dependency injection for extensibility.

## Learning Objectives

By completing these exercises, you will:

1. **Identify** OCP violations in existing code
2. **Understand** the problems caused by violating OCP
3. **Apply** refactoring techniques to fix violations
4. **Use** design patterns (Strategy, Factory) to achieve OCP
5. **Maintain** existing contracts while extending functionality
6. **Write** extensible code that doesn't require modification

## Project Structure

```
project-root/
├── README.md                    # Main repository documentation
├── pom.xml                      # Maven configuration
├── report-generator-exercise.md # Report Generator exercise instructions
├── shipping-calculator-exercise.md # Shipping Calculator exercise instructions
└── src/
    ├── main/
    │   └── java/
    │       └── com/
    │           └── example/
    │               ├── report/
    │               │   ├── ReportGeneratorService.java    # Interface (UNTOUCHED)
    │               │   ├── ReportGenerator.java           # Violates OCP
    │               │   ├── ReportData.java                 # Data class
    │               │   └── ReportRequest.java              # Data class
    │               └── shipping/
    │                   ├── ShippingCalculatorService.java  # Interface (UNTOUCHED)
    │                   ├── ShippingCalculator.java        # Violates OCP
    │                   ├── Shipment.java                  # Data class
    │                   └── ShippingQuote.java            # Data class
    └── test/
        └── java/
            └── com/
                └── example/
                    ├── report/
                    │   └── ReportGeneratorIntegrationTest.java  # Tests (UNTOUCHED)
                    └── shipping/
                        └── ShippingCalculatorIntegrationTest.java  # Tests (UNTOUCHED)
```

## How to Run Tests

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Running All Tests

```bash
mvn test
```

### Running Tests for a Specific Exercise

```bash
# Report Generator tests
mvn test -Dtest=ReportGeneratorIntegrationTest

# Shipping Calculator tests
mvn test -Dtest=ShippingCalculatorIntegrationTest
```

### Compiling the Project

```bash
mvn compile
```

## Getting Started

1. **Clone or download** this repository
2. **Navigate** to an exercise directory
3. **Read** the exercise-specific markdown file
4. **Run** the tests to verify they pass initially
5. **Identify** the OCP violations
6. **Refactor** the code following the exercise instructions
7. **Verify** tests still pass after refactoring

## Important Notes

- **UNTOUCHED Files**: Some files are marked as "UNTOUCHED" - these define contracts and must not be modified
- **Working Code**: All code works correctly before refactoring - the goal is to improve design, not fix bugs
- **Test-Driven**: Tests verify the public API contract - they should pass before and after refactoring
- **One Principle**: Each exercise focuses on OCP violations, though code may violate other principles too

## Additional Resources

- [SOLID Principles Overview](https://en.wikipedia.org/wiki/SOLID)
- [Open/Closed Principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle)
- [Strategy Pattern](https://en.wikipedia.org/wiki/Strategy_pattern)
- [Refactoring Techniques](https://refactoring.guru/)

## License

This is an educational exercise repository. Feel free to use and modify for learning purposes.

