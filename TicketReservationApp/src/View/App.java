package View;

import View.Views.*;
import View.ViewControllers.*;
import Controller.*;
import Model.PaymentModel.*;
import Model.UserModel.*;

public class App {
        public static void main(String[] args) {
                DatabaseController databaseController = new DatabaseController();
                FinancialController financialController = new FinancialController();
                Cart cart = new Cart();
                UserManager userManager = new UserManager();
                BossController boss = new BossController(databaseController, financialController, cart, userManager);
                Gui gui = new Gui();
                GuiController guiController = new GuiController(boss);

                PaymentView paymentView = new PaymentView(gui, "paymentPanel");
                PaymentViewController paymentViewController = new PaymentViewController(paymentView, guiController);

                MenuView menu = new MenuView(gui, "menuPanel");
                MenuViewController menuController = new MenuViewController(menu, paymentViewController, guiController);

                LoginView loginView = new LoginView(gui, "loginPanel");
                LoginViewController loginViewController = new LoginViewController(loginView, menuController,
                                guiController);

                SeatView seatView = new SeatView(gui, "seatPanel");
                SeatViewController seatViewController = new SeatViewController(seatView, guiController);
                seatView.display("seatPanel");

                ShowTimeView showTimeView = new ShowTimeView(gui, "showTimePanel");
                ShowTimeViewController showTimeViewController = new ShowTimeViewController(showTimeView,
                                seatViewController, guiController);

                TheatreView theatreView = new TheatreView(gui, "theatrePanel");
                TheatreViewController theatreViewController = new TheatreViewController(theatreView,
                                showTimeViewController, guiController);

                MovieView movieView = new MovieView(gui, "moviePanel");
                MovieViewController movieViewController = new MovieViewController(movieView, theatreViewController,
                                guiController);

                IssueNewsView issueNewsView = new IssueNewsView(gui, "issueMovieNewsPanel");
                IssueNewsViewController issueNewsViewController = new IssueNewsViewController(issueNewsView,
                                guiController);

                RefundView refundView = new RefundView(gui, "refundPanel");
                RefundViewController refundViewController = new RefundViewController(refundView, guiController);

                RegisterUserView registerUserView = new RegisterUserView(gui, "registerUserPanel");
                RegisterUserViewController registerUserViewController = new RegisterUserViewController(registerUserView,
                                guiController);

                paymentView.display("paymentPanel");
                gui.display();

                // MenuView menu = new MenuView();
                // MenuController menuController = new MenuController(gui, "menuPanel");

                // MenuView menu = new MenuView();
                // MenuController menuController = new MenuController(gui, "menuPanel");

                // CustomerView custView = new CustomerView(gui, "customerPanel");
                // ClientController clientCtrl = new ClientController("localhost", 4444);
                // CustomerController custCtrl = new CustomerController(custView, clientCtrl);
                // InventoryView invView = new InventoryView(gui, "inventoryPanel");
                // InventoryController invCtrl = new InventoryController(invView, clientCtrl);

        }
}
