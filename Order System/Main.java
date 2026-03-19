import java.util.Scanner;

class Meals{
    private int price;
    private int dessert_price = 100;
    private int packing = 20;
    private boolean veg;
    private boolean dessert = false;

    public Meals(boolean veg){
        this.veg = veg;
        if(this.veg){
            this.price = 200;
        }else{
            this.price = 400;
        }
    }

    public void getPrice(){
        System.out.println(price);
    }

    public void includeDessert(boolean dessert){
        if(dessert){
            this.dessert=true;
            this.price+=this.dessert_price;
        }
    }

    public void placeOrder(){
        System.out.println("Order Placed Successfully.");
        System.out.println("Bill : ");
        this.price+=this.packing;
        if(this.veg){
            System.out.println("Meals (veg) : "+200);
        }else{
            System.out.println("Meals (non-veg) : "+400);
        }
        if(this.dessert){
            System.out.println("Dessert Price : "+this.dessert_price);
        }
        System.out.println("Packaging Charge : "+this.packing);
        System.out.println("Total amount : "+this.price);
    }
}
public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Veg/Non-Veg ? : (Enter V/N)");
        String in = s.nextLine();
        if(in.length()!=1 || ((in.charAt(0)!='V') && (in.charAt(0)!='N'))){
            System.out.println("Invalid !");
            s.close();
            return;
        }
        boolean pass = false;
        if(in.charAt(0)=='V'){
            pass = true;
        }
        Meals Buy = new Meals(pass);
        System.out.println("Do you need to include Dessert ? : (Enter Y/N)");
        String add = s.nextLine();
        if(add.length()!=1 || ((add.charAt(0)!='Y') && (add.charAt(0)!='N'))){
            System.out.println("Invalid !");
            s.close();
            return;
        }
        boolean ad = false;
        if(add.charAt(0)=='Y'){
            ad  = true;
        }
        Buy.includeDessert(ad);
        Buy.placeOrder();
        s.close();
    }
}