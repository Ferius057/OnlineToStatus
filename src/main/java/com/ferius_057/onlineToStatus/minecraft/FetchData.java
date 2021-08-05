package com.ferius_057.onlineToStatus.minecraft;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FetchData {
    private InetSocketAddress host;

    public void setAddress(InetSocketAddress host) {
        this.host = host;
    }

    public int readVarInt(DataInputStream in) throws IOException {
        int i = 0;
        int j = 0;
        while (true) {
            int k = in.readByte();
            i |= (k & 0x7F) << j++ * 7;
            if (j > 5) throw new RuntimeException("VarInt слишком большой");
            if ((k & 0x80) != 128) break;
        }
        return i;
    }

    public void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }

            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }

    public StatusResponse fetchData() throws IOException {
        Socket socket = new Socket();
        OutputStream outputStream;
        DataOutputStream dataOutputStream;
        InputStream inputStream;
        InputStreamReader inputStreamReader;

        int timeout = 8000;
        socket.setSoTimeout(timeout);

        socket.connect(host, timeout);

        outputStream = socket.getOutputStream();
        dataOutputStream = new DataOutputStream(outputStream);

        inputStream = socket.getInputStream();
        inputStreamReader = new InputStreamReader(inputStream);

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream handshake = new DataOutputStream(b);
        handshake.writeByte(0x00);
        writeVarInt(handshake, 4);
        writeVarInt(handshake, this.host.getHostString().length());
        handshake.writeBytes(this.host.getHostString());
        handshake.writeShort(host.getPort());
        writeVarInt(handshake, 1);

        writeVarInt(dataOutputStream, b.size());
        dataOutputStream.write(b.toByteArray());

        dataOutputStream.writeByte(0x01);
        dataOutputStream.writeByte(0x00);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int size = readVarInt(dataInputStream);
        int id = readVarInt(dataInputStream);

        if (id == -1) {
            throw new IOException("Преждевременное окончание потока.");
        }

        if (id != 0x00) {
            throw new IOException("Недопустимый пакет");
        }
        int length = readVarInt(dataInputStream);

        if (length == -1) {
            throw new IOException("Преждевременное окончание потока.");
        }

        if (length == 0) {
            throw new IOException("Недопустимая длина строки.");
        }

        byte[] in = new byte[length];
        dataInputStream.readFully(in);
        String json = new String(in);


        long now = System.currentTimeMillis();
        dataOutputStream.writeByte(0x09);
        dataOutputStream.writeByte(0x01);
        dataOutputStream.writeLong(now);

        readVarInt(dataInputStream);
        id = readVarInt(dataInputStream);

        if (id == -1) {
            throw new IOException("Преждевременное окончание потока.");
        }

        if (id != 0x01) {
            throw new IOException("Недопустимый пакет");
        }

        StatusResponse response = new Gson().fromJson(json, StatusResponse.class);


        dataOutputStream.close();
        outputStream.close();
        inputStreamReader.close();
        inputStream.close();
        socket.close();

        return response;
    }
}