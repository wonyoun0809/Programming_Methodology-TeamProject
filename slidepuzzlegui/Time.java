package slidepuzzlegui;

import java.time.*;

public class Time {

    private Instant startTime;
    private Instant endTime;

    public void startGame() {
        startTime = Instant.now();  // 게임 시작 시간 기록
    }

    public void endGame() {
        endTime = Instant.now();  // 게임 종료 시간 기록
    }

    public long getElapsedTime() {
        return Duration.between(startTime, endTime).getSeconds();  // 게임 종료 후 걸린 시간(초 단위)
    }
}

