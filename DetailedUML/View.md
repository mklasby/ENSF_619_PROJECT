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
            +flashMessage(e: ActionEvent): void
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

        class IssueNewsViewController{
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
@enduml