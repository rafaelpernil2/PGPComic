package controlador;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interfaz.AdministrarSerieView;
import interfaz.CrossoverComicView;
import orm.Serie;

public class CrossoverComicListaCtr implements ListSelectionListener {

	private CrossoverComicView vista;

	public CrossoverComicListaCtr(CrossoverComicView vista) {

		this.vista = vista;
	}

	public void valueChanged(ListSelectionEvent e) {
		JList<?> lista = (JList<?>) e.getSource();
		if (!lista.isSelectionEmpty()) {
			Object o = lista.getSelectedValue();

			if (o.getClass().equals(Serie.class)) {
				Serie s = (Serie) o;
			}
		}
	}

}
