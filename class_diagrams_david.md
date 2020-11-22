@startuml

class User{
    -ticket: Ticket
    -paymentInfo: PaymentInfo
}

class RegisteredUser{
    -name: String
    -address: String 
    -userName: String
    -email: String
    -password: String
    
}

class RegularUser{
    -email: String
}



class PaymentInfo{
    -cardNumber: int
    -expiryDate: int

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
}

class PayTicketFee{
    -amount: double
    -receipt: TicketReciept
}

class PayAnnualFee{
    -amount: double
    -receipt: AnnualReceipt
}

@enduml