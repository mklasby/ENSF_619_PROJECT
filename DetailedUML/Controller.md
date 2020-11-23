@startuml
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
        -updateShowtimeAvailability(ticket: JsonObject): void
        +getSeatList(): ResultSets
        +getSeat(movieName: String, theatreId: int, showtimeDate: Date, seatNum: int): ResultSet
        +insertTicket(ticket: JsonObject): void
        +getReceipt(receiptId: int): ResultSet
        +returnTicket(receiptId: int): void
        +insertReceipt(receipt: JsonObject): void
    }
}
@enduml