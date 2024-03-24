import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Bankdetails {
    Scanner sc=new Scanner(System.in);
    private String name;
    private String accountnumber;
    private String ifsc;
    private String contact;
    private String email;
    private String accounttype;
    private int balance=0;
    public void getBankDetails(){
        System.out.println("in bd");
        System.out.println("Enter your name : ");
        String enteredName = sc.nextLine().toLowerCase();
        System.out.println("Enter your conntact details : ");
        String enteredContact = sc.nextLine();
        try{
            String sql="select * from customerdatabase where name=? and contact=?";
            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_DataBase","postgres","1204");
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,enteredName);
            st.setString(2,enteredContact);
            ResultSet rs=st.executeQuery();
            rs.next();
            name=rs.getString("name");
            accountnumber=rs.getString("accountNumber");
            ifsc=rs.getString("IFSC");
            contact=rs.getString("contact");
            email=rs.getString("email");
            accounttype=rs.getString("accountType");
            balance=rs.getInt("balance");
            con.close();
        }
        catch(Exception e){
            System.out.println("Exception : "+e);
        }
        finally {
            System.out.println("Name of Account Holder : " + name + "\nContact Details : " + contact + "\nAccount Number : " + accountnumber + "\nIFSC : " + ifsc + "\nEmail : " + email + "\nAccount Type : " + accounttype + "\nAccount Balance : " + balance);
        }
    }
}
