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

import controlador.ModificarMedioCtr;
import controlador.ModificarSerieCtr;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.Serie;
import orm.SerieHome;

public class ModificarMedioView {
	private MediodepublicacionHome medioManager;

	private JFrame frame;
	private JLabel nombreMedioLabel;
	private JTextField nombreMedio;
	private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	private JButton bModificarMedio, bCancelar;
	List<Mediodepublicacion> listaMedSel = new ArrayList<Mediodepublicacion>(0);

	/**
	 * Launch the application.
	 */
	public static void main(AdministrarMedioView vista) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarMedioView window = new ModificarMedioView(vista);
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
	private AdministrarMedioView vistaMedio;

	public ModificarMedioView(AdministrarMedioView vista) {
		vistaMedio = vista;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		medioManager = new MediodepublicacionHome();
		//medMag = new MediodepublicacionHome();

		frame = new JFrame();
		frame.setTitle("Modificacion de medio");
		frame.setBounds(100, 100, 250, 150);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelFormulario = new JPanel();
		panelFormulario.setLayout(new GridLayout(3, 2));
		frame.getContentPane().add(panelFormulario, BorderLayout.NORTH);
		Mediodepublicacion m = vistaMedio.medioSeleccionado();
		nombreMedioLabel = new JLabel("Nombre: ");
		nombreMedio = new JTextField(m.getNombre());
//		anotPubLabel = new JLabel("Anotaciones Privada: ");
//		anotPub = new JTextField(s.getAnotacionPrivada());
//		generoLabel = new JLabel("G�nero: ");
//		genero = new JTextField(s.getGenero());
//		medPubLabel = new JLabel("Medios de publicaci�n: ");
		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		panelFormulario.add(nombreMedioLabel);
		panelFormulario.add(nombreMedio);
//		panelFormulario.add(anotPubLabel);
//		panelFormulario.add(anotPub);
//		panelFormulario.add(generoLabel);
//		panelFormulario.add(genero);
		panelFormulario.setVisible(true);

//		JPanel panel2 = new JPanel();
//		panel2.setLayout(new GridLayout(2, 1));
//		frame.getContentPane().add(panel2, BorderLayout.CENTER);
//		panel2.add(medPubLabel);
//		panel2.add(listaMedio);
//		panelFormulario.setVisible(true);
//		panel2.setVisible(true);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(1, 2));
		frame.getContentPane().add(panelBotones, BorderLayout.SOUTH);

		bModificarMedio = new JButton("Modificar Medio");
		bModificarMedio.setActionCommand("MODIFICAR");
		panelBotones.add(bModificarMedio);

		bCancelar = new JButton("Cancelar");
		bCancelar.setActionCommand("CANCELAR");
		bCancelar.setEnabled(true);
		panelBotones.add(bCancelar);
		

		listaMedio.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				JList<Mediodepublicacion> lista = (JList<Mediodepublicacion>) e.getSource();
				listaMedSel = (List<Mediodepublicacion>) lista.getSelectedValuesList();
			}
		});
		ModificarMedioCtr ctr = new ModificarMedioCtr(this, vistaMedio);
		muestraMedio(m);
		ControladorAction(ctr);
	}

	public void muestraMedio(Mediodepublicacion m) {
		modeloMedio.clear();

		List<Mediodepublicacion> list = medioManager.list();
		for (Mediodepublicacion med : list) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
		int[] indices = new int[modeloMedio.size()];
		for (int i = 0; i < indices.length; i++) {
			indices[i]=-1;
		}
		int x = 0;
	}

	public String getNombreMedio() {
		return nombreMedio.getText();
	}

	public List<Mediodepublicacion> getMedios() {
		return listaMedSel;
	}

	public void ControladorAction(ActionListener ctr) {
		bModificarMedio.addActionListener(ctr);
		bCancelar.addActionListener(ctr);
	}

	public MediodepublicacionHome getMedioManager() {
		return medioManager;
	}

	public void cerrarVentana() {
		frame.setVisible(false);
		frame.dispose();
	}
}
