package Domain;

public class ResultadoBusqueda {
    private final int indice;
    private final int comparaciones;

    public ResultadoBusqueda(int indice, int comparaciones) {
        this.indice = indice;
        this.comparaciones = comparaciones;
    }

    public int getIndice() {
        return indice;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public boolean encontrado() {
        return indice >= 0;
    }
}
