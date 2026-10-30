# Estructura-de-datos-unidad-2-protocolo-individual
Sebatian De Jesus Arango Sierra / codigo: 7502510024

# Control de prodcutos por codigo (ejercicio #2)

## Description

Programa en Java que administra un arreglo de objetos `Producto` (código, nombre, precio y cantidad disponible) y permite:

- **Búsqueda lineal** 
- **Búsqueda de productos por nombre parcial** 
- **Ordenamiento por código** 
- **Búsqueda binaria** 
- **Consulta de códigos inexistentes**
- **Conteo de comparaciones** 


## Estructura del proyecto

- **Domain:** clases que describen los datos, sin lógica de búsqueda.
- **Business:** los algoritmos de búsqueda y ordenamiento.
- **Main:** la interacción con el usuario (menú por consola).
- **Test:** pruebas automáticas

## Requisitos

- Java 17 o superior. Verifique con `java -version` y `javac -version`.

## Cómo ejecutar

### Desde IntelliJ IDEA
1. Abra la carpeta del proyecto.
2. Ejecute `Main/main.java` (botón verde junto a `main`) para usar el menú.
3. Ejecute `Test/Pruebas.java` para correr los casos de prueba.

## Uso del menú

| Opción | Acción que hace |
|-------:|--------|
| 1 | Listar productos |
| 2 | Búsqueda lineal por código |
| 3 | Búsqueda parcial por nombre |
| 4 | Ordenar por código |
| 5 | Búsqueda binaria por código (requiere haber ordenado) |
| 6 | Comparar lineal vs. binaria para un mismo código |
| 0 | Salir |

> **Nota:** la búsqueda binaria solo es válida si el arreglo está ordenado de forma creciente por código. El programa lo verifica y no la ejecuta si no se cumple.

## Complejidad

| Algoritmo | Mejor caso | Peor / promedio | Requisito |
|-----------|-----------|-----------------|-----------|
| Búsqueda lineal por código | O(1) | O(n) | Ninguno |
| Búsqueda parcial por nombre | O(n) | O(n·m·k) ≈ O(n) | Ninguno |
| Ordenamiento por inserción | O(n) | O(n²) | Ninguno |
| Búsqueda binaria por código | O(1) | O(log n) | Ordenado por código |




