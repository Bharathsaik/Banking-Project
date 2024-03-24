import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Bankbalance {
    Scanner sc=new Scanner(System.in);
    private String accountnumber;
    private String pin;
    private int balance;
    public void getBankBalance(){
        System.out.println("in bb");

//        while(true){
        System.out.println("Enter Your Account Number (11 digit number) : ");
        accountnumber=sc.nextLine();
//            if(accountnumber.substring(1,8)!="6281639"){
//                System.out.println("You have entered wrong account number!!!\nRe-Enter the Account Number.....");
//                continue;
//            }
//            else{
//                break;
//            }
//        }

        System.out.println("Enter Your Pin : ");
        pin=sc.nextLine();
        try{
            String sql="select balance from customerbalance where accountnumber=? and pin=?";
            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_DataBase","postgres","1204");
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,accountnumber);
            st.setString(2,pin);
            ResultSet rs=st.executeQuery();
            rs.next();
            balance=rs.getInt("balance");
            System.out.println("Your Account Balance : " + balance);
            con.close();
        }
        catch (Exception e){
            System.out.println("Exception : "+e);
        }
        finally {
            System.out.println("JDBC Bank.........");
        }
    }
}
