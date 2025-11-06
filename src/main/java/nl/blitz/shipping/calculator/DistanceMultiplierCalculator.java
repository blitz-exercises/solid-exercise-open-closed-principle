package nl.blitz.shipping.calculator;

public class DistanceMultiplierCalculator {

    public double calculate(String origin, String destination) {
        if (origin.equals(destination)) {
            return 1.0;
        }

        boolean isInternational = !origin.equalsIgnoreCase(destination) &&
                (origin.contains("US") || destination.contains("US"));

        if (isInternational) {
            return 2.0;
        }

        if (origin.contains("NY") && destination.contains("CA")) {
            return 1.8;
        } else if (origin.contains("CA") && destination.contains("NY")) {
            return 1.8;
        } else if (origin.contains("TX") && destination.contains("FL")) {
            return 1.5;
        } else {
            return 1.2;
        }
    }

}
