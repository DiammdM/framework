package org.example.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 自定义handler处理器
 *
 * @author: Diammd
 * @since: 2024/2/24
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

  /**
   * 读取客户端数据
   *
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

    // 判断请求类型
    if (msg instanceof HttpRequest) {
      System.out.println("msg 类型：" + msg.getClass());
      System.out.println("客户端地址：" + ctx.channel().remoteAddress());

      // 过滤特定资源
      URI uri = new URI(((HttpRequest) msg).uri());
      if("/favicon.ico".equals(uri.getPath())){
        return;
      }

      // 回复信息给浏览器【http协议】
      ByteBuf byteBuf = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);

      // 构造一个http的相应
      FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
              HttpResponseStatus.OK, byteBuf);
      response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
      response.headers().set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

      // 将response返回
      ctx.writeAndFlush(response);
    }
  }
}
