package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    private T t;


    public Maybe(T t){
        this.t = t;
    }
    public Maybe(){}

    public static <T> Maybe<T> of(T x){
        return new Maybe<T>(x);
    }

    public T get() {
        if(this.t == null){
            throw new NoSuchElementException("maybe is empty");
        }
        return t;
    }
    public void ifPresent(Consumer<T> consumer){
        if(this.t!=null)
            consumer.accept(this.t);
    }
    public <R> Maybe<R> map(Function<T,R> function){
        if(this.t!=null){
            return new Maybe<R>(function.apply(this.t));
        } else{
            return new Maybe();
        }
    }
    public boolean isPresent(){
        if(this.t==null)
            return false;
        else
            return true;
    }
    public T orElse(T defVal){
        if(this.t==null)
            return defVal;
        else
            return this.t;
    }
    public Maybe<T> filter(Predicate<T> predicate){
        if (predicate.test(this.t))
            return this;
        else
            return new Maybe();
    }

    @Override
    public String toString() {
        if (this.t==null)
            return "Maybe is empty";
        else
            return "Maybe has value " + this.t;
    }
}
