package top.haidong556.common.websocket.handler;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import top.haidong556.common.codec.PackageCodec;
import top.haidong556.entity.MessagesPackage;

public class PackageDecodeHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        ByteBuf payload = frame.content();
        byte[] payloadBytes = new byte[payload.readableBytes()];
        payload.readBytes(payloadBytes);
        MessagesPackage messagePackage = null;
        try {
            messagePackage = PackageCodec.decode(payloadBytes);
        } catch (InvalidProtocolBufferException e) {
            System.out.println("encode erro");
            throw new RuntimeException(e);
        }

        // 将解码后的消息传递给下一个处理器
        ctx.fireChannelRead(messagePackage);


    }
}
