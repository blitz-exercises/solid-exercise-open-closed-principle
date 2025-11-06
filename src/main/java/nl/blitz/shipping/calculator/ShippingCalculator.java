package nl.blitz.shipping.calculator;

import nl.blitz.report.formatter.*;
import nl.blitz.shipping.method.*;
import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.shipment.ShippingQuote;

import java.util.List;

public class ShippingCalculator implements ShippingCalculatorService {

    private final List<ShippingMethod> shippingMethods = List.of(
            new StandardShipping(),
            new ExpressShipping(),
            new OvernightShipping(),
            new InternationalShipping()
    );
    
    @Override
    public ShippingQuote calculateShipping(Shipment shipment) {
        String shippingType = shipment.getShippingMethod();
        return executeShipment(shipment, shippingType);
    }
    
    @Override
    public ShippingQuote calculateStandardShipping(Shipment shipment) {
        return executeShipment(shipment, "STANDARD");
    }
    
    @Override
    public ShippingQuote calculateExpressShipping(Shipment shipment) {
        return executeShipment(shipment, "EXPRESS");
    }
    
    @Override
    public ShippingQuote calculateOvernightShipping(Shipment shipment) {
        return executeShipment(shipment, "OVERNIGHT");
    }
    
    @Override
    public ShippingQuote calculateInternationalShipping(Shipment shipment) {
        return executeShipment(shipment, "INTERNATIONAL");
    }

    private ShippingQuote executeShipment(Shipment shipment, String shipmentMethod) {
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase(shipmentMethod))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: " + shipmentMethod));
    }

}
