package intro;

public class Rectangle {
    //data fields
    private double width;
    private double height;
    private String color;
    
    
    private static int numOfRectangles = 0;
    // static (a class of rectangles)
    
    
    //methods 
    public Rectangle(double x, double y){
        width = x;
        height = y;
        color = c;
        numOfRectangles++;
    }

    public double area(){
        return width*height;
    }
    
    public double getWidth() {
    	return width;
    }
    
    public double getHeight() {
    	return height;
    }
    
    public setColor(String color) {
    	this.color = color;
    }
    
    public void setWidth(double width) {
    	this.width = width;
    }
    
    public void setHeight(double height) {
    	this.height = height;
    }
    
    public static int getNumOfRectangles() {
    	return numOfRectangles;
    }
    
    public static void main(String[] args){
        //TODO Auto-generated method sub
        Rectangle r1 = new Rectangle(3.0, 4.0, "Blue");
        Rectangle r2 = new Rectangle(2.0, 4.0, "Blue");
        Rectangle r3 = new Rectangle(3.0, 6.0, 'Blue');
        
       
        
        System.out.println("Height is a private variable but I am in class so I can access it. "
        +"The height of the rectangle is " + r1.getHeight());

        double a = r1.area();
        System.out.println("The area of this rectangle is "+ a);
        
        //double a2 = r2.area();
        
        
        int n = Rectangle.numOfRectangles;
        System.out.println(n);
    }

}
