package com.ricardoorellana.welcometovoxfeed.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Campaign
 *
 * Holds Campaign data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class Campaign implements Parcelable {
    private String name;
    private String coverImage;

    public Campaign() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "campaingnName='" + name + '\'' +
                ", campaingCoverImage='" + coverImage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.coverImage);
    }

    protected Campaign(Parcel in) {
        this.name = in.readString();
        this.coverImage = in.readString();
    }

    public static final Parcelable.Creator<Campaign> CREATOR = new Parcelable.Creator<Campaign>() {
        @Override
        public Campaign createFromParcel(Parcel source) {
            return new Campaign(source);
        }

        @Override
        public Campaign[] newArray(int size) {
            return new Campaign[size];
        }
    };
}
