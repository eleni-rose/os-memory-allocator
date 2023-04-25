import java.io.*;
import java.util.Scanner;

public class Mallocator {

    /********************* 
    **********************
      FirstFit Algorithm
    **********************
    **********************/

    public static String firstFit(int[] blockSize, int m, int[] processSize, int n)
    {
        int[] allocation = new int[n];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if applicable
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (blockSize[j] >= processSize[i])
                {
                    allocation[i] = j;

                    blockSize[j] -= processSize[i];

                    break;
                }
            }
        }

        String firstOutput = ""; 

        firstOutput += "\nProcess ID\tProcess Size\tBlock\n";
        for (int i = 0; i < n; i++)
        {
            firstOutput += "" + (i + 1) + "\t\t" + processSize[i] + "\t\t";
            if (allocation[i] != -1)
                firstOutput += allocation[i] + 1;
            else
                firstOutput += "Not allocated";
            firstOutput += "\n";
        }

        return firstOutput;
    }

    /******************** 
    *********************
      BestFit Algorithm
    *********************
    *********************/

    public static String bestFit(int[] blockSize, int m, int[] processSize, int n)
    {
        int[] allocation = new int[n];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if applicable
        for (int i = 0; i < n; i++)
        {
            int bestBlock = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestBlock == -1)
                        bestBlock = j;
                    else if (blockSize[bestBlock] > blockSize[j])
                        bestBlock = j;
                }
            }

            if (bestBlock != -1) {
                allocation[i] = bestBlock;

                blockSize[bestBlock] -= processSize[i];
            }
        }

        String bestOutput = ""; 

        bestOutput += "\nProcess ID\tProcess Size\tBlock\n";
        for (int i = 0; i < n; i++)
        {
            bestOutput += "" + (i + 1) + "\t\t" + processSize[i] + "\t\t";
            if (allocation[i] != -1)
                bestOutput += allocation[i] + 1;
            else
                bestOutput += "Not allocated";
            bestOutput += "\n";
        }

        return bestOutput;
    }

    /*********************
    **********************
      WorstFit Algorithm
    **********************
    **********************/

    public static String worstFit(int[] blockSize, int m, int[] processSize, int n)
    {
        int[] allocation = new int[n];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if applicable
        for (int i = 0; i < n; i++)
        {
            int worstBlock = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (worstBlock == -1)
                        worstBlock = j;
                    else if (blockSize[worstBlock] < blockSize[j])
                        worstBlock = j;
                }
            }

            if (worstBlock != -1) {
                allocation[i] = worstBlock;

                blockSize[worstBlock] -= processSize[i];
            }
        }

        String worstOutput = ""; 

        worstOutput += "\nProcess ID\tProcess Size\tBlock\n";
        for (int i = 0; i < n; i++)
        {
            worstOutput += "" + (i + 1) + "\t\t" + processSize[i] + "\t\t";
            if (allocation[i] != -1)
                worstOutput += allocation[i] + 1;
            else
                worstOutput += "Not allocated";
            worstOutput += "\n";
        }

        return worstOutput;
    }

    /*********************
    **********************
      Read/Write Drivers
    **********************
    **********************/

    public static void main(String[] args) throws IOException {

        int[] blockSize = {100, 200, 300, 400, 500, 600};
        int[] processSize = {215, 280, 473, 503, 123};
        int m = blockSize.length;
        int n = processSize.length;

        // Read memory input data
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Pinput.data"));
            String mLine;
            String mLine2[] = mLine.split("\\s");
            
            while((mLine = reader.readLine()) != null)
                System.out.println(mLine);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Read process input data
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Pinput.data"));
            String pLine;
            while((pLine = reader.readLine()) != null)
                System.out.println(pLine);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write FirstFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("FFOutput.data"));
            writer.write((firstFit(blockSize, m, processSize, n)));
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }

        // Write BestFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BFOutput.data"));
            writer.write((bestFit(blockSize, m, processSize, n)));
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }

        // Write WorstFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("WFOutput.data"));
            writer.write((worstFit(blockSize, m, processSize, n)));
            writer.close();
        }   catch (IOException e) {
            e.printStackTrace();
        }

    }
}