package nl.blitz.shipping;

/**
 * Data class representing shipment details.
 * This class remains unchanged during refactoring exercises.
 */
public final class Shipment {
    
    private final double weight; // in kilograms
    private final double length; // in centimeters
    private final double width;  // in centimeters
    private final double height; // in centimeters
    private final String origin;
    private final String destination;
    private final String shippingMethod;
    private final int itemCount;
    
    public Shipment(double weight, double length, double width, double height,
                   String origin, String destination, String shippingMethod, int itemCount) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.origin = origin;
        this.destination = destination;
        this.shippingMethod = shippingMethod;
        this.itemCount = itemCount;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public double getLength() {
        return length;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getShippingMethod() {
        return shippingMethod;
    }
    
    public int getItemCount() {
        return itemCount;
    }
    
    public double getVolume() {
        return length * width * height;
    }
}

