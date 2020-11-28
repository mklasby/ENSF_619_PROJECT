module TicketReservationApp {
	exports View.Views;
	exports Model.UserModel;
	exports Model.TheatreModel;
	exports CommonMessage;
	exports Controller.PaymentController;
	exports Controller;
	exports View.ViewControllers;
	exports Model.PaymentModel;

	requires java.desktop;
	requires java.json;
	requires java.sql;
}