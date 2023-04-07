
# Projeto Decoder authUser

O projeto é baseado no Projeto decoder, onde inicialmente será aplicado a as intruções do curso, porém posteriormente será feito refatoração do código para outra estrutura de pastas ou banco de dados.



## Deploy

Para fazer o deploy e necessario possuir  as seguintes ferramentas:

```
  Docker
  Intelij com Java 11
  Robot 3t
  DBeaver
  
```
O docker compose esta localizado em : "src/main/resources/docker-compose.yml".






## Demonstração

<p align="center">
  <img title="#Demo" src="src/main/resources/gif/Demo.gif" width="800px">
</p>



## Client URL


```


```


## Documentação da API

#### Criaçao de novo registro

```http
  POST /
```

| Parâmetro         | Tipo          | Descrição                           |
| :----------       | :---------    | :---------------------------------- |
| `id`      | `string`   | **Obrigatório**. O ID do registro |


#### Retorna o registro utilizando pelo ID

```http
  GET /
```

| Parâmetro | Tipo       | Descrição                         |
|:----------| :--------- |:----------------------------------|
| `id`      | `string`   | **Obrigatório**. O ID do registro |


#### Retorna todos registros 

```http
  GET /
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |



#### Excluir registro baseado em ID

```http
  DELETE /
```

| Parâmetro | Tipo       | Descrição                          |
|:----------| :--------- | :--------------------------------- |
| `id`      | `string`   | **Obrigatório**. O ID do registro |



## Stack utilizada

**Back-end:** Java, Docker


## Autores

- [@Emersondll](https://emersondll.github.io)


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/stackdeveloper/)


