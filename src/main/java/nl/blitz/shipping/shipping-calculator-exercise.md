# Shipping Calculator Exercise

## Overview

This exercise demonstrates a **Shipping Calculator** system that violates the Open/Closed Principle. The current implementation requires modifying the `ShippingCalculator` class whenever a new shipping method needs to be added (e.g., STANDARD, EXPRESS, OVERNIGHT, INTERNATIONAL). Your task is to refactor this code to follow OCP by making it extensible without modification.

## The Problem

The `ShippingCalculator` class violates OCP in several ways:

1. **Conditional Logic**: The class uses if/else chains or switch statements to calculate costs based on shipping method type
2. **Method-Specific Logic**: Cost calculation logic is embedded directly in the calculator for each shipping method
3. **Modification Required**: Adding a new shipping method (e.g., SAME_DAY, ECONOMY) requires modifying the `ShippingCalculator` class
4. **Tight Coupling**: Shipping method logic is tightly coupled to the calculator class

### Example Violation

```java
public ShippingQuote calculateShipping(Shipment shipment) {
    String method = shipment.getShippingMethod();
    double baseCost = 0.0;
    int estimatedDays = 0;
    
    if ("STANDARD".equals(method)) {
        baseCost = calculateStandardCost(shipment);
        estimatedDays = 5;
    } else if ("EXPRESS".equals(method)) {
        baseCost = calculateExpressCost(shipment);
        estimatedDays = 2;
    } else if ("OVERNIGHT".equals(method)) {
        baseCost = calculateOvernightCost(shipment);
        estimatedDays = 1;
    } else if ("INTERNATIONAL".equals(method)) {
        baseCost = calculateInternationalCost(shipment);
        estimatedDays = 10;
    }
    // Adding new method requires modifying this code
}
```

## Why This Is a Problem

1. **Violation of OCP**: The class is not closed for modification - every new shipping method requires changing existing code
2. **Risk of Breaking Changes**: Modifying the calculator could break existing shipping calculations
3. **Testing Overhead**: Every modification requires retesting all existing shipping methods
4. **Poor Separation of Concerns**: Shipping method logic is mixed with calculator logic
5. **Maintenance Difficulty**: As shipping methods grow, the class becomes harder to maintain
6. **Limited Extensibility**: Cannot add new shipping methods without access to the source code
7. **Business Logic Coupling**: Shipping rules and rates are hardcoded in the calculator

## Your Task

Refactor the `ShippingCalculator` class to follow the Open/Closed Principle by:

1. **Extract Shipping Strategy**: Create a `ShippingMethod` interface that defines how to calculate shipping costs
2. **Implement Shipping Strategies**: Create separate classes for each shipping method (e.g., `StandardShipping`, `ExpressShipping`, `OvernightShipping`, `InternationalShipping`)
3. **Use Dependency Injection**: Inject shipping methods into the calculator rather than hardcoding them
4. **Remove Conditional Logic**: Replace if/else chains with polymorphism
5. **Maintain Contracts**: Ensure the `ShippingCalculatorService` interface remains unchanged

### Refactoring Steps

1. **Create `ShippingMethod` Interface**
   ```java
   public interface ShippingMethod {
       ShippingQuote calculate(Shipment shipment);
       String getMethodName();
   }
   ```

2. **Implement Shipping Strategies**
   - Create `StandardShipping`, `ExpressShipping`, `OvernightShipping`, `InternationalShipping` classes
   - Each implements `ShippingMethod` interface
   - Move shipping method-specific logic from `ShippingCalculator` to respective shipping classes

3. **Refactor `ShippingCalculator`**
   - Remove method-specific calculation methods
   - Use a collection or map of shipping methods
   - Delegate calculation to the appropriate shipping method based on shipment
   - Accept shipping methods via constructor or setter

4. **Update Factory/Creation Logic**
   - Create shipping methods somewhere (factory, configuration, etc.)
   - Inject them into `ShippingCalculator`

## What Must Remain Unchanged

The following files define the public API contract and must **NOT** be modified:

- `ShippingCalculatorService.java` - Interface defining the public API
- `ShippingCalculatorIntegrationTest.java` - Integration tests verifying the contract
- `Shipment.java` - Data class structure
- `ShippingQuote.java` - Data class structure

These files represent the contract that must be maintained. Your refactoring should preserve this contract while improving the internal design.

## Expected Structure After Refactoring

```
com.example.shipping/
├── ShippingCalculatorService.java         # Interface (UNTOUCHED)
├── ShippingCalculator.java                # Refactored - uses shipping methods
├── ShippingMethod.java                    # NEW - Interface for shipping methods
├── StandardShipping.java                  # NEW - Standard shipping strategy
├── ExpressShipping.java                   # NEW - Express shipping strategy
├── OvernightShipping.java                 # NEW - Overnight shipping strategy
├── InternationalShipping.java             # NEW - International shipping strategy
├── Shipment.java                          # Data class (UNCHANGED)
└── ShippingQuote.java                     # Data class (UNCHANGED)
```

## Success Criteria

Your refactoring is successful when:

1. ✅ All existing tests pass without modification
2. ✅ `ShippingCalculator` can be extended with new shipping methods without modification
3. ✅ Shipping method-specific logic is separated into dedicated classes
4. ✅ No if/else chains or switch statements for shipping method selection
5. ✅ The `ShippingCalculatorService` interface contract is preserved
6. ✅ Code follows clean code principles (single responsibility, meaningful names)

## Evaluation

### Before Refactoring
- Adding SAME_DAY shipping: Requires modifying `ShippingCalculator` class
- Testing: Must test all shipping methods after any change
- Risk: High - modifications could break existing functionality
- Coupling: Shipping rules tightly coupled to calculator

### After Refactoring
- Adding SAME_DAY shipping: Create `SameDayShipping` class, register it - no modification to `ShippingCalculator`
- Testing: Only need to test new shipping method
- Risk: Low - core calculator logic unchanged
- Coupling: Shipping rules isolated in respective classes

## Benefits of Following OCP

After refactoring, you'll achieve:

1. **Extensibility**: Add new shipping methods by creating new shipping classes
2. **Maintainability**: Shipping method-specific changes isolated to respective classes
3. **Testability**: Each shipping method can be tested independently
4. **Flexibility**: Easy to swap implementations or add new ones
5. **Reduced Risk**: Core calculator logic remains untouched when extending
6. **Business Logic Separation**: Shipping rules and rates can be configured or changed independently

## Getting Started

1. **Review the Code**
   - Read `ShippingCalculator.java` and identify OCP violations
   - Understand the current implementation
   - Run tests: `mvn test -Dtest=ShippingCalculatorIntegrationTest`

2. **Plan Your Refactoring**
   - Identify all shipping method-specific logic
   - Design the `ShippingMethod` interface
   - Plan how to inject shipping methods

3. **Implement Step by Step**
   - Create the interface first
   - Extract one shipping method at a time (start with STANDARD)
   - Refactor `ShippingCalculator` gradually
   - Run tests frequently to ensure nothing breaks

4. **Verify Success**
   - All tests pass
   - New shipping methods can be added without modifying `ShippingCalculator`
   - Code is cleaner and more maintainable

## Running Tests

```bash
# Run all tests
mvn test

# Run only Shipping Calculator tests
mvn test -Dtest=ShippingCalculatorIntegrationTest

# Run with verbose output
mvn test -Dtest=ShippingCalculatorIntegrationTest -X
```

## Notes

- The initial code works correctly - your goal is to improve design, not fix bugs
- Focus on OCP, but maintain good coding practices (naming, structure, etc.)
- Consider edge cases (invalid weights, distances, etc.)
- Use logging appropriately (avoid System.out.println)
- Shipping costs should be calculated based on weight, distance, and method-specific rules

