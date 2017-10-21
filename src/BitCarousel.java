import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitCarousel {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        int shiftCount = Integer.parseInt(reader.readLine());
        for (int i = 0; i < shiftCount; i++) {
            String direction = reader.readLine();
            if ("right".equals(direction)){
               if (getBit(num,0) == 1 ){
                   //num |= 0x6; set 5-th bit
                   //num &= ~0x6; unset 5-th bit
                   num >>= 1;
                   num |= 0b100000;
               }
               else {
                   num >>= 1;
               }
            }
            if ("left".equals(direction)){
                if (getBit(num,5) == 1 ){
                    num <<= 1;
                    num &= ~0b1000000; //erase 7-th bit!
                    num |= 0b1; //or num |= 1; is the same
                }
                else {
                    num <<= 1;
                }
            }
        }
        System.out.println(num);
        System.out.println(Integer.toBinaryString(num));
    }
    private static int getBit(int num, int position){
        return (num >> position) & 1;
    }
    private static int setBit(int num, int position){return num |= (1 << position);}
    private static int unsetBit(int num, int position){return num &= ~(1 << position);}

}
