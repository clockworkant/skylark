package com.clockworkant.skylark.api.model;

/**
 * Created by alec on 16/02/2016.
 */
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

    public String getContent_url() {
        return content_url;
    }

    public String getContent_type() {
        return content_type;
    }

    public int getPosition() {
        return position;
    }
}
