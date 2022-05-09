/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package movielens;

/**
 *
 * @author fes77
 */
public class Car {
    private int fuel;
    private int weight;
    
    {
        weight = 1000;
        System.out.println("Inside first object initializer");
    }
    
    {
        weight += 1000;
        System.out.println("Inside second object initializer");
    }
    
    public Car()
    {
        
    }
    
    public Car(int weight)
    {
        this.fuel = 0;
        this.weight = weight;
    }
    
    public Car(int fuel, int weight)
    {
        this.fuel = fuel;
        this.weight = weight;
    }
    
    public void refuel()
    {
        fuel = 100;
        weight += 100;
    }

    @Override
    public String toString() {
        return "Car{" + "fuel=" + fuel + ", weight=" + weight + '}';
    }
    
    
    
    public static void main(String[] args)
    {
        Car car = new Car(){
            {
                refuel();
                System.out.println("Inside initializer block");
            }
        };
        
        System.out.println(car);
    }
}
