package com.gooosie.gankio.model;

import java.util.Date;

/**
 * Gank
 */

public class Gank extends BaseModel {

    public boolean used;
    public String type;
    public String url;
    public String who;
    public String desc;
    public Date createdAt;
    public Date publishedAt;

    @Override
    public String toString() {
        return "Gank{" +
                "used=" + used +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", who='" + who + '\'' +
                ", desc='" + desc + '\'' +
                ", createdAt=" + createdAt +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
