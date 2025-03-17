package customerTicketManagement;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Objects;
import doublyLinkedList.*;

public class TicketSystem {
	 private static ArrayList<User> users = new ArrayList<>();
	    private static DoublyLinkedList<Ticket> ticketList = new DoublyLinkedList<>();
	    private static User loggedInUser = null;

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        users.add(new User("admin@admin.com", "admin123", "admin"));
	        users.add(new User("customer1@customer.com", "cust123", "customer"));

	        while (true) {
	            displayMainMenu();
	            int choice = getUserChoice();

	            if (loggedInUser == null) {

	                handleLoginChoice(choice, scanner);
	            } else {

	                handleLoggedInChoice(choice, scanner);
	            }
	        }
	    }


	    static void displayMainMenu() {
	        System.out.println("\n****************************");
	        System.out.println("Welcome to the Ticket Management System");
	        System.out.println("****************************");

	        if (loggedInUser == null) {
	            System.out.println("1. Sign In");
	            System.out.println("2. Sign Up");
	            System.out.println("3. Exit");
	        } else {
	            System.out.println("1. View Tickets");
	            if (loggedInUser.getRole().equals("admin")) {
	                System.out.println("2. Manage Tickets");
	            }
	            else {
	            System.out.println("2. Create Ticket");
	            }
	            System.out.println("3. Log Out");
	        }
	        System.out.print("Please choose an option: ");
	    }


	static int getUserChoice() {
	        Scanner scanner = new Scanner(System.in);
	        int choice = -1;

	        while (choice < 1 || choice > 3) {
	            try {
	                choice = Integer.parseInt(scanner.nextLine());
	                if (choice < 1 || choice > 3) {
	                    System.out.print("Invalid choice. Please choose again (1-3): ");
	                }
	            } catch (NumberFormatException e) {
	                System.out.print("Invalid input. Please choose again (1-3): ");
	            }
	        }
	        return choice;
	    }

	 
static void handleLoginChoice(int choice, Scanner scanner) {
	        if (choice == 1) {
	            signIn(scanner);
	        } else if (choice == 2) {
	            signUp(scanner);
	        } else if (choice == 3) {
	            System.out.println("Goodbye!");
	            System.exit(0);
	        } else {
	            System.out.println("Invalid option. Please try again.");
	        }
	    }

	     static void handleLoggedInChoice(int choice, Scanner scanner) {
	        if (choice == 1) {
	            viewTickets();
	        } else if (choice == 2) {
	            if (loggedInUser.getRole().equals("admin")) {
	                manageTickets(scanner);
	            } else {
	                createTicket(scanner);
	            }
	        } else if (choice == 3) {
	            logOut();
	        } else {
	            System.out.println("Invalid option. Please try again.");
	        }
	    }


	    static void signUp(Scanner scanner) {
	        System.out.println("\nSign Up");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();
	        System.out.print("Enter your role (admin/customer): ");
	        String role = scanner.nextLine();

	       
	        for (User user : users) {
	            if (user.getEmail().equals(email)) {
	                System.out.println("This email is already registered. Please try another one.");
	                return;
	            }
	        }

	   
	        users.add(new User(email, password, role));
	        System.out.println("Signup successful! You can now log in.");
	    }


	    private static void signIn(Scanner scanner) {
	        System.out.println("\nSign In");
	        System.out.print("Enter your email: ");
	        String email = scanner.nextLine();
	        System.out.print("Enter your password: ");
	        String password = scanner.nextLine();

	        for (User user : users) {
	            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
	                loggedInUser = user;
	                System.out.println("Login successful!");
	                return;
	            }
	        }
	        System.out.println("Invalid email or password. Please try again.");
	    }

	   
	 static void logOut() {
	        loggedInUser = null;
	        System.out.println("You have logged out successfully.");
	    }

	
	    static void viewTickets() {
	        System.out.println("\nYour Tickets:");
	        if (ticketList.isEmpty()) {
	            System.out.println("No tickets available.");
	        } else {
	            ticketList.print();
	        }
	    }


	  static void createTicket(Scanner scanner) {
	        System.out.print("\nEnter ticket description: ");
	        String description = scanner.nextLine();
	        Ticket newTicket = new Ticket(description, loggedInUser.getEmail());
	        ticketList.add(newTicket);
	        System.out.println("Ticket created successfully!");
	    }

	
	static void manageTickets(Scanner scanner) {
	        System.out.println("\nAdmin - Manage Tickets");
	        ticketList.print();

	        System.out.print("\nEnter ticket ID to update status or 'exit' to return: ");
	        String input = scanner.nextLine();

	        if (input.equalsIgnoreCase("exit")) {
	            return;
	        }

	        try {
	            int ticketID = Integer.parseInt(input);
	            Ticket ticket = findTicket(ticketID);

	            if (ticket != null) {
	                System.out.print("Enter new status (Open/Closed): ");
	                String status = scanner.nextLine();
	                ticket.setStatus(status);
	                System.out.println("Ticket status updated.");
	            } else {
	                System.out.println("Ticket not found.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid input. Please enter a valid ticket ID.");
	        }
	    }


	    private static Ticket findTicket(int ticketID) {
	        Node<Ticket> current = ticketList.headNode;
	        while (current != null) {
	            if (current.data.getTicketID() == ticketID) {
	                return current.data;
	            }
	            current = current.next;
	        }
	        return null;
	    }
	}


