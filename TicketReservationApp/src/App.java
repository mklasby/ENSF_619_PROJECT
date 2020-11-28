import View.Views.*;
import View.ViewControllers.*;
import Controller.*;
import Model.*;

public class App {
    public static void main(String[] args) {
        Gui gui = new Gui();
        CustomerView custView = new CustomerView(gui, "customerPanel");
        ClientController clientCtrl = new ClientController("localhost", 4444);
        CustomerController custCtrl = new CustomerController(custView, clientCtrl);
        InventoryView invView = new InventoryView(gui, "inventoryPanel");
        InventoryController invCtrl = new InventoryController(invView, clientCtrl);
        gui.display();

    }
}
