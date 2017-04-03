import java.util.Scanner;
public class Driver
{
    public static void main(String[] args)
    {

        Scanner in = new Scanner(System.in);
        System.out.println("Please type in a board size (give one integer value)");
        Display dis = new Display(in.nextInt());
        dis.gameMenuDisplay();
    }
}
