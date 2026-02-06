/*************************************************************************
* Assignment2 for CSCI 271-001 Spring 2026
*
* Author: Tommy Quinlan
* OS: Windows 11
* Compiler: javac 25.0.1
* Date: February 6, 2026
*
* Purpose
* This class will display numbers as fractions after implementing different operations
* like addition, subtraction,multiplication, and division. The program allows for infinity
* and NaN values in case the fraction returns an infinite number or is not a number.
*************************************************************************/

/*******************************************************************
* I declare and confirm the following:
* - I have not discussed this program code with anyone other than my
* instructor or the teaching assistants assigned to this course.
* - I have not used programming code obtained from someone else,
* or any unauthorised sources, including the Internet, either
* modified or unmodified.
* - If any source code or documentation used in my program was
* obtained from other sources, like a text book or course notes,
* I have clearly indicated that with a proper citation in the
* comments of my program.
* - I have not designed this program in such a way as to defeat or
* interfere with the normal operation of the supplied grading code.
*
* <Tommy Quinlan>
* <W30713461>
********************************************************************/

public class Fraction {

    long num; // numerator of fraction
    long den; // denominator of fraction

    /***************************** Fraction *****************************
    * Description: Creates a fraction using a numerator and denominator
    * and then normalizesthe answer.
    *
    * Parameters:
    * a – numerator (input)
    * b – denominator (input)
    *
    * Pre: None
    * Post: Normalized fraction
    * Returns: None
    * Calls: normalize
    *********************************************************************/
    Fraction(long a, long b) {
        num = a;
        den = b;
        normalize();
    }

    /***************************** Fraction *****************************
    * Description: Creates a fraction with 1 as the denominator.
    *
    * Parameters:
    * a – numerator (input)
    *
    * Pre: None
    * Post: The fraction represents a / 1.
    * Returns: None
    * Calls: None
    *********************************************************************/
    Fraction(long a) {
        num = a;
        den = 1;
    }

    /***************************** normalize *****************************
    * Description: This function normalizes the fraction by handling different cases like
    * zero cases, positive denominators, and reducing the fraction.
    *
    * Parameters: None
    *
    * Pre: num and den are initialized
    * Post: Fraction is in reduced, normalized form
    * Returns: None
    * Calls: gcd
    *********************************************************************/
    void normalize() {

        // 0/0 or undefined
        if (num == 0 && den == 0)
            return;

        // when dividing by 0
        if (den == 0) {
            num = (num < 0) ? -1 : 1;
            return;
        }

        // make denominator positive
        if (den < 0) {
            num = -num;
            den = -den;
        }

        long g = gcd(num, den); // Greatest common divisor
        num = num / g;
        den = den / g;
    }

    /******************************* gcd ********************************
    * Description: Computes the greatest common divisor of two numbers
    * using the Euclid's formula.
    *
    * Parameters: a, b
    *
    * Pre: None
    * Post: a contains the GCD
    * Returns: Greatest common divisor
    * Calls: None
    *********************************************************************/
    long gcd(long a, long b) {

        // assure numerator is non-negative
        if (a < 0)
            a = -a;

        // Euclidean algorithm
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }

        // no dividing by zero
        if (a == 0)
            a = 1;

        return a;
    }

    /***************************** toString *****************************
    * Description: Changes the fraction to string
    *
    * Parameters: None
    *
    * Pre: Fraction initialized
    * Post: None
    * Returns: String of fraction
    * Calls: None
    *********************************************************************/
    public String toString() {
        if (num == 0 && den == 0) return "NaN";
        if (den == 0) return (num < 0) ? "-Infinity" : "Infinity";
        if (den == 1) return "" + num;
        return num + "/" + den;
    }

    /******************************* add ********************************
    * Description: Adds two fractions.
    *
    * Parameters: f
    *
    * Pre: f initialized
    * Post: None
    * Returns: Sum of fractions
    * Calls: Fraction constructor
    *********************************************************************/
    Fraction add(Fraction f) {
        return new Fraction(num * f.den + f.num * den, den * f.den);
    }

    /***************************** subtract *****************************
    * Description: Subtracts one fraction from another.
    *
    * Parameters:f
    *
    * Pre: f initialized
    * Post: None
    * Returns: Difference of fractions
    * Calls: Fraction constructor
    *********************************************************************/
    Fraction subtract(Fraction f) {
        return new Fraction(num * f.den - f.num * den, den * f.den);
    }

    /***************************** multiply *****************************
    * Description: Multiplies two fractions.
    *
    * Parameters:f
    *
    * Pre: f initialized
    * Post: None
    * Returns: Product of fractions
    * Calls: Fraction constructor
    *********************************************************************/
    Fraction multiply(Fraction f) {
        return new Fraction(num * f.num, den * f.den);
    }

    /******************************* divide *****************************
    * Description: Divides one fraction by another.
    *
    * Parameters:f
    *
    * Pre: f.num is not zero
    * Post: None
    * Returns: Quotient of fractions
    * Calls: Fraction constructor
    *********************************************************************/
    Fraction divide(Fraction f) {
        return new Fraction(num * f.den, den * f.num);
    }

    /******************************* negate *****************************
    * Description: Negates the fraction.
    *
    * Parameters: None
    *
    * Pre: None
    * Post: None
    * Returns: Negated fraction
    * Calls: Fraction constructor
    *********************************************************************/
    Fraction negate() {
        return new Fraction(-num, den);
    }

    /******************************** pow *******************************
    * Description: Calls multiply repeatedly to return a fraction to the 
    * power of n.
    *
    * Parameters:n
    *
    * Pre: None
    * Post: None
    * Returns: Fraction raised to power n
    * Calls: multiply, Fraction constructor
    *********************************************************************/
    Fraction pow(int n) {

        Fraction r = new Fraction(1); // result comes in here

        // For positive exponents
        if (n > 0)
            for (int i = 0; i < n; i++)
                r = r.multiply(this);

        // For negative exponents
        if (n < 0) {
            Fraction inverse = new Fraction(den, num);
            for (int i = 0; i > n; i--)
                r = r.multiply(inverse);
        }

        return r;
    }

    /******************************* main *******************************
    * Description: Contains tests for testing the functionality of the 
    * Fraction function.
    *
    * Parameters:args
    * 
    * Pre: None
    * Post: Print out test cases
    * Returns: None
    * Calls: Fraction methods
    *********************************************************************/
    public static void main(String[] args) {

        Fraction a = new Fraction(16);
        Fraction b = new Fraction(3, 5).add(new Fraction(7));
        Fraction c = new Fraction(6, 7);

        System.out.println(c.multiply(a.divide(b))); // 240/133
        System.out.println(new Fraction(6, -24));    // -1/4
        System.out.println(new Fraction(23, 0));     // Infinity
        System.out.println(new Fraction(0, 0));      // NaN
        System.out.println(new Fraction(2, 3).pow(3));   // 8/27
        System.out.println(new Fraction(2, 3).pow(-2));  // 9/4
        System.out.println(new Fraction(-6, 0)); // -Infinity
        System.out.println(new Fraction(7, 1)); // 7 (not 7/1)
        System.out.println(new Fraction(8, -6));    // -4/3
    }
}
