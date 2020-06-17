package EntradaValores;
/* @author Paulo Henrique */

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Arquivo {
    
    public static float leituraArquivo(String nome, Mochila mochila,  List <Item> listaItens) throws IOException{
    
        int numItens;
        float capacidadeMaxima;
        Item item;
        String linha;
        String[] valorLido;
        
        /* Variável de leitura */  
        Scanner lerArquivo;
        lerArquivo = new Scanner(new File(nome));
        
        linha = lerArquivo.nextLine(); // Primeira linha: Numero de itens e Capacidade da mochila
        valorLido = linha.split(" ");
        numItens = Integer.parseInt(valorLido[0]);
        capacidadeMaxima = Float.parseFloat(valorLido[1]);
        
        for(int i=0; i<numItens; i++){ // Demais linhas do arquivo: Valores e pesos dos itens
            linha = lerArquivo.nextLine();
            valorLido = linha.split(" ");
            item = new Item((i+1), Float.parseFloat(valorLido[0]), Float.parseFloat(valorLido[1]));
            listaItens.add(item);
        }
        return capacidadeMaxima;
    }
}