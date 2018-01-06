package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import controlador.AdministrarComicListaCtr;
import controlador.AdministrarSerieComboBoxCtr;
import orm.Comic;
import orm.ComicHasSerie;
import orm.ComicHome;
import orm.Publicacion;
import orm.Serie;
import orm.SerieHome;

public class AdministrarComicView {
	private SerieHome serieManager;
	private ComicHome comicManager;
	private JFrame frmAdministracionDeSerie;
	private JList<ComicHasSerie> listaComic;
	private JList<Publicacion> listaPublicacion;
	private DefaultListModel<ComicHasSerie> modeloComic;
	private DefaultListModel<Publicacion> modeloPublicacion;
	private JButton bCrearComic, bModificarComic, bEliminarComic;
	private JComboBox serieComboBox;
	private JPanel panel_2;
	private JLabel picLabel;

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
		serieManager = new SerieHome();

		frmAdministracionDeSerie = new JFrame();
		frmAdministracionDeSerie.setTitle("Administracion de Comic");
		frmAdministracionDeSerie.setBounds(100, 100, 450, 300);
		frmAdministracionDeSerie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAdministracionDeSerie.getContentPane().setLayout(new BorderLayout(0, 0));

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

		JPanel panel_1 = new JPanel();

		frmAdministracionDeSerie.getContentPane().add(panel_1, BorderLayout.WEST);

		listaComic = new JList<ComicHasSerie>();
		listaComic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaComic.setSelectedIndex(0);
		modeloComic = new DefaultListModel<>();
		serieComboBox = new JComboBox();
		serieComboBox.setVisible(true);
		panel_1.add(serieComboBox);
		// Agrego los listener y las movidas
		serieComboBox.addActionListener(new AdministrarSerieComboBoxCtr(this));
		listaComic.setModel(modeloComic);
		panel_1.add(listaComic);
		listaPublicacion = new JList<Publicacion>();
		modeloPublicacion = new DefaultListModel<>();
		listaPublicacion.setModel(modeloPublicacion);
		panel_1.add(listaPublicacion);

		panel_2 = new JPanel();
		frmAdministracionDeSerie.getContentPane().add(panel_2, BorderLayout.CENTER);
		picLabel = new JLabel();

//		pintaImagen(new File("imagen.jpg"));

		panel_2.add(picLabel);
		
		listaComic.addListSelectionListener(new AdministrarComicListaCtr(this));
		CargaSeries();
	}

	public void ControladorAction(ActionListener ctr) {
		bCrearComic.addActionListener(ctr);
		bEliminarComic.addActionListener(ctr);
		bModificarComic.addActionListener(ctr);
	}

	public void CargaSeries() {
		serieComboBox.removeAllItems();
		List<Serie> st = serieManager.list();

		for (Serie s : st) {
			serieComboBox.addItem(s);
		}
	}

	public void MuestraPublicacion(Comic c) {
		modeloPublicacion.clear();

		for (Publicacion p : c.getPublicacions()) {
			modeloPublicacion.addElement(p);
		}
	}

	public void MuestraComic(Serie s) {
		modeloComic.clear();
		for (ComicHasSerie m : s.getComicHasSeries()) {
			modeloComic.addElement(m);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public SerieHome getSerieManager(){
		return serieManager;
	}

}
