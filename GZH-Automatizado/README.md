# Template Automatizado WEB Cucumer Docker/Kubernetes 

## Table of Contents

- [Autores](#Autores)
- [Pré-requisitos](#pré-requisitos)
- [Libraries](#libraries)
- [Executando Testes](#Executando Testes)


## Autores

- [Geibson Lehugeur](https://www.linkedin.com/in/geibson-lehugeur/)
- [Jeferson Gonçalves](https://www.linkedin.com/in/jefgoncalves/)
- [Giullia Telles](https://www.linkedin.com/in/giullia-cardoso-telles/)

## Pré-requisitos

Devem estar instalados na maquina para a execução dos testes.

* [Java](https://www.java.com/en/download/)
* [Gradle](https://gradle.org/)
* [Firefox](https://www.mozilla.org/)
* [Kubernetes](https://kubernetes.io/)
* [Containerd](https://containerd.io/)
* [Minikube](https://minikube.sigs.k8s.io/docs/)
* [Docker](https://www.docker.com/)
* [Direitos de administrador da maquina]()


## Libraries

* [Cucumber](https://cucumber.io/) - Utilizado para o Behavior-Driven Development (BDD).
* [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) - Controla o browser para a execução dos testes.
* [Hamcrest](http://hamcrest.org/JavaHamcrest/tutorial) - a framework for writing matcher objects allowing `match` rules to be defined declaratively.

## Plugins para a IDEA
Plugins utilizados no Intellij para desenvolver o projeto

* Cucumber +
* Cucumber for Java
* Docker
* Docker Registry Explorer
* Gherkin
* GitToolsBox
* Tabnine AI Code Completion-JS, Java, Python, TS, Rust, Go, PHP & More

## Executando Testes

### Spring Profile

Para a execução do perfil `default` só e necessario executar o comando abaixo. 
```
./gradlew test
```
Para executar os testes em `Homologaçao` utilizando o perfil `hlg` a variavel de ambiente `SPRING_PROFILES_ACTIVE` precisa ser determinada com o comando abaixo:
```
SPRING_PROFILES_ACTIVE=hlg ./gradlew test
```
Para executar os testes utilizando as tags do cucumber o comando abaixo deve ser executado:
```
gradle test -Dcucumber.options="--tags @tdc"
```


### Contexto
Pode ser rodado localmente `local` (usa o Chrome) ou `remote` (nos Docker containers). O valor default é `remote`, para rodar `local` a propriedade `context` precisa ser alterada para `local`. o `docker-compose` e requisito para toda a execução mesmo localmente pode ser executado pela linha de comando com o seguinte comando.

```
./gradlew test -Dcontext=local
```

### Paralelo
O projeto esta por default configurado para rodar em paralelo. O `threads count` para execuções em paralelo são `10`
Podemos mudar para `1` para rodar em sequencia ou aumentar o `thread count` com os comandos abaixo:
```
./gradlew test -Ddataproviderthreadcount="1"
./gradlew test -Ddataproviderthreadcount="20"
```

### Localmente
No `application.yml` substitua a propriedade `context` to `local`.

* Right-click no feature file e selecione `Run 'Feature: login'`; ou
* Right-click no CucumberRunner.java file e selecione `Run 'CucumberRunner'`.


### Remote (Kubernetes - Containerd - Minikube) 
No arquivo `application.yml` a propriedade `context` e `remote`.

Minikube, Kubectl e Docker já instalados na máquina.

Criando e iniciando os nodos para teste atráves do terminal:

As variáveis `ADDRESS` e `SELENIUM_PORT` devem ser atualizadas no arquivo `WebDriverManager`.

Iniciando o Minikube

    minikube start

Mudando o runtime de containers do Minikube para o Containerd

    minikube start --container-runtime=containerd --vm=true

Mudando o gerenciador de containers do Minikube para o Docker

    minikube start --container-runtime=docker --vm=true

Verificando o Dashboard do Kubernetes

    minikube dashboard 

Os comandos abaixo devem ser executados pelo terminal na pasta `automatizado-gzh/src/test/resources`

Deploy Selenium Hub no POD do Kubernetes

    kubectl create -f selenium-hub-deployment.yaml

Deploy o Kubernetes Service

    kubectl create -f selenium-hub-svc.yaml

Deploy Selenium Chrome Container no Pod

    kubectl create -f selenium-node-chrome-deployment.yaml

Encontrando a URl do Selenium Grid Console, selecione o primeiro ip com a porta para abrir o console no browser

    minikube service selenium-hub --url

Deletando os serviços selenium-hub, service e nodes do Kubernetes

    kubectl delete -f selenium-hub-deployment.yaml
    kubectl delete -f selenium-hub-svc.yaml
    kubectl delete -f selenium-node-chrome-deployment.yaml
    Check Kubernetes Dashboard

Parando o Minikube

    minikube stop

Deletando o Minikube

    minikube delete

### Remote (Docker) 
No arquivo `application.yml` a propriedade `context` e `remote`.

Execute o comando `up` para rodar os containers do docker executando o comando:
```
docker-compose up -d --scale firefox=2 --scale chrome=2 --scale edge=2
```

* Right-click no feature file e selecione `Run 'Feature: gzhSiteNavigation'`; ou
* Right-click no CucumberRunner.java file e selecione `Run 'CucumberRunner'`.

Deligue o container docker depois do teste executando o comando:
```
docker-compose down
```

### Acompanhar execução no container
É possivel verificar a execução do teste dentro do container visualmente.
* Para acompanhar visualmente a execução dos testes, é necessario acessar a sessão do container via URL do selenium Grid;
* Para acessar a tela do container é necessário clicar no ícone da "camera" o selenium vai solicitar uma senha `pass: secret`;
```
URL: http://localhost:4444/ui/index.html#/sessions
```


