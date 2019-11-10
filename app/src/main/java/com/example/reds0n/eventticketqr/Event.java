package com.example.reds0n.eventticketqr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Event extends AppCompatActivity {

    private RecyclerView recycle;
    private DatabaseReference mDatabase;


    FirebaseRecyclerAdapter<category,CategoryViewHolder> FBRA;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        recycle = (RecyclerView) findViewById(R.id.eventsid);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    protected void onStart(){
        super.onStart();

        FBRA = new FirebaseRecyclerAdapter<category, CategoryViewHolder>(
                category.class,
                R.layout.flyers_layout,
                Event.CategoryViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(CategoryViewHolder viewHolder, category model, final int position) {

                viewHolder.setName(model.getName());
                viewHolder.setImage(getApplicationContext(),model.getImage());


                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //category id to new intent
                        Intent eventList = new Intent(Event.this,QRcode.class);

                        //extra key for matching

                        eventList.putExtra("CategoryId",FBRA.getRef(position).getKey());
                        startActivity(eventList);
                    }

                });


            }
        };

        recycle.setAdapter(FBRA);

    }
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public  CategoryViewHolder(View itemView){
            super(itemView);
            mView=itemView;

        }

        public void setName(String name){
            TextView food_name = (TextView) mView.findViewById(R.id.menu_text);
            food_name.setText(name);


        }



        public void setImage(Context ctx, String image ){

            ImageView food_image = (ImageView) mView.findViewById(R.id.menu_image);
            Picasso.with(ctx).load(image).fit().into(food_image);


        }


    }


}
