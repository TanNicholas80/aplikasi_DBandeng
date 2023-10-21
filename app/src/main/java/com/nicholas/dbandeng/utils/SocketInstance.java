package com.nicholas.dbandeng.utils;

import android.util.Log;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class SocketInstance {
    Socket mSocket;
    public SocketInstance() {
        try {
            mSocket = IO.socket("https://dbandeng.luthficode.my.id");
            Log.d("socket", "success connected to socket");
        } catch (URISyntaxException e) {
            Log.d("socket", e.getMessage());
        }
    }

    public Socket getmSocket(){
        return mSocket;
    }
}
