package com.moraes.igor.appsimples.mode;

import java.io.Serializable;

public class Empreendimento implements Serializable {
    public String empreendimento;
    public String endereco;

    public double valorProjeto;
    public String lancamento;
    public String inicioObra;
    public String finalObra;

    public int unidades;
    public int unidadesVendidas;
    public int unidadesDisponivel;
    public int unidadesOutras;

    public double vgv;
    public double vendas;

    public double recebido;
    public double custoOrcado;
    public double custoIncorrido;
    public double resultadoAtual;
    public double saldoDevedor;
}
