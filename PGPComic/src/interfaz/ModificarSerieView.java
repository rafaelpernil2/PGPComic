package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

import controlador.ModificarSerieCtr;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.Serie;
import orm.SerieHome;

public class ModificarSerieView {
	private SerieHome serieManager;
	private MediodepublicacionHome medMag;

	private JFrame frame;
	private JLabel nombreSerieLabel, anotPubLabel, generoLabel, medPubLabel;
	private JTextField nombreSerie, anotPub, genero;
	private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	private JButton bCrearSerie, bCancelar;
	List<Mediodepublicacion> listaMedSel = new ArrayList<Mediodepublicacion>(0);

	/**
	 * Launch the application.
	 */
	public static void main(AdministrarSerieView vista) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarSerieView window = new ModificarSerieView(vista);
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

	public ModificarSerieView(AdministrarSerieView vista) {
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
		frame.setTitle("Modificaciï¿½n de serie");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(3, 2));
		frame.getContentPane().add(panelFormulario, BorderLayout.NORTH);
		Serie s = vistaSerie.serieSeleccionada();
		nombreSerieLabel = new JLabel("Nombre: ");
		nombreSerie = new JTextField(s.getNombre());
		anotPubLabel = new JLabel("Anotaciones Privada: ");
		anotPub = new JTextField(s.getAnotacionPrivada());
		generoLabel = new JLabel("Género: ");
		genero = new JTextField(s.getGenero());
		medPubLabel = new JLabel("Medios de publicación: ");
		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		panelFormulario.add(nombreSerieLabel);
		panelFormulario.add(nombreSerie);
		panelFormulario.add(anotPubLabel);
		panelFormulario.add(anotPub);
		panelFormulario.add(generoLabel);
		panelFormulario.add(genero);
		panelFormulario.setVisible(true);

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2, 1));
		frame.getContentPane().add(panel2, BorderLayout.CENTER);
		panel2.add(medPubLabel);
		panel2.add(listaMedio);
		panelFormulario.setVisible(true);
		panel2.setVisible(true);

		JPanel panelBotones = new JPanel();
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		bCrearSerie = new JButton("Modificar Serie");
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
		ModificarSerieCtr ctr = new ModificarSerieCtr(this, vistaSerie);
		muestraMedio(s);
		ControladorAction(ctr);
	}

	public void muestraMedio(Serie s) {
		modeloMedio.clear();

		List<Mediodepublicacion> list = medMag.list();
		for (Mediodepublicacion m : list) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
		int[] indices = new int[modeloMedio.size()];
		for (int i = 0; i < indices.length; i++) {
			indices[i]=-1;
		}
		int x = 0;
		for (Mediodepublicacion m : list) {
			for (Mediodepublicacion mediodepublicacion : s.getMediodepublicacions()) {
				if (m.equals(mediodepublicacion)) {
					indices[x] = list.indexOf(m);
					x++;
				}
			}
		}
		listaMedio.setSelectedIndices(indices);
	}

	public String getNombreSerie() {
		return nombreSerie.getText();
	}

	public List<Mediodepublicacion> getMedios() {
		return listaMedSel;
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
