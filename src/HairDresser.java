import java.util.Random;

public class HairDresser implements Runnable{
    private final HairSalon hairSalon;
    private final String name;

    public HairDresser(HairSalon hairSalon, String name) {
        this.hairSalon = hairSalon;
        this.name = name;
    }
    @Override
    public void run(){
        Random random = new Random();
        try{
            while(true){
                Client client = hairSalon.getClient();
                System.out.println(this.name+" begins to attend to "+client.getName());
                int timeAtention = random.nextInt(10)+1;
                Thread.sleep(timeAtention*1000);
                System.out.println(this.name+" ended to attend to "+client.getName()+" in "+timeAtention+" seconds");
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
//            e.printStackTrace();//detalla donde esta el fallo
        }
    }
}
