<h1 align="center">
    <img alt="Logo" title="Knapsack" src="images/Knapsack.svg" width="300px" />
</h1>

<p align="center">
  <a href="#projeto">Projeto</a>
  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#motivação">Motivação</a>
  &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#estrutura-da-entrada">Arquitetura</a>
</p>

## ✦ Projeto
<p align="justify">O trabalho teve como objetivo a implementação prática de uma meta-heurística baseada em algoritmos genéticos, aplicada ao famoso problema da Mochila 0/1.</p>

## ✦ Motivação
<p align="justify">O problema da Mochila 0/1 (0/1 Knapsack Problem) possui as algumas características interessantes, nesse famoso problema cada ítem pode ser escolhido no máximo uma vez. O objetivo é escolher a melhor combinação possível, ou seja, a que alcance o maior somatório de valor dos itens</p>

<p align="justify">O funcionamento de algoritmos genéticos consiste em tratar as possíveis soluções de um problema como "indivíduos" de uma "população", que irá "evoluir" a cada iteração ou "geração". Para isso é necessário construir um modelo de evolução onde os indivíduos sejam soluções de um problema. Os passos do algoritmo são os seguintes:</p>

- Representação das possíveis soluções do problema no formato de um código genético;
- População inicial que contenha uma diversidade de modo a permitir ao algoritmo combinar características e produzir novas soluções;
- Existência de um método para medir a qualidade de uma solução potencial;
- Um procedimento de combinação de soluções para gerar novos indivíduos na população;
- Um critério de escolha das soluções que permanecerão na população ou que serão retirados desta;
- Um procedimento para introduzir periodicamente alterações em algumas soluções da população. Desse modo mantém-se a diversidade da população e a possibilidade de se produzir soluções inovadoras para serem avaliadas pelo critério de seleção dos mais aptos

<p align="justify"><b>O algoritmo é executado enquanto houver uma melhora da função objetivo (alcançar a melhor combinação de itens). Foi definido como critério de parada do algoritmo o encerramento após três iterações sem melhora de resultados.</b></p>

## ✦ Estrutura da entrada
<p align="justify">A estrutura do arquivo de entrada é a seguinte:</p>

- Primeira linha: Número de itens | Capacidade da mochila
- Demais linhas do arquivo: Valores | pesos dos itens

---

<p align="justify">O projeto foi desenvolvido, para fins didáticos, durante a disciplina de Meta-heurística do curso de Bacharelado em Ciência da Computação da UFV – Campus Florestal</p>
