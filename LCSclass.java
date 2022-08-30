// Import libraries
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

// Create class 
public class LCSclass
{
    // Create LCS method
    public static List<String> LCS(String A, String B, int x, int y, int[][] AB)
    {
        // Find the end of the LCS
        if (x == 0 || y == 0)
        {
            return new ArrayList<>(Collections.nCopies(1, ""));
        }
 
        // If characters match
        if (A.charAt(x - 1) == B.charAt(y - 1))
        {
            // Find all LCS and store
            List<String> LCSArray = LCS(A, B, x - 1, y - 1, AB);
 
            for (int i = 0; i < LCSArray.size(); i++) {
                LCSArray.set(i, LCSArray.get(i) + (A.charAt(x - 1)));
            }
 
            return LCSArray;
        }
 
        // Find max values
        if (AB[x - 1][y] > AB[x][y - 1]) {
            return LCS(A, B, x - 1, y, AB);
        }
 
        if (AB[x][y - 1] > AB[x - 1][y]) {
            return LCS(A, B, x, y - 1, AB);
        }
 
        // Considering both characters
        List<String> topVal = LCS(A, B, x - 1, y, AB);
        List<String> leftVal = LCS(A, B, x, y - 1, AB);
 
        // Merge two lists and return
        topVal.addAll(leftVal);
        return topVal;
    }
 
    // Create lenLCS method
    public static void lenLCS(String A, String B, int[][] AB)
    {
        // Fill the table from bottom
        for (int i = 1; i <= A.length(); i++)
        {
            for (int j = 1; j <= B.length(); j++)
            {
                // If A == B or A != B
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    AB[i][j] = AB[i - 1][j - 1] + 1;
                }
                else {
                    AB[i][j] = Integer.max(AB[i - 1][j], AB[i][j - 1]);
                }
            }
        }
    }
 
    // Create LCS method
    public static Set<String> LCS(String X, String Y, int[][] AB)
    {
        // Length of LCS
        lenLCS(X, Y, AB);
 
        // Find all the LCS
        List<String> LCSArray = LCS(X, Y, X.length(), Y.length(), AB);
 
        // Fix duplicates
        return new HashSet<>(LCSArray);
    }
 
    public static void main(String[] args)
    {
        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // Read input A and calculate its length
        System.out.println("Enter A");
        String A = input.nextLine();
        int lenA = A.length();

        // Read input B and calculate its length
        System.out.println("Enter B");
        String B = input.nextLine();
        int lenB = B.length();
        
        // Create a table to store common values details
        int[][] AB = new int[lenA + 1][lenB + 1];
 
        // Find all LCS of string A and B
        Set<String> LCSArray = LCS(A,B, AB);
 
        // Print all LCS of string A and B
        System.out.println(LCSArray);
    }
}