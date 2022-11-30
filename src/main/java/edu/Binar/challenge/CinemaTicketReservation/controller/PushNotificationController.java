package edu.Binar.challenge.CinemaTicketReservation.controller;

import edu.Binar.challenge.CinemaTicketReservation.model.PushNotificationRequest;
import edu.Binar.challenge.CinemaTicketReservation.model.PushNotificationResponse;
import edu.Binar.challenge.CinemaTicketReservation.service.PushNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mycinema-v1")
public class PushNotificationController {

    @Autowired
    private PushNotificationService pushNotificationService;

    private static final String MESSAGE = "Notification has been sent";

    @PostMapping("/notification/topic")
    public ResponseEntity<PushNotificationResponse> sendNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotificationWithoutData(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @PostMapping("/notification/token")
    public ResponseEntity<PushNotificationResponse> sendTokenNotification(@RequestBody PushNotificationRequest request) {

        pushNotificationService.sendPushNotificationToToken(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @PostMapping("/notification/data")
    public ResponseEntity<PushNotificationResponse> sendDataNotification(@RequestBody PushNotificationRequest request) {

        pushNotificationService.sendPushNotification(request);

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity<PushNotificationResponse> sendSampleNotification() {

        pushNotificationService.sendSamplePushNotification();

        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), MESSAGE), HttpStatus.OK);
    }
}
