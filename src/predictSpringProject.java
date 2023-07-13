import java.util.Scanner;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;


public class predictSpringProject {
	
	public static String formatDate(String input_date) {
		//helper method that takes in a date in String form and formats it so
		//that it can be inserted into MySQL
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss aa");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			Date date = sdf.parse(input_date);
			String formatted_date = formatter.format(date);
			return formatted_date;
		} catch (ParseException e) {
			try {
				Date date = sdf2.parse(input_date);
				String formatted_date = formatter.format(date);
				return formatted_date;
			} catch (ParseException p) {
				System.out.println(p);
				return "ERROR";
			}
		}
	}

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter file name: "); // Product_feed.tsv
		String filename = s.nextLine();
		System.out.println(filename);
		String DB_NAME = "`predictspringproject`";
		String TABLE_NAME = "`products`";
		try {
			Class.forName("com.mysql.jdbc.Driver"); //load the JDBC driver
			//establish connection to database:
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/predictspringproject", "root", "1:zE=EK4vKCMYNk32kW>J7a?xQ_jC*");
			Statement stmt2 = connection.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM products");
			ResultSetMetaData rsmd = rs2.getMetaData();
			int ncols = rsmd.getColumnCount();
			//get table columns and their data types
			String[] coltypes = new String[ncols];
			for(int i = 1; i < ncols + 1; i++) {
				System.out.println(rsmd.getColumnName(i) + " " + rsmd.getColumnTypeName(i));
				coltypes[i-1] = rsmd.getColumnTypeName(i); //record columns and their types
			}
			System.out.println("Number of cols: " + ncols);
			try(BufferedReader bfr = new BufferedReader(new FileReader(filename))){
				String header = bfr.readLine(); //read header from line
				String[] header_split = header.split("\t"); //split line based on tab
				String insert_statement = "INSERT INTO " + DB_NAME + "." + TABLE_NAME + " (";
				for(int i = 0; i < ncols; i++) {
					if(i != ncols - 1) {
						insert_statement = insert_statement + "`" + header_split[i] + "`" + ", ";
					} else {
						insert_statement = insert_statement + "`" + header_split[i] + "`";						
					}
				}
				insert_statement = insert_statement + ") VALUES (";
				
				String line1 = bfr.readLine();
				int lines_read = 1;
				while(line1 != null) {
					String[] line1_split = line1.split("\t");
					String new_insert_statement = insert_statement;
					for(int i = 0; i < line1_split.length; i++) {
						String data = line1_split[i];
						switch(coltypes[i]) {
							case "VARCHAR":
								data = data.replace("'", "''");
								data = "'" + data + "'";
								break;
							case "TEXT":
								data = data.replace("'", "''");
								data = "'" + data + "'";
								break;
							case "CHAR":
								data = "'" + data + "'";
							case "TINYINT":
								if(data == "true") {
									data = "1";
								} else {
									data = "0";
								}
								break;
							case "DATETIME":
								if(data != "") {
									data = "'" + formatDate(data) + "'";
								}
								break;
							case "DECIMAL":
								if(data.contains(",")) {
									String[] data_split = data.split(",");
									data = data_split[0];
								}
						}
						if(i != line1_split.length - 1) {
							if(line1_split[i] != "") {
								new_insert_statement = new_insert_statement + data + ", ";
							} else {
								new_insert_statement = new_insert_statement + "NULL, ";
							}
						} else {
							if(line1_split[i] != "") {
								new_insert_statement = new_insert_statement + data;
							} else {
								new_insert_statement = new_insert_statement + "NULL";
							}
							int ci = i;
							System.out.println(line1_split.length);
							System.out.println(ncols);
							while(ci < ncols - 1) {
								//if the last items (e.g. sort_order, quantity_sold, average_rating) in a line are not provided
								new_insert_statement = new_insert_statement + ", NULL";
								ci += 1;
							}
							
						}
					}
					new_insert_statement = new_insert_statement + ");"; //finish the insert statement
					System.out.println(new_insert_statement); //print final insert statement
					Statement new_stmt = connection.createStatement();
					Boolean res = new_stmt.execute(new_insert_statement);
					System.out.println("Result: " + res); //get result of insert statement
					line1 = bfr.readLine();
					lines_read += 1;
				}
				
			} catch (IOException e) {
				System.out.println("There was an IO Exception");
				System.out.println(e);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		
	}
	
}
