<img align=left src="https://i.imgur.com/P9fjzh4.png" height=150 alt="badge-challenge">

<h2 align=center>Challenge ONE ForumHub Back End - Java</h2>

<div align=center>
<img height="80" margin="10" src="https://i.imgur.com/9Gq6RS0.png">
</div>
<h2 align=center>ForumHub API</h2>



Tabela de conteúdos
=================  

<!--ts-->  

* [Sobre](#-sobre)

* [Tecnologias](#-tecnologias-e-ferramentas)

* [Features](#-features)

* [Regras de negocio da api](#-features)

  

  

## 🎯 Sobre

 Aplicação Back-end construída durante o Challenge-Alura-One-Java,  pela Oracle.

A aplicação consiste em um Forum de duvidas. 

Fique à vontade para explorar o código-fonte, enviar feedbacks ou colaborar com sugestões. Sua contribuição é muito bem-vinda!



- Foi criada uma classe personalizada "Result" para retornar o estado da requisição e o objeto.

  com codigos de resposta personalizado.



## 🛠 Tecnologias e ferramentas:

### Tecnologias

<p align="left"> <a href="https://www.java.com" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/></a> <a href="https://spring.io/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/springio/springio-icon.svg" alt="spring" width="40" height="40"/> </a> <a href="https://maven.apache.org/" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/actions/starter-workflows/main/icons/maven.svg" alt="git" width="40" height="40"/> </a> <a href="https://www.postgresql.org" target="_blank" rel="noreferrer"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/postgresql/postgresql-original-wordmark.svg" alt="postgresql" width="40" height="40"/> </a></p>  



### Ferramentas e bibliotecas

<p align="left">
<a href="https://www.jetbrains.com/idea/" target="_blank" rel="noreferrer"> <img src="https://resources.jetbrains.com/storage/products/company/brand/logos/IntelliJ_IDEA_icon.svg?_gl=1*1ls50uz*_ga*MTEwNzIzOTY3LjE2ODMyNDQ0Mzg.*_ga_9J976DJZ68*MTY4MzgyMDMxOC44LjAuMTY4MzgyMDMyNi41Mi4wLjA.&_ga=2.233017118.1603209044.1683820318-110723967.1683244438" alt="intellij idea" width="40" height="40"/> </a>
	<a href="https://git-scm.com/" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a>


</p>


- JPA / Hibernate

- Swagger(Open Api)

- Spring Boot versions

- Banco de dados em memoria h2.

  - 3.2.5
  - 4.0.0

  

  Diagrama de relacionamento das entidaes:

  ![db](https://github.com/user-attachments/assets/c949b495-475e-4909-89da-87e36201ea5a)

  

---

### 🚀 Features

- [x] Buscar Uma dúvida.

- [x] Listar todas as dúvidas.

- [x] Listar cursos cadastrados

- [x] Lista respostas.

- [x] Listar todos usuários(ADMIN).

- [x] Editar um usuário.(ADMIN)
      
- [x] Deletar um usuário(ADMIN)

- [x] Fazer uma postagem de uma dúvida.

- [x] Deletar uma postagem.

- [x] Editar uma postagem.

  

### 🚀 Regras de negocio da api:

- O usuario deve estar logado para poder fazer uma postagem.

- Um usuário não pode deletar uma postagem de outro usuário.

- Um usuário não pode editar uma postagem de outro usuario.

- Um usuário não pode deletar ou editar uma resposta de utro usuário.

- Somente Usuário com permissão de ADMIN pode acessar a rota de Usuários.

- O usuário pode estar ou não logado para ver as postagem e/ou respostas.
- 

- <h2>Como Executar</h2>
<ul>
<li>Clonar repositório git:</li>

</ul>
<pre><code>git clone https://github.com/carlos4565mana/challenge-java-alura-oracle-one-t6-Forum-Hub.git
</code></pre>
<ul>
<li>Construir o projeto:</li>

</ul>
<pre><code>./mvnw clean package
</code></pre>
<ul>
<li>Executar:</li>

</ul>
<pre><code>java -jar ./target/spring-security-jwt-0.0.1-SNAPSHOT.jar
</code></pre>
<ul>
<li></li>

</ul>

Testar ( com [Swagger ):
http://localhost:8080/swagger-ui.html

  

  

[![Linkedin Badge](https://img.shields.io/badge/-Carlos-blue?style=flat-square&logo=Linkedin&logoColor=white&link=www.linkedin.com/in/carlos-santos-3390a1224/)](www.linkedin.com/in/carlos-santos-3390a1224/)  
![Gmail Badge](https://img.shields.io/badge/-carloscal61@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:carloscal61@gmail.com)

