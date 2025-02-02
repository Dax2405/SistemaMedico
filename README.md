## SmartTurn - EPN 

SmartTurn es un proyectoque está
diseñado utilizando la arquitectura n-tier, que separa la aplicación en capas lógicas para mejorar la escalabilidad,
mantenibilidad y reutilización del código.

### Arquitectura n-tier

El sistema PoliSalud está dividido en las siguientes capas:

1. **Capa de Presentación (GUI)**: Maneja la interfaz de usuario y la interacción con el usuario final.
2. **Capa de Lógica de Negocio (BL)**: Contiene la lógica de negocio y las reglas de la aplicación.
3. **Capa de Acceso a Datos (DAC)**: Gestiona la comunicación con la base de datos y las operaciones CRUD.
4. **Capa de Datos**: Representa la base de datos y los datos persistentes.

### Gestión de Dependencias

Las dependencias estan manejadas por `/lib`
