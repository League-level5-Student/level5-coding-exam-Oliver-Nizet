package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		
		String count1 = JOptionPane.showInputDialog("How many robots?");
		int count = Integer.parseInt(count1);
		String color = JOptionPane.showInputDialog("What color? (Red, Green, or Blue)");
		String sides1 = JOptionPane.showInputDialog("How many sides? (>0)");
		int sides = Integer.parseInt(sides1);
		Robot[] robots = new Robot[count];	
		for(int i = 0; i < count; i++) {
			//Initializing Objects in the Arrays
			robots[i] = new Robot();
			//Using equations to place robots in a 4x3 grid (max size)
			if(i > 7) {
				robots[i].setX(100+((i-8)*200));
			} else if (i > 3) {
				robots[i].setX(100+((i-4)*200));
			} else {
				robots[i].setX(100+(i*200));
			}
			robots[i].setY(100+((i/4)*200));
			if(color.equalsIgnoreCase("red")) {
				robots[i].setPenColor(255,0,0);
			} else if(color.equalsIgnoreCase("green")) {
				robots[i].setPenColor(0,255,0);
			} else {
				robots[i].setPenColor(0,0,255);
			}
		}
		Thread[] threads = new Thread[count];
		for(int i = 0; i < count; i++) {
			Robot r = robots[i];
			threads[i] = new Thread(() -> {drawShape(r,sides);});
			threads[i].start();
		}
	}
	static void drawShape(Robot r, int sides) {
		r.penDown();
		r.setSpeed(100);
		for(int i = 0; i < sides; i++) {
			r.move(50);
			r.turn(180-((180*sides-360)/sides));
		}
		r.hide();
	}
}
