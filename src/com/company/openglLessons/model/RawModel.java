package com.company.openglLessons.model;

/**
 * Created by omak on 3/23/17.
 */
public class RawModel {
    private int id;
    private int count;
    public RawModel (int id,int count){
        this.id = id;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }
}
