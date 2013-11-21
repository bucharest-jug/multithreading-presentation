package example7;

public class ActorSample extends Actor {
    @Override
    protected Object receive(Object message) {
        if (message == "increment") {
            inc += 1;
            return inc;
        }
        else if (message == "decrement") {
            inc -= 1;
            return inc;
        }
        else
            throw new UnsupportedOperationException();
    }

    private int inc = 0;
}
