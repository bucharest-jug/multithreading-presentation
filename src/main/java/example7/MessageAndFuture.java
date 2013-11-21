package example7;

public final class MessageAndFuture {
    public final Object message;
    public final CompletableFuture<Object> future;

    public MessageAndFuture(Object o, CompletableFuture<Object> f) {
        message = o;
        future = f;
    }
}
