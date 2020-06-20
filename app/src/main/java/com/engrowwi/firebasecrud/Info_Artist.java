package com.engrowwi.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class Info_Artist extends AppCompatActivity {

    TextView textViewFirst;
    TextView textViewSecond;
    TextView textViewThird;
    TextView gendermale;
    DatabaseReference databaseArtists;
    Spinner spinnercountry;

    ListView listViewArtists;



    //a list to store all the artist from firebase database
    List<Artist> artists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info__artist);



        textViewFirst = findViewById(R.id.textViewName);
        textViewSecond = findViewById(R.id.textviewsecondname);
        textViewThird = findViewById(R.id.textthirdname);
        gendermale = findViewById(R.id.gender);
        spinnercountry = (Spinner) findViewById(R.id.spinnerGenres);

        listViewArtists = (ListView) findViewById(R.id.listViewTracks);




        //list to store artists
        artists = new ArrayList<>();


        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");




        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Artist artist = artists.get(i);
                CallUpdateAndDeleteDialog(artist.getId(), artist.getFirstName(),artist.getSecondName(),artist.getThirdName(),artist.getGender1(),artist.getCountry(),artist.getAge());
                return true;
            }
        });


    }





    private void CallUpdateAndDeleteDialog(final String id, String firstname, String secondname, String thirdname , final String gender1 , final String country , final String age) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_update_dialog, null);
        dialogBuilder.setView(dialogView);
//Access Dialog views
        final EditText updateTextname = (EditText) dialogView.findViewById(R.id.updateTextname);
        final EditText updateTextemail = (EditText) dialogView.findViewById(R.id.updateTextemail);
        final EditText updateTextmobileno = (EditText) dialogView.findViewById(R.id.updateTextmobileno);
        final RadioButton updatemale = (RadioButton) dialogView.findViewById(R.id.updmale);
        final RadioButton updatefemale = (RadioButton) dialogView.findViewById(R.id.updfemale);
        final Spinner Country = (Spinner) dialogView.findViewById(R.id.countries);
        final EditText updateage = (EditText)dialogView.findViewById(R.id.updateage);


        updateTextname.setText(firstname);
        updateTextemail.setText(secondname);
        updateTextmobileno.setText(thirdname);
        updateage.setText(age);



        if (gender1.equals("male")){
            updatemale.setChecked(true);
        }else
        {
            updatefemale.setChecked(true);
        }



        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateUser);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteUser);
//username for set dialog title
        dialogBuilder.setTitle(firstname);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteArtist(id);
                b.dismiss();
            }
        });

        // Click listener for Update data
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstname = updateTextname.getText().toString().trim();
                String secondname = updateTextemail.getText().toString().trim();
                String thirdname = updateTextmobileno.getText().toString().trim();
                String genre = Country.getSelectedItem().toString();
                String age = updateage.getText().toString();

                String gendermale = updatemale.getText().toString();
                String genderfemale = updatefemale.getText().toString();
//checking if the value is provided or not Here, you can Add More Validation as you required

                if (!TextUtils.isEmpty(firstname)) {
                    if (!TextUtils.isEmpty(age)) {
                        if (!TextUtils.isEmpty(secondname)) {
                            if (!TextUtils.isEmpty(thirdname)) {
//Method for update data

                                String gender = "";

                                if (updatemale.isChecked()) {
                                    gender = "male";
                                } else if (updatefemale.isChecked()) {
                                    gender = "female";
                                }
                                updateUser(id, firstname, secondname, thirdname, gender, genre, age);
                                b.dismiss();
                            }
                        }
                    }
                }

            }
        });
    }

    

    private boolean updateUser(String id, String firstname, String secondname , String thirdname , String gender1 ,String country , String age ) {


//getting the specified User reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);
        Artist artist = new Artist(id, firstname,secondname, thirdname,gender1,country,age);
//update User to firebase
        dR.setValue(artist);
        Toast.makeText(getApplicationContext(), "User Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteArtist(String id) {
        //getting the specified artist reference

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("artists").child(id);


        //removing artist
        dR.removeValue();



        return true;


    }



    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                artists.clear();

                //iterating through all the nodes

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Artist artist = postSnapshot.getValue(Artist.class);
                    artists.add(artist);


                }

                //creating adapter
                ArtistList artistAdapter = new ArtistList(Info_Artist.this, artists);


                listViewArtists.setAdapter(artistAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

}
