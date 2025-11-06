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
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase(shippingType))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: " + shippingType));
    }
    
    @Override
    public ShippingQuote calculateStandardShipping(Shipment shipment) {
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase("STANDARD"))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: STANDARD"));
    }
    
    @Override
    public ShippingQuote calculateExpressShipping(Shipment shipment) {
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase("EXPRESS"))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: EXPRESS"));
    }
    
    @Override
    public ShippingQuote calculateOvernightShipping(Shipment shipment) {
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase("OVERNIGHT"))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: OVERNIGHT"));
    }
    
    @Override
    public ShippingQuote calculateInternationalShipping(Shipment shipment) {
        return shippingMethods.stream()
                .filter(s -> s.getShippingType().equalsIgnoreCase("INTERNATIONAL"))
                .findFirst()
                .map(s -> s.calculate(shipment))
                .orElseThrow(() -> new IllegalArgumentException("Unsupported shipping method: INTERNATIONAL"));
    }

}
