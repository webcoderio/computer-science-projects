package PPR_Util;
import java.io.*;
import java.security.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.*;

public class HashEW {
    static byte[] arr;

	public static void main(String[] args) {
        readFile();
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(arr);
            byte[] hash = sha.digest();
            for (int i = 0; i < hash.length; i++) System.out.print(hash[i] + " ");
    }catch (NoSuchAlgorithmException e) { e.printStackTrace(); }

	}//main()

    private static void readFile()
    {
        int status;
        JFileChooser chooser = new JFileChooser();
        int n = JOptionPane.showConfirmDialog(null,
                "Is EW avaiable now?", "Read EW", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.NO_OPTION) System.exit(1);

        status = chooser.showOpenDialog(null);
        if (status == JFileChooser.APPROVE_OPTION)
            readSource(chooser.getSelectedFile());
        else
            JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    }

    private static void readSource(File fileName)
    {
        BufferedReader brf = null;
        try
        {
            brf = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName.getAbsolutePath())));
        }
        catch (IOException ioe) { throw new RuntimeException(ioe); }

        try
        {
            if (brf == null)
                throw new RuntimeException("Cannot read from closed file "
                                           + fileName.getAbsolutePath() + ".");

            String line = brf.readLine();
            while (line.length() == 0) line = brf.readLine();
            Vector<String> v=new Vector<String>();
            int count = 0;
            while (line != null && count < 20) { v.addElement(line); line = brf.readLine(); count++; }
            //int s = v.size();
            arr = new byte[20];
            for (int i = 0; i < 20; i++)
                arr[i] = new Byte(v.remove(0));
            brf.close();
        }catch (IOException ioe) { throw new RuntimeException(ioe); }
    }  // method readSource()
}

