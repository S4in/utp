package zad1;


import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class XList<T> extends ArrayList<T> {
    public XList() {
        super();
    }

    public XList(T... t) {
        super();

        if (t[0] instanceof Collection && t.length == 1) {
            super.addAll((Collection) t[0]);
        } else {
            for (T object : t) {
                super.add(object);

            }
        }


    }

    public static <T> XList of(T... t) {

        XList x = new XList<>();
        if (t[0] instanceof Collection && t.length == 1) {
            x.addAll((Collection) t[0]);
        } else {
            for (T object : t) {
                x.add(object);

            }
        }
        return x;
    }

    public static XList<String> charsOf(String str) {
        String[] chars = new String[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars[i] = String.valueOf(str.charAt(i));
        }
        return new XList<String>(chars);
    }

    public static XList<String> tokensOf(String str, String... sep) {
        String[] strings;
        if (sep.length == 1)
            strings = str.split(sep[0]);
        else
            strings = str.split("\\s");
        return new XList<String>(strings);
    }

    /* public XList<T>  union(Collection object) { //uzycie T lub T.. powoduje blad przez konewrsje lub rzutowanie
             XList x = new XList<>();

             x.addAll(this);
             x.addAll((Collection) object);

         return x;
             }*/
    public XList<T> union(Object... objects) {
        XList xList = new XList(this);
        xList.addAll(XList.of(objects));
        return xList;
    }

    public XList<T> diff(Object... objects) {
        XList diff = new XList<>();
        XList obj = new XList(objects);
        for (Object object : this) {
            if (!obj.contains(object))
                diff.add(object);
        }
        return diff;
    }

    public XList<T> unique() {
       XList unique = new XList();
       for(T t : this){
           if(!unique.contains(t))
               unique.add(t);
       }
       return unique;
    }

    public XList combine() {// zakladam ze wywołuje się tę metodę, gdy this jest listą list.

            int length = 1;
            XList<XList<T>> result = new XList<XList<T>>();
        for (Object list:this ) {
            length *= ((List)list).size();
        }
        for (int i = 0; i < length; i++) {
            result.add(getCartesianProduct((XList<XList<T>>) this,i));
        }
        return result;
        // []

    }
    private XList<T> getCartesianProduct(XList<XList<T>> lists, int index){
        int  currentSetLength, totalSets = lists.size();
        T currentElement;
        XList result = new XList();
        for (int i =0; i < totalSets ; i++) {
            currentSetLength = ((Collection)lists.get(i)).size();
            currentElement = (T) ((List) lists.get(i)).get(index % currentSetLength);
            result.add(currentElement);
            index = index / currentSetLength;
        }
        return result;
    }
    public <R> XList<R> collect(Function<T,R> func){
        XList<R> result = new XList<R>();
        for (T t:this) {
            result.add(func.apply(t));
        }
        return result;
    }
    public String join(String... strings){
        String separator;
        if (strings.length==0)
            separator = "";
        else
            separator = strings[0];
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i<this.size(); i++){

            stringBuilder.append(this.get(i).toString());
            if(i!=this.size()-1)
                    stringBuilder.append(separator);
        }
        return stringBuilder.toString();
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }

    }

}



