package PasswordApp;

/*
 * This customize JDialog solve the problem that
 * the native dialog close windows button will not exit the entire system.
 * By impliment this dialog, when the user login is invalid
 * The entire system will be exited.
 */
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
public class MyDialog  extends JDialog {
  protected void processWindowEvent(WindowEvent e) {
    if(e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(1);
    }
  }
}