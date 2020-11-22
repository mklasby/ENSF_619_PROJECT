@startuml


package View{

    class GUI{
        -views: HashMap<String, View>
        -issueNewsView: IssueNewsView
        -registerView: RegisterView
        -loginView: LoginView
        -movieView: MovieView
        -theatreView: TheatreView
        -showtimeView: ShowtimeView
        -seatView: SeatView
        -paymentView: PaymentView
        -refundView: RefundView
        -mainView: MainView
        -container: JPanel

        +GUI()
        +getContainer(): JPanel
        +getView(key: String): View 
    }

    class GUIController{
        -gui: GUI
        +GUIController()
        +{static} main(args: String[]): void
        +getGUI(): GUI


        +login(userName: String, password: String): JsonObject
        +selectMovie(name: String): JsonObject
        +checkout(): JsonObject
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
        -view: LoginView
        +LoginViewController(view: LoginView)
        +getView(): LoginView
    }

    class IssueNewsView{
        -uploadButton: JButton
        -emailButton: JButton
        -newsTextArea: JTextArea

        +IssueNewsView()
        +getNewsTextArea(): JTextArea
    }

    class IssueNewsController{
        -view: IssueNewsView

        +IssueNewsController(view: IssueNewsView)
        +getView(): IssueNewsView
    }

    class RegisterView{
        -usernameLabel: JLabel
        -passwordLabel: JLabel
        -nameLabel: JLabel
        -addressLabel: JLabel
        -cardNumLabel: JLabel
        -cardTypeLabel: JLabel
        -usernameField: JTextField
        -passwordField: JTextField
        -nameField: JTextField
        -addressField: JTextField
        -cardNumField: JTextField
        -cardTypeField: JTextField

        +RegisterView()
        +getUsernameField(): JTextField
        +getNameField(): JTextField
        +getAddressField(): JTextField
        +getCardNumberField(): JTextField
        +getCardTypeField(): JTextField
    }

    class RegisterViewController{
        -view: RegisterView

        +IssueNewsController(view: IssueNewsView)
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
        
        +MovieViewController(view: MovieView)
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

        +TheatreViewController(view: TheatreView)
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

        +ShowtimeViewController(view: ShowtimeView)
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
        
        +SeatViewController(view: SeatView)
        +getView(): SeatView
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

        +PaymentViewController(view: PaymentView)
        +getView(): PaymentView
    }

    class RefundView{
        -receiptLabel: JLabel
        -receiptField: JTextField

        +RefundView()
        +getReceiptField(): JTextField
    }

    class RefundViewController{
        -view: RefundView

        +RefundViewController(view: RefundView)
        +getView(): RefundView
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
        
        +MenuViewController(view: MenuView)
        +getView(): MenuViewController
        +setRegisteredView(): void
        +setManagerView(): void
        +setBasicView(): void
    }





}

package Controller{

}

package Model{

}

@enduml