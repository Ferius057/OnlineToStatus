package com.ferius_057.onlineToStatus.minecraft;

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
            System.err.println("X | Неудалось получить онлайн. " + e.getMessage());
            System.exit(0);
        }
        assert statusResponse != null;
        return new int[] {statusResponse.getPlayers().getOnline(), statusResponse.getPlayers().getMax()};
    }
}