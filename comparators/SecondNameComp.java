package fmi.informatics.comparators;

import fmi.informatics.extending.Student;

public class SecondNameComp extends StudentComparator{

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getsecondName().equals(stu2.getsecondName())) {
			return 0;
		} else {
			return (stu1.getsecondName().compareTo(stu2.getsecondName()) * sortOrder);
		}
	}

}
