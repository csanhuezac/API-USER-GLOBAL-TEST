# API-USER-GLOBAL-TEST
ejercicio practico postulacion

REQUISITOS SOWTWARE:

*apache-maven-3.6.1
*Java 8
*eclipse version 2019-03

INSTALACION PROJECTO

1. CLONAR DE REPOSITORIO GITHUB CODIGO FUENTE

   git clone https://github.com/csanhuezac/API-USER-GLOBAL-TEST.git  
   
2. IMPORT PROJECT ECLIPSE :

    File > import > Maven > Existing Maven projects
  
    Elegir directorio de clonacion del projecto
   
3.- INSTLACION DE DEPENDENCIAS 

  Abrir ubicacion con terminal ejecutar :
  
      mvn install
  
4.- DEPLOY PROYECTO 

  a) Abrir ruta de la clase main :
  
      Java Resources > src/main/java > com.test.globalLogic.api_rest > ApiRestApplication.java
      
  b) Deploy Server Java Aplication
  
       Boton derecho sobre la clase main "ApiRestApplication.java" > Run As >Java Aplication
       
5.- EJECUTAR SERVICIO 

  a) Create User:
  
    Method Http : POST
    URL Service : http://localhost:8080/api/user/create
    
  b) Get All User:
  
    Method Http : GET
    URL Service : http://localhost:8080/api/user/
 
    


