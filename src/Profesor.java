//EL CONSUMIDOR
public class Profesor extends Thread {
    private DepositoCafe deposito;
    private String nombreProfesor;
    private int cantidadConsumir;

    // Creamos el constructor
    public Profesor(DepositoCafe depositoCompartido, String nombreProfesor, int cantidadConsumir) {
        this.deposito = depositoCompartido;
        this.nombreProfesor = nombreProfesor;
        this.cantidadConsumir = cantidadConsumir;
    }

    // Sobrescribimos el mé_todo run() de Thread.
    // To_do lo que hay aquí dentro es el "trabajo" que hará este hilo de
    // forma independiente en segundo plano cuando le hagamos .start() en el Main.
    @Override
    public void run() {
        // Un bucle que se repite tantas veces como cafés quiera tomarse.
        for (int i = 0; i < cantidadConsumir; i++) {
            try {
                // 1. Va al depósito a por su café.
                // Si está vacío, el mé_todo 'retirarCafe' lo pondrá a dormir (wait)
                // automáticamente hasta que la máquina haga más.
                deposito.retirarCafe(this.nombreProfesor);

                // 2. Se toma el café antes de pedir otro.
                // Simulamos ese tiempo con un sleep aleatorio.
                int tiempoBebiendo = (int) (Math.random() * 4000);
                Thread.sleep(tiempoBebiendo);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El profesor " + nombreProfesor + " ya se ha tomado sus " + cantidadConsumir + " cafés.");
    }
};
