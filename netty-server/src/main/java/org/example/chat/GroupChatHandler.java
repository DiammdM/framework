package org.example.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * GroupChatHandler
 *
 * @author: Diammd
 * @since: 2024/2/26
 */
public class GroupChatHandler extends SimpleChannelInboundHandler<String> {

  private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

  /**
   * 建立连接，立即执行
   *
   * @param ctx
   * @throws Exception
   */
  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + " 加入聊天\n");
    channelGroup.add(channel);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    Channel channel = ctx.channel();
    channelGroup.writeAndFlush("【客户端】" + channel.remoteAddress() + " 离开了聊天\n");
    System.out.println("chaneLGroup Size:" + channelGroup.size());
  }

  // 处于活动状态
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(ctx.channel().remoteAddress() + "上线了~\n");
  }

  // 处于不活动状态
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(ctx.channel().remoteAddress() + "下线了~\n");
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    Channel currentChannel = ctx.channel();
    for (Channel channel : channelGroup) {
      if (channel != currentChannel)
      {
        channel.writeAndFlush("【客户】：" + currentChannel.remoteAddress() + " 发送了消息:" + msg);
      } else {
        channel.writeAndFlush("【自己】" + currentChannel.remoteAddress() + " 发送了消息:" + msg);
      }
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 关闭通道
    ctx.close();
  }
}
