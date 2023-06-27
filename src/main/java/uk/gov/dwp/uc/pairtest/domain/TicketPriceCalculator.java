package uk.gov.dwp.uc.pairtest.domain;

public interface TicketPriceCalculator {
    int calculatePrice(int numberOfTickets);
    int getAllocatedTickets(int requestedTicketCount);
}
