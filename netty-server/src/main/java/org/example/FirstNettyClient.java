package org.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.concurrent.TimeUnit;

public class FirstNettyClient {
  public static void main(String[] args) throws InterruptedException {
    // 客户端引导器
    Bootstrap bootstrap = new Bootstrap();

    // 配置线程组
    bootstrap.group(new NioEventLoopGroup());

    // 指定IO类型为NIO
    bootstrap.channel(NioSocketChannel.class);

    // 定义处理器
    bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
      @Override
      protected void initChannel(NioSocketChannel ch) throws Exception {
        ch.pipeline().addLast(new StringDecoder());
      }
    });

    // 建立连接
    Channel channel = bootstrap.connect("127.0.0.1", 8081).channel();

    // 发送消息
    while (true) {
      channel.writeAndFlush("hello, world!");
      TimeUnit.SECONDS.sleep(5);
    }
  }
}
