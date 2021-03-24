package org.example.Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.example.constant.Constants;
import org.example.factory.ZookeeperFactory;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

public class NettyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup  childGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128) // 允许128个排队
                    .childOption(ChannelOption.SO_KEEPALIVE, false) // 不使用心跳机制
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new DelimiterBasedFrameDecoder(56635, Delimiters.lineDelimiter()[0]));
                            sc.pipeline().addLast(new StringDecoder());
                            sc.pipeline().addLast(new IdleStateHandler(60,45,20, TimeUnit.SECONDS));//netty心跳机制实现
                            sc.pipeline().addLast(new SimpleServerHandler());
                            sc.pipeline().addLast(new StringEncoder());
                        }
                    });
            ChannelFuture f = bootstrap.bind(8081).sync();
            CuratorFramework client = ZookeeperFactory.create();
            InetAddress netAddress = InetAddress.getLocalHost();
            int port=8081;
            int weight=1;
            client.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(Constants.SERVER_PATH+"/"
                    +netAddress.getHostAddress()+"#"+port+"#"+weight+"#");
            f.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
