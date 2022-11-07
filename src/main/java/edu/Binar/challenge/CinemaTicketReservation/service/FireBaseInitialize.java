package edu.Binar.challenge.CinemaTicketReservation.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FireBaseInitialize {

    @PostConstruct
    public void initialize() {

        try (FileInputStream serviceAccount = new FileInputStream("./demofirebase-a8207-firebase-adminsdk-abmeh-a829142150.json")) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://demofirebase-a8207-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
