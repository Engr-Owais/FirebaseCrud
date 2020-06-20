package com.engrowwi.firebasecrud;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artist> {

    private Activity context;
    List<Artist> artists;

    public ArtistList(Activity context, List<Artist> artists) {
        super(context, R.layout.layout_artist_list, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_artist_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewsecond = (TextView) listViewItem.findViewById(R.id.textviewsecondname);
        TextView textViewthird = (TextView) listViewItem.findViewById(R.id.textthirdname);
        TextView textViewgender =  (TextView) listViewItem.findViewById(R.id.gender);
        TextView textViewcountry =  (TextView) listViewItem.findViewById(R.id.country);
        TextView textage = (TextView) listViewItem.findViewById(R.id.age);



        Artist artist = artists.get(position);
        textViewName.setText("First Name : "+artist.getFirstName());
        textViewsecond.setText("Last Name : "+artist.getSecondName());
        textViewthird.setText("Middle Name : "+artist.getThirdName());
        textViewgender.setText("Gender : "+artist.getGender1());
        textViewcountry.setText("Country : "+artist.getCountry());
        textage.setText("Age :"+artist.getAge());


        return listViewItem;
    }
}
