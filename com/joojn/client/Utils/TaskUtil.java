package com.joojn.client.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.joojn.client.Event.EventTarget;
import com.joojn.client.Event.impl.TickEvent;

public class TaskUtil {
    private static HashMap<Integer, RunnableId> taskLaterHash = new HashMap<>();
    private static HashMap <Integer, RunnableId> taskLaterAsyncHash = new HashMap<>();
    private static HashMap <Integer, RunnableId> loopForTimeHash = new HashMap<>();

    public static void runTaskLater(RunnableId runnable, long time){
        runnable.setTime(time);
        taskLaterHash.putIfAbsent(runnable.getId(), runnable);
    }

    public static void runTaskLaterAsync(RunnableId runnable, long time){
        runnable.setTime(time);
        taskLaterAsyncHash.putIfAbsent(runnable.getId(), runnable);
    }

    public static void runEveryTickFor(RunnableId runnable, long time){
        runnable.setTime(time);
        loopForTimeHash.putIfAbsent(runnable.getId(), runnable);
    }

    @EventTarget
    public void onTick(TickEvent event){
        ArrayList <Integer> taskLaterAsyncHashRemove = new ArrayList<>();
        ArrayList <Integer> taskLaterHashRemove = new ArrayList<>();
        ArrayList <Integer> loopForTimeHashRemove = new ArrayList<>();

        // async hashes
        for(int id : taskLaterAsyncHash.keySet()){
            RunnableId runnable = taskLaterAsyncHash.get(id);
            long time = runnable.getTime() - 1;
            runnable.setTime(time);

            if(time < 1){
                taskLaterAsyncHashRemove.add(id);
                new Thread(runnable).start();
            }
        }

        // loops
        for(int id : loopForTimeHash.keySet()){
            RunnableId runnable = loopForTimeHash.get(id);
            long time = runnable.getTime() - 1;
            runnable.setTime(time);

            runnable.run();
            if(time < 1){
                loopForTimeHashRemove.add(id);
                runnable.onEnd();
            }
        }

        // sync hashes
        for(int id : taskLaterHash.keySet()){
            RunnableId runnable = taskLaterHash.get(id);
            long time = runnable.getTime() - 1;
            runnable.setTime(time);

            if(time < 1){
                taskLaterHashRemove.add(id);
                runnable.run();
            }
        }

        taskLaterAsyncHash.keySet().removeAll(taskLaterAsyncHashRemove);
        taskLaterHash.keySet().removeAll(taskLaterHashRemove);
        loopForTimeHash.keySet().removeAll(loopForTimeHashRemove);
    }
}
