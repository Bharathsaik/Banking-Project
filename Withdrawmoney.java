import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Withdrawmoney {
    Scanner sc=new Scanner(System.in);
    private String accountnumber;
    private String pin;
    private int requiredMoney;
    private int balance;

    public void withDrawMoney(){
        System.out.println("Enter your account number : ");
        accountnumber=sc.nextLine();
        System.out.println("Enter your pin number : ");
        pin=sc.nextLine();
        System.out.println("Enter Amount to withdraw : ");
        requiredMoney=sc.nextInt();
        try{
//            String sql="select balance from customerbalance where accountnumber=? and pin=?";
//            String sql_update="update customerbalance set balance=? where accountnumber=? and pin=?";
            String sql="select balance from customerbalance where pin=?";
            String sql_update="update customerbalance set balance=? where pin=?";
            String sql_maindb="update customerdatabase set balance=? where accountNumber=?";
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_DataBase","postgres","1204");
            PreparedStatement st=con.prepareStatement(sql);

//            st.setString(1,accountnumber);
            st.setString(1,pin);
            ResultSet rs=st.executeQuery();

            rs.next();

            balance = rs.getInt("balance");



            if(balance < requiredMoney){
                System.out.println("Insufficient Balance");
            }
            else{
                balance=balance-requiredMoney;
                PreparedStatement st_update = con.prepareStatement(sql_update);
                st_update.setInt(1,balance);
//                st_update.setString(2,accountnumber);
                st_update.setString(2,pin);
                st_update.execute();

                PreparedStatement st_sql_maindb=con.prepareStatement(sql_maindb);
                st_sql_maindb.setInt(1,balance);
                st_sql_maindb.setString(2,accountnumber);
                st_sql_maindb.execute();

                System.out.println(requiredMoney + " is successfully withdrawn");
                System.out.println("your current balance is : "+balance);
            }
            con.close();
        }
        catch (Exception e){
            System.out.println("Exception : "+e);
        }
        finally {
            System.out.println("JDBC Bank.....");
        }
    }
}
