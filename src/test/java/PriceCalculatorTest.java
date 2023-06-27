import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.*;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PriceCalculatorTest {
    @Test
    public void computeTwoAdultTicket_Test(){
        var ticketRequest = TicketCollection.twoAdultsTicket();
        var priceCalculator = new AdultTicketPriceCalculator();

        assertEquals(40, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
        assertEquals(2, priceCalculator.getAllocatedTickets(ticketRequest.getNoOfTickets()));
    }

    @Test
    public void computeFourChildrenTicket_Test(){
        var ticketRequest = TicketCollection.forChildrenTicket();
        var priceCalculator = new ChildTicketCalculator();

        assertEquals(40, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
        assertEquals(4, priceCalculator.getAllocatedTickets(ticketRequest.getNoOfTickets()));
    }

    @Test
    public void computeThreeInfantTicket_Test(){
        var ticketRequest = TicketCollection.threeInfantTicket();
        var priceCalculator = new InfactTicketPriceCalculator();

        assertEquals(0, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
        assertEquals(0, priceCalculator.getAllocatedTickets(ticketRequest.getNoOfTickets()));
    }
}
