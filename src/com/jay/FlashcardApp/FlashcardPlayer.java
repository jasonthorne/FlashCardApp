package com.jay.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class FlashcardPlayer {

    //instance vars (private to this class, so can have same names as in 'FlashcardBuilder'):
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<Flashcard> cardList;
    private Iterator cardIterator;
    private Flashcard currentCard;
    private int currentCardIndex;
    private JFrame frame;
    private JButton showAnwserButton;
    private boolean isShowAnswer;


    //constructor
    public FlashcardPlayer(){

        //create a frame
        frame = new JFrame("Flashcard Player"); //"Flashcard Player" is title

        //provide graceful close of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a JPanel to contain frame contents (Goes on top of frame)
        JPanel mainPanel = new JPanel();

        //create a font from 'Font' class
        Font myFont = new Font("Helvetica new", Font.BOLD, 22); //type, style, size

        //create a text area for display
        display = new JTextArea(10,20); //row, column
        display.setFont(myFont); //add myFont

        //create JScrollPanes to add a scroll to text areas
        JScrollPane questionScrollPane = new JScrollPane(display); //create question scroll pane, PASSING IN 'display' text area
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //ensure scroll bar is always vertical
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //ensure NO horizonal scrollbar

        //create next button
        showAnwserButton = new JButton("Show answer");
        showAnwserButton.addActionListener(new NextCardListener()); //create and add a 'NextCardListener' obj to button's actionListener

        //Create a menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File"); //create a menu to ADD to menuBar container ("File" is text shown)
        JMenuItem loadMenuItem = new JMenuItem("Load card set");  //create a menu item to ADD to menu("Load card set" is text shown)

        //add event listener to loadMenuItem item
        loadMenuItem.addActionListener(new OpenMenuListener()); //"OpenMenuListener" created below

        //add menuItem to Menu
        fileMenu.add(loadMenuItem);

        //add Menu to MenuBar
        menuBar.add(fileMenu);

        //Add menuBar to frame (Added to 'frame', NOT 'mainPanel', as it needs to be in its own special location on page)
        frame.setJMenuBar(menuBar);

        //add components to main panel (ORDER MATTERS!)
        mainPanel.add(questionScrollPane);
        mainPanel.add(showAnwserButton);

        //add mainPanel to frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel); //get the content pane from the frame, and add components to that. (one which centers everything, and 'mainPanel')
        frame.setSize(640, 500); //width, height
        frame.setVisible(true); //MAKE FRAME VISIBLE!!! :P

    }

    public static void main (String[] args){

        //create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new FlashcardPlayer(); //this will run our 'FlashcardPlayer'

            }
        });

    }

    private class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    //allows user to find and open the file to be read
    private class OpenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         //Create a file dialog with 'file chooser' (allows user to choose where to open the file)+++++++++++
        JFileChooser fileOpen = new JFileChooser();
        fileOpen.showOpenDialog(frame); ////this is shown from the frame, so frame is a required parameter.
        loadFile(fileOpen.getSelectedFile()); //'loadFile' takes a 'File' type as a param

        }

    }

    //opens a passed File
    private void loadFile(File selectedFile) {

        //instantiate cardList array
        cardList = new ArrayList<Flashcard>(); //'<Flashcard> ensures it holds flashcard objects'

        try { //Try needed for safety as we're dealing with reading/writing

            //Using a 'bufferedReader' as it allows us to be more efficient with memory:
            BufferedReader reader = new BufferedReader(new FileReader(selectedFile)); //needs passed in a fileReader obj, which itself needs passed in the selected file
            String line = null;

            while ((line = reader.readLine()) != null) { //what is read from EACH line in the file, is put into our 'line' string (while there is content in the file line (!=null)

                makeCard(line); //send line contents to makeCard()
            }



        }catch (Exception e){

            System.out.println("Oh noes! Couldn't read file!");
            e.printStackTrace();

        }

        //show the first card in the list:
        cardIterator = cardList.iterator();

    }

    private void makeCard(String lineToParse) {

        //===================================
        //ONE WAY OF DOING THIS:
        /*
        String[] result = lineToParse.split("/"); //array now holds: [question, answer]

        //create new flashcard with a question and answer
        Flashcard card = new Flashcard(result[0], result[1]);
        cardList.add(card); //add card to cardList array
        System.out.println("made a card! :P");
        */
        //=====================================
        //ANOTHER (MORE MODERN) WAY:

        //splits the contents of the line into 'tokens' by the delimeter passed in ("/").
        StringTokenizer result = new StringTokenizer(lineToParse, "/");
        if(result.hasMoreTokens()){
            Flashcard card = new Flashcard(result.nextToken(), result.nextToken());
            cardList.add(card); //add card to cardList array
            System.out.println("made a card! :P");
        }

    }
}
