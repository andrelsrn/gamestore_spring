# 🎮 BuyToPlay - API REST

> API RESTful desenvolvida com Spring Boot para gerenciamento de um e-commerce de jogos e consoles. Projeto desenvolvido durante o bootcamp da Generation Brasil.

---

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3**
    * Spring Data JPA
    * Spring Web
    * Validation (Jakarta Validation)
* **MySQL** (Banco de dados relacional)
* **Lombok** (Produtividade e código limpo)
* **Apidog / Postman** (Testes de rotas)

---

## 🗄️ Modelo do Banco de Dados (DER)

A aplicação conta com um relacionamento de **1 para N** entre `Categoria` e `Produto`:
* Uma **Categoria** possui vários **Produtos**.
* Um **Produto** pertence a apenas uma **Categoria**.

---

## 🚀 Endpoints da API

### 📂 Categoria (`/categorias`)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/categorias` | Listar todas as categorias |
| `GET` | `/categorias/{id}` | Buscar categoria por ID |
| `GET` | `/categorias/genero/{genero}` | Buscar categorias por gênero |
| `POST` | `/categorias` | Cadastrar uma nova categoria |
| `PUT` | `/categorias` | Atualizar uma categoria existente |
| `DELETE` | `/categorias/{id}` | Deletar uma categoria |

### 🕹️ Produto (`/produtos`)

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/produtos` | Listar todos os produtos |
| `GET` | `/produtos/{id}` | Buscar produto por ID |
| `GET` | `/produtos/nome/{nome}` | Buscar produtos por nome |
| `POST` | `/produtos` | Cadastrar um novo produto |
| `PUT` | `/produtos` | Atualizar um produto existente |
| `DELETE` | `/produtos/{id}` | Deletar um produto |

---

## 📝 Exemplo de JSON para Requisições

### Cadastrar Produto (`POST /produtos`)

```
{
"nome": "God of War Ragnarök",
"console": "PlayStation 5",
"preco": 349.90,
"quantidadeEstoque": 20,
"imagem": "https://i.imgur.com/gowragnarok.jpg",
"categoria": {
"id": 1
}

```
---

## 💻 Como Rodar o Projeto Localmente

```
1. **Clone o repositório:**
   git clone https://github.com/SEU_USUARIO/buytoplay.git

2. **Configure o banco de dados (`application.properties`):**
   spring.datasource.url=jdbc:mysql://localhost:3306/db_buytoplay?createDatabaseIfNotExist=true&serverTimezone=UTC
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update

3. **Execute a aplicação:**
   mvn spring-boot:run
```