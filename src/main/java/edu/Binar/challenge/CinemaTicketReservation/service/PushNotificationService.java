package edu.Binar.challenge.CinemaTicketReservation.service;

import edu.Binar.challenge.CinemaTicketReservation.model.PushNotificationRequest;

import java.util.Map;

public interface PushNotificationService {

    void sendSamplePushNotification();
    void sendPushNotification(PushNotificationRequest request);
    void sendPushNotificationWithoutData(PushNotificationRequest request);
    void sendPushNotificationToToken(PushNotificationRequest request);

}
