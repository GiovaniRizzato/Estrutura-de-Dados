#Sobre as estruturas 

# Lista 
Lista é uma estrutura de dados baseada em lista duplamente encadeada sem muita otimização, a ideia em usa-la é que possui uma complexidade baixa e possibilita utilização eficiente de Iteradores (tanto iterador normal, quanto iterador manipulador). 

# Fila 
Fila é uma estrutura em lista que leva o princípio de FIFO (First in, First out OU primeiro a entrar, primeiro a sair), ou seja, pode-se apenas adicionar elementos no final da fila e remove-los do início. Como é uma lista duplamente encadeada, também possui suporte à iteradores, porém apenas para Iterador normal, pois o iterador manipulador iria arruinar o concito de FIFO da estrutura. 

# Vetor 
Vetor é uma estrutura baseada em Array, porem com capacidade dinâmica, ou seja, pode se expandir se necessário, custo "0" para operações de consulta porem, dependendo da frequência com que for inserido e excluído elementos acaba tendo um desempenho muito ruim. 

# Tabela de espalhamento 
Uma estrutura que tem inteligência de distribuição de dados a partir do hashCode() do objeto, que permite uma consulta com custo baixíssimo, mesmo em estruturas grandes utilizando como parâmetro de consulta o próprio objeto, diferente do vetor que precisa saber a posição do objeto para consulta-lo, desvantagem seria que é necessário o objeto em questão para fazer as devidas operações como remover para trabalhar com esta estrutura, um bom exemplo de utilização de tabela seria um dicionario, onde pode-se adicionar, excluir e consultar se existe na coleção de forma muito eficiente. 
