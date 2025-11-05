# Shipping Calculator Exercise

## Overview
Learn the Open/Closed Principle by refactoring a shipping cost calculator that violates OCP through switch statements. The system requires modifications to add new shipping methods.

## Exercise Goals
- Identify OCP violations in shipping method handling
- Understand why adding new shipping methods requires core code changes
- Apply Strategy or Factory patterns to make shipping calculation extensible
- Write code that's open for extension (new methods) but closed for modification (core logic)

## What You'll Implement
The current `ShippingCalculator` class uses switch statements to handle different shipping methods. You need to:
1. Create implementations for each shipping method
2. Eliminate the switch statement from the core calculator
3. Make it possible to add new shipping methods without modifying existing code
4. Maintain the existing `ShippingCalculatorService` interface (UNTOUCHED)

## Key Concepts
- **Open/Closed Principle**: Open for extension, closed for modification
- **Strategy Pattern**: Different algorithms for calculating shipping costs
- **Factory Pattern**: Creating the appropriate calculator for each method
- **Dependency Injection**: Inject calculators instead of hardcoding them

## Important Notes
- The `ShippingCalculatorService` interface cannot be modified (UNTOUCHED)
- Tests must pass before and after refactoring
- Add new strategy/factory implementations in the same package
- Consider surcharges and minimum fees for different methods

## Testing
All tests should pass both before and after refactoring:
```bash
mvn test -Dtest=ShippingCalculatorIntegrationTest
```
