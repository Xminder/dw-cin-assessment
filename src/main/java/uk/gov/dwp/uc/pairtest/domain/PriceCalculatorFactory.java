package uk.gov.dwp.uc.pairtest.domain;

public class PriceCalculatorFactory {
    public static TicketPriceCalculator getPriceCaluclator(TicketTypeRequest.Type type){
        if (type.equals(TicketTypeRequest.Type.ADULT))
            return new AdultTicketPriceCalculator();
        else if (type.equals(TicketTypeRequest.Type.CHILD))
            return new ChildTicketCalculator();
        else if (type.equals(TicketTypeRequest.Type.INFANT))
            return new InfactTicketPriceCalculator();

        throw new IllegalArgumentException();
    }
}
