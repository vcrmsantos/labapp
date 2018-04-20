package br.com.fatecipiranga.app.labapp.main.Utils;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//static for no instantiation

public class FirebaseConfig {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth autenticacao;

    public static DatabaseReference getFireBase() {

        if (referenciaFirebase == null) {
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }

        return referenciaFirebase;
    }

    //jBgW8yG8MjfJsOtlmSY1Q7nntcz2

    public static FirebaseAuth getFirebaseAuthentication(){
        if(autenticacao == null){
            autenticacao  = FirebaseAuth.getInstance();

        }
        return autenticacao;
    }

    public  static DatabaseReference getNotificationRef(){
        return FirebaseDatabase.getInstance().getReference("Notifications");
    }


}
