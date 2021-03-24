package org.example.client.core;

import io.netty.channel.ChannelFuture;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ChannelManager {
    // 加权轮询时改为list,之前为set
    static CopyOnWriteArrayList<String> realServerPaths = new CopyOnWriteArrayList<>();
    static AtomicInteger position = new AtomicInteger(0);

    public static CopyOnWriteArrayList<ChannelFuture> channelFutures = new CopyOnWriteArrayList<>();

    public static void removeChannel(ChannelFuture channel){
        channelFutures.remove(channel);
    }

    public static void add(ChannelFuture channel){
        channelFutures.add(channel);
    }

    public static void clear(){
        channelFutures.clear();
    }

    // 轮询获取
    public static ChannelFuture get(AtomicInteger i){
        int size = channelFutures.size();
        ChannelFuture channel = null;
        if(i.get()>size){
            channel = channelFutures.get(0);
            position.set(1);
        }
        else{
            channel = channelFutures.get(i.getAndIncrement());
        }
        return channel;
    }
}
