package observer.cofre;

import java.util.ArrayList;
import java.util.List;

public class Cofre {
<<<<<<< HEAD
	
	private int senha;
	private boolean aberto;
	private List<CofreListener> listeners = new ArrayList<>();
	
=======

	private int senha;
	private boolean aberto;
	private List<CofreListener> listeners = new ArrayList<>();

>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
	public Cofre(int senha) {
		this.senha = senha;
		this.aberto = true;
	}
<<<<<<< HEAD
	
	public boolean isAberto() {
		return this.aberto;
	}
	
=======

	public boolean isAberto() {
		return this.aberto;
	}

>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
	public void fechar() {
		this.aberto = false;
		for (CofreListener listener : this.listeners) {
			listener.cofreFoiFechado();
		}
	}
<<<<<<< HEAD
	
	public void abrir(int senhaInformada) {
		if(senhaInformada == this.senha) {
			this.aberto = true;
			this.listeners.forEach(l -> l.cofreFoiAberto());
		} else {
			throw new RuntimeException("Senha incorreta!");
		}
	}
	
=======

	public void abrir(int senhaInformada) {
		if (senhaInformada == this.senha) {
			this.aberto = true;
			this.listeners.forEach(l -> l.cofreFoiAberto());
		}		
	}

>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
	public void addListener(CofreListenerConsole listener) {
		this.listeners.add(listener);
	}

}
