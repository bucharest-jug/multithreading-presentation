package common;

import java.util.NoSuchElementException;

public final class Optional<T> {
    private final T value;
    private final boolean hasValue;

    private Optional(T value, boolean hasValue) {
        this.value = value;
        this.hasValue = hasValue;
    }

    public T get() {
        if (!hasValue)
            throw new NoSuchElementException("absent.get");
        return value;
    }

    public T getOrElse(T defaultValue) {
        if (!hasValue)
            return defaultValue;
        else
            return value;
    }

    public boolean isAbsent() {
        return !hasValue;
    }

    public boolean isPresent() {
        return hasValue;
    }

    @Override
    public int hashCode() {
        if (isPresent() && value != null)
            return value.hashCode() ^ 255;
        else if (isPresent())
            return absent().hashCode() ^ 255;
        else
            return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Optional<?>) {
            Optional<?> o2 = (Optional<?>) obj;
            if (o2.value == null || value == null)
                return value == o2.value;
            else
                return value.equals(o2.value);
        }
        else
            return false;
    }

    public static <T> Optional<T> of(T value) {
        return new Optional<>(value, true);
    }

    @SuppressWarnings("unchecked")
    private static Optional empty = new Optional(null, false);

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> absent() {
        return (Optional<T>) empty;
    }
}
