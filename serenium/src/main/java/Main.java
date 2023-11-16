import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ScaleSimulator simulator = new ScaleSimulator();
        simulator.weigh(Arrays.asList(new GoldBar(0), new GoldBar(1), new GoldBar(2)),
                Arrays.asList(new GoldBar(3), new GoldBar(4), new GoldBar(5)));

        String result = simulator.getWeighingResult();
        System.out.println("Weighing result: " + result);

        if (result.contains("<")){
            simulator.weigh(Arrays.asList(new GoldBar(0)), Arrays.asList(new GoldBar(1)));
            String res = simulator.getWeighingResult();
            System.out.println("res: " + res);
            if (res.contains("<")) {
                System.out.println(0);
                simulator.selectGoldBar(0);
            }
            else if(res.contains(">")) {
                System.out.println(1);
                simulator.selectGoldBar(1);
            }
            else {
                System.out.println(2);
                simulator.selectGoldBar(2);
            }
        } else if (result.contains(">")){
            simulator.weigh(Arrays.asList(new GoldBar(3)), Arrays.asList(new GoldBar(4)));
            String res = simulator.getWeighingResult();
            System.out.println("res: " + res);
            if (res.contains("<")) {
                System.out.println(3);
                simulator.selectGoldBar(3);
            }
            else if(res.contains(">")) {
                System.out.println(4);
                simulator.selectGoldBar(4);
            }
            else {
                System.out.println(5);
                simulator.selectGoldBar(5);
            }
        } else{
            simulator.weigh(Arrays.asList(new GoldBar(6)), Arrays.asList(new GoldBar(7)));
            String res = simulator.getWeighingResult();
            System.out.println("res: " + res);
            if (res.contains("<")) {
                System.out.println(6);
                simulator.selectGoldBar(6);
            }
            else if(res.contains(">")) {
                System.out.println(7);
                simulator.selectGoldBar(7);
            }
            else {
                System.out.println(8);
                simulator.selectGoldBar(8);
            }
        }
//        simulator.selectGoldBar(2);
        String alertMessage = simulator.getAlertMessage();
//        simulator.resetScale();

        System.out.println("Alert message: " + alertMessage);
        simulator.close();
    }
}
