package uk.gov.dwp.uc.pairtest.businessrules;

import uk.gov.dwp.uc.pairtest.domain.BusinessRule;

public class MaxiumTicketPerPurchaseRule implements BusinessRule {

    private final int maxiumTicketPerPurchase = 20;
    private  int numberOfTicketBeingPurchase;

    public MaxiumTicketPerPurchaseRule(int numberOfTicketBeingPurchase){
        this.numberOfTicketBeingPurchase = numberOfTicketBeingPurchase;
    }
    @Override
    public boolean isBroken() {
        return numberOfTicketBeingPurchase > maxiumTicketPerPurchase;
    }

    @Override
    public String getMessage() {
        return "Only a maximum of 20 tickets that can be purchased at a time";
    }
}
