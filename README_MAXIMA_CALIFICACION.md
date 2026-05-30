# Taller de automatización - Screenplay BDD

Proyecto reestructurado para cumplir las observaciones del profesor Robinson Coronado:

- Actor principal definido: **Cliente comprador**.
- Escenarios en Gherkin con ruta feliz, caminos excepcionales, cobertura y estado del ambiente.
- `Task`: acciones de negocio.
- `Interaction`: detalle operativo o “la menuda” (`ClickOn`, `EnterValue`, `ScrollTo`, `SelectOption`, `Pause`).
- `Question`: validaciones limpias sin asserts complejos dentro de StepDefinitions.
- Datos de prueba centralizados en `utils/Constants.java` y `utils/TestData.java`.
- Generación de reporte Serenity BDD en `target/site/serenity/index.html`.
- Delay observable entre pasos mediante `Pause`, como exige la rúbrica.

## Comando de ejecución

```bash
gradle clean test aggregate --info
```

O, si se usa wrapper:

```bash
./gradlew clean test aggregate --info
```

En Windows Git Bash:

```bash
chmod +x gradlew
./gradlew clean test aggregate --info
```

## Reporte

Después de ejecutar, abrir:

```text
target/site/serenity/index.html
```

## Escenarios implementados

1. Compra E2E exitosa de dos productos disponibles.
2. Login inválido.
3. Checkout sin información obligatoria.
4. Retiro de producto del carrito.
5. Ordenamiento por precio de menor a mayor.
6. Validación de acceso al inventario después del login.

## Estructura clave

```text
src/main/java/co/edu/udea/calidad/automatizacion
├── interactions
├── models
├── questions
├── tasks
├── userinterfaces
└── utils

src/test/java/co/edu/udea/calidad/automatizacion
├── runners
└── stepdefinitions

src/test/resources/features/saucedemo
└── compra_e2e.feature
```
