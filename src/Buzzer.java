import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Buzzer {

    public boolean is_buzzer_running = false;
    public Thread sound_player;
    public Thread reserve;
    public ModeManagerInteracter instance;
    public final static long RESERVE_OFF_TIME_IN_MIL = 30000;
    public static Buzzer singleton;

    public static Buzzer getInstance(){
        if(singleton == null){
            singleton = new Buzzer();
        }
        return singleton;
    }

    public Buzzer(){
        sound_player = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url  = this.getClass().getClassLoader().getResource("beep.wav");
                try {
                    while(true){
                        if(is_buzzer_running){
                        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                        // Get a sound clip resource.
                        Clip clip = null;
                        clip = AudioSystem.getClip();
                        // Open audio clip and load samples from the audio input stream.
                        clip.open(audioIn);
                        clip.start();
                        Thread.sleep(300);
                        }else{
                            Thread.yield();
                        }
                    }
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sound_player.start();
        instance = ModeManagerInteracter.getInstance();
    }

    public void OnBuzzer(){
        if(is_buzzer_running){
            OffBuzzer();
        }
        is_buzzer_running = true;
        instance.activeReserveForcedAction(new StandardCallback() {
            @Override
            public void CallbackFunction() {
                OffBuzzer();
            }
        });
        reserve = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(RESERVE_OFF_TIME_IN_MIL);
                    OffBuzzer();
                } catch (InterruptedException e) {

                }
            }
        });
        reserve.start();
    }

    public void OffBuzzer(){
        is_buzzer_running = false;
        instance.activeCancelForceAction();
        reserve.interrupt();
    }
}
