import java.awt.BorderLayout;

import javax.swing.*;
/*
* One play logic for one player
* 
* @param gui
*            the battle ship gui
* @param player
*            the playing player
* @param name
*            the name of the player
*/
public class BattleShipGui extends JFrame {

private static final long serialVersionUID = 1L;

private JPanel jContentPane = null;

private JTextArea boardTextArea = null;

private JTextArea label = null;

/**
* This is the default constructor
*/
public BattleShipGui() {
super();
initialize();
}

/**
* This method initializes this
*
* @return void
*/
private void initialize() {
this.setSize(420, 540);
this.setContentPane(getJContentPane());
this.setTitle("JFrame");
}

/**
* This method initializes jContentPane
*
* @return javax.swing.JPanel
*/
private JPanel getJContentPane() {
	if (jContentPane == null) {
	jContentPane = new JPanel();
	jContentPane.setLayout(new BorderLayout());
	jContentPane.add(getBoardTextArea(), BorderLayout.CENTER);
	jContentPane.add(getLabel(), BorderLayout.SOUTH);
}
return jContentPane;
}

/**
* This method initializes boardTextArea
*
* @return javax.swing.JTextArea
*/
private JTextArea getBoardTextArea() {
if (boardTextArea == null) {
boardTextArea = new JTextArea();
}
return boardTextArea;
}

/**
* Display the board to the text area
*
* @param board
* the board content
*/
public void setBoard(String board) {
boardTextArea.setText(board);
}

/**
* Set the lable of the game
*
* @param lable
* the lable content
*/
public void setLable(String lable) {
label.setText(lable);
}

/**
* This method initializes label
*
* @return javax.swing.JTextArea
*/
private JTextArea getLabel() {
if (label == null) {
label = new JTextArea();
label.setEditable(false);
label.setBackground(this.getBackground());
}
return label;
}

} // class