# Teste Java

### Requisitos
1. JDK 8
1. Maven 3

### Execução e submissão
1. Clone este projeto
2. Após clonar o repositório execute: `mvn spring-boot:run`
3. Acesse: `http://localhost:8080/customers`
4. Crie uma branch com seu nome e sobrenome. Exemplo: `joaodasilva`
4. Realize o teste (descritivos abaixo).
5. Suba a branch para o repositório.


### Teste
1. O endpoint `http://localhost:8080/customers` retorna uma lista de clientes.
2. Implemente na classe `CustomerController` as APIs para criação, editação e excluisão de clientes.
3. Os dados de cadastro e edição devem ser validades.
4. A listagem de cliente deverá ser paginada.
5. Uma cliente poderá ter múltiplos endereços.
6. No cadastro do endereço permita inserir apenas o CEP carregando os dados via consumo do serviço: https://viacep.com.br/


### Diferenciais
1. Tratamento de exceções
2. Testes unitários
3. Validações
4. Frontend (UI)
5. Documentação
