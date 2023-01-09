/*
 * This class represents a complex number with both a real and imaginary component
 * 
 * Author: Tejas Navada
 * 12/17/22
 */
public class ComplexNumber {
    
    private double real;
    private double imaginary;
    
    public ComplexNumber(double r, double i) {
        real = r;
        imaginary = i;
    }
    
    public ComplexNumber(double r) {
        real = r;
        imaginary = 0;
    }
    
    public ComplexNumber(int r) {
        real = (double)r;
        imaginary = 0;
    }
    
    public ComplexNumber() {
        real = 0;
        imaginary = 0;
    }
    
    public ComplexNumber add(ComplexNumber toAdd) {
        return new ComplexNumber(this.real+toAdd.real,this.imaginary+toAdd.imaginary);
    }
    public ComplexNumber sub(ComplexNumber toSub) {
        return new ComplexNumber(this.real-toSub.real,this.imaginary-toSub.imaginary);
    }
    
    public ComplexNumber mult(ComplexNumber toMult) {
        return new ComplexNumber((this.real*toMult.real)-(this.imaginary*toMult.imaginary),(this.real*toMult.imaginary) + (toMult.real*this.imaginary));
    }
    
    public ComplexNumber div(ComplexNumber toDiv) {
        if(toDiv.imaginary == 0||toDiv.imaginary !=toDiv.imaginary) {
            return new ComplexNumber(this.real/toDiv.real,this.imaginary/toDiv.real);
        }
        ComplexNumber conj = this.conjugate();
        return this.mult(conj).div(toDiv.mult(conj));
        
    }
    
    public ComplexNumber conjugate() {
        ComplexNumber conj = new ComplexNumber(real,-1*imaginary);
        return conj;
    }
    public ComplexNumber clone() {
        return new ComplexNumber(real,imaginary);
    }
    
    public boolean equals(ComplexNumber other) {
        return this.real == other.real && this.imaginary == other.imaginary;
    }

    public String toString() {
        if(imaginary==0||imaginary!=imaginary) {
            return String.valueOf(real);
        }
        return real+" + "+imaginary+"i";
    }
}
