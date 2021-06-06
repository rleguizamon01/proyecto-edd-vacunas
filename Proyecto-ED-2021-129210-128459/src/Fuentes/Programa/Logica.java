package Programa;

import Auxiliar.*;
import TDACola.*;
import TDAColaCP.*;
import TDALista.*;
import TDAMapeo.*;
import TDAPila.*;

public class Logica {
	
	PriorityQueue<Integer,Persona> cola;
	HashAbierto<Integer,Persona> mapeoEliminados;
	
	
	public Logica() {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		cola = new Heap<Integer,Persona>(comp);
		mapeoEliminados = new HashAbierto<Integer,Persona> ();
	}
	
	/**
	 * 
	 * @param nombre Nombre del paciente
	 * @param apellido Apellido del paciente
	 * @param dni DNI del paciente
	 * @param grupo Grupo de riesgo del paciente
	 * @return Persona creada
	 * @throws TDAColaCP.InvalidKeyException si grupo es nulo 
	 */
	public Persona inscribir(String nombre, String apellido, int dni, int grupo) throws TDAColaCP.InvalidKeyException {
		
		Persona per=new Persona(nombre,apellido,dni);	
		cola.insert(grupo,per);
		return per;
		
		
	}
	
	/**
	 * 
	 * @return Una cadena con el paciente mas riesgoso
	 * @throws EmptyPriorityQueueException si la cola esta vacia
	 */
	public String pacienteRiesgoso() throws EmptyPriorityQueueException {
		
		return cola.min().getValue().toString();
		
	}
	
	/**
	 * 
	 * @return Una cadena con todos los pacientes ordenados del mas riesgoso al menos riesgoso 
	 * @throws EmptyPriorityQueueException  si la cola esta vacia
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 */
	
	public String listaPacientes() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException {
		
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("lista():: La cola está vacía");
		
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		String listado = "";
		TDAColaCP.Entry<Integer,Persona> paciente;		
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			listado += paciente.getValue().toString();
			listado += "\n";
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		cola=colaAux;
		
		return listado;
	}
	
	/**
	 * 
	 * @return Una cadena con todos los pacientes del menos riesgoso al mas riesgoso
	 * @throws EmptyPriorityQueueException si la cola esta vacia
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 * @throws EmptyStackException si la pila esta vacia
	 */
	
	public String listaPacientesInverso() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, EmptyStackException {
		
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("listaInv():: La cola está vacía");
		
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		String listado = "";
		Stack<Persona> pila= new PilaConEnlaces<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			pila.push(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		while (!pila.isEmpty()) {
			listado += pila.pop().toString();
			listado += "\n";
		}
		
		cola=colaAux;
		
		return listado;
	}
	
	/**
	 * 
	 * @param dni DNI del paciente que se va a eliminar
	 * @return Persona eliminada de la cola
	 * @throws EmptyPriorityQueueException si la cola esta vacia
	 * @throws TDAColaCP.InvalidKeyException si uno de los pacientes de la cola tiene clave nula 
	 * @throws TDAMapeo.InvalidKeyException Si dni es nulo
	 * @throws NotFoundException Si no se encontro ninguna persona con ese DNI en la cola
	 */
	
	public Persona eliminar(int dni) throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, TDAMapeo.InvalidKeyException, NotFoundException{
		
		if(cola.isEmpty())
			throw new EmptyPriorityQueueException("eliminar(dni):: La cola está vacía");
		
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		PriorityQueue<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		TDAColaCP.Entry<Integer,Persona> paciente = null;
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
		
		return paciente.getValue();
	}
		/**
	 * 
	 * @param dni DNI de la persona que se va a eliminar
	 * @return Una cadena con la persona eliminada
	 * @throws TDAMapeo.InvalidKeyException Si dni es nulo
	 * @throws NotFoundException Si no hay ninguna persona que tenga ese dni en el mapeo
	 */
	
	public String pacienteHistorico(int dni) throws TDAMapeo.InvalidKeyException, NotFoundException{
		Persona per=mapeoEliminados.get(dni);
		if (per==null)
			throw new NotFoundException("La persona con el DNI ingresado no se ha eliminado");
		return per.toString();
	}

}
