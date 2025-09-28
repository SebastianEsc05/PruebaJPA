package interfaces;

import org.example.Producto;

public interface IProductoDAO {
    void insertar(Producto producto);
    void actualizar(Producto producto);
    void eliminar(int id);
    Producto obtenerPorId(int id);
    java.util.List<Producto> obtenerTodos();
}
