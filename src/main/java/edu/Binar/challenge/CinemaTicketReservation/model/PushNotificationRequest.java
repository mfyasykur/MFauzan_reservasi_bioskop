package edu.Binar.challenge.CinemaTicketReservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PushNotificationRequest {

    private String title;
    private String message;
    private String topic;
    private String token;

    public PushNotificationRequest(String title, String message, String topic) {
        this.title = title;
        this.message = message;
        this.topic = topic;
    }
}
