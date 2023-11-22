import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int[][] field  = new int [7][7];
        for(int i = 1; i < 8; i++){
            for(int j = 1; j < 8; j++){
                field[i][j] = 0;
            }
        }
        int value = 0;
        while( value < 1) {
            int x12 = random.nextInt(7);
            int y12 = random.nextInt(7);
            int x22 = random.nextInt(7);
            int y22 = random.nextInt(7);
            int x32 = random.nextInt(7);
            int y32 = random.nextInt(7);


            if (check3squareShipsAllCoordinates(x12, y12, x22, y22, x32, y32)) {
                System.out.println(x12 + " " + y12 + " " + x22 + " " + y22 + " " + x32 + " " + y32);
                value++;
            }
        }

        for(int i = 0; i < 3; i++){
            int xCoordinate = random.nextInt(7);
            int yCoordinate = random.nextInt(7);
            if(checkSquaresAround(xCoordinate, yCoordinate, field)){
                field[xCoordinate][yCoordinate] = 1;
            }
        }

        for(int i = 1; i<8; i++) {
            for (int j = 1; j < 8; j++) {
                if (j == 0) {
                    System.out.print("\n" + field[i][j] + " ");
                } else {
                    System.out.print(field[i][j] + " ");
                }

            }

        }

    }


    public static boolean checkSquaresAround(int x, int y, int[][] matrix){
        boolean square1 = matrix[x][y-1] != 0;
        boolean square2 = matrix[x][y+1] != 0;
        boolean square3 = matrix[x-1][y] != 0;
        boolean square4 = matrix[x+1][y] != 0;
        boolean square5 = matrix[x-1][y-1] != 0;
        boolean square6 = matrix[x+1][y+1] != 0;
        boolean square7 = matrix[x-1][y+1] != 0;
        boolean square8 = matrix[x+1][y-1] != 0;
        if(square1 || square2 || square3 || square4 || square5 || square6 || square7 || square8){
            return false;
        }
        else{
            return true;
        }


    }
    public static boolean check2squareShipsAllCoordinates(int x1, int y1, int x2, int y2){
        boolean xEquals = x1 == x2;
        boolean yEquals = y1 == y2;
        if(xEquals){
            return check2squareShipsXCoordinate(y1, y2);
        }
        else if(yEquals){
            return check2squareShipsXCoordinate(x1, x2);
        }
        else{
            return false;
        }
    }
    public static boolean check2squareShipsXCoordinate(int x, int x1){
        boolean var3 = x - x1 == 1;
        boolean var4 = x - x1 == -1;
        return var3 || var4;
    }

    public static boolean check3squareShipsAllCoordinates(int x1, int y1, int x2, int y2, int x3, int y3){
        boolean xEquals = x1 == x2 && x1 == x3;
        boolean yEquals = y1 == y2 && y1 == y3;
        if(xEquals){
            return check3squareShipsXCoordinate(y1, y2, y3);
        }
        else if(yEquals){
            return check3squareShipsXCoordinate(x1, x2, x3);
        }
        else{
            return false;
        }
    }

    public static boolean check3squareShipsXCoordinate(int x, int x1, int x2){
        boolean var1 = x - x1 == 2 && x - x2 == 1;
        boolean var2 = x - x1 == -2 && x - x2 == -1;
        boolean var3 = x - x1 == 1 && x - x2 == 2;
        boolean var4 = x - x1 == -1 && x - x2 == -2;
        return var1 || var2 || var3 || var4;
    }
}


}