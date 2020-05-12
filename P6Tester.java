import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class P6Tester
{
  /**
   * Test for the SlideGame class
   * @author Ishaan Gupta
   */
  
  
  @Test
  public void testMerge()
  {
    /**
     * Test 0, 1 & many
     */
    
    //Test 0 (when there are 0 elements in the Array)
    SlideGame s = new SlideGame();
    int b[][] = {{0,0,0,0,0},{0,0,0,0,0}};
    int c[][] = {{0,0,0,0,0},{0,0,0,0,0}};
    s.setBoard(b);
    s.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c,s.getBoard());
    
    //Test 1 (when there are 1 elements in the Array)
    SlideGame s1 = new SlideGame();
    int b1[][] = {{0,1},{0,0}};
    int c1[][] = {{1,0},{0,0}};
    s1.setBoard(b1);
    s1.merge(SlideGame.Direction.LT,0,1);
    assertArrayEquals("Testing merge method for LD",c1,s1.getBoard());
    
    //Test Many (when there are more than 1 elements in the Array)
    SlideGame s2 = new SlideGame();
    int b2[][] = {{1,0,1,1,2},{0,0,0,0,0}};
    int c2[][] = {{2,1,2,0,0},{0,0,0,0,0}};
    s2.setBoard(b2);
    s2.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c2,s2.getBoard());
    
    SlideGame s3 = new SlideGame();
    int b3[][] = {{1,0,1,0,2},{0,0,0,0,0}};
    int c3[][] = {{4,0,0,0,0},{0,0,0,0,0}};
    s3.setBoard(b3);
    s3.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c3,s3.getBoard());
    
    /**
     * Test First, Middle & Last
     */
    
    //Test First(If the first value is 0)
    SlideGame s4 = new SlideGame();
    int b4[][] = {{0,0,1,0,2},{0,0,0,0,0}};
    int c4[][] = {{1,2,0,0,0},{0,0,0,0,0}};
    s4.setBoard(b4);
    s4.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c4,s4.getBoard());
    
    
    //Test First(If the first value is 1)
    SlideGame s5 = new SlideGame();
    int b5[][] = {{1,1,1,1,2},{0,0,0,0,0}};
    int c5[][] = {{2,4,0,0,0},{0,0,0,0,0}};
    s5.setBoard(b5);
    s5.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c5,s5.getBoard());
    
    //Test Middle(If the first element is in middle)
    SlideGame s6 = new SlideGame();
    int b6[][] = {{0,0,1,0,1},{0,0,0,0,0}};
    int c6[][] = {{2,0,0,0,0},{0,0,0,0,0}};
    s6.setBoard(b6);
    s6.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c6,s6.getBoard());
    
    
    //Test Last(If the first element is at the end)
    SlideGame s7 = new SlideGame();
    int b7[][] = {{0,0,0,0,2},{0,0,0,0,0}};
    int c7[][] = {{2,0,0,0,0},{0,0,0,0,0}};
    s7.setBoard(b7);
    s7.merge(SlideGame.Direction.LT,0,4);
    assertArrayEquals("Testing merge method for LD",c7,s7.getBoard());
    
    /**
     * Test for 8 direction
     */
    
    //RT`
    SlideGame s8 = new SlideGame();
    int[][] e = {{8,4,0,4},{5,0,7,8},{9,10,11,12},{2,0,1,1}};
    s8.setBoard(e);
    s8.merge(SlideGame.Direction.RT,0,0);
    int[][] e1 = {{0,0,0,16},{5,0,7,8},{9,10,11,12},{2,0,1,1}};
    assertArrayEquals("Testing ExtractArray method for RT direction",e1,s8.getBoard()); 
    
    //DN
    SlideGame s81 = new SlideGame();
    int[][] e21 = {{8,4,0,4},{5,0,7,8},{9,10,7,12},{2,0,0,1}};
    s81.setBoard(e21);
    s81.merge(SlideGame.Direction.DN,3,2);
    int[][] e22 = {{8,4,0,4},{5,0,0,8},{9,10,0,12},{2,0,14,1}};
    assertArrayEquals("Testing ExtractArray method for RT direction",e22,s81.getBoard());   
    
    //UP
    SlideGame s9 = new SlideGame();
    int[][] e2 = {{2,4,0,4},{2,0,7,8},{1,10,11,12},{1,0,1,1}};
    s9.setBoard(e2);
    s9.merge(SlideGame.Direction.UP,0,0);
    int[][] e3 = {{4,4,0,4},{2,0,7,8},{0,10,11,12},{0,0,1,1}};
    assertArrayEquals("Testing ExtractArray method for UP direction",e3,s9.getBoard());    
    
    //LDU
    SlideGame s10 = new SlideGame();
    int[][] e4 = {{2,4,4,4},{2,0,0,8},{1,10,2,12},{1,0,2,2}};
    s10.setBoard(e4);
    s10.merge(SlideGame.Direction.LDU,0,0);
    int[][] e5 = {{4,4,4,4},{2,2,0,8},{1,10,0,12},{1,0,2,0}};
    assertArrayEquals("Testing merge method for LDU direction",e5,s10.getBoard());    
    
    //LDU
    SlideGame s11 = new SlideGame();
    int[][] e6 = {{2,1,4,4},{2,0,1,8},{1,10,2,2},{1,0,2,2}};
    s11.setBoard(e6);
    s11.merge(SlideGame.Direction.LDU,0,1);
    int[][] e7 = {{2,4,4,4},{2,0,0,8},{1,10,2,0},{1,0,2,2}};
    assertArrayEquals("Testing merge method for LDU direction",e7,s11.getBoard()); 
    
    //RDU
    SlideGame s12 = new SlideGame();
    int[][] e8 = {{2,1,4,1},{2,0,0,8},{1,2,12,2},{2,0,2,2}};
    s12.setBoard(e8);
    s12.merge(SlideGame.Direction.RDU,0,3);
    int[][] e9 = {{2,1,4,1},{2,0,4,8},{1,0,12,2},{0,0,2,2}};
    assertArrayEquals("Testing merge method for LDU direction",e9,s12.getBoard()); 
    
    
    //RDU
    SlideGame s13 = new SlideGame();
    int[][] e10 = {{2,4,4,1},{4,0,0,8},{1,2,12,2},{2,0,2,2}};
    s13.setBoard(e10);
    s13.merge(SlideGame.Direction.RDU,0,1);
    int[][] e11 = {{2,8,4,1},{0,0,0,8},{1,2,12,2},{2,0,2,2}};
    assertArrayEquals("Testing merge method for LDU direction",e11,s13.getBoard()); 
    
    //LDD
    SlideGame s14 = new SlideGame();
    int[][] e12 = {{2,1,4,16},{4,0,8,8},{1,4,12,2},{4,0,2,2}};
    s14.setBoard(e12);
    s14.merge(SlideGame.Direction.LDD,3,0);
    int[][] e13 = {{2,1,4,0},{4,0,0,8},{1,0,12,2},{32,0,2,2}};
    assertArrayEquals("Testing merge method for LDU direction",e13,s14.getBoard()); 
    
    //LDD
    SlideGame s15 = new SlideGame();
    int[][] e14 = {{2,1,4,16},{4,0,8,8},{1,4,12,2},{4,0,2,2}};
    s15.setBoard(e14);
    s15.merge(SlideGame.Direction.LDD,3,1);
    int[][] e15 = {{2,1,4,16},{4,0,8,0},{1,4,8,2},{4,12,2,2}};
    assertArrayEquals("Testing merge method for LDU direction",e15,s15.getBoard()); 
    
    //RDD
    SlideGame s16 = new SlideGame();
    int[][] e16 = {{2,1,4,16},{4,0,8,8},{1,4,2,2},{4,0,2,2}};
    s16.setBoard(e16);
    s16.merge(SlideGame.Direction.RDD,3,3);
    int[][] e17 = {{0,1,4,16},{4,0,8,8},{1,4,2,2},{4,0,2,4}};
    assertArrayEquals("Testing merge method for LDU direction",e17,s16.getBoard());        
  }
  
  @Test
  public void testMove()
  {
    //LDU
    SlideGame s = new SlideGame();
    int[][] e = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f = { { 2, 2, 1, 1 }, { 2, 0, 0, 0 }, { 2, 0, 0, 0 }, { 2, 0, 0, 0 } };
    s.setBoard(e);
    s.move(0,0);
    assertArrayEquals("Testing merge method for LDU direction",f,s.getBoard());
    
    //RDU
    SlideGame s1 = new SlideGame();
    int[][] e1 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f1 = { { 1, 0, 2, 4 }, { 0, 1, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 2, 0 } };
    s1.setBoard(e1);
    s1.move(0,3);
    assertArrayEquals("Testing merge method for LDU direction",f1,s1.getBoard());
    
    //UP
    SlideGame s2 = new SlideGame();
    int[][] e2 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f2 = { { 4, 2, 4, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
    s2.setBoard(e2);
    s2.move(0,2);
    assertArrayEquals("Testing merge method for LDU direction",f2,s2.getBoard());
    
    //LDD
    SlideGame s3 = new SlideGame();
    int[][] e3 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f3 = { { 1, 0, 0, 0 }, { 0, 1, 0, 0 }, { 2, 0, 0, 1 }, { 4, 1, 2, 0 } };
    s3.setBoard(e3);
    s3.move(3,0);
    assertArrayEquals("Testing merge method for LDU direction",f3,s3.getBoard());
    
    //RDD
    SlideGame s4 = new SlideGame();
    int[][] e4 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f4 = { { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 2 }, { 2, 2, 2, 2 } };
    s4.setBoard(e4);
    s4.move(3,3);
    assertArrayEquals("Testing merge method for LDU direction",f4,s4.getBoard());
    
    //DN
    SlideGame s5 = new SlideGame();
    int[][] e5 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f5 = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 2, 0 }, { 2, 2, 2, 2 } };
    s5.setBoard(e5);
    s5.move(3,1);
    assertArrayEquals("Testing merge method for LDU direction",f5,s5.getBoard());
    
    //LT
    SlideGame s6 = new SlideGame();
    int[][] e6 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f6 = { { 2, 1, 0, 0 }, { 2, 0, 0, 0 }, { 2, 0, 0, 0 }, { 2, 1, 2, 0 } };
    s6.setBoard(e6);
    s6.move(2,0);
    assertArrayEquals("Testing merge method for LDU direction",f6,s6.getBoard());
    
    //RT
    SlideGame s7 = new SlideGame();
    int[][] e7 = {{1,0,1,1},{0,1,1,0},{1,0,0,1},{2,1,2,0}};
    int[][] f7 = { { 0, 0, 1, 2 }, { 0, 0, 0, 2 }, { 0, 0, 0, 2 }, { 0, 2, 1, 2 } };
    s7.setBoard(e7);
    s7.move(2,3);
    assertArrayEquals("Testing merge method for LDU direction",f7,s7.getBoard());    
  }    
}
//  
//  @Test
//  public void testExtractArray()
//  {
//    SlideGame s = new SlideGame();
//    /**
//     * Test 0,1 and Many
//     */
//    
//    //Test 0 (Zero elements in the given direction
//    int[][] d = {{},{}};
//    int[] a0 = {};
//    s.setBoard(d);
//    assertArrayEquals("Testing ExtractArray method for Test 0",a0,s.extractArray(SlideGame.Direction.DN,0,0));    
//    
//    //Test 1(One element in the given direction)
//    int[][] d1 = {{1,0},{0,0}};
//    int[] a01 = {0,1};
//    s.setBoard(d1);
//    assertArrayEquals("Testing ExtractArray method for Test 1",a01,s.extractArray(SlideGame.Direction.LDD,0,0));   
//    
//    //Test Many(Many element in the given direction)
//    int[][] d2 = {{1,0,1,1},{0,0,0,0}};
//    int[] a02 = {1,0,1,1};
//    s.setBoard(d2);
//    assertArrayEquals("Testing ExtractArray method for Test Many",a02,s.extractArray(SlideGame.Direction.LT,0,0)); 
//    
//    
//    /**
//     * Test First, Middle, Last
//     */
//    
//    //Test First (element found at first position in given direction)
//    int[][] e = {{1,2,3,4},{5,0,7,8},{9,10,0,12},{13,14,15,0}};
//    int[] f = {1,0,0,0};
//    s.setBoard(e);
//    assertArrayEquals("Testing ExtractArray method for Test First",f,s.extractArray(SlideGame.Direction.LDU,0,0));  
//    
//    //Test Middle (element found at Middle position in given direction)
//    int[][] e1 = {{0,2,3,4},{5,1,7,8},{9,10,0,12},{13,14,15,0}};
//    int[] f1 = {0,1,0,0};
//    s.setBoard(e1);
//    assertArrayEquals("Testing ExtractArray method for Test Middle",f1,s.extractArray(SlideGame.Direction.LDU,0,0));  
//    
//    //Test Last (element found at Last position in given direction)
//    int[][] e2 = {{0,2,3,4},{5,0,7,8},{9,10,0,12},{13,14,15,1}};
//    int[] f2 = {0,0,0,1};
//    s.setBoard(e2);
//    assertArrayEquals("Testing ExtractArray method for Test Last",f2,s.extractArray(SlideGame.Direction.LDU,0,0));  
//    
//    int[][] a = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//    s.setBoard(a);
//    
//    /**
//     * Test for 8 possible directions
//     */
//    
//    //Downward
//    int [] b = {9,5,1,0};
//    assertArrayEquals("Testing ExtractArray method for down direction",b,s.extractArray(SlideGame.Direction.DN,0,0));    
//    int [] b1 = {11,7,3,0};
//    assertArrayEquals("Testing ExtractArray method for down direction",b1,s.extractArray(SlideGame.Direction.DN,0,2));
//    
//    //Upward
//    int [] b2 = {1,5,9,0};
//    assertArrayEquals("Testing ExtractArray method for Up direction",b2,s.extractArray(SlideGame.Direction.UP,0,0));
//    int [] b3 = {2,6,10,0};
//    assertArrayEquals("Testing ExtractArray method for Up direction",b3,s.extractArray(SlideGame.Direction.UP,0,1));
//    
//    //Left
//    int [] b4 = {1,2,3,4};
//    assertArrayEquals("Testing ExtractArray method for Left direction",b4,s.extractArray(SlideGame.Direction.LT,0,0));
//    int [] b5 = {5,6,7,8};
//    assertArrayEquals("Testing ExtractArray method for Left direction",b5,s.extractArray(SlideGame.Direction.LT,1,0));
//    
//    //Right
//    int [] b6 = {12,11,10,9};
//    assertArrayEquals("Testing ExtractArray method for Right direction",b6,s.extractArray(SlideGame.Direction.RT,2,0));
//    int [] b7 = {8,7,6,5};
//    assertArrayEquals("Testing ExtractArray method for Right direction",b7,s.extractArray(SlideGame.Direction.RT,1,0));
//    
//    int c [][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//    s.setBoard(c);    
//    //Left Diagnol Down
//    int [] b8 = {16,11,6,1};
//    assertArrayEquals("Testing ExtractArray method for Left Diagnol Down direction",b8,s.extractArray(SlideGame.Direction.LDD,2,3));
//    
//    //Left diagnol up
//    int [] b9 = {1,6,11,16};
//    assertArrayEquals("Testing ExtractArray method for Left diagnol up direction",b9,s.extractArray(SlideGame.Direction.LDU,0,0));
//    
//    //Right diagnol up
//    int [] b10 = {4,7,10,13};
//    assertArrayEquals("Testing ExtractArray method for Right diagnol up direction",b10,s.extractArray(SlideGame.Direction.RDU,0,0));
//    
//    //Right diagnol down
//    int [] b11 = {13,10,7,4};
//    assertArrayEquals("Testing ExtractArray method for Right diagnol down direction",b11,s.extractArray(SlideGame.Direction.RDD,0,0));
//    
//  }
//  
//  @Test
//  public void testExtractArray2()
//  {
//    /*
//     * Test 0, 1 & many
//     */
//    
//    //Test 0 (when there are 0 elements in the Array)
//    SlideGame s = new SlideGame();
//    int b[][] = {{0,0,0,0,0},{0,0,0,0,0}};
//    int c[][] = {{0,0,0,0,0},{0,0,0,0,0}};
//    s.setBoard(b);
//    s.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c,s.getBoard());
//    
//    //Test 1 (when there are 1 elements in the Array)
//    SlideGame s1 = new SlideGame();
//    int b1[][] = {{0,1},{0,0}};
//    int c1[][] = {{1,0},{0,0}};
//    s1.setBoard(b1);
//    s1.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c1,s1.getBoard());
//    
//    //Test Many (when there are more than 1 elements in the Array)
//    SlideGame s2 = new SlideGame();
//    int b2[][] = {{1,0,1,1,2},{0,0,0,0,0}};
//    int c2[][] = {{2,1,2,0,0},{0,0,0,0,0}};
//    s2.setBoard(b2);
//    s2.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c2,s2.getBoard());
//    
//    SlideGame s3 = new SlideGame();
//    int b3[][] = {{1,0,1,0,2},{0,0,0,0,0}};
//    int c3[][] = {{4,0,0,0,0},{0,0,0,0,0}};
//    s3.setBoard(b3);
//    s3.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c3,s3.getBoard());
//    
//    /**
//     * Test First, Middle & Last
//     */
//    
//    //Test First(If the first value is 0)
//    SlideGame s4 = new SlideGame();
//    int b4[][] = {{0,0,1,0,2},{0,0,0,0,0}};
//    int c4[][] = {{1,2,0,0,0},{0,0,0,0,0}};
//    s4.setBoard(b4);
//    s4.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c4,s4.getBoard());
//    
//    
//    //Test First(If the first value is 1)
//    SlideGame s5 = new SlideGame();
//    int b5[][] = {{1,1,1,1,2},{0,0,0,0,0}};
//    int c5[][] = {{2,4,0,0,0},{0,0,0,0,0}};
//    s5.setBoard(b5);
//    s5.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c5,s5.getBoard());
//    
//    //Test Middle(If the first element is in middle)
//    SlideGame s6 = new SlideGame();
//    int b6[][] = {{0,0,1,0,1},{0,0,0,0,0}};
//    int c6[][] = {{2,0,0,0,0},{0,0,0,0,0}};
//    s6.setBoard(b6);
//    s6.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c6,s6.getBoard());
//    
//    
//    //Test Last(If the first element is at the end)
//    SlideGame s7 = new SlideGame();
//    int b7[][] = {{0,0,0,0,2},{0,0,0,0,0}};
//    int c7[][] = {{2,0,0,0,0},{0,0,0,0,0}};
//    s7.setBoard(b7);
//    s7.extractArray(SlideGame.Direction.LT,0,0);
//    assertArrayEquals("Testing merge method for LD",c7,s7.getBoard());
//  }
//    
//    
//  
//  
//}
//
