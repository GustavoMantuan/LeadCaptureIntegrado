package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dorga on 28/04/2015.
 */
public class Evento implements Serializable {
    private long _id;
    private String nome;
    private Date data;

    public Evento(long _id, String nome, Date data) {
        this._id = _id;
        this.nome = nome;
        this.data = data;
    }

    public Evento(String nome, Date data) {
        this.nome = nome;
        this.data = data;
    }

    public Evento() {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
