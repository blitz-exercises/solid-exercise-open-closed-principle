package nl.blitz.shipping.method;

import nl.blitz.shipping.calculator.DistanceMultiplierCalculator;
import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.calculator.ShippingCalculator;
import nl.blitz.shipping.shipment.ShippingQuote;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpressShipping implements ShippingMethod {

    private static final Logger logger = Logger.getLogger(ShippingCalculator.class.getName());

    private static final DistanceMultiplierCalculator DISTANCE_MULTIPLIER_CALCULATOR = new DistanceMultiplierCalculator();

    private static final double EXPRESS_BASE_RATE = 5.00;

    @Override
    public ShippingQuote calculate(Shipment shipment) {
        logger.log(Level.INFO, "Calculating EXPRESS shipping");

        double baseCost = EXPRESS_BASE_RATE * shipment.getWeight();

        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 8.00;
        }

        // Distance-based calculation
        double distanceMultiplier =
                DISTANCE_MULTIPLIER_CALCULATOR.calculate(shipment.getOrigin(), shipment.getDestination());
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
    public String getShippingType() {
        return "EXPRESS";
    }

}
