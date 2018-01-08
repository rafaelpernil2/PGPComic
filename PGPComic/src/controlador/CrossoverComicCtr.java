package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JFileChooser;

import org.apache.commons.io.IOUtils;

import interfaz.AdministrarComicView;
import interfaz.CrossoverComicView;
import orm.Comic;
import orm.ComicHasSerie;
import orm.ComicHasSerieId;
import orm.Publicacion;
import orm.Serie;

public class CrossoverComicCtr implements ActionListener {
	private CrossoverComicView vista;
	private AdministrarComicView vistaAdm;
	private byte[] blob;

	public CrossoverComicCtr(CrossoverComicView vista, AdministrarComicView vistaAdm) {

		super();
		this.vista = vista;
		this.vistaAdm = vistaAdm;
		// TODO Auto-generated constructor stub
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
			for (Serie s : vista.seriesSeleccionadas()) {
				ComicHasSerie cS = new ComicHasSerie(new ComicHasSerieId(c.getIdComic(), s.getIdserie()), c, s);
				cS.setAnotacionPublica(vista.getAnotPub());
				vista.getComicMag().attachDirty(c);	
				vista.getComHasSerie().attachDirty(cS);
			}
			vista.cerrarVentana();
			vistaAdm.CargaSeries();
			break;
		case "CANCELAR":
			vista.cerrarVentana();
			break;
		default:
			break;
		}
	}

}
