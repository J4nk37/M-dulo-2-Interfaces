import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestorUsuarios extends JFrame {

    private final JPanel panelCentral = new JPanel(new CardLayout());

    // Área de logs y resumen (a la derecha)
    private final JTextArea areaResumen = new JTextArea();
    private final JTextArea areaLogs = new JTextArea();

    // Modelo de usuarios
    private final DefaultTableModel modeloUsuarios = new DefaultTableModel(new String[]{"Nombre", "Rol"}, 0);

    // Ajustes
    private final JCheckBox chkNotificaciones = new JCheckBox("Activar notificaciones");
    private final JCheckBox chkOscuro = new JCheckBox("Habilitar modo oscuro");
    private final JCheckBox chkConexion = new JCheckBox("Conexión automática");

    public GestorUsuarios() {
        setTitle("Gestor de usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setResizable(true);

        getContentPane().setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(8, 10, 10, 10));

        // ---------------- Cabecera ----------------
        JPanel panelCabecera = new JPanel(new BorderLayout());
        JLabel lblTitulo = new JLabel("Gestor de usuarios", UIManager.getIcon("FileView.directoryIcon"), JLabel.LEFT);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 18f));
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        panelTitulo.add(lblTitulo);
        panelCabecera.add(panelTitulo, BorderLayout.NORTH);
        panelCabecera.add(new JSeparator(), BorderLayout.SOUTH);
        add(panelCabecera, BorderLayout.NORTH);

        // ---------------- Navegación ----------------
        JPanel panelNavegacion = new JPanel(new GridLayout(6, 1, 0, 8));
        JButton btnFormulario = new JButton("Formulario");
        JButton btnDashboard = new JButton("Dashboard");
        JButton btnUsuarios = new JButton("Usuarios");
        JButton btnInformes = new JButton("Informes");
        JButton btnAjustes = new JButton("Ajustes");
        JButton btnAyuda = new JButton("Ayuda");

        for (JButton b : new JButton[]{btnFormulario, btnDashboard, btnUsuarios, btnInformes, btnAjustes, btnAyuda}) {
            b.setFocusPainted(false);
            panelNavegacion.add(b);
        }
        add(panelNavegacion, BorderLayout.WEST);

        // ---------------- Panel central (CardLayout) ----------------
        panelCentral.setBorder(new EmptyBorder(8, 10, 10, 10));
        panelCentral.add(crearVistaFormulario(), "FORMULARIO"); // vista principal
        panelCentral.add(crearVistaDashboard(), "DASHBOARD");
        panelCentral.add(crearVistaUsuarios(), "USUARIOS");
        panelCentral.add(crearVistaInformes(), "INFORMES");
        panelCentral.add(crearVistaAjustes(), "AJUSTES");
        panelCentral.add(crearVistaAyuda(), "AYUDA");
        add(panelCentral, BorderLayout.CENTER);

        // ---------------- Previsualización (derecha) ----------------
        JPanel panelPreview = new JPanel(new BorderLayout());
        panelPreview.setPreferredSize(new Dimension(300, 0));
        JTabbedPane pestañas = new JTabbedPane();

        areaResumen.setEditable(false);
        pestañas.addTab("Resumen", new JScrollPane(areaResumen));

        areaLogs.setEditable(false);
        pestañas.addTab("Logs", new JScrollPane(areaLogs));

        panelPreview.add(pestañas, BorderLayout.CENTER);
        add(panelPreview, BorderLayout.EAST);

        // ---------------- Barra inferior ----------------
        JPanel barraBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnLimpiar = new JButton("Limpiar Logs");
        JButton btnGuardar = new JButton("Guardar");

        barraBotones.add(btnCancelar);
        barraBotones.add(btnLimpiar);
        barraBotones.add(btnGuardar);
        add(barraBotones, BorderLayout.SOUTH);

        // ---------------- Listeners de navegación ----------------
        CardLayout cl = (CardLayout) panelCentral.getLayout();
        btnFormulario.addActionListener(e -> cl.show(panelCentral, "FORMULARIO"));
        btnDashboard.addActionListener(e -> cl.show(panelCentral, "DASHBOARD"));
        btnUsuarios.addActionListener(e -> cl.show(panelCentral, "USUARIOS"));
        btnInformes.addActionListener(e -> cl.show(panelCentral, "INFORMES"));
        btnAjustes.addActionListener(e -> cl.show(panelCentral, "AJUSTES"));
        btnAyuda.addActionListener(e -> cl.show(panelCentral, "AYUDA"));

        // ---------------- Acciones globales ----------------
        btnCancelar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> areaLogs.setText(""));
        btnGuardar.addActionListener(e -> JOptionPane.showMessageDialog(this, "Guardado general (demo)."));
    }

    // ---------- Formulario principal ----------
    private JPanel crearVistaFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        int fila = 0;

        JTextField campoNombre = new JTextField();
        JTextField campoEmail = new JTextField();
        JComboBox<String> comboRol = new JComboBox<>(new String[]{"Admin", "Editor", "Invitado"});
        JCheckBox checkActivo = new JCheckBox("Activo", true);
        JTextArea areaNotas = new JTextArea(5, 20);

        // Nombre
        gbc.gridx = 0; gbc.gridy = fila; gbc.weightx = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoNombre, gbc);
        fila++;

        // Email
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campoEmail, gbc);
        fila++;

        // Rol
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Rol:"), gbc);
        gbc.gridx = 1;
        panel.add(comboRol, gbc);
        fila++;

        // Activo
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Activo:"), gbc);
        gbc.gridx = 1;
        panel.add(checkActivo, gbc);
        fila++;

        // Notas
        gbc.gridx = 0; gbc.gridy = fila; gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(new JLabel("Notas:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1; gbc.weighty = 1;
        JScrollPane scrollNotas = new JScrollPane(areaNotas);
        areaNotas.setLineWrap(true);
        areaNotas.setWrapStyleWord(true);
        panel.add(scrollNotas, gbc);

        return panel;
    }

    // ---------- Dashboard ----------
    private JPanel crearVistaDashboard() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.add(new JLabel("Dashboard", JLabel.CENTER), BorderLayout.NORTH);
        panel.add(new JLabel("Métricas: 12 usuarios, 5 informes...", JLabel.CENTER), BorderLayout.CENTER);
        return panel;
    }

    // ---------- Usuarios ----------
    private JPanel crearVistaUsuarios() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JTable tabla = new JTable(modeloUsuarios);
        modeloUsuarios.addRow(new String[]{"Juan", "Admin"});
        modeloUsuarios.addRow(new String[]{"Ana", "Editor"});
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    // ---------- Informes ----------
    private JPanel crearVistaInformes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> lista = new JList<>(modelo);
        JButton btnGenerar = new JButton("Generar informe");
        btnGenerar.addActionListener(e -> modelo.addElement("Informe " + (modelo.size() + 1)));
        panel.add(new JScrollPane(lista), BorderLayout.CENTER);
        panel.add(btnGenerar, BorderLayout.SOUTH);
        return panel;
    }

    // ---------- Ajustes ----------
    private JPanel crearVistaAjustes() {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.add(chkNotificaciones);
        panel.add(chkOscuro);
        panel.add(chkConexion);
        return panel;
    }

    // ---------- Ayuda ----------
    private JPanel crearVistaAyuda() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JTextArea area = new JTextArea("Manual de usuario:\n- Paso 1...\n- Paso 2...");
        area.setEditable(false);
        panel.add(new JScrollPane(area), BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new GestorUsuarios().setVisible(true));
    }
}
