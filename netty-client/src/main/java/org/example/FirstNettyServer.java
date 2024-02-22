package org.example;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Date;

/**
 * FirstNettyServer
 *
 * @author: Diammd
 * @create: 2024/2/22 15:05
 */
public class FirstNettyServer {
  public static void main(String[] args) throws InterruptedException {
    // 创建服务端启动引导器
    ServerBootstrap bootstrap = new ServerBootstrap();

    // 配置线程模型
    bootstrap.group(new NioEventLoopGroup());

    // 指定服务端的线程模型
    bootstrap.channel(NioServerSocketChannel.class);

    // 定义处理器
    bootstrap.childHandler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel ch) throws Exception {

                ch.pipeline().addLast(new StringDecoder());

                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                  @Override
                  public void channelActive(ChannelHandlerContext ctx) throws Exception {
                    System.out.println(ctx.channel() + ",hello world!");
                  }

                  @Override
                  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    System.out.println(new Date() + "," + msg);
                  }
                });
              }
            });

    // 绑定端口
    ChannelFuture channelFuture = bootstrap.bind(8081).sync();

    // 监听关闭事件
    ChannelFuture closeFuture = channelFuture.channel().closeFuture();
    closeFuture.sync();
  }
}
