package org.example.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * HeartBeatHandler
 *
 * @author: Diammd
 * @since: 2024/2/28
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

  /**
   * 用户事件触发
   *
   * @param ctx 上下文
   * @param evt 事件
   * @throws Exception
   */
  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent event = (IdleStateEvent) evt;
      String eventType = "";
      switch (event.state()){
        case READER_IDLE:
          eventType = "读空闲";
          break;
        case WRITER_IDLE:
          eventType = "写空闲";
          break;
        case ALL_IDLE:
          eventType = "读写空闲";
          break;
      }
      System.out.println(ctx.channel().remoteAddress() + ":超时事件:" + eventType);
      ctx.close();
    }
  }
}
