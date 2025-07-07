# Sistema de Árvore Genealógica - Raízes Tech

## Descrição do Projeto

Este projeto implementa um sistema interativo em Java para criação, gerenciamento e consulta de árvores genealógicas do tipo N-ária, onde cada pessoa pode ter múltiplos filhos e uma referência ao pai ou mãe. O sistema permite cadastrar membros da família, exibir a árvore completa, consultar detalhes individuais, calcular o grau de parentesco entre duas pessoas, contar o número de gerações e realizar remoção inteligente de membros.

---

## Funcionalidades

- **Cadastro de Pessoas:** Permite adicionar pessoas na árvore vinculando-as ao pai ou mãe já cadastrados.
- **Exibição Hierárquica:** Mostra a árvore genealógica completa no console com indentação para representar gerações.
- **Consulta de Detalhes:** Exibe informações de uma pessoa, incluindo nome, data de nascimento, pai/mãe e filhos.
- **Cálculo de Grau de Parentesco:** Calcula e exibe o parentesco entre duas pessoas, identificando relações como pai/mãe-filho(a), irmãos, avô/avó-neto(a), tios-sobrinho(a), primos de primeiro grau e parentesco distante.
- **Contagem de Gerações:** Calcula o número total de gerações presentes na árvore.
- **Remoção Inteligente:** Permite remover uma pessoa da árvore, com opção de remover apenas o indivíduo (ligando seus filhos ao avô/avó) ou remover toda a descendência.

---

## Tecnologias Utilizadas

- Linguagem: Java 8+
- Estruturas de dados: Árvores N-árias usando objetos com listas de filhos
- Entrada/Saída: Console (terminal)
- API Java: `java.time.LocalDate` para manipulação de datas

---

## Estrutura do Código

- `Pessoa.java` — classe que representa um membro da família, com atributos de nome, data de nascimento, referência ao pai/mãe e lista de filhos.
- `ArvoreGenealogica.java` — classe que gerencia a árvore, armazenando pessoas em um mapa para busca rápida, com métodos para adicionar, remover, exibir e calcular parentesco.
- `Main.java` — classe principal que implementa o menu interativo para o usuário operar o sistema via terminal.

---

4. Utilize o menu exibido para interagir com o sistema.

---

## Exemplo de Uso

- Adicionar membros informando nome, data de nascimento e pai/mãe.
- Exibir a árvore completa para visualizar as gerações.
- Buscar detalhes para ver as informações de um membro.
- Consultar parentesco entre duas pessoas para entender as relações familiares.
- Remover pessoas da árvore de forma inteligente para manter consistência.
- Contar gerações para saber a profundidade da árvore.

---

## Considerações Finais

Este projeto visa demonstrar a manipulação de estruturas de dados complexas (árvores N-árias), lógica de busca e relações hierárquicas, além de práticas de programação orientada a objetos em Java.

---

## Autor

Monalise Schwanck Pinho

---

## Data

Julho de 2025

---

