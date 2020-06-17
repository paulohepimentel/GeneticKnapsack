package Heuristica_Genetica;
/* @author Paulo Henrique */

import EntradaValores.*;
import static Heuristica_Genetica.MetaHeuristica.algoritmoGenetico;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mochila01Genetica {
    
    public static void main(String[] args) throws IOException {
        
        String nomeArq;
        Mochila mochila, solucao;
        List <Item> listaItens;
        float capacidadeMochila, maiorValor=0, menorValor=90000000;
        int tamPopulacao, nExecucoes;
        
        /* Soluções -> Valores */
        List <Float> listaSolucao = new ArrayList<> ();
        float media=0, d=0, desvio=0;
        
        nomeArq = "entrada10.txt";
        tamPopulacao = 10; // Tamanho da popualção igual ao numero de itens
        nExecucoes = 1000; // Algoritmo genético
        
        for(int i=0; i<30; i++){
            mochila = new Mochila();
            listaItens = new ArrayList<>();
            capacidadeMochila = Arquivo.leituraArquivo(nomeArq, mochila, listaItens);
            solucao = algoritmoGenetico(nExecucoes, tamPopulacao, capacidadeMochila, listaItens);
            listaSolucao.add(solucao.getFitness());
            
            if(solucao.getFitness() > maiorValor)
                maiorValor = solucao.getFitness();
            if(solucao.getFitness() < menorValor)
                menorValor = solucao.getFitness();
            
            media = media + solucao.getFitness();
        }
        media = media/30;  // Calculo da media
        for(int i=0; i<30; i++)  // Calculo do desvio padrão
            d = (float) (desvio + (Math.pow((listaSolucao.get(i)-media), 2))/30);
        desvio = (float) Math.sqrt(d);
        
        System.out.println("Solução: Para 10 itens, capacidade da mochila 269");
        System.out.println(listaSolucao.toString());
        System.out.println("Melhor resultado: "+maiorValor);
        System.out.println("Pior resultado: "+menorValor);
        System.out.println("Media: "+media);
        System.out.println("Desvio Padrão: "+desvio);
        System.out.println();
    }
}