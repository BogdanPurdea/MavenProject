package MavenProjectGR.controller;

import MavenProjectGR.model.Client;
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
    private Client test, notAdded, noName, noAddress, invalidName;
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

        test = new Client("Test", "City, Street, 1","2");
        ctrl.AddClient("Test", "City, Street, 1","2");
        notAdded = new Client("notAddedTest", "City, Street, 1","3");
        noName = new Client("", "City, Street, 1","4");
        noAddress = new Client("noAddress", "","5");
        invalidName = new Client("!", "City, Street, 1","6");
    }
    @Test
    public void addClient() throws Exception {
        assertEquals("Success", ctrl.AddClient("Client", "City, Street, 1","1"));
        assertEquals("Client already exists!",ctrl.AddClient("Client", "City, Street ,1","1"));
        assertEquals("Invalid character: 1", ctrl.AddClient("Client1", "City, Street, 1","2"));
    }
    @Test
    public void testAddClientIndex(){
        assertEquals("Success", ctrl.AddClientIndex(test, 2018, 12, 20));
    }
    @Test
    public void testAddClientIndexIfYearInvalid(){
        assertEquals("Year can't be 0 or less!", ctrl.AddClientIndex(test, 0, 12, 20));
    }
    @Test
    public void testAddClientIndexIfMonthInvalid(){
        assertEquals("Month can't be 0 or less!", ctrl.AddClientIndex(test, 2018, 0, 20));
    }
    @Test
    public void testAddClientIndexIfToPayInvalid(){
        assertEquals("Money to pay can't be less than 0!", ctrl.AddClientIndex(test, 2018, 12, -1));
    }
    @Test
    public void testAddClientIndexIfClientNonexistent(){
        assertEquals("Client does not exist!", ctrl.AddClientIndex(notAdded, 2018, 12, 0));
    }
    @Test
    public void testAddClientIndexIfIndexAlreadyExistent(){
        ctrl.AddClientIndex(test, 2018, 12, 0);
        assertEquals("Monthly index already exists!", ctrl.AddClientIndex(test, 2018, 12, 0));
    }
    @Test
    public void testAddClientIndexIfClientNameOrAddressIsEmpty(){
        assertEquals("Name or address cannot be empty!", ctrl.AddClientIndex(noName, 2018, 12, 0));
        assertEquals("Name or address cannot be empty!", ctrl.AddClientIndex(noAddress, 2018, 12, 0));
    }
    @Test
    public void testAddClientIndexIfClientNameInvalid(){
        assertEquals("Invalid character: !", ctrl.AddClientIndex(invalidName, 2018, 12, 0));
    }
}