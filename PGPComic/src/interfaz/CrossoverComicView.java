package interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controlador.CrossoverComicCtr;
import controlador.CrossoverComicListaCtr;
import orm.Comic;
import orm.ComicHasSerieHome;
import orm.ComicHome;
import orm.Mediodepublicacion;
import orm.MediodepublicacionHome;
import orm.Publicacion;
import orm.Serie;
import orm.SerieHome;

public class CrossoverComicView implements ActionListener, ListSelectionListener {

	private JFrame frame;
	private JLabel nombreComicLabel, numComicLabel, tiempoPublicacionLabel, anotPubLabel, anotPrivLabel;
	private JTextField nombreComic, numComic, anotPub, anotPriv;
	private JButton bAdjImagen, bCrear, bCancelar;
	private JLabel picLabel;
	private JScrollPane scrollableList, scrollableSerieList;
	private JList<Mediodepublicacion> listaMedio;
	private DefaultListModel<Mediodepublicacion> modeloMedio;
	private MediodepublicacionHome medioMag;
	private ComicHome comicMag;
	private JComboBox fechas;
	private ComicHasSerieHome chsMag;
	private SerieHome serieMag;
	private byte[] blob;
	private AdministrarComicView vista;
	private JList<Serie> listaSerie;
	private DefaultListModel<Serie> modeloSerie;
	private JPanel datosPanel, tPublicacionPanel, imagenPanel;
	private Map<Mediodepublicacion, Integer> fechaPublicacion;

	/**
	 * Launch the application.
	 */
	public static void main(AdministrarComicView vista) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrossoverComicView window = new CrossoverComicView(vista);
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
	public CrossoverComicView(AdministrarComicView vista) {
		this.vista = vista;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		chsMag = new ComicHasSerieHome();
		medioMag = new MediodepublicacionHome();
		comicMag = new ComicHome();
		serieMag = new SerieHome();
		
		
		fechaPublicacion = new HashMap<>();
		
		frame = new JFrame();
		frame.setTitle("Crear Crossover");
		frame.setBounds(100, 100, 800, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 2));

		datosPanel = new JPanel();
		datosPanel.setLayout(new GridLayout(2, 2));

		nombreComicLabel = new JLabel("Nombre: ");
		datosPanel.add(nombreComicLabel);
		// if (!vista.getComicSeleccionado().equals(null))
		// nombreComic = new
		// JTextField(vista.getComicSeleccionado().getNombre());
		// else
		nombreComic = new JTextField();
		datosPanel.add(nombreComic);

		numComicLabel = new JLabel("Número: ");
		datosPanel.add(numComicLabel);
//		if (!vista.getComicSeleccionado().equals(null))
//			numComic = new JTextField(vista.getComicSeleccionado().getIsbn().toString());
//		else
			numComic = new JTextField();
		datosPanel.add(numComic);
		datosPanel.setVisible(true);

		frame.getContentPane().add(datosPanel);

		tPublicacionPanel = new JPanel();
		tPublicacionPanel.setLayout(new GridLayout(2, 1));

		tiempoPublicacionLabel = new JLabel("Tiempo de Publicación: ");
		tPublicacionPanel.add(tiempoPublicacionLabel);
		frame.getContentPane().add(tPublicacionPanel);
		listaMedio = new JList<Mediodepublicacion>();

		scrollableList = new JScrollPane(listaMedio); // SCROLL
		
		fechas = new JComboBox();
		rellenarComboBox();
		

		modeloMedio = new DefaultListModel<>();
		listaMedio.setModel(modeloMedio);
		scrollableList.setViewportView(listaMedio); // SCROLL
		listaMedio.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tPublicacionPanel.add(scrollableList); // SCROLL
		tPublicacionPanel.add(new JLabel());
		tPublicacionPanel.add(fechas);

		imagenPanel = new JPanel();
		imagenPanel.setLayout(new GridLayout(1, 2));
		bAdjImagen = new JButton("Adjuntar Imagen");
		bAdjImagen.setActionCommand("ADJUNTAR");
		imagenPanel.add(bAdjImagen);
		picLabel = new JLabel();
		imagenPanel.add(new JScrollPane(picLabel));
		frame.getContentPane().add(imagenPanel);

		
		
		JPanel listaSeries = new JPanel();
		listaSeries.setLayout(new GridLayout(2, 1));
		listaSeries.add(new JLabel("Lista de series"));
		listaSerie = new JList<Serie>();
		modeloSerie = new DefaultListModel<>();
		listaSerie.setModel(modeloSerie);
		listaSerie.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		scrollableSerieList = new JScrollPane(listaSerie); // SCROLL
		scrollableSerieList.setViewportView(listaSerie); // SCROLL
		listaSeries.add(scrollableSerieList);
		frame.getContentPane().add(listaSeries);
		// frame.getContentPane().add(new JLabel()); // Relleno
		
		JPanel anotacionPanel = new JPanel();
		anotacionPanel.setLayout(new GridLayout(2, 2));
		anotPubLabel = new JLabel("Anotación Pública: ");
		anotacionPanel.add(anotPubLabel);
//		StringBuilder sb = new StringBuilder();
//		if (vista.getComicSeleccionado().equals(null)) {
//			for (ComicHasSerie cS : vista.getComicSeleccionado().getComicHasSeries()) {
//				sb.append("Serie: ");
//				sb.append(cS.getSerie().getNombre());
//				sb.append(" Anotación: ");
//				sb.append(cS.getAnotacionPublica());
//				sb.append("\n");
//			}
//		}
		anotPub = new JTextField();
		anotacionPanel.add(anotPub);
		anotPrivLabel = new JLabel("Anotación Privada: ");
		anotacionPanel.add(anotPrivLabel);
		anotPriv = new JTextField();
		anotacionPanel.add(anotPriv);
		frame.getContentPane().add(anotacionPanel);

		JPanel botonesPanel = new JPanel();
		botonesPanel.setLayout(new GridLayout(1, 2));
		bCrear = new JButton("CREAR");
		bCrear.setActionCommand("CREAR");
		botonesPanel.add(bCrear);
		bCancelar = new JButton("CANCELAR");
		bCancelar.setActionCommand("CANCELAR");
		botonesPanel.add(bCancelar);
		frame.getContentPane().add(botonesPanel, BorderLayout.CENTER);

		CrossoverComicListaCtr ctrl = new CrossoverComicListaCtr(this);
		listaSerie.addListSelectionListener(ctrl);
		fechas.addActionListener(this);
		listaMedio.addListSelectionListener(this);
		CrossoverComicCtr ctr = new CrossoverComicCtr(this, vista);
		ControladorAction(ctr);
		cargaMedios();
		cargaSeries();
	}

	public void ControladorAction(ActionListener ctr) {
		bAdjImagen.addActionListener(ctr);
		bCrear.addActionListener(ctr);
		bCancelar.addActionListener(ctr);
	}

	public void cargaMedios() {

		modeloMedio.clear();
		for (Mediodepublicacion m : medioMag.list()) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
	}

	public void muestraMedios(Comic c) {
		modeloMedio.clear();

		List<Mediodepublicacion> list = medioMag.list();
		for (Mediodepublicacion m : list) {
			if (!m.equals(null))
				modeloMedio.addElement(m);
		}
		int[] indices = new int[modeloMedio.size()];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = -1;
		}
		int x = 0;
		for (Mediodepublicacion m : list) {
			for (Publicacion p : c.getPublicacions()) {

				if (m.equals(p.getMediodepublicacion())) {
					indices[x] = list.indexOf(m);
					x++;
				}

			}

		}
		listaMedio.setSelectedIndices(indices);
	}

	public void cerrarVentana() {
		frame.setVisible(false);
		frame.dispose();
	}

	public ComicHome getComicMag() {
		return comicMag;
	}

	public ComicHasSerieHome getComHasSerie() {
		return chsMag;
	}

	public String getNombreComic() {
		return nombreComic.getText();
	}

	public int getNumComic() {
		int i = -1;
		try {
			i = Integer.parseInt(numComic.getText());

		} catch (NumberFormatException e) {

		}
		return i;
	}

	public String getAnotPub() {
		return anotPub.getText();
	}

	public String getAnotPriv() {
		return anotPriv.getText();
	}

	public void cargaSeries() {
		modeloSerie.clear();
		List<Serie> st = serieMag.list();
		for (Serie s : st) {
			modeloSerie.addElement(s);
		}
	}

	public List<Serie> seriesSeleccionadas() {
		return listaSerie.getSelectedValuesList();
	}

	public SerieHome getserieMag() {
		return serieMag;
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
	private void rellenarComboBox() {
		fechas.addItem("No Publicar");
		fechas.addItem("Una Semana");
		fechas.addItem("Un Mes");
		fechas.addItem("Dos Meses");
		fechas.addItem("Tres Meses");
		fechas.addItem("Un aÃ±o");
	}
	public Map<Mediodepublicacion, Integer> getMapa() {
		return fechaPublicacion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(fechas.getSelectedIndex());
		fechaPublicacion.put(listaMedio.getSelectedValue(), fechas.getSelectedIndex());
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		if (fechaPublicacion.containsKey(listaMedio.getSelectedValue())) {
			fechas.setSelectedIndex(fechaPublicacion.get(listaMedio.getSelectedValue()));
		}

	}
}
