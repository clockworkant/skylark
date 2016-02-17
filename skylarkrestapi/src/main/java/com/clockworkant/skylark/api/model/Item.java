package com.clockworkant.skylark.api.model;

public class Item {
    protected String content_url;
    protected String content_type;
    protected int position;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item{");
        sb.append("content_url='").append(content_url).append('\'');
        sb.append(", content_type='").append(content_type).append('\'');
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }

    public String getContentUrl() {
        return content_url;
    }

    public String getContentType() {
        return content_type;
    }

    public int getPosition() {
        return position;
    }
}
