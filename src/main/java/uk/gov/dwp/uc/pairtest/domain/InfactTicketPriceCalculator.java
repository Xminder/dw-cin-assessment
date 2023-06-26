package uk.gov.dwp.uc.pairtest.domain;

public class InfactTicketPriceCalculator implements TicketPriceCalculator {
    private int unitPrice = 0;
    @Override
    public int calculatePrice(int numberOfTickets) {
        return unitPrice * numberOfTickets;
    }
}
