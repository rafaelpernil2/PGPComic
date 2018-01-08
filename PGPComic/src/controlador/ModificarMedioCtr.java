package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import interfaz.AdministrarMedioView;
import interfaz.AdministrarSerieView;
import interfaz.ModificarMedioView;
//import interfaz.ModificarMedioViewOld;
import interfaz.ModificarSerieView;
import orm.Mediodepublicacion;
import orm.Serie;

public class ModificarMedioCtr implements ActionListener {
	ModificarMedioView vistaMod;
	AdministrarMedioView vistaMedio;
	
	public ModificarMedioCtr(ModificarMedioView modificarMedioView, AdministrarMedioView vistaMedio) {
		vistaMod = modificarMedioView;
		this.vistaMedio = vistaMedio;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "MODIFICAR":
			Mediodepublicacion m = vistaMedio.medioSeleccionado();
			vistaMod.getMedioManager().delete(m);
			vistaMod.getMedioManager().attachClean(new Mediodepublicacion(vistaMod.getNombreMedio()));
			vistaMod.cerrarVentana();
			vistaMedio.cargaMedios();
			vistaMedio.volverASeleccionar(vistaMedio.getCurrentIndex());
			vistaMod.muestraMedio(m);
			break;
		case "CANCELAR":
			vistaMod.cerrarVentana();
			break;
		default:
			break;
		}
	}

}
