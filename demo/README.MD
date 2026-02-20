 Calculadora de Juros API

API desenvolvida com Spring Boot para cálculo de juros, com autenticação usando JWT, persistência em PostgreSQL e execução com Docker.

- Tecnologias utilizadas

Java 17+

Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

JWT (Auth0)

Docker

Swagger (OpenAPI)


 - Estrutura do Projeto
controller → recebe requisições
service → regras de negócio
repository → acesso ao banco
model → entidades
config → segurança, JWT e Swagger

- Configuração (application.yml)
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/calculadora
    username: postgres
    password: 8745

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

api:
  security:
    token:
      secret: ${JWT_SECRET:my-super-secret-key}
      
 Responsável por:

conectar no banco

configurar JPA

definir a chave secreta do JWT



-  Usuário (Usuario)
@Entity
public class Usuario implements UserDetails
 O que faz:

Representa o usuário no banco

Integra com Spring Security

Campos:

id

name

email (login)

password

role


- Autenticação
- Register
@PostMapping("/register")
Fluxo:

Verifica se o email já existe

Criptografa senha com BCrypt

Salva no banco


- Login
@PostMapping("/login")
Fluxo:

Valida email e senha

Autentica com Spring Security

Gera token JWT

Retorna token


- TokenService (JWT)
@Service
public class TokenService
generateToken()

Cria o token:

.withIssuer("CalculadoraDeJuros")
.withSubject(usuario.getEmail())
.withExpiresAt(...)
.sign(algorithm)
 Explicação:

issuer → quem criou o token

subject → usuário (email)

expiresAt → tempo de expiração

sign → assinatura segura



- validateToken()
JWT.require(algorithm)
 O que faz:

valida assinatura

retorna email do usuário


- SecurityFilter (JWT)
public class SecurityFilter extends OncePerRequestFilter
Função:

Intercepta TODAS as requisições

Fluxo:

Pega header Authorization

Remove "Bearer "

Valida token

Busca usuário no banco

Autentica automaticamente

 
 
 - SecurityConfig
@Configuration
 Configura:

CSRF desativado

sessão STATELESS

rotas públicas:

/auth/login

/auth/register

resto protegido



 - Swagger (OpenAPI)
@Configuration
public class OpenApiConfig
 Função:

Configura documentação da API

Mostra:

nome da API

versão

descrição

 
 -  Docker
 docker-compose.yml

Dois serviços:

 postgres

banco de dados

 app

API Spring Boot

Comunicação:
app → postgres (rede interna do Docker)

Fluxo completo da aplicação
Register → salva usuário
Login → gera token
Cliente envia token
↓
SecurityFilter intercepta
↓
Valida token
↓
Usuário autenticado
↓
Acesso liberado




Desenvolvido por Marcos Lins 