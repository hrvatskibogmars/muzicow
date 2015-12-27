package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.R;

/**
 * Created by Emil on 27.12.2015..
 */
public class CustomEvenetListAdapter extends ArrayAdapter{
    List<Event> event;
    public CustomEvenetListAdapter(Context context, List<Event> event){
        super(context, R.layout.event_itemlist_design,event);
        this.event = event;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.event_itemlist_design, parent, false);
        TextView eventItems = (TextView)customView.findViewById(R.id.eventItems);
        eventItems.setText(event.get(position).getName());
        return customView;
    }



}
