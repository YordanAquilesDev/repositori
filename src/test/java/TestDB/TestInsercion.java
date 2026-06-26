package TestDB;

import Aplicacion.repositoryimpl.AnimalRepositoryImpl;
import Aplicacion.repositoryimpl.ClienteRepositoryImpl;
import Aplicacion.repositoryimpl.ProductoRepositoryImpl;
import Aplicacion.repositoryimpl.ProveedorRepositoryImpl;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Cliente;
import Dominio.Modelo.Producto;
import Dominio.Modelo.Proveedor;

public class TestInsercion {

    private static final String SEP = "------------------------";

    public static void main(String[] args) {
        testProducto();
        testCliente();
        testAnimal();
        testProveedor();
        listarTodo();
    }

    static void testProducto() {
        System.out.println(SEP);
        System.out.println("Insertando productos...");
        ProductoRepositoryImpl repo = new ProductoRepositoryImpl();

        Producto p1 = new Producto(0, 100.0, "kg", "Maíz", "Granos", 2.50);
        Producto p2 = new Producto(0, 50.0, "unidad", "Huevos", "Lácteos", 0.30);
        Producto p3 = new Producto(0, 200.0, "litro", "Leche", "Lácteos", 1.20);

        System.out.println("  Maíz    -> " + (repo.save(p1) > 0 ? "OK" : "FAIL"));
        System.out.println("  Huevos  -> " + (repo.save(p2) > 0 ? "OK" : "FAIL"));
        System.out.println("  Leche   -> " + (repo.save(p3) > 0 ? "OK" : "FAIL"));
    }

    static void testCliente() {
        System.out.println(SEP);
        System.out.println("Insertando clientes...");
        ClienteRepositoryImpl repo = new ClienteRepositoryImpl();

        Cliente c1 = new Cliente(0, "Juan", "Pérez", "12345678", "999111222", "Av. Siempre Viva 123");
        Cliente c2 = new Cliente(0, "María", "López", "87654321", "988333444", "Jr. Las Flores 456");

        System.out.println("  Juan   -> " + (repo.save(c1) > 0 ? "OK" : "FAIL"));
        System.out.println("  María  -> " + (repo.save(c2) > 0 ? "OK" : "FAIL"));
    }

    static void testAnimal() {
        System.out.println(SEP);
        System.out.println("Insertando animales...");
        AnimalRepositoryImpl repo = new AnimalRepositoryImpl();

        Animal a1 = new Animal(0, "Vacuno", "Angus");
        Animal a2 = new Animal(0, "Porcino", "Yorkshire");
        Animal a3 = new Animal(0, "Vacuno", "Holstein");

        System.out.println("  Angus      -> " + (repo.save(a1) > 0 ? "OK" : "FAIL"));
        System.out.println("  Yorkshire  -> " + (repo.save(a2) > 0 ? "OK" : "FAIL"));
        System.out.println("  Holstein   -> " + (repo.save(a3) > 0 ? "OK" : "FAIL"));
    }

    static void testProveedor() {
        System.out.println(SEP);
        System.out.println("Insertando proveedores...");
        ProveedorRepositoryImpl repo = new ProveedorRepositoryImpl();

        Proveedor prv1 = new Proveedor(0, "García", "Carlos", "11111111", "987654321");
        Proveedor prv2 = new Proveedor(0, "Martínez", "Ana", "22222222", "976543210");

        System.out.println("  Carlos -> " + (repo.save(prv1) > 0 ? "OK" : "FAIL"));
        System.out.println("  Ana    -> " + (repo.save(prv2) > 0 ? "OK" : "FAIL"));
    }

    static void listarTodo() {
        System.out.println(SEP);
        System.out.println("=== LISTADO COMPLETO ===");
        System.out.println(SEP);

        System.out.println("\n-- Productos --");
        new ProductoRepositoryImpl().findAll().forEach(p ->
            System.out.println("  [" + p.getIdProducto() + "] " + p.getNombre()
                    + " | $" + p.getPrecioUnidad() + " | stock: " + p.getStockActual() + " " + p.getUnidadMedida()));

        System.out.println("\n-- Clientes --");
        new ClienteRepositoryImpl().findAll().forEach(c ->
            System.out.println("  [" + c.getIdCliente() + "] " + c.getNombre() + " " + c.getApellido()
                    + " | DNI: " + c.getDni() + " | Cel: " + c.getCelular()));

        System.out.println("\n-- Animales --");
        new AnimalRepositoryImpl().findAll().forEach(a ->
            System.out.println("  [" + a.getIdAnimal() + "] " + a.getEspecie() + " - " + a.getRaza()));

        System.out.println("\n-- Proveedores --");
        new ProveedorRepositoryImpl().findAll().forEach(p ->
            System.out.println("  [" + p.getIdProveedor() + "] " + p.getNombre() + " " + p.getApellido()
                    + " | DNI: " + p.getDni() + " | Tel: " + p.getTelefono()));

        System.out.println(SEP);
        System.out.println("TEST COMPLETADO");
    }
}
