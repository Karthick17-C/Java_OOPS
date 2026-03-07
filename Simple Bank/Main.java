import java.util.ArrayList;
class Bank{
    private ArrayList<Account> acc = new ArrayList<>();

    public Account createAccount(User user){
        Account a = new Account(user);
        acc.add(a);
        user.setAccount(a);
        return a;
    }

    public void transfer(Account from, Account to, double amount){
        String result =  from.withdraw(amount);
        if(result.equals("Done")){
            to.credit(amount);
            from.debit(amount);
        }else{
            System.out.println("Transaction Declined");
        }
    }   
}
class User{
    private String name;
    private Account account;

    public User(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public void setAccount(Account account){
        this.account = account;
    }

    public void statement(){
        if(account!=null){
            account.printStatement();
        }else{
            System.out.println("Account not found");
        }
    }
}
class Account{
    private String Acc_no;
    private double balance;
    private User user;
    private ArrayList<String> transactions = new ArrayList<>();

    public Account(User user){
        this.user = user;
        Acc_no = "RBI"+System.nanoTime();
    }

    public String getAcc(){
        return Acc_no;
    }

    public void deposit(double amount){
        balance+=amount;
        transactions.add("Deposit:"+amount);
    }

    public void credit(double amount){
        balance+=amount;
        transactions.add("Credited:"+amount);
    }

    public void debit(double amount){
        transactions.add("Debited:"+amount);
    }

    public void getBalance(){
        System.out.println("Balance : "+balance);
    }

    public String withdraw(double amount){
        if(balance >= amount){
            balance-=amount;
        }else{
            return "Insufficient balance";
        }
        return "Done";
    }

    public void printStatement(){
        System.out.println("Account No : "+Acc_no);
        System.out.println("User: "+user.getName());
        System.out.println("Transcation logs :");
        for(String i : transactions){
            System.out.println("   "+i);
        }
        System.out.println("Balance : "+balance);
        System.out.println("-----------------------");
    }

}
public class Main {
    public static void main(String[] args) {
        Bank b = new Bank();
        User no1 = new User("Karthik");
        User no2 = new User("Dhoni");
        Account a1 =  b.createAccount(no1);
        Account a2 =  b.createAccount(no2);
        a1.deposit(1000);
        a2.deposit(1000);
        b.transfer(a1, a2, 500);
        no1.statement();
        no2.statement();
    }
}