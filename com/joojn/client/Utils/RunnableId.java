package com.joojn.client.Utils;

public class RunnableId implements Runnable{

    public int id;
    public long time;

    public RunnableId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setTime(long time){
        this.time = time;
    }

    public long getTime(){
        return this.time;
    }

    @Override
    public void run() {
    }

    public void onEnd(){

    }
}
