package com.sies.appdevround2;



import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import ru.tinkoff.scrollingpagerindicator.ScrollingPagerIndicator;


public class MainActivity extends AppCompatActivity  {

    private String name = "All That Jazz";
    private String disc = "Smooth Jazz for your soul ";

    private final int[] slideImageList = new int[]{
            R.drawable.jazz_slide_1,R.drawable.jazz_slide_2,R.drawable.jazz_slide_3
    };

    private final int[] eventImageList = new int[]{
            R.drawable.jazz_event_1,R.drawable.jazz_event_2,R.drawable.jazz_event_3
    };

    private final String[] eventName = new String[]{
            "The NCPA National Jazz Festival - The Latination and Kevin Day Quintet","Sanjeeta Bhattacharya Live at The Finch",
            "The NCPA National Jazz Festival - Greg Banaszak Quintet and Jam Session"
    };

    private final String[] eventDisc = new String[]{
            "Tata Theater, The NCPA","Tata Theater, The NCPA","Tata Theater, The NCPA"
    };

    private final String[] date = new String[]{
            "Nov 17","Nov 20", "Nov 21"
    };

    private final int[] price = new int[]{
            269,389,269
    };

    private int likes = 1000;

    private boolean wasOn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            likes = savedInstanceState.getInt("likes");
            wasOn = savedInstanceState.getBoolean("wasOn");
            ToggleButton toggleButton = findViewById(R.id.heart_button);
            toggleButton.setChecked(wasOn);
        }

        RecyclerView slideshow = findViewById(R.id.slideshow_adapter);
        LinearLayoutManager slideshowLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        slideshow.setLayoutManager(slideshowLayoutManager);
        RecyclerView.Adapter slideshowAdapter = new SlideshowAdapter(slideImageList,getResources());
        slideshow.setAdapter(slideshowAdapter);

        ScrollingPagerIndicator recyclerIndicator = findViewById(R.id.indicator);
        recyclerIndicator.attachToRecyclerView(slideshow);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(slideshow);

       RecyclerView event = findViewById(R.id.event_list_adapter);
        LinearLayoutManager eventLayoutManager = new LinearLayoutManager(this);
        event.setLayoutManager(eventLayoutManager);
        RecyclerView.Adapter eventAdapter = new EventListAdapter(eventImageList,eventName,eventDisc,date,price, getResources());
        event.setAdapter(eventAdapter);

        TextView textView = findViewById(R.id.hearts);
        textView.setText(String.valueOf(likes));

        disc+="| "+ date.length +" Events";

        TextView group = findViewById(R.id.event_group);
        TextView group_disc = findViewById(R.id.event_group_disc);

        group.setText(name);
        group_disc.setText(disc);


    }
    public void onHeart(View view) {
        boolean on = ((ToggleButton) view).isChecked();
        TextView textView = findViewById(R.id.hearts);
        if(on){
            likes+=1;
            textView.setText(String.valueOf(likes));
            wasOn = true;
        }
        else{
            likes-=1;
            textView.setText(String.valueOf(likes));
            wasOn = false;
        }
    }

    public void onShare(View view){
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        startActivity(Intent.createChooser(share,"Share"));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("likes", likes);
        savedInstanceState.putBoolean("wasOn",wasOn);
    }




}
