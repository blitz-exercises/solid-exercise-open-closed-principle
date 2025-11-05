package nl.blitz.shipping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test for the complete shipping calculation flow.
 * 
 * IMPORTANT: This test class should remain UNTOUCHED during refactoring exercises.
 * The ShippingCalculatorService interface defines the public API contract that must be maintained.
 */
class ShippingCalculatorIntegrationTest {
    
    private ShippingCalculatorService shippingCalculator;
    
    @BeforeEach
    void setUp() {
        shippingCalculator = new ShippingCalculator();
    }
    
    @Test
    void testCalculateStandardShipping() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote quote = shippingCalculator.calculateStandardShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("STANDARD", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(5, quote.getEstimatedDays());
        assertEquals("StandardCarrier", quote.getCarrier());
    }
    
    @Test
    void testCalculateExpressShipping() {
        Shipment shipment = new Shipment(3.0, 25.0, 20.0, 10.0, "NY", "CA", "EXPRESS", 1);
        
        ShippingQuote quote = shippingCalculator.calculateExpressShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("EXPRESS", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(2, quote.getEstimatedDays());
        assertEquals("ExpressCarrier", quote.getCarrier());
    }
    
    @Test
    void testCalculateOvernightShipping() {
        Shipment shipment = new Shipment(2.0, 20.0, 15.0, 10.0, "NY", "CA", "OVERNIGHT", 1);
        
        ShippingQuote quote = shippingCalculator.calculateOvernightShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("OVERNIGHT", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(1, quote.getEstimatedDays());
        assertEquals("OvernightCarrier", quote.getCarrier());
    }
    
    @Test
    void testCalculateInternationalShipping() {
        Shipment shipment = new Shipment(4.0, 35.0, 25.0, 20.0, "US", "UK", "INTERNATIONAL", 1);
        
        ShippingQuote quote = shippingCalculator.calculateInternationalShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("INTERNATIONAL", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(10, quote.getEstimatedDays());
        assertEquals("InternationalCarrier", quote.getCarrier());
    }
    
    @Test
    void testCalculateShippingWithStandardMethod() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote quote = shippingCalculator.calculateShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("STANDARD", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(5, quote.getEstimatedDays());
    }
    
    @Test
    void testCalculateShippingWithExpressMethod() {
        Shipment shipment = new Shipment(3.0, 25.0, 20.0, 10.0, "NY", "CA", "EXPRESS", 1);
        
        ShippingQuote quote = shippingCalculator.calculateShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("EXPRESS", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(2, quote.getEstimatedDays());
    }
    
    @Test
    void testCalculateShippingWithOvernightMethod() {
        Shipment shipment = new Shipment(2.0, 20.0, 15.0, 10.0, "NY", "CA", "OVERNIGHT", 1);
        
        ShippingQuote quote = shippingCalculator.calculateShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("OVERNIGHT", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(1, quote.getEstimatedDays());
    }
    
    @Test
    void testCalculateShippingWithInternationalMethod() {
        Shipment shipment = new Shipment(4.0, 35.0, 25.0, 20.0, "US", "UK", "INTERNATIONAL", 1);
        
        ShippingQuote quote = shippingCalculator.calculateShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("INTERNATIONAL", quote.getShippingMethod());
        assertTrue(quote.getCost() > 0);
        assertEquals(10, quote.getEstimatedDays());
    }
    
    @Test
    void testCalculateShippingWithUnsupportedMethod() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "SAME_DAY", 1);
        
        assertThrows(IllegalArgumentException.class, () -> {
            shippingCalculator.calculateShipping(shipment);
        });
    }
    
    @Test
    void testCalculateShippingWithCaseInsensitiveMethod() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "standard", 1);
        
        ShippingQuote quote = shippingCalculator.calculateShipping(shipment);
        
        assertNotNull(quote);
        assertEquals("STANDARD", quote.getShippingMethod());
    }
    
    @Test
    void testStandardShippingMinimumCost() {
        Shipment smallShipment = new Shipment(0.5, 10.0, 10.0, 10.0, "NY", "NY", "STANDARD", 1);
        
        ShippingQuote quote = shippingCalculator.calculateStandardShipping(smallShipment);
        
        assertTrue(quote.getCost() >= 5.00);
    }
    
    @Test
    void testExpressShippingMinimumCost() {
        Shipment smallShipment = new Shipment(0.5, 10.0, 10.0, 10.0, "NY", "NY", "EXPRESS", 1);
        
        ShippingQuote quote = shippingCalculator.calculateExpressShipping(smallShipment);
        
        assertTrue(quote.getCost() >= 8.00);
    }
    
    @Test
    void testOvernightShippingMinimumCost() {
        Shipment smallShipment = new Shipment(0.5, 10.0, 10.0, 10.0, "NY", "NY", "OVERNIGHT", 1);
        
        ShippingQuote quote = shippingCalculator.calculateOvernightShipping(smallShipment);
        
        assertTrue(quote.getCost() >= 15.00);
    }
    
    @Test
    void testInternationalShippingMinimumCost() {
        Shipment smallShipment = new Shipment(0.5, 10.0, 10.0, 10.0, "US", "UK", "INTERNATIONAL", 1);
        
        ShippingQuote quote = shippingCalculator.calculateInternationalShipping(smallShipment);
        
        assertTrue(quote.getCost() >= 25.00);
    }
    
    @Test
    void testVolumeBasedSurcharge() {
        // Large volume shipment (> 50000 cm³)
        Shipment largeVolumeShipment = new Shipment(5.0, 60.0, 50.0, 30.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote quote = shippingCalculator.calculateStandardShipping(largeVolumeShipment);
        
        assertNotNull(quote);
        assertTrue(quote.getCost() > 0);
    }
    
    @Test
    void testInternationalShippingIncludesCustomsFee() {
        Shipment shipment = new Shipment(2.0, 20.0, 15.0, 10.0, "US", "UK", "INTERNATIONAL", 1);
        
        ShippingQuote quote = shippingCalculator.calculateInternationalShipping(shipment);
        
        assertNotNull(quote);
        // International shipping should include customs fee, so cost should be higher
        assertTrue(quote.getCost() >= 25.00);
    }
    
    @Test
    void testCostIncreasesWithWeight() {
        Shipment lightShipment = new Shipment(1.0, 20.0, 15.0, 10.0, "NY", "CA", "STANDARD", 1);
        Shipment heavyShipment = new Shipment(10.0, 20.0, 15.0, 10.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote lightQuote = shippingCalculator.calculateStandardShipping(lightShipment);
        ShippingQuote heavyQuote = shippingCalculator.calculateStandardShipping(heavyShipment);
        
        assertTrue(heavyQuote.getCost() > lightQuote.getCost());
    }
    
    @Test
    void testExpressMoreExpensiveThanStandard() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote standardQuote = shippingCalculator.calculateStandardShipping(shipment);
        ShippingQuote expressQuote = shippingCalculator.calculateExpressShipping(shipment);
        
        assertTrue(expressQuote.getCost() > standardQuote.getCost());
    }
    
    @Test
    void testOvernightMoreExpensiveThanExpress() {
        Shipment shipment = new Shipment(5.0, 30.0, 20.0, 15.0, "NY", "CA", "STANDARD", 1);
        
        ShippingQuote expressQuote = shippingCalculator.calculateExpressShipping(shipment);
        ShippingQuote overnightQuote = shippingCalculator.calculateOvernightShipping(shipment);
        
        assertTrue(overnightQuote.getCost() > expressQuote.getCost());
    }
}

