public class Main {

    public static void main(String[] args) {
        // Creamos el Monitor (el DepositoCafe) y le asignamos 5 cafes como capacidad máxima.
        DepositoCafe deposito = new DepositoCafe(5);

        // Creamos al productor (la MaquinaCafe)v y le pasamos el depósito.
        MaquinaCafe maquina = new MaquinaCafe(deposito);

        // Creamos los consumidores (los profesores) y le pasamos el depósito, el nombre del profesor, y cuántos cafés se van a tomar.
        // Como hemos puesto 20, lo hacemos "cuadrar".
        Profesor profeAna = new Profesor(deposito, "Ana", 7);
        Profesor profeLuis = new Profesor(deposito, "Luis", 7);
        Profesor profeMarta = new Profesor(deposito, "Marta", 6);

        // Arrancamos todos los hilos a la vez.
        // Al usar 'extends Thread' en nuestras clases, ya podemos usar .start() directamente.
        maquina.start();
        profeAna.start();
        profeLuis.start();
        profeMarta.start();
    }
};