@startuml


package View{

    class GUI{
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

    class LoginView{
        -titleLabel: JLabel
        -usernameLabel: JLabel
        -passwordLabel: JLabel
        -usernameField: JTextField
        -passwordField: JTextField
        -submitButton: JButton
        -view: JPanel

        +LoginView()
        +getUsernameField(): String
        +getPasswordField(): String
    }

    class LoginViewController{

    }


    class IssueNewsController{

    }

    class RegisterViewController{

    }


    class MovieViewController{

    }

    class TheatreViewController {

    }

    class ShowtimeViewController{

    }

    class SeatViewController{
        -seat: SeatView
        

        +SeatViewController(seat: SeatView)
        +getGui(): GUI

    }

    class SeatView{
        -seats: HashMap<Integer, Seat>
        -seatButtons: HashMap<Integer, Seat>
        
        +SeatView()

        +setSeats()


    }





}

package Controller{

}

package Model{

}

@enduml