# E-commerce Application

Esta é uma aplicação básica de e-commerce desenvolvida em Java utilizando o framework Spring Boot. A aplicação permite gerenciar produtos, pedidos e categorias, além de realizar operações de CRUD (Create, Read, Update, Delete) e consultas personalizadas.

## Estrutura do Projeto

A estrutura do projeto está organizada em pacotes conforme descrito abaixo:

### Pacote `entities`

Contém as classes de entidade que representam as tabelas do banco de dados.

- **Product**: Representa um produto no sistema.
- **Category**: Representa uma categoria de produtos.
- **Order**: Representa um pedido realizado por um cliente.
- **OrderItem**: Representa um item de pedido, associando um produto a um pedido.
- **User**: Representa um usuário do sistema.

### Pacote `repositories`

Contém as interfaces de repositório que estendem `JpaRepository` para realizar operações de banco de dados.

- **ProductRepository**: Interface para operações de CRUD com a entidade `Product`.
- **CategoryRepository**: Interface para operações de CRUD com a entidade `Category`.
- **OrderRepository**: Interface para operações de CRUD com a entidade `Order`.
- **OrderItemRepository**: Interface para operações de CRUD com a entidade `OrderItem`.
- **UserRepository**: Interface para operações de CRUD com a entidade `User`.

### Pacote `services`

Contém as classes de serviço que encapsulam a lógica de negócios da aplicação.

- **ProductService**: Serviço para operações relacionadas a produtos.
- **CategoryService**: Serviço para operações relacionadas a categorias.
- **OrderService**: Serviço para operações relacionadas a pedidos.
- **OrderItemService**: Serviço para operações relacionadas a itens de pedido.
- **UserService**: Serviço para operações relacionadas a usuários.

### Pacote `dto`

Contém as classes de Data Transfer Object (DTO) que são usadas para transferir dados entre as camadas da aplicação.

- **ProductDTO**: DTO para a entidade `Product`.
- **CategoryDTO**: DTO para a entidade `Category`.
- **OrderDTO**: DTO para a entidade `Order`.
- **OrderItemDTO**: DTO para a entidade `OrderItem`.
- **UserDTO**: DTO para a entidade `User`.

### Pacote `config`

Contém classes de configuração da aplicação.

- **TestConfig**: Classe de configuração usada para carregar dados de teste no banco de dados.

### Pacote `exceptions`

Contém classes para tratamento de exceções personalizadas.

- **ResourceNotFoundException**: Exceção lançada quando um recurso não é encontrado.
- **DatabaseException**: Exceção lançada para erros relacionados ao banco de dados.

## Configuração de Testes

A classe `TestConfig` é usada para configurar o ambiente de testes da aplicação. Ela é anotada com `@Configuration` e `@Profile("test")`, o que significa que será carregada apenas quando o perfil de teste estiver ativo. A classe injeta um `DataSource` e um `DBService` para inicializar o banco de dados com dados de teste.

```java
@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() {
        dbService.instantiateTestDatabase();
        return true;
    }
}
```

## Executando a Aplicação
Para executar a aplicação, você pode usar o Maven. Certifique-se de que todas as dependências estão corretamente configuradas no arquivo pom.xml.

```
# Usando Maven
mvn spring-boot:run

```

## Contribuição
Sinta-se à vontade para contribuir com este projeto. Você pode abrir issues e pull requests no repositório do GitHub.
