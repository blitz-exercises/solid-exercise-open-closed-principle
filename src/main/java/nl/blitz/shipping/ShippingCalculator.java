package nl.blitz.shipping;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Shipping calculator implementation that violates the Open/Closed Principle.
 * 
 * This class requires modification whenever a new shipping method needs to be added.
 * Refactor this to follow OCP by extracting shipping method-specific logic into separate classes.
 */
public class ShippingCalculator implements ShippingCalculatorService {
    
    private static final Logger logger = Logger.getLogger(ShippingCalculator.class.getName());
    
    // Base rates (in USD per kg)
    private static final double STANDARD_BASE_RATE = 2.50;
    private static final double EXPRESS_BASE_RATE = 5.00;
    private static final double OVERNIGHT_BASE_RATE = 10.00;
    private static final double INTERNATIONAL_BASE_RATE = 8.00;
    
    @Override
    public ShippingQuote calculateShipping(Shipment shipment) {
        logger.log(Level.INFO, "Calculating shipping for method: " + shipment.getShippingMethod());
        
        // VIOLATION: Using if/else chain to determine shipping method
        // Adding a new shipping method requires modifying this method
        if ("STANDARD".equalsIgnoreCase(shipment.getShippingMethod())) {
            return calculateStandardShipping(shipment);
        } else if ("EXPRESS".equalsIgnoreCase(shipment.getShippingMethod())) {
            return calculateExpressShipping(shipment);
        } else if ("OVERNIGHT".equalsIgnoreCase(shipment.getShippingMethod())) {
            return calculateOvernightShipping(shipment);
        } else if ("INTERNATIONAL".equalsIgnoreCase(shipment.getShippingMethod())) {
            return calculateInternationalShipping(shipment);
        } else {
            throw new IllegalArgumentException("Unsupported shipping method: " + shipment.getShippingMethod());
        }
    }
    
    @Override
    public ShippingQuote calculateStandardShipping(Shipment shipment) {
        logger.log(Level.INFO, "Calculating STANDARD shipping");
        
        double baseCost = STANDARD_BASE_RATE * shipment.getWeight();
        
        // Volume-based surcharge
        if (shipment.getVolume() > 50000) { // > 50000 cm³
            baseCost += 5.00;
        }
        
        // Distance-based calculation (simplified)
        double distanceMultiplier = calculateDistanceMultiplier(shipment.getOrigin(), shipment.getDestination());
        double finalCost = baseCost * distanceMultiplier;
        
        // Minimum cost
        if (finalCost < 5.00) {
            finalCost = 5.00;
        }
        
        int estimatedDays = 5;
        
        return new ShippingQuote("STANDARD", finalCost, estimatedDays, "StandardCarrier");
    }
    
    @Override
    public ShippingQuote calculateExpressShipping(Shipment shipment) {
        logger.log(Level.INFO, "Calculating EXPRESS shipping");
        
        double baseCost = EXPRESS_BASE_RATE * shipment.getWeight();
        
        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 8.00;
        }
        
        // Distance-based calculation
        double distanceMultiplier = calculateDistanceMultiplier(shipment.getOrigin(), shipment.getDestination());
        double finalCost = baseCost * distanceMultiplier;
        
        // Express premium
        finalCost *= 1.2; // 20% premium
        
        // Minimum cost
        if (finalCost < 8.00) {
            finalCost = 8.00;
        }
        
        int estimatedDays = 2;
        
        return new ShippingQuote("EXPRESS", finalCost, estimatedDays, "ExpressCarrier");
    }
    
    @Override
    public ShippingQuote calculateOvernightShipping(Shipment shipment) {
        logger.log(Level.INFO, "Calculating OVERNIGHT shipping");
        
        double baseCost = OVERNIGHT_BASE_RATE * shipment.getWeight();
        
        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 15.00;
        }
        
        // Distance-based calculation
        double distanceMultiplier = calculateDistanceMultiplier(shipment.getOrigin(), shipment.getDestination());
        double finalCost = baseCost * distanceMultiplier;
        
        // Overnight premium
        finalCost *= 1.5; // 50% premium
        
        // Minimum cost
        if (finalCost < 15.00) {
            finalCost = 15.00;
        }
        
        int estimatedDays = 1;
        
        return new ShippingQuote("OVERNIGHT", finalCost, estimatedDays, "OvernightCarrier");
    }
    
    @Override
    public ShippingQuote calculateInternationalShipping(Shipment shipment) {
        logger.log(Level.INFO, "Calculating INTERNATIONAL shipping");
        
        double baseCost = INTERNATIONAL_BASE_RATE * shipment.getWeight();
        
        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 10.00;
        }
        
        // International distance multiplier (always higher)
        double distanceMultiplier = calculateDistanceMultiplier(shipment.getOrigin(), shipment.getDestination());
        double finalCost = baseCost * distanceMultiplier * 1.5; // International premium
        
        // Customs and handling fee
        finalCost += 12.50;
        
        // Minimum cost
        if (finalCost < 25.00) {
            finalCost = 25.00;
        }
        
        int estimatedDays = 10;
        
        return new ShippingQuote("INTERNATIONAL", finalCost, estimatedDays, "InternationalCarrier");
    }
    
    // Helper method to calculate distance multiplier (simplified)
    private double calculateDistanceMultiplier(String origin, String destination) {
        // Simplified distance calculation
        // In real implementation, this would use actual distance calculation
        if (origin.equals(destination)) {
            return 1.0;
        }
        
        // Check if international (simplified check)
        boolean isInternational = !origin.equalsIgnoreCase(destination) && 
                                  (origin.contains("US") || destination.contains("US"));
        
        if (isInternational) {
            return 2.0;
        }
        
        // Domestic distance multipliers
        if (origin.contains("NY") && destination.contains("CA")) {
            return 1.8;
        } else if (origin.contains("CA") && destination.contains("NY")) {
            return 1.8;
        } else if (origin.contains("TX") && destination.contains("FL")) {
            return 1.5;
        } else {
            return 1.2; // Default multiplier
        }
    }
}

