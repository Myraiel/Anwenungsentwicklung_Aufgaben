import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

public class Stundenplan extends JPanel {

    private boolean DEBUG = false;

    static String[] workdays = {"MONTAG" , "DIENSTAG" , "MITTWOCH" , "DONNERSTAG" , "FREITAG"};
    static String[] subjects = {"N/A" , "Mathematik" , "Deutsch" , "Englisch" , "Kunst" , "Sport" , "Physik", "Inforkatik"};
    static String[][] data = {
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]},
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]},
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]},
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]},
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]},
            {subjects[0], subjects[0], subjects[0], subjects[0], subjects[0]}
    };

    public Stundenplan(){


        final JTable table = new JTable(data, workdays);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Kai's Stundenplan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Stundenplan newContentPane = new Stundenplan();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private static void input(){
        int day=0;
        int hour=0;
        System.out.println("Möchten Sie Daten eintragen?");
        System.out.println("1.Ja \n2.Nein");

        Scanner in = new Scanner(System.in);
        int userInputTemp = in.nextInt();
        System.out.println(userInputTemp);
        if (userInputTemp == 1){
            System.out.println("Welchen Tag möchten Sie verändern? MO, DI, MI, DO, FR");
            Scanner scannerDay = new Scanner(System.in);
            String userInputDay = scannerDay.nextLine();

            if (userInputDay=="MO"){
                day=0;
            } else if (userInputDay=="DI"){
                day=1;
            } else if (userInputDay=="MI"){
                day=2;
            } else if (userInputDay=="DO"){
                day=3;
            } else if (userInputDay=="FR"){
                day=4;
            }

            System.out.println("Welche Stunde? 1-6");
            Scanner scannerHour = new Scanner(System.in);
            hour = scannerHour.nextInt() - 1;
            System.out.println("Welches Fach? Mathematik, Deutsch, Englisch, Kunst, Sport, Physik, Inforkatik");
            Scanner scannerSubject = new Scanner(System.in);
            String userInputSubject = scannerSubject.nextLine();

            if(userInputSubject=="Mathematik"){
                data[day][hour]=subjects[1];
            } else if(userInputSubject=="Deutsch"){
                data[day][hour]=subjects[2];
            } else if(userInputSubject=="Englisch"){
                data[day][hour]=subjects[3];
            } else if(userInputSubject=="Kunst"){
                data[day][hour]=subjects[4];
            } else if(userInputSubject=="Sport"){
                data[day][hour]=subjects[5];
            } else if(userInputSubject=="Physik"){
                data[day][hour]=subjects[6];
            } else if(userInputSubject=="Inforkatik"){
                data[day][hour]=subjects[7];
            }
            input();
        }else return;



    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        input();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
