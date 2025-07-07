
# Simulador de Jogo de Tabuleiro - Estrutura de Dados

## 🎯 Descrição Geral e Objetivo do Projeto

Este projeto simula um jogo de tabuleiro estratégico em Java, inspirado no estilo do jogo "Banco Imobiliário".  
Foi desenvolvido como avaliação prática da disciplina de Estrutura de Dados com o objetivo de aplicar conceitos fundamentais como:

- Lista Ligada Circular
- Pilha
- Árvore Binária de Busca (BST)
- Listas Encadeadas para gerenciamento dinâmico de jogadores e imóveis

O jogo inclui funcionalidades de movimentação de jogadores, compra e aluguel de imóveis, cobrança de impostos e restituições, negociações entre jogadores, hipoteca de propriedades, além de um ranking dinâmico por patrimônio.

---

## 🧠 Estruturas de Dados Utilizadas

### ✅ Lista Ligada Circular
- **Uso:** Representa o tabuleiro do jogo.
- **Justificativa:** Permite a movimentação contínua dos jogadores em um ciclo infinito, ideal para simular voltas completas no tabuleiro e controlar a lógica de passagem pelo ponto de partida.

### ✅ Lista (ArrayList)
- **Uso:** Armazena os jogadores e os imóveis cadastrados.
- **Justificativa:** Facilidade para adicionar, remover e acessar elementos dinamicamente.

### ✅ Árvore Binária de Busca (BST)
- **Uso:** Implementa o sistema de ranking dos jogadores com base no patrimônio.
- **Justificativa:** Facilita a ordenação e a consulta eficiente da classificação em tempo real, com inserções e percursos otimizados.

---

## 🛠️ Instruções para Compilar e Executar

### ✅ Requisitos:
- Java JDK 11 ou superior instalado
- Um editor como IntelliJ

### 📂 Estrutura de Arquivos Esperada:
```
/ProjetoJogoTabuleiro
│
├── src/
│   │
│   ├── Jogo.java
│   ├── Main.java
│   ├── Tabuleiro.java
│   │
│   ├── model/
│   │   ├── Jogador.java
│   │   ├── Imovel.java
│   │   ├── Casa.java
│   │   ├── Inicio.java
│   │   ├── Imposto.java
│   │   └── Restituicao.java
│   │
│   ├── service/
│   │    ├── Hipoteca.java
│   │    ├── Negociacao.java
│   │ 
│   ├── ranking/
│   │   └── RankingBST.java
│       ├── NoBST.java
└── README.md
```


---

## 📝 Observações

- O jogo não pode ser iniciado com menos de **2 jogadores** e **10 imóveis** cadastrados.
- O jogador recebe salário ao completar uma volta completa no tabuleiro.
- Em cada turno, o jogador pode lançar dados, ver status, negociar ou desistir.

---

> Projeto desenvolvido para fins acadêmicos, com foco em aplicar e demonstrar estruturas de dados em Java.
