/*
 * This class represents a Matrix of Complex Numbers
 * 
 * Author: Tejas Navada
 * 12/17/22
 */
public class Matrix {
    
    
    
    private ComplexNumber[][] matrix;
    
    public Matrix() {
        matrix = null;
    }
    
    public Matrix(ComplexNumber[][] m) {
        matrix = new ComplexNumber[m.length][m[0].length];
        for(int i = 0;i < m.length;i++) {
            for(int j = 0;j < m[0].length;j++) {
                matrix[i][j] = m[i][j].clone();
            }
        }
        
    }
    public Matrix(double[][] m) {
        matrix = new ComplexNumber[m.length][m[0].length];
        for(int i = 0;i < m.length;i++) {
            for(int j = 0;j < m[0].length;j++) {
                matrix[i][j] = new ComplexNumber(m[i][j]);
            }
        }
    }
    
    public static Matrix identity(int size) {
        double[][] i = new double[size][size];
        for(int r = 0;r < i.length;r++) {
            for(int c = 0;c < i[0].length;c++) {
                if(r==c) {
                    i[r][c] = 1;
                }
                else {
                    i[r][c] = 0;
                }
            }
        }
        return new Matrix(i);
    }
    
    /*
     * Returns a matrix without the row and column parameters used in finding
     * the determinant and cofactor matrix
     */
    public Matrix remove(int row, int col) {
        Matrix matrix2 = new Matrix(matrix);
        matrix2 = matrix2.rowRemoved(row);
        matrix2 = matrix2.colRemoved(col);
        return matrix2;
    }
    
    /*
     * adds a row to matrix used in creating transpose matrix
     */
    public Matrix rowAdded(int row,ComplexNumber[] v) {
        if(matrix == null) {
            ComplexNumber[][] m = new ComplexNumber[1][v.length];
            m[0] = v;
            return new Matrix(m);
        }
        ComplexNumber[][] matrix2 = new ComplexNumber[matrix.length+1][matrix[0].length];
        int offset = 0;
        for(int i = 0; i < matrix2.length; i++) {
            if(i == row) {
                for(int k = 0; k < v.length; k++) {
                    matrix2[i][k] = v[k];
                }
                offset++;
                continue;
            }
            for(int j = 0; j < matrix2[0].length; j++) {
                matrix2[i][j] = matrix[i-offset][j];
            }
        }
        return new Matrix(matrix2);
    }
    
    public Matrix colAdded(int col,ComplexNumber[] v) {
        if(matrix == null) {
            ComplexNumber[][] m = new ComplexNumber[v.length][1];
            for(int i = 0; i < v.length; i++) {
                m[i][0] = v[i];
            }
            return new Matrix(m);
        }
        ComplexNumber[][] matrix2 = new ComplexNumber[matrix.length][matrix[0].length+1];
        for(int i = 0; i < matrix2.length; i++){
            for(int j = 0; j < matrix2[0].length; j++){
                int offset = 0;
                if(j == col) {
                    offset++;
                    matrix2[i][j] = v[i];
                    continue;
                }
                matrix2[i][j] = matrix[i][j-offset];
            }
        }
            
        
        return new Matrix(matrix2);
    }
    
    public Matrix rowRemoved(int row) {
        ComplexNumber[][] matrix2 = new ComplexNumber[matrix.length-1][matrix[0].length];
        int offset = 0;
        for(int i = 0; i < matrix.length; i++) {
            if(i == row) {
                offset++;
                continue;
            }
            for(int j = 0; j < matrix[0].length; j++) {
                matrix2[i-offset][j] = matrix[i][j];
            }
        }
        return new Matrix(matrix2);
    }
    
    /*
     * removed a column of matrix used in creating transpose matrix
     */
    public Matrix colRemoved(int col) {
        ComplexNumber[][] matrix2 = new ComplexNumber[matrix.length][matrix[0].length-1];
        for(int i = 0; i < matrix.length; i++)
        {
            for(int j = 0,currColumn=0; j < matrix[0].length; j++)
            {
                if(j != col)
                {
                    matrix2[i][currColumn++] = matrix[i][j];
                }
            }
        }
        return new Matrix(matrix2);
    }
    
    /*
     * returns array of asked row used in matrix multiplication
     */
    public ComplexNumber[] getRow(int i) {
        ComplexNumber[] toReturn = new ComplexNumber[matrix[0].length];
        for(int j = 0; j < matrix[0].length; j++) {
            toReturn[j] = matrix[i][j].clone();
        }
        
        return toReturn;
    }
    /*
     * returns array of asked column used in matrix multiplication
     */
    public ComplexNumber[] getCol(int j) {
        ComplexNumber[] toReturn = new ComplexNumber[matrix.length];
        for(int i = 0; i < matrix.length; i++) {
            toReturn[i] = matrix[i][j].clone();
        }
        
        return toReturn;
    }
    
    public ComplexNumber get(int r,int c) {
        return matrix[r][c];
    }
    public void set(ComplexNumber n, int r,int c) {
        matrix[r][c] = n.clone();
    }
    
    public int getRows() {
        return matrix.length; 
    }
    public int getCols() {
        return matrix[0].length; 
    }
    
    public String toString() {
        String toReturn = "";
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                toReturn +=  matrix[i][j].toString()+" ";
            }
            toReturn+="\n";
        }
        return toReturn;
    }
    
}
