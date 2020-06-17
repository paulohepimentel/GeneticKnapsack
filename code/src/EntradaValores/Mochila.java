package EntradaValores;
/* @author Paulo Henrique */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Mochila {
    
    private int id;
    private float fitness;
    private float capacidadeMaxima;
    private float capacidadeUtilizada;
    private int numeroItens;
    private final List <Item> listaItens;

    public Mochila() {
        this.id = 0;
        this.fitness = 0;
        this.capacidadeMaxima = 0;
        this.capacidadeUtilizada = 0;
        this.numeroItens = 0;
        this.listaItens = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public float getFitness() {
        return fitness;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    
    public float getCapacidadeMaxima() {
        return capacidadeMaxima;
    }
    
    public void setCapacidadeMaxima(float capacidadeMáxima) {
        this.capacidadeMaxima = capacidadeMáxima;
    }

    public float getCapacidadeUtilizada() {
        return capacidadeUtilizada;
    }
    
    public void setCapacidadeUtilizada(float capacidadeUtilizada) {
        this.capacidadeUtilizada = capacidadeUtilizada;
    }
    
    public int getNumeroItens() {
        return numeroItens;
    }
    
    public void setNumeroItens(int numeroItens) {
        this.numeroItens = numeroItens;
    }
    
    public List<Item> getListaItens() {
        return listaItens;
    }
    
    public void setListaItens(List <Item> listItem) {
       listaItens.addAll(listItem);
    }
    
    public void setItem(Item item) {
        listaItens.add(item);
        listaItens.sort(Comparator.comparing(Item::getValor).reversed());
    }
    
    public void removeItem(Item item){
        listaItens.remove(item);
    }
}