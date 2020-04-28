# transaction-validation-service
Exemplo de um Micro-Serviço em Quarkus para validações de transações.

## Requisitos para iniciar a aplicação em desenvolvimento
**MongoDB**. Pode ser fácilmente iniciado com um container Docker.

```shell script
docker run --name mongodb -d mongo
```
**JDK 1.13** ou superior para compilar.
```shell script
./gradlew application:quarkusDev
```

## Validações
As validações ocorrem toda vez que uma transação é recebida. Todo o processo de validação é assincrono, facilitando a escalabilidade da aplicação.
- Para transações do tipo "CREDIT_CARD", executa validação conferindo se outra transação de mesmo valor não foi efetuada a menos de 5 minutos.
- Para transações do tipo "ATM", "CREDIT_CARD", "MOBILE" e "TRANSFER", executa validação conferindo se o rementente ou o receptor estão como situação "ILLEGAL" numa listra restritiva.
- As validações podem ser conferidas via API.

## API
A API é utilizada para fins de testes e validações. Num cenário ideal, todos os eventos podem ser recebidos de maneira assincrona, via Kafka.

### GET
[/event](#get-event)

[/person](#get-person)

[/transaction](#get-transaction)

[/validation](#get-validation)

### POST
[/person](#post-person)

[/transaction](#post-transaction)

#### GET /event
Recupera todos os eventos gerados pela aplicação.
**Response** 200 OK
```json
[
  {
    "code":  "event1",
    "command": {
      "personCode": "sender1",
      "personSituation": "LEGAL",
      "commandType": "CREATE_PERSON"
    },
    "date": 1588083071905
  },
  {
    "code":  "event2",
    "command": {
      "transactionCode": "transaction1",
      "transactionType": "CREDIT_CARD",
      "transactionValue": 100.00,
      "transactionDate": 1588083071905,
      "transactionSenderCode": "sender1",
      "transactionReceiverCode": "receiver1",
      "commandType": "CREATE_TRANSACTION"
    },
    "date": 1588083071905
  },
  {
    "code":  "event3",
    "command": {
      "transactionCode": "transaction1",
      "transactionType": "CREDIT_CARD",
      "transactionValue": 100.00,
      "transactionDate": 1588083071905,
      "transactionSenderCode": "sender1",
      "transactionReceiverCode": "receiver1",
      "commandType": "CREATE_TRANSACTION"
    },
    "date": 1588083071905
  },
  {
    "code":  "event4",
    "command": {
      "transactionCode": "transaction1",
      "validationType": "CREDIT_CARD_SUCCESS",
      "message": "Message",
      "commandType": "CREATE_VALIDATION"
    },
    "date": 1588083071905
  },
  {
    "code":  "event5",
    "command": {
      "transactionCode": "transaction1",
      "transactionSenderCode": "sender1",
      "transactionReceiverCode": "receiver1",
      "commandType": "RESTRICTED_LIST_VALIDATION"
    },
    "date": 1588083071905
  },
  {
    "code":  "event6",
    "command": {
      "transactionCode": "transaction1",
      "transactionSenderCode": "sender1",
      "transactionValue": 100.00,
      "transactionDate": 1588083071905,
      "commandType": "VALIDATE_CREDIT_CARD_TRANSACTION"
    },
    "date": 1588083071905
  },
  {
    "code":  "event7",
    "command": {
      "transactionCode": "transaction1",
      "transactionType": "CREDIT_CARD",
      "transactionValue": 100.00,
      "transactionDate": 1588083071905,
      "transactionSenderCode": "sender1",
      "transactionReceiverCode": "receiver1",
      "commandType": "VALIDATE_TRANSACTION"
    },
    "date": 1588083071905
  }
]
```

#### GET /person
Recupera todas as pessoas conhecidas pela aplicação. Apenas para teste, numa solução ideal as pessoas viriam de uma lista externa.

**Response** 200 OK
```json
[
  {
    "code":  "person1",
    "situation": "LEGAL"
  },
  {
    "code":  "person2",
    "situation": "ILLEGAL"
  }
]
```

#### GET /transaction
Recupera todas as transações recebidas pela aplicação.

**Response** 200 OK
```json
[
  {
    "code":  "transaction1",
    "type": "CREDIT_CARD",
    "value": 100.00,
    "date": 1588083071905,
    "senderCode": "sender1",
    "receiverCode": "receiver1"
  }
]
```

#### GET /validation
Recupera todas as validações executadas pela aplicação para consulta.

**Response** 200 OK
```json
[
  {
    "code":  "validation1",
    "transactionCode": "transaction1",
    "type": "CREDIT_CARD_SUCCESS",
    "date": 1588083071905
  },
  {
    "code":  "validation1",
    "transactionCode": "transaction1",
    "type": "CREDIT_CARD_VIOLATION",
    "date": 1588083071905,
    "message": "Violation"
  }
]
```
#### POST /person
Insere uma pessoa para fins de testes na aplicação. Apenas para teste, numa solução ideal as pessoas viriam de uma lista externa.

**Request**

Content-Type:application/json

```json
{
  "personSituation": "LEGAL"
}
```

**Response** 204 No Content

#### POST /transaction
Insere uma transação para fins de testes na aplicação. Apenas para teste, numa solução ideal as transações viriam de um sistema legado, via Kafka com a utilização do JDBC Connector.

**Request**

Content-Type:application/json

```json
{
  "transactionCode": "transaction1",
  "transactionType": "CREDIT_CARD",
  "transactionValue": 100.00,
  "transactionDate": 1588083071905,
  "transactionSenderCode": "sender1",
  "transactionReceiverCode": "receiver1"
}
```

**Response** 204 No Content

## Stack de tecnologia:
- JDK 1.13
- Gradle 6.3
- Quarkus 1.3.2.Final
    - Vertx
    - RestEasy
    - Jackson
    - RestAssured
- MongDBDriver 2.12.3
- Morphia 1.5.3
- JUnit 5.6.2
- Mockito 3.3.3