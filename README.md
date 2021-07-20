# Desafio tecnico Sicredi

Requisitos de sistema:
* JDK 8;
* Maven;
* Se possuir MongoDB instalado na maquina, disponibilizar acesso na porta padrao 27017, caso contrario, pode ser utilizada a maquina de mongodb disponivel na nuvem, descomentando a primeira linha do application.properties ira se conectar com a instancia na nuvem.

Como executar a aplicacao:
* No diretorio padrao do projeto, executar: mvn clean install
* Apos a build sera criado um novo jar dentro do diretorio /target/
* Acessar o diretorio /target e executar: java -jar teste-sicredi-0.0.1-SNAPSHOT.jar
* Aplicacao estara disponivel no endereco: http://localhost:8080
* Endereco da documentacao disponivel no endereco: http://localhost:8080/swagger-ui.html

Aplicacao possui integracao com o Heroku, para que cada commit no github, sera realizado build e deploy no Heroku. Disponivel no endereco:
https://teste-sicredi.herokuapp.com/swagger-ui.html