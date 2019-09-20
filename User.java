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
public class User {

	private final String USERNAME; // The user's name
	private String password; // The user's password
	private boolean isAdmin; // Whether or not the user has Admin powers
 
	// Creates a new user with the given password and admin status
	public User(String username, String password, boolean isAdmin) {
		this.USERNAME = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	// Reports whether the password is correct
	public boolean isValidLogin(String password) {
		boolean correctpass = false;
		if (password.equals(this.password)) {
			correctpass = true;
		}
		return correctpass;
	}
	
	// Returns the user's name
	public String getUsername() {
		return this.USERNAME;
	}
	
	// Reports whether the user is an admin
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	// Set the new password
	public void setPassword(String password) {
		this.password = password;
	}
	
	// Set the new admin status
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
