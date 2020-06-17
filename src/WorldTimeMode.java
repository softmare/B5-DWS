import java.lang.Thread;

public class WorldTimeMode implements Mode {
    public TimeManager time_manager = TimeManager.getInstance();
    public Segment segment;
    public Thread world_time_updater = new Thread();
    public World[] worlds = new World[20];
    public int world_index = 0;
    public boolean locked = false;     // 초기에는 locked 되어있지 않으므로 false로 초기화
    public Time current_time = new Time();
    public boolean is_world_time = false;

    public WorldTimeMode(Segment segment){
        this.segment = segment;
    }

    public void initWorldTimeMode() {
        worlds[0] = new World(new Time(0,0,-17,0,0,0),"WASHINGTON");
        worlds[1] = new World(new Time(0,0,-9,0,0,0),"LONDON");
        worlds[2] = new World(new Time(0,0,-8,0,0,0),"PARIS");
        worlds[3] = new World(new Time(0,0,-7,0,0,0),"BERLIN");
        worlds[4] = new World(new Time(0,0,0,0,0,0),"TOKYO");
        worlds[5] = new World(new Time(0,0,-8,0,0,0),"ROMA");
        worlds[6] = new World(new Time(0,0,-14,0,0,0),"OTTAWA");
        worlds[7] = new World(new Time(0,0,-6,0,0,0),"MOCKBA");
        worlds[8] = new World(new Time(0,0,-7,0,0,0),"BRUSSELS");
        worlds[9] = new World(new Time(0,0,0,0,0,0),"SEOUL");
        worlds[10] = new World(new Time(0,0,-1,0,0,0),"BEIJING");
        worlds[11] = new World(new Time(0,-30,-3,0,0,0),"DELHI");
        worlds[12] = new World(new Time(0,0,-2,0,0,0),"ZAKARTA");
        worlds[13] = new World(new Time(0,0,-6,0,0,0),"ANKARA");
        worlds[14] = new World(new Time(0,0,-6,0,0,0),"RIYADH");
        worlds[15] = new World(new Time(0,0,-7,0,0,0),"PRETORIA");
        worlds[16] = new World(new Time(0,0,-15,0,0,0),"MAXICO");
        worlds[17] = new World(new Time(0,0,-12,0,0,0),"BRASILIA");
        worlds[18] = new World(new Time(0,0,-12,0,0,0),"BUENOS AIRES");
        worlds[19] = new World(new Time(0,0,1,0,0,0),"SYDNEY");
        world_time_updater = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(is_world_time) {
                        try {
                            Thread.sleep(1000);
                            if(is_world_time)
                            syncWorldTime();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Thread.yield();
                    }
                }
            }
        });
        world_time_updater.start();
    }

    public void nextWorldTime() {
        increaseWorldTimeIndex();

        // 변경된 나라로 시간 동기화
        syncWorldTime();
    }

    public void prevWorldTime() {
        decreaseWorldTimeIndex();

        // 변경된 나라로 시간 동기화
        syncWorldTime();
    }

    public void increaseWorldTimeIndex() {
        if(world_index < worlds.length) world_index++;
        if(world_index == worlds.length) world_index = 0;
    }

    public void decreaseWorldTimeIndex() {
        if(world_index > -1) world_index--;
        if(world_index == -1) world_index = worlds.length-1;
    }

    public void holdCurrentWorldTime() {
        locked = true;
    }

    public void releaseCurrentWorldTimeRock() {
        locked = false;
    }

    public void syncWorldTime() {
        current_time = time_manager.getCurrentTime();   // 현재 시간
        current_time.addTime(worlds[world_index].weight);    // 현재시간 + 나라별 시간차 가중치
        // 변경된 값 세그먼트에 출력 SetSegmentUpper(“국가명”)
        segment.setSegmentUpper(worlds[world_index].name, true);
        // 변경된 나라의 시간 출력
        segment.setSegmentLower(current_time.makeSugarStringDay(), true);
    }

    @Override
    public void OnButtonA() {
        if(locked == false) prevWorldTime();
    }

    @Override
    public void OnButtonB() {
        if(locked == false) nextWorldTime();
    }

    @Override
    public void OnButtonC() {
        if(locked == true) releaseCurrentWorldTimeRock();   // 홀드 상태(true)면 잠금 해제(false)
        else holdCurrentWorldTime();    // 작금 해제 상태(false)면 홀드(true)
    }

    @Override
    public void OnEndOfThisMode() {
        is_world_time = false;
    }

    @Override
    public void OnInitThisMode() {
        syncWorldTime();
        is_world_time = true;
    }

    @Override
    public String toString(){
        return "WORLD";
    }
}