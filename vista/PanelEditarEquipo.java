package vista;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelEditarEquipo extends JFrame
{
	private JLabel labNombre, labCantidad;
	
	private JTextField txtNombre, txtCantidad;
	
	private JButton btnAceptar, btnCancelar;
	
	public PanelEditarEquipo()
	{
		ImageIcon image = new ImageIcon("res/favicon.jpg");
		setIconImage(image.getImage());
		setTitle("Editar equipo");
		setSize(300, 100);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(4, 2));
		
		labNombre = new JLabel("Nombre: ");
		labCantidad = new JLabel("Cantidad: ");
		
		txtNombre = new JTextField();
		txtNombre.setToolTipText("Nombre del equipo: ");
		
		txtCantidad = new JTextField();
		txtCantidad.setToolTipText("Cantidad del equipo: ");
		
		JLabel vacio1 = new JLabel("");
		JLabel vacio2 = new JLabel("");
		
		btnAceptar = new JButton("Aceptar");
		btnCancelar = new JButton("Cancelar");
		
		add(labNombre);
		add(txtNombre);
		
		add(labCantidad);
		add(txtCantidad);
		
		add(vacio1);
		add(vacio2);
		
		add(btnAceptar);
		add(btnCancelar);
	}
	
	public JTextField darTxtNombre()
	{
		return txtNombre;
	}
	
	public JTextField darTxtCantidad()
	{
		return txtCantidad;
	}
	
	public JButton darBtnAceptar()
	{
		return btnAceptar;
	}
	
	public JButton darBtnCancelar()
	{
		return btnCancelar;
	}
}