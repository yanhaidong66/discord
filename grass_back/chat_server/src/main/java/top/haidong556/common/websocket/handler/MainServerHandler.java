package top.haidong556.common.websocket.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import top.haidong556.entity.MessagesPackage;

import java.util.logging.Logger;

public class MainServerHandler extends SimpleChannelInboundHandler<MessagesPackage> {
    private static final Logger logger = Logger
            .getLogger(MainServerHandler.class.getName());

    private MessagesPackage handleMessagesPackage(ChannelHandlerContext ctx,
                                       MessagesPackage messagesPackage){
        System.out.println(messagesPackage);
        return messagesPackage;

    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        MessagesPackage returnMessagesPackage = handleMessagesPackage(channelHandlerContext, (MessagesPackage) messagesPackage);
        channelHandlerContext.fireChannelRead(returnMessagesPackage);
    }
}