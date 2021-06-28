package Programa;

import Auxiliar.*;
import TDAColaCP.*;
import TDAColaCP.Entry;
import TDALista.ListaDobleSinCentinelas;
import TDALista.PositionList;
import TDAMapeo.*;
import TDAPila.*;

public class Logica {
	
	PriorityQueue<Integer,Persona> cola;
	HashAbierto<Integer,Persona> mapeoEliminados;
	
	
	public Logica() {
		ComparadorInverso<Integer> comp = new ComparadorInverso<Integer>();
		cola = new Heap<Integer,Persona>(comp);
		mapeoEliminados = new HashAbierto<Integer,Persona> ();
	}
	
	/**
	 * 
	 * @param nombre Nombre del paciente
	 * @param apellido Apellido del paciente
	 * @param dni DNI del paciente
	 * @param grupo Grupo de riesgo del paciente
	 * @throws TDAColaCP.InvalidKeyException si grupo es nulo 
	 */
	public void inscribir(String nombre, String apellido, int dni, int grupo) throws TDAColaCP.InvalidKeyException {
		Persona per = new Persona(nombre,apellido,dni);	
		cola.insert(grupo,per);
	}
	
	/**
	 * 
	 * @return Una cadena con el paciente mas riesgoso
	 * @throws EmptyPriorityQueueException si la cola esta vacia
	 */
	public Entry<Integer, Persona> pacienteRiesgoso() throws EmptyPriorityQueueException {
		return cola.min();
	}
	
	/**
	 * Agrega a una lista a todos los pacientes registrados de manera ordenada (más riesgoso a menos riesgoso)
	 * @return Una lista con todos los pacientes ordenados del mas riesgoso al menos riesgoso 
	 * @throws EmptyPriorityQueueException  si la cola está vacía
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 */
	public PositionList<Persona> listaPacientes() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException {
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("listaPacientes():: La cola está vacía");
		
		ComparadorInverso<Integer> comp = new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		PositionList<Persona> listado = new ListaDobleSinCentinelas<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;		
		
		while(!cola.isEmpty()) {
			paciente = cola.removeMin();
			listado.addLast(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		cola=colaAux;
		
		return listado;
	}
	
	/**
	 * Agrega a una lista a todos los pacientes registrados de manera ordenada (menos riesgoso a más riesgoso)
	 * @return Una lista con todos los pacientes del menos riesgoso al mas riesgoso
	 * @throws EmptyPriorityQueueException si la cola está vacia
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 * @throws EmptyStackException si la pila está vacia
	 */
	public PositionList<Persona> listaPacientesInverso() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, EmptyStackException {
		
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("listaPacientesInverso():: La cola está vacía");
		
		ComparadorInverso<Integer> comp = new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		PositionList<Persona> listado = new ListaDobleSinCentinelas<Persona>();
		Stack<Persona> pila = new PilaConEnlaces<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		
		while (!cola.isEmpty()) {
			paciente = cola.removeMin();
			pila.push(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		while (!pila.isEmpty()) 
			listado.addLast(pila.pop());
		
		
		cola = colaAux;
		
		return listado;
	}
	
	/**
	 * 
	 * @param dni DNI del paciente que se va a eliminar
	 * @throws EmptyPriorityQueueException si la cola esta vacia
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 * @throws TDAMapeo.InvalidKeyException Si dni es nulo
	 * @throws NotFoundException Si no se encontró ninguna persona con ese DNI en la cola
	 */
	
	public void eliminar(int dni) throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, TDAMapeo.InvalidKeyException, NotFoundException{
		
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("eliminar(dni):: La cola está vacía");
		
		ComparadorInverso<Integer> comp = new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		TDAColaCP.Entry<Integer,Persona> paciente;
		boolean encontre=false;
		

		while (!cola.isEmpty() && !encontre) {
			paciente=cola.removeMin();
			if(paciente.getValue().getDNI()==dni) {
				encontre=true;
				mapeoEliminados.put(dni,paciente.getValue());
			}else {
				colaAux.insert(paciente.getKey(),paciente.getValue()); 
			}
		}
		
		while (!colaAux.isEmpty()) {
		

			paciente=colaAux.removeMin();
			cola.insert(paciente.getKey(),paciente.getValue());
		}
		
		if(!encontre)
			throw new NotFoundException("La persona con el DNI ingresado no existe");
	}
	
	/**
	 * 
	 * @param dni DNI de la persona que se va a eliminar
	 * @return Una cadena con la persona eliminada
	 * @throws TDAMapeo.InvalidKeyException Si dni es nulo
	 * @throws NotFoundException Si no hay ninguna persona que tenga ese dni en el mapeo
	 */
	public Persona pacienteHistorico(int dni) throws TDAMapeo.InvalidKeyException, NotFoundException{
		Persona per = mapeoEliminados.get(dni);
		if (per == null)
			throw new NotFoundException("La persona con el DNI ingresado no se ha eliminado");
		return per;
	}

}
