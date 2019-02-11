package com.tarekmtolba.raseedi.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ad implements Parcelable {
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("order")
    @Expose
    private Integer order;

    /**
     *
     * @param picture
     * @param title
     * @param order
     * @param url
     */
    public Ad(String picture, String title, String url, Integer order) {
        super();
        this.picture = picture;
        this.title = title;
        this.url = url;
        this.order = order;
    }

    public String getPicture() {
        return picture;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Integer getOrder() {
        return order;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.picture);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeValue(this.order);
    }

    public Ad() {
    }

    protected Ad(Parcel in) {
        this.picture = in.readString();
        this.title = in.readString();
        this.url = in.readString();
        this.order = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Ad> CREATOR = new Parcelable.Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel source) {
            return new Ad(source);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };
}
