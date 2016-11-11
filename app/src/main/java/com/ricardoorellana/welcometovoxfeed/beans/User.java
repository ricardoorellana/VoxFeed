package com.ricardoorellana.welcometovoxfeed.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * User
 *
 * Holds user data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class User implements Parcelable {
    private String username;
    private String profileImage;

    public User() {
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getUserProfileImage() {
        return profileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.profileImage = userProfileImage;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + username + '\'' +
                ", userProfileImage='" + profileImage + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.profileImage);
    }

    protected User(Parcel in) {
        this.username = in.readString();
        this.profileImage = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
