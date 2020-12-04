package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {

    private ArrayList<T> list;
    public ListCreator(List<T> src) {
        list = new ArrayList<T>();
        this.list.addAll(src);

    }

    public static<T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<T>(src);
    }

    public ListCreator<T> when(Predicate<T> selector){//selktor bez parametra tu
        for (int i = 0; i < list.size() ; i++) {
            if(!selector.test( list.get(i))) {
                list.remove(list.get(i));
                i--; // indeksowanie się zmienia po usunięciu obiektu, kolejny obiekt ma indeks tego usunietego elementu
            }
        }
        return this;
    }

    public <R> List<R> mapEvery(Function<T,R> map){
        ArrayList<R> arrayList = new ArrayList<R>();
        for (int i = 0; i <list.size() ; i++) {
            arrayList.add(map.apply( list.get(i)));
        }
        return arrayList;
    }}