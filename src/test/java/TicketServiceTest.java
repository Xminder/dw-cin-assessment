import org.junit.Test;
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.TicketService;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

public class TicketServiceTest {

   @Test(expected = InvalidPurchaseException.class)
    public void validAccountHolderRuleBroken_Test(){
        TicketService ticketService = new TicketServiceImpl(
                new TicketPaymentServiceImpl(),
                new SeatReservationServiceImpl()
        );

       ticketService.purchaseTickets(0L, TicketCollection.twoAdultsTicket());
   }

    @Test(expected = InvalidPurchaseException.class)
    public void adultAlongChildrenRuleBroken_Test(){
        TicketService ticketService = new TicketServiceImpl(
                new TicketPaymentServiceImpl(),
                new SeatReservationServiceImpl()
        );

        ticketService.purchaseTickets(4L, TicketCollection.childAndInfantTickets());
    }

    @Test(expected = InvalidPurchaseException.class)
    public void maximumTicketPerPurchaseRuleBroken_Test(){
        TicketService ticketService = new TicketServiceImpl(
                new TicketPaymentServiceImpl(),
                new SeatReservationServiceImpl()
        );

        ticketService.purchaseTickets(2L, TicketCollection.twoGenerationFamilyTickets());
    }
}
