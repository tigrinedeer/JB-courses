package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            sb.append(scanner.nextLine());
        }
        String text = sb.toString();
        System.out.println("The text is: \n"+text);
        TextCounter tc = new TextCounter(text);
        tc.printResults();

        System.out.println("Enter the score you want to calculate(ARI, FK, SMOG, CL,all):");
        Scanner in = new Scanner(System.in);
        String method = in.nextLine();

        double age = 0;
        switch (method) {
            case "ARI": { age+= new AutomatedReadabilityIndexCounter(tc).generateResult().age2;
                            break; }
            case "FK": { age+= new FleschKincaidIndexCounter(tc).generateResult().age2; break; }
            case "SMOG": { age+= new SMOGIndexCounter(tc).generateResult().age2; break; }
            case "CL": { age+= new ColemanLiauIndexCounter(tc).generateResult().age2; break; }
            case "all": {
                age+= new AutomatedReadabilityIndexCounter(tc).generateResult().age2;
                age+= new FleschKincaidIndexCounter(tc).generateResult().age2;
                age+= new SMOGIndexCounter(tc).generateResult().age2;
                age+= new ColemanLiauIndexCounter(tc).generateResult().age2;
                break;
            }
            default: { break;}
        }

        System.out.println(String.format("This text should be understood in average by %.2f-year-olds", age/4));
    }
}
