@startuml


package View{

    package Views{
        class GUI{
            -views: HashMap<String, View>
            -container: JPanel
            -userStatus: JLabel
            +GUI()
            +getContainer(): JPanel
            +getView(key: String): View
            +getUserStatus(): JLabel
            +showView(key: String): void
        }

         abstract class View{
            -titleLabel: JLabel
            -menuButton: JButton
            -submitButton: JButton
            +getPanel(): JPanel
            +registerActionListener(listener: ActionListener, component: JComponent)
            +flashMessage()
            +verifyInput()
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

        class IssueNewsView{
            -uploadButton: JButton
            -newsTextArea: JTextArea
            +IssueNewsView()
            +getNewsTextArea(): JTextArea
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

        class MovieView{
            -movieLabel: JLabel
            -movieTextArea: JTextArea
            -selectionLabel: JLabel
            -selectionField: JTextField

            +MovieView()
            +getSelectionField(): JTextField
            +getTextArea(): JTextArea
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

            class ShowtimeView{
            -showtimeLabel: JLabel
            -showtimeTextArea: JTextArea
            -selectionLabel: JLabel
            -selectionField: JTextField

            +ShowtimeView()
            +getSelectionField(): JTextField
            +getTextArea(): JTextArea
        }

        class SeatView{
            -seatLabel: JLabel
            -seats: HashMap<int[], JButton>

            +SeatView()
            +getSeats(): HashMap<int[], JButton>
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

        class RefundView{
            -receiptLabel: JLabel
            -receiptField: JTextField
            -ticketLabel: JLabel
            -ticketField: JTextField
            

            +RefundView()
            +getReceiptField(): JTextField
            +getTicketField(): JTextField
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
    }
    package ViewControllers{
        class GUIController{
            -gui: GUI
            +GUIController()
            +{static} main(args: String[]): void
            +getGUI(): GUI
            +showView(view: String):void
            +login(userName: String, password: String): JsonObject
            +getMovieList(): JsonObject
            +selectMovie(name: String): JsonObject
            +selectTheatre(theatreId: int): JsonObject
            +selectShowTime(showtimeDate: Date): JsonObject
            +selectSeat(seatRow: int, seatCol: int)
            +checkout(): JsonObject
            +refundTicket(ticketId: number): JsonObject
            +registerUser(name: String, password: String, cardType: String, cardNum: String, email: String) 
        }

        class LoginViewController{
            -guiController: GUIController
            -view: LoginView
            +LoginViewController(view: LoginView, guiController: GUIController)
            +getView(): LoginView
            +actionPerformed(e: ActionEvent): void
        }

        class IssueNewsController{
            -guiController: GUIController
            -view: IssueNewsView
            +IssueNewsController(view: IssueNewsView, guiController: GUIController)
            +getView(): IssueNewsView
            +actionPerformed(e: ActionEvent): void
        }

        class RegisterViewController{
            -view: RegisterView
            -guiController: GUIController
            +IssueNewsController(view: IssueNewsView, guiController: GUIController)
            +getView(): RegisterView      
            +actionPerformed(e: ActionEvent): void
            +verifyInput(): boolean
        }

        class MovieViewController{
            -view: MovieView
            -guiController: GUIController
            +MovieViewController(view: MovieView, guiController: GUIController)
            +paintMovies(movies: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class TheatreViewController {
            -view: TheatreView
            -guiController: GUIController
            +TheatreViewController(view: TheatreView, guiController: GUIController)
            +paintTheatres(theatres: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class ShowtimeViewController{
            -view: ShowtimeView
            -guiController: GUIController
            +ShowtimeViewController(view: ShowtimeView, guiController: GUIController)
            +paintShowtimes(showtimes: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class SeatViewController{
            -seat: SeatView
            -guiController: GUIController
            +SeatViewController(view: SeatView, guiController: GUIController)
            +getView(): SeatView
            +paintSeats(seats: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class PaymentViewController{
            -view: PaymentView
            -guiController: GUIController
            +PaymentViewController(view: PaymentView, guiController: GUIController)
            +getView(): PaymentView
            +flashMessage(message: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class RefundViewController{
            -view: RefundView
            -guiController: GUIController
            +RefundViewController(view: RefundView, guiController: GUIController)
            +getView(): RefundView
            +paintTicket(ticket: JsonObject): void
            +actionPerformed(e: ActionEvent): void
        }

        class MenuViewController{
            -view: MenuViewController
            -guiController: GUIController
            +MenuViewController(view: MenuView)
            +getView(): MenuViewController
            +setRegisteredView(): void
            +setManagerView(): void
            +setBasicView(): void
            +actionPerformed(e: ActionEvent): void
        }
    }
}

package Controller{
    class BossController{
        -cart: Cart
        -userMgmt: UserManager
        -financialController: FinancialController
        -dbController: DatabaseController
        +browseMovie(): JsonObject
        +selectMovie(name: Movie): JsonObject
        +selectTheatre(theatreId: int): JsonObject
        +selectShowTime(date: String): JsonObject
        +selectSeat(seatRow: int, seatCol: int): JsonObject
        +registerUser(name: String, password: String, cardType: String, cardNum: String, email: String) 
        +refundTicket(receiptNum: int): JsonObject
        +checkout(): JsonObject
        +verifyPayment(cardType: char, cardNum: int, cardPin: int): JsonObject
        +payAnnual(userId: int): JsonObject
        +issueMovieNews(news: File): JsonObject
    }

    class FinancialController{
        +FinancialController()
        +processPayment(paymentInfo: JsonObject): boolean 
        +processRefund(paymentInfo: JsonObject): boolean
    }

    class DataBaseController{
        -connection: Connection
        -{static}connectionInfo: String
        -{static}username: String
        -{static}password: String
        +getUser(userId: int): ResultSet
        +getRegisteredUserList(): ResultSet
        +updateRegisteredUserPayment(userId: int, cardNumber: int, cardType: char): boolean
        +registerUser(name: String, password: String, cardType: String, cardNum: String, email: String): void
        +getRegisteredUserEmails():ResultSet
        +getMovieList(): ResultSet
        +getMovie(movieName: String): ResultSet
        +getTheatreList(): ResultSet
        +getTheatre(movieName: String, theatreId: int): ResultSet
        +getShowTimeList(): ResultSet
        +getShowtime(movieName: String, theatreId: int, showtimeDate: date): ResultSet
        -updateShowtimeAvailability(): void
        +getSeatList(): ResultSets
        +getSeat(movieName: String, theatreId: int, showtimeDate: Date, seatNum: int): ResultSet
        +insertTicket(ticket: JsonObject): void
        +getReceipt(receiptId: int): ResultSet
        +returnTicket(receiptId: int): void
    }
}
 
package Model{

    package TheatreModel{
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
            -theatreName: String
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
            -setEarlyAccessSeatsAvailable(status: boolean): void
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
        class User{
            -ticket: Ticket
            -paymentInfo: PaymentInfo
        }
    }

package UserModel{
    class RegisteredUser{
        -name: String
        -address: String 
        -userName: String
        -email: String
        -password: String
    }

    class UnregisteredUser{
        -email: String
    }

    class PaymentInfo{
        -cardNumber: int
        -cardType: String
        -cardPin: int
        
    }

    class PaymentManager{
        -user: User
        +payForTicket
        +refundTicket(): void
        +payAnnualFee(): void
        +sendTickettoUser(): void
        +sendReceipttoUser(): void
    }

    class Refund{
        -ticketAmount: double
        -receipt: RefundReceipt
        -calculateRefund: double
        +processRefund(): void
        +createCoupon(): void
    }

    class Coupon{
        -refundAmount: double
        -expiryDate: int
    }

    class PayTicketFee{
        -amount: double
        -receipt: TicketReciept
    }

    class PayAnnualFee{
        -amount: double
        -receipt: AnnualReceipt
    }
}


}

@enduml


    class FinancialModel{
        +FinancialModel()
        +processPayment(int cardNum, int cardPin, double amount): boolean 
        +processRefund(int cardNum, int cardPin, double amount): boolean
        + addPaymentInfo(paymentInfo:PaymentInfo): void
        + verifyPaymentInfo(paymentInfo:PaymentInfo):boolean
    }