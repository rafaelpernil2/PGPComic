package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JComboBox;

import interfaz.AdministrarComicView;
import orm.Comic;
import orm.Serie;

public class AdministrarComicComboBoxCtr implements ActionListener {
	private AdministrarComicView vista;

	public AdministrarComicComboBoxCtr(AdministrarComicView vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Se ha invocado algo");
		JComboBox jb = (JComboBox) e.getSource();
	
		Serie s = (Serie) jb.getSelectedItem();
		System.out.println((Serie) jb.getSelectedItem());
		vista.MuestraComic((Serie) jb.getSelectedItem());
//		File imagen = new File("resultado.png");
//		vista.pintaImagen(imagen);

	}

}
