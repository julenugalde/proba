package eus.julenugalde.sandbox.gui;

import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/** Modelo para la JTable en la que se muestra la información de ciudades */
public class ModeloTablaCiudades extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private static final String[] nombresColumnas = 
		{"Nombre", "País", "Distrito", "Población"};
	
	private Object[][] data;
	
	public ModeloTablaCiudades (ArrayList<Ciudad> listaCiudades) {
		setData(listaCiudades);
	}
	
	public void setData(ArrayList<Ciudad> listaCiudades) {
		data = new Object[listaCiudades.size()][nombresColumnas.length];
		int i = 0;
		for (Ciudad ciudad : listaCiudades) {
			data[i][0] = new String(ciudad.getName());
			data[i][1] = new String(ciudad.getCountry());
			data[i][2] = new String(ciudad.getDistrict());
			data[i++][3] = new Integer(ciudad.getPopulation());
		}
	}

	@Override
	public void addTableModelListener(TableModelListener arg0) {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int arg0) {
		return getValueAt(0, arg0).getClass();		
	}

	@Override
	public int getColumnCount() {
		return nombresColumnas.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return nombresColumnas[arg0];
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {

	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		data[arg1][arg2] = arg0;
		fireTableCellUpdated(arg1, arg2);
	}

    public void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
          System.out.print("    row " + i + ":");
          for (int j = 0; j < numCols; j++) {
            System.out.print("  " + data[i][j]);
          }
          System.out.println();
        }
        System.out.println("--------------------------");
      }
    
}
