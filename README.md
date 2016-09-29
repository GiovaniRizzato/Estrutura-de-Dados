#Sobre as estruturas

# Lista
Lista é uma estrutura de dados baseada em lista duplamente encadeada sem muita otimização, a idéia em usala é que possui uma complexidade baixa e possibilita utilização eficiente de Iteradores (tanto iterador normal, quanto iterador manipulador).

# Fila
Fila é uma estrutura em lista que leva o principio de FIFO (First in, First out OU primeiro a entrar, primeiro a sair), ou seja, pode-se apenas adicionar elementos no final da fila e remove-los do incio. Como é uma lista duplamente encadeada, tambem possui suporte à iteradores, porem apenas para Iterador normal, pois o iterador manipulador iria arruinar o concito de FIFO da estrutura.

# Vetor
Vetor é uma estrura baseada em Array, porem com capacidade dinamica, ou seja, pode se expandir se necessario, custo "0" para operações de consulta porem, depedendo da frequencia com que for incerido e excluido elementos acaba tendo um desempenho muito ruim.

# Tabela de espalhamento
Uma estrutura que tem inteligencia de distribuição de dados a partir do hashCode() do objeto, que permite uma consulta com custo baixissimo, mesmo em estruturas grandes utilizando como parametro de consulta o proprio objeto, diferente do vetor que precisa saber a posição do objeto para consulta-lo, desvantagem seria que é necessario o objeto em questão para fazer as devidas operações como remover para trabalhar com esta estrutura, um bom exeomplo de utilização de tabela seria um dicionario, onde pode-se adicionar, excluir e consultar se existe na coleção de forma muito eficiente.
