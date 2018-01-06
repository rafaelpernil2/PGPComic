package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import interfaz.AdministrarComicView;
import orm.Comic;
import orm.Serie;

public class AdministrarSerieComboBoxCtr implements ActionListener {
	private AdministrarComicView vista;  
	public AdministrarSerieComboBoxCtr(AdministrarComicView vista) {
		this.vista = vista; 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Se ha invocado algo");
		JComboBox jb = (JComboBox) e.getSource();
		Serie s = (Serie) jb.getSelectedItem();
		System.out.println((Serie) jb.getSelectedItem());
		vista.MuestraComic((Serie) jb.getSelectedItem());
//		File imagen = new File("portada.jpg");
//		vista.pintaImagen(imagen);
		
	}

}
