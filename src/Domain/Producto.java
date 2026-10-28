package Domain;

public class Producto {
    private final int codigo;
    private final String nombre;
    private final double precio;
    private final int cantidad;

    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return String.format("[codigo=%d | nombre=%s | precio=$%,.2f | cantidad=%d]",
                codigo, nombre, precio, cantidad);
    }
}
