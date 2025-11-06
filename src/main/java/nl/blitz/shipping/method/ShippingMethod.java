package nl.blitz.shipping.method;

import nl.blitz.shipping.shipment.Shipment;
import nl.blitz.shipping.shipment.ShippingQuote;

public interface ShippingMethod {

    ShippingQuote calculate(Shipment shipment);

    String getShippingType();

}
