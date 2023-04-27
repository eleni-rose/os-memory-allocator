import java.io.*;
import java.util.Scanner;

public class Mallocator {

    /*********************
     **********************
     * FirstFit Algorithm
     **********************
     **********************/

    public static String firstFit(int[] blockStart, int[] blockSizes, int blocks, int[] processSizes, int processes) {
        // Create local empty arrays
        int[] b = new int[blockSizes.length];
        int[] p = new int[processSizes.length];

        // Copy values from global blockSizes to local array b
        for (int i = 0; i < blockSizes.length; i++) {
            b[i] = blockSizes[i];
        }

        // Copy values from global processSizes to local array p
        for (int i = 0; i < processSizes.length; i++) {
            p[i] = processSizes[i];
        }

        int[] allocation = new int[processes];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if applicable
        for (int i = 0; i < processes; i++) {
            for (int j = 0; j < blocks; j++) {
                if (b[j] >= p[i]) {
                    allocation[i] = j;

                    b[j] -= p[i];

                    break;
                }
            }
        }

        String firstOutput = "";

        for (int i = 0; i < b.length; i++) {

            // Set block at index i equal to its address start
            b[i] = blockStart[i];

            for (int j = 0; j < allocation.length; j++) {
                if (allocation[j] == i) {
                    firstOutput += b[i] + " " + (b[i] + p[j]) + " " + (j + 1) + "\n";
                    b[i] += p[j];
                }
            }
        }

        String notAllocated = "-";

        // Iterate through allocation array to append any unallocated processes to notAllocated
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] == -1) {
                if (notAllocated.length() == 1) {
                    notAllocated += (i + 1);
                } else {
                    notAllocated += "," + (i + 1);
                }
            }
        }

        // If previous loop didn't detect any unallocated processes, append 0 to notAllocated
        if (notAllocated.length() == 1) {
            notAllocated += 0;
        }

        return firstOutput + notAllocated;
    }

    /********************
     *********************
     * BestFit Algorithm
     *********************
     *********************/

    public static String bestFit(int[] blockStart, int[] blockSizes, int blocks, int[] processSizes, int processes) {
        // Create local empty arrays
        int[] b = new int[blockSizes.length];
        int[] p = new int[processSizes.length];

        // Copy values from global blockSizes to local array b
        for (int i = 0; i < blockSizes.length; i++) {
            b[i] = blockSizes[i];
        }

        // Copy values from global processSizes to local array p
        for (int i = 0; i < processSizes.length; i++) {
            p[i] = processSizes[i];
        }

        int[] allocation = new int[processes];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if applicable
        for (int i = 0; i < processes; i++) {
            int bestBlock = -1;
            for (int j = 0; j < blocks; j++) {
                if (b[j] >= p[i]) {
                    if (bestBlock == -1 || b[bestBlock] > b[j])
                        bestBlock = j;
                    else if (b[bestBlock] > b[j])
                        bestBlock = j;
                }
            }

            if (bestBlock != -1) {
                allocation[i] = bestBlock;

                b[bestBlock] -= p[i];
            }
        }

        String bestOutput = "";

        for (int i = 0; i < b.length; i++) {

            // Set block at index i equal to its address start
            b[i] = blockStart[i];

            for (int j = 0; j < allocation.length; j++) {
                if (allocation[j] == i) {

                    bestOutput += b[i] + " " + (b[i] + p[j]) + " " + (j + 1) + "\n";
                    b[i] += p[j];

                }
            }
        }

        String notAllocated = "-";

        // Iterate through allocation array to append any unallocated processes to notAllocated
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] == -1) {
                if (notAllocated.length() == 1) {
                    notAllocated += (i + 1);
                } else {
                    notAllocated += "," + (i + 1);
                }
            }
        }

        // If previous loop didn't detect any unallocated processes, append 0 to notAllocated
        if (notAllocated.length() == 1) {
            notAllocated += 0;
        }

        return bestOutput + notAllocated;
    }

    /*********************
     **********************
     * WorstFit Algorithm
     **********************
     **********************/

    public static String worstFit(int[] blockStart, int[] blockSizes, int blocks, int[] processSizes, int processes) {
        // Create local empty arrays
        int[] b = new int[blockSizes.length];
        int[] p = new int[processSizes.length];

        // Copy values from global blockSizes to local array b
        for (int i = 0; i < blockSizes.length; i++) {
            b[i] = blockSizes[i];
        }

        // Copy values from global processSizes to local array p
        for (int i = 0; i < processSizes.length; i++) {
            p[i] = processSizes[i];
        }

        int[] allocation = new int[processes];

        // Declare base case of empty memory allocation blocks
        for (int i = 0; i < allocation.length; i++)
            allocation[i] = -1;

        // Iterate through processes, look for a suitable block, and allocate if
        // applicable
        for (int i = 0; i < processes; i++) {
            int worstBlock = -1;
            for (int j = 0; j < blocks; j++) {
                if (b[j] >= p[i]) {
                    if (worstBlock == -1)
                        worstBlock = j;
                    else if (b[worstBlock] < b[j])
                        worstBlock = j;
                }
            }

            if (worstBlock != -1) {
                allocation[i] = worstBlock;

                b[worstBlock] -= p[i];
            }
        }

        String worstOutput = "";

        for (int i = 0; i < b.length; i++) {

            // Set block at index i equal to its address start
            b[i] = blockStart[i];

            for (int j = 0; j < allocation.length; j++) {
                if (allocation[j] == i) {

                    worstOutput += b[i] + " " + (b[i] + p[j]) + " " + (j + 1) + "\n";
                    b[i] += p[j];

                }
            }
        }

        String notAllocated = "-";

        // Iterate through allocation array to append any unallocated processes to notAllocated
        for (int i = 0; i < allocation.length; i++) {
            if (allocation[i] == -1) {
                if (notAllocated.length() == 1) {
                    notAllocated += (i + 1);
                } else {
                    notAllocated += "," + (i + 1);
                }
            }
        }

        // If previous loop didn't detect any unallocated processes, append 0 to notAllocated
        if (notAllocated.length() == 1) {
            notAllocated += 0;
        }

        return worstOutput + notAllocated;
    }

    /*********************
     **********************
     * Read/Write Drivers
     **********************
     **********************/

    public static void main(String[] args) throws IOException {

        // Declare scanners to read in data files
        Scanner memoryScanner = new Scanner(new FileReader("Minput.data"));
        Scanner processScanner = new Scanner(new FileReader("Pinput.data"));

        // Set the blockSizes array size from the first integer in Minput.data
        int blocks = memoryScanner.nextInt();
        int[] blockSizes = new int[blocks];

        // Set the blockStart array
        int[] blockStart = new int[blocks];

        // Add elements to blockSizes array from Minput.data
        for (int i = 0; i < blocks; i++) {
            int addressStart = memoryScanner.nextInt();
            int addressEnd = memoryScanner.nextInt();
            int blockSize = addressEnd - addressStart;
            blockSizes[i] = blockSize;
            blockStart[i] = addressStart;
        }

        // Set the processSizes array size from the first integer in Pinput.data
        int processes = processScanner.nextInt();
        int[] processSizes = new int[processes];

        // Add elements to the processSizes array from Pinput.data
        for (int i = 0; i < processes; i++) {
            int processID = processScanner.nextInt();
            int processSize = processScanner.nextInt();
            processSizes[i] = processSize;
        }

        // Write FirstFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("FFOutput.data"));
            writer.write((firstFit(blockStart, blockSizes, blocks, processSizes, processes)));
            System.out.println("Successfully wrote First Fit to FFOutput.data!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write BestFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("BFOutput.data"));
            writer.write((bestFit(blockStart, blockSizes, blocks, processSizes, processes)));
            System.out.println("Successfully wrote Best Fit to BFOutput.data!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write WorstFit output
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("WFOutput.data"));
            writer.write((worstFit(blockStart, blockSizes, blocks, processSizes, processes)));
            System.out.println("Successfully wrote Worst Fit to WFOutput.data!");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}