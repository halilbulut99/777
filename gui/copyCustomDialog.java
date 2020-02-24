package fmi.informatics.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SortOrder;

import fmi.informatics.comparators.StudentComparator;
import fmi.informatics.enums.EType;
import fmi.informatics.extending.Student;
import jdk.internal.platform.Container;

public abstract class copyCustomDialog extends JFrame {
    public static Student[] students;

    JTable table;
    copyStudentDataModel copystudentDataModel;

    public static void main(String[] args) {
        

        
        students = FileReader.readPeople();

        
        initializeData();

        copyStudentData gui = new copyStudentData();
        gui.createAndShowGUI();
    }

    
    public static void initializeData() {
        if (!FileReader.isFileExists()) {
            FileReader.createPersonFile();
        }


    }



    public void createAndShowGUI() {
        JFrame frame = new JFrame("Таблица с данни за студенти");
        frame.setSize(800, 450);

        JLabel label = new JLabel("Списък с потребители", JLabel.CENTER);

        frame.getContentPane().setBackground(Color.lightGray);

        copystudentDataModel = new copyStudentDataModel(students);
        table = new JTable(copystudentDataModel);
        table.setBackground(Color.orange);
        JScrollPane scrollPane = new JScrollPane(table);

       

        JButton buttonSortAscending = new JButton("Сортирай по възходящ ред");
        JButton buttonSortDescending = new JButton("Сортирай по низходящ ред");

        
        JButton buttonFilter = new JButton("Филтрирай");


      
        JPanel buttonsPanel = new JPanel();

        buttonsPanel.add(buttonSortAscending);
        buttonsPanel.add(buttonFilter);
        buttonsPanel.add(buttonSortDescending);



        Container container = frame.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(label, BorderLayout.NORTH);
        container.add(scrollPane, BorderLayout.CENTER);
        
        container.add(buttonsPanel, BorderLayout.SOUTH);




     
        final JDialog filterDialog = new copyCustomDialog(getFilterText(), this, EType.FILTER,SortOrder.ASCENDING);

        // Добавяме listener към бутона за сортиране
// Добавяме диалог
        final JDialog sortDialogAscending = new copyCustomDialog(getSortText(), this,EType.SORT,SortOrder.ASCENDING);

        buttonSortAscending.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortDialogAscending.pack();

                sortDialogAscending.setVisible(true);
            }
        });

        final JDialog sortDialogDescending = new copyCustomDialog(getSortText(), this,EType.SORT,SortOrder.DESCENDING);
        // Добавяме listener към бутона за сортиране
        buttonSortDescending.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                sortDialogDescending.pack()
                ;sortDialogDescending.setVisible(true);

            }
        }



        );
      
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterDialog.pack();
                filterDialog.setVisible(true);
            }
        });
    frame.setVisible(true);
    }

        
        public void filterTable ( String lastName , JTable table, Student[] students){


                    this.copystudentDataModel = new copyStudentDataModel(filterData(students,lastName));

            table.setModel(this.copystudentDataModel);
            table.repaint();
        }


        public static Student[] filterData (Student[]students,String lastName){
            ArrayList<Student> filteredData = new ArrayList<>();

            for (int i = 0; i < students.length; i++) {

                if (lastName.equals(students[i].getlastName())) {


                        filteredData.add(students[i]);
                    }
                }

           
            Student[] filteredDataArray = new Student[filteredData.size()];
            filteredDataArray = filteredData.toArray(filteredDataArray);
            return filteredDataArray;
        }

        public void sortTable ( int intValue, JTable table, Student[]students,SortOrder order){
            StudentComparator comparator = null;

            switch (intValue) {
                case 1:

                    comparator = new FirstNameComp();
                    checkIfDescending( order, comparator);
                    break;
                case 2:

                    comparator = new SecondNameComp();
                    checkIfDescending( order, comparator);
                    break;
                case 3:

                    comparator = new LastNameComparator();
                    checkIfDescending( order, comparator);
                    break;

            }

            if (comparator == null) { 
                Arrays.sort(students); 
            } else {
                Arrays.sort(students, comparator);
            }
            copystudentDataModel = new copyStudentDataModel(students);
            table.setModel(copystudentDataModel);
            table.repaint();
        }
    private static void checkIfDescending(SortOrder order,StudentComparator comparator){
        if (order.equals(SortOrder.DESCENDING)){
            comparator.setSortOrder(SortOrder.DESCENDING);

        }
    }

        private static String getSortText () {
            return "Моля, въведете цифрата на колоната, по която искате да сортирате: \n" +
                    " 1 - Име \n" +
                    " 2 - Презиме \n" +
                    " 3 - Фамилия \n";

        }

        // TODO Добавяме текст, който да се визуализира в диалога за филтриране
        private static String getFilterText () {
            return "Моля, въведете фамилията на студентд\n" ;
        }
    }