package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import interfaz.AdministrarMedioView;
import interfaz.AdministrarSerieView;
import interfaz.CrearMedioView;
import interfaz.CrearSerieView;
import orm.ComicHasSerie;
import orm.Mediodepublicacion;
import orm.Serie;

public class CrearMedioCtr implements ActionListener {
	AdministrarMedioView vistaMedio;
	CrearMedioView vista;
	
	public CrearMedioCtr(CrearMedioView vista, AdministrarMedioView vistaMedio) {
		this.vista = vista;
		this.vistaMedio = vistaMedio;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "CREAR":
			Mediodepublicacion s = new Mediodepublicacion(vista.getNombreMedio());
			vista.getMedioManager().attachClean(s);
			vista.cerrarVentana();
			vistaMedio.cargaMedios();
			break;
		case "CANCELAR":
			vista.cerrarVentana();
			break;
		default:
			break;
		}

	}

}
