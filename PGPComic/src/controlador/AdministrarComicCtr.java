package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import interfaz.AdministrarComicView;
import interfaz.CrearComicView;
import interfaz.CrossoverComicView;
import orm.Comic;

public class AdministrarComicCtr implements ActionListener {
	private AdministrarComicView vista;

	public AdministrarComicCtr(AdministrarComicView vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		switch (cmd) {
		case "CREAR":
			CrearComicView.main(vista);

			break;
		case "MODIFICAR":

			break;
		case "ELIMINAR":
			int dialogResult = JOptionPane.showConfirmDialog(null,
					"¿Seguro que quiere eliminar el Comic" + vista.getComicSeleccionado() + "?", "Atencion",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Comic c = vista.getComicSeleccionado();

				if (c != null) {
					vista.getComicManager().delete(c);
					vista.CargaSeries();
					// vista.MuestraComic(vista.serieSeleccionada());

				}
			}
			break;

		case "CROSSOVER":
			CrossoverComicView.main(vista);
			break;
		}

	}

}
