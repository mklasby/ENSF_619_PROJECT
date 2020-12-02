module TicketReservationApp {
	exports View.Views;
	exports Model.UserModel;
	exports Model.PaymentModel;
	exports Model.TheatreModel;
	exports CommonMessage;
	exports Controller.PaymentController;
	exports Controller;
	exports View;
	exports View.ViewControllers;

	requires java.desktop;
	requires java.json;
	requires java.sql;
}