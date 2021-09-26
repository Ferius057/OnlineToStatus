package com.ferius_057.onlineToStatus.data;

public class ClearRecord extends Thread {
    @Override
    public void run() {
        while (true) {
            Data.record_hour = 0;
            Data.growth_hour = 0;
            try {
                Thread.sleep(3600000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}