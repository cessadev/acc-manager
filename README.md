### Prueba Técnica para Desarrollador Java Backend (Spring) - Sector Financiero

**Objetivo:** Evaluar habilidades técnicas en Java y Spring, así como conocimientos en arquitectura, desarrollo seguro y patrones de diseño, especialmente en un contexto financiero.

---

#### 1. **Configuración Inicial**

   - Crear un proyecto Spring Boot en una versión compatible con Java 17+.
   - Configurar las dependencias para trabajar con Spring Data JPA, Spring Web, Spring Security, H2 (base de datos en memoria) para pruebas, y Lombok para simplificación del código.

---

#### 2. **Escenario: Gestión de Cuentas y Transacciones Financieras**

La empresa gestiona cuentas bancarias para sus clientes y permite realizar depósitos, retiros y transferencias entre cuentas. Para ello, es importante controlar el balance de cada cuenta y asegurar que no haya fondos insuficientes en las transacciones.

**Requerimientos:**

- **Modelado de datos**:
  - Crear una entidad `Cuenta` con los siguientes atributos: `id` (UUID), `numeroCuenta` (único), `balance` (decimal), `nombrePropietario`, `estado` (activo, inactivo).
  - Crear una entidad `Transaccion` con los atributos: `id` (UUID), `tipo` (depósito, retiro, transferencia), `monto` (decimal), `fecha`, `cuentaOrigen` (si aplica), `cuentaDestino` (si aplica).

---

#### 3. **Implementación de Funcionalidades**

- **Crear, Actualizar y Consultar Cuentas**:
  - Implementar un controlador REST para crear nuevas cuentas, actualizar datos de cuentas existentes y consultar el estado de una cuenta por `numeroCuenta`.
  - Validar que el balance inicial no sea negativo al crear una cuenta.

- **Realizar Transacciones**:
  - Implementar operaciones para depósito y retiro. Al realizar un retiro, validar que la cuenta tenga suficientes fondos.
  - Implementar la funcionalidad de transferencia entre cuentas. Validar que ambas cuentas estén activas y que la cuenta origen tenga fondos suficientes.

- **Historial de Transacciones**:
  - Crear un endpoint para consultar el historial de transacciones de una cuenta, incluyendo filtros opcionales por rango de fechas y tipo de transacción.

---

#### 4. **Reglas de Negocio y Validaciones**

- Implementar lógica para asegurar que no se realicen operaciones en cuentas inactivas.
- Limitar el monto de las transacciones a un valor máximo definido en una configuración externa (por ejemplo, un archivo `application.properties`).

---

#### 5. **Seguridad Básica**

- Implementar autenticación básica con Spring Security para proteger los endpoints, de modo que solo usuarios autenticados puedan realizar operaciones.
- Crear un rol de "Administrador" que pueda crear y consultar cuentas y ver el historial de transacciones. Los usuarios "Operadores" solo pueden realizar transacciones (depósitos, retiros y transferencias).

---

#### 6. **Pruebas Unitarias y de Integración**

- Escribir pruebas unitarias para los servicios de cuentas y transacciones, validando los casos de éxito y fallo.
- Escribir pruebas de integración para los endpoints REST, usando la base de datos H2 configurada.

---

#### 7. **Preguntas de Reflexión**

Al finalizar, pide al candidato que responda brevemente a las siguientes preguntas:

1. ¿Cómo aseguras la integridad de los datos en una transacción bancaria en un entorno de concurrencia alta?
2. ¿Qué mejoras aplicarías para asegurar el cumplimiento de las normativas de seguridad y privacidad en el manejo de datos financieros?
3. ¿Cómo implementarías un mecanismo para auditar todas las operaciones y cambios en las cuentas?

---

**Entregables Esperados**:

- Código fuente del proyecto en un repositorio Git (puede ser GitHub o un ZIP).
- Archivo README con instrucciones de configuración y ejecución.
- Respuestas a las preguntas de reflexión.

**Evaluación**:

- **Calidad del código**: uso adecuado de principios SOLID, patrones de diseño y consistencia en el estilo de código.
- **Comprensión de Spring y Java moderno**: uso adecuado de las anotaciones y configuraciones de Spring.
- **Manejo de la base de datos y JPA**: correcta implementación de relaciones y transacciones.
- **Pruebas**: cobertura de casos principales con pruebas unitarias y de integración.
- **Seguridad**: manejo adecuado de autenticación y control de acceso.

## **License**
This project is distributed under the **MIT License**.
