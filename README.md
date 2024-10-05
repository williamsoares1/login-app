# README - Aplicação Fullstack com React.js e Java Spring

## Descrição do Projeto

Esta aplicação foi desenvolvida a fim de aprender na prática sobre **experiência do usuário** e gerenciamento de sessões de forma eficaz.

## Estrutura do Projeto

- **Front-end**: Desenvolvido em React.js
- **Back-end**: Implementado com Java Spring
- **Docker**: Utilizado para facilitar a configuração e execução do ambiente

## Instruções de Execução

### Back-end

## Caso tenha o Docker instalado

  1. Navegue até a pasta `login-app-api/docker`:
     
     ```bash
       cd login-app-api/docker
  
  3. Execute o seguinte comando para subir a aplicação:
     
     ```bash
     docker-compose up -d --build

## Caso não tenha o docker instalado

  Você precisa ter PostgreSQL e Redis na sua maquina e que estejam funcionando nas respectivas portas configuradas no application-properties, ou altere as portas lá:

### Front-end

1. Navegue até a pasta do front-end:
   
    ```bash
    cd login-app-frontend

3. Execute o comando para iniciar a aplicação:
   
    ```bash
   npm run dev

## Funcionalidades Principais

- **Gerenciamento de Sessão**: A sessão do usuário é armazenada junto com o **Refresh-Token**.
- **Renovação Automática de Sessão**: Um **interceptor** no front-end cuida da renovação automática do token, proporcionando uma experiência de uso mais fluida.

## Tecnologias Usadas

- **Front-end**: 
  - React.js
  - Context API
  - Axios (para chamadas HTTP)

- **Back-end**:
  - Java 17
  - Spring Boot
  - Spring Security
  - Redis
  - PostgreSQL

- **Containerização**:
  - Docker
  - Docker Compose
