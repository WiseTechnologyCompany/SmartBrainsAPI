<div align="center"> <br> 
  <img align="center" alt="smart-brains-logo" height="150" width="150" src="https://github.com/user-attachments/assets/a80a0c13-12d8-4255-ae61-768fa7d35b3e" />
</div> <br>  <br> 


<div align="center">
  Esta API foi projetada com o intuito de ajudar as pessoas a exercerem um controle mais eficaz sobre seus gastos financeiros. Com ela, os usuários podem acessar e interagir de forma prática e eficiente com seus dados financeiros, permitindo o        
  monitoramento de suas despesas e a organização de suas finanças. Por meio dos endpoints disponíveis, a API oferece uma maneira ágil e segura de integrar e analisar informações, facilitando a tomada de decisões mais conscientes e o planejamento     
  financeiro pessoal.
</div>


 <br> 


## 🚀 Ferramentas Utilizadas

* 🔵 Intellij

* ☕️ Java 21

* 🦫 DBeaver

* 🐘 Postgres 16

* 🟢 Spring Boot 3.3.9

* ☁️ Sonar Cloud 6.0.1.5171


 <br> 


## 🔍 Testes

* [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=bugs)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)

* [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)

* [![Coverage](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=coverage)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)

* [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)

* [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)
  
* [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=WiseFinances_SmartBrainsAPI&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=WiseFinances_SmartBrainsAPI)


 <br> 


## 📑 Swagger UI

<br>

```bash
# URL para acessar a documentação da API 
$ http://localhost:8080/SmartBrainsAPI/swagger-ui/index.html#/
```


<br>


## 🐘 Banco de Dados

Para que a API consiga se conectar ao Banco de Dados, é necessário que o arquivo `smartbrains.properties` esteja localizado no diretório `home` do seu computador, dentro da pasta `smartbrains`, funcionando tanto em sistemas Linux quanto Windows.


 <br>


🌐 Windows
```bash
# Caminho para Windows
$ C:\Users\<nome-do-usuario>\smartbrains\smartbrains.properties
```

🐧 Linux
```bash
# Caminho para Linux
$ /home/<nome-do-usuario>/smartbrains/smartbrains.properties
```


 <br>


**Baixe o arquivo `smartbrains.properties` e coloque-o no diretório apropriado.**

🔹 [smartbrains.properties](dist/smartbrains.properties)


<br>


## 🔐 Autenticação

 <br>

  🔹 POST
```bash
# Gera um Token JWT 
$ http://localhost:8080/SmartBrainsAPI/v1/auth
```

 <br>
 
```bash
# Exemplo de requisição
{
    "username": "email@dominio.com.br",
    "password": "senha123"
}
```


 <br>


## 🔷 Principais Métodos Disponíveis

 <br> 

🔹 GET
```bash
# Retorna todos os registros
$ http://localhost:8080/SmartBrainsAPI/v1/usuarios
```

🔹 GET
```bash
# Retorna o registro pelo ID
$ http://localhost:8080/SmartBrainsAPI/v1/usuarios/{💲id}
```

🔹 POST
```bash
# Salva um registro
$ http://localhost:8080/SmartBrainsAPI/v1/usuarios
```

🔹 PATCH
```bash
# Atualiza um registro
$ http://localhost:8080/SmartBrainsAPI/v1/usuarios/{💲id}
```

🔹 DELETE
```bash
# Deleta um registro
$ http://localhost:8080/SmartBrainsAPI/v1/usuarios/{💲id}
```


<br>


## ⚠️ Observação

<div align="left">
  Os exemplos acima são ilustrativos. Por favor, consulte a documentação da API para verificar os verbos HTTP disponíveis para cada endpoint.
</div>


<br> 


## 🖥️ Desenvolvido por:

### 📝 Linkedin: [Felipe Franco](https://www.linkedin.com)
### 📝 Linkedin: [Gustavo Correa](https://www.linkedin.com/in/gustavo-chauar-correa-946168269/)
