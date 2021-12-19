import java.sql.*;

public class Reports {
    /**
     * Displays the revenue and expenditure of each book within the database, as well as the total of all sales and expenditures
     * @param statement Used to execute SQL queries
     */
    public static void SalesExpenditure(Statement statement){
        try {
            ResultSet resultSet = statement.executeQuery("select title, num_available*price, num_sold*price*sale_percentage from book");
            System.out.println("Title\tSales\tExpenditure");
            while (resultSet.next())
                System.out.printf(resultSet.getString("title") + ":\t" + resultSet.getFloat(2) + "\t" + resultSet.getFloat(3) + "\n");

            resultSet = statement.executeQuery("select sum(num_available*price), sum(num_sold*price*sale_percentage) from book");
            resultSet.next();
            System.out.printf("Total:\t" + resultSet.getFloat(1) + "\t" + resultSet.getFloat(2) + "\n");

        } catch (Exception sqle){
            System.out.println("Exception: " + sqle);
        }
    }

    /**
     * Displays the number of books sold, and total revenue of, each genre within the database
     * @param statement Used to execute SQL queries
     */
    public static void SalesPerGenre(Statement statement){
        try{
            ResultSet resultSet = statement.executeQuery("select genre, sum(num_sold), sum(num_sold*price) from book group by genre");
            while(resultSet.next()) System.out.printf(resultSet.getString("genre") + "\t"
                    + resultSet.getInt(2) + "\t" + resultSet.getFloat(3) + "\n");
        } catch (Exception sqle){
            System.out.println("Exception: " + sqle);
        }
    }

    /**
     * Displays the number of books sold, and total revenue of, each author with at least one book in the database
     * @param statement Used to execute SQL queries
     */
    public static void SalesPerAuthor(Statement statement){
        try{
            ResultSet resultSet = statement.executeQuery("select Author, sum(num_sold), sum(num_sold*price) from book group by author");
            while(resultSet.next()) System.out.printf(resultSet.getString("author") + "\t"
                    + resultSet.getInt(2) + "\t" + resultSet.getFloat(3) + "\n");
        } catch(Exception sqle){
            System.out.println("Exception: " + sqle);
        }
    }

}
