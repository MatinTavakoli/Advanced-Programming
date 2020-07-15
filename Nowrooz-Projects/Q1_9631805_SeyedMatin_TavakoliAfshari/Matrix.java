import java.util.ArrayList;
import java.util.Scanner;

public class Matrix {
    /**
     * Simulating a Matrix!
     *
     * @author Matin Tavakoli
     */
    private ArrayList<ArrayList<Integer>> elements = new ArrayList<ArrayList<Integer>>();//The matrix elements!
    private ArrayList<Integer> rowLength = new ArrayList<Integer>();//An arrayList for the rows' lengths!

    public Matrix() {

    }

    public boolean isMatrix(Matrix matrix) {
        for (int i = 1; i < matrix.getElements().size(); i++) {
            if (matrix.getElements().get(i).size() != matrix.getElements().get(i - 1).size())
                return false;
        }
        return true;
    }

    public void display(Matrix matrix) {
        if (matrix.isMatrix(matrix) == true) {
            for (int i = 0; i < matrix.getElements().size(); i++) {
                for (int j = 0; j < matrix.getRowLength().get(i); j++) {
                    System.out.print(matrix.getElements().get(i).get(j) + " ");
                    if (j == matrix.getElements().get(i).size() - 1)
                        System.out.println();
                }
            }
        } else
            System.out.println("You're matrix is illegal!");
    }

    public Matrix sum(Matrix m1, Matrix m2) {
        if (m1.isMatrix(m1) == true && m2.isMatrix(m2) == true) {
            if (m1.getElements().size() == m2.getElements().size() && m1.getRowLength().get(0) == m2.getRowLength().get(0)) {
                Matrix m3 = new Matrix();
                for (int i = 0; i < m1.getElements().size(); i++) {
                    m3.getElements().add(i, new ArrayList<Integer>());
                    m3.getRowLength().add(m1.getRowLength().get(i));
                    for (int j = 0; j < m1.getRowLength().get(i); j++) {
                        m3.getElements().get(i).add(j, m1.getElements().get(i).get(j) + m2.getElements().get(i).get(j));

                    }

                }
                return m3;
            } else {
                System.out.println("We cannot sum these two matrices!");
                return null;
            }
        } else {
            System.out.println("One of your matrices is not a matrice!!");
            return null;
        }
    }

    public Matrix subtract(Matrix m1, Matrix m2) {
        if (m1.isMatrix(m1) == true && m2.isMatrix(m2) == true) {
            if (m1.getElements().size() == m2.getElements().size() && m1.getRowLength().get(0) == m2.getRowLength().get(0)) {
                Matrix m3 = new Matrix();
                for (int i = 0; i < m1.getElements().size(); i++) {
                    m3.getElements().add(new ArrayList<Integer>());
                    m3.getRowLength().add(m1.getRowLength().get(i));
                    for (int j = 0; j < m1.getRowLength().get(i); j++) {
                        m3.getElements().get(i).add(j, m1.getElements().get(i).get(j) - m2.getElements().get(i).get(j));
                    }
                }
                return m3;
            } else {
                System.out.println("We cannot subtract these two matrices!");
                return null;
            }
        } else {
            System.out.println("One of your matrices is not a matrix!!");
            return null;
        }
    }

    public Matrix multiplyByMatrix(Matrix m1, Matrix m2) {
        if (m1.isMatrix(m1) == true && m2.isMatrix(m2) == true) {
            if (m1.getRowLength().get(0) == m2.getElements().size()) {//Because we have at least one row!So we use get(0)!
                Matrix m3 = new Matrix();
                for (int i = 0; i < m1.getElements().size(); i++) {
                    m3.getElements().add(i, new ArrayList<Integer>());
                    for (int j = 0; j < m2.getRowLength().size(); j++) {
                        int result = 0;//A temporary variable!
                        for (int m = 0; m < m1.getRowLength().get(0); m++) {
                            result += (m1.getElements().get(i).get(m)) * (m2.getElements().get(m).get(j));
                        }
                        System.out.println(result);
                        m3.getElements().get(i).add(result);//So we have (max(i)+1)*(max(j)+1) "results" which is the number of m3's elements!!
                    }
                }
                return m3;
            } else {
                System.out.println("These two matrices cannot be multiplied!!");
                return null;
            }

        } else {
            System.out.println("One of your two matrices is illegal!!");
            return null;
        }

    }

    public Matrix multiplyByInt(Matrix matrix, int n) {
        if (matrix.isMatrix(matrix) == true) {
            for (int i = 0; i < matrix.getElements().size(); i++) {
                for (int j = 0; j < matrix.getRowLength().get(i); j++) {
                    matrix.getElements().get(i).set(j, n * j);
                }
            }
            return matrix;
        } else {
            System.out.println("You're matrix is illegal!");
            return null;
        }
    }

    public ArrayList<ArrayList<Integer>> getElements() {
        return elements;
    }

    public ArrayList<Integer> getRowLength() {
        return rowLength;
    }
}
