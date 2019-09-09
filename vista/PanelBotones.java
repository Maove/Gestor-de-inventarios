package vista;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel
{
	private JButton btnAgregar, btnBuscar, btnEditar, btnEliminar;
	
	public PanelBotones()
	{
		setLayout(new GridLayout(1, 3));
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setToolTipText("Agrega un equipo al inventario");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setToolTipText("Busca un equipo en el inventario");
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setToolTipText("Elimina un equipo del inventario");
		
		btnEditar = new JButton("Editar");
		btnEditar.setToolTipText("Edita el equipo seleccionado");
		
		add(btnAgregar);
		add(btnBuscar);
		add(btnEditar);
		add(btnEliminar);
	}
	
	public JButton darBtnAgregar()
	{
		return btnAgregar;
	}
	
	public JButton darBtnBuscar()
	{
		return btnBuscar;
	}
	
	public JButton darBtnEditar()
	{
		return btnEditar;
	}
	
	public JButton darBtnEliminar()
	{
		return btnEliminar;
	}
}