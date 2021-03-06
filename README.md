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
- [x] O endpoint `http://localhost:8080/customers` retorna uma lista de clientes.
- [x] Implemente na classe `CustomerController` as APIs para criação, editação e exclusão de clientes.
- [ ] Os dados de cadastro e edição devem ser validados.
- [x] A listagem de clientes deverá ser paginada.
``` http://localhost:8080/customers?pageSize=3&pageNo=0&sortBy=name ```
- [x] Um cliente poderá ter múltiplos endereços.
- [x] No cadastro do endereço permita inserir apenas o CEP carregando os dados via consumo do serviço: https://viacep.com.br/


### Diferenciais
1. Tratamento de exceções (OK)
2. Testes unitários (NOK)
3. Validações (OK)
4. Frontend (UI) (50%)
5. Documentação (NOK)
