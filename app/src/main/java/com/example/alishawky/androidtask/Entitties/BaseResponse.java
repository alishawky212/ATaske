package com.example.alishawky.androidtask.Entitties;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by alishawky on 14/07/18.
 */

public class BaseResponse implements Parcelable {

    public final static Parcelable.Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        public BaseResponse[] newArray(int size) {
            return (new BaseResponse[size]);
        }

    };
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("articles")
    @Expose
    private ArrayList<Article> articles = null;

    protected BaseResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.sortBy = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.articles, (Article.class.getClassLoader()));
    }

    public BaseResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(source);
        dest.writeValue(sortBy);
        dest.writeList(articles);
    }

    public int describeContents() {
        return 0;
    }

}