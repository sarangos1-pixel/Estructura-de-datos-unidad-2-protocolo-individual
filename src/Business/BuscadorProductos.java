package Business;

import Domain.Producto;
import Domain.ResultadoBusqueda;
import Domain.ResultadoParcial;

public final class BuscadorProductos {

    private BuscadorProductos() {
    }


    // 1. Búsqueda lineal por código

    public static ResultadoBusqueda busquedaLineal(Producto[] productos, int codigo) {
        int comparaciones = 0;
        if (productos == null) {
            return new ResultadoBusqueda(-1, 0);
        }
        for (int i = 0; i < productos.length; i++) {
            comparaciones++;
            if (productos[i].getCodigo() == codigo) {
                return new ResultadoBusqueda(i, comparaciones);
            }
        }
        return new ResultadoBusqueda(-1, comparaciones);
    }

    // 2. Busqueda por nombre parcial

    public static ResultadoParcial buscarPorNombreParcial(Producto[] productos, String texto) {
        if (productos == null || texto == null || texto.length() == 0) {
            return new ResultadoParcial(new int[0], 0);
        }
        int[] temporales = new int[productos.length];
        int encontrados = 0;
        int comparaciones = 0;
        String buscado = aMinuscula(texto);

        for (int i = 0; i < productos.length; i++) {
            String nombre = aMinuscula(productos[i].getNombre());
            // Se desliza el texto buscado sobre el nombre
            for (int inicio = 0; inicio + buscado.length() <= nombre.length(); inicio++) {
                int j = 0;
                while (j < buscado.length()) {
                    comparaciones++;
                    if (nombre.charAt(inicio + j) != buscado.charAt(j)) {
                        break;
                    }
                    j++;
                }
                if (j == buscado.length()) {
                    temporales[encontrados++] = i;
                    break; // ya coincide, pasamos al siguiente producto
                }
            }
        }

        int[] indices = new int[encontrados];
        for (int k = 0; k < encontrados; k++) {
            indices[k] = temporales[k];
        }
        return new ResultadoParcial(indices, comparaciones);
    }

    // convierte a minúscula
    private static String aMinuscula(String s) {
        char[] c = new char[s.length()];
        for (int i = 0; i < c.length; i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (ch + ('a' - 'A'));
            }
            c[i] = ch;
        }
        return new String(c);
    }

    // 3. ordenamiento por codigo
    public static void ordenarPorCodigo(Producto[] productos) {
        if (productos == null) {
            return;
        }
        for (int i = 1; i < productos.length; i++) {
            Producto actual = productos[i];
            int j = i - 1;
            while (j >= 0 && productos[j].getCodigo() > actual.getCodigo()) {
                productos[j + 1] = productos[j];
                j--;
            }
            productos[j + 1] = actual;
        }
    }

    //Verifica que la lista esté ordenada por código.
    public static boolean estaOrdenadoPorCodigo(Producto[] productos) {
        if (productos == null) {
            return true;
        }
        for (int i = 1; i < productos.length; i++) {
            if (productos[i - 1].getCodigo() > productos[i].getCodigo()) {
                return false;
            }
        }
        return true;
    }
}
