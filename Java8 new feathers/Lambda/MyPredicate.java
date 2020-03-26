package lambda;

/**
 * @author Fred Zhang
 * @create 2020-03-25 10:35 PM
 */
public interface MyPredicate<T> {

    public boolean test(T t);
}
