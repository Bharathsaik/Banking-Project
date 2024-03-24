import javax.xml.transform.Source;
import java.sql.*;
import java.util.Scanner;

public class Depositemoney {
    Scanner sc=new Scanner(System.in);
    private String accountnumber;
    private int balance;
    private int depositemoney;
    public void depositeMoney(){
        System.out.println("in dm");
        System.out.println("Enter Your Bank Account Number : ");
        accountnumber=sc.nextLine();
        System.out.println("Enter the Amount you want to deposite : ");
        depositemoney=sc.nextInt();
        try{
            String sql="select balance from customerdatabase where accountnumber=?";
            String sql_cdb="update customerdatabase set balance=? where accountnumber=?";
            String sql_cb="update customerbalance set balance=? where accountnumber=?";
            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_DataBase","postgres","1204");
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,accountnumber);
            ResultSet rs=st.executeQuery();
            rs.next();
            balance = rs.getInt("balance");
            int depositemoneyintodb=depositemoney+balance;
            PreparedStatement st_cdb=con.prepareStatement(sql_cdb);
            st_cdb.setInt(1,depositemoneyintodb);
            st_cdb.setString(2,accountnumber);
            st_cdb.execute();
            PreparedStatement st_cb=con.prepareStatement(sql_cb);
            st_cb.setInt(1,depositemoneyintodb);
            st_cb.setString(2,accountnumber);
            st_cb.execute();
            con.close();
            System.out.println(depositemoney + " is Successfully Deposited!!!!");
            System.out.println("Current Balance is " + depositemoneyintodb);
        }
        catch (Exception e){
            System.out.println("Exception : " + e);
        }
    }
}
