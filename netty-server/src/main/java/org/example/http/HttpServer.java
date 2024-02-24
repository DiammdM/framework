package org.example.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * HttpServer
 *
 * @author: Diammd
 * @since: 2024/2/24
 */
public class HttpServer {
  public static void main(String[] args) {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap bootstrap = new ServerBootstrap();

      bootstrap.group(bossGroup, workerGroup);

      bootstrap.channel(NioServerSocketChannel.class)
              .childHandler(new HttpServerInitializer());

      ChannelFuture channelFuture = bootstrap.bind(8080).sync();

      channelFuture.channel().closeFuture().sync();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
