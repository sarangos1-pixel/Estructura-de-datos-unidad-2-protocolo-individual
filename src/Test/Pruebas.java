package Test;

import Business.BuscadorProductos;
import Domain.Producto;
import Domain.ResultadoBusqueda;
import Domain.ResultadoParcial;
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


        // casos de prueba de búsqueda binaria y parcial

        System.out.println("--- Búsqueda binaria (arreglo ordenado) ---");
        // Orden: 1003,1012,1021,1030,1045,1054,1067,1076,1088,1099
        verificar("existe en el centro (1045 -> pos 4, 1 comp.)",
                BuscadorProductos.busquedaBinaria(ord, 1045), 4, 1);
        verificar("existe al inicio (1003 -> pos 0, 3 comp.)",
                BuscadorProductos.busquedaBinaria(ord, 1003), 0, 3);
        verificar("existe al final (1099 -> pos 9, 4 comp.)",
                BuscadorProductos.busquedaBinaria(ord, 1099), 9, 4);
        verificar("no existe, entre dos códigos (1050 -> -1, 3 comp.)",
                BuscadorProductos.busquedaBinaria(ord, 1050), -1, 3);
        verificar("no existe, menor que todos (1)",
                BuscadorProductos.busquedaBinaria(ord, 1), -1, 3);
        verificar("no existe, mayor que todos (9999)",
                BuscadorProductos.busquedaBinaria(ord, 9999), -1, 4);
        verificar("arreglo vacío",
                BuscadorProductos.busquedaBinaria(new Producto[0], 1), -1, 0);
        verificar("un solo elemento, existe",
                BuscadorProductos.busquedaBinaria(new Producto[] {new Producto(5, "X", 1, 1)}, 5), 0, 1);

        boolean lanzoExcepcion = false;
        try {
            BuscadorProductos.busquedaBinaria(main.crearInventarioInicial(), 1045);
        } catch (IllegalStateException e) {
            lanzoExcepcion = true;
        }
        comprobar("binaria sobre arreglo SIN ordenar lanza IllegalStateException", lanzoExcepcion);

        System.out.println("--- Comparación de resultados (Lineal vs. Binaria) ---");
        boolean coherente = true;
        for (int i = 0; i < ord.length; i++) {
            int cod = ord[i].getCodigo();
            if (BuscadorProductos.busquedaLineal(ord, cod).getIndice() != i
                    || BuscadorProductos.busquedaBinaria(ord, cod).getIndice() != i) {
                coherente = false;
            }
        }
        comprobar("ambos algoritmos encuentran cada código en su posición", coherente);

        System.out.println("--- Búsqueda parcial por nombre ---");
        ResultadoParcial lap = BuscadorProductos.buscarPorNombreParcial(base, "lap");
        comprobar("\"lap\" -> 2 coincidencias (Laptop Lenovo, Laptop HP)", lap.cantidad() == 2);
        comprobar("\"lap\" -> posiciones 0 y 4",
                lap.getIndices()[0] == 0 && lap.getIndices()[1] == 4);
        comprobar("\"LAP\" (mayúsculas) da el mismo resultado",
                BuscadorProductos.buscarPorNombreParcial(base, "LAP").cantidad() == 2);
        comprobar("\"ouse\" -> 1 coincidencia (texto en medio del nombre: Mouse)",
                BuscadorProductos.buscarPorNombreParcial(base, "ouse").cantidad() == 1);
        comprobar("\"hdmi\" -> 1 coincidencia",
                BuscadorProductos.buscarPorNombreParcial(base, "hdmi").cantidad() == 1);
        comprobar("\"zzz\" -> 0 coincidencias",
                BuscadorProductos.buscarPorNombreParcial(base, "zzz").cantidad() == 0);
        comprobar("texto vacío -> 0 coincidencias",
                BuscadorProductos.buscarPorNombreParcial(base, "").cantidad() == 0);
        comprobar("texto más largo que cualquier nombre -> 0",
                BuscadorProductos.buscarPorNombreParcial(base,
                        "esto es mucho mas largo que cualquier nombre del inventario").cantidad() == 0);

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