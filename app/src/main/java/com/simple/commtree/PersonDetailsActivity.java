package com.simple.commtree;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PersonDetailsActivity extends AppCompatActivity {
    private TextView tvPersonDetailName,tvPersonDetailEmail,tvPersonDetailPhone,tvPersonDetailAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        tvPersonDetailName= (TextView) findViewById(R.id.tvPersonDetailName);
        tvPersonDetailEmail= (TextView) findViewById(R.id.tvPersonDetailEmail);
        tvPersonDetailPhone= (TextView) findViewById(R.id.tvPersonDetailPhone);
        tvPersonDetailAge= (TextView) findViewById(R.id.tvPersonDetailAge);

        int position = getIntent().getIntExtra("Position", -1);
        searchPerson(position);
    }

    public void searchPerson(int position) {
        String clickedKey = Results.getInstance().getKeysArray().get(position);
        Results.getInstance().getmDatabase().child(clickedKey).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Person personDetailsModel = dataSnapshot.getValue(Person.class);
                        tvPersonDetailName.setText(getString(R.string.person_name,personDetailsModel.getName()));
                        tvPersonDetailEmail.setText(getString(R.string.person_email,personDetailsModel.getEmail()));
                        tvPersonDetailPhone.setText(getString(R.string.person_phone,personDetailsModel.getPhone()));
                        tvPersonDetailAge.setText(getString(R.string.person_age, String.valueOf(personDetailsModel.getAge())));
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}