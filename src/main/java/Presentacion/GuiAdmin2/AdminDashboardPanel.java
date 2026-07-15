package Presentacion.GuiAdmin2;

import Aplicacion.Service.AnimalService;
import Aplicacion.Service.ClienteService;
import Aplicacion.Service.VentaService;
import Dominio.Modelo.Animal;
import Dominio.Modelo.Venta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AdminDashboardPanel extends JPanel {

    public AdminDashboardPanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(0, 20));
        setBackground(new Color(244, 247, 246));

        JLabel descripcion = new JLabel("Resumen general del sistema");
        descripcion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descripcion.setForeground(new Color(91, 105, 101));
        add(descripcion, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(2, 3, 18, 18));
        grid.setOpaque(false);

        int totalAnimales = 0;
        int stockTotal = 0;
        int stockBajo = 0;
        int totalVentas = 0;
        double montoVentas = 0;
        int totalClientes = 0;

        try {
            List<Animal> animales = new AnimalService().findAll();
            totalAnimales = animales.size();
            stockTotal = animales.stream().mapToInt(Animal::getStock).sum();
            stockBajo = (int) animales.stream().filter(a -> a.getStock() <= 5).count();
        } catch (RuntimeException e) {
            // Si la BD aun no esta alineada, el dashboard sigue abriendo.
        }

        try {
            List<Venta> ventas = new VentaService().findAll();
            totalVentas = ventas.size();
            montoVentas = ventas.stream().mapToDouble(Venta::getTotal).sum();
        } catch (RuntimeException e) {
        }

        try {
            totalClientes = new ClienteService().findAll().size();
        } catch (RuntimeException e) {
        }

        grid.add(crearTarjeta("Animales", String.valueOf(totalAnimales), "Cantidad total de animales registrados"));
        grid.add(crearTarjeta("Stock total", String.valueOf(stockTotal), "Suma de todas las unidades disponibles"));
        grid.add(crearTarjeta("Stock bajo", String.valueOf(stockBajo), "Animales con 5 o menos unidades"));
        grid.add(crearTarjeta("Ventas", String.valueOf(totalVentas), "Numero total de ventas realizadas"));
        grid.add(crearTarjeta("Monto vendido", "S/ " + String.format("%.2f", montoVentas), "Total acumulado de todas las ventas"));
        grid.add(crearTarjeta("Clientes", String.valueOf(totalClientes), "Total de clientes registrados"));

        add(grid, BorderLayout.CENTER);
    }

    private JPanel crearTarjeta(String titulo, String valor, String descripcion) {
        JPanel card = new JPanel(new BorderLayout(0, 6));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(225, 231, 229)),
                new EmptyBorder(24, 24, 24, 24)
        ));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setForeground(new Color(91, 105, 101));

        JLabel lblValor = new JLabel(valor, SwingConstants.LEFT);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblValor.setForeground(new Color(35, 132, 94));

        JLabel lblDescripcion = new JLabel(descripcion, SwingConstants.LEFT);
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDescripcion.setForeground(new Color(150, 160, 156));

        JPanel panelInferior = new JPanel(new BorderLayout(0, 4));
        panelInferior.setOpaque(false);
        panelInferior.add(lblValor, BorderLayout.NORTH);
        panelInferior.add(lblDescripcion, BorderLayout.SOUTH);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(panelInferior, BorderLayout.CENTER);
        return card;
    }
}
