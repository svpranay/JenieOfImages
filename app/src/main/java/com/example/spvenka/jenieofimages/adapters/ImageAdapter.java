package com.example.spvenka.jenieofimages.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.spvenka.jenieofimages.R;
import com.example.spvenka.jenieofimages.models.GImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<GImage> {

    public ImageAdapter(Context context, List<GImage> images) {
        super(context, 0, images);
    }

    // View lookup cache
    private static class ViewHolder {
        public ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final GImage gImage = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_image, parent, false);
            viewHolder.imageView = (ImageView)convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate data into the template view using the data object
        Picasso.with(getContext()).load(Uri.parse(gImage.thumbnailUrl)).placeholder(R.mipmap.ic_launcher).into(viewHolder.imageView);
        // Return the completed view to render on screen
        return convertView;
    }
}
