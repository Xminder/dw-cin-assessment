import org.junit.Test;
import uk.gov.dwp.uc.pairtest.businessrules.AdultAlongChildAndInfantRule;
import uk.gov.dwp.uc.pairtest.businessrules.ValidAccountHolderRule;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ValidAccountHolderRuleTest {
    @Test
    public void validAccountHolderRule_Not_Broken(){
        Long accountId = 4L;

        var rule = new ValidAccountHolderRule(accountId);
        assertFalse(rule.isBroken());
    }

    @Test
    public void validAccountHolderRule_Broken(){
        Long accountId = 0L;

        var rule = new ValidAccountHolderRule(accountId);
        assertTrue(rule.isBroken());
    }
}
