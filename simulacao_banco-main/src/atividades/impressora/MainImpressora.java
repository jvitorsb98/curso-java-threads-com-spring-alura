package atividades.impressora;

public class MainImpressora {



    public static void main(String[] args) {
        Impressora impressora1 = new Impressora(1);
        Impressora impressora2 = new Impressora(2);
        RealizaTarefa realizaTarefa = new RealizaTarefa();
        Thread threadImpressora1 = new Thread(impressora1);

        Thread threadImpressora2 = new Thread(impressora2);
        Thread threadTarefa = new Thread(realizaTarefa);

        System.out.println(threadTarefa.isAlive());
        threadTarefa.start();
        try {
            threadTarefa.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(threadTarefa.isAlive());
        threadImpressora1.setPriority(1);
        threadImpressora2.setPriority(10);

        threadImpressora1.start();
        threadImpressora2.start();
    }

}
