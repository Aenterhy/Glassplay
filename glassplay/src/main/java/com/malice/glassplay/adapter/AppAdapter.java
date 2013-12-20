package com.malice.glassplay.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.glass.widget.CardScrollAdapter;
import com.malice.glassplay.GlassPlay;
import com.malice.glassplay.R;
import com.malice.glassplay.model.App;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AppAdapter extends CardScrollAdapter {

    public static int MAX_IMAGE_WIDTH = 360;
    public static int MAX_IMAGE_HEIGHT = 360;
    private List<App> applctations;

    public AppAdapter(List<App> apps) {
        applctations = apps;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(GlassPlay.get()).inflate(R.layout.view_application, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        App app = applctations.get(i);
        holder.title.setText(app.getTitle());
        holder.company.setText(app.getCreator());

        // TODO: fix it on api side
        if (!TextUtils.isEmpty(app.getRating()) && app.getRating().length() > 1)
            holder.rating.setText("Rating: " + app.getRating().substring(0, 4));

        Picasso.with(GlassPlay.get()).
                load(app.getImage()).
                resize(MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT).
                centerCrop().
                placeholder(R.drawable.ic_placeholder_icon).
                into(holder.image);

        return view;
    }

    @Override
    public int findIdPosition(Object o) {
        return -1;
    }

    @Override
    public int findItemPosition(Object o) {
        return applctations.indexOf(o);
    }

    @Override
    public int getCount() {
        return applctations.size();
    }

    @Override
    public Object getItem(int i) {
        return applctations.get(i);
    }

    static class ViewHolder {
        TextView title;
        TextView company;
        TextView rating;
        ImageView image;

        public ViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            company = (TextView) view.findViewById(R.id.company);
            rating = (TextView) view.findViewById(R.id.rating);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}
