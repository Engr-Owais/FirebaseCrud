//package com.engrowwi.firebasecrud;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class ArtistR extends RecyclerView.Adapter<ArtistR.ContactHolder> {
//
//
//
//    // List to store all the contact details
//    ArrayList<Artist> artist;
//    Context mContext;
//
//
//    // Counstructor for the Class
//    public ArtistR(ArrayList<Artist> artist, Context context ) {
//        this.artist = artist;
//        this.mContext = context;
//
//    }
//
//
//
//    // This method creates views for the RecyclerView by inflating the layout
//    // Into the viewHolders which helps to display the items in the RecyclerView
//    @Override
//    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = (LayoutInflater.from(mContext).inflate(R.layout.layout_artist_list,parent,false));
//        return new ContactHolder(view);
//    }
//
//    @Override
//    public int getItemCount() {
//         return artist.size();
//    }
//
//    // This method is called when binding the data to the views being created in RecyclerView
//    @Override
//    public void onBindViewHolder(@NonNull ContactHolder holder, final int id) {
//        final Artist contact = artist.get(id);
//
//        // Set the data to the views here
//        holder.setContactFirstName(contact.getFirstName());
//        holder.setContactSecondName(contact.getSecondName());
//        holder.setContactThirdName(contact.getThirdName());
//        holder.setContactGender(contact.getGender1());
//        holder.setContactCountry(contact.getCountry());
//        holder.txtfirstname.setText((CharSequence) artist.get(id));
//        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(mContext, (CharSequence) artist.get(id), Toast.LENGTH_SHORT).show();
//               // Toast.makeText(mContext, "clcik", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//    // This is your ViewHolder class that helps to populate data to the view
//    public class ContactHolder extends RecyclerView.ViewHolder  {
//
//        TextView txtfirstname, txtsecondname, txtthirdname, txtgender, txtcountry;
//        View mView;
//        LinearLayout parentLayout;
//
//
//
//        public ContactHolder(View itemView) {
//            super(itemView);
//
//            txtfirstname = itemView.findViewById(R.id.textViewName);
//            txtsecondname = itemView.findViewById(R.id.textviewsecondname);
//            txtthirdname = itemView.findViewById(R.id.textthirdname);
//            txtgender = itemView.findViewById(R.id.gender);
//            txtcountry = itemView.findViewById(R.id.country);
//            parentLayout = itemView.findViewById(R.id.list_root);
//
//        }
//
//        public void setContactFirstName(String firstName) {
//            txtfirstname.setText(firstName);
//        }
//
//        public void setContactSecondName(String secondName) {
//            txtsecondname.setText(secondName);
//        }
//
//        public void setContactThirdName(String thirdName) {
//            txtthirdname.setText(thirdName);
//        }
//
//        public void setContactGender(String gender1) {
//            txtgender.setText(gender1);
//        }
//
//        public void setContactCountry(String country) {
//            txtcountry.setText(country);
//        }
//    }
//}