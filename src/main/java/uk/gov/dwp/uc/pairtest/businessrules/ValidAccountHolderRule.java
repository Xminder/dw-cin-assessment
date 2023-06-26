package uk.gov.dwp.uc.pairtest.businessrules;

import uk.gov.dwp.uc.pairtest.domain.BusinessRule;

public class ValidAccountHolderRule implements BusinessRule {

    private Long accountId = 0L;

    public ValidAccountHolderRule(Long accountId){
        this.accountId = accountId;
    }
    @Override
    public boolean isBroken() {
        return accountId <= 0;
    }

    @Override
    public String getMessage() {
        return "Invalid account holder or account holder without sufficient fund";
    }
}
