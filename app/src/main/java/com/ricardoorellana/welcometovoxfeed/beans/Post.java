package com.ricardoorellana.welcometovoxfeed.beans;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Post
 *
 * Holds Post data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class Post implements Parcelable {
    private String text;
    private String image;
    private String link;

    public Post() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Post{" +
                "text='" + text + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
        dest.writeString(this.image);
        dest.writeString(this.link);
    }

    protected Post(Parcel in) {
        this.text = in.readString();
        this.image = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<Post> CREATOR = new Parcelable.Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel source) {
            return new Post(source);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
