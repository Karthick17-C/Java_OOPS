import java.util.*;

class User{
    private String name;
    private String id;
    private double paid_amount;

    public User(String name, String id){
        this.name = name;
        this.id = id;
        this.paid_amount = 0;
    }

    public void pay(double amount){
        this.paid_amount+=amount;
    }

    public double Amountpaid(){
        return paid_amount;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
}

class Split{

    private double remaining_amount;

    public Split(double amount){
        this.remaining_amount=amount;
    }

    public boolean payment(double amount){
        if(amount>remaining_amount){
            return false;
        }
        remaining_amount-=amount;
        return true;
    }

    public double getRemaining(){
        return remaining_amount;
    }

    public boolean isDone(){
        return remaining_amount==0;
    }

}

class Manager{

    private HashMap<String, User> users = new HashMap<>();
    private HashMap<String, String> passwords = new HashMap<>();
    private Split split;

    public void addUser(String id, String name, String pass){
        users.put(id, new User(id,name));
        passwords.put(id,pass);
    }

    public void createSplit(double amount){
        split = new Split(amount);
    }

    public void PaymentProcess(String id, String pass, double amount){
        User user = users.get(id);
        if(user==null){
            System.out.println("Not fount");
            return;
        }

        if(!pass.equals(passwords.get(id))){
            System.out.println("Wrong Password");
            return;
        }

        if(!split.payment(amount)){
            System.out.println("Invalid amount");
        }

        user.pay(amount);

        System.out.println(user.getName()+" has paid "+amount);
    }

    public void showBill(){
        System.out.println("\n--- Payment Status ---");
        for (User u : users.values()) {
            System.out.println(u.getName() + " paid: " + u.Amountpaid());
        }
        System.out.println("Remaining Bill: " + split.getRemaining());

        if(split.isDone()){
            System.out.println("Bill Settled");
        }else{
            System.out.println("Bill not Settled");
        }
    }

    public boolean isSettled(){
        return split.isDone();
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Manager manager = new Manager();
        System.out.println("Enter the no.of people :");
        int n = s.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter name for User " + i + ": ");
            String name = s.next();
            System.out.print("Set password: ");
            String pwd = s.next();

            manager.addUser(String.valueOf(i), name, pwd);
        }

        System.out.print("\nEnter total bill amount: ");
        double bill = s.nextDouble();
        manager.createSplit(bill);

        while(!manager.isSettled()){
            manager.showBill();

            System.out.println("Enter ID to pay: ");
            String id = s.next();
            System.out.println("Enter Password: ");
            String pass = s.next();
            System.out.println("Enter the amount: ");
            double amount = s.nextDouble();

            manager.PaymentProcess(id,pass,amount);

            if(!manager.isSettled()){
                System.out.println("Need to Continue ? (yes/no)");
                if(!s.next().equalsIgnoreCase("yes")){
                    break;
                }
            }
        }
        System.out.println("Final Status : ");
        manager.showBill();
        s.close();
    }
}