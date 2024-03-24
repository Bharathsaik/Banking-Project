import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CreateAccount {
    Scanner sc=new Scanner(System.in);
    private String name;
    private String accountnumber;
    final String firstPartOfTheAccountNumber="6281639";
    private String ifsc;
    final String firstPartOfTheIfscCode="IFSC00";
    private String contact;
    private String email;
    private String accounttype;
    private int balance=0;
    private String pin;

    public void createNewBankAccount(){
        System.out.println("Enter Your Name : ");
        name=sc.nextLine();
        System.out.println("Enter Your Contact Details : ");
        contact=sc.nextLine();
        System.out.println("Enter Your Email : ");
        email=sc.nextLine();
        System.out.println("Enter Account Type : (savings or current) ");
        accounttype=sc.nextLine();
        if(accounttype.equals("savings")){
            System.out.println("First Minimum deposite is 1000/- only");
            balance=1000;
        }
        else{
            System.out.println("First Minimum deposite is 10,000/- only");
            balance=10000;
        }

        //assiging account number and Ifsc code using mobile number
        accountnumber=firstPartOfTheAccountNumber + contact.substring(contact.length()-4,contact.length());
        //System.out.println("accountNumber : "+accountNumber);
        ifsc=firstPartOfTheIfscCode + contact.substring(contact.length()-4,contact.length());

        System.out.println("Do you want to set your own pin (or) will use randomly generated pin\nif you want to generate your own pin enter 1\nelse enter 0");
        int pin_decision=sc.nextInt();
        sc.nextLine();
        if(pin_decision==1){
            while(true){
                System.out.println("Enter your desired pin (4 digit pin only) : ");
                pin=sc.nextLine();
                if(pin.length() == 4){
                    break;
                }
                else{
                    continue;
                }
            }
        }
        else{
            pin=String.valueOf((int)(Math.random()*9000)+1000);
        }

        try{
            String sql="insert into customerdatabase values(?,?,?,?,?,?,?)";
            String sql_b="insert into customerbalance values(?,?,?)";
            Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Bank_DataBase","postgres","1204");
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,accountnumber);
            st.setString(2,ifsc);
            st.setString(3,contact);
            st.setString(4,email);
            st.setString(5,accounttype);
            st.setInt(6,balance);
            st.setString(7,name);
            st.execute();
            PreparedStatement st1=con.prepareStatement(sql_b);
            st1.setString(1,accountnumber);
            st1.setString(2,pin);
            st1.setInt(3,balance);
            st1.execute();
            con.close();
        }
        catch (Exception e){
            System.out.println("error"+e);
        }
        finally {
            System.out.println("name : "+name+"\naccountNumber : "+accountnumber+"\nIFSC : "+ifsc+"\ncontact : "+contact+"\nemail : "+ email+"\naccountType : "+accounttype+"\nbalance : "+balance+"\npin : "+pin);
        }
    }
}
