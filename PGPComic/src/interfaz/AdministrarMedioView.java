package interfaz;

/*
 * Creado por Francisco Gambero Salinas
 * 6 de Enero de 2018
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import controlador.AdministrarMedioCtr;
import controlador.AdministrarSerieCtr;
import controlador.AdministrarSerieListaCtr;
//import orm.MedioHome;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.Serie;
import orm.SerieHome;

public class AdministrarMedioView {
	private MediodepublicacionHome medioManager;
	
	private JFrame frmAdministracionDeMedio;
	private JList<Mediodepublicacion> listaMedio;
	//private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	//private DefaultListModel<Mediodepublicacion> modeloMedio;
	private JButton bAnadirMedio, bModificarMedio, bEliminarMedio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarMedioView window = new AdministrarMedioView();
					window.frmAdministracionDeMedio.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdministrarMedioView() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		medioManager = new MediodepublicacionHome();

		frmAdministracionDeMedio = new JFrame();
		frmAdministracionDeMedio.setTitle("Administracion de Medios");
		frmAdministracionDeMedio.setBounds(100, 100, 450, 300);
		frmAdministracionDeMedio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministracionDeMedio.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frmAdministracionDeMedio.getContentPane().add(panel, BorderLayout.SOUTH);

		bAnadirMedio = new JButton("Añadir Medio");
		bAnadirMedio.setActionCommand("CREAR"); //Antes AÃ‘ADIR, ahora CREAR
		panel.add(bAnadirMedio);

		bModificarMedio = new JButton("Modificar Medio");
		panel.add(bModificarMedio);
		bModificarMedio.setActionCommand("MODIFICAR");
		bEliminarMedio = new JButton("Eliminar Medio");
		panel.add(bEliminarMedio);
		bEliminarMedio.setActionCommand("ELIMINAR");
		JPanel panel_1 = new JPanel();

		frmAdministracionDeMedio.getContentPane().add(panel_1, BorderLayout.WEST);

		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		listaMedio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(listaMedio);
		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		listaMedio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(listaMedio);
		
		
		AdministrarMedioCtr ctr = new AdministrarMedioCtr(this);
		ControladorAction(ctr);
		cargaMedios();
	}
	
	public void ControladorAction(ActionListener ctr) {
		bAnadirMedio.addActionListener(ctr);
		bEliminarMedio.addActionListener(ctr);
		bModificarMedio.addActionListener(ctr);
	}
	
	public void deseleccionar() {
		listaMedio.clearSelection();
	}
	
	public void volverASeleccionar(int index){
		listaMedio.setSelectedIndex(index);
	}
	
	public int getCurrentIndex(){
		return listaMedio.getSelectedIndex();
	}
	
	public void cargaMedios() {
		
		modeloMedio.clear();
		List<Mediodepublicacion> st = medioManager.list();
		for (Mediodepublicacion s : st) {
			modeloMedio.addElement(s);
		}
	}

	public void muestraMedio(Serie s) {
		
		modeloMedio.clear();
		for (Mediodepublicacion m : s.getMediodepublicacions()) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
	}
	public Mediodepublicacion medioSeleccionado() {
		return listaMedio.getSelectedValue();
	}

	public MediodepublicacionHome getMedioManager() {
		return medioManager;
	}

	public void limpiaMedios() {
		modeloMedio.clear();
		
	}
}
