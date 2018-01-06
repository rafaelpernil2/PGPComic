package controlador;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interfaz.AdministrarSerieView;
import orm.Serie;

public class AdministrarSerieListaCtr implements ListSelectionListener {

	private AdministrarSerieView vista;

	public AdministrarSerieListaCtr(AdministrarSerieView vista) {

		this.vista = vista;
	}

	public void valueChanged(ListSelectionEvent e) {
		JList<?> lista = (JList<?>) e.getSource();
		if (!lista.isSelectionEmpty()) {
			System.out.println("Se ha detectado un cambio" + vista.serieSeleccionada().toString());
			Object o = lista.getSelectedValue();

			if (o.getClass().equals(Serie.class)) {
				Serie s = (Serie) o;
				vista.muestraMedio(s);
			}
		}
	}

}
