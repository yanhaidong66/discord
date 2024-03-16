package top.haidong556.common.websocket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import top.haidong556.common.codec.PackageCodec;
import top.haidong556.entity.MessagesPackage;

public class PackageEncodeHandler extends SimpleChannelInboundHandler<MessagesPackage> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessagesPackage messagesPackage) throws Exception {
        byte[] encoded = PackageCodec.encode(messagesPackage);
        System.out.println("encode package:"+encoded);
        channelHandlerContext.fireChannelRead(encoded);
    }
}
