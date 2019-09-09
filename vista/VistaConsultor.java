package vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VistaConsultor extends JFrame
{
	private PanelDatos panelDatos;
	
	private PanelLista panelLista;
	
	private PanelBotones panelBotones;
	
	private PanelAgregarEquipo panelAgregarEquipo;
	
	private PanelEditarEquipo panelEditarEquipo;
	
	public VistaConsultor()
	{
		ImageIcon image = new ImageIcon("res/favicon.jpg");
		setIconImage(image.getImage());
		setTitle("EventBox - Gestor de inventarios");
		setSize(500, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		panelDatos = new PanelDatos();
		add(panelDatos, BorderLayout.NORTH);
		
		panelLista = new PanelLista();
		add(panelLista, BorderLayout.CENTER);
		
		panelBotones = new PanelBotones();
		add(panelBotones, BorderLayout.SOUTH);
	}
	
	public PanelDatos darPanelDatos()
	{
		return panelDatos;
	}
	
	public PanelLista darPanelLista()
	{
		return panelLista;
	}
	
	public PanelBotones darPanelBotones()
	{
		return panelBotones;
	}
	
	public PanelAgregarEquipo darPanelAgregarEquipo()
	{
		return panelAgregarEquipo;
	}
	
	public void inicializarPanelAgregarEquipo()
	{
		panelAgregarEquipo = new PanelAgregarEquipo();
	}
	
	public PanelEditarEquipo darPanelEditarEquipo()
	{
		return panelEditarEquipo;
	}
	
	public void inicializarPanelEditarEquipo()
	{
		panelEditarEquipo = new PanelEditarEquipo();
	}
}