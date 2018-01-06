package controlador;

import java.io.File;
import java.io.IOException;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.io.FileUtils;

import interfaz.AdministrarComicView;
import orm.Comic;

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

			if (o.getClass().equals(Comic.class)) {
				Comic c = (Comic) o;
				vista.MuestraPublicacion(c);
				byte[] foto = c.getFoto();
				if(foto != null) {
					File imagen = new File("resultado.jpg");
					try {
						FileUtils.writeByteArrayToFile(imagen, foto);
						vista.pintaImagen(imagen);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					vista.pintaImagen(imagen);
				}
			
			}
		}
	}
}
