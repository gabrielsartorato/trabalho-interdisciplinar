package com.gsartorato.scjdtws.firebase;

import java.io.IOException;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class ConexaoFirebase {
	
	public Firestore iniciarFirebase()  {
		
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(getClass().getResourceAsStream("meumedico-67271-firebase-adminsdk-tb3n5-61d586d2ca.json")))
			  .setDatabaseUrl("https://meumedico-67271.firebaseio.com")
			  .build();
			
			FirebaseApp.initializeApp(options);

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return FirestoreClient.getFirestore();
	}

}
