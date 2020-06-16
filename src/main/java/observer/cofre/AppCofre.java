package observer.cofre;

public class AppCofre {
	
	public static void main(String[] args) {
		Cofre daSala = new Cofre(123456);
		
		daSala.addListener(new CofreListenerConsole());
<<<<<<< HEAD
=======
		
>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
		System.out.println(daSala.isAberto());
		
		daSala.fechar();
		System.out.println(daSala.isAberto());
		
		daSala.abrir(111);
		System.out.println(daSala.isAberto());
<<<<<<< HEAD
		
		daSala.abrir(123456);
		System.out.println(daSala.isAberto());
		
		System.out.println("fim");
=======

		daSala.abrir(123456);
		System.out.println(daSala.isAberto());
		
		System.out.println("Fim.");
		
>>>>>>> a5741e04c99201737b5c9c5cc2fc2c5b71ae5388
	}

}
