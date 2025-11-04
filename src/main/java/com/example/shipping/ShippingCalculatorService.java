package com.example.shipping;

/**
 * Contract for shipping cost calculation operations.
 * 
 * IMPORTANT: This interface should remain UNTOUCHED during refactoring exercises.
 * It defines the public API that ShippingCalculator must implement.
 */
public interface ShippingCalculatorService {
    
    /**
     * Calculates shipping cost for a given shipment.
     * 
     * @param shipment The shipment details including weight, dimensions, and shipping method
     * @return A shipping quote with cost, estimated delivery days, and carrier information
     * @throws IllegalArgumentException if the shipping method is not supported
     */
    ShippingQuote calculateShipping(Shipment shipment);
    
    /**
     * Calculates shipping cost for STANDARD shipping method.
     * 
     * @param shipment The shipment details
     * @return A shipping quote for standard shipping
     */
    ShippingQuote calculateStandardShipping(Shipment shipment);
    
    /**
     * Calculates shipping cost for EXPRESS shipping method.
     * 
     * @param shipment The shipment details
     * @return A shipping quote for express shipping
     */
    ShippingQuote calculateExpressShipping(Shipment shipment);
    
    /**
     * Calculates shipping cost for OVERNIGHT shipping method.
     * 
     * @param shipment The shipment details
     * @return A shipping quote for overnight shipping
     */
    ShippingQuote calculateOvernightShipping(Shipment shipment);
    
    /**
     * Calculates shipping cost for INTERNATIONAL shipping method.
     * 
     * @param shipment The shipment details
     * @return A shipping quote for international shipping
     */
    ShippingQuote calculateInternationalShipping(Shipment shipment);
}

