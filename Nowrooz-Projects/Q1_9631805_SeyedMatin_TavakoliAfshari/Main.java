import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputStream = new Scanner(System.in);
        Matrix matrix1 = new Matrix();
        Matrix matrix2 = new Matrix();
        System.out.println("Define the first matrix (X)");
        for (int i = 0; ; i++) {
            String currentLine = inputStream.nextLine();
            if (!(currentLine.equals(""))) {
                if (!(currentLine.contains(",,"))) {
                    matrix1.getElements().add(i, new ArrayList<Integer>());
                    String[] rows = currentLine.split(",");
                    matrix1.getRowLength().add(i, currentLine.length() / 2 + 1);//Because when we have "1 2 3",it's actually 5 characters(2 spaces).So  we divide it by 2 and add 1 to gain access to our row's length.
                    for (int j = 0; j < matrix1.getRowLength().get(i); j++) {
                        matrix1.getElements().get(i).add(j, Integer.parseInt(rows[j]));
                    }
                } else
                    System.out.println("Wrong input!");
            } else {
                break;
            }
        }
        System.out.println();
        System.out.println("Define the second matrix (Y)");
        for (int i = 0; ; i++) {
            String currentLine = inputStream.nextLine();
            if (!(currentLine.equals(""))) {
                if (!(currentLine.contains(",,"))) {
                    matrix2.getElements().add(i, new ArrayList<Integer>());
                    String[] rows = currentLine.split(",");
                    matrix2.getRowLength().add(i, currentLine.length() / 2 + 1);//Because when we have "1 2 3",it's actually 5 characters(2 spaces).So  we divide it by 2 and add 1 to gain access to our row's length.
                    for (int j = 0; j < matrix2.getRowLength().get(i); j++) {
                        matrix2.getElements().get(i).add(j, Integer.parseInt(rows[j]));
                    }
                } else
                    System.out.println("Wrong input!");
            } else {
                break;
            }
        }
        Matrix matrix4 = new Matrix();
        matrix4 = matrix1.multiplyByMatrix(matrix1, matrix2);
        matrix4.display(matrix4);

        System.out.println();
        System.out.println("Enter your polynomial expression!");
        String linearProjection = inputStream.nextLine();
        if (!(linearProjection.equals("")) || linearProjection.contains("++") || linearProjection.contains("--") || linearProjection.contains("**")) {//Unwanted phrases!
            if (!(linearProjection.contains("X"))) {
                linearProjection.replace(" ", "");
                linearProjection.replace("(", "");
                linearProjection.replace(")", "");
                for (int i = 1; i < 9; i++) {
                    linearProjection.replace("+" + i + "Y", "" + i + "Y");//Making it more natural!
                    if (!(linearProjection.contains("+Y")) && !(linearProjection.contains("-Y")) && !(linearProjection.contains("*"))) {
                        matrix2.multiplyByInt(matrix2, Integer.parseInt(linearProjection.substring(0, linearProjection.indexOf("Y"))));
                        matrix2.display(matrix2);
                    } else {
                        if (linearProjection.equals("+Y")) {
                            matrix2.display(matrix2);
                        } else if (linearProjection.equals("-Y")) {
                            matrix2.multiplyByInt(matrix2, -1);
                            matrix2.display(matrix2);
                        } else
                            System.out.println("Wrong input");
                    }
                }

            } else if (!(linearProjection.contains("Y"))) {
                linearProjection.replace(" ", "");
                linearProjection.replace("(", "");
                linearProjection.replace(")", "");
                for (int i = 1; i < 9; i++) {
                    linearProjection.replace("+" + i + "X", "" + i + "X");//Making it more natural!
                }
                if (!(linearProjection.contains("+X")) && !(linearProjection.contains("-X")) && !(linearProjection.contains("*"))) {
                    matrix1.multiplyByInt(matrix1, Integer.parseInt(linearProjection.substring(linearProjection.indexOf("X") - 1, linearProjection.indexOf("X"))));
                    matrix1.display(matrix1);
                } else {
                    if (linearProjection.equals("+X")) {
                        matrix1.display(matrix1);
                    } else if (linearProjection.equals("-X")) {
                        matrix1.multiplyByInt(matrix1, -1);
                        matrix1.display(matrix1);
                    } else
                        System.out.println("Wrong input");
                }
            } else if (linearProjection.contains("X") && linearProjection.contains("Y")) {
                linearProjection.replace(" ", "");
                linearProjection.replace("(", "");
                linearProjection.replace(")", "");
                for (int i = 1; i < 9; i++) {
                    linearProjection.replace("+" + i + "X", "" + i + "X");//Making it more natural!
                    linearProjection.replace("+" + i + "Y", "" + i + "Y");//Making it more natural!
                }
                matrix1.multiplyByInt(matrix1, Integer.parseInt(linearProjection.substring(linearProjection.indexOf("X") - 1, linearProjection.indexOf("X"))));
                matrix2.multiplyByInt(matrix2, Integer.parseInt(linearProjection.substring(linearProjection.indexOf("Y") - 1, linearProjection.indexOf("Y"))));
                linearProjection.replace(linearProjection.substring(linearProjection.indexOf("X") - 1, linearProjection.indexOf("X")), "");
                linearProjection.replace(linearProjection.substring(linearProjection.indexOf("Y") - 1, linearProjection.indexOf("Y")), "");
                if (linearProjection.substring(linearProjection.indexOf("X"), linearProjection.indexOf("Y")).contains("+") || linearProjection.substring(linearProjection.indexOf("Y"), linearProjection.indexOf("X")).contains("+")) {
                    Matrix matrix = new Matrix();
                    matrix = matrix1.sum(matrix1, matrix2);
                    matrix.display(matrix);
                } else if (linearProjection.substring(linearProjection.indexOf("X"), linearProjection.indexOf("Y")).contains("-") || linearProjection.substring(linearProjection.indexOf("Y"), linearProjection.indexOf("X")).contains("-")) {
                    Matrix matrix = new Matrix();
                    matrix = matrix1.subtract(matrix1, matrix2);
                    matrix.display(matrix);
                } else if (linearProjection.substring(linearProjection.indexOf("X"), linearProjection.indexOf("Y")).contains("*") || linearProjection.substring(linearProjection.indexOf("Y"), linearProjection.indexOf("X")).contains("*")) {
                    Matrix matrix = new Matrix();
                    matrix = matrix1.multiplyByMatrix(matrix1, matrix2);
                    matrix.display(matrix);
                }
            }

        } else
            System.out.println("You didn't enter anything or gave a wrong input!");
    }
}

