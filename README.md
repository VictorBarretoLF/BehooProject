# Eventos Project

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

