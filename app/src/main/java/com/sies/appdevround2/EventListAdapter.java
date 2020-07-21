package com.sies.appdevround2;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {
    private final int[] eventImages;
    private final String[] eventName;
    private final String[] eventLocn;
    private final String[] eventdate;
    private final int[] eventPrice;
    Resources resource;

    public static class EventViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        private final TextView name;
        private final TextView location;
        private final TextView month;
        private final TextView date;
        private final TextView price;
        public EventViewHolder(View view) {
            super(view);
            imageView= view.findViewById(R.id.event_image);
            name = view.findViewById(R.id.event_name);
            location = view.findViewById(R.id.event_location);
            month = view.findViewById(R.id.month);
            date = view.findViewById(R.id.date);
            price = view.findViewById(R.id.price);
        }
    }
    public EventListAdapter(int[] myImages, String[] eName, String[] eLocn,String[] edate, int[] eprice, Resources resources){
        eventImages = myImages;
        resource = resources;
        eventName= eName;
        eventdate= edate;
        eventLocn= eLocn;
        eventPrice = eprice;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  =  LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.imageView.setImageBitmap(
                ImageResize.decodeSampledBitmapFromResource(resource, eventImages[position], 150, 150));
        holder.name.setText(eventName[position]);
        holder.location.setText(eventLocn[position]);
        holder.month.setText(eventdate[position].split(" ")[0]);
        holder.date.setText(eventdate[position].split(" ")[1]);
        holder.price.setText("Rs "+eventPrice[position]);
    }


    @Override
    public int getItemCount() {
        return eventImages.length;
    }


}
