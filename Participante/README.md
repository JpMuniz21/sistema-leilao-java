# 🔨 Sistema de Gestão de Leilões

Sistema de gerenciamento de leilões desenvolvido em Java, focado na aplicação de conceitos de Orientação a Objetos e persistência de dados em arquivos de texto.

## 📋 Sobre o Projeto
Este projeto é uma aplicação via console (CLI) que permite o cadastro e administração de leilões, participantes e lances. O diferencial do sistema é o uso de **manipulação de arquivos (I/O)** para salvar os dados, garantindo que as informações persistam mesmo após fechar o programa.

## 🚀 Funcionalidades
* **Gestão de Participantes:** Cadastro e listagem de usuários.
* **Gestão de Leilões:** Criação, iniciação e finalização de leilões.
* **Sistema de Lances:** Registro de lances em leilões ativos.
* **Persistência de Dados:** Todos os cadastros são salvos automaticamente em arquivos `.txt` (Banco de dados em arquivo).
* **Menu Interativo:** Interface via terminal para navegação entre as opções.

## 🛠️ Tecnologias Utilizadas
* **Java** (Lógica e POO)
* **Java I/O** (FileWriter/FileReader para manipulação de arquivos)
* **VS Code** (IDE de desenvolvimento)

## 📂 Estrutura do Projeto
O código segue o padrão MVC simplificado, separando as classes de domínio (`Leilao`, `Participante`, `Lance`) da classe principal de execução (`App.java`).