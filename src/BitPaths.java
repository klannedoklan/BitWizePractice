import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitPaths {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<int[]> paths = new ArrayList<>();
        int[] bitPositions = {3, 2, 1, 0};
        int[] bitNums = {0b0, 0b0, 0b0, 0b0, 0b0, 0b0, 0b0, 0b0};
        for (int i = 0; i < n; i++) {
            paths.add(Arrays.stream(reader.readLine().split(",")).mapToInt(Integer::parseInt).toArray());
        }

        for (int i = 0; i < n; i++) {
            int startPosition = paths.get(i)[0];
            bitNums[0] = invertBit(bitNums[0],bitPositions[startPosition]);
            for (int j = 1; j < paths.get(i).length; j++) {
                int invertPosition = bitPositions[startPosition + paths.get(i)[j]];
                startPosition += paths.get(i)[j];
                bitNums[j] = invertBit(bitNums[j], invertPosition);
            }
        }
        int sum = Arrays.stream(bitNums).sum();
        System.out.println(Integer.toBinaryString(sum));
        System.out.println(Integer.toHexString(sum).toUpperCase());
    }
    private static int invertBit(int num, int position){
        return getBit(num,position) == 0 ? setBit(num,position) : unsetBit(num,position);
    }
    private static int getBit(int num, int position){
        return (num >> position) & 1;
    }
    private static int setBit(int num, int position){return num |= (1 << position);}
    private static int unsetBit(int num, int position){return num &= ~(1 << position);}
}
