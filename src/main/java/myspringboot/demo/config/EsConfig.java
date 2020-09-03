package myspringboot.demo.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class EsConfig {

    @Value("${custom.es.address}")
    private String es_address;

    @Value("${custom.es.port}")
    private Integer es_port;


    @Bean
    public TransportClient elClient() throws UnknownHostException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").put("client.transport.sniff", true).build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(es_address), es_port));
        return client;
    }

}
