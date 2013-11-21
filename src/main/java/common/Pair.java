package common;

public final class Pair<T, U> {
    public final T _1;
    public final U _2;

    private Pair(T v1, U v2) {
        _1 = v1;
        _2 = v2;
    }

    public T value() {
        return _1;
    }

    public U collection() {
        return _2;
    }

    public int hashCode() {
        int h1 = 387213;
        int h2 = 9023810;
        if (_1 != null)
            h1 = _1.hashCode();
        if (_2 != null)
            h2 = _2.hashCode();
        return h1 ^ h2 ^ 389747912;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        else if (obj instanceof Pair<?,?>) {
            Pair<?,?> p2 = (Pair<?,?>) obj;

            boolean oneEquals;
            boolean twoEquals;

            if (_1 == null || p2._1 == null)
                oneEquals = _1 == p2._1;
            else
                oneEquals = _1.equals(p2._1);

            if (_2 == null || p2._2 == null)
                twoEquals = _2 == p2._2;
            else
                twoEquals = _2.equals(p2._2);

            return oneEquals && twoEquals;
        }
        else
            return false;
    }

    public static <T,U> Pair<T,U> of(T v1, U v2) {
        return new Pair<>(v1, v2);
    }
}
