package fmi.informatics.comparators;

import fmi.informatics.extending.Student;

public class LastNameComp extends StudentComparator{

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getlastName().equals(stu2.getsecondName())) {
			return 0;
		} else {
			return (stu1.getlastName().compareTo(stu2.getlastName()) * sortOrder);
		}
	}

}
