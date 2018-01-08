package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import controlador.AdministrarComicListaCtr;
import controlador.AdministrarComicComboBoxCtr;
import controlador.AdministrarComicCtr;
import orm.Comic;
import orm.ComicHasSerie;
import orm.ComicHasSerieHome;
import orm.ComicHome;
import orm.Publicacion;
import orm.Serie;
import orm.SerieHome;

public class AdministrarComicView {
	private SerieHome serieManager;
	private ComicHome comicManager;
	private ComicHasSerieHome chsManager; 
	private JFrame frmAdministracionDeSerie;
	private JList<Comic> listaComic;
	private JList<Publicacion> listaPublicacion;
	private DefaultListModel<Comic> modeloComic;
	private DefaultListModel<Publicacion> modeloPublicacion;
	private JButton bCrearComic, bModificarComic, bEliminarComic,bCrearCrossover;
	private JComboBox serieComboBox;
	private JPanel panel_2;
	private JLabel picLabel;
	private JTextArea anotpub, anotpriv;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarComicView window = new AdministrarComicView();
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
	public AdministrarComicView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Inicializo los Manager

		serieManager = new SerieHome();
		comicManager = new ComicHome();
		chsManager = new ComicHasSerieHome(); 
		// Fin de inicialización de Managers

		frmAdministracionDeSerie = new JFrame();
		frmAdministracionDeSerie.setTitle("Administracion de Comic");
		frmAdministracionDeSerie.setBounds(100, 100, 600, 300);
		frmAdministracionDeSerie.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdministracionDeSerie.getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		frmAdministracionDeSerie.getContentPane().add(panel, BorderLayout.SOUTH);

		bCrearComic = new JButton("Crear Comic");
		bCrearComic.setActionCommand("CREAR");
		panel.add(bCrearComic);

		bModificarComic = new JButton("Modificar Comic");
		panel.add(bModificarComic);
		bModificarComic.setActionCommand("MODIFICAR");

		bEliminarComic = new JButton("Eliminar Comic");
		panel.add(bEliminarComic);
		bEliminarComic.setActionCommand("ELIMINAR");
		bEliminarComic.setEnabled(false);

		bCrearCrossover = new JButton("Crear Crossover");
		panel.add(bCrearCrossover);
		bCrearCrossover.setActionCommand("CROSSOVER");
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(9,1));
		frmAdministracionDeSerie.getContentPane().add(panel_1, BorderLayout.WEST);

		listaComic = new JList<Comic>();
		listaComic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaComic.setSelectedIndex(0);
		modeloComic = new DefaultListModel<>();
		serieComboBox = new JComboBox();
		serieComboBox.setVisible(true);
		panel_1.add(serieComboBox);
		// Agrego los listener y las movidas
		serieComboBox.addActionListener(new AdministrarComicComboBoxCtr(this));
		ControladorAction(new AdministrarComicCtr(this));
		listaComic.setModel(modeloComic);
		panel_1.add(new JLabel("Comics:"));
		panel_1.add(new JScrollPane(listaComic));
		listaPublicacion = new JList<Publicacion>();
		modeloPublicacion = new DefaultListModel<>();
		listaPublicacion.setModel(modeloPublicacion);
		anotpub = new JTextArea(10,10);
		anotpriv = new JTextArea(10,10);
		anotpub.setLineWrap(true);
		anotpriv.setLineWrap(true);
		
		
		panel_1.add(new JLabel("Publicaciones:"));

		
		panel_1.add(new JScrollPane(listaPublicacion));
		panel_1.add(new JLabel("Anotacion Publica:"));

		panel_1.add(new JScrollPane(anotpub));
		panel_1.add(new JLabel("Anotacion Privada"));

		panel_1.add(new JScrollPane(anotpriv));
		
		
		
		
		panel_2 = new JPanel();
		frmAdministracionDeSerie.getContentPane().add(panel_2, BorderLayout.CENTER);
		picLabel = new JLabel();
		picLabel.setSize(100, 100);
		
	
		panel_2.add(new JScrollPane(picLabel));

		listaComic.addListSelectionListener(new AdministrarComicListaCtr(this));
		CargaSeries();
	}

	public void ControladorAction(ActionListener ctr) {
		bCrearComic.addActionListener(ctr);
		bEliminarComic.addActionListener(ctr);
		bModificarComic.addActionListener(ctr);
		bCrearCrossover.addActionListener(ctr);
	}

	public void CargaSeries() {
		serieComboBox.removeAllItems();
		bEliminarComic.setEnabled(false);
		bModificarComic.setEnabled(false);
		List<Serie> st = serieManager.list();
		for (Serie s : st) {
			serieComboBox.addItem(s);
		}
	}
	public void activarEliminar(){
		bEliminarComic.setEnabled(true);
	}
	public void activarModificar(){
		bModificarComic.setEnabled(true);
	}
	public void MuestraPublicacion(Comic c) {
		modeloPublicacion.clear();

		for (Publicacion p : c.getPublicacions()) {
			modeloPublicacion.addElement(p);
		}
	}

	public void MuestraComic(Serie s) {
		modeloComic.clear();
		bModificarComic.setEnabled(false);
		bEliminarComic.setEnabled(false);
		if(s != null) {
			for (ComicHasSerie m : s.getComicHasSeries()) {
				modeloComic.addElement(m.getComic());
			}
		}
		
	}

	public Serie serieSeleccionada() {
		return (Serie) serieComboBox.getSelectedItem();
	}

	public void pintaImagen(File imagen) {
		try {
			BufferedImage myPicture = ImageIO.read(imagen);
			ImageIcon im = new ImageIcon();
			im.setImage(myPicture);
			picLabel.setIcon(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public SerieHome getSerieManager() {
		return serieManager;
	}
	public ComicHasSerieHome getCHSManager() {
		return chsManager; 
	}

	public Comic getComicSeleccionado() {
		return !listaComic.isSelectionEmpty()? listaComic.getSelectedValue() : null;
	}

	public ComicHome getComicManager() {
		return comicManager;
	}
	public void setAnotacionPublica(String texto) {
		anotpub.setText(texto);
	}
	public void setAnotacionPrivada(String texto) {
		anotpriv.setText(texto);
	}

}
