@startuml

package Model{

    class Movie{

    }

    class Theatre{

    }

    class Ticket{

    }

    class RegisteredUser extends Person{

    }

    class Showtime{

    }

    class Person{

    }

    class Manager extends Person{

    }

    class TicketMgmt{

    }

    class UserMgmt{
        
    }

    class Seat{

    }

    class PaymentRecord{

    }

    class ModelBuilder{

    }

    UserMgmt ...> RegisteredUser
    UserMgmt ...> Manager
    TicketMgmt *--- "1" Ticket
    Ticket *--- "1" Movie
    Ticket ---> "1" Theatre
    Ticket ---> "1" Showtime
    Ticket ---> "1" Seat
    Ticket ---> "1" PaymentRecord
    RegisteredUser "0...1" <---> "0..*" Ticket
    ModelBuilder ...> Movie
    ModelBuilder ...> Theatre
    ModelBuilder ...> Showtime
    ModelBuilder ...> Seat
    ModelBuilder ...> PaymentRecord



}

package Controller{
    class AppController{

    }
    
    class DBController{

    }

    class ModelController{

    }
    
    class ViewController{

    }

    class MovieViewController{

    }

    class TheatreViewController{

    }

    class ShowtimeViewController{

    }

    class PaymentViewController{

    }

    class MenuController{

    }

    class SeatViewController{
        
    }

    class RefundViewController{

    }

    AppController o--- "1" DBController
    AppController o--- "1" ModelController
    AppController "1" ---* ViewController
    ViewController o--- "1" MovieViewController
    ViewController o--- "1" TheatreViewController
    ViewController o--- "1" PaymentViewController
    ViewController o--- "1" ShowtimeViewController
    ViewController o--- "1" MenuController
    ViewController o--- "1" SeatViewController
    ViewController o--- "1" RefundViewController
}



package View{

    class GUI{

    }

    class MovieView{

    }

    class TheatreView{

    }
    
    class ShowtimeView{

    }

    class SeatView{

    }
    class PaymentView{

    }
    
    class RefundView{

    }

    class MenuView{

    }

    GUI *--- "1" MovieView
    GUI *--- "1" TheatreView
    GUI *--- "1" ShowtimeView
    GUI *--- "1" SeatView
    GUI *--- "1" PaymentView
    GUI *--- "1" RefundView
    GUI *--- "1" MenuView

}


ModelController ...> UserMgmt
ModelController ...> TicketMgmt
ModelController ...> ModelBuilder
PaymentViewController o--- "1" PaymentView
MovieViewController o--- "1" MovieView
TheatreViewController o--- "1" TheatreView
SeatViewController o--- "1" SeatView
RefundViewController o--- "1" RefundView
ShowtimeViewController o--- "1" ShowtimeView
MenuController o--- "1" MenuView


@enduml
