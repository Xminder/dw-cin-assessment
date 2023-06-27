package uk.gov.dwp.uc.pairtest.domain;

public class ChildTicketCalculator implements TicketPriceCalculator {
    private int unitPrice = 10;
    @Override
    public int calculatePrice(int numberOfTickets) {
        return unitPrice * numberOfTickets;
    }

    public int getAllocatedTickets(int requestedTicketCount) { return 1 * requestedTicketCount; }
}
