# LABMedical

*Projeto avaliativo Módulo 3 | Curso FullStack elaborado pela SESI SENAI.*

## Descrição

Criação de uma API Rest tendo como nome LabMedical, um software médico usado para generenciar e administrar atividades médicas e que deverá ser construído utilizando o framework Spring com Java.

Podendo ser uma plataforma capaz de processar e armazenar dados médicos para os diversos segmentos, cadastrar, upload de arquivos, geração de gráficos, relatórios e muito mais.

## Técnicas e tecnologias utilizadas

- **Versionamento** uso do GitHub para versionamento de código e aplicações.
- **PostgreSQL | versão 15** sistema gerenciador de banco de dados relacional, desenvolvido como projeto de código aberto.
- **Postman | versão 10.15.0** é um API Client que utiliza os serviços REST, simulando o que um front-end faria.
- **Spring initializr** geração de Projeto em Maven, Language Java, Spring Boot 3.1.1, Packaging Jar, Java 17.

Projeto Spring Boot com as dependências / starters:

- Spring Web
- Spring Data JPA
- PostgreSQL Driver
- Lombok
- Validation

## Como executar

Para realizar a execução do projeto, temos duas possibilidades: executando o JDK diretamente na sua máquina ou usando Docker.

### 1. Rodando com o JDK

1. Faça o download e instale o JDK 20.
2. Abra o IntelliJ IDEA.
3. Abra o projeto em IntelliJ IDEA.
4. Clique em Run > Run 'Main' para executar o projeto.

### 2. Usando docker

1. Instale o docker.
2. Usando o terminal acesse o diretório do projeto.
3. Execute o comando docker compose up.

*O acesso dos endpoints pode ser realizado através de um cliente de requisições HTTP.*

## Documentação da API

### Consultas

<details>
 <summary><code>POST</code> <code><b>/</b></code> <code>Cadastrar nova consulta</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | reasonForConsultation   | sim         | String     | Motivo da consulta. Mínimo de 8 caracteres.                    |
> | patientId               | sim         | Integer    | ID do paciente que está sendo consultado                       |
> | consultationDate        | sim         | String     | Data da consulta no seguinte formato: dd/MM/yyy                |
> | consultationTime        | sim         | String     | Hora da consulta no seguinte formato: HH:mm:ss                 |
> | problemDescription      | sim         | String     | Descrição do problema. Mínimo e máximo de 16 e 1024 caracteres.|
> | medicaments             | não         | Object[]   | Lista de medicamentos receitados, pode ser somente um objeto com o ID do medicamento.  |
> | dosageAndRecautions     | sim         | String     | Informações relacionadas as medicações. Mínimo e máximo de 16 e 256 caracteres.       |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da consulta cadastrada.                                        |
> | reasonForConsultation  | String         | Motivo da consulta.                                                 |
> | consultationDate       | String         | Data da consulta                                                    |
> | consultationTime       | String         | Hora da consulta                                                    |
> | problemDescription     | String         | Descrição do problema                                               |
> | medicaments            | Object[]       | Lista de medicamentos receitados                                    |
> | dosageAndRecautions    | String         | Informações relacionadas as medicações                              |
> | status                 | Boolean        | Valor informando se a consulta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `201`         | Consulta cadastrada com sucesso, retornando no body a consulta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |   |

##### Example cURL

> ```javascript
>  curl -X POST 'http://localhost:8080/api/consultas' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>PUT</code> <code><b>/</b></code> <code>Editar uma consulta</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> 
##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | queryId   |  sim             | Integer                 | ID da consulta que está sendo editada.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | reasonForConsultation   | sim         | String     | Motivo da consulta. Mínimo de 8 caracteres.                    |
> | consultationDate        | sim         | String     | Data da consulta no seguinte formato: dd/MM/yyy                |
> | consultationTime        | sim         | String     | Hora da consulta no seguinte formato: HH:mm:ss                 |
> | problemDescription      | sim         | String     | Descrição do problema. Mínimo e máximo de 16 e 1024 caracteres.|
> | medicaments             | não         | Object[]   | Lista de medicamentos receitados, pode ser somente um objeto com o ID do medicamento.  |
> | dosageAndRecautions     | sim         | String     | Informações relacionadas as medicações. Mínimo e máximo de 16 e 256 caracteres.       |
> | status                  | sim         | Boolean    | Valor informando se a consulta está ativa.       |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da consulta cadastrada.                                        |
> | reasonForConsultation  | String         | Motivo da consulta.                                                 |
> | consultationDate       | String         | Data da consulta                                                    |
> | consultationTime       | String         | Hora da consulta                                                    |
> | problemDescription     | String         | Descrição do problema                                               |
> | medicaments            | Object[]       | Lista de medicamentos receitados                                    |
> | dosageAndRecautions    | String         | Informações relacionadas as medicações                              |
> | status                 | Boolean        | Valor informando se a consulta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Consulta editada com sucesso, retornando no body a consulta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a consulta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X PUT 'http://localhost:8080/api/consultas/1' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar todas consultas</code></summary>

##### Query Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | nome      |  não             | String                  | Nome do paciente que será usado para filtrar as consultas.     |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> |                        | Object[]       | Lista de consultas cadastradas                                      |
> 
##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Retorna todas consultas, caso tenha sido passado o paramêtro na URI retorna todas consultas que possuam o paciente com o paramêtro.      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |     |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/consultas?nome=joao'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar consulta pelo ID</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | queryId   |  sim             | Integer                 | ID da consulta que está sendo consultada.                |

##### Response Body

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da consulta cadastrada.                                        |
> | reasonForConsultation  | String         | Motivo da consulta.                                                 |
> | consultationDate       | String         | Data da consulta                                                    |
> | consultationTime       | String         | Hora da consulta                                                    |
> | problemDescription     | String         | Descrição do problema                                               |
> | medicaments            | Object[]       | Lista de medicamentos receitados                                    |
> | dosageAndRecautions    | String         | Informações relacionadas as medicações                              |
> | status                 | Boolean        | Valor informando se a consulta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Consulta cadastrada com sucesso, retornando no body a consulta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a consulta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/consultas/1'
> ```

</details>

<details>
 <summary><code>DELETE</code> <code><b>/</b></code> <code>Deletar uma consulta</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | queryId   |  sim             | Integer                 | ID da consulta que está sendo deletada.                |

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> | patientId |  sim             | Integer                 | ID do paciente relacionado a consulta.                         |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `202`         | Consulta deletada com sucesso      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a consulta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X DELETE 'http://localhost:8080/api/consultas/1' -H 'userId: 1'
> ```

</details>

### Dietas

<details>
 <summary><code>POST</code> <code><b>/</b></code> <code>Cadastrar nova dieta</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | dietName                | sim         | String     | Nome da dieta. Mínimo e máximo de 5 e 100 caracteres.        |
> | patientId               | sim         | Integer    | ID do paciente que está executando a dieta            |
> | dietDate                | sim         | String     | Data da dieta no seguinte formato: dd/MM/yyy                |
> | dietTime                | sim         | String     | Hora da dieta no seguinte formato: HH:mm:ss                 |
> | type                    | sim         | String     | Tipo da dieta que está sendo executada, opções válidas: LOW_CARB, DASH, PALEOLITHIC, KETOGENIC, DUKAN, MEDITERRANEAN e OTHER.|
> | description             | não         | String     | Descrição da dieta que está sendo executada.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da dieta cadastrada.                                        |
> | dietName               | String         | Nome da dieta.                                                 |
> | dietDate               | String         | Data da dieta                                                    |
> | dietTime               | String         | Hora da dieta                                                    |
> | type                   | String         | Tipo da dieta que está sendo executada                                               |
> | description            | String         | Descrição da dieta que está sendo executada                                    |
> | status                 | Boolean        | Valor informando se a dieta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `201`         | dieta cadastrada com sucesso, retornando no body a dieta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |   |

##### Example cURL

> ```javascript
>  curl -X POST 'http://localhost:8080/api/dietas' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>PUT</code> <code><b>/</b></code> <code>Editar uma dieta</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> 
##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | dietId    |  sim             | Integer                 | ID da dieta que está sendo editada.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | dietName                | sim         | String     | Nome da dieta. Mínimo e máximo de 5 e 100 caracteres.        |
> | type                    | sim         | String     | Tipo da dieta que está sendo executada, opções válidas: LOW_CARB, DASH, PALEOLITHIC, KETOGENIC, DUKAN, MEDITERRANEAN e OTHER.|
> | description             | não         | String     | Descrição da dieta que está sendo executada.  |
> | status                  | sim         | Boolean    | Valor informando se a dieta está ativa.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da dieta cadastrada.                                        |
> | dietName               | String         | Nome da dieta.                                                 |
> | dietDate               | String         | Data da dieta                                                    |
> | dietTime               | String         | Hora da dieta                                                    |
> | type                   | String         | Tipo da dieta que está sendo executada                                               |
> | description            | String         | Descrição da dieta que está sendo executada                                    |
> | status                 | Boolean        | Valor informando se a dieta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | dieta editada com sucesso, retornando no body a dieta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a dieta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X PUT 'http://localhost:8080/api/dietas/1' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar todas dietas</code></summary>

##### Query Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | nome      |  não             | String                  | Nome do paciente que será usado para filtrar as dietas.     |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> |                        | Object[]       | Lista de dietas cadastradas                                      |
> 
##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Retorna todas dietas, caso tenha sido passado o paramêtro na URI retorna todas dietas que possuam o paciente com o paramêtro.      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |     |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/dietas?nome=joao'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar dieta pelo ID</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | dietId    |  sim             | Integer                 | ID da dieta que está sendo executada.                |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID da dieta cadastrada.                                        |
> | dietName               | String         | Nome da dieta.                                                 |
> | dietDate               | String         | Data da dieta                                                    |
> | dietTime               | String         | Hora da dieta                                                    |
> | type                   | String         | Tipo da dieta que está sendo executada                                               |
> | description            | String         | Descrição da dieta que está sendo executada                                    |
> | status                 | Boolean        | Valor informando se a dieta está ativa.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | dieta cadastrada com sucesso, retornando no body a dieta      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a dieta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/dietas/1'
> ```

</details>

<details>
 <summary><code>DELETE</code> <code><b>/</b></code> <code>Deletar uma dieta</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | dietId    |  sim             | Integer                 | ID da dieta que está sendo deletada.                |

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> | patientId |  sim             | Integer                 | ID do paciente relacionado a dieta.                            |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `202`         | dieta deletada com sucesso      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que a dieta não foi encontrada   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X DELETE 'http://localhost:8080/api/dietas/1' -H 'userId: 1'
> ```

</details>

### Estatísticas

### Exames

<details>
 <summary><code>POST</code> <code><b>/</b></code> <code>Cadastrar novo exame</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | examName                | sim         | String     | Nome do exame. Mínimo e máximo de 8 e 64 caracteres.        |
> | patientId               | sim         | Integer    | ID do paciente examinado.            |
> | examDate                | sim         | String     | Data do exame no seguinte formato: dd/MM/yyy                |
> | examHour                | sim         | String     | Hora do exame no seguinte formato: HH:mm:ss                 |
> | examType                | sim         | String     | Tipo do exame que foi passado. Mínimo e máximo de 4 e 32 caracteres.|
> | laboratory              | sim         | String     | Nome do laboratório que realizou o exame. Mínimo e máximo de 4 e 32 caracteres.  |
> | documentUrl             | não         | String     | Link para acessar o documento do exame.  |
> | results                 | sim         | String     | Resultados do exame.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exame cadastrada.                                        |
> | examName               | String         | Nome do exame.                                                 |
> | examDate               | String         | Data do exame                                                    |
> | examHour               | String         | Hora do exame                                                    |
> | examType               | String         | Tipo do exame que foi passado.                                              |
> | laboratory             | String         | Nome do laboratório que realizou o exame.                                    |
> | documentUrl            | String         | Link para acessar o documento do exame.                                    |
> | status                 | Boolean        | Valor informando se o exame está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `201`         | Exame cadastrado com sucesso, retornando no body o exame      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |   |

##### Example cURL

> ```javascript
>  curl -X POST 'http://localhost:8080/api/exames' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>PUT</code> <code><b>/</b></code> <code>Editar um exame</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> 
##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | examId    |  sim             | Integer                 | ID do exame que está sendo editado.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | examName                | sim         | String     | Nome do exame. Mínimo e máximo de 8 e 64 caracteres.        |
> | examDate                | sim         | String     | Data do exame no seguinte formato: dd/MM/yyy                |
> | examHour                | sim         | String     | Hora do exame no seguinte formato: HH:mm:ss                 |
> | examType                | sim         | String     | Tipo do exame que foi passado. Mínimo e máximo de 4 e 32 caracteres.|
> | laboratory              | sim         | String     | Nome do laboratório que realizou o exame. Mínimo e máximo de 4 e 32 caracteres.  |
> | documentUrl             | não         | String     | Link para acessar o documento do exame.  |
> | results                 | sim         | String     | Resultados do exame.  |
> | status                 | sim         | Boolean     | Valor informando se o exame está ativo.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exame cadastrada.                                        |
> | examName               | String         | Nome do exame.                                                 |
> | examDate               | String         | Data do exame                                                    |
> | examHour               | String         | Hora do exame                                                    |
> | examType               | String         | Tipo do exame que foi passado.                                              |
> | laboratory             | String         | Nome do laboratório que realizou o exame.                                    |
> | documentUrl            | String         | Link para acessar o documento do exame.                                    |
> | status                 | Boolean        | Valor informando se o exame está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Exame editado com sucesso, retornando no body o exame      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exame não foi encontrado   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X PUT 'http://localhost:8080/api/exames/1' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar todos exames</code></summary>

##### Query Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | nome      |  não             | String                  | Nome do paciente que será usado para filtrar os exames.     |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> |                        | Object[]       | Lista de exames cadastrados                                      |
> 
##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Retorna todos exames, caso tenha sido passado o paramêtro na URI retorna todos exames que possuam o paciente com o paramêtro.      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |     |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/exames?nome=joao'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar exame pelo ID</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | examId    |  sim             | Integer                 | ID da exame que está sendo executado.                |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exame cadastrada.                                        |
> | examName               | String         | Nome do exame.                                                 |
> | examDate               | String         | Data do exame                                                    |
> | examHour               | String         | Hora do exame                                                    |
> | examType               | String         | Tipo do exame que foi passado.                                              |
> | laboratory             | String         | Nome do laboratório que realizou o exame.                                    |
> | documentUrl            | String         | Link para acessar o documento do exame.                                    |
> | status                 | Boolean        | Valor informando se o exame está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | exame cadastrado com sucesso, retornando no body o exame      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exame não foi encontrado   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/exames/1'
> ```

</details>

<details>
 <summary><code>DELETE</code> <code><b>/</b></code> <code>Deletar um exame</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | examId    |  sim             | Integer                 | ID da exame que está sendo deletado.                |

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> | patientId |  sim             | Integer                 | ID do paciente relacionado o exame.                            |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `202`         | exame deletado com sucesso      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exame não foi encontrado  |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X DELETE 'http://localhost:8080/api/exames/1' -H 'userId: 1'
> ```

</details>

### Exercícios

<details>
 <summary><code>POST</code> <code><b>/</b></code> <code>Cadastrar novo exercício</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | name                | sim         | String     | Nome do exercício. Mínimo e máximo de 8 e 64 caracteres.        |
> | patientId               | sim         | Integer    | ID do paciente examinado.            |
> | date                | sim         | String     | Data do exercício no seguinte formato: dd/MM/yyy                |
> | time                | sim         | String     | Hora do exercício no seguinte formato: HH:mm:ss                 |
> | type                | sim         | String     | Tipo do exercício que foi passado, opções possíveis: AEROBICS, MUSCULAR, FLEXIBILITY, STRENGTH, AGILITY ou OTHER|
> | weeklyAmount              | sim         | Integer     | Quantidade semanal que deve ser realizado.  |
> | description             | sim         | String     | Descrição do exercicio.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exercicio cadastrado.                                        |
> | name               | String         | Nome do exercicio.                                                 |
> | date               | String         | Data do exercicio                                                    |
> | time               | String         | Hora do exercicio                                                    |
> | type               | String         | Tipo do exercicio que foi passado.                                              |
> | weeklyAmount            | Integer         | Quantidade semanal que deve ser realizado.                               |
> | description            | String         | Descrição do exercicio.                               |
> | status                 | Boolean        | Valor informando se o exercicio está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `201`         | exercicio cadastrado com sucesso, retornando no body o exercicio      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |   |

##### Example cURL

> ```javascript
>  curl -X POST 'http://localhost:8080/api/exercicios' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>PUT</code> <code><b>/</b></code> <code>Editar um exercicio</code></summary>

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> 
##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | exerciseId    |  sim             | Integer                 | ID do exercicio que está sendo editado.                |

##### Request Body

> | campo                   | obrigatório | tipo       | descrição                                                      |
> |-------------------------|-------------|------------|----------------------------------------------------------------|
> | name                | sim         | String     | Nome do exercício. Mínimo e máximo de 8 e 64 caracteres.        |
> | patientId               | sim         | Integer    | ID do paciente examinado.            |
> | date                | sim         | String     | Data do exercício no seguinte formato: dd/MM/yyy                |
> | time                | sim         | String     | Hora do exercício no seguinte formato: HH:mm:ss                 |
> | type                | sim         | String     | Tipo do exercício que foi passado, opções possíveis: AEROBICS, MUSCULAR, FLEXIBILITY, STRENGTH, AGILITY ou OTHER|
> | weeklyAmount              | sim         | Integer     | Quantidade semanal que deve ser realizado.  |
> | description             | sim         | String     | Descrição do exercicio.  |
> 
##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exercicio cadastrado.                                        |
> | name               | String         | Nome do exercicio.                                                 |
> | date               | String         | Data do exercicio                                                    |
> | time               | String         | Hora do exercicio                                                    |
> | type               | String         | Tipo do exercicio que foi passado.                                              |
> | weeklyAmount            | Integer         | Quantidade semanal que deve ser realizado.                               |
> | description            | String         | Descrição do exercicio.                               |
> | status                 | Boolean        | Valor informando se o exercicio está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | exercicio editado com sucesso, retornando no body o exercicio      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exercicio não foi encontrado   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X PUT 'http://localhost:8080/api/exercicios/1' -H 'userId: 1'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar todos exercicios</code></summary>

##### Query Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | nome      |  não             | String                  | Nome do paciente que será usado para filtrar os exercicios.     |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> |                        | Object[]       | Lista de exercicios cadastrados                                      |
> 
##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | Retorna todos exercicios, caso tenha sido passado o paramêtro na URI retorna todos exercicios que possuam o paciente com o paramêtro.      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `500`         |     |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/exercicios?nome=joao'
> ```

</details>

<details>
 <summary><code>GET</code> <code><b>/</b></code> <code>Buscar exercicio pelo ID</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | exerciseId    |  sim             | Integer                 | ID da exercicio que está sendo executado.                |

##### Response Body

> | campo                  | tipo           | response                                                            |
> |------------------------|----------------|---------------------------------------------------------------------|
> | id                     | Integer        | O ID do exercicio cadastrado.                                        |
> | name               | String         | Nome do exercicio.                                                 |
> | date               | String         | Data do exercicio                                                    |
> | time               | String         | Hora do exercicio                                                    |
> | type               | String         | Tipo do exercicio que foi passado.                                              |
> | weeklyAmount            | Integer         | Quantidade semanal que deve ser realizado.                               |
> | description            | String         | Descrição do exercicio.                               |
> | status                 | Boolean        | Valor informando se o exercicio está ativo.                          |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `200`         | exercicio cadastrado com sucesso, retornando no body o exercicio      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exercicio não foi encontrado   |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X GET 'http://localhost:8080/api/exercicios/1'
> ```

</details>

<details>
 <summary><code>DELETE</code> <code><b>/</b></code> <code>Deletar um exercicio</code></summary>

##### Path Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | exerciseId    |  sim             | Integer                 | ID da exercicio que está sendo deletado.                |

##### Header Parameters

> | nome      |  obrigatório     | data type               | descrição                                                      |
> |-----------|------------------|-------------------------|----------------------------------------------------------------|
> | userId    |  sim             | Integer                 | ID do usuário que está realizando a requisição.                |
> | patientId |  sim             | Integer                 | ID do paciente relacionado o exercicio.                            |

##### Response Code

> | código http   | descrição                                                           |
> |---------------|---------------------------------------------------------------------|
> | `202`         | exercicio deletado com sucesso      |
> | `400`         | Retorna uma mensagem informando quais campos estão inválidos        |
> | `404`         | Retorna uma mensagem informando que o exercicio não foi encontrado  |
> | `500`         |    |

##### Example cURL

> ```javascript
>  curl -X DELETE 'http://localhost:8080/api/exercicios/1' -H 'userId: 1'
> ```

</details>

### Logs
### Medicamentos
### Pacientes
### Prontuários
### Usuários

## Como contribuir

Sinta-se à vontade para enviar solicitações de pull e resaltar problemas. Se você deseja contribuir com este projeto, siga as seguintes etapas:

1. Crie um fork deste repositório em sua conta do GitHub.
2. Clone o fork em seu computador.
3. Crie uma nova branch com um nome descritivo da sua contribuição (por exemplo, "adicionar-nova-página").
4. Faça as alterações e os commits necessários em sua branch.
5. Envie um pull request para o repositório principal, explicando suas mudanças.

## Melhorias a serem adicionadas

- Refatoração do código - Melhorias nos nomes das variáveis
- Adicionar camada de segurança