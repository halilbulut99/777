package fmi.informatics.gui;

import javax.swing.table.AbstractTableModel;

import fmi.informatics.extending.Student;

public class StudentModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private Student[] people;

    // constructor
    public StudentModel(Student[] people) {
        this.people = people;
    }

    @Override
    public int getColumnCount() {
        return 3; // брой колони в таблицата
    }

    @Override
    public int getRowCount() {
        return people.length; // брой редове в таблицата
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return people[rowIndex].getfirstName();
            case 1:
                return people[rowIndex].getsecondName();
            case 2:
                return people[rowIndex].getlastName();

        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Име";
            case 1:
                return "Презиме";
            case 2:
                return "Фамилия";

            default:
                return super.getColumnName(column);
        }
    }
}