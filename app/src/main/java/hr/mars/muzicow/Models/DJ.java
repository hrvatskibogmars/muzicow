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
    String country;
    String city;
    String nickname;
    String email;

    public DJ(){
    }
    public DJ(Parcel in){readFromParcel(in);}

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        dest.writeString(this.city);
        dest.writeString(this.country);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.website);
    }

    private void readFromParcel(Parcel in) {

        this._ID=in.readString();
        this.city=in.readString();
        this.country=in.readString();
        this.name=in.readString();
        this.nickname=in.readString();
        this.website=in.readString();
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


