package vista;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDatos extends JPanel
{
	private JLabel labNombre, labCantidad;
	
	private JTextField txtNombre, txtCantidad;
	
	public PanelDatos()
	{
		setLayout(new GridLayout(2,2));
		
		labNombre = new JLabel("Nombre: ");
		labCantidad = new JLabel("Cantidad: ");
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		
		txtCantidad = new JTextField();
		txtCantidad.setEditable(false);
		
		add(labNombre);
		add(txtNombre);
		add(labCantidad);
		add(txtCantidad);
	}
	
	public JTextField darTxtNombre()
	{
		return txtNombre;
	}
	
	public JTextField darTxtCantidad()
	{
		return txtCantidad;
	}
	
	public void asignarTxtNombre(String nom)
	{
		txtNombre.setText(nom);
	}
	
	public void asignarTxtCantidad(String cant)
	{
		txtCantidad.setText(cant);
	}
}