  import java.util.StringTokenizer;
  import javax.swing.JOptionPane;
  /**
  * 
  *@author  Andy Ng <admin@webcoder.io>
  Webcoder.io All Rights Reserved
  Please do not delete the credit above!
  e-mail address is being protected from spam bots, you need JavaScript enabled to view it
  *
  */
  public class BattleShipGame {
  /**
  * Return the x-Coordinate by the coordinate string(it should be 0,0 format)
  * 
  * @param coordinate
  *            the coordinate string
  * @return the x-Coordinate, if there are some errors, return -1
  */
  private static int getCoordinateX(String coordinate) {
  int index = coordinate.indexOf(',');
  if (index == -1)
  return -1;
  try {
  String xCoordinate = coordinate.substring(0, index);
  return Integer.parseInt(xCoordinate);
  } catch (Exception e) {
  return -1;
  }
  }
  /**
  * It is similar as the method "getCoordinateX", return the y-Coordinate by
  * the coordinate string(it should be 0,0 format)
  * 
  * @param coordinate
  *            the coordinate string
  * @return the y-Coordinate, if there are some errors, return -1
  */
  private static int getCoordinateY(String coordinate) {
  int index = coordinate.indexOf(',');
  if (index == -1)
  return -1;
  try {
  String yCoordinate = coordinate.substring(index + 1, coordinate
  .length());
  return Integer.parseInt(yCoordinate);
  } catch (Exception e) {
  return -1;
  }
  }
  /**
  * The method return a string which display the player's game board. And 'H'
  * means the cell has a ship and it has been hit; 'X' means a miss; '+'
  * means the cell is still in play.
  * 
  * @param player
  *            the player's game board
  * @return a string which display the player's game board
  */
  private static String print(int[][] player) {
  String str = "";
  for (int i = 0; i < player.length; i++) {
  for (int j = 0; j < player[i].length; j++) {
  if (j != 0)
  str = str + " ";
  // Insert the character according to the value of the coordinate
  switch (player[i][j]) {
  case 0:
  str = str + "+";
  break;
  case 1:
  break;
  case 2:
  str = str + "+";
  break;
  case 3:
  str = str + "+";
  break;
  case 4:
  str = str + "+";
  break;
  case -1:
  str = str + "H";
  break;
  case -2:
  str = str + "X";
  break;
  }
  }
  str += "\n";
  }
  return str;
  }
  /**
  * Init the player's game board with the file. It will read the coordinates
  * with the format intruduced in the requirement, and set the value to the
  * player's game board
  * 
  * @param filename
  *            the file name of the file
  * @param player
  *            the player's game board
  */
  private static void readFile(String filename, int[][] player) {
  TextFileInput input = new TextFileInput(filename);
  String line = input.readLine();
  // Loop until get the end line of the file
  while (line != null) {
  // Split the string
  StringTokenizer token = new StringTokenizer(line, "|");
  // Count the number of the coordinates
  int numberOfCoordinate = 0;
  int[][] coordinates = new int[25][2];
  while (token.hasMoreTokens()) {
  // Get each of the coordinate and save it with one array
  String coordinate = token.nextToken();
  coordinates[numberOfCoordinate][0] = getCoordinateX(coordinate);
  coordinates[numberOfCoordinate][1] = getCoordinateY(coordinate);
  ++numberOfCoordinate;
  }
  // set the value to the game board
  for (int i = 0; i < numberOfCoordinate; i++)
  player[coordinates[i][0]][coordinates[i][1]] = numberOfCoordinate;
  line = input.readLine();
  }
  }
  /**
  * The method to check if all ships are sunk. which checks through the array
  * and returns if the number of 2's, 3's and 4's is equal to ZERO
  * 
  * @param player
  *            the player's board
  * @return return true if the number of 2's, 3's and 4's is equal to ZERO;
  *         or return false
  */
  private static boolean allSunk(int[][] player) {
  // count the number of the 2's(number[0]), 3's(number[1]) and
  // 4's(number[2])
  int[] numbers = new int[3];
  // Init
  for (int i = 0; i < numbers.length; i++)
  numbers[i] = 0;
  // Count the number of the ships
  for (int i = 0; i < player.length; i++)
  for (int j = 0; j < player[i].length; j++)
  if (player[i][j] > 0)
  ++numbers[player[i][j] - 2];
  // if the number of 2's, 3's and 4's is equal to ZERO;
  if ((numbers[0] == 0) && (numbers[1] == 0) && (numbers[2] == 0))
  return true;
  else
  return false;
  }
  /**
  * Check which ship is sunk and return the string to represent
  * 
  * @param player
  *            the player's game board
  * @return the string to represent which ship is sunk
  */
  private static String checkSunk(int[][] player) {
  String str = "";
  int numberOfSunk = 0;
  for (int i = 2; i <= 4; i++) {
  // Check whether the ship 'i' is sunk
  boolean isSunk = true;
  for (int j = 0; j < player.length; j++)
  for (int k = 0; k < player[j].length; k++)
  if (player[j][k] == i) {
  // It is not sunk as not all of them is -1
  isSunk = false;
  break;
  }
  if (isSunk) {
  // Ship 'i' is sunk
  str = str + "Ship " + i + " sunk\n";
  numberOfSunk++;
  }
  }
  if (numberOfSunk == 0)
  str = str + "\n\n\n";
  else if (numberOfSunk == 1)
  str = str + "\n\n";
  else if (numberOfSunk == 2)
  str = str + "\n";
  return str;
  }
  /**
  * The play logic for the game for the player 'playerNum' and the opposing
  * player's game board
  * 
  * @param player
  *            the opposing player's game board
  * @param playerNum
  *            the player's number
  */
  private static void play(int[][] player, int playerNum) {
  // Loop until the player input the command correctly or the player
  // didn't hit the ship
  while (true) {
  String msg = "Player " + playerNum
  + ", enter the coordinates in this format: 0,0\n";
  // Get the sunk ship message
  msg += checkSunk(player);
  // Get the board message
  msg += print(player);
  String input = JOptionPane.showInputDialog(null, msg);
  if (input != null) {
  // Player enter -1
  if (input.equals("-1"))
  System.exit(0);
  int x = getCoordinateX(input);
  int y = getCoordinateY(input);
  if (x == -1 || y == -1 || x < 0 || y < 0 || x >= 5 || y >= 5)
  // The player didn't enter the coordinate with correct
  // format or wrong range
  JOptionPane
  .showMessageDialog(null,
  "Please enter the coordinate in correct format or in correct range.");
  else if (player[x][y] < 0)
  // Player enter a coordinate which is hit already
  JOptionPane.showMessageDialog(null,
  "Please enter the coordinate which is not hit.");
  else {
  if (player[x][y] > 0) {
  // Hit the ship
  JOptionPane.showMessageDialog(null,
  "YOU HIT HIM! GO AGAIN!");
  player[x][y] = -1;
  } else if (player[x][y] == 0) {
  // Missed and break the loop
  JOptionPane.showMessageDialog(null, "YOU MISSED!");
  player[x][y] = -2;
  break;
  }
  if (allSunk(player)) {
  JOptionPane
  .showMessageDialog(null, "YOU WON THE GAME.");
  System.exit(0);
  }
  }
  } else {
  // Player click the cancel button
  System.exit(0);
  }
  }
  }
  /**
  * main method
  * 
  * @param args
  */
  public static void main(String[] args) {
  // Init the two players' game board
  int[][] player1 = new int[5][5];
  int[][] player2 = new int[5][5];
  // Read the two game boards
  readFile("player1.txt", player1);
  readFile("player2.txt", player2);
  // Play until the game end
  for (int i = 0;; ++i)
  if (i % 2 == 0)
  play(player2, 1);
  else
  play(player1, 2);
  }
  }