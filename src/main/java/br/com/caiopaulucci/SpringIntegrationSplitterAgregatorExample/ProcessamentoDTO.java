package br.com.caiopaulucci.SpringIntegrationSplitterAgregatorExample;

import java.util.List;

public class ProcessamentoDTO {

    private List<DadoDTO> dados;

    public List<DadoDTO> getDados() {
        return dados;
    }

    public void setDados(List<DadoDTO> dados) {
        this.dados = dados;
    }

    @Override
    public String toString() {
        return "ProcessamentoDTO{" + "dados=" + dados + '}';
    }
}
