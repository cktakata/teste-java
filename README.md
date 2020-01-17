# Teste Java

### Requisitos
1. JDK 8
1. Maven 3

### Execução e submissão
1. Clone este projeto
2. Após clonar, execute: `mvn spring-boot:run`
3. Acesse: `http://localhost:8080/customers`
4. Realize o teste (descritivos abaixo).
5. Suba o teste para o seu repositório e envie a url para análise.
6. Boa sorte =)


### Teste
[x] O endpoint `http://localhost:8080/customers` retorna uma lista de clientes.
[x] Implemente na classe `CustomerController` as APIs para criação, editação e exclusão de clientes.
[ ] Os dados de cadastro e edição devem ser validados.
[ ] A listagem de clientes deverá ser paginada.
[ ] Um cliente poderá ter múltiplos endereços.
[ ] No cadastro do endereço permita inserir apenas o CEP carregando os dados via consumo do serviço: https://viacep.com.br/


### Diferenciais
1. Tratamento de exceções
2. Testes unitários
3. Validações
4. Frontend (UI)
5. Documentação
