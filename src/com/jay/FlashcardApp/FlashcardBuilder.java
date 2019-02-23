package com.jay.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashcardBuilder {

    //instance vars:
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<Flashcard> cardList; //arraylist of flashcard objs from 'Flashcard' class
    private JFrame frame;


    //Class constructor
    public FlashcardBuilder(){
        //build the user interface

        //create a frame
        frame = new JFrame("Flashcard"); //"Flashcard" is title

        //provide graceful close of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //++++create a JPanel to hold everything together (Goes on top of frame)
        JPanel mainPanel = new JPanel();

        //create a font from 'Font' class (which is seperate from Swing classes)
        Font myFont = new Font("Helvetica New", Font.BOLD, 21); //type, style, size

        //create text area for question
        question = new JTextArea(6,20); //rows, colums
        question.setLineWrap(true); //wrap text if too long to fit area
        question.setWrapStyleWord(true); //wrap by word
        question.setFont(myFont); //add myFont

        //create text area for answer
        answer = new JTextArea(6,20); //rows, colums
        answer.setLineWrap(true); //wrap text if too long to fit area
        answer.setWrapStyleWord(true); //wrap by word
        answer.setFont(myFont); //add myFont

        //create JscrollPanes to add a scroll to text areas
        JScrollPane questionJScrollPane = new JScrollPane(question); //create question scroll pane, PASSING IN 'question' text area
        questionJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //ensure scroll bar is always vertical
        questionJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //ensure NO horizonal scrollbar

        JScrollPane answerJScrollPane = new JScrollPane(answer); //create answer scroll pane, , PASSING IN 'answer' text area
        answerJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //ensure scroll bar is always vertical
        answerJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //ensure NO horizonal scrollbar

        //Create 'Next card' Button
        JButton nextButton = new JButton("Next Card");

        //Create some labels
        JLabel questionJLabel = new JLabel("Question");
        JLabel answerJLabel = new JLabel("Anwser");

        //Add components to mainPanel (ORDER HERE MATTERS for layout!!)
        mainPanel.add(questionJLabel); //add question label
        mainPanel.add(questionJScrollPane); //add scrollpane which CONTAINS the question text area
        mainPanel.add(answerJLabel); //add answer label
        mainPanel.add(answerJScrollPane); //add scrollpane which CONTAINS the answer text area
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener()); //Create and add a 'NextCardListener' obj to button's actionListener

        //Create a menuBar and it's items:
        JMenuBar menuBar = new JMenuBar(); //menuBar is a container (which is EMPTY)
        JMenu fileMenu = new JMenu("File"); //create a menu to ADD to menuBar container ("File" is text shown)
        JMenuItem newMenuItem = new JMenuItem("New");//create a menu item to ADD to menu("New" is text shown)
        JMenuItem saveMenuItem = new JMenuItem("Save");//create a menu item to ADD to menu ("Save" is text shown)

        //Add event listeners to menuItem items
        newMenuItem.addActionListener(new newMenuItemListener());
        saveMenuItem.addActionListener(new saveMenuItemListener());

        //Add menu items to menu
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);

        //Add menu to menuBar
        menuBar.add(fileMenu);

        //Add menuBar to frame (Added to 'frame', NOT 'mainPanel', as it needs to be in its own special location on page)
        frame.setJMenuBar(menuBar);

        //Add JFrame 'mainPanel' to the frame:
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel); //get the content pane from the frame, and add components to that. (one which centers everything, and 'mainPanel')
        frame.setSize(500, 600); //width, height
        frame.setVisible(true); //MAKE FRAME VISIBLE!!! :P

        //create an instance of an Arraylist for holding holding 'Flascard' class objects
        cardList = new ArrayList<Flashcard>();

    }

    public static void main(String[] args){

        System.out.println("Sup world!");

        //create the frame on the event dispatching thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashcardBuilder();//this will run our 'FlashcardBuilder'
            }
        });
    }

    //++++++++++++++++++++ACTION LISTENERS++++++++++++++++++++++++++++++++

    class NextCardListener implements ActionListener{ //ActionListener is an interface we need to implement from

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("'Next' button clicked");

            //Create a flashcard Object, passing in 'question' and 'answer' 'JTextArea' text
            Flashcard card = new Flashcard(question.getText(), answer.getText());

            //add flashcard obj ('card') to arrayList of Flashcard objects
            cardList.add(card);

            //clear the text area, ready for next input
            clearCard();
        }

    }


    private class newMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("'newMenuItem' clicked");

        }
    }

    private class saveMenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("'saveMenuItem' clicked");

            //Create a flashcard Object, passing in 'question' and 'answer' 'JTextArea' text
            Flashcard card = new Flashcard(question.getText(), answer.getText());

            //add flashcard obj ('card') to arrayList of Flashcard objects
            cardList.add(card);

            //Create a file dialog with 'file chooser' (allows user to choose where to save file)+++++++++++
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame); //this is shown from the frame, so frame is a required parameter.
            saveFile(fileSave.getSelectedFile()); //'saveFile' takes a 'File' type as a param

        }


    }


    //clears card text areas, ready for new input
    private void clearCard() {

        question.setText(""); //rewrite the text to nothing
        answer.setText(""); //rewrite the text to nothing
        question.requestFocus(); //sets the focus of the cursor to be on the question field
    }

    //accepts and saves a file
    private void saveFile(File selectedFile) {

        //try catch incase of issues
        try {

            //BufferedWriter is an efficient way of writing to files. It requires a FileWriter obj though, which in turn requires the file itself
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(selectedFile));

            //cardIterator obj for iterating through our arraylist of Flashcard objects (cardList)
            Iterator<Flashcard> cardIterator = cardList.iterator();

            //spin through iterator
            while (cardIterator.hasNext()){ //while Iterator's arraylist has a next item
                Flashcard card = (Flashcard)cardIterator.next(); //create a new Flashcard obj from that item. '(Flashcard)' cast is to guarantee that the item will be a Flashcard obj. A wise precacious measure!
                myWriter.write(card.getQuestion() + "/"); //write the card objs getQuestion string to file + a "/"
                myWriter.write(card.getAnswer() + "\n"); //write the card objs getAnswer string to file + a new line

                //format will be written like this:
                //Where does Nessie live?/Loch Ness
            }

            //close writer ++++IMPORTANT!
            myWriter.close();

        } catch (Exception e){

            System.out.println("She cannie take it captain!");
            e.printStackTrace(); //show error

        }

    }
}


