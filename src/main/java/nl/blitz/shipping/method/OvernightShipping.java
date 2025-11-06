package nl.blitz.shipping.method;

import nl.blitz.shipping.calculator.DistanceMultiplierCalculator;
import nl.blitz.shipping.calculator.ShippingCalculator;
import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.shipment.ShippingQuote;

import java.util.logging.Level;
import java.util.logging.Logger;

public class OvernightShipping implements ShippingMethod {

    private static final Logger logger = Logger.getLogger(ShippingCalculator.class.getName());

    private static final DistanceMultiplierCalculator DISTANCE_MULTIPLIER_CALCULATOR = new DistanceMultiplierCalculator();

    private static final double OVERNIGHT_BASE_RATE = 10.00;

    @Override
    public ShippingQuote calculate(Shipment shipment) {
        logger.log(Level.INFO, "Calculating OVERNIGHT shipping");

        double baseCost = OVERNIGHT_BASE_RATE * shipment.getWeight();

        // Volume-based surcharge
        if (shipment.getVolume() > 50000) {
            baseCost += 15.00;
        }

        // Distance-based calculation
        double distanceMultiplier =
                DISTANCE_MULTIPLIER_CALCULATOR.calculate(shipment.getOrigin(), shipment.getDestination());
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
    public String getShippingType() {
        return "OVERNIGHT";
    }

}
