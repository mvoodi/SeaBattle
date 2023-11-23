import java.util.Scanner;
import java.util.Random;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        int[][] field  = new int [7][7];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
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
                field[x12][y12]=1;
                field[x22][y22]=1;
                field[x32][y32]=1;
                value++;
            }
        }
        while(value < 3) {
            int x12 = random.nextInt(7);
            int y12 = random.nextInt(7);
            int x22 = random.nextInt(7);
            int y22 = random.nextInt(7);



            if(check2squareShipsAllCoordinates(x12, y12, x22, y22) && check2SquaresShipsAround(x12, y12, x22, y22, field)) {
                System.out.println(x12 + " " + y12 + " " + x22 + " " + y22);
                field[x12][y12]=1;
                field[x22][y22]=1;
                value++;
            }
        }
        value=0;
        while(value < 4) {
            int x12 = random.nextInt(7);
            int y12 = random.nextInt(7);



            if(checkSquaresAround(x12, y12, field)) {
                System.out.println(x12 + " " + y12);
                field[x12][y12]=1;
                value++;
            }
        }




        for(int i = 0; i<7; i++) {
            for (int j = 0; j < 7; j++) {
                if (j == 0) {
                    System.out.print("\n" + field[i][j] + " ");
                } else {
                    System.out.print(field[i][j] + " ");
                }

            }

        }

        String[][] displayField  = new String [8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j==0 && i!= 0){
                    displayField[i][j] = definingСoordinatesLetter(i);
                }
                else if(i==0 && j!=0){
                    displayField[i][j] = j + "|";
                }
                else{
                    displayField[i][j] = "_|";
                }
            }
        }
        for(int a = 0; a < 50; a++){
            System.out.println("Hit - *\n" +
                    "Miss - o\n" +
                    "Sunk - X");
            for(int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (j == 0) {
                        System.out.print("\n" + displayField[i][j]);
                    } else {
                        System.out.print(displayField[i][j]);
                    }

                }

            }
            System.out.println();
            String shotsLatterCoordinate = scanner.nextLine();
            int shotsXCoordinate = replaceLetter(shotsLatterCoordinate);
            String shotsYCoordinate1 = scanner.nextLine();
            int shotsYCoordinate = Integer.parseInt(shotsYCoordinate1);
            String miss = "o|";
            String hit = "*|";
            String sunk = "X|";
            if(field[shotsXCoordinate-1][shotsYCoordinate-1] != 0 && checkSquaresAround(shotsXCoordinate-1, shotsYCoordinate-1, field)){
                System.out.println("Congratulations! You have sunk 1 ship!");
                displayField[shotsXCoordinate][shotsYCoordinate] = sunk;
            }
            else if(field[shotsXCoordinate-1][shotsYCoordinate-1] == 1 && !checkSquaresAround(shotsXCoordinate-1, shotsYCoordinate-1, field)){
                displayField[shotsXCoordinate][shotsYCoordinate] = hit;
            }
            else if(field[shotsXCoordinate-1][shotsYCoordinate-1] != 0 && field[shotsXCoordinate-1][shotsYCoordinate-1] != 1){
                System.out.println("You've already shot this cage! \n" +
                        "Or you entered the coordinates incorrectly. \n" +
                        "Try again!");
            }
            else{
                displayField[shotsXCoordinate][shotsYCoordinate] = miss;
            }

            clearConsole();
        }



    }
    public static void clearConsole() {
        System.out.print("\033[H\033[J");
        System.out.flush();
    }
    public static int replaceLetter ( String letter){
        int rowsNum = 0;
        if(letter.equals("A") || letter.equals("A;")){
            rowsNum++;
        }
        else if(letter.equals("B") || letter.equals("B;")){
            rowsNum+=2;
        }
        else if(letter.equals("C") || letter.equals("C;")){
            rowsNum+=3;
        }
        else if(letter.equals("D") || letter.equals("D;")){
            rowsNum+=4;
        }
        else if(letter.equals("E") || letter.equals("E;")){
            rowsNum+=5;
        }
        else if(letter.equals("F") || letter.equals("F;")){
            rowsNum+=6;
        }
        else if(letter.equals("G") || letter.equals("G;")){
            rowsNum+=7;
        }
        return rowsNum;
    }

    public static String definingСoordinatesLetter (int a){
        if(a==1){
            return "A|";
        }
        else if(a==2){
            return "B|";
        }
        else if(a==3){
            return "C|";
        }
        else if(a==4){
            return "D|";
        }
        else if(a==5){
            return "E|";
        }
        else if(a==6){
            return "F|";
        }
        else{
            return "G|";
        }
    }



    public static boolean check2SquaresShipsAround(int x1, int y1, int x2, int y2, int[][] matrix){
        boolean squaresCheking = checkSquaresAround(x1, y1, matrix) && checkSquaresAround(x2, y2, matrix);
        return squaresCheking;
    }


    public static boolean checkSquaresAround(int x, int y, int[][] matrix){
        if(x==0 && (y != 0 && y != 6)){
            boolean square1 = matrix[x][y-1] != 0;
            boolean square2 = matrix[x][y+1] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square6 = matrix[x+1][y+1] != 0;
            boolean square8 = matrix[x+1][y-1] != 0;
            return !(square1 || square2 || square4 || square6 || square8);
        }
        else if(x==0 && y==0){
            boolean square2 = matrix[x][y+1] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square6 = matrix[x+1][y+1] != 0;
            return !(square6 || square2 || square4);
        }
        else if(x == 0 && y == 6){
            boolean square1 = matrix[x][y-1] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square8 = matrix[x+1][y-1] != 0;
            return  !(square1 || square4 || square8);
        }
        else if(x==6 && (y!=0 && y!=6)){
            boolean square1 = matrix[x][y-1] != 0;
            boolean square2 = matrix[x][y+1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square5 = matrix[x-1][y-1] != 0;
            boolean square7 = matrix[x-1][y+1] != 0;
            return !(square1 || square7 || square5 || square2 || square3);
        }
        else if(x==6 && y==0){
            boolean square2 = matrix[x][y+1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square7 = matrix[x-1][y+1] != 0;
            return !(square7 || square2 || square3);
        }
        else if(x==6 && y==6){
            boolean square1 = matrix[x][y-1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square5 = matrix[x-1][y-1] != 0;
            return !(square1 || square3 || square5);
        }
        else if ((x!=0 && x!= 6) && y==0){
            boolean square2 = matrix[x][y+1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square6 = matrix[x+1][y+1] != 0;
            boolean square7 = matrix[x-1][y+1] != 0;
            return !(square2 || square3 || square4 || square6 || square7);
        }
        else if((x!=0 && x!= 6) && y==6){
            boolean square1 = matrix[x][y-1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square5 = matrix[x-1][y-1] != 0;
            boolean square8 = matrix[x+1][y-1] != 0;
            return !(square1 || square3 || square4 || square5 || square8);
        }
        else{
            boolean square1 = matrix[x][y-1] != 0;
            boolean square2 = matrix[x][y+1] != 0;
            boolean square3 = matrix[x-1][y] != 0;
            boolean square4 = matrix[x+1][y] != 0;
            boolean square5 = matrix[x-1][y-1] != 0;
            boolean square6 = matrix[x+1][y+1] != 0;
            boolean square7 = matrix[x-1][y+1] != 0;
            boolean square8 = matrix[x+1][y-1] != 0;
            return !(square1 || square2 || square3 || square4 || square5 || square6 || square7 || square8);
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