package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.CrearSerieCtr;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.SerieHome;

public class CrearSerieView {
	private SerieHome serieManager;
	private MediodepublicacionHome medMag;

	private JFrame frame;
	private JLabel nombreSerieLabel, anotPubLabel, generoLabel, medPubLabel;
	private JTextField nombreSerie, anotPub, genero;
	private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	private JButton bCrearSerie, bCancelar;
	List<Mediodepublicacion> listaMedSel = new ArrayList<Mediodepublicacion>();

	/**
	 * Launch the application.
	 */
	public static void main(AdministrarSerieView vista) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearSerieView window = new CrearSerieView(vista);
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private AdministrarSerieView vistaSerie;
	public CrearSerieView(AdministrarSerieView vista) {
		vistaSerie = vista;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serieManager = new SerieHome();
		medMag = new MediodepublicacionHome();

		frame = new JFrame();
		frame.setTitle("Creaci�n de serie");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(3, 2));
		frame.getContentPane().add(panelFormulario, BorderLayout.NORTH);
		nombreSerieLabel = new JLabel("Nombre: ");
		nombreSerie = new JTextField();
		anotPubLabel = new JLabel("Anotaciones Privada: ");
		anotPub = new JTextField();
		generoLabel = new JLabel("G�nero: ");
		genero = new JTextField();
		medPubLabel = new JLabel("Medios de publicaci�n: ");
		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		panelFormulario.add(nombreSerieLabel);
		panelFormulario.add(nombreSerie);
		panelFormulario.add(anotPubLabel);
		panelFormulario.add(anotPub);
		panelFormulario.add(generoLabel);
		panelFormulario.add(genero);
		
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.add(medPubLabel);
		panel2.add(listaMedio);
		panelFormulario.setVisible(true);
		panel2.setVisible(true);

		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		bCrearSerie = new JButton("Crear Serie");
		bCrearSerie.setActionCommand("CREAR");
		panelBotones.add(bCrearSerie);

		bCancelar = new JButton("Cancelar");
		panelBotones.add(bCancelar);
		bCancelar.setActionCommand("CANCELAR");
		
		
		listaMedio.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList lista = (JList) e.getSource();
				listaMedSel = (List<Mediodepublicacion>) lista.getSelectedValuesList();
			}
		});
		CrearSerieCtr ctr = new CrearSerieCtr(this,vistaSerie);
		muestraMedio();
		ControladorAction(ctr);
	}
	public void muestraMedio() {
		modeloMedio.clear();
		for (Mediodepublicacion m : medMag.list()) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
	}
	
	public List<Mediodepublicacion> getMedios(){
		return listaMedSel;
	}
	public String getNombreSerie() {
		return nombreSerie.getText();
	}

	public String getAnotPub() {
		return anotPub.getText();
	}

	public String getGenero() {
		return genero.getText();
	}

	public void ControladorAction(ActionListener ctr) {
		bCrearSerie.addActionListener(ctr);
		bCancelar.addActionListener(ctr);
	}

	public SerieHome getSerieManager() {
		return serieManager;
	}

	public void cerrarVentana() {
		frame.setVisible(false);
		frame.dispose();
	}
}
