package com.ricardoorellana.welcometovoxfeed.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stat
 *
 * Holds statistic data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class Stat implements Parcelable {
    private int clicks;
    private int shares;
    private int likes;
    private int comments;
    private int audience;

    public Stat() {
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getAudience() {
        return audience;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "clicks=" + clicks +
                ", shares=" + shares +
                ", likes=" + likes +
                ", comments=" + comments +
                ", audience=" + audience +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.clicks);
        dest.writeInt(this.shares);
        dest.writeInt(this.likes);
        dest.writeInt(this.comments);
        dest.writeInt(this.audience);
    }

    protected Stat(Parcel in) {
        this.clicks = in.readInt();
        this.shares = in.readInt();
        this.likes = in.readInt();
        this.comments = in.readInt();
        this.audience = in.readInt();
    }

    public static final Parcelable.Creator<Stat> CREATOR = new Parcelable.Creator<Stat>() {
        @Override
        public Stat createFromParcel(Parcel source) {
            return new Stat(source);
        }

        @Override
        public Stat[] newArray(int size) {
            return new Stat[size];
        }
    };
}
