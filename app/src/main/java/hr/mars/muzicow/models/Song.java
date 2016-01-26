package hr.mars.muzicow.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emil on 16.1.2016..
 */
public class Song implements Parcelable {
    String _ID;
    String artist;
    String event_ID;
    String description;
    String upvoited;

    public String getUpvoited() {
        return upvoited;
    }

    public void setUpvoiteD(String upvoite) {
        this.upvoited = upvoite;
    }


    public Song(){

    }

    public Song(Parcel in){readFromParcel(in);}

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getEvent_ID() {
        return event_ID;
    }

    public void setEvent_ID(String event_ID) {
        this.event_ID = event_ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String status;
    String youtube;
    String name;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._ID);
        dest.writeString(this.artist);
        dest.writeString(this.event_ID);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeString(this.youtube);
        dest.writeString(this.name);
        dest.writeString(this.upvoited);

    }

    private void readFromParcel(Parcel in) {

        this._ID=in.readString();
        this.artist=in.readString();
        this.event_ID=in.readString();
        this.description=in.readString();
        this.status=in.readString();
        this.youtube=in.readString();
        this.name =in.readString();
        this.upvoited =in.readString();

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public Song createFromParcel(Parcel in)
        {

            return new Song(in);
        }

        @Override
        public Song[] newArray(int size)
        {
            return new Song[size];
        }
    };
}
