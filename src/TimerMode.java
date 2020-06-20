public class TimerMode implements Mode{
    private boolean is_timer_running;
    private boolean is_timer_started;
    private boolean is_timer_used;
    private Segment segment;
    private ButtonActionCallback button_a;
    private ButtonActionCallback button_b;
    private ButtonActionCallback button_c;
    private Time setted_time;
    private Thread timer_runner;
    private Buzzer buzzer;

    private int total_seconds;  // 분을 초로 바꾼 timer 시간

    public TimerMode(Segment segment) {
        is_timer_running = false;
        is_timer_started = false;
        is_timer_used = false;
        this.segment = segment;
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerMinute();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerSeconds();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startTimer();
            }
        };
        setted_time = new Time();
    }
    public void initTimerMode() {
        buzzer = Buzzer.getInstance();
        timer_runner = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        if(!checkTimerEnded() && is_timer_running) {
                            Thread.sleep(1000);
                            if(!checkTimerEnded() && is_timer_running)
                            decreaseTimer();
                        }else{
                            if(is_timer_started && is_timer_running){
                                buzzer.OnBuzzer();
                                cancelTimer();
                            }else{
                                Thread.yield();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        timer_runner.start();
    }
    private void startTimer() {
        int set_minute;
        int set_seconds;

        if(checkTimerZero() == true) {
            // 00 : 00 일 땐, 아무일도 안 일어난다.
        } else {
            set_minute = setted_time.getMinute();
            set_seconds = setted_time.getSeconds();

            // 분을 초 단위로 바꾸고, curr_seconds 에 더하기
            total_seconds = Time.convertMinuteToSeconds(set_minute) + set_seconds;

            // 타이머 시작
            is_timer_running = true;
            is_timer_started = true;

            // startTimer 눌렀을 때의, A, B, C 의 번호 매핑이 바뀜.
            mappingTimerRunning();
            displayTimer();
        }
    }
    private void pauseTimer()  {
        if(is_timer_running == true) {
            is_timer_running = false;
            mappingTimerPause();
            displayTimer();
        }
    }
    private void continueTimer() {
        if(checkTimerZero() == false) {
            is_timer_running = true;
            mappingTimerRunning();
            displayTimer();
        }
    }
    private void cancelTimer() {
        is_timer_running = false;
        is_timer_started = false;
        mappingTimerState();
        displayTimer();
    }
    private void increaseTimerMinute() {
        if(setted_time.getMinute() < 59) {
            int set_minute = setted_time.getMinute();
            set_minute++;
            setted_time.setMinute(set_minute);
        } else {
            setted_time.setMinute(0);
        }
        displayTimer();
    }
    private void increaseTimerSeconds() {
        if(setted_time.getSeconds() < 59) {
            int set_seconds = setted_time.getSeconds();
            set_seconds++;
            setted_time.setSeconds(set_seconds);
        } else {
            setted_time.setSeconds(0);
        }
        displayTimer();
    }

    private boolean checkTimerZero() {
        if(setted_time.getMinute() == 0 && setted_time.getSeconds() == 0) {
            return true;
        }
        return false;
    }

    private boolean checkTimerEnded(){
        return total_seconds == 0? true : false;
    }


    private void decreaseTimer() {
        total_seconds--;
        displayTimer();
    }

    private void mappingTimerState() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerMinute();
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                increaseTimerSeconds();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                startTimer();
            }
        };
    }
    private void mappingTimerRunning() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                pauseTimer();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                cancelTimer();
            }
        };
    }
    private void mappingTimerPause() {
        button_a = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                // 비활성화
            }
        };
        button_b = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                continueTimer();
            }
        };
        button_c = new ButtonActionCallback() {
            @Override
            public void OnButtonPressed() {
                cancelTimer();
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
        is_timer_used = false;
    }

    @Override
    public void OnInitThisMode() {
        is_timer_used = true;
        displayTimer();
    }


    public void displayTimer(){
        if(!is_timer_used){
            return;
        }
        if(!is_timer_started){
            segment.setSegmentUpper("SET TIMER",true);
            segment.setSegmentLower(setted_time.makeSugarStringDay().substring(3),true);
        }else{
            int min, sec;
            min = ((int)(total_seconds / 60));
            sec = (total_seconds % 60);
            segment.setSegmentLower( (min < 10? "0" + min:min)+ ":" + (sec < 10? "0" + sec:sec),true);
            if(is_timer_running){
                segment.setSegmentUpper("RUNNING",true);
            }else{
                segment.setSegmentUpper("PAUSE",true);
            }
        }
    }

    @Override
    public String toString(){
        return "TIMER";
    }
}