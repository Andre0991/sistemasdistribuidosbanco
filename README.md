# Sistemas distribuídos

# Extras
- Inteface gráfica;
- Extrato
- Serviços para logs dos vários tipos de transferência, incluindo discriminar por cliente;
- Arquitetura dividida em pacotes de classes de serviço, dao, conversors, entidades, etc.

## API
Disponvel [aqui](https://documenter.getpostman.com/view/2571717/sistemasdistribuidos/6n5yYVd "yay").

## Executando o projeto
Por conveniência, todos os bancos estão no mesmo repositório, incluindo as classes do banco central. No entanto, este poderia ser uma aplicação separada com a mesma arquitetura e que contém apenas as classes relacionadas a ele.

Por convenção, espera-se que três instâncias do tomcat sejam executadas.
- A primeira instância representa o banco A (porta 8080).
- A segunda instância representa o banco A (porta 9080).
- A terceira instância representa o banco central (porta 10080).

## Tecnologias utilizadas

Back-end:
- Jersey (ReST)
- Jackson (Json)
- Tomcat (servidor de aplicação)
- Maven (gerenciador de pacotes)

Front-end:
- html
- css (bootstrap)
- jquery

Testes:
- postman

# Interface gráfica
A interface está disponível na pasta `interface-grafica` na raiz do projeto.

![Home](/media/home.png "home")

![Modal](/media/modal.png "modal")

![Transferências](/media/transferencias.png "transferencias")


## Nota
O projeto foi criado seguindo as instruções deste tutorial: https://www.mkyong.com/webservices/jax-rs/jersey-hello-world-example/
