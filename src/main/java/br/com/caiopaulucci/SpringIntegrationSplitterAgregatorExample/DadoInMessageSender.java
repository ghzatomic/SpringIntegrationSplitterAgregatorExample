package br.com.caiopaulucci.SpringIntegrationSplitterAgregatorExample;

import java.util.List;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "queueInData")
public interface DadoInMessageSender {

    void enviar(List<DadoDTO> registro);

}