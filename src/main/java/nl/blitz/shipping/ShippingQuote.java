package nl.blitz.shipping;

/**
 * Data class representing a shipping quote result.
 * This class remains unchanged during refactoring exercises.
 */
public final class ShippingQuote {
    
    private final String shippingMethod;
    private final double cost;
    private final int estimatedDays;
    private final String carrier;
    
    public ShippingQuote(String shippingMethod, double cost, int estimatedDays, String carrier) {
        this.shippingMethod = shippingMethod;
        this.cost = cost;
        this.estimatedDays = estimatedDays;
        this.carrier = carrier;
    }
    
    public String getShippingMethod() {
        return shippingMethod;
    }
    
    public double getCost() {
        return cost;
    }
    
    public int getEstimatedDays() {
        return estimatedDays;
    }
    
    public String getCarrier() {
        return carrier;
    }
}

