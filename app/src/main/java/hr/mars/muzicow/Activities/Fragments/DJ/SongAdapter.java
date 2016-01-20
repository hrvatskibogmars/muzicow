package hr.mars.muzicow.Activities.Fragments.DJ;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.Models.Song;
import hr.mars.muzicow.R;

/**
 * Created by Emil on 20.1.2016..
 */
public class SongAdapter extends ArrayAdapter{

        List<Song> songs;
        public SongAdapter(Context context, List<Song> songs){
            super(context, R.layout.event_itemlist_design,songs);
            this.songs = songs;

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View customView = layoutInflater.inflate(R.layout.event_itemlist_design, parent, false);
            TextView eventItems = (TextView)customView.findViewById(R.id.eventItems);
            eventItems.setText(songs.get(position).getName());

                if (songs.get(position).getStatus().equals("1")){
                    eventItems.setTextColor(Color.parseColor("#673AB7"));
                }
                else {
                    eventItems.setTextColor(Color.parseColor("#F44336"));
                }


            return customView;
        }




}
