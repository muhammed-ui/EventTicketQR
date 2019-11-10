package com.example.reds0n.eventticketqr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

public class QRcode extends AppCompatActivity {

    public ImageView QRcode;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    Button paid;
    category myEvent;
    TextView price,eventName,eventDescription;
    ImageView eventFlyer, eventImages;
    Button Buy;

    DatabaseReference mDatabase,  myEventDatabase;

    String eventId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

      //    QRcode = (ImageView) findViewById(R.id.QRcode);
        //  paid = (Button) findViewById(R.id.paid);

        final String Payer = Common.currentuser.getEmail();
        mDatabase = FirebaseDatabase.getInstance().getReference("Paid");
        myEventDatabase = FirebaseDatabase.getInstance().getReference("Events");


        eventDescription= (TextView) findViewById(R.id.eventDescription);
        price= (TextView) findViewById(R.id.price);
        eventName= (TextView) findViewById(R.id.eventName);
        eventFlyer= (ImageView) findViewById(R.id.eventIFlyer);
        Buy= (Button) findViewById(R.id.buyTicket);


        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ticket = new Intent(QRcode.this,Ticket.class);
                startActivity(ticket);
            }
        });

        if (getIntent() != null) {


            eventId = getIntent().getStringExtra("CategoryId");


            if (!eventId.isEmpty() && eventId != null) {


                loadfood(eventId);


            }

        }
          //     final String eventName = DataSnapshot.class.getName();
        category Event = new category();
        final String eventName = Event.getName();

        paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.child(eventName).push().setValue(Payer);

            }
        });


            //QR code sample

      //    String QRText = Common.currentuser.getEmail();
        //  MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
      // try {
       //     BitMatrix bitMatrix = multiFormatWriter.encode(QRText, BarcodeFormat.QR_CODE, 200, 200);
         //  BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
          ///  Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
           // QRcode.setImageBitmap(bitmap);
      //  } catch (WriterException e) {
          //  e.printStackTrace();
       // }


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);

    }


    private void loadfood(String eventId) {
               myEventDatabase.child(eventId).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {

                       myEvent=dataSnapshot.getValue(category.class);
                       Picasso.with(getBaseContext()).load(myEvent.getImage()).into(eventFlyer);
                       collapsingToolbarLayout.setTitle(myEvent.getName());
                       price.setText(myEvent.getPrice());
                       eventName.setText(myEvent.getName());
                       eventDescription.setText(myEvent.getDescription());
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });


    }
}