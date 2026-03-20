import java.util.Scanner;

class Meals{
    protected int price;
    private int dessert_price = 100;
    private int packing = 20;
    private boolean veg;
    protected boolean dessert = false;

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

class SpecialMeals extends Meals{
    private int Special = 300;

    public SpecialMeals(boolean veg){
        super(veg);
        price+=Special;
        includeDessert(true);
    }

    @Override
    public void placeOrder(){
        System.out.println("Special Order Received.");
        System.out.println("Extra Charges for Special Meals : "+Special);
        super.placeOrder();
    }
}
public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Specail/Normal Meals? : (Enter S/N)");
        String spec = s.nextLine();
        if(spec.length()!=1 || ((spec.charAt(0)!='S') && (spec.charAt(0)!='N'))){
            System.out.println("Invalid !");
            s.close();
            return;
        }
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
        Meals Buy;
         if(spec.charAt(0)=='S'){
            Buy = new SpecialMeals(pass);
        }else{
            Buy = new Meals(pass);
        }
        if(spec.charAt(0)=='N'){
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
        }
        Buy.placeOrder();
        s.close();
    }
}