package org.example.client.core;

import io.netty.channel.ChannelFuture;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.example.client.zk.ZookeeperFactory;

import java.util.List;

public class ServerWatcher implements CuratorWatcher {

    @Override
    public void process(WatchedEvent watchedEvent) throws Exception {
        CuratorFramework client = ZookeeperFactory.create();
        String path = watchedEvent.getPath();
        client.getChildren().usingWatcher(this).forPath(path);
        List<String> serverPaths = client.getChildren().forPath(path);
        ChannelManager.realServerPaths.clear();
        for(String serverPath : serverPaths){
            String[] str = serverPath.split("#");
            int weight =Integer.parseInt(str[2]);
            if(weight>0){
                for (int w=0;w<weight;w++){
                    ChannelManager.realServerPaths.add(str[0]+"#"+str[1]);

                }
            }
        }
        ChannelManager.clear();
        for(String realServer:ChannelManager.realServerPaths){
            String[] str = realServer.split("#");
            try {
                ChannelFuture channelFuture = TcpClient.b.connect(str[0],Integer.parseInt(str[1]));
                ChannelManager.add(channelFuture);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


    }
}
