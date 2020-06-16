package observer.cofre;

import java.util.Date;

public class CofreListenerConsole implements CofreListener {

	@Override
	public void cofreFoiAberto() {
		System.out.println("O cofre foi aberto: " + new Date().toLocaleString());
	}
<<<<<<< HEAD
	
	@Override
	public void cofreFoiFechado() {
		System.out.println("O cofre foi fechado: " + new Date().toLocaleString());
	}
=======

	@Override
	public void cofreFoiFechado() {
		System.out.println("O cofre foi fechado: " + new Date().toLocaleString());		
	}

>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
}
