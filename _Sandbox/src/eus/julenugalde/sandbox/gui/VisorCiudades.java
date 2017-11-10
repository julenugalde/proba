package eus.julenugalde.sandbox.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.*;

/** Aplicación que utiliza los datos de la base de datos world para mostrar en una JTable
 * los datos de ciudades del mundo
 */
public class VisorCiudades extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int MENSAJE = 1;
	private static final int ERROR = 2;
	
	private JComboBox<String> jcbPaises = new JComboBox<String>();
	private JScrollPane jspTabla;
	private JTable jtCiudades;
	private JLabel jlEstado;
	
	private DataSource dataSource;
	private ArrayList<Pais> listaPaises;
	private ArrayList<Ciudad> listaCiudades;
	private ModeloTablaCiudades modeloTablaCiudades;
	
	public static void main (String[] args) {
		VisorCiudades ventana = new VisorCiudades("Visor ciudades");
		ventana.setVisible(true);
	}
	
	public VisorCiudades (String titulo) {
		inicializarVentana(titulo);
		iniciarConexion();
		cargarDatosPaises();
		crearComponentes();
		anadirElementosVentana();
		asignarListeners();
		
		String codigo = ((String)jcbPaises.getSelectedItem()).substring(0, 3);
		setEstado("Aplicacion iniciada. Seleccionado pais con codigo " + codigo, MENSAJE);
	}

	private void cerrarConexion() {
		try {
			Connection connection = dataSource.getConnection();
			if ((connection != null) | (!connection.isClosed())) {
				connection.close();
			}
		} catch (SQLException e) {
			setEstado("Error al cerrar la conexión con el servidor MySQL", ERROR);
		}
		
	}

	private void setEstado(String estado, int tipoMensaje) {
		switch (tipoMensaje) {
		case ERROR:
			jlEstado.setForeground(Color.RED);
			break;
		case MENSAJE:
			jlEstado.setForeground(Color.BLACK);
			break;
		}
		jlEstado.setText(estado);
	}

	private void cargarDatosPaises() {
		try {
			//Petición con Statement
			Connection connection = dataSource.getConnection();

			Statement orden = connection.createStatement();
			String sql = "SELECT code, name FROM country ORDER BY Population DESC";
			ResultSet rs = orden.executeQuery(sql);
			
			listaPaises = new ArrayList<Pais> ();
			while (rs.next()) {
				listaPaises.add(new Pais(rs.getString("Code"), rs.getString("Name")));
			}
			
		} catch (SQLException sqlex) {	
			System.err.println("Error SQL: " + sqlex.getLocalizedMessage());			
		} finally {
			this.dispose();
		}
	}

	private void iniciarConexion() {
		String user = "julen";
		
		try {
			//Leer contraseña del teclado
			System.out.print("Password: ");
			BufferedReader br = new BufferedReader(new InputStreamReader (System.in));
			String password = br.readLine();
		
			/*Class.forName("com.mysql.jdbc.Driver");    //Registra el driver
			String url = "jdbc:mysql://localhost:3306/world";
			connection = DriverManager.getConnection(
				url+"?verifyServerCertificate=false&useSSL=true", 	//usa SSL sin verificación	
				user, password);									//del certificado
			*/
			dataSource = new DataSourceWorld();
			Connection connection = dataSource.getConnection(user, password);
			if (connection == null) {
				System.err.println("Error en la conexion");
				this.dispose();
			}
		} catch (IOException ioex) {
			System.err.println("Error I/O: " + ioex.getLocalizedMessage());			
		} catch (SQLException sqlex) {	
			System.err.println("Error SQL: " + sqlex.getLocalizedMessage());			
		} /*catch (ClassNotFoundException cnfex) {	
			System.err.println("Error SQL: " + cnfex.getLocalizedMessage());			
		}*/ finally {
			this.dispose();
		}
	}

	private void asignarListeners() {
		jcbPaises.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cargarDatosCiudades();
				modeloTablaCiudades.setData(listaCiudades);
				//modeloTablaCiudades.printDebugData();
				modeloTablaCiudades.fireTableDataChanged();
				jtCiudades.invalidate();
			}
		});
	}

	private void anadirElementosVentana() {
		//Panel superior en el que estará una etiqueta y el JComboBox
		JPanel panelPaises = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelPaises.add(new JLabel("Seleccionar pais"));
		panelPaises.add(jcbPaises);
				
		//Estructura con BorderLayout
		this.setLayout(new BorderLayout());
		this.add(panelPaises, BorderLayout.NORTH);
		this.add(jspTabla, BorderLayout.CENTER);
		this.add(jlEstado, BorderLayout.SOUTH);		
	}

	private void crearComponentes() {
		//JComboBox con listado de paises
		String[] paises = new String[listaPaises.size()];		
		int i=0;
		for (Pais pais : listaPaises) {
			paises[i++] = pais.getCode() + " - " + pais.getName();
		}
		jcbPaises = new JComboBox<String>();
		for (Pais pais : listaPaises) {
			jcbPaises.addItem(pais.getCode() + " - " + pais.getName());
		}
		jcbPaises.setMaximumRowCount(30);
		
		//Se cargan los datos de las ciudades para el primer pais en la lista
		jcbPaises.setSelectedIndex(0);
		cargarDatosCiudades();
				
		//JScrollPane que contendrá la tabla
		modeloTablaCiudades = new ModeloTablaCiudades(listaCiudades);
		jtCiudades = new JTable(modeloTablaCiudades);
		//jtCiudades.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		//jtCiudades.setFillsViewportHeight(true);
	    jtCiudades.setPreferredScrollableViewportSize(new Dimension(500, 70));
		//jspTabla = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		//		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspTabla = new JScrollPane(jtCiudades);
		
	    jlEstado = new JLabel("");
	}

	private void cargarDatosCiudades() {
		//Los primeros tres caracteres son el codigo de pais
		String codigo = ((String)jcbPaises.getSelectedItem()).substring(0, 3);
		if (jlEstado != null) {
			setEstado("Seleccionado pais con codigo " + codigo, MENSAJE);
		}
		
		try {
			Connection connection = dataSource.getConnection();

			if ((connection == null) | connection.isClosed()) {
				iniciarConexion();
			}
			//Petición con Statement
			Statement orden = connection.createStatement();
			String sql = "SELECT country.Name AS CountryName, city.Name AS CityName, " + 
					"city.District, city.Population " +
					"FROM city " + 
					"JOIN country ON city.CountryCode=country.Code " + 
					"WHERE country.Code='" + codigo + "'";
			ResultSet rs = orden.executeQuery(sql);
	
			listaCiudades = new ArrayList<Ciudad> ();
			while (rs.next()) {
				listaCiudades.add(new Ciudad(
						rs.getString("CountryName"), 
						rs.getString("CityName"),
						rs.getString("District"), 
						rs.getInt("Population")));
			}
			
		} catch (SQLException sqlex) {	
			System.err.println("Error SQL: " + sqlex.getLocalizedMessage());	
			this.dispose();
		} 
	}

	private void inicializarVentana(String titulo) {
		this.setTitle(titulo);
		this.setSize(900, 700);
		this.setLocation(350, 10);
		this.setOpacity(1);
		this.setEnabled(true);
		this.setResizable(true);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				cerrarConexion();				
				dispose();
			}
		});
		
		//Establecer el look&feel de la ventana
		UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
		int i = 3;	//Windows
		try {
			UIManager.setLookAndFeel(laf[i].getClassName());
		} catch (Exception e) {
			setEstado("Error al establecer el look&feel", ERROR);
		}	
	}
}
