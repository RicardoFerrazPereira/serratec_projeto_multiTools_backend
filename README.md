# Projeto MultiTools Backend

Projeto Aplicado da Residência em Desenvolvimento Web FullStack [Serratec] 

## Sobre o projeto:

Projeto MultiTools é um programa Gerenciador de projetos, onde o usuário Administrador cria um projeto e convida um(s) usuário(s) que fará parte desse projeto.</br>
</br>
O Usuário convidado a participar do projeto, vai logar no site, acessar os projetos em que foi convidado e inserir as ferramentas que vão ser utilizadas nesse projeto.</br>
</br>
O Usuário Administrador do site realiza o orçamento do pacote de ferramentas do projeto para planejar meus custos.

### Relacionamento:

* Um projeto pode ter um ou mais usuários;
* Um usuário pode participar de um ou mais projetos;
* Um projeto pode ter uma ou várias ferramentas.

### Funcionalidades do usuário convidado:

* Realizar cadastro para criar uma conta;
* Recuperar senha;
* Selecionar ferramentas da Área de Busca para inserir em um projeto;
* Acessar a área de projetos para observar as ferramentas relacionadas a eles;
* Usar filtros para facilitar o processo de busca por ferramentas;
* Excluir ferramentas de um projeto.
* Selecionar a opção "Meu Perfil" para editar minhas informações e sair da conta

### Funcionalidades do usuário Administrador:

* Cadastrar novos projetos;
* Relacionar usuários a cada projeto;
* Realizar o orçamento do pacote de ferramentas do projeto;


### Requisitos:

* Construir uma API conectada a um banco de dados;
* CRUD completo para projeto, ferramenta e usuário;
* Autenticação e autorização (OAuth, JWT);
* Conteinerização da aplicação;


## 🛠️ Ferramentas utilizadas no projeto:

* [Eclipse](https://www.eclipse.org/) - IDE usada
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Postman](https://www.postman.com/) - Testar a API
* [Swagger](https://swagger.io/) - Consumo e visualização de serviços de uma API REST
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework Java


