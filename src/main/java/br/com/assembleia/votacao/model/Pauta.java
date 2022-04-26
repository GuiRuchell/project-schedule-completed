package br.com.assembleia.votacao.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Date prazo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;

    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;

    }

    public Date getPrazo() {
        return prazo;

    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;

    }
}
