import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

public class TicketCollection {
    public static TicketTypeRequest[] familyTickets(){
        var adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 2);
        var childTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 2);
        var infantTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        TicketTypeRequest[] ticketRequests = { adultTicketRequest, childTicketRequest, infantTicketRequest };
        return ticketRequests;
    }

    public static TicketTypeRequest[] childAndInfantTickets(){
        var childTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 1);
        var infantTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.INFANT, 1);

        TicketTypeRequest[] ticketRequests = { childTicketRequest, infantTicketRequest };
        return ticketRequests;
    }

    public static TicketTypeRequest[] twoGenerationFamilyTickets(){
        var adultTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.ADULT, 10);
        var childTicketRequest = new TicketTypeRequest(TicketTypeRequest.Type.CHILD, 15);

        TicketTypeRequest[] ticketRequests = { adultTicketRequest, childTicketRequest };
        return ticketRequests;
    }
}
