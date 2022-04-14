package br.edu.fag.modelo;

import javax.persistence.*;


@Entity
@Table(name = "Estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String codigo;
    @Column(length = 400, nullable = false)
    private String nome;
    @Column(length = 2, nullable = false)
    private String sigla;


}
