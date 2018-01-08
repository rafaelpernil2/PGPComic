package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.commons.io.IOUtils;

import interfaz.AdministrarComicView;
import interfaz.CrearComicView;
import orm.Comic;
import orm.ComicHasSerie;
import orm.ComicHasSerieId;
import orm.Mediodepublicacion;
import orm.Publicacion;
import orm.PublicacionHome;
import orm.PublicacionId;

public class CrearComicCtr implements ActionListener {
	private CrearComicView vista;
	private AdministrarComicView vistaAdm;
	private byte[] blob;
	private PublicacionHome publicacionManager;

	public CrearComicCtr(CrearComicView vista, AdministrarComicView vistaAdm) {
		super();
		this.vista = vista;
		this.vistaAdm = vistaAdm;
		publicacionManager = new PublicacionHome();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "ADJUNTAR":
			JFileChooser fileChooser = new JFileChooser();
			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File fichero = fileChooser.getSelectedFile();

				FileInputStream foto = null;
				try {
					foto = new FileInputStream(fichero);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					blob = IOUtils.toByteArray(foto);
					if (blob != null)
						System.out.println("Archivo correcto!");
					vista.pintaImagen(fichero);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}
			break;
		case "CREAR":
			Comic c = new Comic(vista.getComicMag().getMaxID() + 1, vista.getNombreComic(), vista.getNumComic(),
					this.blob, vista.getAnotPriv(), new HashSet<ComicHasSerie>(), new HashSet<Publicacion>());
			ComicHasSerie cS = new ComicHasSerie(
					new ComicHasSerieId(c.getIdComic(), vistaAdm.serieSeleccionada().getIdserie()), c,
					vistaAdm.serieSeleccionada());
			// Set<ComicHasSerie> set = new HashSet<ComicHasSerie>();
			// set.add(cS);
			// c.setComicHasSeries(set);
			cS.setAnotacionPublica(vista.getAnotPub());
			vista.getComicMag().attachClean(c);
			vista.getComHasSerie().attachClean(cS);
			vista.cerrarVentana();
			vistaAdm.CargaSeries();

			anadirPublicaciones(c);

			break;

		case "CANCELAR":
			vista.cerrarVentana();
			break;
		default:
			break;
		}
	}

	private Date deIndexADate(int index) {
		Date res = new Date();
		Calendar cal = Calendar.getInstance(); // creates calendar
		switch (index) {
		case 0:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 0); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future

			break;
		case 1:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.HOUR_OF_DAY, 168); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future
			break;
		case 2:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.MONTH, 1); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future
			break;
		case 3:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.MONTH, 2); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future
			break;
		case 4:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.MONTH, 3); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future
			break;

		case 5:

			cal.setTime(res); // sets calendar time/date
			cal.add(Calendar.MONTH, 12); // adds one hour
			res = cal.getTime(); // returns new date object, one hour in the future
			break;
		}

		return res;
	}

	private void anadirPublicaciones(Comic c) {
		Map<Mediodepublicacion, Integer> mapa = vista.getMapa();

		for (Mediodepublicacion m : mapa.keySet()) {
			Date fecha = deIndexADate(mapa.get(m));
			PublicacionId pid = new PublicacionId(fecha, c.getIdComic(), m.getNombre()); 
			Publicacion p = new Publicacion(pid, c, m);
			publicacionManager.attachDirty(p);
		}

	}

}
