package uk.gov.dwp.uc.pairtest.domain;

public interface BusinessRule {
    boolean isBroken();
    String getMessage();
}
