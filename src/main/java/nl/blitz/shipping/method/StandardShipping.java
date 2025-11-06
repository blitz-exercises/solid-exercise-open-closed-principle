package nl.blitz.shipping.method;

import nl.blitz.shipping.calculator.DistanceMultiplierCalculator;
import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.calculator.ShippingCalculator;
import nl.blitz.shipping.shipment.ShippingQuote;

import java.util.logging.Level;
import java.util.logging.Logger;

public class StandardShipping implements ShippingMethod {

    private static final Logger logger = Logger.getLogger(ShippingCalculator.class.getName());

    private static final DistanceMultiplierCalculator DISTANCE_MULTIPLIER_CALCULATOR = new DistanceMultiplierCalculator();

    private static final double STANDARD_BASE_RATE = 2.50;

    @Override
    public ShippingQuote calculate(Shipment shipment) {
        logger.log(Level.INFO, "Calculating STANDARD shipping");

        double baseCost = STANDARD_BASE_RATE * shipment.getWeight();

        // Volume-based surcharge
        if (shipment.getVolume() > 50000) { // > 50000 cm³
            baseCost += 5.00;
        }

        // Distance-based calculation (simplified)
        double distanceMultiplier =
                DISTANCE_MULTIPLIER_CALCULATOR.calculate(shipment.getOrigin(), shipment.getDestination());
        double finalCost = baseCost * distanceMultiplier;

        // Minimum cost
        if (finalCost < 5.00) {
            finalCost = 5.00;
        }

        int estimatedDays = 5;

        return new ShippingQuote("STANDARD", finalCost, estimatedDays, "StandardCarrier");
    }

    @Override
    public String getShippingType() {
        return "STANDARD";
    }

}
