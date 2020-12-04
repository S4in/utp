/**
 *
 *  @author Dudek Jakub S19088
 *
 */

package zad1;


import java.text.Collator;
import java.util.Locale;

public class Purchase implements Comparable<Purchase>{

    private final String name, product, id;
    private final float cost, price, amount;
    private final int numberId;



    Purchase(String id, String name, String product, float price, float amount){
            this.id = id;
            this.name = name;
            this.product = product;
            this.cost = price * amount;
            this.amount = amount;
            this.price = price;
            this.numberId = Integer.parseInt(id.substring(1,id.length()));
    }


    @Override
    public int compareTo(Purchase purchase) {

        int result = Collator.getInstance(new Locale("pl")).compare(this.name,purchase.getName()); //sortuje po plsku:)
        if(result == 0){
            result = this.numberId - purchase.getId();
        }
        return result;
    }



    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }
    public int getId(){
        return numberId;
    }

    @Override
    public String toString() {
        char coma =';';
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(coma).append(name).append(coma).append(product).append(coma).append(price).append(coma).append(amount);
        return stringBuilder.toString();
    }
}
