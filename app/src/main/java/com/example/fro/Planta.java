package com.example.fro;

import java.util.Arrays;

public class Planta {
    /*!< Identificadores da planta */
    private String nome; /*E o nome bonito da planta que sera mostrado, tipo "Sambaia" ou "Palmeira Rafia"*/

    /*!< Informacoes sobre cuidados da planta */
    private String frequenciaDeRegamento;
    private String localAdequadoParaPlantio; /*!< Se deve ficar mais no sol ou nem tanto */
    private String poda;
    private String fertilizantesRecomendados;
    private String pragasComuns;

    /*!< Informacoes sobre a planta */
    private String altura;
    private String tempoDeVida;
    private String preco;
    private String outrasInformacoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFrequenciaDeRegamento() {
        return frequenciaDeRegamento;
    }

    public void setFrequenciaDeRegamento(String frequenciaDeRegamento) {
        this.frequenciaDeRegamento = frequenciaDeRegamento;
    }

    public String getLocalAdequadoParaPlantio() {
        return localAdequadoParaPlantio;
    }

    public void setLocalAdequadoParaPlantio(String localAdequadoParaPlantio) {
        this.localAdequadoParaPlantio = localAdequadoParaPlantio;
    }

    public String getPragasComuns() {
        return pragasComuns;
    }

    public void setPragasComuns(String pragasComuns) {
        this.pragasComuns = pragasComuns;
    }

    public String getFertilizantesRecomendados() {
        return fertilizantesRecomendados;
    }

    public void setFertilizantesRecomendados(String fertilizantesRecomendados) {
        this.fertilizantesRecomendados = fertilizantesRecomendados;
    }

    public String getOutrasInformacoes() {
        return outrasInformacoes;
    }

    public void setOutrasInformacoes(String outrasInformacoes) {
        this.outrasInformacoes = outrasInformacoes;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTempoDeVida() {
        return tempoDeVida;
    }

    public void setTempoDeVida(String tempoDeVida) {
        this.tempoDeVida = tempoDeVida;
    }

    public String getPoda() {
        return poda;
    }

    public void setPoda(String poda) {
        this.poda = poda;
    }

    @Override
    public String toString() {
        String informacoesPlanta = "";
        if(frequenciaDeRegamento != null) informacoesPlanta += "- Frequência de regamento: " + frequenciaDeRegamento + "\n";
        if(localAdequadoParaPlantio != null) informacoesPlanta += "- Local para plantio: " + localAdequadoParaPlantio + "\n";
        if(altura != null) informacoesPlanta += "- Altura: " + altura + "\n";
        if(tempoDeVida != null) informacoesPlanta += "- Tempo de vida: " + tempoDeVida + "\n";
        if(poda != null) informacoesPlanta += "- Poda: " + poda + "\n";
        if(fertilizantesRecomendados != null) informacoesPlanta += "- Fertilizantes: " + fertilizantesRecomendados + "\n";
        if(pragasComuns != null) informacoesPlanta += "- Pragas: " + pragasComuns + "\n";
        if(preco != null) informacoesPlanta += "- Preço: " + preco + "\n";
        if(outrasInformacoes != null) informacoesPlanta += "\n- Outras informações: " + outrasInformacoes + "\n";
        return informacoesPlanta;
    }
}


