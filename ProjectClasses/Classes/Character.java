/* This Class Contains the attributes and the methods of a Character class
 * Started March 25, 2017
 */
import javafx.scene.image.*;
public class Character {
   //type of character
   private int type;
   //Team this character belongs to (Either R(red) or B(blue))
   private String team;
   //Name of the character
   private String name;
	//hp is the hit points (Health) of the character)
   private int hp;
	//Strength is the physical damage done to a character
   private int strength;
   //Image of character
   private Image charImage;
	//Magic is the magic damage done to a character
	private int magic;
	//resistance is the magic defense
	private int resistance;
	//physical defense
	private int defense;
	//XP that increases when the character attacks/kills an enemy
	private boolean alive;
	
	//Constructor that sets alive as true
	public Character(String n, String t,int hp, int str,int mag, int res, int def){
		name = n;
		team = t;
		this.hp = hp;
		strength = str;
		magic = mag;
		resistance = res;
		defense = def;
		alive = true;
	}
	
	public Character(String n, String t){
		name = n;
		team = t;
		type = 1;
	}
	
	//Method that calculates the damage done to an enemy unit
	//Takes into account the strength of the character
	public double attack(){
		//The damage done by the character can be calculated in a different way
      //ranger
      if(type == 0)
    	  if((int)(Math.random()*10) == 1)
    		  return strength*3;
    	  else
    		  return strength*1.5;
      //warrior
      else if (type == 1)
    	  if((int)(Math.random()*20) == 1)
    		  return strength*2;
    	  else
    		  return strength;
      //mage
      else if (type == 2)
         return magic*2;
      //tank
      else
         return strength;
	}
   
   //sets type of character
   public void setType(int t)
   {
      type = t;
   }
   public void setName(String name) {
	   this.name = name;
   }
   
   public int getType()
   {
      return type;
   }
	
	//Method that calculates the damage taken from an attack
	//Removes the HP taken from another characters damage
	//also checks if the character is dead (i.e if hp is less than or equal to 0)
	public void defend(Character r){
      //checks if attacking character is a mage or not
      if(r.getType() == 2){
    	  if(getType() == 3)
    		  hp = (int)(hp - (r.attack()*2/resistance));
    	  else
    		  hp = (int)(hp - (r.attack()/resistance));
      }
      else 
		   hp = (int)(hp - (r.attack()/defense));
		if (hp <= 0){
			hp = 0;
			alive = false;
		}
	}
	
	
	//Method that checks if the character is alive.
	//If not, sets the alive attribute to false
	public boolean isAlive(){
		return alive;
	}
	
	//Get name
	public String getName(){
		return name;
	}
	
	//Get team name 
	public String getTeam(){
		return team;
	}
   
   public int getHp(){
      return hp;
   }
   public void setImage(Image image){
	   charImage = image;
   }
   public Image getImage(){
	   return charImage;
   }

   public int getStrength(){
	   return strength;
   }
   
   public int getMagic(){
	   return magic;
   }
   public int getResistance(){
	   return resistance;
   }
   public int getDefense(){
	   return defense;
   }
	
}