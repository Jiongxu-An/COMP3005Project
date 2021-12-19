import java.sql.*;
import java.util.*;
import java.io.*;

public class JDBC {
    static final int port = 8080;
    static final String db = "postgres";
    static final String user = "postgres";
    static final String password = "password";
    static final String url = "jdbc:postgresql://localhost:" + port + "/" + db;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice, subChoice, search, basketChoice, reportChoice;
        //String input;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1. Create Table (Insert DDL)");
            System.out.println("2. Insert Sample File");
            System.out.println("3. Enter Bookstore as User");
            System.out.println("4. Enter Bookstore as Admin");
            System.out.println("0. Exit Program\n");
            System.out.print("Enter Your Choice: ");

            choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    try {
                        File file = new File("SQL/DDL.sql");
                        Scanner reader = new Scanner(file);
                        String ddl = "";
                        while (reader.hasNextLine()){
                            String data = reader.nextLine();
                            ddl += data;
                        }
                        try (Connection conn = DriverManager.getConnection(url, user, password);
                             Statement stmt = conn.createStatement();
                        ){
                            try {
                                stmt.executeUpdate(ddl);
                                System.out.println("Table Created");
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } catch (FileNotFoundException e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                    try {
                        File file = new File("SQL/Sample.sql");
                        Scanner reader = new Scanner(file);
                        String sample = "";
                        while (reader.hasNextLine()){
                            String data = reader.nextLine();
                            sample += data;
                        }
                        try (Connection conn = DriverManager.getConnection(url, user, password);
                             Statement stmt = conn.createStatement();
                        ){
                            try {
                                stmt.executeUpdate(sample);
                                System.out.println("Database data initialized");
                            } catch (SQLException e) {
                                System.out.println(e);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } catch (FileNotFoundException e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    do {
                        System.out.println("\nUser Menu");
                        System.out.println("1. Search Books");
                        System.out.println("2. Enter Basket");
                        System.out.println("3. Place Order");
                        System.out.println("0. Return to Previous Menu\n");
                        System.out.print("Enter Your Choice: ");

                        subChoice = scanner.nextInt();

                        switch (subChoice) {
                            case 1:
                                do {
                                    System.out.println("\n1. Search by Title");
                                    System.out.println("2. Search by Author");
                                    System.out.println("3. Search by ISBN");
                                    System.out.println("4. Search by Genre");
                                    System.out.println("0. Return to Previous Menu\n");
                                    System.out.print("Enter Your Choice: ");

                                    search = scanner.nextInt();

                                    switch (search) {
                                        case 1:
                                            try (Connection conn = DriverManager.getConnection(url , user, password);
                                            ){
                                                scanner.nextLine();
                                                System.out.print("Enter Book Title: ");
                                                String title = scanner.nextLine();
                                                String query = "select isbn, title from book where lower (title) like lower (?)";
                                                PreparedStatement pstmt = conn.prepareStatement(query);
                                                pstmt.setString(1, "%" + title + "%");
                                                try {
                                                    ResultSet rs = pstmt.executeQuery();
                                                    System.out.println("Matching Result:");
                                                    while (rs.next()) {
                                                        System.out.println(rs.getLong("isbn") + " " + rs.getString("title"));
                                                    }
                                                } catch (SQLException e){
                                                    System.out.println(e);
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                            break;
                                        case 2:
                                            try (Connection conn = DriverManager.getConnection(url, user, password);
                                            ){
                                                scanner.nextLine();
                                                System.out.print("Enter Author Name: ");
                                                String author = scanner.nextLine();
                                                String query = "select isbn, title, author from book where lower (author) like lower (?)";
                                                PreparedStatement pstmt = conn.prepareStatement(query);
                                                pstmt.setString(1, "%" + author + "%");
                                                try {
                                                    ResultSet rs = pstmt.executeQuery();
                                                    System.out.println("Matching Result:");
                                                    while (rs.next()) {
                                                        System.out.println(rs.getLong("isbn") + " " + rs.getString("title") + " " + rs.getString("author"));
                                                    }
                                                } catch (SQLException e){
                                                    System.out.println(e);
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                            break;
                                        case 3:
                                            try (Connection conn = DriverManager.getConnection(url, user, password);
                                            ){
                                                scanner.nextLine();
                                                System.out.print("Enter ISBN: ");
                                                String isbn = scanner.nextLine();
                                                String query = "select isbn, title from book where isbn like ?";
                                                PreparedStatement pstmt = conn.prepareStatement(query);
                                                pstmt.setString(1, "%" + isbn + "%");
                                                try {
                                                    ResultSet rs = pstmt.executeQuery();
                                                    System.out.println("Matching Result:");
                                                    while (rs.next()) {
                                                        System.out.println(rs.getLong("isbn") + " " + rs.getString("title"));
                                                    }
                                                } catch (SQLException e){
                                                    System.out.println(e);
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                            break;
                                        case 4:
                                            try (Connection conn = DriverManager.getConnection(url, user, password);
                                            ){
                                                scanner.nextLine();
                                                System.out.print("Enter Genre: ");
                                                String genre = scanner.nextLine();
                                                String query = "select isbn, title, genre from book where lower (genre) like lower (?)";
                                                PreparedStatement pstmt = conn.prepareStatement(query);
                                                pstmt.setString(1, "%" + genre + "%");
                                                try {
                                                    ResultSet rs = pstmt.executeQuery();
                                                    System.out.println("Matching Result:");
                                                    while (rs.next()) {
                                                        System.out.println(rs.getLong("isbn") + " " + rs.getString("title") + " " + rs.getString("genre"));
                                                    }
                                                } catch (SQLException e){
                                                    System.out.println(e);
                                                }
                                            } catch (Exception e) {
                                                System.out.println(e);
                                            }
                                            break;
                                    }
                                } while (search != 0);
                                break;
                            case 2:
                                System.out.println("1. Add a Book to Basket");
                                System.out.println("2. Remove a Book from Basket");
                                System.out.println("0. Return to Previous Menu\n");
                                System.out.print("Enter your choice: ");

                                basketChoice = scanner.nextInt();

                                switch (basketChoice){
                                    case 1:
                                        scanner.nextLine();
                                        System.out.print("Enter ISBN to Add Book: ");
                                        String isbn = scanner.nextLine();
                                        try (Connection conn = DriverManager.getConnection(url, user, password);
                                             Statement stmt = conn.createStatement();
                                        ){
                                            int basket_id = 1000;
                                            try{
                                                String maxID = "select basket_id from basket where basket_id = (select max(basket_id) from basket)";

                                                ResultSet rs_id = stmt.executeQuery(maxID);
                                                while (rs_id.next()) {
                                                    basket_id = rs_id.getInt("basket_id") + 1;
                                                }
                                            } catch (SQLException e){
                                                System.out.println(e);
                                            }
                                            String query = "insert into basket values (?, ?)";
                                            PreparedStatement pstmt = conn.prepareStatement(query);
                                            pstmt.setInt(1, basket_id);
                                            pstmt.setString(2, isbn);
                                            try{
                                                pstmt.executeUpdate();
                                                System.out.println("Successfully added into basket");
                                            } catch (SQLException e){
                                                System.out.println(e);
                                            }
                                        } catch (Exception e){
                                            System.out.println(e);
                                        }
                                        break;
                                    case 2:

                                        try (Connection conn = DriverManager.getConnection(url, user, password);
                                             Statement stmt = conn.createStatement();
                                        ){
                                            // list all books in the basket
                                            boolean found = false;
                                            try {
                                                String basket = "select isbn from basket";
                                                ResultSet rs = stmt.executeQuery(basket);
                                                while (rs.next()) {
                                                    System.out.println(rs.getString("isbn"));
                                                    found = true;
                                                }
                                            } catch (SQLException e){
                                                System.out.println(e);
                                            }
                                            // Remove implementation
                                            if (found){
                                                scanner.nextLine();
                                                System.out.print("Enter the ISBN to remove a book from basket: ");

                                                String input = scanner.nextLine(); // user input ISBN
                                                try{
                                                    String query = "delete from basket where basket_id = (select basket_id from basket where isbn = ? limit 1);";
                                                    PreparedStatement pstmt = conn.prepareStatement(query);
                                                    pstmt.setString(1, input);
                                                    int deleted = pstmt.executeUpdate();
                                                    if (deleted == 0){
                                                        System.out.println("ISBN does not match with the books in the basket");
                                                    } else {
                                                        System.out.println("Successfully Removed");
                                                    }
                                                }catch (SQLException e){
                                                    System.out.println(e);
                                                }
                                            } else {
                                                System.out.println("The basket is empty");
                                            }

                                        } catch (Exception e){
                                            System.out.println(e);
                                        }
                                        break;
                                }
                                break;
                            case 3:
                                System.out.println("Place Order");
                                break;
                        }
                    } while (subChoice != 0);
                    break;
                case 4:
                    do {
                        System.out.println("\nAdmin Menu");
                        System.out.println("1. Display Report");
                        System.out.println("2. Add a book");
                        System.out.println("3. Remove a book");
                        System.out.println("0. Return to Previous Menu\n");
                        System.out.print("Enter Your Choice: ");

                        subChoice = scanner.nextInt();

                        switch (subChoice) {
                            case 1:
                                try (Connection connection = DriverManager.getConnection(url, user, password);
                                     Statement statement = connection.createStatement();){
                                    do {
                                        System.out.println("\n1. Sales vs Expenditures" +
                                                "\n2. Sales per Genre" +
                                                "\n3. Sales per Author" +
                                                "\n0. Return to Previous Menu" +
                                                "\nEnter Your Choice: ");

                                        reportChoice = scanner.nextInt();
                                        switch (reportChoice) {
                                            case 1:
                                                Reports.SalesExpenditure(statement);
                                                break;

                                            case 2:
                                                Reports.SalesPerGenre(statement);
                                                break;

                                            case 3:
                                                Reports.SalesPerAuthor(statement);
                                                break;
                                        }
                                    }   while (reportChoice != 0);
                                    break;

                                } catch(Exception sqle){
                                    System.out.println(sqle);
                                }
                                break;
                            case 2:
                                try (Connection conn = DriverManager.getConnection(url, user, password);
                                     Statement stmt = conn.createStatement();
                                ){
                                    scanner.nextLine();
                                    System.out.print("Enter ISBN: ");
                                    String isbn = scanner.nextLine();
                                    System.out.print("Enter Title: ");
                                    String title = scanner.nextLine();
                                    System.out.print("Enter Author name: ");
                                    String author = scanner.nextLine();
                                    System.out.print("Enter Price: ");
                                    float price = scanner.nextFloat();
                                    scanner.nextLine();
                                    System.out.print("Enter Publisher: ");
                                    String publisher = scanner.nextLine();
                                    System.out.print("Enter Genre: ");
                                    String genre = scanner.nextLine();
                                    System.out.print("Enter Number of Page: ");
                                    int page = scanner.nextInt();

                                    // check if inputted publisher exists
                                    String query = "select publisher_id from publisher where name = ?";
                                    PreparedStatement pstmt = conn.prepareStatement(query);
                                    pstmt.setString(1, publisher);
                                    try {
                                        ResultSet rs = pstmt.executeQuery();
                                        String publisher_id = "";
                                        while (rs.next()) publisher_id = (rs.getString("publisher_id"));
                                        // Generates the new ID
                                        if (publisher_id == ""){
                                            int newID = 0;
                                            try {
                                                String maxID = "select publisher_id from publisher where publisher_id = (select max(publisher_id) from publisher)";
                                                ResultSet rs_id = stmt.executeQuery(maxID);
                                                while (rs_id.next()) newID = rs_id.getInt("publisher_id") + 1;
                                            } catch (SQLException e){
                                                System.out.println(e);
                                            }
                                            try {
                                                String insertPublisher = "insert into publisher values (?, ?)";
                                                PreparedStatement pstmtPub = conn.prepareStatement(insertPublisher);
                                                pstmtPub.setInt(1, newID);
                                                pstmtPub.setString(2, publisher);
                                                pstmtPub.executeUpdate();
                                                System.out.println("New Publisher added");
                                            } catch (SQLException e){
                                                System.out.println(e);
                                            }
                                            publisher_id = Integer.toString(newID);
                                        }

                                        String insertBook = "insert into book values (?, ?, ?, ?, ?, ?, ?)";
                                        PreparedStatement pstmtBook = conn.prepareStatement(insertBook);
                                        pstmtBook.setString(1, isbn);
                                        pstmtBook.setString(2, publisher_id);
                                        pstmtBook.setString(3, title);
                                        pstmtBook.setString(4, author);
                                        pstmtBook.setString(5,genre);
                                        pstmtBook.setInt(6, page);
                                        pstmtBook.setFloat(7, price);
                                        try {
                                            pstmtBook.executeUpdate();
                                            System.out.println("New book added");
                                        } catch (SQLException e){
                                            System.out.println(e);
                                        }
                                    } catch (SQLException e){
                                        System.out.print(e);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 3:
                                try (Connection conn = DriverManager.getConnection(url, user, password);
                                     Statement stmt = conn.createStatement();
                                ){
                                    // list all books
                                    ResultSet rs = stmt.executeQuery("select isbn, title from book");
                                    System.out.println("ISBN          Title");
                                    while (rs.next()){
                                        System.out.println(rs.getString("isbn") + " " + rs.getString("title"));
                                    }
                                    // delete a book
                                    scanner.nextLine();
                                    System.out.print("Enter ISBN to remove: ");
                                    String isbn = scanner.nextLine();
                                    String query = "delete from book where isbn = ? ";

                                    PreparedStatement pstmt = conn.prepareStatement(query);
                                    pstmt.setString(1, isbn);
                                    try {
                                        int deleted = pstmt.executeUpdate();
                                        if (deleted == 0){
                                            System.out.println("ISBN does not exist in database");
                                        } else {
                                            System.out.println("Successfully Removed");
                                        }
                                    } catch (SQLException e){
                                        System.out.println(e);
                                    }
                                } catch (Exception e) {
                                    System.out.println(e);
                                }
                                break;
                            case 4:
                                System.out.println("4");
                                break;
                        }
                    } while (subChoice != 0);
                    break;
            }
        } while (choice != 0);
    }
}