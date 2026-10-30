package Test;

import Business.BuscadorProductos;
import Domain.Producto;
import Domain.ResultadoBusqueda;
import Main.main;


public class Pruebas {

    private static int total = 0;
    private static int fallidas = 0;

    public static void main(String[] args) {
        Producto[] base = main.crearInventarioInicial();

        System.out.println("--- Búsqueda lineal ---");
        verificar("existe al inicio (1045 -> pos 0, 1 comp.)",
                BuscadorProductos.busquedaLineal(base, 1045), 0, 1);
        verificar("existe en medio (1021 -> pos 5, 6 comp.)",
                BuscadorProductos.busquedaLineal(base, 1021), 5, 6);
        verificar("existe al final (1076 -> pos 9, 10 comp.)",
                BuscadorProductos.busquedaLineal(base, 1076), 9, 10);
        verificar("no existe (9999 -> -1, 10 comp.)",
                BuscadorProductos.busquedaLineal(base, 9999), -1, 10);
        verificar("arreglo vacío (-1, 0 comp.)",
                BuscadorProductos.busquedaLineal(new Producto[0], 1), -1, 0);
        verificar("arreglo null (-1, 0 comp.)",
                BuscadorProductos.busquedaLineal(null, 1), -1, 0);

        System.out.println("--- Ordenamiento ---");
        Producto[] ord = main.crearInventarioInicial();
        comprobar("el original NO está ordenado", !BuscadorProductos.estaOrdenadoPorCodigo(ord));
        BuscadorProductos.ordenarPorCodigo(ord);
        comprobar("después de ordenar SÍ está ordenado", BuscadorProductos.estaOrdenadoPorCodigo(ord));
        comprobar("primer código es 1003", ord[0].getCodigo() == 1003);
        comprobar("último código es 1099", ord[ord.length - 1].getCodigo() == 1099);
        Producto[] vacio = new Producto[0];
        BuscadorProductos.ordenarPorCodigo(vacio);
        comprobar("ordenar arreglo vacío no falla", vacio.length == 0);

        System.out.println();
        System.out.println("Total: " + total + " pruebas | Correctas: " + (total - fallidas)
                + " | Fallidas: " + fallidas);
        if (fallidas > 0) {
            System.exit(1);
        }
    }

    private static void verificar(String descripcion, ResultadoBusqueda r, int indiceEsperado, int comparacionesEsperadas) {
        boolean ok = r.getIndice() == indiceEsperado && r.getComparaciones() == comparacionesEsperadas;
        registrar(descripcion + " -> obtenido: pos " + r.getIndice() + ", " + r.getComparaciones() + " comp.", ok);
    }

    private static void comprobar(String descripcion, boolean condicion) {
        registrar(descripcion, condicion);
    }

    private static void registrar(String descripcion, boolean ok) {
        total++;
        if (!ok) {
            fallidas++;
        }
        System.out.println((ok ? "  [OK]    " : "  [FALLO] ") + descripcion);
    }
}