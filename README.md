# Decoder authUser project

The project is based on the decoder Project, which will initially be applied to the course instructions, but later the code will be refactored for another structure of folders or database.

## Deploy

To deploy it is necessary to have the following tools:

```
   docker
   Intelij with Java 11
   robot 3t
   DBeaver
  
```
Docker compose is located at: "src/main/resources/docker-compose.yml".

## Demonstration

<p align="center">
  <img title="#Demo" src="src/main/resources/gif/giphy.gif" width="800px">
</p>

## Client URL

```
localhost:8087

```

## API documentation

#### Creating a new record

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

| Parameter    | Type     | Description                        |
|:-------------|:---------| :--------------------------------- |
| `id`         | `string` | **Mandatory**. The registration ID |


#### Returns the record using by ID

```
curl --request GET \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parameter    | Type     | Description                        |
|:-------------|:---------| :--------------------------------- |
| `id`         | `string` | **Mandatory**. The registration ID |


#### Returns all records

```
  curl --request GET \
  --url http://localhost:8087/user \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parameter    | Type     | Description                        |
|:-------------|:---------| :--------------------------------- |
| `id`         | `string` | **Mandatory**. The registration ID |



#### Delete record based on ID

```
  curl --request DELETE \
  --url http://localhost:8087/user/ecb7e9be-1a44-4887-9f3a-a89e3720de0d \
  --cookie JSESSIONID=1445158BB71F05CDF725CAC00DF41905
```

| Parameter    | Type     | Description                        |
|:-------------|:---------|:-----------------------------------|
| `id`         | `string` | **Mandatory**. The registration ID |

#### Registry based updates

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

| Parameter    | Type     | Description                        |
|:-------------|:---------|:-----------------------------------|
| `id`         | `string` | **Mandatory**. The registration ID |



## Stack used

**Back-end:** Java, Docker


## Authors

- [@Emersondll](https://emersondll.github.io)


## 🔗 Links
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/stackdeveloper/)


