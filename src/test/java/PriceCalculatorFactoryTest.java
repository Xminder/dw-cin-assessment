import org.junit.Test;
import uk.gov.dwp.uc.pairtest.domain.AdultTicketPriceCalculator;
import uk.gov.dwp.uc.pairtest.domain.ChildTicketCalculator;
import uk.gov.dwp.uc.pairtest.domain.InfactTicketPriceCalculator;
import uk.gov.dwp.uc.pairtest.domain.PriceCalculatorFactory;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class PriceCalculatorFactoryTest {
    @Test
    public void computeTwoAdultTicket_Test(){
        var ticketRequest = TicketCollection.twoAdultsTicket();
        var priceCalculator = PriceCalculatorFactory
                .getPriceCaluclator(ticketRequest.getTicketType());

        assertThat(priceCalculator, instanceOf(AdultTicketPriceCalculator.class));
        assertEquals(40, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
    }

    @Test
    public void computeFourChildrenTicket_Test(){
        var ticketRequest = TicketCollection.forChildrenTicket();
        var priceCalculator = PriceCalculatorFactory
                .getPriceCaluclator(ticketRequest.getTicketType());

        assertThat(priceCalculator, instanceOf(ChildTicketCalculator.class));
        assertEquals(40, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
    }

    @Test
    public void computeThreeInfantTicket_Test(){
        var ticketRequest = TicketCollection.threeInfantTicket();
        var priceCalculator = PriceCalculatorFactory
                .getPriceCaluclator(ticketRequest.getTicketType());

        assertThat(priceCalculator, instanceOf(InfactTicketPriceCalculator.class));
        assertEquals(0, priceCalculator.calculatePrice(ticketRequest.getNoOfTickets()));
    }
}
