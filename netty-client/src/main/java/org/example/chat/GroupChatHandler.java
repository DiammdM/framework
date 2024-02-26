package org.example.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * GroupChatHandler
 *
 * @author: Diammd
 * @since: 2024/2/26
 */
public class GroupChatHandler extends SimpleChannelInboundHandler<String> {
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
    System.out.println(msg.trim());
  }
}
