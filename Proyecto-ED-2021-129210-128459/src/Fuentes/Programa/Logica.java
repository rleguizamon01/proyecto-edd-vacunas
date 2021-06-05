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
	
	public Persona inscribir(String nombre, String apellido, int dni, int grupo) throws TDAColaCP.InvalidKeyException {
		
		Persona per=new Persona(nombre,apellido,dni);	
		cola.insert(grupo,per);
		
		return per;
		
	}
	
	public String pacienteRiesgoso() throws EmptyPriorityQueueException {
		
		return cola.min().getValue().toString();
		
	}
	
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
	
	
	public Persona eliminar(int dni) throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, TDAMapeo.InvalidKeyException, NotFoundException{
		
		/*if(cola.isEmpty())
			throw new EmptyPriorityQueueException("eliminar(dni):: La cola está vacía");*/
		
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
			paciente=cola.removeMin();
			cola.insert(paciente.getKey(),paciente.getValue());
		}
		
		if(!encontre)
			throw new NotFoundException("La persona con el DNI ingresado no existe");
		
		return paciente.getValue();
	}
	
	public String pacienteHistorico(int dni) throws TDAMapeo.InvalidKeyException{
		
		return mapeoEliminados.get(dni).toString();
	
	}
}
