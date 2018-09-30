I have created the multi module project with the name async-booking-platform which is having following two modules.

1- booking-service (Akka module in which we have created BookingSupervisor actor, we can instantiat BookingSupervisor actor by passing BookingManager, BookingManager is actual services which are responsible to performed action with the halp of BookingRepository repository. BookingRepository is working as the fictitious databes with the help of Set & Map.) 

In this module we have created following two main class.
1- Application - Acting as Unit test case of BookingManager
2- Booking App - Acting as Unit test case of BookingSupervisor actor

2- web-service (Play module in which we have used BookingService, BookingService used play actor system & create BookingSupervisor actor to communicate with booking-service). Following are the calls which are used to access the booking-service.

1- Get availability of particular room on particular date with URL http://localhost:9000/availability?room=101&dateStr=2018-09-07
2- Add Booking for particular Guest of particular roo on given date with URL http://localhost:9000/book?guest=Test&room=101&dateStr=2018-09-06
3- Get all available rooms on particular date with URL http://localhost:9000/rooms?dateStr=2018-09-05

The above mentioned actions are registerd in web-service\conf\routes file. We can use postman or any rest client to test these. We have created all request/response in asynch manner.)

How to run the application.

1- Use following steps to running booking-service module.
	a) Navigate till ~\async-booking-platform 
	b) sbt
	c) project booking-service, you will prompt with following options
		[1] com.booking.service.Application
		[2] com.booking.service.BookingApp
	d) Press 1 to run Application or enter 2 to run BookingApp

2- Use following steps to running web-service module.
	a) Navigate till ~\async-booking-platform 
	b) sbt
	c) project web-service
	d) run Play application will start