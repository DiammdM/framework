package org.example.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * GroupChatClient
 *
 * @author: Diammd
 * @since: 2024/2/26
 */
public class GroupChatClient {
  private String ip;
  private Integer port;

  public GroupChatClient(String ip, Integer port) {
    this.ip = ip;
    this.port = port;
  }

  public void run() {
    NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
    try {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(eventExecutors)
              .channel(NioSocketChannel.class)
              .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  ChannelPipeline pipeline = ch.pipeline();
                  // 向pipeline加入解码器
                  pipeline.addLast("decoder", new StringDecoder());
                  // 向pipeline加入编码器
                  pipeline.addLast("encoder", new StringEncoder());
                  pipeline.addLast("myHandler", new GroupChatHandler());
                }
              });

      ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();

      Channel channel = channelFuture.channel();
      Scanner scanner = new Scanner(System.in);
      while (scanner.hasNext()) {
        String msg = scanner.nextLine();
        channel.writeAndFlush(msg);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      eventExecutors.shutdownGracefully();
    }
  }

  public static void main(String[] args) {
    GroupChatClient groupChatClient = new GroupChatClient("127.0.0.1", 8888);
    groupChatClient.run();
  }
}
