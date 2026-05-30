# Advantage Online Shopping - Screenplay BDD Automation

## Autor

Jose Alfredo Martinez Valdes

## Sitio automatizado

https://advantageonlineshopping.com/

## Objetivo del proyecto

Automatizar un flujo E2E completo sobre Advantage Online Shopping usando Screenplay Pattern, Serenity BDD, Cucumber, Selenium WebDriver, Java y Gradle.

El flujo principal valida que un cliente pueda:

1. Abrir la tienda online.
2. Registrar una cuenta nueva.
3. Seleccionar un producto disponible.
4. Agregar el producto al carrito con una cantidad definida.
5. Realizar el checkout.
6. Pagar mediante SafePay.
7. Validar la confirmación exitosa de la compra.

## Escenarios automatizados

| Tipo | Escenario |
|---|---|
| E2E / Happy Path | Register user, select product and complete payment with SafePay |
| Exception | Invalid login is rejected |
| Coverage | Add product to cart and validate quantity |
| Data Driven | Search products by name using Scenario Outline |

## Tags implementados

- @e2e
- @happy_path
- @registration
- @checkout
- @exception
- @login
- @coverage
- @cart
- @search
- @data_driven
- @smoke
- @regression

## Tecnologías

- Java
- Gradle
- Serenity BDD
- Cucumber
- Selenium WebDriver
- Screenplay Pattern
- ChromeDriver

## Arquitectura Screenplay

```text
src/test/java/co/edu/udea/calidad/advantage
├── interactions
├── questions
├── runners
├── stepdefinitions
├── tasks
└── utils

src/test/resources/features/advantage
└── advantage_purchase_e2e.feature

## Modular Feature Structure

The project was improved by separating the original feature into business modules:

```text
src/test/resources/features/advantage
├── checkout
│   └── checkout_e2e.feature
├── login
│   └── login_exception.feature
├── cart
│   └── cart_coverage.feature
└── search
    └── search_data_driven.feature



