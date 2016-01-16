package hr.mars.muzicow.Activities.Fragments.User;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.mars.muzicow.APIs.DJAPI;
import hr.mars.muzicow.APIs.SongAPI;
import hr.mars.muzicow.Models.DJ;
import hr.mars.muzicow.Models.Event;
import hr.mars.muzicow.Models.Song;
import hr.mars.muzicow.R;
import hr.mars.muzicow.Models.DummyDataPlaylist;
import hr.mars.muzicow.Registry.Registry;
import hr.mars.muzicow.Services.ServiceGenerator;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PlaylistFragment extends Fragment {
    ArrayList<String> song;
    RecyclerView rv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rv = (RecyclerView) inflater.inflate(R.layout.fragment_playlist, container, false);
        setupRecyclerView(rv);
        return rv;
    }


    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleStringRecyclerViewAdapter(getActivity(),
                getRandomSublist()));
    }

    private ArrayList<String> getReqSongs(){
        final String event_id = "events?filter=%7B%22where%22%3A%7B%22dj_ID%22%3A%22" + ((DJ) Registry.getInstance().get("djObject")).get_ID() + "%22%7D%7D";
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
        eventRetrofit.getEvent(event_id, new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                String eventID= events.get(0).get_ID();
                String songs ="songs?filter=%7B%22where%22%3A%7B%22event_id%22%3A%22"+eventID+"%22%7D%7D";
                eventRetrofit.getSongs(songs, new Callback<List<Song>>() {
                    @Override
                    public void success(List<Song> songs, Response response) {
                        song= new ArrayList<>();
                        song.add(songs.get(0).getName());
                        Log.d("songs",songs.get(0).getName());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("songs",error.getMessage());
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("songs",error.getMessage());
            }
        });
        return song;
    }

    private List<String> getRandomSublist() {
        final ArrayList<String> list = new ArrayList<>();
        final String event_id = "events?filter=%7B%22where%22%3A%7B%22dj_ID%22%3A%22" + ((DJ) Registry.getInstance().get("djObject")).get_ID() + "%22%7D%7D";
        final SongAPI eventRetrofit = ServiceGenerator.createService(SongAPI.class);
        eventRetrofit.getEvent(event_id, new Callback<List<Event>>() {
            @Override
            public void success(List<Event> events, Response response) {
                String eventID= events.get(1).get_ID();
                String songs ="songs?filter=%7B%22where%22%3A%7B%22event_id%22%3A%22"+eventID+"%22%7D%7D";
                Log.d("songs","id eventa " +eventID);
                eventRetrofit.getSongs(songs, new Callback<List<Song>>() {
                    @Override
                    public void success(List<Song> songs, Response response) {
                        Log.d("songs",songs.get(0).getName());
                        for(int i=0;i<songs.size();i++) {
                            list.add(songs.get(i).getName());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("songs", error.getMessage());
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("songs",error.getMessage());
            }
        });
        return list;
    }

    public static class SimpleStringRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<String> mValues;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            //public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                //mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public String getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleStringRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mBoundString = mValues.get(position);
            holder.mTextView.setText(mValues.get(position));
            holder.mTextView.setTextColor(Color.parseColor("#F44336"));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, SongDetailActivity.class);
                    intent.putExtra(SongDetailActivity.EXTRA_NAME, holder.mBoundString);

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
