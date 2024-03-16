package top.haidong556.service;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import top.haidong556.ChatServerApplication;


import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

public class NacosService {
    static {
        InputStream resourceAsStream = NacosService.class.getClassLoader().getResourceAsStream("nacos-config.properties");
        Properties properties=new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DATAID=properties.getProperty("dataId");
        GROUP=properties.getProperty("group");
        NACOS_SERVER_ADDR=properties.getProperty("nacos-server-addr");

        Properties nettyConfig=new Properties();
        resourceAsStream = NacosService.class.getClassLoader().getResourceAsStream("netty-config.properties");
        try {
            nettyConfig.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        NETTY_PORT= nettyConfig.getProperty("port");
        NETTY_ADDR = nettyConfig.getProperty("addr");
    }
    private static final String DATAID;
    private static final String NETTY_ADDR;
    private static final String NETTY_PORT;
    private static final String GROUP;
    private static final String NACOS_SERVER_ADDR;
    private volatile ConfigService configService=null;
    private volatile NamingService namingService=null;
    private boolean createNamingService() throws NacosException {
        if(namingService==null){
            synchronized (this){
                namingService = NamingFactory.createNamingService(NACOS_SERVER_ADDR);

            }
        }
        return true;
    }
    private boolean createConfigService() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", NACOS_SERVER_ADDR);
        if(configService==null){
            synchronized (this){
                if(configService==null)
                    configService = NacosFactory.createConfigService(properties);
            }
        }
        return true;
    }


    public boolean register() throws NacosException, IOException {
        createNamingService();

        Instance instance = new Instance();
        instance.setIp(NETTY_ADDR);
        instance.setPort(Integer.parseInt(NETTY_PORT));
        instance.setHealthy(true);
        instance.setServiceName("chat-server");
        instance.setWeight(2.0);


        namingService.registerInstance("chat-server", instance);
        return true;
    }

    public Properties getConfig() throws NacosException, IOException {
        Properties config=new Properties();
        createConfigService();
        String content = configService.getConfig(DATAID, GROUP, 5000);
        StringReader stringReader=new StringReader(content);
        config.load(stringReader);
        return config;
    }

    public boolean disconnect() throws NacosException {
        namingService.deregisterInstance("chat-server",NETTY_ADDR,Integer.parseInt(NETTY_PORT),"DEFAULT");
        return true;
    }



}
