package uk.gov.dwp.uc.pairtest.businessrules;

import uk.gov.dwp.uc.pairtest.domain.BusinessRule;

public class AdultAlongChildAndInfactRule implements BusinessRule {
    private int numberOfAdultTicketBeingPurchase = 0;
    public AdultAlongChildAndInfactRule(int numberOfAdultTicketBeingPurchase){
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
