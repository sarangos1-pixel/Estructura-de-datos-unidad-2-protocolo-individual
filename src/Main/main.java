package Main;

import Business.BuscadorProductos;
import Domain.Producto;
import Domain.ResultadoBusqueda;
import Domain.ResultadoParcial;

import java.util.Scanner;

public class main {

    private static Producto[] productos = crearInventarioInicial();
    private static boolean ordenado = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero(sc, "Elija una opción: ");
            System.out.println();
            switch (opcion) {
                case 1:
                    listar();
                    break;
                case 2:
                    buscarLineal(sc);
                    break;
                case 3:
                    buscarParcial(sc);
                    break;
                case 4:
                    ordenar();
                    break;
                case 5:
                    buscarBinaria(sc);
                    break;
                case 6:
                    comparar(sc);
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            System.out.println();
        } while (opcion != 0);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("===== CONTROL DE PRODUCTOS POR CÓDIGO =====");
        System.out.println("Estado del arreglo: " + (ordenado ? "ORDENADO por código" : "SIN ORDENAR"));
        System.out.println("1. Listar productos");
        System.out.println("2. Búsqueda lineal por código");
        System.out.println("3. Búsqueda parcial por nombre");
        System.out.println("4. Ordenar por código");
        System.out.println("5. Búsqueda binaria por código");
        System.out.println("6. Comparar lineal vs binaria (mismo código)");
        System.out.println("0. Salir");
    }

    private static void listar() {
        System.out.println("Inventario (" + productos.length + " productos):");
        for (int i = 0; i < productos.length; i++) {
            System.out.println("  posición " + i + " -> " + productos[i]);
        }
    }

    // 1. Búsqueda lineal
    private static void buscarLineal(Scanner sc) {
        int codigo = leerEntero(sc, "Código a buscar: ");
        ResultadoBusqueda r = BuscadorProductos.busquedaLineal(productos, codigo);
        mostrarResultado("Búsqueda lineal", codigo, r);
    }

    // 2. Búsqueda parcial por nombre
    private static void buscarParcial(Scanner sc) {
        System.out.print("Texto a buscar en el nombre: ");
        String texto = sc.nextLine().trim();
        ResultadoParcial r = BuscadorProductos.buscarPorNombreParcial(productos, texto);
        if (r.cantidad() == 0) {
            System.out.println("No se encontraron productos cuyo nombre contenga \"" + texto + "\".");
        } else {
            System.out.println("Coincidencias para \"" + texto + "\": " + r.cantidad());
            for (int idx : r.getIndices()) {
                System.out.println("  posición " + idx + " -> " + productos[idx]);
            }
        }
        System.out.println("Comparaciones de caracteres: " + r.getComparaciones());
    }

    // 3. Ordenamiento por código
    private static void ordenar() {
        BuscadorProductos.ordenarPorCodigo(productos);
        ordenado = true;
        System.out.println("Inventario ordenado por código (inserción).");
        listar();
    }

    // 4. Búsqueda binaria
    private static void buscarBinaria(Scanner sc) {
        if (!ordenado) {
            System.out.println("La búsqueda binaria requiere el arreglo ordenado.");
            System.out.println("Use primero la opción 4 (Ordenar por código).");
            return;
        }
        int codigo = leerEntero(sc, "Código a buscar: ");
        ResultadoBusqueda r = BuscadorProductos.busquedaBinaria(productos, codigo);
        mostrarResultado("Búsqueda binaria", codigo, r);
    }

    // 5. Comparación lineal vs binaria
    private static void comparar(Scanner sc) {
        if (!ordenado) {
            System.out.println("Para comparar de forma justa, ordene primero (opción 4).");
            return;
        }
        int codigo = leerEntero(sc, "Código a buscar: ");
        ResultadoBusqueda lineal = BuscadorProductos.busquedaLineal(productos, codigo);
        ResultadoBusqueda binaria = BuscadorProductos.busquedaBinaria(productos, codigo);
        System.out.println("Código " + codigo + " sobre " + productos.length + " productos:");
        System.out.println("  Lineal : " + resumen(lineal));
        System.out.println("  Binaria: " + resumen(binaria));
    }

    //
    private static void mostrarResultado(String algoritmo, int codigo, ResultadoBusqueda r) {
        if (r.encontrado()) {
            System.out.println(algoritmo + ": código " + codigo + " encontrado en la posición "
                    + r.getIndice() + ".");
            System.out.println("  " + productos[r.getIndice()]);
        } else {
            System.out.println(algoritmo + ": el código " + codigo + " NO existe en el inventario.");
        }
        System.out.println("Comparaciones realizadas: " + r.getComparaciones());
    }

    private static String resumen(ResultadoBusqueda r) {
        String donde = r.encontrado() ? "posición " + r.getIndice() : "no existe";
        return donde + ", " + r.getComparaciones() + " comparaciones";
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String linea = sc.nextLine().trim();
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }

    //** Inventario inicial
    public static Producto[] crearInventarioInicial() {
        return new Producto[] {
                new Producto(1045, "Laptop Lenovo", 2850000, 8),
                new Producto(1012, "Mouse inalambrico", 45000, 40),
                new Producto(1088, "Teclado mecanico", 180000, 15),
                new Producto(1003, "Monitor 24 pulgadas", 620000, 12),
                new Producto(1067, "Laptop HP", 3100000, 5),
                new Producto(1021, "Cable HDMI", 18000, 100),
                new Producto(1099, "Disco duro 1TB", 260000, 20),
                new Producto(1030, "Memoria USB 64GB", 35000, 60),
                new Producto(1054, "Impresora laser", 750000, 7),
                new Producto(1076, "Camara web", 95000, 25)
        };
    }
}