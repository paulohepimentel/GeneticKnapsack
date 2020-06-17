package Heuristica_Genetica;
/* @author Paulo Henrique */

import EntradaValores.Mochila;
import EntradaValores.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MetaHeuristica {

    /* INDIVÍDUO: Gera uma mochila aleatória */
    public static void geraIndividuoAleatorio(Mochila mochila, List <Item> listaItens){
        
        Random random = new Random();
        
        for(int i=0; i<listaItens.size(); i++) {
            if(random.nextInt(100)%2 == 0){
                if((mochila.getCapacidadeUtilizada()+listaItens.get(i).getPeso()) <= (mochila.getCapacidadeMaxima())){
                    mochila.setItem(listaItens.get(i)); // Acrescenta item i a mochila
                    mochila.setNumeroItens((mochila.getNumeroItens()+1));
                    mochila.setCapacidadeUtilizada((listaItens.get(i).getPeso() + mochila.getCapacidadeUtilizada()));
                    mochila.setFitness((listaItens.get(i).getValor() + mochila.getFitness()));
                }
            }
        }
        calculaFitness(mochila);
    }
    
    /* FITNESS: Caso a capacidade seja ultrapassada, o fitness se torna negativo */
    public static void calculaFitness(Mochila mochila){
        
        if(mochila.getCapacidadeUtilizada() > mochila.getCapacidadeMaxima()){
            if(mochila.getFitness() > 0)
                mochila.setFitness(mochila.getFitness()*(-1));
        }
    }
    
    /* INDIVÍDUO: Imprime o conteudo da mochila e seus valores*/
    public static void imprimeIndividuo(Mochila mochila){
     
        System.out.println("Mochila ID: "+mochila.getId());
        System.out.println("-> Capacidade maxima da mochila: "+mochila.getCapacidadeMaxima());
        System.out.println("-> Capacidade utilizada: "+mochila.getCapacidadeUtilizada());
        System.out.println("-> Numero de itens na mochila: "+mochila.getNumeroItens());
        System.out.print("-> Lista dos itens que estão na mochila: ");
        System.out.print("[");
        for(int i=0; i<mochila.getListaItens().size(); i++){
            if(i == (mochila.getListaItens().size()-1))
                System.out.print(mochila.getListaItens().get(i).getId());
            else
                System.out.print(mochila.getListaItens().get(i).getId()+", ");
        }
        System.out.print("]");
        System.out.println("\n-> Fitness do indivíduo: "+mochila.getFitness());
        System.out.println();
    }
        
    /* POPULAÇÃO: Método que gera uma população aleatória de n individuos(mochilas) */
    public static List <Mochila> geraPopulacao(int n, float capacidadeMochila, List <Item> listaItens){
       
        List <Mochila> populacao = new ArrayList<>(); // População - Várias mochilas
        
        /* Geração de uma população com n individuos */
        for(int i=0; i<n; i++){
            populacao.add(new Mochila());
            populacao.get(i).setId(i);
            populacao.get(i).setCapacidadeMaxima(capacidadeMochila);
            geraIndividuoAleatorio(populacao.get(i), listaItens);
        }
        return populacao;
    }
    
    /* POPULAÇÃO: Método que imprime a população de indivíduos */
    public static void imprimePopulacao(List <Mochila> populacao){
        
        System.out.println("----- População -> Inicio");
        for(int i=0; i<populacao.size();i++)
            imprimeIndividuo(populacao.get(i));
        System.out.println("----- População -> Fim");
    }
    
    /* SELEÇÃO: Método responsável por definir os vencedores do torneio binário */
    public static List <Mochila> selecaoPopulacao(List <Mochila> populacao){
        
        List <Mochila> vencedores = new ArrayList<>(); // Vencedores do torneio
        Mochila competidor1, competidor2;
        Random random = new Random();
        
        /* O numero de chaves do torneio é igual ao tamanho da população */
        for(int i=0; i<(populacao.size()/2); i++){
            /* Define aleatóriamente as chaves do torneio */
            competidor1 = populacao.get(random.nextInt(populacao.size()));     
            competidor2 = populacao.get(random.nextInt(populacao.size()));
            if(competidor1.getFitness() > competidor2.getFitness()){
                if(!vencedores.contains(competidor1))  /* Não há repetição na lista */
                    vencedores.add(competidor1);
            }
            else{
                if(!vencedores.contains(competidor2))  /* Não há repetição na lista */
                    vencedores.add(competidor2);
            }
        }
        return vencedores;
    }
    
    /* CRUZAMENTO: Método que atribui ao filho dados do pai */
    public static void individuoCruzado(Mochila pai1, Mochila pai2, Mochila filho, List <Mochila> novaGeracao){
        
        filho.setId(novaGeracao.size()+1);
        filho.setCapacidadeMaxima(pai1.getCapacidadeMaxima());
    }
    
    /* CRUZAMENTO: Método que realiza o cruzamento em uma população descartando os pais */
    public static List <Mochila> cruzamentoPopulacao(List <Mochila> populacao, List <Item> listaItens){
        
        int i=0;
        Mochila pai1, pai2, filho;
        List <Mochila> novaGeracao = new ArrayList<>(); // População após o cruzamento
        
        while((i+1) < populacao.size()){
            if(populacao.size() > 1){
                pai1 = populacao.get(i);
                pai2 = populacao.get(i+1);
                filho = new Mochila();
                individuoCruzado(pai1, pai2, filho, novaGeracao);
        
                // Filho herda itens alternados
                for(int j=0; j<listaItens.size(); j++){
                    if(j%2==0){ // Herda item do pai 1
                        if(j < pai1.getListaItens().size()){
                            //Não insere itens repetidos
                            if(!filho.getListaItens().contains(pai1.getListaItens().get(j)))
                                filho.setItem(pai1.getListaItens().get(j));
                        }
                    }
                    else{ // Herda item do pai 2
                        if(j < pai2.getListaItens().size()){
                            // Não insere itens repetidos
                            if(!filho.getListaItens().contains(pai2.getListaItens().get(j)))
                                filho.setItem(pai2.getListaItens().get(j));
                        }
                    }
                }
                filho.setNumeroItens(filho.getListaItens().size());
                filho.setCapacidadeUtilizada(0);
                filho.setFitness(0);
                //Percorre lista de itens e os define na mochila filho
                for(int j=0; j<filho.getNumeroItens(); j++){
                    filho.setFitness(filho.getListaItens().get(j).getValor() + filho.getFitness());
                    filho.setCapacidadeUtilizada((filho.getListaItens().get(j).getPeso() + filho.getCapacidadeUtilizada()));
                }
                calculaFitness(filho);
                novaGeracao.add(filho); //Filho adicionado a nova geração
            }
            else{ //População formada por um indivíduo só -> Filho é um clone do pai
                pai1 = populacao.get(i);
                novaGeracao.add(pai1);
            }
            i=i+2; //Dois pais para o cruzamento
        }
        return novaGeracao;
    }

    /* MUTAÇÃO: Escolha de n indivíduos para inversão de um item aleatório*/
    public static void mutacaoPopulacao(List <Mochila> populacao, List <Item> listaItens){
        
        int itemMudado;
        Random random = new Random();
        
        for(int i=0; i<populacao.size(); i++){
            if(random.nextInt(100)%2 == 0){
                itemMudado = random.nextInt(populacao.get(i).getListaItens().size());
                fazMutacao(populacao.get(i), listaItens.get(itemMudado));
            }
        }
    }
    
    /* MUTAÇÃO: Acrescenta ou remove o item na mochila */
    public static void fazMutacao(Mochila mochila, Item item){
        
        if(mochila.getListaItens().contains(item)){ /* Item está na mochila -> Deve ser removido */
            mochila.removeItem(item);
            mochila.setNumeroItens(mochila.getNumeroItens() - 1);
            mochila.setCapacidadeUtilizada(mochila.getCapacidadeUtilizada() - item.getPeso());
            mochila.setFitness(mochila.getFitness() - item.getValor());
        }
        else{ /* Item não está na mochila -> Deve ser acrescentado */
            mochila.setItem(item);
            mochila.setNumeroItens(mochila.getNumeroItens() + 1);
            mochila.setCapacidadeUtilizada(mochila.getCapacidadeUtilizada() + item.getPeso());
            mochila.setFitness(mochila.getFitness() + item.getValor());
        }
        calculaFitness(mochila);
    }
    
    /* CRITÉRIO DE PARADA: Método que retorna o melhor indivíduo da população */
    public static Mochila melhorIndividuo(List <Mochila> populacao, List<Item> listaItens){
        
        Mochila mochila;
        
        if(populacao.isEmpty())
            mochila = new Mochila();
        else
            mochila = populacao.get(0);
        for(int i=0; i<populacao.size(); i++){
            if(mochila.getFitness() < populacao.get(i).getFitness())
                mochila = populacao.get(i);
        }
        /* Tentativa de roubo -> Se tem espeço, acrescenta o item */
        for(int i=0; i<listaItens.size(); i++){
            if(!mochila.getListaItens().contains(listaItens.get(i))){
                if((mochila.getCapacidadeUtilizada()+listaItens.get(i).getPeso()) <= (mochila.getCapacidadeMaxima())){
                    mochila.setItem(listaItens.get(i)); // Acrescenta item i a mochila
                    mochila.setNumeroItens((mochila.getNumeroItens()+1));
                    mochila.setCapacidadeUtilizada((listaItens.get(i).getPeso() + mochila.getCapacidadeUtilizada()));
                    mochila.setFitness((listaItens.get(i).getValor() + mochila.getFitness()));
                }
            }
        }
        calculaFitness(mochila);
        return mochila;
    }
    
    /* Algoritmo Genético para o problema da mochila */
    public static Mochila algoritmoGenetico(int nExecucoes, int tamPopulacao, float capacidadeMochila, List<Item> listaItens){
        
        Mochila mochila, melhorGlobal = new Mochila();
        List <Mochila> populacaoInicial, vencedoresTorneio, novaGeracao;
        
        for(int i=0; i<nExecucoes; i++){
            populacaoInicial = geraPopulacao(tamPopulacao, capacidadeMochila, listaItens);  /* Geração inicial */
            vencedoresTorneio = selecaoPopulacao(populacaoInicial);  /* Seleção */
            novaGeracao = cruzamentoPopulacao(vencedoresTorneio, listaItens);  /* Cruzamento */
            mutacaoPopulacao(novaGeracao, listaItens);  /* Mutação */
            mochila = melhorIndividuo(novaGeracao, listaItens);  /* Critério de parada - Melhor indivíduo */
            if(mochila.getFitness() > melhorGlobal.getFitness())
                melhorGlobal = mochila;
        }
        //imprimeIndividuo(melhorGlobal);
        return melhorGlobal;
    }
}