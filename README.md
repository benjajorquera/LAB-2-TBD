# Lab-2-TBD
Laboratorio 2021-2

# Instrucciones:

1. Crear la base de datos con postgresSQL.

2. Restaurarla utilizando el Backup-dbvoluntariado.sql

4. Cambiar la configuración de la bd en backend > src > main > java\cl\tbd\proyecto1 > repositories > DatabaseContext.java con el nombre de la base de datos y la respectiva contraseña.

5. Crear una base de datos en MongoDB con nombre "dbvoluntariado", en ella crear la colección "voluntarios" y "habilidad"

# Para correr el proyecto

## Frontend
En la carpeta 'frontend' ejecutar en una terminal:

    npm install
    npm run dev

Por defecto correrá en localhost:3000/

## Back end
En la carpeta 'backend'  ejecutar en una terminal: 

    gradlew boot run

Por defecto correrá en localhost:8080/
