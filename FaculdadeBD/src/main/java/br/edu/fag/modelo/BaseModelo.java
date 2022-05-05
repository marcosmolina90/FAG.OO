package br.edu.fag.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseModelo {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtUpdate;

    private Integer versao;

    @PrePersist
    private void prePersist(){
        if(this.dtCriacao == null){
            this.dtCriacao = new Date();
        }
        if(this.versao == null){
            this.versao = 0;
        }else{
            this.versao++;
        }

        this.dtUpdate = new Date();
    }

    @PreUpdate
    private void preUpdate(){
        if(this.dtCriacao == null){
            this.dtCriacao = new Date();
        }
        if(this.versao == null){
            this.versao = 0;
        }else{
            this.versao++;
        }

        this.dtUpdate = new Date();
    }



}
