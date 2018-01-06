package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import interfaz.AdministrarSerieView;
import interfaz.CrearSerieView;
import interfaz.ModificarSerieView;
import orm.Serie;

public class AdministrarSerieCtr implements ActionListener {
	private AdministrarSerieView vista;

	public AdministrarSerieCtr(AdministrarSerieView vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "CREAR":
			CrearSerieView.main(vista);
			break;
		case "MODIFICAR":
			ModificarSerieView.main(vista);
			break;
		case "ELIMINAR":
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta serie?",
					"Atención", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Serie s = vista.serieSeleccionada();
				vista.deseleccionar();
				if (s != null) {
					vista.getSerieManager().delete(s);
					vista.limpiaMedios();
					vista.cargaSeries();
				}
			}
			break;
		default:
			break;
		}

	}

}
