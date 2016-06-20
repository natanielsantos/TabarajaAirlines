/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author MaxVictor
 */
public class Voo {
    
    private int id_voo;
    private Aeronave aeronave;
    private Aeroporto aeroportoPartida;
    private LocalDate dataPartida;
    private LocalTime horaPartida;
    private Aeroporto aeroportoChegada;
    private LocalDate dataChegada;
    private LocalTime horaChegada;
    private int lotacao;
    private double pesoCargaEmbarcada;
    private double precoViagem;
    private int tipoAeronave;

    public Voo() {
    }

    public Voo(int id_voo, Aeronave aeronave, Aeroporto aeroportoPartida, LocalDate dataPartida, LocalTime horaPartida, Aeroporto aeroportoChegada, LocalDate dataChegada, LocalTime horaChegada, int lotacao, double pesoCargaEmbarcada, double precoViagem, int tipoAeronave) {
        this.id_voo = id_voo;
        this.aeronave = aeronave;
        this.aeroportoPartida = aeroportoPartida;
        this.dataPartida = dataPartida;
        this.horaPartida = horaPartida;
        this.aeroportoChegada = aeroportoChegada;
        this.dataChegada = dataChegada;
        this.horaChegada = horaChegada;
        this.lotacao = lotacao;
        this.pesoCargaEmbarcada = pesoCargaEmbarcada;
        this.precoViagem = precoViagem;
        this.tipoAeronave = tipoAeronave;
    }

    public Aeronave getAeronave() {
        return aeronave;
    }

    public void setAeronave(Aeronave aeronave) {
        this.aeronave = aeronave;
    }

    public Aeroporto getAeroportoPartida() {
        return aeroportoPartida;
    }

    public void setAeroportoPartida(Aeroporto aeroportoPartida) {
        this.aeroportoPartida = aeroportoPartida;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    public Aeroporto getAeroportoChegada() {
        return aeroportoChegada;
    }

    public void setAeroportoChegada(Aeroporto aeroportoChegada) {
        this.aeroportoChegada = aeroportoChegada;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public LocalTime getHoraChegada() {
        return horaChegada;
    }

    public void setHoraChegada(LocalTime horaChegada) {
        this.horaChegada = horaChegada;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public double getPesoCargaEmbarcada() {
        return pesoCargaEmbarcada;
    }

    public void setPesoCargaEmbarcada(double pesoCargaEmbarcada) {
        this.pesoCargaEmbarcada = pesoCargaEmbarcada;
    }

    public double getPrecoViagem() {
        return precoViagem;
    }

    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }

    public int getId_voo() {
        return id_voo;
    }

    public void setId_voo(int id_voo) {
        this.id_voo = id_voo;
    }

    public int getTipoAeronave() {
        return tipoAeronave;
    }

    public void setTipoAeronave(int tipoAeronave) {
        this.tipoAeronave = tipoAeronave;
    }
    
    
    /*public void consultar(){
        System.out.println("=== Aeronave ===");
        aeronave.consultar();
        System.out.println("=== Aeroporto Partida ===");
        aeroportoPartida.consultar();
        System.out.println("Data Partida: " + dataPartida);
        System.out.println("Hora Partida: " + horaPartida);
        System.out.println("=== Aeroporto Chegada ===");
        aeroportoChegada.consultar();
        System.out.println("Data Chegada: " + dataChegada);
        System.out.println("Hora Chegada: " + horaChegada);
        System.out.println("Lotação: " + lotacao);
        System.out.println("Peso Carga Embarcada: " + pesoCargaEmbarcada);
        System.out.println("Preço da Viagem: " + precoViagem);
    }*/
    
    public void consultar(){
        System.out.print("Aeronave: ");
        System.out.println(aeronave.getIdentificacao());
        System.out.println("Aeroporto Partida: " + aeroportoPartida.getIdentificacao());
        System.out.println("Data Partida: " + dataPartida);
        System.out.println("Hora Partida: " + horaPartida);
        System.out.println("Aeroporto Chegada: " + aeroportoChegada.getIdentificacao());
        System.out.println("Data Chegada: " + dataChegada);
        System.out.println("Hora Chegada: " + horaChegada);
        System.out.println("Lotação: " + lotacao);
        System.out.println("Peso Carga Embarcada: " + pesoCargaEmbarcada);
        System.out.println("Preço da Viagem: " + precoViagem);
    }
}
