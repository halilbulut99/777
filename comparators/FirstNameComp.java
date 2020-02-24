package fmi.informatics.comparators;

import fmi.informatics.extending.Student;

public abstract class FirstNameComp extends StudentComparator{

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getfirstName().equals(stu2.getfirstName())) {
			return 0;
		} else {
			return (stu1.getfirstName().compareTo(stu2.getfirstName()) * sortOrder);
		}
	}
}
