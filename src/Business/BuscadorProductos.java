package Business;

import Domain.Producto;
import Domain.ResultadoBusqueda;

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

}
