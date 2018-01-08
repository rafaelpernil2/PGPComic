package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import interfaz.AdministrarSerieView;
import interfaz.CrearSerieView;
import orm.ComicHasSerie;
import orm.Mediodepublicacion;
import orm.Serie;

public class CrearSerieCtr implements ActionListener {
	AdministrarSerieView vistaSerie;
	CrearSerieView vista;
	
	public CrearSerieCtr(CrearSerieView vista, AdministrarSerieView vistaSerie) {
		this.vista = vista;
		this.vistaSerie = vistaSerie;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "CREAR":
			int id = vista.getSerieManager().getMaxID() + 1;
			Serie s = new Serie(id,vista.getNombreSerie(),vista.getAnotPub(),vista.getGenero(),new HashSet<Mediodepublicacion>(vista.getMedios()),new HashSet<ComicHasSerie>());
			vista.getSerieManager().attachClean(s);
			vista.cerrarVentana();
			vistaSerie.cargaSeries();
//			vistaSerie.volverASeleccionar(id);
			vistaSerie.muestraMedio(s);
			break;
		case "CANCELAR":
			vista.cerrarVentana();
			break;
		default:
			break;
		}

	}

}
