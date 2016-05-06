package tiy.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Dominique on 5/4/2016.
 */
public class ChatClientTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSendMessage() throws Exception {
        ChatClient chatClient = new ChatClient();
        String messageForServer = "test message to the server";
        String expectedResponse = "echo::" + messageForServer;
        String actualResponse = chatClient.sendMessage(messageForServer);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSendHistoryCommand() throws Exception {
        ChatClient chatClient = new ChatClient();
        String commandForServer = "history";
        ArrayList<String> responseArray = chatClient.sendCommand(commandForServer);

        assertNotNull(responseArray);
        assertNotEquals(responseArray.size(), 0);
        assertNotEquals(responseArray.size(), 1);

        // For convenience, display the items in the history here ... 
        for (String item : responseArray) {
            System.out.println(item);
        }

    }
}