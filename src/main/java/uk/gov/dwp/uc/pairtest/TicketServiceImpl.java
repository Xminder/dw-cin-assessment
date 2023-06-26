package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.businessrules.AdultAlongChildAndInfantRule;
import uk.gov.dwp.uc.pairtest.businessrules.MaximumTicketPerPurchaseRule;
import uk.gov.dwp.uc.pairtest.businessrules.ValidAccountHolderRule;
import uk.gov.dwp.uc.pairtest.domain.BusinessRule;
import uk.gov.dwp.uc.pairtest.domain.PriceCalculatorFactory;
import uk.gov.dwp.uc.pairtest.domain.TicketPriceCalculator;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */
    private final TicketPaymentService ticketPaymentService;
    private final SeatReservationService seatReservationService;

    public TicketServiceImpl(TicketPaymentService ticketPaymentService,
                             SeatReservationService seatReservationService){
        this.ticketPaymentService = ticketPaymentService;
        this.seatReservationService = seatReservationService;
    }

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {

        checkRule(new ValidAccountHolderRule(accountId));
        // compute amount for requested tickets
        // make payment request
        // compute no of seats
        // make request to seat reservation service
        int ticketAmount = 0;
        int ticketCount = 0;
        int numberOfAdultTicketsBeingPurchased = 0;
        for(TicketTypeRequest ticketTypeRequest: ticketTypeRequests){
            if (ticketTypeRequest.getTicketType().equals(TicketTypeRequest.Type.INFANT))
                continue;
            if (ticketTypeRequest.getTicketType().equals(TicketTypeRequest.Type.ADULT))
                numberOfAdultTicketsBeingPurchased++;

            TicketPriceCalculator ticketPriceCalculator =
                    PriceCalculatorFactory.getPriceCaluclator(ticketTypeRequest.getTicketType());
            ticketAmount += ticketPriceCalculator.calculatePrice(ticketTypeRequest.getNoOfTickets());
            ticketCount += ticketTypeRequest.getNoOfTickets();
        }

        checkRule(new AdultAlongChildAndInfantRule(numberOfAdultTicketsBeingPurchased));
        checkRule(new MaximumTicketPerPurchaseRule(ticketCount));

        ticketPaymentService.makePayment(accountId, ticketAmount);
        seatReservationService.reserveSeat(accountId, ticketCount);
    }

    private void checkRule(BusinessRule rule){
        if (rule.isBroken()){
            throw new InvalidPurchaseException();
        }
    }
}
