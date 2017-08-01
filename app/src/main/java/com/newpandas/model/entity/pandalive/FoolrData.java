package com.newpandas.model.entity.pandalive;

/**
 * Created by yan on 2017/7/31.
 */

public class FoolrData {
    private int total;
    private int date;

    public FoolrData() {
    }

    public FoolrData(int total, int date) {
        this.total = total;
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FloorDate{" +
                "total=" + total +
                ", date='" + date + '\'' +
                '}';
    }
}
