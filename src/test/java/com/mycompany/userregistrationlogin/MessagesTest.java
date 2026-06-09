package com.mycompany.userregistrationlogin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest {

    // ================= PHONE VALIDATION =================
    @Test
    public void testCheckRecipientCell_Valid() {
        assertTrue(Messages.checkRecipientCell("+27838968976"));
    }

    // ================= MESSAGE LENGTH =================
    @Test
    public void testCheckMessageLength_ValidChars() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 250; i++) {
            sb.append("A");
        }

        assertTrue(Messages.checkMessageLength(sb.toString()));
    }

    @Test
    public void testCheckMessageLength_Invalid() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 260; i++) {
            sb.append("A");
        }

        assertFalse(Messages.checkMessageLength(sb.toString()));
    }

    // ================= MESSAGE CREATION =================
    @Test
    public void testMessageCreation() {

        Messages msg = new Messages("+27821234567", "Hello world", 1);

        assertNotNull(msg.getMessageID());
        assertNotNull(msg.getMessageHash());
        assertEquals("+27821234567", msg.getRecipient());
        assertEquals("Hello world", msg.getMessageText());
    }

    // ================= SENT MESSAGE =================
    @Test
    public void testSentMessage_Send() {

        Messages msg = new Messages("+27821234567", "Hello", 1);

        String result = msg.sentMessage(1);

        assertEquals("Message successfully sent.", result);
    }

    @Test
    public void testSentMessage_Discard() {

        Messages msg = new Messages("+27821234567", "Hello", 2);

        String result = msg.sentMessage(2);

        assertEquals("Message discarded.", result);
    }

    @Test
    public void testSentMessage_Store() {

        Messages msg = new Messages("+27821234567", "Hello", 3);

        String result = msg.sentMessage(3);

        assertEquals("Message stored for later.", result);
    }

    // ================= INVALID OPTION =================
    @Test
    public void testSentMessage_Invalid() {

        Messages msg = new Messages("+27821234567", "Hello", 1);

        String result = msg.sentMessage(99);

        assertEquals("Invalid option.", result);
    }
}