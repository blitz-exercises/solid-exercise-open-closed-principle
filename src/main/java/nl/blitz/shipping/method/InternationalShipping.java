package nl.blitz.shipping.method;

import nl.blitz.shipping.calculator.DistanceMultiplierCalculator;
import nl.blitz.shipping.calculator.ShippingCalculator;
import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.shipment.ShippingQuote;

import java.util.logging.Level;
import java.util.logging.Logger;

public class InternationalShipping implements ShippingMethod {

    private static final Logger logger = Logger.getLogger(ShippingCalculator.class.getName());

    private static final DistanceMultiplierCalculator DISTANCE_MULTIPLIER_CALCULATOR = new DistanceMultiplierCalculator();

    private static final double INTERNATIONAL_BASE_RATE = 8.00;

    @Override
    public ShippingQuote calculate(Shipment shipment) {
        logger.log(Level.INFO, "Calculating INTERNATIONAL shipping");

        double baseCost = INTERNATIONAL_BASE_RATE * shipment.getWeight();

        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 10.00;
        }

        // International distance multiplier (always higher)
        double distanceMultiplier =
                DISTANCE_MULTIPLIER_CALCULATOR.calculate(shipment.getOrigin(), shipment.getDestination());
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

    @Override
    public String getShippingType() {
        return "INTERNATIONAL";
    }

}
