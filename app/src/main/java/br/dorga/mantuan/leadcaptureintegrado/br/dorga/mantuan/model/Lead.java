package br.dorga.mantuan.leadcaptureintegrado.br.dorga.mantuan.model;

import java.util.Date;

/**
 * Created by Dorga on 28/04/2015.
 */
public class Lead {
    private long _id;
    private String nome;
    private String email;
    private String telefoneRes;
    private String telefoneCes;
    private Date nascimento;
    private Date dataLead;
    private String endereco;
    private String numero;
    private String bairro;
    private String facebook;
    private int cidade;
    private int evento;
    private int fies; // 0 para Nao 1 para Sim
    private int prouni;
    private Double percProuni; // 50% 100%;
    private int enem; //0 para nao 1 para sim
    private int curso1;
    private int curso2;
    private Double notaEnem;


    public Lead(long _id, String nome, String email, String telefoneRes, String telefoneCes, Date nascimento, Date dataLead, String endereco, String numero, String bairro, String facebook, int cidade, int evento, int fies, int prouni, Double percProuni, int enem, int curso1, int curso2, Double notaEnem) {
        this._id = _id;
        this.nome = nome;
        this.email = email;
        this.telefoneRes = telefoneRes;
        this.telefoneCes = telefoneCes;
        this.nascimento = nascimento;
        this.dataLead = dataLead;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.facebook = facebook;
        this.cidade = cidade;
        this.evento = evento;
        this.fies = fies;
        this.prouni = prouni;
        this.percProuni = percProuni;
        this.enem = enem;
        this.curso1 = curso1;
        this.curso2 = curso2;
        this.notaEnem = notaEnem;
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

    public String getTelefonecel() {
        return telefoneRes;
    }

    public void setTelefonecel(String telefoneRes) {
        this.telefoneRes = telefoneRes;
    }

    public String getTelefonetel() {
        return telefoneCes;
    }

    public void setTelefonetel(String telefoneCes) {
        this.telefoneCes = telefoneCes;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getDataLead() {
        return dataLead;
    }

    public void setDataLead(Date dataLead) {
        this.dataLead = dataLead;
    }

    public String getLougradouro() {
        return endereco;
    }

    public void setLougradouro(String endereco) {
        this.endereco = endereco;
    }

    public String getNumeroEnd() {
        return numero;
    }

    public void setNumeroEnd(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
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

    public int getEnem() {
        return enem;
    }

    public void setEnem(int enem) {
        this.enem = enem;
    }

    public int getCurso1() {
        return curso1;
    }

    public void setCurso1(int curso1) {
        this.curso1 = curso1;
    }

    public int getCurso2() {
        return curso2;
    }

    public void setCurso2(int curso2) {
        this.curso2 = curso2;
    }

    public Double getNotaEnem() {
        return notaEnem;
    }

    public void setNotaEnem(Double notaEnem) {
        this.notaEnem = notaEnem;
    }
}
