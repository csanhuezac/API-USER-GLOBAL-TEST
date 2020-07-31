# API-USER-GLOBAL-TEST
ejercicio practico postulacion

REQUISITOS SOFTWARE

    * apache-maven-3.6.1
    * Java 8
    * eclipse version 2019-03




DEPLOYANDO EL PROYECTO

 

1.- CLONAR DE REPOSITORIO GITHUB CODIGO FUENTE
  
       git clone https://github.com/csanhuezac/API-USER-GLOBAL-TEST.git  

2.- DEPLOY DEL PROJECTO

   Ejecuta los siguientes comandos:
   
        mvn clean 
        mvn install
        mvn spring-boot:run
       
3.- EJECUTAR TEST 

   Ejecuta el siguiente comandos:
   
         mvn test

4.- EJECUTAR SERVICIO 

  a) Create User:
  
    Method Http : POST
    URL Service : http://localhost:8080/api/user/create
    Request     : {  
                    "name":"cLopez",
                    "password":"Clop23",
                    "email":"clop@mail.com",
                    "phones": [{
                       "number":1234567,
                       "citycode":"02",
                       "countrycode":"56"
                    }]
                 }
    
  b) Get All User:
  
    Method Http : GET
    URL Service : http://localhost:8080/api/user/{id}
    Ejemplo     : http://localhost:8080/api/user/5dbd8386-2323-4475-bc1d-1024be75280c
    
 5.- CONSOLA BASE DE DATOS
 
      JDBC URL: jdbc:h2:mem:mydb
      URL     : http://localhost:8080/console
      USER    : sa
      PASSWORD: admin
    


