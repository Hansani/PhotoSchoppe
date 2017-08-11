package com.assignment.hansi.photoschoppe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hansanibiyanwila on 7/28/17.
 */

public class Image implements Parcelable {

    private int index;
    private String title;
    private String link;
    private String media;
    private String date_taken;
    private String description;
    private String published;
    private String author;
    private String author_id;
    private String tags;


    //constructor
    public Image() {
    }

    public Image(int index, String title, String link, String media, String date_taken, String description,
                 String published, String author, String author_id, String tags) {
        this.index = index;
        this.title = title;
        this.link = link;
        this.media = media;
        this.date_taken = date_taken;
        this.description = description;
        this.published = published;
        this.author = author;
        this.author_id = author_id;
        this.tags = tags;
    }

    public Image(int index, String title, String link, String media) {
        this.index = index;
        this.title = title;
        this.link = link;
        this.media = media;

    }

    //parceling part
    protected Image(Parcel in) {
        index = in.readInt();
        title = in.readString();
        link = in.readString();
        media = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    //getter and setter
    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getDate_taken() {
        return date_taken;
    }

    public void setDate_taken(String date_taken) {
        this.date_taken = date_taken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    //implements method
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(index);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeString(media);
    }

}
