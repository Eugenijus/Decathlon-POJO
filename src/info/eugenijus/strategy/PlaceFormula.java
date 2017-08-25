package info.eugenijus.strategy;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import info.eugenijus.model.Athlete;

public class PlaceFormula {
	/**
	 * sort list of athletes and gives them places
	 * The bigger the score, the higher the place (1st place being highest) 
	 * @param athletes
	 */
	public void markPlaces(List<Athlete> athletes) {
		if(athletes.size() == 1) {
			athletes.get(0).setPlace("1");
			return;
		}
		Collections.sort(athletes);
		Collections.reverse(athletes);
		for(int i=0, j=1; i<athletes.size(); i++, j++) {
			athletes.get(i).setPlace(Integer.toString(j));
		}
		fixSamePlaces(athletes);
	}
	
	private void fixSamePlaces(List<Athlete> athletes) {
		if(athletes.size() < 2) {
			return;
		}
		Set<Athlete> list = new HashSet<Athlete>();
		int foundSame = 0;

		/** 
		 * 1. go through the list
		 * 2. compare i=0 and j=1,
		 * 3. if places are same, then add to the list
		 * 4. when next pair is not equal, then process the list and empty it
		 */
		int place1 = 0;
		int place2 = 0;
		for(int i=0, j=1; j<athletes.size(); i++, j++) {
			place1 = Integer.valueOf(athletes.get(i).getResult().getTotalScore());
			place2 = Integer.valueOf(athletes.get(j).getResult().getTotalScore());
			if(place1 == place2) {				
				list.add(athletes.get(i));
				list.add(athletes.get(j));
				foundSame++;
				//if the end of the list
				if(j == athletes.size()-1) {
					markCustomPlaces(list);
					list = new HashSet<Athlete>();
					foundSame = 0;
				}
			} else {
				if(foundSame > 0) {
					markCustomPlaces(list);
					list = new HashSet<Athlete>();
					foundSame = 0;
				}
			}
		}
	}
	
	private void markCustomPlaces(Set<Athlete> athletes) {
		StringBuilder builder = new StringBuilder();
		for(Athlete a : athletes) {
			builder.append(a.getPlace()).append("-");
		}
		String customPlace = builder.substring(0, builder.length()-1);
		for(Athlete a : athletes) {
			a.setPlace(customPlace);
		}
	}
}
