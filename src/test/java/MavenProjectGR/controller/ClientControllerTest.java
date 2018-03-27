package MavenProjectGR.controller;

import MavenProjectGR.repository.DataManager;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class ClientControllerTest {
    @Test
    public void addClient() throws Exception {
        File testClientFile= new File("E:/Files/SSVV/MavenProject/testClient.txt");
        PrintWriter tc = new PrintWriter(testClientFile);
        File testIssueFile = new File("E:/Files/SSVV/MavenProject/testIssue.txt");
        PrintWriter ti = new PrintWriter(testIssueFile);
        DataManager dm = new DataManager("testClient.txt", "testIssue.txt");
        ClientController ctrl = new ClientController(dm);
        assertEquals("Success", ctrl.AddClient("Client", "City, Street, 1","10"));
        assertEquals("Client already exists!",ctrl.AddClient("Client", "City, Street ,1","10"));
        assertEquals("Invalid character: 1", ctrl.AddClient("Client1", "City, Street, 1","12"));
    }

}