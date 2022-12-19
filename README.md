# Eventos Project

## Executando aplicação usando o Docker

1. Clone o repositório para uma pasta local
2. Em seguida na pasta onde se encontra o arquivo docker-compose.yml execute o seguinte comando:
    ```
        docker-compose up
    ```
3. O projeto irá ultilizar a porta http://localhost:8080, divirta-se!

## Executando aplicação usando o IntelliJ

1. Clone o repositório para uma pasta local e abra o projeto usando o intelliJ, execute a classe ***TestewebappApplication***.
2. O projeto irá ultilizar a porta http://localhost:8080, divirta-se!

## Rotas, ações e metodos da API:
| Methods | Urls | Actions |
|:--------|:----:|--------:|
| GET| api/eventos| Listar todos os eventos|
| GET| api/usuarios| Listar todos os usuários|
| GET| api/eventos/find-usuarios-by-evento/:idEvento| Listar todos os usuários de um determinado evento|
| GET| api/usuarios/find-eventos-by-usuario/:idUsuario| Listar todos os eventos de um determinado usuário|
| POST| api/eventos| Criar um evento|
| POST| api/usuarios| Criar um usuário|
| PUT| api/usuarios/inscricao/:idUsuario/:idEvento| Inscrever usuário em evento|
| PUT| api/usuarios/cancelar/:idUsuario/:idEvento| Cancelar inscrição do usuário em evento|
| PUT| api/eventos/entrada/:idUsuario/:idEvento| Realizar entrada do usuário em um evento|
