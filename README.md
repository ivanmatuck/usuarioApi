Requisitos:

API na linguagem Java, com framework Springboot e banco de dados H2 para gerência de usuários. 

Tem as seguintes rotas:                                                                           
a) Cadastro de usuario com nome (texto), documento (numerico) e um array de contatos contendo email (texto), telefone (numerico), flag principal (bit) (metodo post)
Obs: a flag principal é um campo bit que determina qual o contato principal
b) Busa usuario pelo filtro documento (metodo get)
c) Alterar dados usuario, email e contato (metodo put)
d) Deletar usuario pelo documento (metodo del)
2 - Subir os arquivos no git hub
a) Fornecer a caminho do github.
b) criar um arquivo readme com as instruções da api.

Link publico para collection de testes POSTMAN:
https://www.getpostman.com/collections/25c653cddfbd16eb243f

Adicionar Usuario com Contatos:

POST http://localhost:8080/api/v1/people

Payload Request: JSON de Usuário com Array de Contatos
{
"name": "Teste1",
"cpf": 14888215809,
"contatosUsuarios":
    [{
    "email": "novo1@hotmail.com",
    "telefone": "(11)94221-9383",
    "isContatoPrincipal": "true"
    },
    {
    "email": "novo2@hotmail.com",
    "telefone": "(11)94223-9393",
    "isContatoPrincipal": "false"
    }]
}


Editar contatos do usuário por CPF (apaga os salvos e insere novos)

PUT http://localhost:8080/api/v1/people{cpf}

Payload Request: JSON de Array de contatos
{
"contatosUsuarios":
    [{
    "email": "novo1@hotmail.com",
    "telefone": "(11)94221-9383",
    "isContatoPrincipal": "true"
    },
    {
    "email": "novo2@hotmail.com",
    "telefone": "(11)94223-9393",
    "isContatoPrincipal": "false"
    }]
}

Deletar usuarios por CPF
DELETE http://localhost:8080/api/v1/people{cpf}

Busca todos os usuários salvos
GET http://localhost:8080/api/v1/people

Busca Usuário por CPF
GET http://localhost:8080/api/v1/people/{cpf}

