import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitRoller {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(reader.readLine());
        int freezePos = Integer.parseInt(reader.readLine());
        int rollCount = Integer.parseInt(reader.readLine());

        System.out.println(rollRight(num,freezePos,rollCount));
    }
    private static int getBit(int num, int position){
        return (num >> position) & 1;
    }
    private static int setBit(int num, int position){return num |= (1 << position);}
    private static int unsetBit(int num, int position){return num &= ~(1 << position);}
    private static int setPositionWith(int num, int position, int value){ return value==0? unsetBit(num,position): setBit(num,position);}
    private static int rollRight(int num, int freezePos, int times){
        int freezeValue = getBit(num, freezePos);
        //System.out.printf("%d -> %d%n",freezePos,freezeValue);
        //System.out.printf("%18s -> %d%n", Integer.toBinaryString( (1 << 19) | num ).substring( 1 ),0);

        for (int i = 0; i < times; i++) {
            int beforePos;
            int afterPos;
            if (freezePos == 0){
                beforePos = 1;
                afterPos = 18;
            }
            else if (freezePos == 18){
                beforePos = 0;
                afterPos = 17;
            }
            else {
                beforePos = freezePos + 1;
                afterPos = freezePos - 1;
            }
            int beforeValue = getBit(num,beforePos);

            if (getBit(num,0) == 1 ){
                num >>= 1;
                num = setBit(num,18);
            }
            else {
                num >>= 1;
            }
            num = setPositionWith(num,freezePos,freezeValue);
            num = setPositionWith(num,afterPos,beforeValue);
            //System.out.printf("%18s -> %d%n", Integer.toBinaryString( (1 << 19) | num ).substring( 1 ),i+1);
        }
        return num;
    }
}
