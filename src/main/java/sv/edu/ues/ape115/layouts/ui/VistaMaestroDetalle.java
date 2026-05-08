package sv.edu.ues.ape115.layouts.ui;

import sv.edu.ues.ape115.layouts.model.Producto;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * R04 — Vista Maestro-Detalle con JSplitPane (solución completa).
 * RN-R04.1 selección JList carga detalle. RN-R04.2 búsqueda por Enter.
 * RN-R04.3 JList y JTextArea en JScrollPane. RN-R04.4 resizeWeight=0.30.
 */
public class VistaMaestroDetalle extends JFrame {

    private final List<Producto>             productos = new ArrayList<>();
    private final DefaultListModel<Producto> modelo    = new DefaultListModel<>();
    private JList<Producto> lstProductos;

    // Campos del detalle
    private final JTextField       txtNombre  = new JTextField();
    private final JTextField       txtPrecio  = new JTextField();
    private final JTextField       txtStock   = new JTextField();
    private final JComboBox<String> cboCat    = new JComboBox<>(
        new String[]{"Electrónica", "Hogar", "Accesorios"});
    private final JTextArea txDesc = new JTextArea(4, 16);

    public VistaMaestroDetalle() {
        super("R04 — Vista Maestro-Detalle: JSplitPane");
        cargarDatos();
        construirUI();
        setSize(860, 560);
        setMinimumSize(new Dimension(700, 480));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        Object[][] d = {
            {"Laptop Dell",    "Electrónica", 899.99, 12, "Laptop i7 16GB RAM"},
            {"Monitor LG",     "Electrónica", 249.50, 30, "Monitor 24\" IPS 75Hz"},
            {"Teclado Mec.",   "Accesorios",   89.00, 50, "Switches Blue RGB"},
            {"Silla Ergon.",   "Hogar",        320.00,  8, "Soporte lumbar"},
            {"Audífonos Sony", "Electrónica", 299.00, 20, "Noise Cancelling"},
            {"Mochila",        "Accesorios",   75.00, 40, "Para laptop 15.6\""},
            {"Lámpara LED",    "Hogar",         45.00, 60, "Luz ajustable"},
            {"Mouse Logitech", "Accesorios",  109.00, 35, "Inalámbrico"},
        };
        for (Object[] row : d) {
            Producto p = new Producto((String)row[0], (String)row[1],
                (Double)row[2], (Integer)row[3], (String)row[4]);
            productos.add(p); modelo.addElement(p);
        }
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        setContentPane(root);

        // JSplitPane con parámetros exactos de R04
        JSplitPane split = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            crearMaestro(),
            crearDetalle()
        );
        split.setDividerLocation(260);      // RN-R04
        split.setResizeWeight(0.30);        // RN-R04.4
        split.setOneTouchExpandable(true);  // test T05.3
        split.setContinuousLayout(true);
        root.add(split, BorderLayout.CENTER);
    }

    // ── Panel Maestro (izquierda) ─────────────────────────────────
    private JPanel crearMaestro() {
        JPanel p = new JPanel(new BorderLayout(0, 6));
        // TitledBorder requerido por test T05.7
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Productos", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), new Color(25, 118, 210)),
            BorderFactory.createEmptyBorder(6, 8, 6, 8)));

        // NORTH: campo de búsqueda con LineBorder azul
        JTextField txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtBuscar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(25, 118, 210), 1),
            BorderFactory.createEmptyBorder(4, 6, 4, 6)));
        txtBuscar.setToolTipText("Escribe y presiona Enter para buscar");
        txtBuscar.addActionListener(e -> filtrar(txtBuscar.getText())); // RN-R04.2
        p.add(txtBuscar, BorderLayout.NORTH);

        // CENTER: JList en JScrollPane (RN-R04.3)
        lstProductos = new JList<>(modelo);
        lstProductos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lstProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // RN-R04.1: al seleccionar carga el detalle
        lstProductos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting())
                cargarDetalle(lstProductos.getSelectedValue());
        });
        p.add(new JScrollPane(lstProductos), BorderLayout.CENTER);
        return p;
    }

    // ── Panel Detalle (derecha) ───────────────────────────────────
    private JPanel crearDetalle() {
        JPanel outer = new JPanel(new BorderLayout(0, 8));
        // TitledBorder requerido por test T05.7
        outer.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                "Detalle del Producto", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), new Color(25, 118, 210)),
            BorderFactory.createEmptyBorder(6, 10, 6, 10)));

        JPanel form = new JPanel(new GridBagLayout());
        form.setOpaque(false);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 6, 5, 6);
        g.anchor = GridBagConstraints.WEST;

        // Nombre
        addLbl(form, g, "Nombre:", 0, 0);
        g.gridx = 1; g.gridy = 0; g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        txtNombre.setFont(f()); form.add(txtNombre, g); r(g);

        // Precio
        addLbl(form, g, "Precio $:", 0, 1);
        g.gridx = 1; g.gridy = 1;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        txtPrecio.setFont(f()); form.add(txtPrecio, g); r(g);

        // Stock
        addLbl(form, g, "Stock:", 0, 2);
        g.gridx = 1; g.gridy = 2;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        txtStock.setFont(f()); form.add(txtStock, g); r(g);

        // Categoría
        addLbl(form, g, "Categoría:", 0, 3);
        g.gridx = 1; g.gridy = 3; g.gridwidth = 2;
        g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
        cboCat.setFont(f()); form.add(cboCat, g); r(g);

        // Descripción JTextArea (span 1-2 × filas 4-5) RN-R04.3
        addLbl(form, g, "Descripción:", 0, 4);
        g.gridx = 1; g.gridy = 4; g.gridwidth = 2; g.gridheight = 2;
        g.fill = GridBagConstraints.BOTH; g.weightx = 1; g.weighty = 1;
        txDesc.setFont(f()); txDesc.setLineWrap(true); txDesc.setWrapStyleWord(true);
        form.add(new JScrollPane(txDesc), g);

        outer.add(form, BorderLayout.CENTER);

        // Botones SOUTH
        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 4));
        pBtns.setOpaque(false);
        JButton btnCancelar = boton("Cancelar", new Color(100, 100, 100));
        JButton btnGuardar  = boton("Guardar",  new Color(46, 125, 50));
        btnCancelar.addActionListener(e -> limpiar());
        pBtns.add(btnCancelar); pBtns.add(btnGuardar);
        outer.add(pBtns, BorderLayout.SOUTH);
        return outer;
    }

    // ── Lógica ────────────────────────────────────────────────────
    private void cargarDetalle(Producto p) {
        if (p == null) return;
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        cboCat.setSelectedItem(p.getCategoria());
        txDesc.setText(p.getDescripcion());
    }

    private void filtrar(String q) {
        modelo.clear();
        String t = q.trim().toLowerCase();
        productos.stream()
            .filter(p -> t.isEmpty() || p.getNombre().toLowerCase().contains(t))
            .forEach(modelo::addElement);
    }

    private void limpiar() {
        txtNombre.setText(""); txtPrecio.setText(""); txtStock.setText("");
        txDesc.setText(""); lstProductos.clearSelection();
    }

    // ── Mini-helpers ──────────────────────────────────────────────
    private Font f() { return new Font("Segoe UI", Font.PLAIN, 13); }
    private void r(GridBagConstraints g) {
        g.gridwidth = 1; g.gridheight = 1; g.weightx = 0; g.weighty = 0;
    }
    private void addLbl(JPanel p, GridBagConstraints g, String t, int x, int y) {
        g.gridx = x; g.gridy = y;
        g.fill = GridBagConstraints.NONE; g.weightx = 0; g.gridwidth = 1;
        JLabel l = new JLabel(t); l.setFont(f()); p.add(l, g);
    }
    private JButton boton(String label, Color color) {
        JButton btn = new JButton(label);
        btn.setFont(f()); btn.setBackground(color); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaMaestroDetalle().setVisible(true));
    }
}
