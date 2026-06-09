package com.mycompany.userregistrationlogin;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Scanner object to read user input from keyboard
        Scanner input = new Scanner(System.in);

        // Create Login object to access validation and login methods
        Login login = new Login();
        System.out.println("\n====================================");
        System.out.println("       WELCOME TO CHAT APP");
        System.out.println("====================================");
        System.out.println();
        
        System.out.println("=====USER REGISTRATION=====");
        System.out.println();
        
       String firstName;
            // While loop for first name
            while (true) {
                System.out.print("Enter first name: ");
                firstName = input.nextLine();

                if (!firstName.isBlank() && firstName.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("First name must contain letters only and cannot be empty!");
                }
            }
        
        String lastName;
            // While loop for last name
            while (true) {
                System.out.print("Enter last name: ");
                lastName = input.nextLine();

                if (!lastName.isBlank() && lastName.matches("[a-zA-Z]+")) {
                    break;
                } else {
                    System.out.println("Last name must contain letters only and cannot be empty!");
                }
            }
        // ================= USERNAME SECTION =================
        String username;

        // Loop until a valid username is entered
        while (true) {
            System.out.print("Enter username(5 Characters long including an '_'): ");
            username = input.nextLine();

            // Call method to check username rules
            if (login.checkUsername(username)) {
                break; // Exit loop if valid
            } else {
                System.out.println("Invalid username! Must contain '_' and be max 5 characters.");
            }
        }

        // ================= PASSWORD SECTION =================
        String password;

        // Loop until valid password is entered
        while (true) {
            System.out.print("Enter password(must be 8+ chars long with Capital letter, number and special symbol): ");
            password = input.nextLine();

            // Check password requirements
            if (login.checkPassword(password)) {
                break; // Exit loop if valid
            } else {
                System.out.println("Invalid password! Must be 8+ chars with capital, number & symbol.");
            }
        }

        // ================= PHONE SECTION =================
        String phone;

        // Loop until valid South African phone number is entered
        while (true) {
            System.out.print("Enter SA phone number(+27XXXXXXXXX): ");
            phone = input.nextLine();

            // Validate phone number format
            if (login.checkPhone(phone)) break;

            System.out.println("Invalid SA phone number! Format: +27821234567\n");
        }

        // ================= REGISTRATION =================
        //Register user
        if (login.register(firstName, lastName, username, password, phone)) {
            System.out.println("\nRegistration successful!\n");
        } else {
            System.out.println("\nRegistration failed!\n");
        }

        // ================= LOGIN SECTION =================
        
        System.out.println("=====LOGIN=====");
        
        boolean isLoggedIn = false;

        
        //This loop will keep running while the user is not logged in
        
        while (!isLoggedIn) {
            System.out.print("Login username: ");
            String loginUser = input.nextLine();

            System.out.print("Login password: ");
            String loginPass = input.nextLine();
            
          

            // Check if login details match stored details
            if (login.loginUser(loginUser, loginPass)) {
            System.out.println(login.returnLoginStatus(loginUser, loginPass));
                isLoggedIn = true;
            } else {
                System.out.println(login.returnLoginStatus(loginUser, loginPass));
            }
        System.out.println(" ");
        // Stores messages that were successfully sent
        ArrayList<Messages> sentMessages = new ArrayList<>();
        // Stores messages saved for later sending
        ArrayList<Messages> storedMessages = new ArrayList<>();
        

        boolean running = true;
        // Menu loop
        while (running) {

            //Welcome message
            System.out.println("=======WELCOME TO CHAT APP=======");
            System.out.println("Stored Messages: " + storedMessages.size());
            System.out.println("====================================");
            System.out.println("1. Send Messages");
            System.out.println("2. Show Recently Sent Messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.println("====================================");

            System.out.print("CHOOSE OPTION : ");

            int choice;
            // ============== INPUT VALIDATION ==============
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input!");
                continue;
            }

            // ================= SWITCH MENU =================
            
            switch (choice) {

                case 1:
                    // ================= SEND MESSAGES =================
                    System.out.print("How many messages do you want to send? ");
                    int totalMessages = Integer.parseInt(input.nextLine());
                    // Loop for multiple message sending
                    for (int i = 1; i <= totalMessages; i++) {

                        
                        System.out.println("==============MESSAGE " + i + " OF " + totalMessages + "==============");
                       
                        
                        Messages tempMsg = new Messages("+27000000000", "temp", i);
                        System.out.println("Message ID: " + tempMsg.getMessageID());
                        System.out.println("==================================");

                        // ================= RECIPIENT INPUT + VALIDATION =================
                        String recipient;

                        while (true) {

                            System.out.print("Enter recipient number (+27XXXXXXXXX): ");
                            recipient = input.nextLine();

                            if (Messages.checkRecipientCell(recipient)) {
                                break;
                            } else {
                                System.out.println("Invalid phone number format ensure it has +27 and is 10 digits!");
                            }
                        }

                        // ================= MESSAGE INPUT + VALIDATION =================
                        String messageText;

                        while (true) {

                            System.out.print("Enter your message (max 250 letters): ");
                            messageText = input.nextLine();

                            if (Messages.checkMessageLength(messageText)) {
                                break;
                            } else {
                                System.out.println("Message is more than 250 letters!");
                            }
                        }

                        // Create message object
                        Messages msg = new Messages(recipient, messageText, i);

                        // ================= MESSAGE MENU =================
                        System.out.println("""

                                Choose Option:
                                1 - Send Message
                                           
                                2 - Discard Message
                                           
                                3 - Store Message To Send Later          
                                """);

                        int option = Integer.parseInt(input.nextLine());

                        // Process message
                       String result = msg.sentMessage(option);
                            System.out.println(result);

                            // Only show details if NOT discarded
                            if (option == 1 || option == 3) {
                                System.out.println(msg.printMessage());
                        }

                        // ================= STORE MESSAGE BASED ON OPTION =================
                        switch (option) {

                            case 1:
                                sentMessages.add(msg);
                                break;

                            case 3:
                                storedMessages.add(msg);
                                break;
                        }

                        // Display details
                        
                    }

                    break;
                // ================= SHOW SENT MESSAGES =================
                case 2:

                    System.out.println("\n======= RECENTLY SENT MESSAGES =======");

                    if (sentMessages.isEmpty()) {

                        System.out.println("Coming soon.....");

                    } else {

                        for (Messages m : sentMessages) {

                            System.out.println(m.printMessage());
                        }
                    }

                    System.out.println("======================================");

                    break;
                // ================= EXIT APPLICATION =================
                case 3:

                    System.out.println("\n====================================");
                    System.out.println("   Thank you for using Chat App!");
                    System.out.println("====================================");

                    running = false;

                    break;
                 
                case 4:

                    boolean inStoredMenu = true;

                    while (inStoredMenu) {

                        System.out.println("\n========== STORED MESSAGES ==========");
                        System.out.println("a. Display all messages");
                        System.out.println("b. Display longest message");
                        System.out.println("c. Search message by ID");
                        System.out.println("d. Search messages by recipient");
                        System.out.println("e. Delete message by hash");
                        System.out.println("f. Display full message report");
                        System.out.println("g. Return to main menu");
                        System.out.print("Choose option: ");

                        String opt = input.nextLine();

                        switch (opt.toLowerCase()) {

                            case "a":
                                if (storedMessages.isEmpty()) {
                                    System.out.println("No stored messages.");
                                } else {
                                    for (Messages m : storedMessages) {
                                        System.out.println(m.printMessage());
                                    }
                                }
                                break;

                            case "b":
                                Messages longest = null;
                                for (Messages m : storedMessages) {
                                    if (longest == null ||
                                        m.getMessageText().length() > longest.getMessageText().length()) {
                                        longest = m;
                                    }
                                }

                                System.out.println(longest != null
                                        ? longest.printMessage()
                                        : "No messages stored.");
                                break;

                            case "c":
                                System.out.print("Enter Message ID: ");
                                String id = input.nextLine();
                                
                                boolean foundById = false;
                                for (Messages m : storedMessages) {
                                    if (m.getMessageID().equals(id)) {
                                        System.out.println(m.printMessage());
                                        foundById = true;
                                    }
                                }
                                
                                if (!foundById) {
                                    System.out.println("No message found with that ID.");
                                }
                                break;

                            case "d":
                                System.out.print("Enter recipient: ");
                                String rec = input.nextLine();
                                
                                boolean foundRec = false;

                                for (Messages m : storedMessages) {
                                    if (m.getRecipient().equals(rec)) {
                                        System.out.println(m.printMessage());
                                        foundRec = true;
                                    }
                                }
                                
                                if(!foundRec){
                                    System.out.println("no messaeg found for recipient");
                                }
                                break;

                            case "e":
                                System.out.print("Enter message hash: ");
                                String hash = input.nextLine();

                                boolean exists = false;

                                for (Messages m : storedMessages) {
                                    if (m.getMessageHash().equals(hash)) {
                                        exists = true;
                                        break;
                                    }
                                }

                                if (!exists) {
                                    System.out.println("No message found with that hash.");
                                } else {
                                    boolean removed = storedMessages.removeIf(m ->
                                            m.getMessageHash().equals(hash));

                                    System.out.println("Message successfully deleted.");
                                }
                                break;

                            case "f":
                                System.out.println("\n===== FULL MESSAGE REPORT =====");

                                for (Messages m : storedMessages) {
                                    System.out.println(m.printMessage());
                                }

                                System.out.println("Total stored: " + storedMessages.size());
                                break;

                            case "g":
                                inStoredMenu = false;
                                break;

                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                default:

                    System.out.println("Invalid option please enter a,b,c,d,e,f,g.!");
            }
        }

        input.close();
    }
 }
}
  
    
