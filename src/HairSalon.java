import java.util.LinkedList;
import java.util.Queue;

public class HairSalon {
    private static final int NUM_HAIR_DRESSERS = 3;
    private static final int NUM_CLIENTS = 10;
    private Queue<Client> colaClients = new LinkedList<>();
    Object lock = new Object();
    private Thread[] hair_dressers;

    public HairSalon(){
        hair_dressers = new Thread[NUM_HAIR_DRESSERS];
        for(int i=0;i<NUM_HAIR_DRESSERS;i++){
            hair_dressers[i] = new Thread(new HairDresser(this,"Hair Dresser "+(i+1) ));
        }
    }

    public void init(){
        for(Thread hairDresser : hair_dressers){
            hairDresser.start();
        }

        for(int i = 1; i<NUM_CLIENTS;i++){
            Client client = new Client("Client "+i);
            addClient(client);
        }
    }

    public void addClient(Client client){
        synchronized (lock){
            colaClients.offer(client);
            lock.notify();
        }
    }

    public Client getClient() throws InterruptedException{
        synchronized (lock){
            while(colaClients.isEmpty()){
                lock.wait();
            }
            return colaClients.poll();
        }
    }
}
