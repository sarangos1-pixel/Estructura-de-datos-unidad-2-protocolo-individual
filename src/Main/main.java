package Main;

import Business.BuscadorProductos;
import Domain.Producto;
import Domain.ResultadoBusqueda;
import Domain.ResultadoParcial;

public class main {

    public static void main(String[] args) {
        Producto[] productos = crearInventarioInicial();

        System.out.println("Inventario inicial (" + productos.length + " productos):");
        for (int i = 0; i < productos.length; i++) {
            System.out.println("  posición " + i + " -> " + productos[i]);
        }
        System.out.println();

        //busqueda lineal
        probarLineal(productos, 1021);   // existe
        probarLineal(productos, 7777);   // no existe

        System.out.println();
        //busqueda por nombre parcial
        probarParcial(productos, "lap");  // 2 coincidencias
        probarParcial(productos, "HDMI"); // mayúsculas, 1 coincidencia
        probarParcial(productos, "ZZZzzzz");  // ninguna
        System.out.println();

        //ordenamiento por codigo
        System.out.println("¿Está ordenado por código? "
                + BuscadorProductos.estaOrdenadoPorCodigo(productos));
        BuscadorProductos.ordenarPorCodigo(productos);
        System.out.println("Después de ordenar:");
        listar(productos);
        System.out.println("¿Está ordenado por código? "
                + BuscadorProductos.estaOrdenadoPorCodigo(productos));

    }
// 1. Busqueda lineal
    private static void probarLineal(Producto[] productos, int codigo) {
        ResultadoBusqueda r = BuscadorProductos.busquedaLineal(productos, codigo);
        if (r.encontrado()) {
            System.out.println("Código " + codigo + " encontrado en la posición " + r.getIndice()
                    + " (" + r.getComparaciones() + " comparaciones)");
        } else {
            System.out.println("El código " + codigo + " NO existe ("
                    + r.getComparaciones() + " comparaciones)");
        }
    }

    // 2. busqueda por nombre parcial
    private static void probarParcial(Producto[] productos, String texto) {
        ResultadoParcial r = BuscadorProductos.buscarPorNombreParcial(productos, texto);
        if (r.cantidad() == 0) {
            System.out.println("Sin coincidencias para \"" + texto + "\"");
        } else {
            System.out.println("Coincidencias para \"" + texto + "\": " + r.cantidad());
            for (int idx : r.getIndices()) {
                System.out.println("  posición " + idx + " -> " + productos[idx]);
            }
        }
        System.out.println("  Comparaciones de caracteres: " + r.getComparaciones());
    }

    //3, ordenamiento por codigo
    private static void listar(Producto[] productos) {
        for (int i = 0; i < productos.length; i++) {
            System.out.println("  posición " + i + " -> " + productos[i]);
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