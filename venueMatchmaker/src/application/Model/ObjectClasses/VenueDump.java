package application.Model.ObjectClasses;

import java.util.ArrayList;

public class VenueDump extends Venue{
	
	private String type;

	public VenueDump() 
	{
		
	}

	@Override
	public void setSuitableType(ArrayList<String> suitableType) {
		this.suitableType = suitableType;
		
		type = "";
		
		for(String s : suitableType) 
		{
			type += s + "; "; 
		}
	}
	
	@Override
	public void addToType(String type) 
	{
		suitableType.add(type);
		type += (type + "; ");
	}
	
	public String getType() 
	{
		return type;
	}

}
