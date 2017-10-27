package eus.julenugalde.sandbox;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class SortedSetEmpleados implements SortedSet<Empleado> {
	private Empleado[] listaEmpleados;
	
	public SortedSetEmpleados (Empleado empleado) {
		listaEmpleados = new Empleado[1];
		listaEmpleados[0] = empleado;
	}
	
	public SortedSetEmpleados () {
		listaEmpleados = null;
	}
	
	@Override
	public boolean add(Empleado arg0) {
		if (listaEmpleados == null) {	//La lista estaba vacía. Se añade este elemento
			listaEmpleados = new Empleado[1];
			listaEmpleados[0] = arg0;
		}
		
		//La lista contenía algún elemento. Se añadirá si no está ya en la lista
		//Se busca la posición para insertar el nuevo elemento de forma ordenada
		int posicion = 0;
		while (posicion<listaEmpleados.length) {
			if (arg0.compareTo(listaEmpleados[posicion]) > 0)
				posicion++;
			else if (arg0.compareTo(listaEmpleados[posicion]) == 0)
				return false;	//El elemento ya existía
			else break;	//Hemos encontrado la posición para insertar
		}		
		//DEBUG System.out.println(posicion);
		//Se crea un nuevo array y se inserta el nuevo elemento en orden
		Empleado[] nuevaLista = new Empleado[listaEmpleados.length + 1];
		for (int i=0; i<posicion; i++)
			nuevaLista[i] = listaEmpleados[i];
		nuevaLista[posicion] = arg0;
		for (int i=posicion; i<(nuevaLista.length-1); i++)
			nuevaLista[i+1] = listaEmpleados[i];
		this.listaEmpleados = nuevaLista;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Empleado> arg0) {
		if (arg0==null) return false;
		@SuppressWarnings("unchecked")	//TODO Ver como evitar el unchecked cast
		Iterator<Empleado> iterador = (Iterator<Empleado>)arg0.iterator();
		while (iterador.hasNext()) {
			this.add(iterador.next());
		}
		return true;		
	}

	@Override
	public void clear() {
		listaEmpleados = null;
	}

	@Override
	public boolean contains(Object arg0) {
		Empleado aux = (Empleado)arg0;
		if (aux ==  null) return false;
		for (int i=0; i<listaEmpleados.length; i++) {
			if (aux.compareTo(listaEmpleados[i]) == 0)
				return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		@SuppressWarnings("unchecked")	//TODO Evitar el unchecked cast
		Collection<Empleado> elementos = (Collection<Empleado>) arg0;
		Iterator<Empleado> iterador = elementos.iterator();
		while (iterador.hasNext()) {
			if (!this.contains(iterador.next()))
				return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (listaEmpleados == null);
	}

	@Override
	public Iterator<Empleado> iterator() {
		Iterator<Empleado> iterador = new Iterator<Empleado>() {
			private int indice = 0;
			
			@Override
			public boolean hasNext() {
				return ((indice<listaEmpleados.length) && (listaEmpleados[indice] != null));
			}

			@Override
			public Empleado next() {
				return listaEmpleados[indice++];				
			}
			
		};
		return iterador;
	}

	@Override
	public boolean remove(Object arg0) {
		if (listaEmpleados == null) return false;
		Empleado aux = (Empleado)arg0;
		
		Empleado[] nuevaLista = new Empleado[listaEmpleados.length-1];
		boolean encontrado = false;
		int resultado;
		for (int i=0; i<listaEmpleados.length; i++) {
			resultado = aux.compareTo(listaEmpleados[i]);
			if (resultado == 0)  {	//Hemos encontrado el elemento
				encontrado = true;				
			}
			else {	//El elemento no esta en la posicion actual
				if (encontrado)	//Hemos encontrado antes el elemento
					nuevaLista[i-1] = listaEmpleados[i];
				else {
					if (i<nuevaLista.length)
						nuevaLista[i] = listaEmpleados[i];
				}
			}			
		}
		if (encontrado) {
			this.listaEmpleados = nuevaLista;
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		@SuppressWarnings("unchecked")	//TODO Evitar el unchecked cast
		Collection<Empleado> coleccion = (Collection<Empleado>)arg0;
		if (coleccion == null)
			return false;
		boolean resultado = false;
		Iterator<Empleado> iterador = this.iterator();
		while (iterador.hasNext()) {
			if (this.remove(iterador.next()))
				resultado = true;
		}
		return resultado;		
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {		
		return listaEmpleados.length;
	}

	@Override
	public Object[] toArray() {
		return listaEmpleados;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<? super Empleado> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Empleado> headSet(Empleado arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Empleado> subSet(Empleado arg0, Empleado arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Empleado> tailSet(Empleado arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String listarEmpleados() {
		StringBuilder sb = new StringBuilder(
				"NOMBRE\tFECHA NACIM.\tSALARIO\tPUESTO\t\tSALARIO/MEDIA PUESTO\n" + 
				"-----------------------------------------------------------\n");
		Iterator<Empleado> iterador = this.iterator();
		Empleado aux;
		while (iterador.hasNext()) {
			aux = iterador.next();
			sb.append(aux.getNombre() + "\t" + 
					new SimpleDateFormat("yyyy-MM-dd").format(aux.getFechaNacimiento()) + "\t" + 
					aux.getSalario() + "€\t" + aux.getPuesto().toString() + "\t" + 
					(aux.getSalario()- aux.getPuesto().sueldoMedio())+"€\n");
		}			
		return sb.toString();
	}

}
