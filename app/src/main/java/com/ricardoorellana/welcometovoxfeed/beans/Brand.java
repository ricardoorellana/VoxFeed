package com.ricardoorellana.welcometovoxfeed.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Brand
 *
 * Holds brand data
 *
 * @author rorellanam
 * @version 1.0
 * @since 1.0
 */
public class Brand implements Parcelable {
    private String name;
    private String logo;

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.logo);
    }

    protected Brand(Parcel in) {
        this.name = in.readString();
        this.logo = in.readString();
    }

    public static final Parcelable.Creator<Brand> CREATOR = new Parcelable.Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel source) {
            return new Brand(source);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };
}
