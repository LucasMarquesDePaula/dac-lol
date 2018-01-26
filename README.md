# Laundry On-Line

Laundry On-Line é um sistema criado como uma atividade para disciplina de Desenvolvimento de Aplicações Corporativas do curso de Tacnologia em Análise e Desenvolvimento de Sistemas da Universidade Federal do Paraná.

O objetivo final do sistema é avaliar a capacidade de desenvolvimento dos discentes. Por isso, o trabalho envolve um ciclo completo de um software, desde a analise até a entrega final ao cliente.

As função do sistema é organizar e controlar o funcionamento de uma lavanderia. Para que isso ocorra de maneira satisfatória, existirão dois perfis de acesso: cliente e funcionário. O cliente poderá fazer seu cadastro no sistema, realizar pedidos, visualizá-los e cancelá-los. Já o funcionário poderá confirmar lavagens, cadastrar roupas, visualizar pedidos, cadastrar funcionários, confirmar pagamentos e emitir relatórios.

## Tecnologias Utilizadas
 1. Vue JS
 2. Hibernate
 3. Maven
 4. JSP

## Padrão de Projeto
MVC

## Requisitos

 - **Perfil Cliente:** visualização de pedidos, cadastro do cliente, pedido on-line, cancelamento de pedidos e consulta de pedidos.
  - **Perfil Funcionário:** visualização de pedidos pela empresa, confirmação de lavagem, cadastro de tipos de roupas, cadastro de
   funcionários, confirmação de pagamentos e emissão de relatórios.

| Requisito | Tipo | Prioridade | Perfil |
|-----------|------|------------|--------|
| Leiaute bem elaborado | Não-funcional | 3 | Todos |
| Utilizar DHTML | Não-funcional | 5 | Todos |
| Validação dos campos | Não-funcional | 3 | Todos |
| Senhas criptografadas | Não-funcional | 4 | Todos |
| Datas em formato brasileiro | Não-funcional | 2 | Todos |
| Valores reais em formato brasileiro | Não-funcional | 2 | Todos |
| Tabelas normalizadas e com padrão de codificação | Não-funcional | 4 | Todos |
| Visualização de pedidos | Funcional | 5 | Cliente / Funcionário |
| Cadastro do cliente | Funcional | 5 | Cliente |
| Pedido on-line | Funcional | 4 | Cliente |
| Cancelamento de pedidos | Funcional | 2 | Cliente |
| Consulta de pedidos | Funcional | 3 | Cliente |
| Confirmação de lavagem | Funcional | 5 | Funcionário |
| Cadastro de tipos de roupas | Funcional | 1 | Funcionário |
| Cadastro de funcionários | Funcional | 5 | Funcionário |
| Confirmação de pagamentos | Funcional | 5 | Funcionário |
| Emissão de relatórios | Funcional | 1 | Funcionário |
