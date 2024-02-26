package org.example.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * GroupChatServer
 *
 * @author: Diammd
 * @since: 2024/2/26
 */
public class GroupChatServer {
  private int port;

  public GroupChatServer(int port) {
    this.port = port;
  }

  public void run() {
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(boosGroup, workerGroup);
      bootstrap.channel(NioServerSocketChannel.class);
      bootstrap.option(ChannelOption.SO_BACKLOG, 128)
              .childOption(ChannelOption.SO_KEEPALIVE, true)
              .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  ChannelPipeline pipeline = ch.pipeline();
                  // 向pipeline加入解码器
                  pipeline.addLast("decoder", new StringDecoder());
                  // 向pipeline加入编码器
                  pipeline.addLast("encoder", new StringEncoder());
                  pipeline.addLast(new GroupChatHandler());
                }
              });

      ChannelFuture channelFuture = bootstrap.bind(port).sync();
      // 关闭监听
      channelFuture.channel().closeFuture().sync();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }finally {
      boosGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    GroupChatServer groupChatServer = new GroupChatServer(8888);
    groupChatServer.run();
  }
}
