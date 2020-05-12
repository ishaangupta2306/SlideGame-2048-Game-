import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.geometry.Insets;
import java.util.*;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import java.util.Random;
import java.util.Arrays;
/**
 * A class Representing the SlideGame
 * @author Ishaan Gupta
 */
public class SlideGame extends Application
{
  //Stores address of stage
  private Stage stage;
  //Stores address of Scene
  private Scene scene;
  //Stores number of rows of the board
  private int rows;
  //Stores number of columns of the board
  private int columns;  
  //Stores address of Integer array of board
  private int board[][];
  //Stores address of array of button
  private Button buttonS[][]; 
  //Stores the address of Border
  private BorderPane border = new BorderPane();
  private GridPane grid = new GridPane();
  //Tracks if a new random move should be made
  private boolean makeMove = false; 
  //Tracks if the game started
  private boolean gameStarted = false;
  //The level of the game
  private String levelOfGame = "normal";
  /**
   * Number of Random numbers to be put after a new move depending on the levelOfGame
   * If levelOfGame = normal, numberAtATime = 1
   * else levelOfGame = Pro, numberAtATime = 2
   */
  private int numberAtATime;
  //Stores the baseBackground
  private static BackgroundFill baseBackground = new BackgroundFill(Color.BURLYWOOD, CornerRadii.EMPTY,new Insets(2,2,2,2));
  //Field used for background of White player
  private static BackgroundFill fillWhite = new BackgroundFill(Color.RED, new CornerRadii(5),new Insets(5,5,5,5));
  
  /**
   * Represents the 8 directions on the board   
   * Left,right,up,down,Left diagonal up, right diagonal up, left diagonal down,right diagonal down
   */
  public enum Direction{LT,RT,UP,DN,LDU,RDU,LDD,RDD};
  
  public void start(Stage primaryStage)
  {
    //Local variable to store the list of parameters
    List<String> list = getParameters().getRaw();
    //Local variable to store the iterator of list
    Iterator<String> listIterator = list.listIterator();    
    //Local variable to store the content of the list
    Object array[] = new Object[3];
    //Local variable used a counter
    int k = 0;
    //Local Variable
    String level; 
    
    //Loop to extract the input parameter
    try{
    while(listIterator.hasNext())
      array[k++] = listIterator.next();
    }
    catch(ArrayIndexOutOfBoundsException e)
    {      
      System.out.println("Invalid input....Play with default board and level");
      levelOfGame = "normal";      
      rows = 10;
      columns = 10;
    }
      
   
    //If level of game, rows and columns of game input
    if(k == 3)
    {
      if(array[0].toString().compareToIgnoreCase("normal") == 0 || array[0].toString().compareToIgnoreCase("pro") == 0)
        levelOfGame = array[0].toString();
      else
        System.out.println("Invalid level....play with default level with your desired board");
        
      try{
      rows = Integer.parseInt(array[1].toString());
      columns = Integer.parseInt(array[2].toString());
      }
      catch(NumberFormatException e)
      {
        System.out.println("Invalid input....play with default board with your desired level");
        rows = 10;
        columns = 10;
      }
    }
    
    //If rows and columns of game input
    else if(k == 2)
    {
      levelOfGame = "normal";
      try{
      rows = Integer.parseInt(array[0].toString());
      columns = Integer.parseInt(array[1].toString());
      }
      catch(NumberFormatException e)
      {
        System.out.println("Invalid input....play with default board and level");
        rows = 10;
        columns = 10;
      }
    }
    
    //If the level of game input
    else if(k == 1)
    {
      if(array[0].toString().compareToIgnoreCase("normal") == 0|| array[0].toString().compareToIgnoreCase("pro") == 0)
        levelOfGame = array[0].toString();
      else
        System.out.println("Invalid level....play with default level and board");
      rows = 10;
      columns = 10;          
    }
    
    //If nothing input, default values
    else if(k == 0)
    {
      levelOfGame = "normal";      
      rows = 10;
      columns = 10;
      if(k == 0)
        System.out.println("Play with default board and level");
     
    }
  
      
    if(levelOfGame.equals("normal"))
      numberAtATime = 1;
    else
      numberAtATime = 2;
    
    //Initialization of Game mechanic 
    board =  new int[rows][columns];
    buttonS = new Button[rows][columns];
    gameStarted = true;
    
    //Loop to initialize the buttons with background
    for(int i = 0; i < board.length; i++)
      for(int j = 0;j < board[i].length; j++)
    {
      buttonS[i][j] = new Button();      
      buttonS[i][j].setBackground(new Background(baseBackground));
      buttonS[i][j].setMinWidth(80);
      buttonS[i][j].setMinHeight(80);
      grid.add(buttonS[i][j], j+1, i+1);        
      buttonS[i][j].setOnAction(new ButtonAction());   
    }
    
    //Grid put on borderPane
    border.setCenter(grid);
    //First random entry
    newEntry();
    //Scene of start button
    Scene startScene = new Scene(border);
    //Stage set
    stage = primaryStage;
    // Add the "scene" to the main window   
    stage.setScene(startScene);
    //Stage is presented
    stage.show();
  }
  
  /**
   * Class representing the action of buttons
   * @author Ishaan Gupta
   */
  public class ButtonAction implements EventHandler<ActionEvent> 
  {
    public void handle(ActionEvent e)
    {
      //Local variable to store the address of the clicked button
      Button b = (Button)e.getSource(); 
      
      //Loop to iterate over the button 
      for(int i = 0; i < rows; i++)
        for(int j = 0; j < columns; j++)
      {       
        if(b == buttonS[i][j])    
          move(i,j);
      }
      //If merging happened
      if(makeMove == true)
      {
        newEntry();
        makeMove = false;
      }
    }  
  }
  
  /**
   * Helper Method which performs merging in a particular direction
   * @param d Direction
   * @param row row index
   * @param column column index
   */
  public void merge(Direction d, int row, int column)
  {
    //Indices of finalizing values
    int r = 0, x = 0, y = 0, c = 0, iR = -1, iC = -1;
    
    switch(d)
    {
      case LT : x = 0;  y = 1;   r = row;            c = 0;                 iR = row;          iC = -1;              break;
      case RT : x = 0;  y = -1;  r = row;            c = board[0].length-1; iR = row;          iC = board[0].length; break;
      case UP : x = 1;  y = 0;   r = 0;              c = column;            iR = -1;           iC = column;          break;
      case DN : x = -1; y = 0;   r = board.length-1; c = column;            iR = board.length; iC = column;          break;
      case LDU: x = 1;  y = 1;   r = row;            c = column;            iR = -1;           iC = -1;              break;
      case RDU: x = 1;  y = -1;  r = row;            c = column;            iR = 0;            iC = board[0].length; break;
      case RDD: x = -1; y = -1;  r = row;            c = column;            iR = board.length; iC = board[0].length; break;
      case LDD: x = -1; y =  1;  r = row;            c = column;            iR = board.length; iC = -1;              break;      
    }
    //Local variable to track merging
    int temp = 0;
    //Loop to perform merging
    for(int i = r, j = c; i > -1 && i < board.length && j > -1 && j < board[0].length; i+=x, j+=y)
    {
      if(board[i][j] != 0)
      {
        temp++;
        
        if(iR > -1 && iR < board.length && iC > -1 && iC < board[0].length && (board[iR][iC] == board[i][j])) 
        {
          board[iR][iC] *= 2;
          if(gameStarted)
          {
            buttonS[iR][iC].setText(Integer.toString(board[iR][iC]));
            setButtonColor(iR,iC);
          }
          board[i][j] = 0;
          if(gameStarted)
          {
            buttonS[i][j].setText("");
            setButtonColor(i,j);
          }
          makeMove = true;
        }
        
        else
        {
          if( temp ==1 && !(d == Direction.LT || d == Direction.RT))
          {
            iR = row;
            iC = column;
          }
          
          else
          {
            iR += x;
            iC += y;
          }
          
          board[iR][iC] = board[i][j];
          if(gameStarted)
          {
            buttonS[iR][iC].setText(Integer.toString(board[iR][iC]));
            setButtonColor(iR,iC);          
          }
          if((d == Direction.LT || d == Direction.RT) && iC != j)
          {
            board[i][j] = 0; 
            if(gameStarted)
            {
              buttonS[i][j].setText("");
              setButtonColor(i,j);
            }
            makeMove = true;
          }
          
          else if((d == Direction.UP || d == Direction.DN) && iR != i)
          {
            board[i][j] = 0;  
            if(gameStarted)
            {
              buttonS[i][j].setText("");
              setButtonColor(i,j);
            }
            makeMove = true;
          }
          
          else if((d == Direction.LDU || d == Direction.LDD || d == Direction.RDD || d == Direction.RDU) && iR != i && iC != j)
          {
            board[i][j] = 0;  
            if(gameStarted)
            {
              buttonS[i][j].setText("");
              setButtonColor(i,j);
            }
            makeMove = true;
          }
        }
      }
    }
  }  

  /**
   * Helper method which performs merging for all values in a particular direction
   * @param r row index
   * @param c column index
   */
  public void move(int r, int c)
  {
    if(r == 0)
    {
      if(c == 0)
      {
        //mergeBoard(Direction.LDU);
        for(int i = 0; i < board.length; i++)
          merge(Direction.LDU,i,0);
        
        for(int i = 0; i < board[0].length-1; i++)
          merge(Direction.LDU,0,i);
      }
      else if(c == board[0].length -1)
      {
        //mergeBoard(Direction.RDU);
        for(int i = 0; i < board.length; i++)
          merge(Direction.RDU,i,board[0].length-1);
        
        for(int i = 0; i < board[0].length-1; i++)
          merge(Direction.RDU,0,i);
      }
      
      else
      {
        //mergeBoard(Direction.UP);
        for(int i = 0; i < board[0].length; i++)
          merge(Direction.UP,0,i);
      }      
    }
    
    else if(r == board.length -1)
    {
      if(c == 0)
      {
        //mergeBoard(Direction.LDD);        
        for(int i = 0; i < board.length; i++)
          merge(Direction.LDD,i,0);
        
        for(int i = 0; i < board[0].length; i++)
          merge(Direction.LDD,board.length-1,i);
      }  
      
      else if(c == board[0].length -1)
      {
        //mergeBoard(Direction.RDD);
        for(int i = 0; i < board.length; i++)
          merge(Direction.RDD,i,board[0].length-1);
        
        for(int i = 0; i < board[0].length; i++)
          merge(Direction.RDD,board.length-1,i);
      }
      
      else
      {
        //mergeBoard(Direction.DN);
        for(int i = 0; i < board[0].length; i++)
          merge(Direction.DN,board.length-1,i);
      }        
    }
    
    else
    {
      if(c == 0)
      {
        //mergeBoard(Direction.LT);
        for(int i = 0; i < board.length; i++)
          merge(Direction.LT,i,0);
      }
      
      else if(c == board[0].length-1)
      {
        //mergeBoard(Direction.RT);
        for(int i = 0; i < board.length; i++)
          merge(Direction.RT,i,board[0].length);
      }      
    }    
  }
  
  /**
   * Helper method which makes a new entry after a successful merging
   */
  private void newEntry()
  {    
    for(int i = 0; i < numberAtATime; i++)
    {
      Random rand = new Random();
      int r = rand.nextInt(rows);
      int c = rand.nextInt(columns);
      //Loop for random position
      while(board[r][c] != 0)
      {
        r = rand.nextInt(rows);
        c = rand.nextInt(columns);
      }
      Random rand2 = new Random();    
      int x = rand2.nextInt(5);
      //Loop for random value
      while(x == 3 || x == 0)
        x = rand2.nextInt(5);
      
      board[r][c] = x;
      buttonS[r][c].setText(Integer.toString(x));    
      setButtonColor(r,c);
    }
  }  

  /**
   * Helper method to set the color of button depending on it's value
   * @param i row index
   * @param j column index
   */
  private void setButtonColor(int i, int j)
  {    
    if(board[i][j] == 0)
      buttonS[i][j].setBackground(new Background(baseBackground));
    else if(board[i][j] == 1)
      buttonS[i][j].setBackground(new Background(baseBackground));
    else if(board[i][j] == 2)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, CornerRadii.EMPTY,new Insets(1,1,1,1)))); 
    else if(board[i][j] == 4)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.ORANGERED, CornerRadii.EMPTY,new Insets(1,1,1,1)))); 
    else if(board[i][j] == 8)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY,new Insets(1,1,1,1)))); 
    else if(board[i][j] == 16)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.SPRINGGREEN, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 32)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY,new Insets(1,1,1,1))));           
    else if(board[i][j] == 64)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.MEDIUMSPRINGGREEN, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 128)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.MAGENTA, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 256)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.CYAN, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 512)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 1024)
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY,new Insets(1,1,1,1))));
    else if(board[i][j] == 2048)            
      buttonS[i][j].setBackground(new Background(new BackgroundFill(Color.DARKMAGENTA, CornerRadii.EMPTY,new Insets(1,1,1,1))));
  }
  
  /**
   * Getter method for board
   * @eturn int[][]
   */
  public int[][] getBoard()
  {
    return board;
  }
  
  
  /**
   * Setter method for board
   * @param newBoard new board
   */
  public void setBoard(int [][] newBoard)
  {
    this.board = newBoard;
  }
  
  
  public static void main(String args[])
  {
    Application.launch(args);
  }
}




