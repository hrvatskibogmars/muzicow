package hr.mars.muzicow.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Emil on 22.12.2015..
 */
public class DJ implements Parcelable{
    String _ID;
    String name;
    String website;
    String location;
    String nickname;
    String description;
    String profile_url;
    String twitter_url;

    public String getTwitter_url() {return twitter_url;}

    public void setTwitter_url(String twitter_url) {this.twitter_url = twitter_url;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {this.description = description;}

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }

    public DJ(){
    }
    public DJ(Parcel in){readFromParcel(in);}

    public String get_ID() {
        return _ID;
    }

    public void set_ID(String _ID) {
        this._ID = _ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._ID);
        dest.writeString(this.location);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.website);
        dest.writeString(this.description);
        dest.writeString(this.profile_url);
        dest.writeString(this.twitter_url);
    }

    private void readFromParcel(Parcel in) {

        this._ID=in.readString();
        this.location=in.readString();
        this.name=in.readString();
        this.nickname=in.readString();
        this.website=in.readString();
        this.description=in.readString();
        this.profile_url =in.readString();
        this.twitter_url =in.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public DJ createFromParcel(Parcel in)
        {
            return new DJ(in);
        }

        @Override
        public DJ[] newArray(int size)
        {
            return new DJ[size];
        }
    };
}


