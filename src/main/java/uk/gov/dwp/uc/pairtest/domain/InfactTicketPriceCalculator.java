package uk.gov.dwp.uc.pairtest.domain;

public class InfactTicketPriceCalculator implements TicketPriceCalculator {
    private int unitPrice = 0;
    @Override
    public int calculatePrice(int numberOfTickets) {
        return unitPrice * numberOfTickets;
    }

    public int getAllocatedTickets(int requestedTicketCount) { return 0 * requestedTicketCount; }
}
