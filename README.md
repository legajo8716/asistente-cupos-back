
# 🧠 Flav-IA - Backend

Este es el backend del sistema **Flav-IA**, una herramienta diseñada exclusivamente para la dirección de la carrera de 
Licenciatura informática de la UNQ, para que sus directivos, entre ellas la directoria Flavia🤭, 
puedan recibir sugerencias de asignaciones de estudiantes a comisiones, de la manera más justa y eficientemente posible, 
en pos de que conserven un poco la salud mental en cada etapa de inscripciones🙏

El LLM utilizada es GPT-3.5 de Open IA, pero con unos cambios de configuración de `spring.ai`, es compatible con otros modelos.
## 🚀 Stack Tecnológico

- Java 17
- Spring Boot 3
- PostgreSQL
- Docker + Docker Compose
- Gradle
- Spring AI (integración con LLMs)

---

## 🛠️ Requisitos

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/)
- (Opcional) JDK 17+ y Gradle si querés correrlo sin Docker

---

## ⚙️ Configuración

1. Cloná el repositorio:

   ```bash
   git clone https://github.com/FLAV-IA/asistente-cupos-back.git
   cd asistente-cupos-back
   ```

2. Copiá el archivo de ejemplo de variables de entorno:

   ```bash
   cp .env.example .env
   ```

3. Ajustá las variables en `.env` si es necesario. Este archivo define configuración sensible como credenciales de la base de datos, 
4. puertos y claves API para integración con modelos de lenguaje.

---

## 🐳 Levantar el proyecto con Docker

```bash
./dev.sh
```

Este script va a:

- Crear y levantar la base de datos PostgreSQL
- Copiar las variables al .env dónde se debe colocar credenciales
- Construir la imagen del backend
- Correr la app en `http://localhost:8080`

> **Nota 1:** El contenedor de la base de datos se inicializa con los datos necesarios si se corre en dev y test con los seeders definidos.
> **Nota 2:** El backend en sus ambientes de dev y test, tiene deshabilitado cors y admite cualquier conexión.

---

## 🧪 Endpoints y Testing

Una vez levantado, podés acceder a la API REST en:

```
http://localhost:8080/asistente
```

Si querés correr los tests manualmente desde el entorno local (fuera de Docker):

```bash
./gradlew test
```

---

## 🧩 Estructura del Proyecto

```
src
├── main
│   ├── java/com/edu/asistenteCupos  ← lógica del backend
│   └── resources                     ← configs, templates, cvs, etc.
└── test
    └── java/com/edu/asistenteCupos  ← tests unitarios e integración
```

---

## 🧹 Limpieza

Para bajar los contenedores y borrar los volúmenes:

```bash
docker-compose down -v
```

---

## 🧑‍💻 Contribución

¡Pull requests y sugerencias son bienvenidas! Si vas a colaborar, asegurate de seguir la estructura del proyecto y escribir tests para tu código.
