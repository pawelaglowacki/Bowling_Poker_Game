import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {
	private final int LAST_FRAME = 10;
	private final int ALL_PINS = 10;

	private int frameNumber = 1;
	private boolean finished = false;
	private List<Frame> frames = new ArrayList<Frame>();

	public void roll(int numberOfPins) {

		ensureFrameExistence();
		addPointsTocurrentFrame(numberOfPins);
		HandleStrike(numberOfPins);
		HandleSpare();
		if (currentFrame().getBallNumber() < 3)
			addPointsToPreviousFrames(numberOfPins);
		nextBall();
	}

	public void nextBall() {
		if (frameNumber == LAST_FRAME) {
			TenthFrameHandling();
			return;
		}

		if (currentFrame().getBallNumber() == 1) {
			if (currentFrame().isStrike()) {
				frameNumber++;
				return;
			}
			currentFrame().setBallNumber(2);
			return;
		}

		currentFrame().setBallNumber(1);
		frameNumber++;

	}

	public void TenthFrameHandling() {
		if (TenthFrame().getBallNumber() == 1) {
			TenthFrame().setBallNumber(2);
			return;
		}

		if (TenthFrame().getBallNumber() == 2) {
			if (TenthFrame().getScore() >= ALL_PINS) {
				TenthFrame().setBallNumber(3);
				return;
			}
			finished = true;
		}

		if (TenthFrame().getBallNumber() == 3) {
			finished = true;
		}
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public int getFrameScore(int numberOfFrame) {
		return frames.get(numberOfFrame - 1).getScore();
	}

	public int getScore() {
		int result = 0;
		for (int i = 0; i < frames.size(); i++) {
			result += frames.get(i).getScore();
		}
		return result;
	}

	public void ensureFrameExistence() {
		if (frames.size() < frameNumber)
			frames.add(new Frame());
	}

	public void addPointsTocurrentFrame(int numberOfPins) {
		currentFrame().add_pins(numberOfPins);
	}

	public void HandleStrike(int numberOfPins) {
		if (numberOfPins == 10)
			currentFrame().setStrike(true);
	}

	public void HandleSpare() {
		if (!currentFrame().isStrike() && currentFrame().getScore() == 10)
			currentFrame().setSpare(true);
		return;
	}

	public void addPointsToPreviousFrames(int numberOfPins) {

		if (frameNumber >= 2)
			if (currentFrame().getBallNumber() == 1 && previousFrame().isSpare()) {
				previousFrame().add_pins(numberOfPins);
			}

		if (frameNumber >= 2)
			if (previousFrame().isStrike()) {
				previousFrame().add_pins(numberOfPins);

				if (frameNumber >= 3)
					if (SecondToLastFrame().isStrike() && currentFrame().getBallNumber() == 1) {
						previousFrame().add_pins(numberOfPins);
					}
			}
	}

	public boolean isFinished() {
		return finished;
	}

	public Frame currentFrame() {
		return frames.get(frameNumber - 1);
	}

	private Frame previousFrame() {
		return frames.get(frameNumber - 2);
	}

	public Frame SecondToLastFrame() {
		return frames.get(frameNumber - 3);
	}

	public Frame TenthFrame() {
		return frames.get(9);
	}
}