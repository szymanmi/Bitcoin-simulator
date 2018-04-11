import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;




public class OdliczanieDoKoncaZajec {
    private static Object LOCK = new Object();

    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Hello World!");
        System.out.println(5+5);
        System.out.println("Janek jest gĹ‚upi");
        String test = keyboard.nextLine();
        System.out.println(test);

        LocalTime koniec = LocalTime.of(17, 45);
        LocalTime teraz = LocalTime.now();
        Duration zostalo = Duration.between(teraz, koniec);
        System.out.println("Do koĹ„ca zajÄ™c zostaĹ‚o: ");
        System.out.print(zostalo.toMinutes());

        for( int i = 0; i < 100; i++){
            try {
                Thread.sleep(10000);
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            teraz = LocalTime.now();
            zostalo = Duration.between(teraz, koniec);

            //System.out.println("Do koĹ„ca zajÄ™c zostaĹ‚o: ");
            System.out.print(zostalo.toMinutes() + ":" + zostalo.getSeconds());
            System.out.println();
        }

        //System.out.println(test);
    }
}