/*CSCI 1101 Final Project - This class will instance the grid board, containing empty spaces, obstacles, objectives and characters*/

import java.util.Random;
public class GridMap {
  
	private LinkedList[][] grid = new LinkedList[1][8];
	//private static int turn = 0;
	
	public GridMap() { // Populating the grid with empty spaces
		for(int i = 0; i < 8; i++) { // for loop for each row
			grid[0][i] = new LinkedList();
			for (int j = 0; j < 8; j++){ //inner for loop for creating each element in the linked list
				Node temp = new Node(new EmptySpace(), grid[0][i].getFront());
				grid[0][i].addToFront(temp);
			}
		}
	}

	public void generateObstacles() { //populate the board with obstacles
		Random rnd = new Random();
		int target = rnd.nextInt(10); //creating a target for the guess, currently 10% chance a square will contain an obstle
		int guess;
		for (int i = 0; i < 8; i++ ) {//calling each linked list element in grid.
			Node temp = grid[0][i].getFront(); //starting at the front, then iterating through the list.
			int count = 0; //keep track of where to place the new obstacle
			while (temp != null) {
				guess = rnd.nextInt(10);
				if (guess == target && temp.getEmptySpace() != null) { //guess is target and the node is an empty space
					Obstacle obst = new Obstacle();//a new obstacle to place
					grid[0][i].replace(count,new Node(obst,temp.getNext())); //replacing the element at temp with an obstacle
				}
				temp = temp.getNext(); //updating
				count++;
			}	
		}	
	}
	public void  generateObjective() {//place an objective in each corner
		grid[0][0].replace(0, new Node(new Objective("B"), grid[0][0].getFront().getNext()));//place blue teams objective in top left
		grid[0][7].replace(7, new Node(new Objective("R"), grid[0][7].getNode(7).getNext()));//place red teams objective in bottom right
		
	}
	
	public void generateCharacter(Character ch1, Character ch2, Character ch3, String team) { //place three characters on the board, 
		if (team.equals("blue")){
			grid[0][0].replace(1, new Node(ch1, grid[0][0].getNode(1).getNext())); //placing around  blue objective
			grid[0][1].replace(1, new Node(ch2, grid[0][1].getNode(1).getNext()));
			grid[0][1].replace(0, new Node(ch3, grid[0][1].getFront().getNext()));
		}
		else if (team.equals("red")){
			grid[0][6].replace(6, new Node(ch1, grid[0][6].getNode(6).getNext())); //placing around red objective
			grid[0][6].replace(7, new Node(ch2, grid[0][6].getNode(7).getNext()));
			grid[0][7].replace(6, new Node(ch3, grid[0][7].getNode(6).getNext()));
		}
	}
	
	//Display the string map
	//Used only during demo, might be removed for full game
	public void  displayMap() {
		for(int i = 0; i < 8; i++){
			System.out.println();
			Node temp = grid[0][i].getFront();
			while (temp != null){
				if (temp.getObstacle() != null){
					System.out.print(temp.getObstacle().getName()+" - ");
				}
				else if (temp.getEmptySpace() != null){
					System.out.print(temp.getEmptySpace().getData()+" - ");
				}
				else if (temp.getObjective() != null) {
					System.out.print(temp.getObjective() + " - ");
				}
				else if(temp.getCharacter() != null) {
					System.out.print(temp.getCharacter().getName()+" - ");
				}
				temp = temp.getNext();
			}
		}
		System.out.println();
	}
	
	//Moves the character to a another spot on the board
	//If there is an obstacle, a character or an objective, do not move the character
	public void moveChar(String direction, Character c){
      int index = 0;
      int i;
      
      //Finds the character in the array of LinkedList
      for (i=0; i < 8; i++){
    	 index = 0;
         Node n = grid[0][i].getFront();
         while (index < 8 && grid[0][i].getNode(index).getCharacter() != c){
            n = n.getNext();
            index++;
         }
         if (index < 8)
        	 break;
      }
      boolean walk = false;
      //Move the character in the specified direction
      if(grid[0][i].getNode(index).getCharacter()!=null){
         Node temp=null, temp2=null;
         EmptySpace e= new EmptySpace();
         
         if (direction.equals("right")){
             temp = grid[0][i].getNode(index);
             if(index+1 < 8){
               temp2= grid[0][i].getNode(index+1);
               if(temp2.getEmptySpace() != null)
            	   walk = true;
             }
         }        
         if (direction.equals("left")){
             temp = grid[0][i].getNode(index);
             if(index-1 >= 0) {
               temp2= grid[0][i].getNode(index-1);
               if(temp2.getEmptySpace() != null)
            	   walk = true;
             }
         } 
         if (direction.equals("down")){
             temp = grid[0][i].getNode(index);
             if(i+1 < 8){
               temp2= grid[0][i+1].getNode(index); 
               if(temp2.getEmptySpace() != null)
            	   walk = true;
             }
         }    
         if (direction.equals("up")){
             temp = grid[0][i].getNode(index);
             if(i-1 < 8){
               temp2= grid[0][i-1].getNode(index); 
               if(temp2.getEmptySpace() != null)
            	   walk = true;
             }
         }                       
         if(walk == true){
            temp.setData(e);
            temp2.setData(c);
            //turn++;
            walk = false;
         }
      }
   }
	//Get method for the grid LinkedList Double Array
	public LinkedList[][] getGrid(){
		return grid;
	}
	public void removeChar(Character c) {//removes a character
		for (int i = 0; i < 8; i++ ) {//calling each linked list element in grid.
			Node temp = grid[0][i].getFront(); //starting at the front, then iterating through the list.
			while (temp != null) {
				if (temp.getEmptySpace() == null && temp.getObstacle() == null && temp.getObjective() == null && temp.getCharacter().equals(c)) { 
					EmptySpace emp = new EmptySpace(); 
					temp.setData(emp);
					
				}
				temp = temp.getNext(); //updating

			}
		}
	}
}
