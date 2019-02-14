package com.jay.FlashcardApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
        frame = new JFrame("Flashcard");

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
        JScrollPane questionJScrollPane = new JScrollPane(question); //create question scroll pane
        questionJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //ensure scroll bar is always vertical
        questionJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //ensure NO horizonal scrollbar

        JScrollPane answerJScrollPane = new JScrollPane(answer); //create answer scroll pane
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
        JMenuBar menuBar = new JMenuBar(); //menuBar is a container
        JMenu fileMenu = new JMenu("File"); //create a menu to add to menuBar container ("File" is text shown)
        JMenuItem newMenuItem = new JMenuItem("New");//create a menu item ("New" is text shown)
        JMenuItem saveMenuItem = new JMenuItem("Save");//create a menu item ("Save" is text shown)

        //Add event listeners to menuItem items
        newMenuItem.addActionListener(new newMenuItemListener());



        //Add JFrame 'mainPanel' to the frame:
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel); //get the content pane from the frame, and add components to that. (one which centers everything, and 'mainPanel')
        frame.setSize(500, 600); //width, height
        frame.setVisible(true); //MAKE FRAME VISIBLE!!! :P

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

    class NextCardListener implements ActionListener{ //ActionListener is an interface we need to implement from

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("'Next' button clicked");

        }
    }


}


