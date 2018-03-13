package MavenProjectGR;

import MavenProjectGR.controller.ClientController;
import MavenProjectGR.repository.DataManager;
import MavenProjectGR.ui.ElectricaUI;

public class App {
	public static void main(String[] args) {
		DataManager repo = new DataManager();
		
		ClientController ctrl = new ClientController();
		
		ElectricaUI console = new ElectricaUI(ctrl);
		console.Run();
	}
}
