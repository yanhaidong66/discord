package top.haidong556;


import com.alibaba.nacos.api.exception.NacosException;
import top.haidong556.service.NacosService;
import top.haidong556.service.WebSocketService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ChatServerApplication {

    public static void main(String[] args) throws IOException, NacosException {

        NacosService nacosService=new NacosService();
        Properties config = nacosService.getConfig();
        boolean register = nacosService.register();

        Properties nettyConfig=new Properties();
        InputStream resourceAsStream = ChatServerApplication.class.getClassLoader().getResourceAsStream("netty-config.properties");
        nettyConfig.load(resourceAsStream);
        int nettyPort= Integer.parseInt(nettyConfig.getProperty("port"));
        WebSocketService socketServer=new WebSocketService();
        socketServer.run(nettyPort);
    }
}
