package interfaz;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AdministrarAppView implements ActionListener {
	private JFrame frame;
	private JButton bSerie, bComic, bMedio;
	private JPanel panel;

	public AdministrarAppView() {
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Aplicacion Comic");
		frame.setBounds(100, 100, 200, 400);
		panel = new JPanel(new GridLayout(0, 1));

		frame.getContentPane().add(panel);

		bSerie = new JButton("Administrar Series");
		bSerie.setActionCommand("SERIE");
		bComic = new JButton("Administrar Comics");
		bComic.setActionCommand("COMIC");
		bMedio = new JButton("Administrar Medios de Publicacion");
		bMedio.setActionCommand("MEDIO");

		panel.add(bSerie);
		panel.add(bComic);
		panel.add(bMedio);

		addController(this);

	}

	public void addController(ActionListener ctr) {
		bSerie.addActionListener(ctr);
		bComic.addActionListener(ctr);
		bMedio.addActionListener(ctr);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministrarAppView window = new AdministrarAppView();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "SERIE":
			AdministrarSerieView.main(null);
			break;
		case "COMIC":
			AdministrarComicView.main(null);
			break;
		case "MEDIO":
			AdministrarMedioView.main(null);
			break;

		}

	}

}
