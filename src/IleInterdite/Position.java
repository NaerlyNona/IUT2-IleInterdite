/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IleInterdite;

/**
 *
 * @author monnetlu
 */
public class Position implements Comparable<Position> {
    
    private int x;
    private int y;
    
    public Position(int x, int y){
        setX(x);
        setY(y);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int compareTo(Position o) {
        if ((this.getX() == o.getX()) && (this.getY() == o.getY()) ){
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Oui");
        return (this.getX() == ((Position)(obj)).getX()) && (this.getY() == ((Position)(obj)).getY());

    }

    
    
}
