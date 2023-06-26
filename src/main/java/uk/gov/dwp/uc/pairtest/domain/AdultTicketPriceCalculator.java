package uk.gov.dwp.uc.pairtest.domain;

public class AdultTicketPriceCalculator implements TicketPriceCalculator {
    private int unitPrice = 20;
    @Override
    public int calculatePrice(int numberOfTickets) {
        return unitPrice * numberOfTickets;
    }
}
