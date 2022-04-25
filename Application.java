package jdbcapp.ExamJDBC;

import java.sql.*;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        int choose;
        Scanner scanner = new Scanner(System.in);
        do {
            Menu();
            choose = Integer.parseInt(scanner.nextLine());
            switch (choose){
                case 1:
                    addEmployee();
                    break;
                case 2:
                    getEmployeeByName();
                    break;
                case 3:
                    getAllEmployee();
                    break;
                case 4:
                    editEmployeeDetail();
                    break;
                case 5:
                    deleteEmployee();
                    break;
                case 6:
                    closeDatabase();
                    System.out.println("Thoat!!!!");
                    break;
                default:
                    System.out.println("Fail!!! Please chosse again");
                    break;
            }
        }while (choose!=6);
    }

    private static void addEmployee() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id of emplolee: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter empno of employee: ");
        String empno = scanner.nextLine();
        System.out.println("Enter name of employee: ");
        String name = scanner.nextLine();
        System.out.println("Enter age of employee: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter address of employee: ");
        String address = scanner.nextLine();

        Connection connection = SQLServerConnection.getSQLServerConnection();
        String query = "insert into employee values(?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.setString(2,empno);
        preparedStatement.setString(3,name);
        preparedStatement.setInt(4,age);
        preparedStatement.setString(5,address);
        preparedStatement.execute();
        System.out.println("Query inserted: Id: "+id + ", empNo: " +empno+", name: "+name+", age: "+age+", address: "+address);
    }
    private static void getEmployeeByName() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of employee: ");
        String name = scanner.nextLine();

        Connection connection = SQLServerConnection.getSQLServerConnection();
        String query = "select * from employee where name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1,name);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("---------------------------------");
            System.out.println("Emp id: "+ resultSet.getInt(1));
            System.out.println("Emp No: "+ resultSet.getString(2));
            System.out.println("Name: "+ resultSet.getString(3));
            System.out.println("age: "+ resultSet.getInt(4));
            System.out.println("address: "+ resultSet.getString(5));
        }
    }
    private static void getAllEmployee() throws SQLException, ClassNotFoundException {
        Connection connection = SQLServerConnection.getSQLServerConnection();

        String query = "select * from employee ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("---------------------------------");
            System.out.println("Emp id: "+ resultSet.getInt(1));
            System.out.println("Emp No: "+ resultSet.getString(2));
            System.out.println("Name: "+ resultSet.getString(3));
            System.out.println("age: "+ resultSet.getInt(4));
            System.out.println("address: "+ resultSet.getString(5));
        }
    }

    private static void editEmployeeDetail() throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter empNo : ");
        String empNo = scanner.nextLine();
        System.out.println("Enter name : ");
        String name = scanner.nextLine();
        System.out.println("Enter age : ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter address : ");
        String address = scanner.nextLine();
        System.out.println("Enter id of employee to want edit: ");
        int id = Integer.parseInt(scanner.nextLine());
        Connection connection = SQLServerConnection.getSQLServerConnection();
        String query = "update employee set empno = ?, name = ?, age = ?, address = ? where empid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,empNo);
        preparedStatement.setString(2,name);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4,address);
        preparedStatement.setInt(5,id);
        preparedStatement.execute();
        System.out.println("Edit Success");
    }

    private static void deleteEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Enter name of employee to want delete: ");
        Connection connection = SQLServerConnection.getSQLServerConnection();
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String query = "delete  from employee where name like ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        preparedStatement.execute();
        System.out.println("Deleted Success");
    }

    private static void closeDatabase() throws SQLException, ClassNotFoundException {
        Connection connection = SQLServerConnection.getSQLServerConnection();
        connection.close();
    }

    static void Menu(){
        System.out.println("=========Menu==========");
        System.out.println("1. Add employee");
        System.out.println("2. Get employee by name");
        System.out.println("3. Get all emplyee");
        System.out.println("4. Edit employee detail");
        System.out.println("5. Delete employee");
        System.out.println("6. Exit!!!");
        System.out.println("Choose: ");
    }
}
