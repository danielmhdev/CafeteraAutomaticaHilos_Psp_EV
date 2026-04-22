### Justificación

**1. Recurso compartido**

El recurso compartido es la lista `ArrayList<String> deposito`. Representa el espacio físico donde la máquina (productor) almacena los cafés y del cual los profesores (consumidores) los extraen. Al modificarse concurrentemente, requiere protección.

**2. Clase monitor**

La clase `DepositoCafe` actúa como monitor. Encapsula el recurso compartido y utiliza métodos `synchronized` (`depositarCafe` y `retirarCafe`) para garantizar que el acceso a la lista de cafés sea mutuamente excluyente (solo un hilo a la vez).

**3. Uso de wait() y notifyAll()**

Se utilizan exclusivamente dentro de los métodos `synchronized` del monitor:
- **`wait()`**: Se usa dentro de bucles `while` de comprobación. Pausa la máquina si el depósito está lleno, y pausa a los profesores si está vacío.
- **`notifyAll()`**: Se invoca al final de ambos métodos (tras añadir o retirar un café exitosamente) para avisar a los hilos en espera de que el estado del depósito ha cambiado.

**4. ¿Por qué son realmente necesarios?**

Básicamente, son los mecanismos que evitan que nuestro programa colapse o se quede bloqueado para siempre:
- El **`wait()`** sirve para que un hilo no se quede pillado en un bucle. En lugar de eso, el hilo se pone en pausa y, lo más importante, suelta "la llave" del depósito para que la máquina o los profesores puedan entrar a hacer su trabajo.
- El **`notifyAll()`** es nuestro sistema de avisos. Si no lo pusiéramos, los hilos que hemos puesto en pausa con `wait()` nunca se enterarían de que las cosas han cambiado y se quedarían durmiendo eternamente (provocando un *deadlock* o bloqueo).