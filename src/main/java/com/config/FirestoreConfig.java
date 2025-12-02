package com.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.util.Base64;

@Configuration
public class FirestoreConfig {

    @PostConstruct
    public void initFirestore() throws Exception {

        String base64 = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_BASE64");

        if (base64 == null) {
            throw new IllegalStateException("Falta la variable GOOGLE_APPLICATION_CREDENTIALS_BASE64 en Render");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64);

        GoogleCredentials credentials =
                GoogleCredentials.fromStream(new ByteArrayInputStream(decodedBytes));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }

        FirestoreClient.getFirestore();
        System.out.println("🔥 Firestore inicializado correctamente en Render");
    }
}
