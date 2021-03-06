package com.moraes.igor.appsimples.model;

import java.util.List;

public class Empreendimento {
    public int id;
    public String empreendimento;
    public String endereco;

    public double valorMProjetado;
    public int pavimentos;
    public int unidades;
    public double areaTotalTerreno;
    public double areaTerreno;
    public String inicioObras;
    public String fimDasObras;
    public String entragaDasChaves;
    public long duracaoAteMomento;
    public long faltaParaTerminar;
    public int vendidas;
    public int disponiveis;
    public int indisponivel;
    public double vgv;
    public double percentVgv;
    public double vendido;
    public double aVender;
    public double diferencaVgv;
    public double recebido;
    public double aReceber;
    public double recFinanciadoOutros;

    public double valorAReceber;
    public double valorAReceber0A30;
    public double valorAReceber31A60;
    public double valorAReceber61A120;
    public double valorAReceber121;

    public double valorAPagar;
    public double valorAPagar0A30;
    public double valorAPagar31A60;
    public double valorAPagar61A120;
    public double valorAPagar121;

    public double valorFluxoProjetado;
    public double valorFluxoProjetado0A30;
    public double valorFluxoProjetado31A60;
    public double valorFluxoProjetado61A120;
    public double valorFluxoProjetado121;

    public double valorSaldo;

    public List<Unidades> lUnidades;
    public List<SaldoConta> lSaldoConta;
}
