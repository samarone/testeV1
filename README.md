# Teste V1

Este é projeto experimental de uma API Rest que cuida do ciclo de vida de uma entidade Cliente.

## Começando

Segue então o passo a passo de como rodar o projeto em seu ambiente de desenvolvimento.

### Pré-requisitos

1. Git 2.7.4 ou superior
2. Java JDK 1.8 ou superior
3. Maven 3.5.3 ou superior
4. Docker 18.06.1-ce ou superior **(Opcional)**
5. Heroku CLI 7.18.10 ou superior **(Opcional)**

Ou itens 4 e 5 serão usados para deploy na nuvem, caso não seja o objetivo, não se faz necessário a instalação dos mesmos.

### Obtendo os fontes

1. Fazer o checkout do projeto em: https://github.com/samarone/testeV1
```
$ git clone https://github.com/samarone/testeV1
```
2. Entrar na pasta do projeto
```
$ cd testeV1
```
3. Limpar, compilar, testar e empacotar

Neste passo todos as dependências do projeto serão baixadas, então o projeto será compilado, os testes automatizados irão rodar e, em caso de sucesso, o projeto será empacotado e ficará pronto para ser executado localmente.

```
mvn clean compile test package
```

4. Rodando!
```
mvn spring-boot:run
```
Será exibido a mensagem final:
```
2018-12-03 20:46:01.127  INFO 27544 --- [           main] s.samarone.testeV1.TesteV1Application    : Started TesteV1Application in 6.724 seconds (JVM running for 11.065)
```

## Experimentando a API

Junto a aplicação também fica disponível o **swagger**, que deve estar acessível através de:
```
http://localhost:8080/swagger-ui.html
```
Abra seu navegador e aponte para essa url. Através da swagger-ui será possivel conhecer todos os endpoints e também a estrutura deste, assim como testar as chamadas e seus resultados.

## Documentação da API

Usando Spring Rest Docs foi gerado um html com a documentação de utilização da api, tal arquivo se encontra na raíz do projeto com nomde de [api-guide.html](api-guide.html) , abra-o em seu ambiente de desenvolvimento usando seu navegador preferido.

## Colocando em Produção

O projeto por ter a estrutura de **Spring-Boot** pode ser implantado na maioria dos provedores do mercado, tais como: AWS, Heroku, OpenShift, Cloud Foundry entre outros. Mas pelo natureza experimental foi decidido disponibilizar o passo a passo de como implementar no Heroku em detalhes e também como uma imagem docker para que possa ser usada em outros provedores que suportem.

### Heroku

Existem algumas formas de colocar nosso projeto para rodar na heroku, e uma mais simples é utilizando o próprio git para tal.

1. Estando dentro da pasta root do projeto e tendo o heroku cli instalado podemos executar:
```
heroku create -a <SEU_PREFIXO>-teste-v1
```
Onde *<SEU_PREFIXO>* deve ser substituído por algum valor de sua preferência.
Esse comando cria um novo App em sua conta heroku e também associa um remote chamado *heroku* a seus repositórios remotos do projeto.

2. Precisamos agora criar uma instância de banco de dados e associar a essa aplicação dentro da heroku, fazendo:
```
$ heroku addons:create heroku-postgresql
Creating heroku-postgresql on ⬢ <SEU_PREFIXO>-teste-v1... free
Database has been created and is available
 ! This database is empty. If upgrading, you can transfer
 ! data from another database with pg:copy
Created postgresql-amorphous-90907 as DATABASE_URL
```
Após a execução com sucesso do comando podemos consultar a url onde o banco de dados está trabalhando:
```
$ heroku config
=== <SEU_PREFIXO>-teste-v1 Config Vars
DATABASE_URL: postgres://pmupewqnwvgjkq:6a1452d3014c56d938e3a5bf44d9f0f300d1cc805347e5e2f2d3440755ee240d@ec2-107-21-125-209.compute-1.amazonaws.com:5432/dnn93f7mrc72f
```
Obs: Quando o heroku provisiona uma base para a aplicação, automaticamente são registradas três variáveis dentro do ambiente da aplicação, são elas: SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD, ver uso [application-prd.yml](src/main/resources/application-prd.yml)

3. Devemos configurar agora um variável de ambiente dentro da aplicação para que possa ser usada uma base PostgreSQL:
Spring Profile Active
```
$ heroku config:set SPRING_PROFILES_ACTIVE=prd
Setting SPRING_PROFILES_ACTIVE and restarting ⬢ <SEU_PREFIXO>-teste-v1... done, v5
SPRING_PROFILES_ACTIVE: prd 
```
4. Agora devemos enviar o código da nossa aplicação diretamente para o remote heroku configurado anteriormente:
```
$ git push heroku master
```
Após alguns minutos a aplicação já está pronta para uso através da **URL: https://<SEU_PREFIXO>-teste-v1.herokuapp.com/**

2. Imagem Docker

Também foi gerado uma imagem docker do projeto e disponibilizada no Docker Hub na URL:
```
https://hub.docker.com/r/samarone/testev1/
```
Para rodar localmente um container da imagem basta executar o comando:
```
docker run -p8080:8080 samarone/testev1
```
Para implantação em produção cada provedor possui um caminho diferente que foge ao escopo desse doc, assim consulte a documentação do provedor escolhido para melhor compreensão do processo.

## Construído com

* [Java](https://www.oracle.com/java/) - Java Platform
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework and Tools
* [Eclipse](https://www.eclipse.org) - IDE
* [Linux Mint](https://linuxmint.com/) - OS

## Autor

* **Samarone Lopes** - [linkedin](https://www.linkedin.com/in/samaronelopes/)
