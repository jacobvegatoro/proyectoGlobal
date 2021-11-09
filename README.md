# Proyecto Global

Este proyecto consiste en una aplicación que expone una API RESTful para la creación de usuarios.
El formato de recepción de datos es JSON, así como también su retorno.

## Comenzando

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

### Pre-requisitos

Para el caso de desarrollo se utilizaron las siguientes herramientas:

- Eclipse 
- Spring Tools Suite 4
- H2 como base de datos en memoria
- jUnit para pruebas unitarias
- Hibernate 

### Instalación 

1.- Abre un terminal en MacOS, o bien una consola de comandos en Windows

2.- Escribe el siguiente comando y presiona ENTER
```
git clone https://github.com/givanthak/spring-boot-rest-api-tutorial.git
```
3.- Abre el archivo de propiedades del proyecto en src/main/resources/application.properties 

4.- Cambia los datos del usuario y clave de la base H2 embebida

### Ejecuta las API
Esta API permite crear un usuario en sistema. Recibe un JSON, y retorna un token JWT 

```
POST /api/v1/users/crear 

```
La siguiente API retorna el listado de usuarios registrado en la base de datos. Se le debe entregar el token generado por el proceso anterior

```
GET /api/v1/users

```

## Autor️

* **Jacob Vega Toro** - *Trabajo Inicial* - [jacobvegatoro](https://github.com/jacobvegatoro)
