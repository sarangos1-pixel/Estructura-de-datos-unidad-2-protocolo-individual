package Domain;

public class ResultadoParcial {
    private final int[] indices;
    private final int comparaciones;

    public ResultadoParcial(int[] indices, int comparaciones) {
        this.indices = indices;
        this.comparaciones = comparaciones;
    }

    public int[] getIndices() {
        return indices;
    }

    public int getComparaciones() {
        return comparaciones;
    }

    public int cantidad() {
        return indices.length;
    }
}
