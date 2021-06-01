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
	
	public String pacienteRiesgoso() throws EmptyPriorityQueueException {
		
		return cola.min().getValue().toString();
		
	}
	
	public String listaPacientes() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		Heap<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		String lista = "";
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			lista += paciente.getValue().toString();
			lista += "\n";
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		cola=colaAux;
		
		return lista;
	}
	
	public String listaPacientesInverso() throws EmptyPriorityQueueException, TDAColaCP.InvalidKeyException, EmptyStackException {
		ComparadorInverso<Integer> comp= new ComparadorInverso<Integer>();
		Heap<Integer,Persona> colaAux = new Heap<Integer,Persona>(comp);
		String lista = "";
		Stack<Persona> pila= new PilaConEnlaces<Persona>();
		TDAColaCP.Entry<Integer,Persona> paciente;
		
		while (!cola.isEmpty()) {
			paciente=cola.removeMin();
			pila.push(paciente.getValue());
			colaAux.insert(paciente.getKey(),paciente.getValue());
		}
		
		while (!pila.isEmpty()) {
			lista += pila.pop().toString();
			lista += "\n";
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
	
	public String pacienteHistorico(int dni) throws TDAMapeo.InvalidKeyException{
		
		return mapeoEliminados.get(dni).toString();
	
	}
}
