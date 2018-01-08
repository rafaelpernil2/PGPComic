package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import interfaz.AdministrarSerieView;
import interfaz.ModificarSerieView;
import orm.Mediodepublicacion;
import orm.Serie;

public class ModificarSerieCtr implements ActionListener {
	ModificarSerieView vistaMod;
	AdministrarSerieView vistaSerie;
	
	public ModificarSerieCtr(ModificarSerieView modificarSerieView, AdministrarSerieView vistaSerie) {
		vistaMod = modificarSerieView;
		this.vistaSerie = vistaSerie;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "CREAR":
			Serie s = new Serie(vistaSerie.serieSeleccionada().getIdserie(),vistaMod.getNombreSerie(),vistaMod.getAnotPub(),vistaMod.getGenero(),new HashSet<Mediodepublicacion>(vistaMod.getMedios()),vistaSerie.serieSeleccionada().getComicHasSeries());
			vistaMod.getSerieManager().attachDirty(s);
			vistaMod.cerrarVentana();
			vistaSerie.cargaSeries();
//			vistaSerie.volverASeleccionar(vistaSerie.getCurrentIndex());
			vistaMod.muestraMedio(s);
			break;
		case "CANCELAR":
			vistaMod.cerrarVentana();
			break;
		default:
			break;
		}
	}

}
