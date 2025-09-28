package dao;

import interfaces.IProductoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.example.Producto;

import java.util.List;

public class ProductoDAO implements IProductoDAO {

    private EntityManager entityManager;

    public ProductoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insertar(Producto producto) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(producto);
            tx.commit();
            System.out.println("Producto insertado: " + producto);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void actualizar(Producto producto) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.merge(producto);
            tx.commit();
            System.out.println("Producto actualizado: " + producto);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public void eliminar(int id) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            Producto producto = entityManager.find(Producto.class, id);
            if (producto != null) {
                entityManager.remove(producto);
                System.out.println("Producto eliminado: " + producto);
            } else {
                System.out.println("Producto con ID " + id + " no encontrado.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                e.printStackTrace();
            }
        }
    }

    @Override
    public Producto obtenerPorId(int id) {
        return entityManager.find(Producto.class, id);
    }

    @Override
    public List<Producto> obtenerTodos() {
        TypedQuery<Producto> query = entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }
}
