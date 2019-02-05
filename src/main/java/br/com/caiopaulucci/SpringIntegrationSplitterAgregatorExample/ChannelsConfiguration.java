package br.com.caiopaulucci.SpringIntegrationSplitterAgregatorExample;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ChannelsConfiguration {

    @Bean
    @Qualifier("queueInData")
    public MessageChannel queueInData() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("queueInData");
        executor.initialize();
        return new ExecutorChannel(executor);
    }

    @Splitter(inputChannel = "queueInData", outputChannel = "queueSplitterData")
    public List<DadoDTO> splitterQueueData(List<DadoDTO> registros) {
        return registros;
    }

    @Bean
    @Qualifier("queueSplitterData")
    public MessageChannel queueSplitterInData() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("queueSplitterData");
        executor.initialize();
        return new ExecutorChannel(executor);
    }

    @ServiceActivator(inputChannel = "queueSplitterData", outputChannel = "queueProcessData")
    public DadoDTO bridgeQueueProcessData(DadoDTO dado) {
        return dado;
    }

    @Bean
    @Qualifier("queueProcessData")
    public MessageChannel queueProcessData() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("queueProcessData");
        executor.initialize();
        return new ExecutorChannel(executor);
    }

    @ServiceActivator(inputChannel = "queueProcessData", outputChannel = "queueAgregatorData")
    public DadoDTO bridgeQueueAgregatorData(DadoDTO dado) {
        return dado;
    }

    @Bean
    @Qualifier("queueAgregatorData")
    public MessageChannel queueAgregatorData() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("queueAgregatorData");
        executor.initialize();
        return new ExecutorChannel(executor);
    }

    @Aggregator(inputChannel = "queueAgregatorData", sendPartialResultsOnExpiry = "true", outputChannel = "queueProcessFinishData")
    public ProcessamentoDTO aggregatingMethod(List<DadoDTO> items) {
        ProcessamentoDTO proc = new ProcessamentoDTO();
        proc.setDados(items);
        return proc;
    }

    @Bean
    @Qualifier("queueProcessFinishData")
    public MessageChannel queueProcessFinishData() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("queueProcessFinishData");
        executor.initialize();
        return new ExecutorChannel(executor);
    }

    @ServiceActivator(inputChannel = "queueProcessFinishData")
    public void serviceActivatorQueueProcessFinishData(ProcessamentoDTO processamento) {

        System.out.println("Aqui ! : "+processamento.toString());

    }

}
