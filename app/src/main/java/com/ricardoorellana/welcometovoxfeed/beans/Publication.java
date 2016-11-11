package com.ricardoorellana.welcometovoxfeed.beans;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Publication
 *
 * Holds publication data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class Publication implements Parcelable {
    private int id;
    private String socialNetwork;
    private String date;
    private double earnings;

    private User user;
    private Campaign campaign;
    private Brand brand;
    private Post post;
    private Stat stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getEarning() {
        return earnings;
    }

    public void setEarning(double earnings) {
        this.earnings = earnings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Stat getStat() {
        return stats;
    }

    public void setStat(Stat stat) {
        this.stats = stat;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", socialNetwork='" + socialNetwork + '\'' +
                ", earning=" + earnings +
                ", user=" + user +
                ", campaign=" + campaign +
                ", brand=" + brand +
                ", post=" + post +
                ", stat=" + stats +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.socialNetwork);
        dest.writeString(this.date);
        dest.writeDouble(this.earnings);
        dest.writeParcelable(this.user, flags);
        dest.writeParcelable(this.campaign, flags);
        dest.writeParcelable(this.brand, flags);
        dest.writeParcelable(this.post, flags);
        dest.writeParcelable(this.stats, flags);
    }

    public Publication() {
    }

    protected Publication(Parcel in) {
        this.id = in.readInt();
        this.socialNetwork = in.readString();
        this.date = in.readString();
        this.earnings = in.readDouble();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.campaign = in.readParcelable(Campaign.class.getClassLoader());
        this.brand = in.readParcelable(Brand.class.getClassLoader());
        this.post = in.readParcelable(Post.class.getClassLoader());
        this.stats = in.readParcelable(Stat.class.getClassLoader());
    }

    public static final Parcelable.Creator<Publication> CREATOR = new Parcelable.Creator<Publication>() {
        @Override
        public Publication createFromParcel(Parcel source) {
            return new Publication(source);
        }

        @Override
        public Publication[] newArray(int size) {
            return new Publication[size];
        }
    };
}

