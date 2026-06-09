# Chat Application Part 3

## Student Information
- Name: Ethan Gareth Billett  
- Student Number: ST10518720  
- Module: PROG5121  

---

## Project Information
- I have created a program using 4 classes including:
  - Main.java  
  - Login.java  
  - Messages.java  
  - MessagesTest.java  
  - LoginTest.java  

---

## Features

### Username Validation
- Must contain an underscore (_)
- Maximum of 5 characters

---

### Password Validation
- Minimum 8 characters
- Must include:
  - At least one uppercase letter
  - At least one number
  - At least one special character

---

### Cellphone Validation
- South African Phone Validation
- Accepts:
  - Local format: 0XXXXXXXXX
  - International format: +27XXXXXXXXX
- Must follow valid SA mobile number patterns

---

### User Registration
- Stores validated first name, last name, username, password, and phone number
- Validates all inputs before registration

---

### Login System
- Verifies entered credentials against stored user data
- Displays login status using returnLoginStatus() method
- Shows welcome message on successful login

---

## Messaging System

### Message Creation
- Each message includes:
  - Auto-generated Message ID
  - Recipient number
  - Message text (maximum 250 characters)
  - Message number (passed from loop index)
  - Message hash

---

### Message Hash Format
Example:


- Removes punctuation from first and last words
- No extra separators between words

---

### Message Actions
- Send message
- Discard message
- Store message for later

---

## Stored Messages System (NEW FEATURE)

A submenu system (Option 4) allows full message management:

### a. Display All Messages
- Shows all stored messages

### b. Display Longest Message
- Finds and displays the longest message

### c. Search Message by ID
- Finds message using Message ID
- Shows error if not found

### d. Search Messages by Recipient
- Displays all messages sent to a specific recipient

### e. Delete Message by Hash
- Deletes message using message hash
- Confirms success or failure

### f. Full Message Report
- Displays all stored messages
- Shows total number of stored messages

### g. Return to Main Menu
- Exits submenu and returns to main application

---

## System Enhancements
- Displays number of stored messages above main menu
- Message entry shows:
  - “Message X of Y”
  - Message ID before recipient input
- Improves user experience and clarity

---

## Unit Testing (JUnit)
- Username validation tests
- Password validation tests
- Phone number validation tests
- Message creation tests
- Message length validation (character-based)
- Message sending, storing, and discarding tests
- Invalid input handling tests

---

## GitHub Integration (CI/CD)
- GitHub Actions `.yml` file included
- Automatically builds project on push or pull request
- Runs all JUnit tests automatically
- Ensures code stability and correctness

---

## Technologies Used
- Java
- NetBeans IDE
- JUnit Testing
- Git & GitHub
- GitHub Actions (YML CI Pipeline)
