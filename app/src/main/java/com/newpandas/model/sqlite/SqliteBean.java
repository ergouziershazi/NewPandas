package com.newpandas.model.sqlite;

/**
 * Created by 联想 on 2017/7/21.
 */

public class SqliteBean {
    int collect;
    String imageurl;
    String movietime;
    String moviename;
    String moviedate;
    String movieurl;

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setMovietime(String movietime) {
        this.movietime = movietime;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public void setMoviedate(String moviedate) {
        this.moviedate = moviedate;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getMovietime() {
        return movietime;
    }

    public String getMoviename() {
        return moviename;
    }

    public String getMoviedate() {
        return moviedate;
    }

    public String getMovieurl() {
        return movieurl;
    }
}
