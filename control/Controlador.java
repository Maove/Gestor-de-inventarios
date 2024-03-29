package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Consultor;
import modelo.Equipo;
import vista.VistaConsultor;

public class Controlador
{
	private static Consultor modelo;
	
	private static VistaConsultor vista;
	
	// Librería de MySQL
    public static String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public static String database = "name_db";

    // Host
    public static String hostname = "ip_db_server";

    // Puerto
    public static String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public static String username = "name_user_db";

    // Clave de usuario
    public static String password = "pw_user_db";
    
    public static Connection conexDB;
	
	public static void main(String[] args)
	{
		try
		{
			conexDB = inicializarConexionDB();
			
			if(conexDB != null)
			{
				System.out.println("Base de datos lista para funcionar");
				
				inicializarModelo();
				cargarModelo();
				
				inicializarVista();
				
				asignarEventos();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No existe conexión con la base de datos", "Error - MySQL", JOptionPane.ERROR_MESSAGE);;
				System.out.println("No existe conexión a la base de datos");
				System.exit(0);
			}
		}
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al inicializar la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
			
	}
	
	public static void inicializarModelo()
	{
		modelo = new Consultor();		
	}
	
	public static void inicializarVista()
	{
		ArrayList<Equipo> listado = modelo.darEquipos();
		vista = new VistaConsultor();
		vista.darPanelLista().refrescarLista(listado);
		Equipo equipo = ( Equipo )vista.darPanelLista().darLista().getSelectedValue( );
		vista.darPanelDatos().darTxtNombre().setText(equipo.darNombre());
		vista.darPanelDatos().darTxtCantidad().setText(""+equipo.darCantidad());		
		vista.setVisible(true);
		
		vista.inicializarPanelAgregarEquipo();
		vista.inicializarPanelEditarEquipo();
	}
	
	public static void asignarEventos()
	{
		vista.darPanelLista().darLista().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				Equipo equipo = ( Equipo )vista.darPanelLista().darLista().getSelectedValue( );
				
				if(equipo != null )
		        {
					vista.darPanelDatos().darTxtNombre().setText(equipo.darNombre());
					vista.darPanelDatos().darTxtCantidad().setText(""+equipo.darCantidad());
		        }
			}
		});
		
		vista.darPanelBotones().darBtnAgregar().addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				vista.darPanelAgregarEquipo().setVisible(true);
			}
		});
		
///////////////////////		BOTONES EN LA VENTANA AGREGAR EQUIPO      /////////////////////////////////
		
		vista.darPanelAgregarEquipo().darBtnAceptar().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nombreEquipo = vista.darPanelAgregarEquipo().darTxtNombre().getText();
				String cantidadEquipo = vista.darPanelAgregarEquipo().darTxtCantidad().getText();
				
				if(nombreEquipo.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Nombre de equipo no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(cantidadEquipo.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Cantidad del equipo no puede ser nula", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(isNumeric(cantidadEquipo) == false)
				{
					JOptionPane.showMessageDialog(null, "La cantidad no es dato numérico", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(modelo.buscarEquipoPorNombre(nombreEquipo) == null)
					{
						try
						{						
							Statement statem = conexDB.createStatement();
							String sql = "INSERT INTO equipos (nombre, cantidad) VALUES('" + nombreEquipo + "', '" + cantidadEquipo + "');";
							System.out.println(sql);
							statem.execute(sql);
							
							modelo.agregarEquipo(nombreEquipo, Integer.parseInt(cantidadEquipo));
							vista.darPanelAgregarEquipo().dispose();
							vista.darPanelLista().refrescarLista(modelo.darEquipos());
							
							vista.darPanelAgregarEquipo().darTxtNombre().setText("");
							vista.darPanelAgregarEquipo().darTxtCantidad().setText("");
						}
						catch(SQLException ex)
						{
							ex.printStackTrace();
						}	
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Este equipo ya existe en el inventario. No se puede agregar", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		vista.darPanelAgregarEquipo().darBtnCancelar().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				vista.darPanelAgregarEquipo().darTxtNombre().setText("");
				vista.darPanelAgregarEquipo().darTxtCantidad().setText("");
				
				vista.darPanelAgregarEquipo().dispose();
			}
		});
		
////////////////////////////////////////////////////////////////////////////////////////////////////
		
		vista.darPanelBotones().darBtnBuscar().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nom = JOptionPane.showInputDialog(null, "Ingresa el nombre del equipo a buscar:", "Buscar equipo", JOptionPane.INFORMATION_MESSAGE);
				
				if(nom != null)
				{
					Equipo buscado = modelo.buscarEquipoPorNombre(nom);
					if(buscado != null)
					{
						JOptionPane.showMessageDialog(null, buscado.darNombre() + " en inventario: " + buscado.darCantidad(), "Buscar equipo", JOptionPane.INFORMATION_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El equipo buscado no existe en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		vista.darPanelBotones().darBtnEditar().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Equipo seleccionado = (Equipo) vista.darPanelLista().darLista().getSelectedValue();
				vista.darPanelEditarEquipo().darTxtNombre().setText(seleccionado.darNombre());
				vista.darPanelEditarEquipo().darTxtCantidad().setText(seleccionado.darCantidad()+"");				
				vista.darPanelEditarEquipo().setVisible(true);
			}
		});
		
///////////////////////		BOTONES EN LA VENTANA EDITAR EQUIPO      /////////////////////////////////
		
		vista.darPanelEditarEquipo().darBtnAceptar().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String nombreEquipo = vista.darPanelEditarEquipo().darTxtNombre().getText();
				String cantidadEquipo = vista.darPanelEditarEquipo().darTxtCantidad().getText();
				Equipo seleccionado = (Equipo) vista.darPanelLista().darLista().getSelectedValue();
				
				try
				{
					Statement statem = conexDB.createStatement();
					String sql = "UPDATE equipos SET nombre = '"+nombreEquipo+"', cantidad = '"+cantidadEquipo+"' WHERE	nombre='"+seleccionado.darNombre()+"';";
					System.out.println(sql);
					statem.execute(sql);
					
					modelo.buscarEquipoPorNombreYCantidad(seleccionado.darNombre(), seleccionado.darCantidad()).asignarNombre(nombreEquipo);
					modelo.buscarEquipoPorNombreYCantidad(seleccionado.darNombre(), seleccionado.darCantidad()).asignarCantidad(Integer.parseInt(cantidadEquipo));
					
					vista.darPanelEditarEquipo().dispose();
					vista.darPanelLista().refrescarLista(modelo.darEquipos());
					
					vista.darPanelEditarEquipo().darTxtNombre().setText("");
					vista.darPanelEditarEquipo().darTxtCantidad().setText("");
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
			}
		});
		
		vista.darPanelEditarEquipo().darBtnCancelar().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				vista.darPanelEditarEquipo().darTxtNombre().setText("");
				vista.darPanelEditarEquipo().darTxtCantidad().setText("");
				
				vista.darPanelEditarEquipo().dispose();
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////
		
		vista.darPanelBotones().darBtnEliminar().addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int respuesta = JOptionPane.showConfirmDialog(null, "Estás seguro de querer eliminar el equipo seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(respuesta == JOptionPane.YES_OPTION)
				{
					try
					{					
						Equipo seleccionado = (Equipo) vista.darPanelLista().darLista().getSelectedValue();
						String nombreEquipo = seleccionado.darNombre();
						int cantidadEquipo = seleccionado.darCantidad();
						
						Statement statem = conexDB.createStatement();
						String sql = "DELETE FROM equipos WHERE nombre='"+nombreEquipo+"' AND cantidad='" + cantidadEquipo + "';";
						System.out.println(sql);
						statem.execute(sql);
						
						modelo.eliminarEquipo(seleccionado.darNombre(), seleccionado.darCantidad());
						vista.darPanelLista().refrescarLista(modelo.darEquipos());
					}
					catch(SQLException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});
	}
	
	public static Connection inicializarConexionDB() throws ClassNotFoundException, SQLException
	{
		Connection conn = null;

		Class.forName(driver);
		conn = DriverManager.getConnection(url, username, password);
		System.out.println("La conexión con la base de datos ha sido creada");            

        return conn;
	}
	
	
	public static void cargarModelo()
	{
		try
		{
			Statement statem = conexDB.createStatement();
			String sql = "SELECT * FROM equipos";
			ResultSet resultado = statem.executeQuery(sql);
			
			while(resultado.next())
			{
				String nombre = resultado.getString("nombre");
				int cantidad = resultado.getInt("cantidad");
				
				modelo.agregarEquipo(nombre, cantidad);
			}			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void cerrarConexionDB(Connection con)
	{
		try
		{
			con.close();
			
			System.out.println("La conexión con la base de datos ha sido cerrada!");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean isNumeric(String num)
	{
		try
		{
			Integer.parseInt(num);
			return true;
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
