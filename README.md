<div align="center">
  <h1>📚 BookShop - E-commerce de Livros Digitais</h1>
</div>


<p align="center">
  <img src="Logo BookShop.jpeg" alt="" width="300">
</p>

Bem-vindo ao **BookShop**, um e-commerce desenvolvido para a compra e venda de livros digitais, criado como parte do Projeto Final da disciplina de **API RESTful** da **Residência de Software**.

![GitHub repo size](https://img.shields.io/github/repo-size/badges/shields?style=plastic)
![Java Version](https://img.shields.io/badge/Java-17-blue?style=plastic)

## 🎓 **Sobre o Projeto**

Este projeto foi desenvolvido como parte da conclusão do curso de API RESTful, sob orientação da professora **Jacqueline Oliveira**, com o objetivo de criar um e-commerce funcional para livros digitais. O projeto é chamado **BookShop**, e todas as funcionalidades foram implementadas de acordo com os requisitos fornecidos, utilizando uma arquitetura robusta e moderna.

## 👥 **Integrantes do Projeto**

- Samuel Teldison
- Caio Pacheco
- Vinicius Ramos
- Rafael Januzzi
- Rafael Guberman

## 🛠️ **Tecnologias Utilizadas**

- **Java** com **Spring Boot** para a criação da API.
- **Flyway** para o controle de versão do banco de dados.
- **JWT** para autenticação e controle de acesso.
- **DTOs** para a transferência de dados entre as camadas da aplicação.
- **Swagger** para documentação da API.
- **Trello** para gestão de tarefas e acompanhamento do progresso.

## 🚀 **Funcionalidades Implementadas**

- 📦 **CRUD Completo**: Para todos os recursos da API (livros, clientes, pedidos).
- 🧾 **Relatório de Pedidos**: Geração automática de um relatório detalhado com:
  - ID do pedido
  - Data do pedido
  - Valor total
  - Itens do pedido (código, nome do produto, preço unitário, quantidade, percentual de desconto e valor líquido).
- 📧 **Envio de E-mails**: Ao cadastrar um novo pedido, um e-mail é enviado ao cliente com o relatório.
- 🏷️ **Validações Personalizadas**: Mensagens de erro personalizadas para exceções de item não encontrado e validações de cadastro.
- 🔒 **Autenticação e Controle de Acesso**: Implementado com **JWT** e **Spring Security**.

## 📚 **Regras de Negócio**

- Cálculo automático dos valores bruto e líquido de cada pedido.
- Armazenamento do valor total do pedido (soma dos valores líquidos dos itens).
- Validação do CEP através de uma API externa.
  
### **Regras Opcionais Implementadas**
- Data do pedido não pode ser retroativa.
- Não é permitido cadastrar produtos com descrições idênticas ou clientes com o mesmo CPF/e-mail.

## ✅ **Checklist do Projeto**

- [x] Implementação do CRUD para todos os recursos
- [x] Geração de relatórios de pedidos
- [x] Validações personalizadas e envio de e-mail
- [x] Implementação de segurança com JWT

## 🗂️ **Organização do Código**

- **/src/main/java**: Contém o código-fonte da API
- **/src/main/resources**: Contém os recursos de configuração e scripts de banco de dados
- **/docs**: Documentação adicional do projeto

## 📞 **Contato**

Em caso de dúvidas ou sugestões, entre em contato com um dos integrantes do projeto:

- **Samuel Teldison**: [Email](mailto:hometeldison@gmail.com)
- **Caio Pacheco**: [Email](mailto:caiojunqueirapacheco@gmail.com)
- **Vinicius Ramos**: [Email](mailto:viniramospimenta@gmail.com)
- **Rafael Januzzi**: [Email](mailto:rafael_januzi@hotmail.com)
- **Rafael Guberman**: [Email](mailto:rafaeltyf@gmail.com)
