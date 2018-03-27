package MavenProjectGR.controller;

import MavenProjectGR.repository.DataManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class ClientControllerTest {
    private PrintWriter tc, ti;
    private ClientController ctrl;
    @Before
    public void setUp(){
        File testClientFile= new File("E:/Files/SSVV/MavenProject/testClient.txt");
        try {
            tc = new PrintWriter(testClientFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        File testIssueFile = new File("E:/Files/SSVV/MavenProject/testIssue.txt");
        try {
            ti = new PrintWriter(testIssueFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        DataManager dm = new DataManager("testClient.txt", "testIssue.txt");
        ctrl = new ClientController(dm);
    }
    @Test
    public void addClient() throws Exception {
        assertEquals("Success", ctrl.AddClient("Client", "City, Street, 1","1"));
        assertEquals("Client already exists!",ctrl.AddClient("Client", "City, Street ,1","1"));
        assertEquals("Invalid character: 1", ctrl.AddClient("Client1", "City, Street, 1","2"));
    }

}