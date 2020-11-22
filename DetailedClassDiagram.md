@startuml


package View{

    class GUI{
        -views: HashMap<String, View>
        -container: JPanel
        +GUI()
        +getContainer(): JPanel
        +getView(key: String): View 
        +showView(key: String): void
    }

    class GUIController{
        -gui: GUI
        +GUIController()
        +{static} main(args: String[]): void
        +getGUI(): GUI


        +login(userName: String, password: String): JsonObject
        +selectMovie(name: String): JsonObject
        +checkout(): JsonObject
        +refundTicket(ticketId: number): JsonObject
        +registerUser(name: String, password: String, cardType: String, cardNum: String, ) 

    }

    abstract class View{
        -titleLabel: JLabel
        -menuButton: JButton
        -submitButton: JButton
        -userStatus: JLabel
        +getPanel(): JPanel
        +getUserStatus(): JLabel
        +registerActionListener(listener: ActionListener, component: JComponent)
    }

    class LoginView{
        -usernameLabel: JLabel
        -passwordLabel: JLabel
        -usernameField: JTextField
        -passwordField: JTextField

        +LoginView()
        +getUsernameField(): JTextField
        +getPasswordField(): JTextField
    }

    class LoginViewController{
        -guiController: GUIController
        -view: LoginView
        +LoginViewController(view: LoginView, guiController: GUIController)
        +getView(): LoginView
    }

    class IssueNewsView{
        -uploadButton: JButton
        -newsTextArea: JTextArea

        +IssueNewsView()
        +getNewsTextArea(): JTextArea
    }

    class IssueNewsController{
        -guiController: GUIController
        -view: IssueNewsView

        +IssueNewsController(view: IssueNewsView, guiController: GUIController)
        +getView(): IssueNewsView
    }

    class RegisterView{
        -usernameLabel: JLabel
        -passwordLabel: JLabel
        -nameLabel: JLabel
        -addressLabel: JLabel
        -emailLabel: JLabel
        -cardNumLabel: JLabel
        -cardTypeLabel: JLabel
        -usernameField: JTextField
        -passwordField: JTextField
        -nameField: JTextField
        -addressField: JTextField
        -cardNumField: JTextField
        -cardTypeField: JTextField
        -emailField: JTextField

        +RegisterView()
        +getUsernameField(): JTextField
        +getNameField(): JTextField
        +getAddressField(): JTextField
        +getCardNumberField(): JTextField
        +getCardTypeField(): JTextField
        +getEmailField(): JTextField
    }

    class RegisterViewController{
        -view: RegisterView
        -guiController: GUIController

        +IssueNewsController(view: IssueNewsView, guiController: GUIController)
        +getView(): RegisterView      
    }

    class MovieView{
        -movieLabel: JLabel
        -movieTextArea: JTextArea
        -selectionLabel: JLabel
        -selectionField: JTextField

        +MovieView()
        +getSelectionField(): JTextField
        +getTextArea(): JTextArea
    }

    class MovieViewController{
        -view: MovieView
        -guiController: GUIController
        
        +MovieViewController(view: MovieView, guiController: GUIController)
        +paintMovies(movies: JsonObject): void
    }

    class TheatreView{
        -theatreLabel: JLabel
        -theatreTextArea: JTextArea
        -selectionLabel: JLabel
        -selectionField: JTextField

        +TheatreView()
        +getSelectionField(): JTextField
        +getTextArea(): JTextArea
    }

    class TheatreViewController {
        -view: TheatreView
        -guiController: GUIController

        +TheatreViewController(view: TheatreView, guiController: GUIController)
        +paintTheatres(theatres: JsonObject): void
    }

    class ShowtimeView{
        -showtimeLabel: JLabel
        -showtimeTextArea: JTextArea
        -selectionLabel: JLabel
        -selectionField: JTextField

        +ShowtimeView()
        +getSelectionField(): JTextField
        +getTextArea(): JTextArea
    }

    class ShowtimeViewController{
        -view: ShowtimeView
        -guiController: GUIController

        +ShowtimeViewController(view: ShowtimeView, guiController: GUIController)
        +paintShowtimes(showtimes: JsonObject): void
    }

    class SeatView{
        -seatLabel: JLabel
        -seats: HashMap<int[], JButton>

        +SeatView()
        +getSeats(): HashMap<int[], JButton>
    }

    class SeatViewController{
        -seat: SeatView
        -guiController: GUIController
        
        +SeatViewController(view: SeatView, guiController: GUIController)
        +getView(): SeatView
        +paintSeats(seats: JsonObject): void
    }

    class PaymentView{
        -cardNumLabel: JLabel
        -cardTypeLabel: JLabel
        -cardNumField: JTextField
        -cardTypeField: JTextField

        +PaymentView()
        +getCardNumberField(): JTextField
        +getCardTypeField(): JTextField

    }

    class PaymentViewController{
        -view: PaymentView
        -guiController: GUIController

        +PaymentViewController(view: PaymentView, guiController: GUIController)
        +getView(): PaymentView
        +flashMessage(message: JsonObject): void

    }

    class RefundView{
        -receiptLabel: JLabel
        -receiptField: JTextField
        -ticketLabel: JLabel
        -ticketField: JTextField
        

        +RefundView()
        +getReceiptField(): JTextField
        +getTicketField(): JTextField
    }

    class RefundViewController{
        -view: RefundView
        -guiController: GUIController

        +RefundViewController(view: RefundView, guiController: GUIController)
        +getView(): RefundView
        +paintTicket(ticket: JsonObject): void
    }

    class MenuView {
        -loginLabel: JLabel
        -selectMovieLabel: JLabel
        -checkoutLabel: JLabel
        -registerUserLabel: JLabel
        -refundLabel: JLabel
        -payAnnualLabel: JLabel
        -issueNewsLabel: JLabel
        -logoutLabel: JLabel
        -loginButton:JButton
        -selectMovieButton:JButton
        -checkoutLabel:JButton
        -registerUserButton:JButton
        -refundButton:JButton
        -payAnnualButton:JButton
        -issueNewsButton: JButton
        -logoutButton: JButton

        +MenuView()
        +setRegisteredView(): void
        +setManagerView(): void
    }

    class MenuViewController{
        -view: MenuViewController
        -guiController: GUIController
        
        +MenuViewController(view: MenuView)
        +getView(): MenuViewController
        +setRegisteredView(): void
        +setManagerView(): void
        +setBasicView(): void
    }





}

package Controller{

    class FinancialController{
        +FinancialController()
        +processPayment(paymentInfo: JsonObject): boolean 
        +processRefund(paymentInfo: JsonObject): boolean
    }

    class FinancialModel{
        +FinancialModel()
        +processPayment(int cardNum, int cardPin, double amount): boolean 
        +processRefund(int cardNum, int cardPin, double amount): boolean
        + addPaymentInfo(paymentInfo:PaymentInfo): void
        + verifyPaymentInfo(paymentInfo:PaymentInfo):boolean
    }

    class DataBaseController{

        -connection: Connection
        -{static}connectionInfo: String
        -{static}username: String
        -{static}password: String

        +alterMovie(seat:Seat):void
        +getMovieList():ResultSet

        +alterTheatre(theatre:Theatre):void
        +getTheatreList():ResultSet

        +alterShowTime(showTime:ShowTime):void
        +getShowTimeList():ResultSet

        +alterSeat(seat:Seat):void
        +getSeatList():ResultSet

        +insertRegisteredUser(registeredUser:RegisteredUser):void
        +alterRegisteredUser(registeredUser:RegisteredUser):void
        +searchRegisteredUser(registeredUser:RegisteredUser):ResultSet
        +getRegisteredEmails():ResultSet

        +insertTicket(ticket:Ticket):void
        +deleteTicket(ticket:Ticket):void
        +searchTicket(registeredUser:RegisteredUser):ResultSet
    }
}

package Model{

class IssueNewsController{
-news:News
-isManager:Boolean
-issueNewsController(movieName:String, isManager:boolean)
-issueNews():Movie
}

class News{
+News(movieName:String)

}


class BuildTicket{
	-ticket:Ticket
	+BuildTicket():void
	+getTicket():JsonObject

}

class Ticket{
	-movie:Movie
	-theatre:Theatre
	-showTime:ShowTime
	-seat:Seat
	+Ticket():void
	+getMovie():Movie
	+getTheatre():Theatre
	+getShowTime():ShowTime
	+getSeat():Seat
	+setMovie(movie:Movie)
	+setTheatre(theatre:Theatre)
	+setShowTime(time:Date)
	+setSeat(seat:Seat)
	

}


class BrowseMovie{
	-movieList: ArrayList<Movie>
	-isRegUser: boolean 
	+BrowseMovie(sqlSet: ResultSet, isRegUser: boolean)
	-parseMovieList(sqlSet: ResultSet)
	+getMovieList():JsonObject
	+selectMovie(sqlMovie:ResultSet): Movie
}


class SelectTheatre{
	-theatreList: ArrayList<Theatre>
	+SelectTheatre(sqlSet: ResultSet)
	-parseTheatreList(sqlSet: ResultSet)
	+getTheatreList():JsonObject
	+selectTheatre(sqlTheatre:ResultSet): Theatre
}

class SelectShowTime{
	-showTimeList: ArrayList<ShowTime>
	+SelectShowTime(sqlSet: ResultSet)
	-parseShowTimeList(sqlSet: ResultSet):void
	+getShowTimeList():JsonObject
	+selectShowTime(sqlShowTime:ResultSet): ShowTime
}


class SelectSeat{
	-showSeatList: ArrayList<Seat>
	+SelectSeat(sqlSet: ResultSet)
	-parseSeatList(sqlSet: ResultSet):void
	+getSeatList():JsonObject
	+selectSeat(sqlSeat:ResultSet): Seat
}




    
class Movie{
	-movieName: String
	-moviePrice: double 
	-earlyAccess: boolean
	-theatreList: ArrayList<Theatre>
	+getMovieName():String
	+getMoviePrice():int
	+getEarlyAccess(): boolean 
	-setMovieName(name:String):void
	-setMoviePrice(price:double):void
	-setEarlyAccess(status:boolean):void
	
	
}

class Theatre{
	-threatreName: String
	+Theatre(theatreResultSet:ResultSet)
	+getTheatreName():String
	-setTheatreName(name:String):void
}

class ShowTime{
	-time: Date
	-earlyAccessMovie: boolean
	-earlyAccessSeatsAvailable:boolean
	-isFull: boolean
	+ShowTime(showTimeResultSeat)
	+getTime():Date
	+getEarlyAccessMovie():boolean
	+getEarlyAccessSeatsAvailable():boolean
	+getIsFull():boolean
	-setTime(tune: Date):void
	-setEarlyAccessMovie(status: boolean ):void
	-setEarlyAccessSeatsAvaliable(status: boolean): void
	-setIsFull(status: boolean): void

}


class Seat{
	-seatStatus: boolean
	-seatRow: int
	-seatCol: int
	-Seat(seatResultSet: ResultSet)
	+getSeatRow():int
	+getSeatCol():int
	+getSeatStatus(): boolean
	-setSeatRow(row: int):void
	-setSeatCol(col: int ):void
	-setSeatStatus(status: boolean): void

   
}

}

@enduml