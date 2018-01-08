package controlador;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FileUtils;

import interfaz.AdministrarComicView;
import orm.Comic;
import orm.ComicHasSerieHome;
import orm.ComicHasSerieId;
import orm.Serie;

public class AdministrarComicListaCtr implements ListSelectionListener {
	private AdministrarComicView vista;
	
	public AdministrarComicListaCtr(AdministrarComicView vista) {
		this.vista = vista;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList lista = (JList) e.getSource();

		if (!lista.isSelectionEmpty()) {
			System.out.println("Se ha detectado un cambio" + vista.serieSeleccionada().toString());
			Object o = lista.getSelectedValue();
			vista.activarEliminar();
			vista.activarModificar();
			if (true) { //Inicialmente había una precondición, pero la hemos tenido que eliminar para depurar fallos de JAVA.
				System.out.println("Es un comic, por cierto");
				Comic c = (Comic) o;
				vista.MuestraPublicacion(c);
				byte[] foto = c.getFoto();
				if(foto != null) {
					File imagen = new File("resultado.png");
					try {
						FileUtils.writeByteArrayToFile(imagen, foto);
						vista.pintaImagen(imagen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					vista.pintaImagen(new File("default.jpg"));
				}
				
				vista.setAnotacionPrivada(c.getAnotacionPrivada());
				
				vista.setAnotacionPublica(vista.getCHSManager().findById(new ComicHasSerieId(c.getIdComic(),vista.serieSeleccionada().getIdserie())).getAnotacionPublica());
				
				
			}
		}
	}
}
