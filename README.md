# Desafio_cielo_backend

# Desafio1:

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

Ja em relação aos testes, por causa da minha rotina de trabalho, eu não tive tempo  de fazer a Implementação de 70% de testes unitários, então eu apenas deixei um teste simples de ultima hora para que você avaliador(a) saiba que eu pelo menos sei fazer, e não é nada que eu não consiga implementar.

# Desafio2:

Nesse desafio eu criei uma pasta fila em service para tratar todos os metodos da fila, entre eles os de inserir um usuário na fila, remover e selecionar o primeiro da fila. Ao enviar as requisições na rota /fila do cntroller irá retornar uma fila com todos os usuários cadastrados no banco de dados por ordem de inserção, na rota /fila/first irá retornar o primeiro da fila para ser atendido. E assim como foi exigido no desafio, ao atualizar qualquer usuário em suas respectivas tabelas, automaticamente esse usuário voltará para o fim da fila, excluindo o anterior.

OBS: Eu não tive tempo de implementar a lógica de ao retornar o primeiro da fila para ser atendido, deveria excluir esse user da fila, mas é algo facil que eu posso implementar.

# Desafio3:

Nesse desafio eu criei um serviço AwsSqsUtil na pasta util, que trata toda a conexão com o SQS da AWS. 

OBS: _Eu não estava conseguindo utilizar o @Value, que é o que eu costumo usar, para trazer atraves do meu application.properties as minhas variáveis de conexão, provavelmente deveria ser algum defeito da IDE que no meu pc pessoal demora muito para carregar as coisas. Para poupar tempo e conseguir entregar eu carreguei as variáveis direto no service e voltou a funcionar._

No controller Eu criei no endpoint /sqs/produce uma lógica para capturar todos os usuários cadastrados e enviar um por um para o painel sqs da AWS, e au consumir os usuários no endpoint /sqs/consume eles são automaticamente retirados da fila. 
