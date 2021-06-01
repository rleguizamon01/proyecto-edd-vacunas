package Programa;

import Auxiliar.*;
import TDACola.*;
import TDAColaCP.*;
import TDALista.*;
import TDAMapeo.*;
import TDAPila.*;

public class Logica {
	
	Heap<Integer,Persona> cola;
	HashAbierto<Integer,Persona> mapeoEliminados;
	
	public Logica() {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		cola = new Heap<Integer,Persona>(comp);
		mapeoEliminados = new HashAbierto<Integer,Persona> ();
	}
	
	public void inscripción(String nombre, String apellido, int dni, int grupo) throws TDAColaCP.InvalidKeyException {
		
		Persona per=new Persona(nombre,apellido,dni);	
		cola.insert(grupo,per);
		
	}
	
	public Persona pacienteRiesgoso() throws EmptyPriorityQueueException {
		
		return cola.min().getValue();
		
	}
	
	public PositionList<Persona> listaPacientes() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		Heap<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		PositionList<Persona> lista=new ListaDobleSinCentinelas<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			lista.addLast(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		cola=colaAux;
		
		return lista;
	}
	
	public PositionList<Persona> listaPacientesInverso() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, EmptyStackException {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		Heap<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		PositionList<Persona> lista=new ListaDobleSinCentinelas<Persona>();
		Stack<Persona> pila= new PilaConEnlaces<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			pila.push(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		while (!pila.isEmpty()) {
			lista.addLast(pila.pop());
		}
		
		cola=colaAux;
		
		return lista;
	}
	
	
	public boolean eliminar(int dni) throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, TDAMapeo.InvalidKeyException{
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		Heap<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
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
			paciente=cola.removeMin();
			cola.insert(paciente.getKey(),paciente.getValue());
		}
		
		
		
		return encontre;
	}
	
	public Persona pacienteHistorico(int dni) throws TDAMapeo.InvalidKeyException{
		
		return mapeoEliminados.get(dni);
	
	}
}
