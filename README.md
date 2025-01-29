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

## Exemplos de JSON para Métodos POST e PUT

Aqui estão alguns exemplos de JSON que podem ser utilizados nos métodos POST e PUT dos controllers, bem como exemplo retornado nos métodos GET.

### Exemplo de JSON para `Product`

#### GET/products

Exemplo de Resposta

```json
[
    {
        "id": 1,
        "name": "The Lord of the Rings",
        "description": "Lorem ipsum dolor sit amet, consectetur.",
        "price": 90.5,
        "category": [
            "ELECTRONICS"
        ],
        "imgUrl": null
    }
]
```

#### POST /products

```json
{
    "id": "null",
    "name": "Produto Exemplo",
    "description": "Descrição do produto exemplo",
    "price": 29.01,
    "category": ["Nome da categoria"]
}
```

#### PUT /products

```json
{
    "id": "Id a ser atualizado",
    "name": "Produto Exemplo Atualizado",
    "description": "Descrição atualizada do produto exemplo",
    "price": 39.99,
    "category": ["Nome da categoria"]
}
```

### Exemplo de JSON para `Category`

#### GET/categories

Exemplo de Resposta

```json
[
    {
        "id": 1,
        "name": "ELECTRONICS"
    }
]
```

#### POST /categories

```json
{   
    "id": "null",
    "name": "Categoria Exemplo"
}
```

#### PUT /categories/{id}

```json
{   
    "id": "Id a ser atualizado",
    "name": "Categoria Exemplo Atualizada"
}
```

### Exemplo de JSON para `Order`

#### GET/orders

Exemplo de Resposta

```json
[
    {
        "id": 1,
        "moment": "2019-06-20T19:53:07Z",
        "orderStatus": "PAID",
        "clientId": 1
    }
]
```

#### POST /orders

```json
{
    "id": "null",
    "moment": "2025-10-10T10:00:00Z",
    "orderStatus": "número ou palavra correspondente ao status da ordem",
    "clientId": "número correspondente ao Id do cliente"
}
```

#### PUT /orders

```json
{
    "id": "Id a ser atualizado",
    "moment": "2023-10-10T10:00:00Z",
    "orderStatus": "número ou palavra correspondente ao status da ordem",
    "clientId": "número correspondente ao Id do cliente"
}
```

### Exemplo de JSON para `User`

#### GET/users

Exemplo de Resposta

```json
[
    {
        "id": "Id do usuário",
        "name": "Usuário Exemplo",
        "email": "usuario@example.com",
        "phone": "xxxxxxxxx",
        "password": "senha123"
    }
]
```

#### POST /users

```json
{
    "id": "null",
    "name": "Usuário Exemplo Atualizado",
    "email": "usuario_atualizado@example.com",
    "phone": "xxxxxxxxx",
    "password": "senha123"
}
```

#### PUT /users

```json
{
    "id": "Id a ser atualizado",
    "name": "Usuário Exemplo Atualizado",
    "email": "usuario_atualizado@example.com",
    "phone": "xxxxxxxxx",
    "password": "nova_senha123"
}
```

### Exemplo de JSON para `Payment`

#### GET/payments

Exemplo de Resposta

```json
[
    {
        "id": 1,
        "moment": "2025-01-29T13:15:42.869457Z",
        "order_id": 1
    }
]
```

#### POST /payments

```json
{
    "id": "null",
    "moment": "2025-10-10T10:00:00Z",
    "order_id": "número correspondente ao Id do pedido"
}
```

#### PUT /payments

```json
{
    "id": "Id a ser atualizado",
    "moment": "2023-10-10T10:00:00Z",
    "order_id": "número correspondente ao Id do pedido"
}
```

### Exemplo de JSON para `OrderItem`

#### GET/order-item

Exemplo de Resposta

```json
[
    {
        "order_id": 3,
        "product_id": 5,
        "quantity": 2,
        "price": 100.99
    }
]
```

#### POST /order-items

```json
{
        "order_id": "id da ordem",
        "product_id": "id do produto",
        "quantity": 5,
        "price": 50.0
}
```

#### PUT /order-items

```json
{
        "order_id": "id da ordem a ser atualizada",
        "product_id": "id do produto a ser atualizado",
        "quantity": 5,
        "price": 50.0
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
