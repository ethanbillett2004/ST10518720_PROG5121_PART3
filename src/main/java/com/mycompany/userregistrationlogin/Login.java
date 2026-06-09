package com.mycompany.userregistrationlogin;

public class Login {

    // Variables to store registered user details
     private String storedFirstName;
    private String storedLastName;
    private String storedUsername; //variables can only be accessed inside login class
    private String storedPassword;
    private String storedPhone;

    // ================= USERNAME VALIDATION =================
    public boolean checkUsername(String username) {
        // Username must contain "_" and be 5 characters or less
        return username.contains("_") && username.length() <= 5;
    }

    // ================= PASSWORD VALIDATION =================
    public boolean checkPassword(String password) {

        // If password is null(no data), return false
        if (password == null) return false;

        // Regex pattern explanation:
        // (?=.*[A-Z]) → at least one uppercase letter
        // (?=.*[0-9]) → at least one number
        // (?=.*[@#$%^&+=!]) → at least one special character
        // .{8,} → minimum length of 8 characters
        String pattern = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$";

        return password.matches(pattern);
    }

    // ================= PHONE VALIDATION =================
    public boolean checkPhone(String phone) {

        // Remove any spaces from input
        phone = phone.replaceAll("\\s", "");

        // If number starts with +27 (international format)
        if (phone.startsWith("+27")) {
            String rest = phone.substring(3);

            // Must be 9 digits after +27 and start with 6–8
            return rest.matches("[6-8][0-9]{8}");

        } 
        // If number starts with 0 (local format)
        else if (phone.startsWith("0")) {

            // Must be 10 digits total and start with 06–08
            return phone.matches("0[6-8][0-9]{8}");

        } 
        // Invalid format
        else {
            return false;
        }
    }
    

    // ================= REGISTER USER =================
    public boolean register(String firstName,
                        String lastName,
                        String username,
                        String password,
                        String phone) {

            if (firstName.isBlank() || lastName.isBlank()) {
                return false;
            }

            if (!checkUsername(username)
                    || !checkPassword(password)
                    || !checkPhone(phone)) {
                return false;
            }
            this.storedFirstName = firstName;
            this.storedLastName = lastName;
            this.storedUsername = username;
            this.storedPassword = password;
            this.storedPhone = phone;

            return true;
        }

    // ================= LOGIN VALIDATION =================
    public boolean loginUser(String username, String password) {

        // Check if entered details match stored details
        return username.equals(storedUsername) && password.equals(storedPassword);
    }
    //Login return status
    public String returnLoginStatus(String username, String password) {

    if (loginUser(username, password)) {
        return "Welcome " + storedFirstName + " " + storedLastName +
               ", it is great to see you again.";
    } else {
        return "Username or password incorrect, please try again.";
    }
}
}
