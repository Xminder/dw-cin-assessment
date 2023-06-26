package uk.gov.dwp.uc.pairtest.businessrules;

import uk.gov.dwp.uc.pairtest.domain.BusinessRule;

public class AdultAlongChildAndInfantRule implements BusinessRule {
    private int numberOfAdultTicketBeingPurchase = 0;
    public AdultAlongChildAndInfantRule(int numberOfAdultTicketBeingPurchase){
        this.numberOfAdultTicketBeingPurchase = numberOfAdultTicketBeingPurchase;
    }

    @Override
    public boolean isBroken() {
        return numberOfAdultTicketBeingPurchase <= 0 ;
    }

    @Override
    public String getMessage() {
        return "Child and Infant tickets cannot be purchased without purchasing an Adult";
    }
}
