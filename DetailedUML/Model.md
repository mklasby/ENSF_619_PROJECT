@startuml
package Model{

    package TheatreModel{
        class IssueNewsController{
            -news:News
            +IssueNewsController(movieNews: File)
            +issueNews(emails: ArrayList<String>):Movie
            }

        class News{
            -fname: String
            -content: String
            +News(movieNews: File)
            +encode(): JsonObject
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
            +Seat(seatResultSet: ResultSet)
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
    class UserManager{
        -user: User
        +getUser()
        +userIsRegistered(): boolean

    }


    class RegisteredUser{
        -name: String
        -address: String 
        -userName: String
        -email: String
        -password: String
        -hasPaidDues: boolean
    }

    class UnregisteredUser{
        -email: String
    }

    class Manager{
        -name: String
        -address: String 
        -userName: String
        -email: String
        -password: String
    }

    class PaymentInfo{
        -cardNumber: int
        -cardType: String
        -cardPin: int
    }

    class PaymentManager{
        -user: User
        +payForTicket(ticket: Ticket): JsonObject
        +refundTicket(ticketReceipt: ResultSet): JsonObject
        +payAnnualFee(): JsonObject
    }

    class RefundTicket{
        -ticketAmount: double
        -receipt: RefundReceipt
        -couponRequired: boolean
        +RefundTicket(ticketReceipt: ResultSet, couponRequired: boolean)
        +getReceipt(ticketReceipt: Result, couponRequired: boolean)
        +addCoupon(): void
    }

    class Coupon{
        -refundAmount: double
        -expiryDate: Date
    }

    class PayTicketFee{
        +PayTicketFee(ticketId: int, amount: Double)
        -amount: double
        -receipt: TicketReceipt
    }

    class PayAnnualFee{
        -amount: double
        -receipt: AnnualReceipt
    }

    class Receipt{
        receiptNum: int
        amount: double
    }

    class RefundReceipt{
        -ticketNumber: int
        -coupon: Coupon
    }

    class TicketReceipt{
        -ticketNumber: int
    }

    class AnnualReceipt{
        -year: int
    }
}
@enduml