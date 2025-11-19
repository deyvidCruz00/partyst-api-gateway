# Partyst API Gateway

API Gateway con Spring Cloud Gateway que enruta tráfico al balanceador de carga Nginx.

## Arquitectura

```
Cliente → API Gateway (Spring Cloud) → Load Balancer (Nginx) → Backend Nodes (3x)
```

## Características

- ✅ **Enrutamiento**: Redirige todas las peticiones al balanceador de carga
- ✅ **CORS**: Configurado para aceptar peticiones de cualquier origen
- ✅ **Circuit Breaker**: Protección contra fallos en cascada
- ✅ **Rate Limiting**: Límite de peticiones por segundo
- ✅ **Retry Logic**: Reintento automático de peticiones fallidas
- ✅ **Fallback Endpoints**: Respuestas de error cuando servicios no están disponibles
- ✅ **Logging**: Registro de todas las peticiones y respuestas
- ✅ **Health Checks**: Monitoreo del estado del gateway

## Rutas Configuradas

| Ruta | Destino | Descripción |
|------|---------|-------------|
| `/auth/**` | Load Balancer | Autenticación (registro, login) |
| `/api/**` | Load Balancer | API general |
| `/projects/**` | Load Balancer | Gestión de proyectos |
| `/users/**` | Load Balancer | Gestión de usuarios |

## Configuración

### Variables de Entorno

- `PORT`: Puerto del gateway (default: 8080)
- `SPRING_PROFILES_ACTIVE`: Perfil activo (prod, dev)

### Endpoints de Actuator

- `/actuator/health`: Estado del gateway
- `/actuator/gateway/routes`: Rutas configuradas
- `/actuator/metrics`: Métricas del sistema

## Desarrollo Local

```bash
# Compilar
mvn clean package

# Ejecutar
java -jar target/api-gateway-1.0.0.jar

# O con Maven
mvn spring-boot:run
```

## Despliegue en Render

1. Crear repositorio Git y push del código
2. Conectar repositorio en Render
3. Render detectará `render.yaml` automáticamente
4. Desplegar servicio

## Pruebas

```bash
# Health check
curl http://localhost:8080/actuator/health

# Registro a través del gateway
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"test","email":"test@test.com","password":"Test123!","name":"Test"}'

# Ver rutas configuradas
curl http://localhost:8080/actuator/gateway/routes
```

## URL del Balanceador

El gateway apunta a: `https://partyst-loadbalancer.onrender.com`

Este balanceador distribuye tráfico entre 3 nodos backend usando Nginx.
