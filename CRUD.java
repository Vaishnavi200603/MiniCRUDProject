package JDBC;

import java.sql.*;
import java.util.Scanner;


public class MiniCURDProject {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false", "root", "student");
        Statement st = con.createStatement();

        while(true){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("SELECT AN OPTION : ");
            System.out.println("""
                1. Select
                2. Insert
                3. Update
                4. Delete
                5. Exit
                """);
            int num = sc.nextInt();

            switch (num) {
                case 1 -> {
                    ResultSet rs = st.executeQuery("Select * from language");
                    while (rs.next()) {
                        System.out.print(rs.getString(1));
                        System.out.print(" | " + rs.getString(2));
                        System.out.print(" | " + rs.getString(3));
                        System.out.println();
                    }
                    rs.close();
                }
                case 2 -> {
                    try{
                        PreparedStatement ps = con.prepareStatement("Insert into language values(?,?,?)");
                        System.out.print("Enter id : ");
                        int id = sc.nextInt();
                        System.out.print("Enter Language : ");
                        sc.nextLine();
                        String lang = sc.nextLine();
                        System.out.print("Enter Date and time : ");
                        String lastUpdate = sc.nextLine();  //2023-12-14 19:13:33

                        ps.setString(1, String.valueOf(id));
                        ps.setString(2, lang);
                        ps.setString(3, lastUpdate);

                        int insertNum = ps.executeUpdate();
                        if (insertNum > 0) System.out.println("Successfully Inserted");
                        else System.out.println("Insertion Failed");
                    }
                    catch(Exception e){
                        System.out.println("Wrong, TRY AGAIN");
                    }

                }
                case 3 -> {
                    try{
                        System.out.println("Enter an Update Query : ");
                        sc.nextLine();
                        String q = sc.nextLine(); //Update language set name='English(American)' where language_id = '1';
                        int updateNum = st.executeUpdate(q);
                        if (updateNum > 0) System.out.println("Successfully Update");
                        else System.out.println("Updation Failed");
                    }
                    catch(Exception e){
                        System.out.println("Wrong Query, TRY AGAIN");
                    }
                }
                case 4 -> {
                    try{
                        System.out.println("Enter a Delete Query : ");
                        sc.nextLine();
                        String d = sc.nextLine();   //Delete from language where language_id = '2';
                        int deleteNum = st.executeUpdate(d);
                        if (deleteNum > 0) System.out.println("Successfully Deleted");
                        else System.out.println("Deletion Failed");
                    }
                    catch(Exception e){
                        System.out.println("Wrong Query, TRY AGAIN");
                    }
                }
                default -> System.exit(0);
            }
        }
    }
}
