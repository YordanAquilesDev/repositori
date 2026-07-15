/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Presentacion.GuiUsuario;

import Aplicacion.Service.AnimalService;
import Dominio.Modelo.Animal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author yordan
 */
public class Tienda extends JPanel {
 private AnimalService animalService ;
    /**
     * Creates new form Tienda
     */
 private JPanel contenedorTarjetas;
  private JScrollPane scrollPane;
    public Tienda() {
        initComponentsManual(); 
        animalService= new AnimalService();
        cargarProductos();
    }

   private void initComponentsManual() {
        // 1. Definimos el diseño de este panel principal como BorderLayout
        this.setLayout(new BorderLayout());

        // 2. Este panel contendrá físicamente las tarjetas. 
        // Usamos un GridLayout dinámico: 0 filas (significa infinitas) y 4 columnas.
        // Se adaptará automáticamente según agregues tarjetas.
        contenedorTarjetas = new JPanel();
        contenedorTarjetas.setLayout(new GridLayout(0, 4, 15, 15)); // (filas, columnas, espacioH, espacioV)
        contenedorTarjetas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 3. Metemos el contenedor de tarjetas dentro de un JScrollPane para habilitar el scroll
        scrollPane = new JScrollPane(contenedorTarjetas);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Solo scroll vertical
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Hace que el scroll sea más suave al bajar con la rueda

        // 4. Agregamos el JScrollPane al panel principal de la Tienda
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void cargarProductos() {
        // Limpiamos por si se vuelve a recargar la tienda
        contenedorTarjetas.removeAll();

        try {
            // Llamamos a tu servicio para obtener los datos de la base de datos
            // Reemplaza "Animal" por tu clase modelo real
            List<Animal> listaAnimales = animalService.findAll(); 

            if (listaAnimales.isEmpty()) {
                JLabel lblVacio = new JLabel("No hay productos disponibles por el momento.", SwingConstants.CENTER);
                lblVacio.setFont(new Font("Segoe UI", Font.BOLD, 16));
                contenedorTarjetas.setLayout(new BorderLayout());
                contenedorTarjetas.add(lblVacio, BorderLayout.CENTER);
            } else {
                // Si cambiamos a sin productos, restauramos el grid layout
                contenedorTarjetas.setLayout(new GridLayout(0, 4, 15, 15));
                
                // Creamos una tarjeta para cada animal de la lista y la añadimos
                for (Animal animal : listaAnimales) {
                    CardProducto tarjetaProducto=  new CardProducto(animal);
                    TarjetaAnimal tarjeta = new TarjetaAnimal(animal);
                    contenedorTarjetas.add(tarjetaProducto);
                    System.out.println(contenedorTarjetas);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los animales: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Obligamos a Swing a rediseñar y pintar el scroll con el nuevo contenido
        contenedorTarjetas.revalidate();
        contenedorTarjetas.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

 class TarjetaAnimal extends JPanel {
    
    // Suponiendo que tienes un modelo llamado "Animal" o "Catalogo"
    // Reemplaza 'Animal' por el nombre real de tu clase modelo
    public TarjetaAnimal(Animal animal) {
        // Configuramos un diseño básico para la tarjeta
        this.setLayout(new BorderLayout(10, 10));
        this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(200, 250)); // Tamaño fijo para cada tarjeta

        // Panel interno para la información (Texto)
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
        panelInfo.setOpaque(false);
        panelInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Componentes con los datos del animal
        JLabel lblNombre = new JLabel(animal.getNombre().toUpperCase());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblRaza = new JLabel("Raza: " + animal.getIdRaza() + "N/A");
        lblRaza.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblRaza.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblPrecio = new JLabel("$" + String.format("%.2f", animal.getPrecio()));
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPrecio.setForeground(new Color(46, 125, 50)); // Color verde elegante
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblStock = new JLabel("Disponibles: " + animal.getStock());
        lblStock.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblStock.setForeground(Color.GRAY);
        lblStock.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botón de acción (ej. ver detalles o agregar al carrito)
        JButton btnAccion = new JButton("agregar carrito");
        
        
        btnAccion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAccion.setFocusable(false);

        // Agregamos todo al panel de información
        panelInfo.add(lblNombre);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblRaza);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 10)));
        panelInfo.add(lblPrecio);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 5)));
        panelInfo.add(lblStock);
        panelInfo.add(Box.createRigidArea(new Dimension(0, 15)));
        panelInfo.add(btnAccion);

        // Añadimos el panel de información al centro de la tarjeta
        this.add(panelInfo, BorderLayout.CENTER);
    }
    
    private void agreagrCarrirto(){
        
    }
}
