package org.example;
import dao.ProductoDAO;
import jakarta.persistence.*;

import java.util.List;


public class JPATienda {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TiendaPU");
        EntityManager em = emf.createEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        System.out.println("--- Iniciando operaciones con JPA ---");
        System.out.println("Insertar productos");
        // Insertar productos
        Producto p1 = new Producto("Laptop", 15000.0);
        Producto p2 = new Producto("Celular", 8000.0);
        Producto p3 = new Producto("Tablet", 5000.0);
        productoDAO.insertar(p1);
        productoDAO.insertar(p2);
        productoDAO.insertar(p3);
        System.out.println("\n");
        System.out.println("Listar productos");
        // listar todos los productos
        listarProductos(productoDAO);
        System.out.println("\n");
        System.out.println("Actualizar producto");
        // Actualizar un producto
        p2.setPrecio(7500.0);
        productoDAO.actualizar(p2);
        System.out.println("\n");
        System.out.println("Listar productos");
        // listar todos los productos
        listarProductos(productoDAO);
        System.out.println("\n");
        System.out.println("Eliminar producto");
        // Eliminar un producto
        productoDAO.eliminar(p1.getId());
        System.out.println("\n");
        System.out.println("Listar productos");
        // listar todos los productos
        listarProductos(productoDAO);

        em.close();
        emf.close();
    }
    private static void listarProductos(ProductoDAO productoDAO) {
        List<Producto> productos = productoDAO.obtenerTodos();
        if(productos.isEmpty()) {
            System.out.println("No hay productos disponibles.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto p : productos) {
                System.out.println(p);
            }
        }
    }
}