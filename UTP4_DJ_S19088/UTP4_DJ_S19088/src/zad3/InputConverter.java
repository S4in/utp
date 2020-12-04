package zad3;

import java.util.function.Function;

public class InputConverter<T> {
    private T t;
    public InputConverter(T t){
        this.t = t;
    }
    public <R> R convertBy(Function... functions){
        R result = (R)t;
        int length = functions.length;
            for (int i = 0; i < length ; i+=2) {
                if (i == length -1)//jesli jest ostatnia funkcja w tablicy
                    result = (R) functions[i].apply(result);
                else
                    result = (R) functions[i].andThen(functions[i + 1]).apply(result);

            }

        return result;
    }
}
