package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modelo.Equipo;

public class PanelLista extends JPanel //implements ListSelectionListener
{
	private JList list;
	
	public PanelLista()
	{
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Equipos"));
		
		list = new JList(); //data has type Object[]
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//		list.addListSelectionListener(this);
		
//		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane();
		listScroller.setViewportView(list);
		listScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		listScroller.setPreferredSize(new Dimension(250, 80));
		
//		list.setListData(listado);
		
		add(listScroller, BorderLayout.CENTER);
	}
	
	public JList darLista()
	{
		return list;
	}
	
	public void refrescarLista( ArrayList nuevaLista )
	    {
	        list.setListData( nuevaLista.toArray( ) );
	        list.setSelectedIndex( 0 );
	    }
	
	public void seleccionar( int seleccionado )
    {
        list.setSelectedIndex( seleccionado );
        list.ensureIndexIsVisible( seleccionado );
    }

//	@Override
//	public void valueChanged(ListSelectionEvent e)
//	{
//		if( list.getSelectedValue( ) != null )
//        {
//            Equipo equipo = ( Equipo )list.getSelectedValue( );
//        }
//	}
}