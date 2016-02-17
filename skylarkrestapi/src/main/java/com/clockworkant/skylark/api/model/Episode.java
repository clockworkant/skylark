package com.clockworkant.skylark.api.model;

import java.util.ArrayList;
import java.util.List;

public class Episode {
    private String title;
    private String subtitle;
    private List<String> image_urls;

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public List<String> getImage_urls() {
        return image_urls != null ? image_urls : new ArrayList<String>();
    }
}
