import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeManager {
    public static TimeManager instance = null;
    public LocalDateTime local_date_time;

    public TimeManager(){ }

    public static TimeManager getInstance(){
        if(instance == null){
            instance = new TimeManager();
        }
        return instance;
    }

    public Time getCurrentTime(){
        if(TestCurrentTime());
        return new Time(local_date_time);
    }

    public Boolean TestCurrentTime(){
        local_date_time = LocalDateTime.now();
        return true;
    }
}
