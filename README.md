# Sistema de Transacciones Bancarias con RabbitMQ


---


## Arquitectura

La arquitectura se basa en un flujo con RabbitMQ como intermediario entre la obtención y el almacenamiento de transacciones.

Como funciona:

- **API GET :** entrega las  transacciones.
- **Producer:** consume la API GET, valida cada transacción y la enruta según `bancoDestino`.
- **RabbitMQ Broker:** recibe los mensajes del producer y los distribuye en 4 colas (`BANRURAL`, `GYT`, `BAC`, `BI`).
- **Consumer:** escucha las 4 colas en paralelo y procesa cada mensaje individualmente.
- **API POST:** recibe cada transacción procesada para su persistencia final.

Relación entre componentes:

1. El **Producer** obtiene y prepara transacciones desde la API GET.
2. El **Producer** publica cada mensaje en la cola correspondiente dentro de RabbitMQ.
3. El **Consumer** toma mensajes de las colas y ejecuta un POST por cada transacción.
4. El mensaje se confirma (ACK) únicamente cuando la API POST responde exitosamente.

### Flujo de Datos

1. **Producer** realiza un GET a la API y obtiene 100 transacciones bancarias
2. **Producer** valida cada transacción y la publica en la cola correspondiente según `bancoDestino`
3. **Consumer** escucha las 4 colas simultáneamente (BANRURAL, GYT, BAC, BI)
4. Por cada mensaje recibido, el **Consumer** hace POST a la API de almacenamiento con los datos de la transacción más `nombre` y `carnet` del estudiante
5. Solo se confirma (ACK) el mensaje si el POST responde con **201 Created**


---

## Video de Demostración

> **Enlace al video:**
> **[VER DEMOSTRACION DEL PROYECTO](https://drive.google.com/drive/folders/14BBjvDtSS1x2nf6sxR8m-DpS3ul8vr70?usp=sharing)**

