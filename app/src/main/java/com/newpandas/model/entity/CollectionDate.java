package com.newpandas.model.entity;

import java.io.Serializable;

/**
 * Created by 联想 on 2017/7/24.
 */

public class CollectionDate implements Serializable{
    String imageurl;
    String movietime;
    String moviename;
    String moviedate;
    String movieurl;
    String movieotherurl;

    public CollectionDate(String imageurl, String movietime, String moviename, String moviedate, String movieurl, String movieotherurl) {
        this.imageurl = imageurl;
        this.movietime = movietime;
        this.moviename = moviename;
        this.moviedate = moviedate;
        this.movieurl = movieurl;
        this.movieotherurl = movieotherurl;
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

    public void setMovieotherurl(String movieotherurl) {
        this.movieotherurl = movieotherurl;
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

    public String getMovieotherurl() {
        return movieotherurl;
    }
}
