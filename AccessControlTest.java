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

public class AccessControlTest {
	/*
	 * This test tries to log in a user that doesn't exist
	 * @return boolean test passed
	 */
	public static boolean testLogin1() {
	  // It doesn't look like we use ac, but we need users initialized
	  AccessControl ac = new AccessControl();
	  String user = "probablyNotInTheSystem1234";
	  String pw = "password";
	  // isValidLogin should return false
	  return !AccessControl.isValidLogin(user, pw);
	}
	
	/*
	 * Create a new AccessControl and do not log in an admin.
	 * Verify that addUser(String username) returns false
	 * and that the new user is not added.
	 * @return boolean test passed
	 */
	public static boolean testAddUser1() {
	  AccessControl acc = new AccessControl();
	  String user = "alexi";
	  boolean addUserReport = acc.addUser(user);
	  if (addUserReport) {
	    return false; // addUserReport should be false
	  // Make sure user wasn't added anyway
	  }
	  return !AccessControl.isValidLogin(user, "changeme");
	}
	
	/*
	 * Create a new AccessControl and log in an admin
	 * Then attempt to add a new admin user
	 * If the new admin user has his admin status successfully
	 * taken away, the test passes
	 * @return boolean test passed
	 */
	public static boolean testTakeAdmin() {
	  AccessControl acc = new AccessControl();
	  acc.setCurrentUser("admin");
	  acc.addUser("Stephen", true);
	  boolean success = acc.takeAdmin("Stephen");
	  return success;
	}
	
	/*
	 * Create a new AccessControl and log in an admin
	 * Then attempt to change the password
	 * If the password is successfully changed, the test passes
	 * @return boolean test passed
	 */
	public static boolean testResetPassword() {
	  AccessControl acc = new AccessControl();
	  acc.setCurrentUser("admin");
	  acc.changePassword("newpassword");
	  boolean success = acc.resetPassword("admin");
	  return success;
	}
	
	/*
	 * Create a new AccessControl and log in an admin
	 * Then attempt to add and then remove a user
	 * If the user is successfully added and removed, the test passes
	 * @return boolean test passed
	 */
	public static boolean testRemoveUser() {
		  AccessControl acc = new AccessControl();
		  acc.setCurrentUser("admin");
		  acc.addUser("Stephen", true);
		  boolean success = !acc.takeAdmin("Stephen");
		  success = acc.removeUser("Stephen");
		  return success;
		}
	
	/*
	 * Testing main. Runs each test and prints which (if any) failed.
	 */
	public static void main(String[] args) {
	  int fails = 0;
	  if (!testLogin1()) {
	    System.out.println("testLogin [bad username] failed");
	    fails++;
	  }
	  if (!testAddUser1()) {
		    System.out.println("testAddUser [bad username] failed");
		    fails++;
		  }
	  if (!testTakeAdmin()) {
		    System.out.println("testTakeAdmin [bad username] failed");
		    fails++;
		  }
	  if (!testResetPassword()) {
		    System.out.println("testResetPassword [bad username] failed");
		    fails++;
		  }
	  if (!testRemoveUser()) {
		    System.out.println("testRemoveUser [bad username] failed");
		    fails++;
		  }
	  if (fails == 0)
	    System.out.println("All tests passed!");
	}
}
