package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * R05 — Dashboard Integrado: todos los layouts y bordes (solución completa).
 * RN-R05.1 borde distinto por región. RN-R05.2 3 pestañas con contenido real.
 * RN-R05.3 pestaña Resumen con GridLayout(2,3)+EtchedBorder. RN-R05.4 resizeWeight=0.70.
 * RN-R05.5 setMinimumSize(900,550). Todos los layouts y bordes del laboratorio presentes.
 */
public class DashboardIntegrado extends JFrame {

    public DashboardIntegrado() {
        super("R05 — Dashboard Integrado — Todos los Layouts");
        construirUI();
        setSize(1100, 680);
        setMinimumSize(new Dimension(900, 550));   // RN-R05.5
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        setContentPane(root);

        root.add(crearNorth(),  BorderLayout.NORTH);
        root.add(crearWest(),   BorderLayout.WEST);
        root.add(crearCentro(), BorderLayout.CENTER);
        root.add(crearSouth(),  BorderLayout.SOUTH);
    }

    // ── NORTH: FlowLayout + MatteBorder inferior (RN-R05.1) ───────
    private JPanel crearNorth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 8));
        // MatteBorder en NORTH
        p.setBorder(BorderFactory.createMatteBorder(
            0, 0, 2, 0, new Color(25, 118, 210)));

        JLabel titulo = new JLabel("📊  Dashboard — APE 115 G0301");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        titulo.setForeground(new Color(25, 118, 210));

        p.add(titulo);
        p.add(Box.createHorizontalStrut(20));
        p.add(tarjetaStat("Total Productos", "8",   new Color(25, 118, 210)));
        p.add(Box.createHorizontalStrut(8));
        p.add(tarjetaStat("Total Clientes",  "142", new Color(46, 125,  50)));
        return p;
    }

    /** Tarjeta de estadística con LineBorder. */
    private JPanel tarjetaStat(String lbl, String val, Color color) {
        JPanel card = new JPanel(new BorderLayout(4, 0));
        // LineBorder en las tarjetas NORTH
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color, 2),
            BorderFactory.createEmptyBorder(6, 14, 6, 14)));
        JLabel lblVal = new JLabel(val, SwingConstants.CENTER);
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblVal.setForeground(color);
        JLabel lblN = new JLabel(lbl, SwingConstants.CENTER);
        lblN.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblN.setForeground(new Color(80, 80, 100));
        card.add(lblVal, BorderLayout.CENTER);
        card.add(lblN,   BorderLayout.SOUTH);
        return card;
    }

    // ── WEST: BoxLayout + CompoundBorder 'Módulos' (RN-R05.1) ─────
    private JPanel crearWest() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));   // BoxLayout requerido por T06.4
        p.setBackground(new Color(245, 247, 252));
        p.setPreferredSize(new Dimension(170, 0));
        // CompoundBorder en WEST
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Módulos", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), new Color(25, 118, 210)),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)));

        for (String item : new String[]{"📦 Productos","👤 Clientes","📈 Reportes","⚙ Config"}) {
            JButton btn = new JButton(item);
            btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            btn.setBackground(new Color(220, 232, 252));
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
            p.add(btn);
            p.add(Box.createVerticalStrut(5));
        }
        p.add(Box.createVerticalGlue());
        return p;
    }

    // ── CENTER: JSplitPane(resizeWeight=0.70) ─────────────────────
    private JSplitPane crearCentro() {
        JSplitPane sp = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            crearTabbedPane(),         // izquierda
            crearFormularioRapido()    // derecha
        );
        sp.setDividerLocation(520);
        sp.setResizeWeight(0.70);      // RN-R05.4
        sp.setOneTouchExpandable(true);
        return sp;
    }

    // JTabbedPane con 3 pestañas (RN-R05.2 y test T06.3)
    private JTabbedPane crearTabbedPane() {
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.addTab("Resumen",   null, crearPestanaResumen(),   "Estadísticas rápidas");
        tabs.addTab("Productos", null, crearPestanaProductos(), "Tabla de productos");
        tabs.addTab("Actividad", null, crearPestanaActividad(), "Registro de actividad");
        return tabs;
    }

    // Pestaña 1: GridLayout(2,3) con tarjetas EtchedBorder (RN-R05.3)
    private JPanel crearPestanaResumen() {
        JPanel p = new JPanel(new GridLayout(2, 3, 8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] stats = {
            {"Ventas Hoy",   "$1,240"}, {"Stock Bajo",    "3"},
            {"Clientes",     "142"},    {"Pedidos",       "7"},
            {"Devoluciones", "2"},      {"Ganancia Mes",  "$18,420"}
        };
        for (String[] s : stats) {
            JPanel card = new JPanel(new BorderLayout(2, 2));
            // EtchedBorder en cada tarjeta del resumen (RN-R05.3)
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
            card.setBackground(new Color(250, 252, 255));
            JLabel val = new JLabel(s[1], SwingConstants.CENTER);
            val.setFont(new Font("Segoe UI", Font.BOLD, 20));
            val.setForeground(new Color(25, 118, 210));
            JLabel lbl = new JLabel(s[0], SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            card.add(val, BorderLayout.CENTER);
            card.add(lbl, BorderLayout.SOUTH);
            p.add(card);
        }
        return p;
    }

    // Pestaña 2: JTable en JScrollPane (RN-R05.2 — contenido real)
    private JPanel crearPestanaProductos() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        String[] cols = {"Nombre", "Categoría", "Precio", "Stock"};
        Object[][] rows = {
            {"Laptop Dell",   "Electrónica", 899.99, 12},
            {"Monitor LG",    "Electrónica", 249.50, 30},
            {"Teclado Mec.",  "Accesorios",   89.00, 50},
            {"Silla Ergon.",  "Hogar",        320.00,  8},
        };
        JTable tbl = new JTable(rows, cols);
        tbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tbl.setRowHeight(26);
        p.add(new JScrollPane(tbl), BorderLayout.CENTER);
        return p;
    }

    // Pestaña 3: JTextArea de actividad (RN-R05.2 — contenido real)
    private JPanel crearPestanaActividad() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        JTextArea tx = new JTextArea();
        tx.setFont(new Font("Courier New", Font.PLAIN, 12));
        tx.setEditable(false);
        tx.setText(
            "[10:30] admin inició sesión\n" +
            "[10:32] Producto 'Laptop Dell' actualizado\n" +
            "[10:45] Nuevo cliente: Juan García\n" +
            "[11:02] Pedido #1042 procesado\n" +
            "[11:15] Exportación de reporte generada");
        p.add(new JScrollPane(tx), BorderLayout.CENTER);
        return p;
    }

    // Panel derecho del JSplitPane: formulario rápido GridBagLayout
    private JPanel crearFormularioRapido() {
        JPanel p = new JPanel(new GridBagLayout());
        // CompoundBorder en el formulario rápido (RN-R05.5)
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Agregar Producto Rápido",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), new Color(25, 118, 210)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.anchor = GridBagConstraints.WEST;

        String[] lbls = {"Nombre:", "Precio $:", "Stock:"};
        for (int i = 0; i < lbls.length; i++) {
            g.gridx = 0; g.gridy = i;
            g.fill = GridBagConstraints.NONE; g.weightx = 0;
            JLabel l = new JLabel(lbls[i]);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            p.add(l, g);
            g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
            JTextField tf = new JTextField(14);
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            p.add(tf, g);
        }
        // Botón Agregar
        g.gridx = 0; g.gridy = 3; g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE; g.weightx = 0;
        g.anchor = GridBagConstraints.EAST;
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(25, 118, 210));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false); btnAgregar.setBorderPainted(false);
        btnAgregar.setOpaque(true);
        btnAgregar.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        p.add(btnAgregar, g);

        // Relleno vertical
        g.gridx = 0; g.gridy = 4; g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH; g.weighty = 1;
        p.add(new JPanel(), g);
        return p;
    }

    // ── SOUTH: FlowLayout + MatteBorder superior (RN-R05.1) ───────
    private JPanel crearSouth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 6));
        // MatteBorder en SOUTH
        p.setBorder(BorderFactory.createMatteBorder(
            1, 0, 0, 0, new Color(200, 210, 230)));

        JLabel lblEstado = new JLabel("Estado: Sistema activo");
        lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblEstado.setForeground(new Color(80, 80, 100));

        JButton btnExportar   = boton("Exportar",   new Color(100, 100, 100));
        JButton btnActualizar = boton("Actualizar", new Color( 25, 118, 210));

        p.add(lblEstado);
        p.add(Box.createHorizontalStrut(20));
        p.add(btnExportar);
        p.add(btnActualizar);
        return p;
    }

    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(color); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DashboardIntegrado().setVisible(true));
    }
}
