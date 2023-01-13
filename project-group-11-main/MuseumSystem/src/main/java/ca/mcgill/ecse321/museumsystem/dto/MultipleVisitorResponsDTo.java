package ca.mcgill.ecse321.museumsystem.dto;

import java.util.ArrayList;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.Visitor;

public class MultipleVisitorResponsDTo {
	
	private Iterable<Visitor> visitors;
	
	public MultipleVisitorResponsDTo(Iterable<Visitor> visitors) {
		ArrayList<Visitor> visitor = new ArrayList<Visitor>();
		for (Visitor e : visitors) {
			visitor.add(e);
		}
		this.visitors = visitor;
	}
	
	public Iterable<Visitor> getVisitors() {
		return this.visitors;
	}

}