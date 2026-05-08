package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * R03 — Menú Lateral con BoxLayout y CardLayout (solución completa).
 * RN-R03.1 navegación funcional. RN-R03.2 botón activo cambia color.
 * RN-R03.3 Salir al fondo con createVerticalGlue(). RN-R03.4 min 800×500.
 */
public class AppMenuLateral extends JFrame {

    // Campos requeridos por el test T04.3
    private final CardLayout card      = new CardLayout();
    private final JPanel     pnlCentro = new JPanel(card);

    private static final String[] MODULOS =
        {"Inicio", "Productos", "Clientes", "Reportes", "Configuración"};

    private JButton btnActivo = null;
    private static final Color COLOR_ACTIVO   = new Color(25, 118, 210);
    private static final Color COLOR_INACTIVO = new Color(50, 55, 75);

    public AppMenuLateral() {
        super("R03 — Menú Lateral: BoxLayout + CardLayout");
        construirUI();
        setSize(900, 580);
        setMinimumSize(new Dimension(800, 500));   // RN-R03.4
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void construirUI() {
        JPanel root = new JPanel(new BorderLayout(0, 0));
        setContentPane(root);
        root.add(crearMenuWest(), BorderLayout.WEST);
        root.add(crearCentro(),   BorderLayout.CENTER);
    }

    // ── WEST: BoxLayout(Y_AXIS) ───────────────────────────────────
    private JPanel crearMenuWest() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(new Color(33, 37, 50));
        p.setPreferredSize(new Dimension(180, 0));

        // RN-R03 CompoundBorder en el panel lateral
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(55, 60, 75)),
            BorderFactory.createEmptyBorder(16, 8, 16, 8)));

        // Título del sistema
        JLabel titulo = new JLabel("APE 115  G0301");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titulo.setForeground(new Color(140, 155, 200));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(titulo);
        p.add(Box.createVerticalStrut(4));
        p.add(sep());
        p.add(Box.createVerticalStrut(10));

        // Botones de módulo (RN-R03.1 y R03.2)
        for (String mod : MODULOS) {
            JButton btn = botonNav(mod);
            btn.addActionListener(e -> {
                card.show(pnlCentro, mod);           // RN-R03.1
                if (btnActivo != null)               // RN-R03.2
                    btnActivo.setBackground(COLOR_INACTIVO);
                btn.setBackground(COLOR_ACTIVO);
                btnActivo = btn;
            });
            p.add(btn);
            p.add(Box.createVerticalStrut(4));
            if (btnActivo == null) {
                btn.setBackground(COLOR_ACTIVO);
                btnActivo = btn;
            }
        }

        // RN-R03.3 Salir pegado al fondo
        p.add(Box.createVerticalGlue());
        p.add(sep());
        p.add(Box.createVerticalStrut(8));
        JButton btnSalir = botonNav("⏻  Salir");
        btnSalir.setBackground(new Color(120, 40, 40));
        btnSalir.addActionListener(e -> dispose());
        p.add(btnSalir);

        return p;
    }

    // ── CENTER: CardLayout ────────────────────────────────────────
    private JPanel crearCentro() {
        for (String mod : MODULOS)
            pnlCentro.add(crearVistaModulo(mod), mod);
        return pnlCentro;
    }

    /** Vista interna de cada módulo con GridBagLayout y TitledBorder. */
    private JPanel crearVistaModulo(String nombre) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
                nombre, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12), new Color(25, 118, 210)),
            BorderFactory.createEmptyBorder(16, 20, 16, 20)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        // Título grande
        JLabel lbl = new JLabel(nombre);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl.setForeground(new Color(25, 118, 210));
        g.gridx = 0; g.gridy = 0; g.gridwidth = 2;
        p.add(lbl, g);

        // Tres campos de ejemplo
        String[] camps = {"Campo 1:", "Campo 2:", "Campo 3:"};
        for (int i = 0; i < camps.length; i++) {
            g.gridx = 0; g.gridy = i + 1; g.gridwidth = 1;
            g.fill = GridBagConstraints.NONE; g.weightx = 0;
            JLabel e = new JLabel(camps[i]);
            e.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            p.add(e, g);
            g.gridx = 1; g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
            JTextField tf = new JTextField(20);
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            p.add(tf, g);
        }
        // Relleno vertical
        g.gridx = 0; g.gridy = 4; g.gridwidth = 2;
        g.fill = GridBagConstraints.BOTH; g.weighty = 1;
        p.add(new JPanel(), g);
        return p;
    }

    // ── Helpers ───────────────────────────────────────────────────
    private JButton botonNav(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_INACTIVO);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setFocusPainted(false); btn.setBorderPainted(false); btn.setOpaque(true);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JSeparator sep() {
        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setForeground(new Color(60, 65, 80));
        s.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        s.setAlignmentX(Component.LEFT_ALIGNMENT);
        return s;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppMenuLateral().setVisible(true));
    }
}
