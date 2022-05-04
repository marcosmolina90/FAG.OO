package br.edu.fag.modelo;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseModelo {


    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;

    @PrePersist
    private void prePersist(){
        if(this.dtCriacao == null){
            this.dtCriacao = new Date();
        }
    }



}
