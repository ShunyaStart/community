package com.simple.commtree;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class home2 extends AppCompatActivity {
    final Context context = this;
    private CustomAdapter customAdapter;
    ListView listView;
    FloatingActionButton flowTing;
    Cursor cursor;
    StudentRepo studentRepo;

    private final static String TAG = home2.class.getName().toString();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    //  DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        setTitle("Search");

        studentRepo = new StudentRepo(this);
        cursor = studentRepo.getStudentList();
        customAdapter = new CustomAdapter(home2.this, cursor, 0);
        listView = (ListView) findViewById(R.id.lstStudent);
        flowTing = (FloatingActionButton) findViewById(R.id.fab);
        listView.setAdapter(customAdapter);
        if (cursor == null) insertDummy();
        flowTing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* custom dialog */
                createDialog();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//              Student abc =(Student)  parent.getSelectedItem();
                SQLiteCursor abc =(SQLiteCursor)listView.getItemAtPosition(position);
                Log.d("Student",abc.getString(1));
                Log.d("Student",abc.getString(2));
                Log.d("Student",abc.getString(3));
                Log.d("Student",abc.getString(4));
                Student val = new Student();
                val.name=abc.getString(2);
                val.email=abc.getString(3);
                val.age=Integer.parseInt(abc.getString(4));


                final EditDialog3 dialog = new EditDialog3(context,val);
                dialog.setContentView(R.layout.add_or_update_person);
                dialog.setTitle("AddDetails");
                dialog.show();;
                TextView tvPersonDetailName,tvPersonDetailEmail,tvPersonDetailPhone,tvPersonDetailAge;
                Dialog Show = new Dialog(context);

            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void createDialog(){
        final EditDialog2 dialog = new EditDialog2(this);
        dialog.setContentView(R.layout.add_or_update_person);
        dialog.setTitle("AddDetails");

       /* bOK = (Button) findViewById(R.id.bOk);
        bCancel = (Button) findViewById(R.id.bCancel);
        pName = (EditText) findViewById(R.id.pName);
        pEmail = (EditText) findViewById(R.id.pEmail);
//              pPhone = (EditText) findViewById(R.id.pPhone);
        pAge = (EditText) findViewById(R.id.pAge);*/
        /*Student student = new Student();

        student.age = 22;
        student.email = "Dhru18shah";
        student.name = "dhrumil shah";
        studentRepo.insert(student);
*/
        /*bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.age = Integer.parseInt(pAge.getText().toString()) ;
                student.email = pEmail.getText().toString();
                student.name = pName.getText().toString();
                int id = studentRepo.insert(student);
                Log.d("ValueID",id+"");
            }
        });*/
        dialog.show();

    }
    private void insertDummy() {

        Student student = new Student();

        student.age = 22;
        student.email = "मुम्बई ";
        student.name = "सुजीत पुजारी";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age = 20;
        student.email = "पुणे ";
        student.name = "द्रुमिल शाह";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age = 21;
        student.email = "सोलापुर ";
        student.name = "प्रवीण पाटिल ";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age = 19;
        student.email = "लातूर ";
        student.name = "प्रदीप होलकर ";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age = 18;
        student.email = "कोल्हापुर ";
        student.name = "नीलेश चव्हाण ";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age = 23;
        student.email = "रत्नागिरी ";
        student.name = "नरेश पाटिल ";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age = 21;
        student.email = "नागपुर ";
        student.name = "दीपक भाटकर ";
        studentRepo.insert(student);

        studentRepo = new StudentRepo(this);
        student.age = 24;
        student.email = "नाशिक ";
        student.name = "राहुल आर ";
        studentRepo.insert(student);


        studentRepo = new StudentRepo(this);
        student.age = 19;
        student.email = "शिरडी ";
        student.name = "संजय सुर्वे ";
        studentRepo.insert(student);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor = studentRepo.getStudentListByKeyword(s);
                    if (cursor == null) {
                        Toast.makeText(home2.this, "No records found!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(home2.this, cursor.getCount() + " records found!", Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor = studentRepo.getStudentListByKeyword(s);
                    if (cursor != null) {
                        customAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });

        }

        return true;

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "home2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.simple.commtree/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "home2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.simple.commtree/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public class EditDialog2 extends Dialog implements android.view.View.OnClickListener {

        Context context;

        EditText pName, pEmail, pPhone, pAge;
        Button bOK,bCancel;

        public EditDialog2(Context context) {
            super(context);
            this.context = context;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            super.onCreate(savedInstanceState);
            setContentView(R.layout.add_or_update_person);

            bOK = (Button) findViewById(R.id.bOk);
            bCancel = (Button) findViewById(R.id.bCancel);
            pName = (EditText) findViewById(R.id.pName);
            pEmail = (EditText) findViewById(R.id.pEmail);
//              pPhone = (EditText) findViewById(R.id.pPhone);
            pAge = (EditText) findViewById(R.id.pAge);
            bOK.setOnClickListener(this);
            bCancel.setOnClickListener(this);
        }

        @Override
        public void onClick( View which) {
            if(which == bOK){
            Student student = new Student();
            student.age = Integer.parseInt(pAge.getText().toString()) ;
            student.email = pEmail.getText().toString();
            student.name = pName.getText().toString();
            int id = studentRepo.insert(student);
            Log.d("ValueID",id+"");
                Toast.makeText(context,id+"",Toast.LENGTH_SHORT);
            hide();
            }
            else
            if(which == bCancel)
            {
                dismiss();
            }

        }
    }

    public class EditDialog3 extends Dialog implements android.view.View.OnClickListener {

        Context context;
        TextView tvPersonDetailName,tvPersonDetailEmail,tvPersonDetailPhone,tvPersonDetailAge;
        private Button ok;
        private Student student;
        public EditDialog3(Context context ,Student student) {
            super(context);
            this.context = context;
            this.student=student;
        }
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            //start
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_person_details);
            tvPersonDetailName= (TextView) findViewById(R.id.tvPersonDetailName);
            tvPersonDetailEmail= (TextView) findViewById(R.id.tvPersonDetailEmail);
            tvPersonDetailPhone= (TextView) findViewById(R.id.tvPersonDetailPhone);
            tvPersonDetailAge= (TextView) findViewById(R.id.tvPersonDetailAge);
           //finish
            tvPersonDetailName.setText(student.name);
            tvPersonDetailEmail.setText(student.email);
            tvPersonDetailPhone.setText("000000000000");
            tvPersonDetailAge.setText(student.age+"");
            ok=(Button) findViewById(R.id.OKJAAGE);
            ok.setOnClickListener(this);
        }
        @Override
        public void onClick( View which) {
            if(which == ok){
                Log.d("ValueID","");
                Toast.makeText(context,"",Toast.LENGTH_SHORT);
                hide();
            }
        }
    }
}
