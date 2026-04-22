import java.util.ArrayList;
/**
 Clase que actúa como MONITOR/SEMAFORO.
 Representa el recurso compartido (el depósito de cafés).
 Encapsula el estado y asegura que el acceso concurrente sea seguro.
 */
public class DepositoCafe {
    // Alamacenamos los cafés que están listos para consumir
    private ArrayList<String> deposito = new ArrayList<String>();
    // Capacidad máxima del depósito.
    private int capacidad;
    /**
     Constructor: Define el estado inicial y la capacidad límite al crear la instancia en el Main.
     */
    public DepositoCafe(int capacidad){
        super();
        this.deposito = new ArrayList<>(0);
        this.capacidad = capacidad;
    }
    // Uso 'synchronized' para que solo un hilo (profesor o máquina) pueda usar el depósito a la vez.
    // Si no, la máquina podría meter un café mientras un profe intenta sacarlo, y se produciría una "race condition".
    public synchronized void depositarCafe(String cafe) throws InterruptedException {
    // Usamos 'while' con 'wait'. Comprobamos si el depósito está lleno.
        while (deposito.size() == capacidad) {
            System.out.println("Máquina en espera, depósito lleno");
            // El depósito está lleno, así que la máquina se pone en pausa y suelta el candado ('lock')
            // para que los profesores puedan entrar a sacar cafés.
            wait();
        }
        // Si llegamos aquí, es que hay hueco. Añadimos el café.
        deposito.add(cafe);
        System.out.println("Cafetera prepara y deposita:" + cafe + ", cantidad restante de tazas de cafe: " +  deposito.size());
        // Avisamos a todos los hilos que estén dormidos (profesores) de que ya hay un café nuevo disponible.
        notifyAll();
    };
    // Usamos "synchronized" por la misma razón que antes.
    public synchronized void retirarCafe(String nombreProfesor) throws InterruptedException {
        // Comprobamos si no hay cafés.
        while (deposito.isEmpty()) {
            System.out.println("Máquina vacía, espera a que se llene");
            // Si no hay café, el hilo profesor se queda en pausa, y entra el hilo de la máquinaCafe a hacer más.
            wait();
        }
        // Sacamos el café más antiguo (el primero de la lista).
        String cafeSacado = deposito.remove(0);
        System.out.println("Profesor: " +  nombreProfesor + " retira: " + cafeSacado + ", cantidad restante de tazas de cafe: " + deposito.size());
        // Avisamos al hilo de depositarCafe, para decirle que ya puede hacer otro cafe.
        notifyAll();
    }
};
