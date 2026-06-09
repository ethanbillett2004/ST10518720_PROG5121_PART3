/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.userregistrationlogin;

/**
 *
 * @author ethan
 */
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Messages {

    // Message attributes
    private String messageID;
    private String recipient;
    private String messageText;
    private String messageHash;
    private String status;
    private int messageNumber;

    // Constructor
    public Messages(String recipient, String messageText, int messageNumber)
    {
    this.messageID = generateMessageID();
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageNumber = messageNumber;
    this.messageHash = createMessageHash();
    }

    // ================= GENERATE 10 DIGIT MESSAGE ID =================
    private String generateMessageID() {

        Random random = new Random();

        long number = 1000000000L +
                ((long)(random.nextDouble() * 9000000000L));

        return String.valueOf(number);
    }

    // ================= VALIDATE PHONE NUMBER =================
    public static boolean checkRecipientCell(String phoneNumber) {

        // Must start with +27 and contain exactly 9 digits after
        return phoneNumber.matches("^\\+27\\d{9}$");
    }

    // ================= CHECK MESSAGE LENGTH =================
    public static boolean checkMessageLength(String message) {

        // Split message into words
        return message.length() <= 250;
    }

    // ================= CREATE MESSAGE HASH =================
    private String createMessageHash()
            {
                String idPrefix = messageID.substring(0, 2);

                String[] words = messageText.trim().split("\\s+");

                String firstWord =
                        words[0]
                        .replaceAll("[^a-zA-Z0-9]", "")
                        .toUpperCase();

                String lastWord =
                        words[words.length - 1]
                        .replaceAll("[^a-zA-Z0-9]", "")
                        .toUpperCase();

                return idPrefix + ":" +
                       messageNumber + ":" +
                       firstWord + lastWord;
            }
    public void storeMessageToJSON() {

    try {

        FileWriter writer =
                new FileWriter("storedMessages.json", true);

        writer.write("{\n");
        writer.write("\"messageID\": \"" + messageID + "\",\n");
        writer.write("\"recipient\": \"" + recipient + "\",\n");
        writer.write("\"message\": \"" + messageText + "\",\n");
        writer.write("\"messageHash\": \"" + messageHash + "\",\n");
        writer.write("\"status\": \"Stored\"\n");
        writer.write("}\n\n");

        writer.close();

        System.out.println("Message stored in JSON file.");

    } catch (IOException e) {

        System.out.println("Error saving message.");
    }
}

    // ================= SENT MESSAGE =================
        
    public String sentMessage(int option) {

            switch (option) {

                case 1:

                    status = "Message Sent";

                    return "Message successfully sent.";

                case 2:

                    status = "Message Discarded";

                    return "Message discarded.";

                case 3:

                    status = "Message Stored";

                    storeMessageToJSON();

                    return "Message stored for later.";

                default:

                    return "Invalid option.";
                }
            }

            // ================= DISPLAY MESSAGE DETAILS =================
            public String printMessage() {

                return """
                        ===================================
                        MESSAGE DETAILS
                        ===================================
                        Message ID   : %s
                        Message Hash : %s
                        Recipient    : %s
                        Message      : %s
                        Status       : %s
                        ===================================
                        """.formatted(
                        messageID,
                        messageHash,
                        recipient,
                        messageText,
                        status
                );
    }

    // ================= GETTERS =================
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getStatus() {
        return status;
    }
}
