/*
 * this class holds all the matrix manipulation methods
 * 
 * Author: Tejas Navada
 * 12/17/22
 */
public class MatrixCalculator {
    
    
    public static boolean isInvertible(Matrix m) {
        return m.getRows()==m.getCols()&&!determinant(m).equals(new ComplexNumber());
    }
    
    
    
    public static boolean isLinIndependent(Matrix m) {
        return !determinant(m).equals(new ComplexNumber());
    }
    
    /*
     * calculates determinant through Laplace expansion
     */
    public static ComplexNumber determinant(Matrix m) {
        if(m.getRows()!=m.getCols()) {
            return null;
        }
        if(m.getRows() == 2 && m.getCols() == 2) {
            return (m.get(0, 0).mult(m.get(1, 1))).sub(m.get(1,0).mult(m.get(0, 1)));
        }
        
        ComplexNumber toReturn = new ComplexNumber();
        for(int i = 0;i < m.getRows();i++) {
            if(!m.get(i,0).equals(new ComplexNumber())) {
                toReturn = toReturn.add(m.get(i, 0).mult((new ComplexNumber(((i%2)*-1)+((i+1)%2)*1)).mult(determinant(m.remove(i, 0)))));
                
            }
        }
        return toReturn;
    }
    
    public static Matrix sub(Matrix m1, Matrix m2) {
        if(m1.getRows()!=m2.getRows()||m1.getCols()!=m2.getCols()){
            return null;
        }
        ComplexNumber[][] difference = new ComplexNumber[m1.getRows()][m1.getCols()];
        for(int i = 0; i < difference.length; i++) {
            for(int j = 0; j < difference[0].length; j++) {
                difference[i][j] = m1.get(i, j).sub(m2.get(i, j));
            }
        }
        Matrix toReturn = new Matrix(difference);
        return toReturn;
        
    }
    
    public static Matrix add(Matrix m1, Matrix m2) {
        if(m1.getRows()!=m2.getRows()||m1.getCols()!=m2.getCols()){
            return null;
        }
        ComplexNumber[][] sum = new ComplexNumber[m1.getRows()][m1.getCols()];
        for(int i = 0; i < sum.length; i++) {
            for(int j = 0; j < sum[0].length; j++) {
                sum[i][j] = m1.get(i, j).add(m2.get(i, j));
            }
        }
        Matrix toReturn = new Matrix(sum);
        return toReturn;
        
    }
    
    /*
     * gets product matrix by dotting row i of m1 with column j of m2 to get scalar
     * value for row i column j of product matrix
     */
    public static Matrix mult(Matrix m1, Matrix m2) {
        if(m1.getCols()!= m2.getRows()) {
            return null;
        }
        ComplexNumber[][] m = new ComplexNumber[m1.getRows()][m2.getCols()];
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[0].length; j++) {
                m[i][j] = dot(m1.getRow(i),m2.getCol(j));
            }
        }
        Matrix toReturn = new Matrix(m);
        return toReturn;
    }
    
    public static Matrix div(Matrix m1,ComplexNumber toDiv) {
        ComplexNumber[][] d = new ComplexNumber[m1.getRows()][m1.getCols()];
        for(int i = 0; i < d.length; i++) {
            for(int j = 0; j < d[0].length; j++) {
                d[i][j] = m1.get(i, j).div(toDiv);
            }
        }
        return new Matrix(d);
    }
    
    private static ComplexNumber dot(ComplexNumber[] v1, ComplexNumber[] v2) {
        ComplexNumber product = new ComplexNumber();
        for(int i = 0; i < v1.length; i++) {
            product = product.add(v1[i].mult(v2[i]));
        }
        return product;
        
    }
    
    public static Matrix transpose(Matrix m) {
        Matrix toReturn = new Matrix();
        for(int j = 0; j < m.getCols(); j++) {
            toReturn = toReturn.rowAdded(j, m.getCol(j));
        }
        return toReturn;
        
    }
    
    public static Matrix cofactor(Matrix m) {
        ComplexNumber[][] c = new ComplexNumber[m.getRows()][m.getCols()];
        for(int i = 0; i < c.length; i++) {
            for(int j = 0; j < c[0].length; j++) {
                c[i][j] = determinant(m.remove(i, j));
                if((i+j)%2==1) {
                    c[i][j]= c[i][j].mult(new ComplexNumber(-1));
                }
            }
        }
        return new Matrix(c);
    }
    
    /*
     * gets inverse by finding cofactor matrix, transposing cofactor matrix to
     * get adjoint matrix, then dividing adjoint matrix by the determinant to
     * get inverse matrix
     */
    public static Matrix inverse(Matrix m) {
        if(!isInvertible(m)) {
            
            return null;
        }
        Matrix inverse = div(transpose(cofactor(m)),determinant(m));
        return inverse;
    }
    
    /*
     * minimizes amount of multiplications
     */
    public static Matrix power(Matrix m,  int p) { 
        if(m.getRows()!=m.getCols()) {
            return null;
        }
        if (p==0) 
            return Matrix.identity(m.getRows());
        Matrix temp = power(m, p/2);
        if(temp == null)
            return null;
        if (p % 2 == 0)
           return mult(temp,temp);
        else{
           if(p > 0)
               return mult(mult(temp,temp),m);
           else
               if(!isInvertible(m)) {
                   return null;
               }
               return mult(mult(temp,temp),inverse(m));
        }
    }

}
