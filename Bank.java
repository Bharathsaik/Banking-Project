import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        int finish=1;
        Scanner sc=new Scanner(System.in);
        System.out.println("Welcome to JDBC bank");
        System.out.println("Select The Service : ");
        System.out.println("\n\n1. Create a New Bank Account (CNB)\n2. Know Your Bank Account Details (BAD)\n3. Fetching Your Bank Balance (BB)\n4. Deposite Money (DM)\n5. Withdraw Money (WM)\n6. Done");
        System.out.println("Enter the service you need (Based on given codes): ");
        String service=sc.nextLine().toUpperCase();
        switch (service){
            case "CNB":
                CreateAccount cnb=new CreateAccount();
                cnb.createNewBankAccount();
                break;
            case "BAD":
                Bankdetails bd=new Bankdetails();
                bd.getBankDetails();
                break;
            case "BB":
                Bankbalance bb=new Bankbalance();
                bb.getBankBalance();
                break;
            case "DM":
                Depositemoney dm=new Depositemoney();
                dm.depositeMoney();
                break;
            case "WM":
                Withdrawmoney wm=new Withdrawmoney();
                wm.withDrawMoney();
                break;
        }
    }
}
