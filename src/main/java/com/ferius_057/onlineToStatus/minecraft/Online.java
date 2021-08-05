package com.ferius_057.onlineToStatus.minecraft;

import com.ferius_057.onlineToStatus.data.Config;
import com.ferius_057.onlineToStatus.data.Data;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Online {
    public int[] get(String ip, int port) {
        FetchData minecraftServer = new FetchData();
        minecraftServer.setAddress(new InetSocketAddress(ip, port));
        StatusResponse statusResponse = null;
        try {
            statusResponse = minecraftServer.fetchData();
        } catch (IOException e) {
            if (Data.work) {
                System.err.println("X | Неудалось получить онлайн. " + e.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                int[] req = get(ip, port);
                return new int[] {req[0], req[1]};
            } else {
                System.err.println("X | Неудалось получить онлайн. " + e.getMessage());
                System.exit(0);
            }
        }
        assert statusResponse != null;
        return new int[] {statusResponse.getPlayers().getOnline(), statusResponse.getPlayers().getMax()};
    }
}