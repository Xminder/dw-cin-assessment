import org.junit.Test;
import uk.gov.dwp.uc.pairtest.businessrules.AdultAlongChildAndInfantRule;
import uk.gov.dwp.uc.pairtest.businessrules.MaximumTicketPerPurchaseRule;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MaximumTicketPerPurchaseRuleTest {
    @Test
    public void maximumTicketPerPurchaseRuleTest_Not_Broken(){
        Integer numberOfTicketToBePurchased = Arrays.stream(TicketCollection.familyTickets())
                .map(t -> t.getNoOfTickets())
                .collect(Collectors.summingInt(Integer::intValue));

        var rule =
                new MaximumTicketPerPurchaseRule(numberOfTicketToBePurchased);
        assertFalse(rule.isBroken());
    }

    @Test
    public void maximumTicketPerPurchaseRuleTest_Broken(){
        Integer numberOfTicketToBePurchased = Arrays.stream(TicketCollection.twoGenerationFamilyTickets())
                .map(t -> t.getNoOfTickets())
                .collect(Collectors.summingInt(Integer::intValue));

        var rule =
                new MaximumTicketPerPurchaseRule(numberOfTicketToBePurchased);
        assertTrue(rule.isBroken());
    }
}
