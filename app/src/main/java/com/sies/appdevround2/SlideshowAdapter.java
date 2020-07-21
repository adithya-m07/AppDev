package com.sies.appdevround2;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SlideshowAdapter extends RecyclerView.Adapter<SlideshowAdapter.SlideshowViewHolder> {
    private final int[] mImages;
    Resources resource;

    public static class SlideshowViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public SlideshowViewHolder(@NonNull ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
    public SlideshowAdapter(int[] myImages, Resources resources){
        mImages = myImages;
        resource = resources;
    }
    @NonNull
    @Override
    public SlideshowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageView imageView  = (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.slideshow, parent, false);
        return new SlideshowViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(@NonNull SlideshowViewHolder holder, final int position) {
        holder.imageView.setImageBitmap(
                ImageResize.decodeSampledBitmapFromResource(resource, mImages[position], 500, 500));
    }


    @Override
    public int getItemCount() {
        return mImages.length;
    }


}
