package com.zubak.demo.config;

import com.zubak.demo.soap.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.zubak.demo.wsdl");
        return marshaller;
    }

//    @Bean
//    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
//        WebServiceTemplate template = new WebServiceTemplate();
//        template.setMarshaller(marshaller);
//        template.setUnmarshaller(marshaller);
//        template.setDefaultUri("http://localhost:8080/ws");
//        return template;
//    }

    @Bean
    public SoapClient soapClient(Jaxb2Marshaller marshaller) {
        SoapClient client = new SoapClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
