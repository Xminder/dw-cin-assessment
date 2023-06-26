import org.junit.Test;
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;
import uk.gov.dwp.uc.pairtest.businessrules.AdultAlongChildAndInfantRule;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class AdultAlongChildAndInfantRuleTest {
    @Test
    public void adultAlongChildAndInfantRule_Not_Broken(){
        long adultTicketRequestCount = Arrays.stream(TicketCollection.familyTickets())
                .filter(t -> t.getTicketType() == TicketTypeRequest.Type.ADULT)
                .count();

        var rule =
                new AdultAlongChildAndInfantRule((int)adultTicketRequestCount);
        assertFalse(rule.isBroken());
    }

    @Test
    public void adultAlongChildAndInfantRule_Broken(){
        long adultTicketRequestCount = Arrays.stream(TicketCollection.childAndInfantTickets())
                .filter(t -> t.getTicketType() == TicketTypeRequest.Type.ADULT)
                .count();

        var rule =
                new AdultAlongChildAndInfantRule((int)adultTicketRequestCount);
        assertTrue(rule.isBroken());
    }
}
