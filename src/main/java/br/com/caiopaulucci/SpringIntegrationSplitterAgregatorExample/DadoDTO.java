package br.com.caiopaulucci.SpringIntegrationSplitterAgregatorExample;

public class DadoDTO {

    private String idGrupo;

    private String dado;

    public DadoDTO() {
    }

    public DadoDTO(String idGrupo, String dado) {
        this.idGrupo = idGrupo;
        this.dado = dado;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return "DadoDTO{" + "idGrupo='" + idGrupo + '\'' + ", dado='" + dado + '\'' + '}';
    }
}
