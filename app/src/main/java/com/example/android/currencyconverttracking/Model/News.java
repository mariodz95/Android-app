package com.example.android.currencyconverttracking.Model;

import com.google.gson.annotations.SerializedName;

public class News {
    @SerializedName("id")
    private String id;
    @SerializedName("guid")
    private String guid;
    @SerializedName("published_on")
    private Integer published_on;
    @SerializedName("imageurl")
    private String imageurl;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("source")
    private String source;
    @SerializedName("body")
    private String body;
    @SerializedName("tags")
    private String tags;
    @SerializedName("categories")
    private String categories;
    @SerializedName("lang")
    private String lang;

    public News(String id, String guid, Integer published_on, String imageurl, String title,String url,String source,String body,String tags,String categories,String lang){
        this.id = id;
        this.guid = guid;
        this.published_on = published_on;
        this.imageurl = imageurl;
        this.title = title;
        this.url = url;
        this.source = source;
        this.body = body;
        this.tags = tags;
        this.categories = categories;
        this.lang = lang;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setPublished_on(Integer published_on) {
        this.published_on = published_on;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public Integer getPublished_on() {
        return published_on;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public String getCategories() {
        return categories;
    }

    public String getLang() {
        return lang;
    }
}
