package com.clockworkant.skylark.api.model;

import java.util.List;

public class SkylarkSet {
    String uid;
    String body;
    String formatted_body;
    boolean featured;
    String quote;
    String quoter;
    String summary;
    String temp_image;
    String title;
    List<Item> items;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SkylarkSet{");
        sb.append("uid='").append(uid).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", formatted_body='").append(formatted_body).append('\'');
        sb.append(", featured=").append(featured);
        sb.append(", quote='").append(quote).append('\'');
        sb.append(", quoter='").append(quoter).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", temp_image='").append(temp_image).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }

    public List<Item> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getBody() {
        return body;
    }

    public boolean isFeatured() {
        return featured;
    }

    public String getQuote() {
        return quote;
    }

    public String getQuoter() {
        return quoter;
    }

    public String getTempImage() {
        return temp_image;
    }
}