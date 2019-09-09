package modelo;

public class Equipo
{
	private String nombre;
	
	private int cantidad;
	
	public Equipo(String nom, int cant)
	{
		nombre = nom;
		
		cantidad = cant;
	}
	
	public String darNombre()
	{
		return nombre;
	}
	
	public int darCantidad()
	{
		return cantidad;
	}
	
	public void asignarNombre(String nom)
	{
		nombre = nom;
	}
	
	public void asignarCantidad(int cant)
	{
		cantidad = cant;
	}
	
	public String toString()
	{
		return nombre;
		
	}
}