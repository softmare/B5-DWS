public class StopWatchMode implements Mode{
    private boolean is_stop_watch_running = false;
    private boolean is_stop_watch_started = false;
    private Thread stop_watch_running;
    private Time elapsed_time;  // 스톱워치에 설정된 현재 상태 분과 초로만! (밀리초는 버리는걸로)
    private ButtonActionCallback button_a;
    private ButtonActionCallback button_b;
    private ButtonActionCallback button_c;
    private Segment segment;

    public StopWatchMode(Segment segment) {
        this.segment = segment;
        is_stop_watch_running = false;
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startStopWatch();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // pause, continue 비활성화
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // init 비활성화
            }
        };
        elapsed_time = new Time();
    }

    public void initStopWatchMode() {
        stop_watch_running = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if(!testStopWatchMax() && is_stop_watch_running) {
                            Thread.sleep(1000);
                            if(!testStopWatchMax() && is_stop_watch_running)
                            increaseStopWatchSeconds();
                        }else{
                            Thread.yield();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        stop_watch_running.start();
        elapsed_time.setSeconds(0);
        elapsed_time.setMinute(0);
    }
    private void startStopWatch() {
        is_stop_watch_running = true;
        is_stop_watch_started = true;
        mappingStopWatchRunning();
        displayStopWatchState();
    }
    private void pauseStopWatch() {
        is_stop_watch_running = false;
        mappingStopWatchPause();
        displayStopWatchState();
    }
    private void continueStopWatch() {
        is_stop_watch_running = true;
        mappingStopWatchRunning();
        displayStopWatchState();
    }
    private void increaseStopWatchSeconds() {
        int set_seconds;
        int set_minute;

        // 현재 초와 분을 얻어오기
        set_seconds = elapsed_time.getSeconds();
        set_minute = elapsed_time.getMinute();

        // 1초 증가해주기
        set_seconds++;

        // 60초 될 때, 초는 0으로 바꿔주고 분을 1 증가해주기
        if(set_seconds == 60) {
            elapsed_time.setSeconds(0);
            elapsed_time.setMinute(set_minute + 1);
        } else {
            elapsed_time.setSeconds(set_seconds);
        }
        displayStopWatchState();
    }
    // 스탑워치의 한계치 59분 59초
    private boolean testStopWatchMax() {
        if(elapsed_time.getMinute() == 59 && elapsed_time.getSeconds() == 59) {
            return true;
        } else {
            return false;
        }
    }
    // 중단한 후에만 reset 가능
    private void resetStopWatch() {
        is_stop_watch_running = false;
        is_stop_watch_started = false;
        elapsed_time.setSeconds(0);
        elapsed_time.setMinute(0);
        mappingStopWatchState();
        displayStopWatchState();
    }
    private void mappingStopWatchState() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startStopWatch();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
    }
    private void mappingStopWatchRunning() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                pauseStopWatch();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
    }
    private void mappingStopWatchPause() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                continueStopWatch();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                resetStopWatch();
            }
        };
    }

    @Override
    public void OnButtonA() {
        if(button_a != null) button_a.OnButtonPressed();
    }

    @Override
    public void OnButtonB() {
        if(button_b != null) button_b.OnButtonPressed();
    }

    @Override
    public void OnButtonC() {
        if(button_c != null) button_c.OnButtonPressed();
    }

    @Override
    public void OnEndOfThisMode() {
        is_stop_watch_running = false;
    }

    @Override
    public void OnInitThisMode() {
        displayStopWatchState();
    }

    private void displayStopWatchState(){
        if(!is_stop_watch_started){
            segment.setSegmentUpper("StopWatch",true);
            segment.setSegmentLower("00:00",true);
        }else{
            if(is_stop_watch_running){
                segment.setSegmentUpper("Running",true);
                segment.setSegmentLower(  elapsed_time.getMinute() + ":"+ elapsed_time.getSeconds(),true);
            }else{
                segment.setSegmentUpper("Paused",true);
                segment.setSegmentLower(  elapsed_time.getMinute() + ":"+ elapsed_time.getSeconds(),true);
            }
        }
    }
}