package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import interfaz.AdministrarMedioView;
import interfaz.AdministrarSerieView;
import interfaz.CrearMedioView;
import interfaz.CrearSerieView;
import interfaz.ModificarMedioView;
//import interfaz.ModificarMedioViewOld;
import interfaz.ModificarSerieView;
import orm.Mediodepublicacion;
import orm.Serie;

public class AdministrarMedioCtr implements ActionListener {
	private AdministrarMedioView vista;

	public AdministrarMedioCtr(AdministrarMedioView vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "CREAR":
			CrearMedioView.main(vista);
			break;
		case "MODIFICAR":
			ModificarMedioView.main(vista);
			break;
		case "ELIMINAR":
			int dialogResult = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar esta serie?",
					"Atencion", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				Mediodepublicacion m = vista.medioSeleccionado();
				vista.deseleccionar();
				if (m != null) {
					vista.getMedioManager().delete(m);
					vista.limpiaMedios();
					vista.cargaMedios();
				}
			}
			break;
		default:
			break;
		}

	}

}
