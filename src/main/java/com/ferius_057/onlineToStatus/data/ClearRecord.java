package com.ferius_057.onlineToStatus.data;

public class ClearRecord extends Thread {
    @Override
    public void run() {
        Data.record_hour = 0;
        try {
            Thread.sleep(3600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}