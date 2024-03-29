package com.reciproci.contacts.models;

public class ModelCalls {
    private String name;
    private String duration;
    private String date;

    public ModelCalls(String number,String duration,String date){
        this.name = number;
        this.duration =  duration;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String number) {
        this.name = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
