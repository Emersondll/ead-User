
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
localhost:8087

```

## Documentação da API

#### Criaçao de novo registro

```
curl --request POST \
  --url http://localhost:8087/auth/signup \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905 \
  --data '{
	"username": "Emers3786s059ed4ddSsofns2ei1e",
	"email": "eme2a3ie4lT75es0is61ddSs8stfe955456@es",
	"password": "123456",
	"fullName": "Emerson Lima",
	"phoneNumber": "123456",
	"documentNumber":"cpf",
	"cpf": 999999
}'
```

| Parâmetro         | Tipo          | Descrição                           |
| :----------       | :---------    | :---------------------------------- |
| `id`      | `string`   | **Obrigatório**. O ID do registro |


#### Retorna o registro utilizando pelo ID

```
curl --request GET \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parâmetro | Tipo       | Descrição                         |
|:----------| :--------- |:----------------------------------|
| `id`      | `string`   | **Obrigatório**. O ID do registro |


#### Retorna todos registros

```
  curl --request GET \
  --url http://localhost:8087/user \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |



#### Excluir registro baseado em ID

```
  curl --request DELETE \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parâmetro | Tipo       | Descrição                          |
|:----------| :--------- | :--------------------------------- |
| `id`      | `string`   | **Obrigatório**. O ID do registro |

#### Updates registro baseado 

```
curl --request PUT \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905 \
  --data '	{
		
		"username": "Emerson 2",
		"email": "emeailTest 2",
		"fullName": "Emerson Lima 2",
		"userStatus": "ACTIVE",
		"userType": "STUDENT",
		"phoneNumber": "123222",
		"cpf": "9999992",
		"creationDate": "10-04-2023 02:53:07",
		"lastUpdateDate": "10-04-2023 02:53:07"
	}'
	
curl --request PUT \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d/password \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905 \
  --data '	{
		"password": "1234567",
		"oldPassword":"123456"
	}'
	

curl --request PUT \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d/image \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905 \
  --data '	{
		"imageUrl": "httpimageUrls"
		
	}'	
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


