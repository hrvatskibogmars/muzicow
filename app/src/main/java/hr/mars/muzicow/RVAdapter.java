package hr.mars.muzicow;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        public ViewHolder(View v) {
            super(v);
            //mTextView = v;
            songName = (TextView) v.findViewById(R.id.song_name);
            //songName =  (TextView) v.findViewById(R.id.song_name);
            authorName = (TextView) v.findViewById(R.id.author_name);
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

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.songName.setText(mDataset.get(position).songName);
        holder.authorName.setText(mDataset.get(position).authorName);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}