package org.example.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * HeartBeatNettyServer
 *
 * @author: Diammd
 * @since: 2024/2/28
 */
public class HeartBeatNettyServer {
  public static void main(String[] args) {
    NioEventLoopGroup boosGroup = new NioEventLoopGroup();
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap bootstrap = new ServerBootstrap();
      bootstrap.group(boosGroup, workerGroup);
      bootstrap.channel(NioServerSocketChannel.class);
      bootstrap.handler(new LoggingHandler(LogLevel.INFO));
      bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
          ChannelPipeline pipeline = ch.pipeline();
          /**
           * IdleStateHandler 是netty提供的处理空闲状态的处理器
           * readerIdleTime：多长时间没有读
           * writerIdleTime：多长时间没有写
           * allIdleTime：多长时间没有读写
           * 当IdleStateEvent触发后，会交给pipeline的下一个处理器去处理，通过调用下一个处理器的UserEventTriggered
           */
          pipeline.addLast(new IdleStateHandler(10,10,10, TimeUnit.SECONDS));
          /**
           * 空闲检测自定义处理器
           */
          pipeline.addLast(new HeartBeatHandler());
        }
      });

      ChannelFuture channelFuture = bootstrap.bind(8888).sync();
      channelFuture.channel().closeFuture().sync();

    } catch (Exception e) {

    } finally {
      boosGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }
}
