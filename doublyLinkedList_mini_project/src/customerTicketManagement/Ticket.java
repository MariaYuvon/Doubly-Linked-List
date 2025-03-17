package customerTicketManagement;

public class Ticket {
	private static int idCounter = 1;
    private int ticketID;
    private String description;
    private String status;
    private String customerEmail;

    public Ticket(String description, String customerEmail) {
        this.ticketID = idCounter++;
        this.description = description;
        this.status = "Open";
        this.customerEmail = customerEmail;
    }

    public int getTicketID() {
        return ticketID;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketID + "\nDescription: " + description + "\nStatus: " + status + "\nCustomer Email: " + customerEmail;
    }

}
