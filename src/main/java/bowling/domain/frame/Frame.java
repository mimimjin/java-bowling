package bowling.domain.frame;

import bowling.domain.PitchResults;
import bowling.domain.Score;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Frame {

    protected final int BOWLING_PIN_COUNT = 10;
    protected final int MAX_FRAME_COUNT = 10;
    protected final String ILLEGAL_KNOCK_DOWN_PINS = "입력한 투구 결과가 남은 핀의 갯수보다 많습니다.";

    protected int index;
    protected PitchResults pitchResults;
    protected Score score;

    public Frame(int index){
        this.index = index;
        this.pitchResults = PitchResults.from(new ArrayList<>());
    }

    public Score getScore() {return this.score;}

    public abstract void setScore(int previousScore);


    public int getIndex() {
        return this.index;
    }

    public PitchResults getPitchResults() {
        return pitchResults;
    }

    public int sumCurrentPitchResults() {
        return pitchResults.sumUpCurrentResult();
    }

    public int sumUpCurrentScore(){
        return score.getScore();
    }

    public abstract void start(int knockedDownPins);

    public abstract boolean isEnd();

    public abstract Frame makeNextFrame(int currentFrameIndex);



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frame frame = (Frame) o;
        return BOWLING_PIN_COUNT == frame.BOWLING_PIN_COUNT &&
                MAX_FRAME_COUNT == frame.MAX_FRAME_COUNT &&
                index == frame.index &&
                Objects.equals(pitchResults, frame.pitchResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(BOWLING_PIN_COUNT, MAX_FRAME_COUNT, index, pitchResults);
    }

    public abstract void renewScore(int knockedDownPins);


    public boolean isRenewScore(Frame currentFrame) {
        return countLeftBonus() > 0 && !(this.index == currentFrame.getIndex());
    }

    public int countLeftBonus(){
        return this.score.getLeftBonusCount();
    }
}
