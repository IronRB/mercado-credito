# Mercado Crédito Challenge Backend
Este repositorio fue creado con el objetivo de resolver Challenge Back-End Mercado Libre resolviendo el caso problema de Mercado Crédito.

## Tabla de Contenidos
1. [Información General](#Información-General)
2. [Pre-requisitos](#Pre-requisitos)
3. [Comenzando](#Comenzando)
4. [Ejecutando las pruebas](#Ejecutando-las-pruebas)
5. [Despliegue ](#Despliegue)
6. [Construido con](#Construido-con)

## Información General 📖
***

## Pre-requisitos 🔧
***
* Java 11
* Postman
* Docker
* Git
* Maven

## Comenzando 🚀

Descripción del challenge:

https://github.com/IronRB/mercado-credito/blob/feature/RobertC/Challenge%20Back-End%20Practico.pdf

Documentación Javadocs:
    
    /docs/index.html

Documentación de los endpoints:

https://documenter.getpostman.com/view/10296139/Uz5Dox2H

### Instalación

Para realizar la instalación en ambiente local:

Clonar el proyecto:

    $ git clone https://github.com/IronRB/mercado-credito.git

Instalación del proyecto:

    $ mvn clean install

Empaquetar el proyecto:

    $ mvn clean package

### Ejecución

Luego para realizar la ejecución del proyecto, hay 2 opciones:

* Ejecución del jarfile del proyecto

    Ir a la ruta target:

        $ cd .\target\

    Ejecución del Jarfile

        $  java -jar challenge-0.0.1-SNAPSHOT.jar --server.port=8080

* Ejecución del dockerfile del proyecto

    Primero, crear la imagen docker:

        $ docker build --tag=challenge:latest .

    Luego, crear un contenedor apartir de la imagen creada anteriormente, ademas indicar que debe correr en el puerto 8080

        $ docker run -p8080:8080 challenge:latest

## Ejecutando las pruebas ⚙️

Ejecución de pruebas unitarias:

    $ mvn test

Ejecución de pruebas unitarias con generación de informa de cobertura:

    $ mvn clean test jacoco:report

Revisar el informe de cobertura:

    $ cd .\target\site\jacoco\
    Abrir el archivo index.html con algún navegador web

Para realizar evaluación de los endpoints realizando solicitudes HTTP, importar el archivo JSON que se encuentra en la siguiente ruta:

    $ cd .\testing\


## Despliegue 📦

Para realizar el despligue en azure, se realizó con 2 alternativas:

La primera desde usando el plugin de Azure intergrado en Maven, ejecutando los siguientes comandos:
    Primero, realizar una empaquetamiento del proyecto: 

    $ mvn clean package

Luego la configuración que va permitir la construcción del Azure App services:

    $ mvn com.microsoft.azure:azure-webapp-maven-plugin:2.5.0:config

    Con la ejecucuón de este comando, se despliega un asistente solicitando la información del id de suscripción en Azure, nombre del proyecto, sistema operativo en el que corre el proyecto y la versión de java necesaria

Luego desplegar la aplicación en Azure App Services

    $ mvn package azure-webapp:deploy

La segunda forma,es usando un GitHub Action, el cual se creó con la finalidad de que cuando se realizé un pull request a la rama master se ejecute el pipeline, para esto se creó un pipeline como código en la ruta:

    \.github\workflows\azure-webapps-java-jar.yml

<a name="Construido-con"></a>
## Construido con 🛠️

* IDE: Intellij IntelliJ IDEA 2022.1.1 (Community Edition)
* Lenguaje de programación: Java versión 11
* Framework de desarollo web: Spring Boot
* Framework para realizar pruebas unitarias: Junit
* Framework para mockear datos: Mockito
* Framework para generar reportes de cobertuta de codigo: Jacoco
* Software para testear la solución: Postman
* Base de datos: H2 database
* Servicio cloud usado para despliegue: Azure APP services
* Plataforma de repositorios: GitHub
* Herramienta para realizar despliegue automatico: GitHub Actions
* Herramienta para crear contenedor de la aplicación: Docker
* Administrador de dependencias: Maven


