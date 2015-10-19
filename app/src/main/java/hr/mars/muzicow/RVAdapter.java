package hr.mars.muzicow;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private List<DummyData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView songName;
        public TextView authorName;
        public TextView mTextView;
        public ImageView upVote;
        public TextView totalUpVote;


        public ViewHolder(View v) {
            super(v);
            //mTextView = v;
            songName = (TextView) v.findViewById(R.id.song_name);
            //songName =  (TextView)  .findViewById(R.id.song_name);
            authorName = (TextView) v.findViewById(R.id.author_name);
            upVote = (ImageView) v.findViewById(R.id.up_vote);
            totalUpVote = (TextView) v.findViewById(R.id.total_up_voted);

            upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, authorName.getText(), Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RVAdapter(List<DummyData> dummyDataList) {
        mDataset = dummyDataList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.content_main, viewGroup, false);
        // set the view's size, margins, paddings and layout parameters
        //...




        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //// TODO: 19/10/15
    /*
        Disable event
        Dodaj refresh animation
        Pretvoriti Mdataset u JSON

     */

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.songName.setText(mDataset.get(position).songName);
        holder.authorName.setText(mDataset.get(position).authorName);
        holder.totalUpVote.setText(mDataset.get(position).upVote);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}