package top.haidong556.common.websocket.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import top.haidong556.entity.MessagesPackage;


public class PackageReturnHandler extends SimpleChannelInboundHandler<byte[]> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] bytes) throws Exception {
        BinaryWebSocketFrame webSocketFrame = new BinaryWebSocketFrame(Unpooled.wrappedBuffer(bytes));
        channelHandlerContext.writeAndFlush(webSocketFrame);
    }
}
