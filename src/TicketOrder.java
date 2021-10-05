public class TicketOrder {
    private String ticketID;
    private String ticketQuantity;

    public TicketOrder(String ticketID, String ticketQuantity) {
        this.ticketID = ticketID;
        this.ticketQuantity = ticketQuantity;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(String ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }
}
