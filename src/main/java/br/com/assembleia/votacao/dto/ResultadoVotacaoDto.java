package br.com.assembleia.votacao.dto;

public class ResultadoVotacaoDto {

    private Long idPauta;
    private int sim;
    private int nao;
    private String resultado;

    public ResultadoVotacaoDto(Long idPauta, int sim, int nao, String resultado) {
        this.idPauta = idPauta;
        this.sim = sim;
        this.nao = nao;
        this.resultado = resultado;
    }

    public Long getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(Long idPauta) {
        this.idPauta = idPauta;
    }

    public int getSim() {
        return sim;
    }

    public void setSim(int sim) {
        this.sim = sim;
    }

    public int getNao() {
        return nao;
    }

    public void setNao(int não) {
        this.nao = não;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
