package interfaz;

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

import controlador.AdministrarSerieCtr;
import controlador.AdministrarSerieListaCtr;
import orm.Mediodepublicacion;
import orm.Serie;
import orm.SerieHome;

public class AdministrarSerieView {
	private SerieHome serieManager;

	private JFrame frmAdministracionDeSerie;
	private JList<Serie> listaSerie;
	private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Serie> modeloSerie;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	private JButton bCrearSerie, bModificarSerie, bEliminarSerie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarSerieView window = new AdministrarSerieView();
					window.frmAdministracionDeSerie.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdministrarSerieView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		serieManager = new SerieHome();

		frmAdministracionDeSerie = new JFrame();
		frmAdministracionDeSerie.setTitle("Administracion de Serie");
		frmAdministracionDeSerie.setBounds(100, 100, 450, 300);
		frmAdministracionDeSerie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministracionDeSerie.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		frmAdministracionDeSerie.getContentPane().add(panel, BorderLayout.SOUTH);

		bCrearSerie = new JButton("Crear Serie");
		bCrearSerie.setActionCommand("CREAR");
		panel.add(bCrearSerie);

		bModificarSerie = new JButton("Modificar Serie");
		panel.add(bModificarSerie);
		bModificarSerie.setActionCommand("MODIFICAR");
		bEliminarSerie = new JButton("Eliminar Serie");
		panel.add(bEliminarSerie);
		bEliminarSerie.setEnabled(false);
		bEliminarSerie.setActionCommand("ELIMINAR");
		JPanel panel_1 = new JPanel();

		frmAdministracionDeSerie.getContentPane().add(panel_1, BorderLayout.WEST);

		listaSerie = new JList<Serie>();
		modeloSerie = new DefaultListModel<>();
		listaSerie.setModel(modeloSerie);
		listaSerie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(listaSerie);
		listaMedio = new JList<Mediodepublicacion>();
		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		listaMedio.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel_1.add(listaMedio);
		
		

		AdministrarSerieListaCtr ctrl = new AdministrarSerieListaCtr(this);
		listaSerie.addListSelectionListener(ctrl);
		
		AdministrarSerieCtr ctr = new AdministrarSerieCtr(this);
		ControladorAction(ctr);
		cargaSeries();
	}

	public void ControladorAction(ActionListener ctr) {
		bCrearSerie.addActionListener(ctr);
		bEliminarSerie.addActionListener(ctr);
		bModificarSerie.addActionListener(ctr);
	}

	public void deseleccionar() {
		listaSerie.clearSelection();
		bEliminarSerie.setEnabled(false);
	}
	public void volverASeleccionar(int index){
		listaSerie.setSelectedIndex(index);
	}
	public int getCurrentIndex(){
		return listaSerie.getSelectedIndex();
	}
	public void cargaSeries() {
		modeloSerie.clear();
		List<Serie> st = serieManager.list();
		for (Serie s : st) {
			modeloSerie.addElement(s);
		}
	}
	public Serie serieSeleccionada() {
		return listaSerie.getSelectedValue();
	}

	public SerieHome getSerieManager() {
		return serieManager;
	}
	public void activarEliminar(){
		bEliminarSerie.setEnabled(true);
	}
	public void muestraMedio(Serie s) {
		modeloMedio.clear();
		for (Mediodepublicacion m : s.getMediodepublicacions()) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
	}
	

	public void limpiaMedios() {
		modeloMedio.clear();
		
	}

}
