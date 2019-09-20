////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Access Control
// Files:           User.java, AccessControl.java, AccessControlTest.java
// Course:          CS300, Fall 2018
//
// Author:          Stephen Fan
// Email:           sfan54@wisc.edu
// Lecturer's Name: Alexi Brooks
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.ArrayList;
import java.util.Scanner;

public class AccessControl {
	private static ArrayList<User> users; // An ArrayList of valid users.
	private User currentUser; // Who is currently logged in, if anyone?
	private static final String DEFAULT_PASSWORD = "changeme";
	                 // Default password given to
	                 //new users or when we reset a user's password.
	
	// A no-parameter constructor
	// Has a default user-admin with username admin, password root
	// and admin set to true
	// currentUser is set to null
	public AccessControl() {
		users = new ArrayList<User>();
		User user1 = new User("admin", "root", true);
		users.add(user1);
		currentUser = null;
	}
	
	// Reports whether a
	// given username/password pair is a valid login
	public static boolean isValidLogin(String username, String password) {		
		boolean validlogin = false;
		
		for (int i = 0; i < users.size(); i++) {
			System.out.println(users.get(i).getUsername());
			if (users.get(i).getUsername().equals(username)) {
				if (users.get(i).isValidLogin(password) == true) {
					validlogin = true;
				}
			}
		}
		return validlogin;
	}
	
	// Change the current user's password
	public void changePassword(String newPassword) {
		currentUser.setPassword(newPassword);
	}
	
	// Log out the current user
	public void logout() {
		currentUser = null;
	}
	
	// A mutator you can use to write tests
	// without simulating user input
	public void setCurrentUser(String username) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				currentUser = users.get(i);
			}
		}
	}
	
	// Create a new user 
	// With the default password and isAdmin==false
	public boolean addUser(String username) {
		boolean valid = false;
		if (currentUser != null) {
			valid = true;
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						valid = false;
					}
				}
			}
			else {
				valid = false;
			}
			if (valid == true) {
				User newuser = new User(username, DEFAULT_PASSWORD, false);
				users.add(newuser);
			}
		}
		return valid;
	}
	
	// Create a new user 
 	// and specify their admin status
	// Create a new user 
	// With the default password and isAdmin==false
	public boolean addUser(String username, boolean isAdmin) {
		boolean valid = false;
		if (currentUser != null) {
			valid = true;
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						valid = false;
					}
				}
			}
			else {
				valid = false;
			}
			if (valid == true) {
				User newuser = new User(username, DEFAULT_PASSWORD, isAdmin);
				users.add(newuser);
			}
		}
		return valid;
	}
	
	// Remove a user (names should be unique) 
	public boolean removeUser(String username) {
		boolean nameremoved = false;
		
		if (currentUser != null) {
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						users.remove(i);
						nameremoved = true;
					}
				}
			}
		}
		return nameremoved;
	}
	
	// Give a user admin power 
	public boolean giveAdmin(String username) {
		boolean adminexists = false;
		
		if (currentUser != null) {
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						users.get(i).setIsAdmin(true);
						adminexists = true;
					}
				}
			}
		}
		return adminexists;
	}
	
	// Remove a user's admin power 
	public boolean takeAdmin(String username) {
		boolean adminexists = false;
		
		if (currentUser != null) {
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						users.get(i).setIsAdmin(false);
						adminexists = true;
					}
				}
			}
		}
		return adminexists;
	}
	
	// Reset a user's password
	public boolean resetPassword(String username) {
		boolean passreset = false;
		
		if (currentUser != null) {
			if (currentUser.getIsAdmin() == true) {
				for (int i = 0; i < users.size(); i++) {
					if (users.get(i).getUsername().equals(username)) {
						users.get(i).setPassword(DEFAULT_PASSWORD);
						passreset = true;
					}
				}
			}
		}
		return passreset;
	}
	
	// Main driver loop.
	// Prompt the user for login information
	// calls the isValidLogin method
	// If the login is valid, call the sessionScreen method
	public void loginScreen(Scanner userInputScanner) {
		while(true) {
			System.out.println("Enter your username: ");
			String username = new String(userInputScanner.nextLine());
			System.out.println("Enter your password: ");
			String password = new String(userInputScanner.nextLine());
			if (isValidLogin(username, password) == true) {
				sessionScreen(username, userInputScanner);
			}
			else {
				System.out.println("Username and password do not match"
						+ " any user in the system.");
			}
		}
	}
	
	// Set the currentUser 
	// Allows them to changePassword or logout 
	// If they are an admin, gives access to admin methods
	public void sessionScreen(String username, Scanner userInputScanner) {
		//sets currentUser to the one who logged in
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUsername().equals(username)) {
				currentUser = users.get(i);
			}
		}
		
		while (currentUser != null) {
			//prints option menu for user
			System.out.println("Menu \nlogout \nnewpw [newpassword]");
			if (currentUser.getIsAdmin() == true) {
				System.out.println("adduser [username] \nadduser [username]"
						+ "[true or false] \nrmuser [username] \n"
						+ "giveadmin [username] \nrmadmin [username] \n"
						+ "resetpw [username]\n");
			}
			
			String userinput = new String(userInputScanner.nextLine());
			
			//matches the user's command with a method
			if (userinput.contains("logout")) {
				logout();
				return ;
			}
			else if (userinput.contains("newpw")) {
				String[] inputarray = userinput.split(" ");
				changePassword(inputarray[1]);
			}
			else if (currentUser.getIsAdmin() == true) {
				if (userinput.contains("adduser")) {
					String[] inputarray = userinput.split(" ");
					if (inputarray.length == 3) {
						if (inputarray[2].equals("true")) {
							addUser(inputarray[1], true);
						}
						else if (inputarray[2].equals("false")) {
							addUser(inputarray[1], false);
						}
					}
					else if (inputarray.length == 2) {
						addUser(inputarray[1]);
					}
				}
				if (userinput.contains("rmuser")) {
					String[] inputarray = userinput.split(" ");
					removeUser(inputarray[1]);
				}
				if (userinput.contains("giveadmin")) {
					String[] inputarray = userinput.split(" ");
					giveAdmin(inputarray[1]);
				}
				if (userinput.contains("rmadmin")) {
					String[] inputarray = userinput.split(" ");
					takeAdmin(inputarray[1]);
				}
				if (userinput.contains("resetpw")) {
					String[] inputarray = userinput.split(" ");
					resetPassword(inputarray[1]);
				}
			}
		}
	}
	
	/*
	 * Launch an AccessControl instance
	 */
	public static void main(String[] args) {
		AccessControl ac = new AccessControl();
		// If we have any persistent information to lead
		// this is where we load it.
		Scanner userIn = new Scanner(System.in);
		ac.loginScreen(userIn);
	}
}
