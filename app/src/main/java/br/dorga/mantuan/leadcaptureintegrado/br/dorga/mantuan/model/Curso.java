package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

/**
 * Created by Dorga on 28/04/2015.
 */
public class Curso {
    private long _id;
    private String nome;
    private int aberto;

    public Curso(long _id, String nome, int aberto) {
        this._id = _id;
        this.nome = nome;
        this.aberto = aberto;
    }

    public Curso(String nome, int aberto) {
        this.nome = nome;
        this.aberto = aberto;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int isAberto() {
        return aberto;
    }

    public void setAberto(int aberto) {
        this.aberto = aberto;
    }
}
