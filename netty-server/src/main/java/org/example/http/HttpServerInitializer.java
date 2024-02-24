package org.example.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * HttpServerInitializer
 *
 * @author: Diammd
 * @since: 2024/2/24Ø
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel ch) throws Exception {
    // 得到管道
    ChannelPipeline pipeline = ch.pipeline();

    // 假如http编解码器
    pipeline.addLast("httpServerCodec", new HttpServerCodec());

    // 增加一个自定义的处理器
    pipeline.addLast("myHandler", new HttpServerHandler());
  }
}
