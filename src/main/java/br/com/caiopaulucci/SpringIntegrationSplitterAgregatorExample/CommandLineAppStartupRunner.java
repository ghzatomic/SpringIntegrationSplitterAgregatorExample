package br.com.caiopaulucci.SpringIntegrationSplitterAgregatorExample;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final DadoInMessageSender dadoInMessageSender;

    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    public CommandLineAppStartupRunner(
        DadoInMessageSender dadoInMessageSender) {this.dadoInMessageSender = dadoInMessageSender;}

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0;i < 2 ; i++) {
            List<DadoDTO> listaDeDados = new ArrayList<>();
            String idGrupo = String.valueOf(i);
            for (int iDado = 0; iDado < 3; iDado++) {
                listaDeDados.add(new DadoDTO(idGrupo, "Dado : "+String.valueOf(iDado)));
            }
            dadoInMessageSender.enviar(listaDeDados);
        }

    }
}