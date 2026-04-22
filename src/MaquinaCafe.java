// El PRODUCTOR
public class MaquinaCafe extends Thread {
    private DepositoCafe deposito;

    // Creamos el constructor
    public MaquinaCafe(DepositoCafe deposito){
        this.deposito = deposito;
    }

    // Sobrescribimos el mé_todo run() de Thread.
    // To_do lo que hay aquí dentro es el "trabajo" que hará este hilo de
    // forma independiente en segundo plano cuando le hagamos .start() en el Main.
    @Override
    public void run() {
        // Producimos un número limitado de cafes, por ejemplo 20.
        for (int i = 1; i <= 20; i++) {
            try {
                // Simulamos el tiempo que tarda en hacer el café.
                // Math.random() * 1000 da un número entre 0 y 1000 milisegundos.
                int tiempoPreparacion = (int) (Math.random() * 1000);
                Thread.sleep(tiempoPreparacion);

                String nombreCafe = "Cafe " + i;

                // Intentamos dejarlo en el depósito.
                // Si el depósito está lleno, este mé_todo pondrá a la máquina
                // en wait() automáticamente.
                deposito.depositarCafe(nombreCafe);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-- La Maquina de Cafe ha producido 20 cafés --");
    }
};
