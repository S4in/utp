package test;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
        Set set = new HashSet();
        set.addAll(this);
        System.out.println(this);
        return new XList(set);
    }

    public XList combine() {// zakladam ze wywołuje się tę metodę, gdy this jest listą list.

        XList<ArrayList> result = new XList<ArrayList>();
        List[] lists = new List[this.size()];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = (List) this.get(i);
        }
        System.out.println(lists[0]);
        System.out.println(lists[1]);
        System.out.println(lists[2]);
        int[] indexes = new int[lists.length];
        for (int i : indexes) {
            i = 0;
        }
        while (true) {
            List temp = new ArrayList();
            for (int i = 0; i < lists.length; i++) {
                for (int j = 0; j < lists.length; j++) {
                    if (lists[j].size() > indexes[i])
                        temp.add(lists[j].get(indexes[i]));

                }

            }
            // result.add((ArrayList) temp);


            System.out.println(temp);
            break;


        }
        return result;
    }

    public void forEachWithIndex(BiConsumer<T, Integer> consumer) {
        for (int i = 0; i < this.size(); i++) {
            consumer.accept(this.get(i), i);
        }

    }

}



