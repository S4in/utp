/**
 *
 *  @author Dudek Jakub S19088
 *
 */

package zad1;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;



public class CustomersPurchaseSortFind {
        private static final String NAMEFILTER = "Nazwiska";
        private static final String COSTFILTER = "Koszty";
        private List<Purchase> purchases;
    public void readFile(String fname) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        purchases = new ArrayList<Purchase>();
        for (String line:lines) {
            String[] tmp =line.split(";");
            String id = tmp[0];
            String name = tmp[1];
            String product = tmp[2];
            float price = Float.parseFloat(tmp[3]);
            float amount = Float.parseFloat(tmp[4]);
            purchases.add(new Purchase(id,name,product,price,amount));
        }

    }

    public void showSortedBy(String filter) {
            switch (filter){
                case NAMEFILTER:
                    SortedByName();
                    break;
                case COSTFILTER:

                    SortedByCost();
                    break;
            }
    }

    private void SortedByCost() {
        Collections.sort(purchases,(p1,p2)->{
            int result = (int) (p2.getCost() - p1.getCost());
            if (result == 0){
                return p1.getId() - p2.getId();
            }
            return result;
        });
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(COSTFILTER).append("\n");
        purchases.stream().forEach(purchase -> stringBuilder.append(purchase.toString()).append("(koszt: ").append(purchase.getCost()).append(')').append("\n"));
        System.out.println(stringBuilder.toString());
    }

    private void SortedByName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NAMEFILTER).append("\n");
     /*  List<String> names = purchases.stream().map(purchase -> purchase.getName())
               .sorted((s1,s2)-> Collator.getInstance(new Locale("pl")).compare(s1,s2)).map(s); //to powinno by dawać sortowanie polskich znakow
        System.out.println(names);*/
        Collections.sort(purchases);
        for (Purchase purchase: purchases) {
            stringBuilder.append(purchase.toString()).append("\n");
        }
        System.out.println(stringBuilder.toString());
        
    }

    public void showPurchaseFor(String id) {
        int i = Integer.parseInt(id.substring(1,id.length()));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Klient: ").append(id).append("\n");
        purchases.stream().
                filter((p)->p.getId() == i).
                forEach(p->stringBuilder.append(p.toString()).append("\n"));
        System.out.println(stringBuilder.toString());

    }
}
