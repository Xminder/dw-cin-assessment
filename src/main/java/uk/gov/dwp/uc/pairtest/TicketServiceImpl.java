package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentService;
import thirdparty.seatbooking.SeatReservationService;
import uk.gov.dwp.uc.pairtest.businessrules.AdultAlongChildAndInfactRule;
import uk.gov.dwp.uc.pairtest.businessrules.MaxiumTicketPerPurchaseRule;
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

        checkRule(new AdultAlongChildAndInfactRule(numberOfAdultTicketsBeingPurchased));
        checkRule(new MaxiumTicketPerPurchaseRule(ticketCount));

        ticketPaymentService.makePayment(accountId, ticketAmount);
        seatReservationService.reserveSeat(accountId, ticketCount);
    }

    private void checkRule(BusinessRule rule){
        if (rule.isBroken()){
            throw new InvalidPurchaseException();
        }
    }

    private boolean isChildOrInfantOnly(TicketTypeRequest...ticketTypeRequests){
        boolean hasAdultRequest = true;
        for(TicketTypeRequest ticketTypeRequest: ticketTypeRequests){
            if (ticketTypeRequest.getTicketType() == TicketTypeRequest.Type.ADULT){
                 hasAdultRequest = false;
                 break;
            }
        }

        return hasAdultRequest;
    }

    private int getTicketPrice(TicketTypeRequest.Type type, int noOfTickets){
        int unitPrice = 0;
        if (type == TicketTypeRequest.Type.ADULT)
            unitPrice = 20;
        else if (type == TicketTypeRequest.Type.CHILD)
            unitPrice = 10;

        return unitPrice * noOfTickets;
    }

}
