package model;

import java.sql.*;

public class graphModel {

	public graphModel() throws SQLException {

		Connection myConnection = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
            long startTime = System.nanoTime();

            
			// 1. connect to database and create a statement
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nerdygadgets", "root" , "");
			statement = myConnection.createStatement();
			
			
            // 2. execute query (x random addresses) 
            // TODO change to bestellingen instead of adresses
            int numberOfResults = 100;
			result = statement.executeQuery("select * from adres ORDER BY RAND() LIMIT " + numberOfResults);
			
			
            // 3. Calculate cost of every edge
            int startNodeID, startNodeIDX, startNodeIDY, targetNodeID, targetNodeIDX, targetNodeIDY;
            double cost;
            
            // for every node
            for (int startNodeIndex = 1; startNodeIndex < numberOfResults + 1; startNodeIndex++) {
                // get the x and y coordinates
                result.absolute(startNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
                startNodeID = result.getInt("adresID");
                startNodeIDX = result.getInt("x");
                startNodeIDY = result.getInt("y");
                
                // for every other node also take the x and y coordinates
                for (int targetNodeIndex = 1; targetNodeIndex < numberOfResults + 1; targetNodeIndex++) {
                    if (startNodeIndex != targetNodeIndex) { // TODO check if the edge is already in the database
                        result.absolute(targetNodeIndex); // absolute() sets the active row in the table, has to be set manually because we switch back and forth between entries
                        targetNodeID = result.getInt("adresID");
                        targetNodeIDX = result.getInt("x");
                        targetNodeIDY = result.getInt("y");
                        
                        // calculate the distance between the nodes with the hypotenuse. Both x and y distance must be positive (absolute)
                        cost = Math.hypot(Math.abs(startNodeIDX-targetNodeIDX), Math.abs(startNodeIDY-targetNodeIDY));
                        
                        // TODO insert into database
                        // System.out.println(startNodeID + " - " + targetNodeID);
                        // System.out.println(cost);
                        // System.out.println("");
                    }
                }
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000;  //divide by 1000000 to get milliseconds.
            System.out.println("Calculating a graph of size " + numberOfResults + " took " + duration + " milliseconds.");
		}
		// afhandelen van een exception wanneer het de connectie/query het niet doet.
		catch (Exception exception) {
            exception.printStackTrace();
		}
		finally {
			if (result != null) {
				result.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (myConnection != null) {
				myConnection.close();
			}
		}
	}

}
