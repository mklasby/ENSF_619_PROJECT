package View;

import View.Views.*;
import View.ViewControllers.*;
import Controller.*;

public class App {
    public static void main(String[] args) {
        Gui gui = new Gui();
        GuiController guiController = new GuiController();
        MenuView menu = new MenuView(gui, "menuPanel");
        MenuViewController menuController = new MenuViewController(menu, guiController);

        LoginView loginView = new LoginView(gui, "loginPanel");
        LoginViewController loginViewController = new LoginViewController(loginView, guiController);

        MovieView movieView = new MovieView(gui, "moviePanel");
        MovieViewController movieViewController = new MovieViewController(movieView, guiController);

        // MenuView menu = new MenuView();
        // MenuController menuController = new MenuController(gui, "menuPanel");

        // MenuView menu = new MenuView();
        // MenuController menuController = new MenuController(gui, "menuPanel");

        // MenuView menu = new MenuView();
        // MenuController menuController = new MenuController(gui, "menuPanel");

        // CustomerView custView = new CustomerView(gui, "customerPanel");
        // ClientController clientCtrl = new ClientController("localhost", 4444);
        // CustomerController custCtrl = new CustomerController(custView, clientCtrl);
        // InventoryView invView = new InventoryView(gui, "inventoryPanel");
        // InventoryController invCtrl = new InventoryController(invView, clientCtrl);
        gui.display();

    }
}
