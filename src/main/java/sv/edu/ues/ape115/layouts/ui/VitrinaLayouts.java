package sv.edu.ues.ape115.layouts.ui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * R01 — Vitrina de Layouts (solución completa).
 * GridLayout(2,3): seis celdas, cada una con su Layout Manager distinto.
 * RN-R01.1 TitledBorder por celda. RN-R01.2 CardLayout navegable.
 * RN-R01.3 redimensionable. RN-R01.4 colores distintos.
 */
public class VitrinaLayouts extends JFrame {

    // Campo requerido por el test T02.3
    private final CardLayout cardDemo = new CardLayout();
    private final JPanel     pnlCard  = new JPanel(cardDemo);

    public VitrinaLayouts() {
        super("R01 — Vitrina: Los 6 Layout Managers de Swing");
        setLayout(new BorderLayout(8, 8));
        add(crearNorth(),  BorderLayout.NORTH);
        add(crearGrilla(), BorderLayout.CENTER);
        add(crearSouth(),  BorderLayout.SOUTH);
        getRootPane().setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        setSize(960, 640);
        setMinimumSize(new Dimension(740, 500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // ── NORTH: FlowLayout CENTER ──────────────────────────────────
    private JPanel crearNorth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(25, 118, 210)));
        JLabel lbl = new JLabel("Vitrina — Los 6 Layout Managers de Java Swing");
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lbl.setForeground(new Color(25, 118, 210));
        JLabel sub = new JLabel("  (Redimensiona la ventana para ver cómo se adapta cada uno)");
        sub.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        sub.setForeground(new Color(100, 100, 120));
        p.add(lbl); p.add(sub);
        return p;
    }

    // ── CENTER: GridLayout(2,3) ────────────────────────────────────
    private JPanel crearGrilla() {
        JPanel g = new JPanel(new GridLayout(2, 3, 10, 10));
        g.add(celda1_BorderLayout());
        g.add(celda2_FlowLayout());
        g.add(celda3_GridLayout());
        g.add(celda4_GridBagLayout());
        g.add(celda5_BoxLayout());
        g.add(celda6_CardLayout());
        return g;
    }

    // Celda 1 — BorderLayout (azul)
    private JPanel celda1_BorderLayout() {
        JPanel c = new JPanel(new BorderLayout(2, 2));
        c.setBorder(titledBorde("BorderLayout", new Color(25, 118, 210)));
        c.add(new JButton("NORTH"),  BorderLayout.NORTH);
        c.add(new JButton("SOUTH"),  BorderLayout.SOUTH);
        c.add(new JButton("EAST"),   BorderLayout.EAST);
        c.add(new JButton("WEST"),   BorderLayout.WEST);
        JLabel lbl = new JLabel("CENTER", SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(new Color(25, 118, 210));
        c.add(lbl, BorderLayout.CENTER);
        return c;
    }

    // Celda 2 — FlowLayout (verde)
    private JPanel celda2_FlowLayout() {
        JPanel c = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 4));
        c.setBorder(titledBorde("FlowLayout", new Color(46, 125, 50)));
        for (int i = 1; i <= 8; i++) {
            JButton b = new JButton("B" + i);
            b.setPreferredSize(new Dimension(50, 28));
            c.add(b);
        }
        return c;
    }

    // Celda 3 — GridLayout(3,3) (rojo)
    private JPanel celda3_GridLayout() {
        JPanel c = new JPanel(new GridLayout(3, 3, 3, 3));
        c.setBorder(titledBorde("GridLayout", new Color(198, 40, 40)));
        for (String k : new String[]{"7","8","9","4","5","6","1","2","3"})
            c.add(new JButton(k));
        return c;
    }

    // Celda 4 — GridBagLayout (violeta)
    private JPanel celda4_GridBagLayout() {
        JPanel c = new JPanel(new GridBagLayout());
        c.setBorder(titledBorde("GridBagLayout", new Color(100, 50, 150)));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(3, 4, 3, 4);
        g.anchor = GridBagConstraints.WEST;
        String[] lbls = {"Nombre:", "Email:", "Ciudad:"};
        for (int i = 0; i < lbls.length; i++) {
            g.gridx = 0; g.gridy = i;
            g.fill = GridBagConstraints.NONE; g.weightx = 0;
            c.add(new JLabel(lbls[i]), g);
            g.gridx = 1;
            g.fill = GridBagConstraints.HORIZONTAL; g.weightx = 1;
            c.add(new JTextField(10), g);
        }
        return c;
    }

    // Celda 5 — BoxLayout(Y_AXIS) (naranja)
    private JPanel celda5_BoxLayout() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBorder(titledBorde("BoxLayout Y_AXIS", new Color(230, 101, 0)));
        for (String item : new String[]{"Inicio","Productos","Clientes","Reportes","Configuración"}) {
            JLabel l = new JLabel("▸  " + item);
            l.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
            c.add(l);
            c.add(Box.createVerticalStrut(5));
        }
        c.add(Box.createVerticalGlue());
        return c;
    }

    // Celda 6 — CardLayout (verde azulado)
    private JPanel celda6_CardLayout() {
        JPanel c = new JPanel(new BorderLayout(0, 4));
        c.setBorder(titledBorde("CardLayout", new Color(0, 121, 107)));

        JPanel cardA = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardA.setBackground(new Color(232, 245, 233));
        cardA.add(new JLabel("🟢  Tarjeta A — Activa"));
        JPanel cardB = new JPanel(new FlowLayout(FlowLayout.CENTER));
        cardB.setBackground(new Color(227, 242, 253));
        cardB.add(new JLabel("🔵  Tarjeta B — Siguiente"));
        pnlCard.add(cardA, "A");
        pnlCard.add(cardB, "B");
        c.add(pnlCard, BorderLayout.CENTER);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        JButton btnAnt = new JButton("◀ Anterior");
        JButton btnSig = new JButton("Siguiente ▶");
        btnAnt.addActionListener(e -> cardDemo.previous(pnlCard));
        btnSig.addActionListener(e -> cardDemo.next(pnlCard));
        botones.add(btnAnt); botones.add(btnSig);
        c.add(botones, BorderLayout.SOUTH);
        return c;
    }

    // ── SOUTH: FlowLayout RIGHT ───────────────────────────────────
    private JPanel crearSouth() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        p.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)));
        JButton btn = new JButton("Salir");
        btn.addActionListener(e -> dispose());
        p.add(btn);
        return p;
    }

    // Helper: CompoundBorder con TitledBorder de color
    private Border titledBorde(String titulo, Color color) {
        return BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(color, 2),
                titulo, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 11), color),
            BorderFactory.createEmptyBorder(4, 6, 4, 6));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VitrinaLayouts().setVisible(true));
    }
}
