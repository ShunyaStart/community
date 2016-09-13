package com.simple.commtree;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {


    private static final String TAG = "MainActivity";
    private static final int RC_SIGN_IN = 9001;
    //private EditText id,pass;

    private Button login,signup;
    private ProgressBar progressBar;
    final Context context = this;


    private FirebaseAuth auth;
    GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

       /* if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }*/

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build() ;

        mGoogleApiClient =   new GoogleApiClient.Builder(this).enableAutoManage(this , this ).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();


       // id = (EditText) findViewById(R.id.editText2);
        //pass = (EditText) findViewById(R.id.editText3);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        login = (Button) findViewById(R.id.button);
        signup = (Button) findViewById(R.id.signup);
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(this);

       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }


        });

      /*  c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cn=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo nf=cn.getActiveNetworkInfo();
                if(nf != null && nf.isConnected()==true )
                {


                    Firebase myFirebaseRef = new Firebase("https://sqlite-5220c.firebaseio.com/");
                    final String id = a.getText().toString();
                    final String pass = b.getText().toString();

                    myFirebaseRef.authWithPassword(id, pass, new Firebase.AuthResultHandler(){
                        @Override
                        public void onAuthenticated(AuthData authData) {
                            //   System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());

                            Intent i = new Intent( context, Home.class);
                            i.putExtra("ComingFrom", authData.getUid());
                            final int result=1;
                            startActivityForResult(i, result);
                            // MainActivity.t.setText("dost");

                        }
                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // there was an error

                            Toast toast = Toast.makeText(getApplicationContext(), "Invalid login", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    });
                }
                else
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Network Not Available", Toast.LENGTH_LONG);
                    toast.show();

                }



            }
        });*/


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.signup:
                signIn();
                break;
        }

    }

    private void signIn(){
        Intent signInintent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInintent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG,"handleSignInResult" + result.isSuccess());
        if(result.isSuccess()){
            GoogleSignInAccount acct = result.getSignInAccount();
           // user.setText("Hello "+ acct.getDisplayName());
            Intent i = new Intent( context, home2.class);
            String str = acct.getDisplayName();
            i.putExtra("ComingFrom", str);
            //final int results=1;
            startActivity(i);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}