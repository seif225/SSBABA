package com.example.ssbaba;

import android.content.Intent;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


     ;
    RecyclerView categroiesRecyclerView;
    private static final int PICK_IMG_REQUEST =1 ;
    private static final int SPACING =1 ;
    private static int SPAN_COUNT=4;
    CategoryAdapter adapter;
    ArrayList<Item> arrayList;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        ViewFlipper viewFlipper=findViewById(R.id.view_flipper);

        int img[]={R.drawable.slide3,R.drawable.slide2,R.drawable.slide1};

        for(int image:img){

            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(image);

            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(3000);
            viewFlipper.setAutoStart(true);

            viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

        }

        arrayList = new ArrayList<>();
        arrayList.add(new Item(R.drawable.ecommerce2,"phones"));
        arrayList.add(new Item(R.drawable.ecommerce2,"tablets"));
        arrayList.add(new Item(R.drawable.ecommerce2,"Smart Watches"));
        arrayList.add(new Item(R.drawable.ecommerce2,"Accessories"));

        categroiesRecyclerView = findViewById(R.id.categories);

        adapter = new CategoryAdapter(arrayList, this, new CategoryAdapter.OnClickListener() {
            @Override
            public void onClick(int i) {
                Intent intent = new Intent(MainActivity.this,SpecificCategoryActivity.class);
                intent.putExtra("i",i);
                Log.e("Main",i+"");

                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        categroiesRecyclerView.setLayoutManager(gridLayoutManager);
        categroiesRecyclerView.addItemDecoration(new GridItemDecoration(SPAN_COUNT,SPACING,true));
        categroiesRecyclerView.setAdapter(adapter);

    }


}
