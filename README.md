API na linguagem (JAVA) para gerência de usuários. 
Tem as seguintes rotas:                                                                           
a) Cadastro de usuario com nome (texto), documento (numerico) e um array de contatos contendo email (texto), telefone (numerico), flag principal (bit) (metodo post)
Obs: a flag principal é um campo bit que determina qual o contato principal
b) Busa usuario pelo filtro documento (metodo get)
c) Alterar dados usuario, email e contato (metodo put)
d) Deletar usuario pelo documento (metodo del)
2 - Subir os arquivos no git hub
a) Fornecer a caminho do github.
b) criar um arquivo readme com as instruções da api.

listAll() e createUsuario()
http://localhost:8080/api/v1/people

findByCpf(), updateByCpf(), deleteByCpf(), 
http://localhost:8080/api/v1/people/{cpf}

