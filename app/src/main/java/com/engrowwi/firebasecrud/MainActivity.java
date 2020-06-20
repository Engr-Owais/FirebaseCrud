package com.engrowwi.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    public static final String RECORD_ID = "recordid";

    EditText editTextName;
    EditText editTextsecond;
    EditText editTextthird;
    EditText edittextage;
    Button buttonAddArtist;
    RadioButton male;
    RadioButton female;
    Spinner spinner;
    int i = 0;
    //our database reference object
    DatabaseReference databaseArtists;



    //a list to store all the artist from firebase database
    List<Artist> artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editfirstTextName);
        editTextsecond = (EditText) findViewById(R.id.editsecondTextName);
        editTextthird = (EditText) findViewById(R.id.editthirdTextName);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        spinner = (Spinner) findViewById(R.id.spinnerGenres);
        edittextage = (EditText)findViewById(R.id.edittextage);


        //list to store artists

        //getting the reference of artists node
        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");



        //adding an onclicklistener to button
        findViewById(R.id.buttonAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addArtist();
            }
        });

        findViewById(R.id.buttonShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(MainActivity.this,Info_Artist.class);
               startActivity(intent);

            }
        });

    }


    private void addArtist() {
        //getting the values to save
        String firstname = editTextName.getText().toString().trim();
        String secondname = editTextsecond.getText().toString().trim();
        String thirdname = editTextthird.getText().toString().trim();
        String age =edittextage.getText().toString() ;
        String gender1;
        String spinn = spinner.getSelectedItem().toString();

        if (male.isChecked())
        {
            gender1="male";
        }

        else {
            gender1="female";
        }



        //checking if the value is provided
        if (!TextUtils.isEmpty(firstname) && (!TextUtils.isEmpty(secondname)) && (!TextUtils.isEmpty(thirdname)))
        {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseArtists.push().getKey();

            //creating an Artist Object
            Artist artist = new Artist(id , firstname ,secondname,thirdname ,gender1 ,spinn,age);



            //Saving the Artist
            databaseArtists.child(id).setValue(artist);





            //setting edittext to blank again
            editTextName.setText("");
            editTextsecond.setText("");
            editTextthird.setText("");


            //displaying a success toast
            Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show();

        }

        else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_LONG).show();
        }


    }
}
