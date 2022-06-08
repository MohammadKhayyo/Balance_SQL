import java.sql.*;
import java.util.Scanner;

public class balance {
    private static int balance;
    public static int StringToInt(String tmp) {
    	int x=-1;
    	 try {
             x= Integer.parseInt(tmp);
         } catch (NumberFormatException e) {
             System.out.println("start over and enter an number when asking for amount");
         }
    	 return x;
    }
    public static int optionForMenu() {
        Scanner scanner = new Scanner(System.in);
        String tmp;
        int option;
        System.out.print("What do you want to do?\n");
        System.out.print("1. Enter new expense\n");
        System.out.print("2. Watch expenses\n");
        System.out.print("3. Enter new income\n");
        System.out.print("4. Watch incomes\n");
        System.out.print("5. Show account balance\n");
        System.out.print("6. Exit\n");
        tmp=scanner.next();
        try {
            option=Integer.parseInt(tmp);
            return option;
        } catch (NumberFormatException e) {
            System.out.println("Enter a number");
        }
        return 7;
    }
    public static void menu(){
  
       for(;;) {
            int option = optionForMenu();
            switch (option) {
                case 1:
                    newExpense();
                    break;
                case 2:
                    watchExpenses();
                    break;
                case 3:
                    newIncomes();
                    break;
                case 4:
                    watchIncomes();
                    break;
                case 5:
                    showBalance();
                    break;
                case 6:
                    return;
                   
                default:
                    System.out.println("Enter number in the range");
                    break;
            }
        }
    }
    private static void newExpense(){
        String date,kindOfPayment,description,tmp;
        int amount=0;
        Scanner tmp1 = new Scanner(System.in);
        Scanner tmp2 = new Scanner(System.in);
        System.out.println("Enter in what date was expense made: ");
        date= tmp1.nextLine();
        System.out.println("Enter the amount:");
        tmp=tmp2.next();
            amount= StringToInt(tmp);
            if(amount==-1) return;
        System.out.println("Enter what kind of payment was used: ");
        kindOfPayment= tmp1.nextLine();
        System.out.println("Enter where the expense was spent: ");
        description= tmp1.nextLine();
        if (balance-amount<0){
            String tmp_for_check;
            System.out.println("the balance is negative, do you want to delete the exchange? [y/n]");
            tmp_for_check= tmp1.next();
            if(tmp_for_check.equals("y")||tmp_for_check.equals("yes")){
                balance=balance-amount;
                DB.deleteBalance();
                DB.addBalance(balance);
                DB.insertExpenses(date,amount,kindOfPayment,description);
            }else {
                System.out.println("the last exchange was deleted");
                return;
            }
        }else {
            balance=balance-amount;
            DB.deleteBalance();
            DB.addBalance(balance);
            DB.insertExpenses(date,amount,kindOfPayment,description);
        }
    }
    private static void watchExpenses(){
        String tmp , date ,  kindOfPayment  , description;
        int amount = 0,option;
        System.out.print("What expenses do you want to know about ?");
        System.out.println();
        System.out.print("chose what 1 for date , 2 if the amount is lower than ,3 if the amount is greater than,  4 for the type of payment , 5 for where the expenses was made");
        System.out.println();
        Scanner tmp_2 = new Scanner(System.in);
        Scanner tmp_1 = new Scanner(System.in);
        tmp=tmp_2.next();
        try {
            option= Integer.parseInt(tmp);
        } catch (NumberFormatException e) {
            System.out.println("start over and enter an number when asking for what to chose ");
            return;
        }
        switch (option) {
            case 1 : {
                System.out.println("what date do you want to see ");
                date = tmp_1.nextLine();
                DB.printExpenses("date", date);
                break;
            }
            case 2 : {
            	System.out.println("Enter amount");
            	amount= StringToInt(tmp);
            	if(amount==-1) return;
                DB.printExpensesIntLess("amount", amount);
                break;
            }
            case 3 : {
            	System.out.println("Enter amount");
                amount= StringToInt(tmp);
                if(amount==-1) return;
                DB.printExpensesIntGreater("amount", amount);
                break;
            }
            case 4 : {
                System.out.println("what kind of Payment do you want to see");
                kindOfPayment = tmp_1.nextLine();
                DB.printExpenses("kindOfPayment", kindOfPayment);
                break;
            }
            case 5 : {
                System.out.println("what description do you want to see");
                description = tmp_1.nextLine();
                DB.printExpenses("description", description);
                break;
            }
            default : System.out.println("You must Enter a number between one and five");
            break;
        }
    }
    private static void newIncomes(){
        String tmp, date ,  kindOfPayment  , description;
        int amount;
        Scanner tmp_1 = new Scanner(System.in);
        Scanner tmp_2 = new Scanner(System.in);
        System.out.println("Enter in what date was Incomes made:");
        date= tmp_1.nextLine();
        System.out.println("enter the amount:");
        tmp=tmp_2.next();
            amount=StringToInt(tmp);
            if(amount==-1) return;
        System.out.println("Enter what kind of payment was used:");
        kindOfPayment= tmp_1.nextLine();
        System.out.println("Enter where the expense was spent:");
        description= tmp_1.nextLine();
        balance=balance+amount;
        DB.deleteBalance();
        DB.addBalance(balance);
        DB.insertIncome(date,amount,kindOfPayment,description);
    }
    private static void watchIncomes(){
        String tmp , date ,  kindOfPayment  , description;
        int amount = 0,option;
        System.out.print("What expenses do you want to know about ?");
        System.out.println();
        System.out.print("chose what 1 for date , 2 if the amount is lower than ,3 if the amount is greater than,  4 for the type of payment , 5 for where the expenses was made");
        System.out.println();
        Scanner tmp_2 = new Scanner(System.in);
        Scanner tmp_1 = new Scanner(System.in);
        tmp=tmp_2.next();
        try {
            option= Integer.parseInt(tmp);
        } catch (NumberFormatException e) {
            System.out.println("start over and enter an number when asking for what to chose ");
            return;
        }
        switch (option) {
            case 1 : 
                System.out.println("what date do you want to see ");
                date = tmp_1.nextLine();
                DB.printIncome("date", date);
                break;
            
            case 2 : 
            	System.out.println("Enter amount");
            	amount= StringToInt(tmp);
            	if(amount==-1) return;
                DB.printIncomeIntLess("amount", amount);
                break;
            
            case 3 : 
            	System.out.println("Enter amount");
                amount= StringToInt(tmp);
                if(amount==-1) return;
                DB.printIncomeIntGreater("amount", amount);
                break;
            
            case 4 : 
                System.out.println("what kind of Payment do you want to see");
                kindOfPayment = tmp_1.nextLine();
                DB.printIncome("kindOfPayment", kindOfPayment);
                break;
            
            case 5 : 
                System.out.println("what description do you want to see");
                description = tmp_1.nextLine();
                DB.printIncome("description", description);
                break;
            
            default : System.out.println("You must Enter a number between one and five");
        }
    }
    private static void showBalance(){
        DB.printBalance();
        System.out.println("- is the balance");
    }
    public static void main(String[] args) {
    	 menu();
	}
}
class DB {
    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:tmp.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        finally {
            return connection;
        }
    }
    public static void insertExpenses(String date, int amount ,String typeOfPayment,String description) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "insert into expenses values(" + "\"" + date + "\"," + "\"" + amount  + "\" ," + "\"" + typeOfPayment + "\"," + "\"" + description + "\") ;";
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printExpenses(String type,String str) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "select * from expenses where "+type +" = "+ "\"" + str+"\";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printExpensesIntLess(String type,int x) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql=null;
                 sql = "select * from expenses where "+type +" < "+ "" + x +";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printExpensesIntGreater(String type,int x) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql=null;
                sql = "select * from expenses where "+type +" > "+ "" + x +";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void insertIncome(String date, int amount,String typeOfPayment,String description) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "insert into income values(" + "\"" + date + "\","+ "\""+amount+"\" ,"+"\"" + typeOfPayment + "\","+"\"" + description + "\");";
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printIncome(String type,String str) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "select * from income where "+type +" = "+ "\"" + str+"\";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printIncomeIntLess(String type,int x) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql =null;
               sql = "select * from income where "+type +" < "+ "" + x +";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printIncomeIntGreater(String type,int x) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql =null;
               sql = "select * from income where "+type +" > "+ "" + x +";";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                String s = resultSet.getString(1) + ", " + resultSet.getInt(2)+ ", " +resultSet.getString(3) + ", " +resultSet.getString(4) ;
                System.out.println(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addBalance(int balance) {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "insert into balance values(" + "" + balance +  ");";
            statement.executeUpdate(sql);
            connection.commit();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteBalance() {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement st = connection.createStatement();
            String sql = "DELETE FROM balance " + ";";
            st.executeUpdate(sql);
            connection.commit();
            st.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void printBalance() {
        try {
            Connection connection = connect();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            String sql = "select * from balance ;";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                int s = resultSet.getInt(1) ;
                System.out.print(s);
            }
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
}