# Desafio_cielo_backend

## Desafio1:

Nesse desafio eu criei uma tabela usuário que ao cadastra-lo, atraves do userType(PF/PJ), vai automaticamente cadastrar esse usuário na tabela em que foi escolhido seu userType.
Para as validações eu criei dois servicos, Validations e Responses, para retornar uma resposta apropriada para cada campo dos usúarios, porem por causa do pouco tempo que tive eu
não consegui me aprofundar mais em alguns poucos detalhes das validações com as mensagens, por isso que por segurança eu tambem utilizei o jakarta.validation para garantir que não seja cadastrado dados errados;
## Se for PF:
{
  "name": "name",
  "email": "email",
  "cpf": "cpf",
  "mcc": "mcc,
  "userType": "userType"
}
## Se for PJ:
{
  "name": "name",
  "email": "email",
  "cpf": "cpf",
  "mcc": "mcc,
  "razaoSocial": "razaoSocial",
  "cnpj": "cnpj"
  "userType": "userType"
}

Para vizualizar o swagger com a api toda mapeada e o CRUD completo eu tilizei o springdoc-openapi-starter-webmvc-ui, que permite acessar o swagger somente accessando a rota:
#http://localhost:8080/swagger-ui/index.html#/
