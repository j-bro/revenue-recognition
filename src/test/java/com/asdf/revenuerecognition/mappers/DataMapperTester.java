package com.asdf.revenuerecognition.mappers;

import com.asdf.revenuerecognition.models.AbstractModel;
import com.asdf.revenuerecognition.models.PersonBean;

import java.util.Iterator;
import java.util.List;

public class DataMapperTester {

	public static void main(String[] args) {
		
		PersonMapper mapper = new PersonMapper();
		//Person person;
		//test search by id
		//person = (Person) mapper.find(1);
		//System.out.println("Person with ID=1: "+ 
		//         person.getFirstName()+" "+person.getLastName());
		
		//test findALL
		
		List<AbstractModel> results = mapper.findbyLastName("Yuhong");
		if(results.size()==0)
			System.out.println("Nothing found");
		else{
			Iterator<AbstractModel> it = results.iterator();
			while(it.hasNext()){
				PersonBean p = (PersonBean) it.next();
				System.out.println("Person with ID: " + p.getID().longValue()+ " "+
						p.getFirstName()+" "+p.getLastName());
			}
		
		}
		
		//test insert
		//person = new Person("YYH", "ABC", 2);
		//Long id = mapper.insertPerson(person);
		//System.out.println("Insert a record with ID = " + id.longValue());		

	}

}
