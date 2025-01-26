## PoliSalud - EPN - G4

PoliSalud es un sistema medico desarrollado por el grupo G4 de la Escuela Politécnica Nacional (EPN). Este proyecto está diseñado utilizando la arquitectura n-tier, que separa la aplicación en capas lógicas para mejorar la escalabilidad, mantenibilidad y reutilización del código.


### Arquitectura n-tier

El sistema PoliSalud está dividido en las siguientes capas:

1. **Capa de Presentación (GUI)**: Maneja la interfaz de usuario y la interacción con el usuario final.
2. **Capa de Lógica de Negocio (BL)**: Contiene la lógica de negocio y las reglas de la aplicación.
3. **Capa de Acceso a Datos (DAC)**: Gestiona la comunicación con la base de datos y las operaciones CRUD.
4. **Capa de Datos**: Representa la base de datos y los datos persistentes.

### Gestión de Dependencias

El proyecto utiliza el archivo `lib/sqlite-jdbc-3.48.0.0.jar` para la conexión con la base de datos SQLite. Las dependencias se pueden gestionar desde la vista `JAVA PROJECTS` en Visual Studio Code. Más detalles se pueden encontrar [aquí](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
