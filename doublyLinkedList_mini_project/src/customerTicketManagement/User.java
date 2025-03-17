package customerTicketManagement;
import doublyLinkedList.*;

public class User {
	 String email;
	    String password;
	    String role; 

	    public User(String email, String password, String role) {
	        this.email = email;
	        this.password = password;
	        this.role = role;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public String getRole() {
	        return role;
	    }
}
