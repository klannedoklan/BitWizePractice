import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalBreakpoint {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Long[]> lines = new ArrayList<>();
        boolean critBreakpoint = false;
        while (true) {
            String input = reader.readLine();
            if ("Break it.".equals(input)) {
                break;
            }
            Long[] coordinates = Arrays.stream(input.split("\\s+"))
                    .map(Long::valueOf)
                    .toArray(Long[]::new);
            lines.add(coordinates);
        }
        StringBuilder sb = new StringBuilder();
        int linesCount = lines.size();
        for (int i = 0; i < linesCount; i++) {
            sb.append("Line: "+Arrays.toString(lines.get(i))+"\n");
        }
        String result = sb.toString();
        long actualCritRatio = 0;

        for (int i = 0; i < linesCount; i++) {
            long currentRatio = getLineCritRatio(lines.get(i));
            for (int j = 0; j < linesCount; j++) {
                if (i!=j){
                    if (getLineCritRatio(lines.get(j)) == currentRatio || getLineCritRatio(lines.get(j)) == 0){
                        critBreakpoint = true;
                    }
                }
            }
            if (currentRatio !=0 && critBreakpoint){
                actualCritRatio = currentRatio;
            }
        }

        if (critBreakpoint){
            System.out.print(result);
            BigInteger finalValue = BigInteger.valueOf(actualCritRatio).pow(linesCount).mod(BigInteger.valueOf(linesCount));

            System.out.printf("Critical Breakpoint: %d",finalValue);
        }
        else {
            System.out.println("Critical breakpoint does not exist.");
        }
    }
    private static Long getLineCritRatio(Long[] points){
        Long x1 = points[0];
        Long y1 = points[1];
        Long x2 = points[2];
        Long y2 = points[3];
        return Math.abs((x2 + y2)-(x1 + y1));
    }
}
