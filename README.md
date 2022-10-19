# MyCinema REST API

# ERD

![ERD](https://github.com/mfyasykur/MuhammadFauzanYasykur_reservasi_bioskop/blob/main/image/Reservasi%20Tiket%20Bioskop.png?raw=true)

Details:
```
Table User {
  userID int [pk, increment]
  name varchar(64)
  username varchar(64) [unique]
  email varchar(64)
  password varchar(20)
  phone varchar(16)
}

Table Movie {
  movieID int [pk]
  title varchar(256)
  description varchar(512)
  duration int
  language varchar(16)
  releaseDate datetime
  country varchar(64)
  genre varchar(20)
  status Movie.showingStatus
}

Enum Movie.showingStatus {
  onShow
  coomingSoon
}

Table Show {
  showID int [pk]
  date datetime
  startTime datetime
  endTime datetime
  cinemaHallID int [ref: > Cinema_Hall.cinemaHallID]
  movieID int [ref: > Movie.movieID]
}

Table Booking {
  bookingID int [pk]
  numberOfSeats int
  timeStamp datetime
  status Booking.bookingStatus
  userID int [ref: > User.userID]
  showID int [ref: > Show.showID]
}

Enum Booking.bookingStatus {
  success
  cancelled
}

Table Cinema {
  cinemaID int [pk]
  name varchar(64)
  totalCinemaHalls int
  cityID int [ref: > City.cityID]
}

Table City {
  cityID int [pk]
  name varchar(64)
  state varchar(64)
  zipCode varchar(16)
}

Table Cinema_Hall {
  cinemaHallID int [pk]
  name varchar(64)
  totalSeats int
  cinemaID int [ref: > Cinema.cinemaID]
}

Table Cinema_Seat {
  cinemaSeatID int [pk]
  seatNumber int
  type Cinema_Seat.seatType
  cinemaHallID int [ref: > Cinema_Hall.cinemaHallID]
}

Enum Cinema_Seat.seatType {
  regular
  sweetBox
}

Table Show_Seat {
  showSeatID int [pk]
  status Show_Seat.seatStatus
  price numeric
  cinemaSeatID int [ref: > Cinema_Seat.cinemaSeatID]
  showID int [ref: > Show.showID]
  bookingID int [ref: > Booking.bookingID]
}

Enum Show_Seat.seatStatus {
  available
  sold
}

Table Payment {
  paymentID int [pk]
  amount numeric
  timeStamp datetime
  method Payment.paymentMethod
  bookingID int [ref: > Booking.bookingID]
}

Enum Payment.paymentMethod {
  cash
  bank
  eWallet
}

Table Invoice {
  invoiceId int [pk]
  invoiceNumber varchar(64)
  title varchar(64)
  date date
  time varchar(8)
  row varchar(2)
  seatNumber varchar(8)
  hall varchar(2)
  price double
  createdAt timeStamp
}
```
# API Documentation via Swagger
![Swagger](https://github.com/mfyasykur/MuhammadFauzanYasykur_reservasi_bioskop/blob/main/image/MyCinemaAPI-Swagger.png?raw=true)

# Generate Invoice PDF with Jaspersoft
![Invoice](https://github.com/mfyasykur/MuhammadFauzanYasykur_reservasi_bioskop/blob/main/image/MyCinema-InvoiceTicket.png?raw=true)

![Invoice.PDF](https://github.com/mfyasykur/MuhammadFauzanYasykur_reservasi_bioskop/blob/main/generated-invoices/invoice-MYCNM-001.pdf?raw=true)
