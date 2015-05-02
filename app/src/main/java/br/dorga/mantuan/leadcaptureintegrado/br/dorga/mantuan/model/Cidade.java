package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

/**
 * Created by Dorga on 28/04/2015.
 */
public class Cidade {
    private long _id;
    private String nome;
    private String estado;

    public Cidade(long _id, String nome, String estado) {
        this._id = _id;
        this.nome = nome;
        this.estado = estado;
    }

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
