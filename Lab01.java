/*=============================================================================
 |   Building a Prim's MST for an input graph
 |
 |     Author:  Henry King
 |     Language:  Java
 |
 |     Class:  COP3503 - CS II Summer 2021
 |     Instructor:  McAlpin
 |     Date:  7/25/2021
 |
 +=============================================================================*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Lab01 {

    static int num_vert;
    static int num_edge;

    public static void main(String args[]) throws FileNotFoundException {

        // Scan input into array for easier reading
        File input = new File("input_MST.txt");
        // File input = new File(args[0]);
        Scanner sc = new Scanner(input);
        // create array to hold strings
        ArrayList<String> inputdatalist = new ArrayList<String>();
        // puts strings in array
        while (sc.hasNext()) {
            inputdatalist.add(sc.next());
        }
        // close the scanner
        sc.close();

        // reads in the number of edges and max vertex
        num_vert = Integer.parseInt(inputdatalist.get(0));
        num_edge = Integer.parseInt(inputdatalist.get(1));

        // now create the adjacency matrix and assign weights
        double matrix[][] = new double[num_vert][num_vert];

        for (int a = 0; a < num_vert; a++) {
            for (int b = 0; b < num_vert; b++) {
                matrix[a][b] = Integer.MAX_VALUE;
            }
        }

        for (int i = 2; i < inputdatalist.size(); i += 3) {
            matrix[Integer.parseInt(inputdatalist.get(i))][Integer.parseInt(inputdatalist.get(i + 1))] = Double
                    .parseDouble(inputdatalist.get(i + 2));
        }

        // print the matrix for testing
        /*
         * for(int v = 0; v < num_vert; v++) {
         * for(int v2 = 0; v2 < num_vert; v2++) {
         * System.out.print(matrix[v][v2] + "\t");
         * }
         * System.out.print("\n");
         * }
         */

        MST.primMST(matrix);
    }

}

class MST {

    static int INT_MAX = Integer.MAX_VALUE;

    // returns true if edge is valid
    static boolean isValidEdge(int u, int v, boolean[] inMST) {
        if (u == v)
            return false;
        if (inMST[u] == false && inMST[v] == false)
            return false;
        else if (inMST[u] == true && inMST[v] == true)
            return false;
        return true;
    }

    static void primMST(double weights[][]) {
        boolean[] inMST = new boolean[Lab01.num_vert];
        System.out.println("\nMST Edges With Respective Weights:\n");

        // Include first vertex in MST
        inMST[0] = true;

        // Keep adding edges while number of included
        // edges does not become V-1.
        int edge_count = 0;
        double minimum_cost = 0;
        while (edge_count < Lab01.num_vert - 1) {
            // find smallest weight valid edge
            double minimum = INT_MAX;
            int a = -1, b = -1;
            for (int i = 0; i < Lab01.num_vert; i++) {
                for (int j = 0; j < Lab01.num_vert; j++) {
                    if (weights[i][j] < minimum) {
                        if (isValidEdge(i, j, inMST)) {
                            minimum = weights[i][j];
                            a = i;
                            b = j;
                        }
                    }
                }
            }

            if (a != -1 && b != -1) {
                edge_count++;
                System.err.printf("%d-%d %.5f \n", a, b, minimum);
                minimum_cost = minimum_cost + minimum;
                inMST[b] = inMST[a] = true;
            }
        }
        System.out.printf("\nTOTAL MST WEIGHT = %.5f\n\n", minimum_cost);
    }

}
