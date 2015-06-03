package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Dorga on 28/04/2015.
 */
public class Lead implements Serializable{
    private long _id;
    private String nome;
    private String email;
    private String telefoneRes;
    private String telefoneCes;
    private Date nascimento;
    private String endereco;
    private String numero;
    private String bairro;
    private int fies; // 0 para Nao 1 para Sim
    private int prouni; // 0 para Nao 1 para Sim
    private Double percProuni; // 50% 100%;
    private String facebook;
    private int enem; //0 para nao 1 para sim
    private Double notaEnem;
    private Date dataLead;
    private Cidade cidade;
    private Curso curso1;
    private Curso curso2;
    private Evento evento;

    public Lead(long _id, String nome, String email, String telefoneRes, String telefoneCes, Date nascimento, String endereco, String numero, String bairro, int fies, int prouni, Double percProuni, String facebook, int enem, Double notaEnem, Date dataLead, Cidade cidade, Curso curso1, Curso curso2, Evento evento) {
        this._id = _id;
        this.nome = nome;
        this.email = email;
        this.telefoneRes = telefoneRes;
        this.telefoneCes = telefoneCes;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.fies = fies;
        this.prouni = prouni;
        this.percProuni = percProuni;
        this.facebook = facebook;
        this.enem = enem;
        this.notaEnem = notaEnem;
        this.dataLead = dataLead;
        this.cidade = cidade;
        this.curso1 = curso1;
        this.curso2 = curso2;
        this.evento = evento;
    }

    public Lead(String nome, String email, String telefoneRes, String telefoneCes, Date nascimento, String endereco, String numero, String bairro, int fies, int prouni, Double percProuni, String facebook, int enem, Double notaEnem, Date dataLead, Cidade cidade, Curso curso1, Curso curso2, Evento evento) {
        this.nome = nome;
        this.email = email;
        this.telefoneRes = telefoneRes;
        this.telefoneCes = telefoneCes;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.fies = fies;
        this.prouni = prouni;
        this.percProuni = percProuni;
        this.facebook = facebook;
        this.enem = enem;
        this.notaEnem = notaEnem;
        this.dataLead = dataLead;
        this.cidade = cidade;
        this.curso1 = curso1;
        this.curso2 = curso2;
        this.evento = evento;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneRes() {
        return telefoneRes;
    }

    public void setTelefoneRes(String telefoneRes) {
        this.telefoneRes = telefoneRes;
    }

    public String getTelefoneCes() {
        return telefoneCes;
    }

    public void setTelefoneCes(String telefoneCes) {
        this.telefoneCes = telefoneCes;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getFies() {
        return fies;
    }

    public void setFies(int fies) {
        this.fies = fies;
    }

    public int getProuni() {
        return prouni;
    }

    public void setProuni(int prouni) {
        this.prouni = prouni;
    }

    public Double getPercProuni() {
        return percProuni;
    }

    public void setPercProuni(Double percProuni) {
        this.percProuni = percProuni;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getEnem() {
        return enem;
    }

    public void setEnem(int enem) {
        this.enem = enem;
    }

    public Double getNotaEnem() {
        return notaEnem;
    }

    public void setNotaEnem(Double notaEnem) {
        this.notaEnem = notaEnem;
    }

    public Date getDataLead() {
        return dataLead;
    }

    public void setDataLead(Date dataLead) {
        this.dataLead = dataLead;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Curso getCurso1() {
        return curso1;
    }

    public void setCurso1(Curso curso1) {
        this.curso1 = curso1;
    }

    public Curso getCurso2() {
        return curso2;
    }

    public void setCurso2(Curso curso2) {
        this.curso2 = curso2;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
