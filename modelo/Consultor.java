package modelo;

import java.util.ArrayList;

public class Consultor
{
	private ArrayList<Equipo> equipos;
	
	public Consultor()
	{
		equipos = new ArrayList<Equipo>();
	}
	
	public ArrayList<Equipo> darEquipos()
	{
		return equipos;
	}
	
	public void agregarEquipo(String nom, int cant)
	{
		Equipo nuevoEquipo = new Equipo(nom, cant);
		
		equipos.add(nuevoEquipo);
	}
	
	public void eliminarEquipo(String nom, int cant)
	{		
		darEquipos().remove(buscarEquipoPorNombreYCantidad(nom, cant));
	}
	
	public Equipo buscarEquipoPorNombre(String nom)
	{
		Equipo buscado = null;
		
		for(int i = 0; i<darEquipos().size(); i++)
		{
			Equipo equipoActual = (Equipo) darEquipos().get(i);
			String nombreActual = equipoActual.darNombre();
			
			if(nombreActual.equalsIgnoreCase(nom))
			{
				buscado = equipoActual;
			}
		}
		
		return buscado;
	}
	
	public Equipo buscarEquipoPorNombreYCantidad(String nom, int cant)
	{
		Equipo buscado = null;
		
		for(int i = 0; i<darEquipos().size(); i++)
		{
			Equipo equipoActual = darEquipos().get(i);
			String nombreBuscado = equipoActual.darNombre();
			int cantidadBuscado = equipoActual.darCantidad();
			
			if(nombreBuscado.equals(nom) && cantidadBuscado == cant)
			{
				buscado = equipoActual;
			}
		}
		
		return buscado;
	}
}