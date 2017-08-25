package info.eugenijus.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
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
		for (Athlete a : athletes) {
			System.out.println("PlacementFormula: " + a);
		}
		fixSamePlaces(athletes);
	}
	
	private void fixSamePlaces(List<Athlete> athletes) {
		if(athletes.size() < 2) {
			return;
		}
		List<Athlete> list = new LinkedList<Athlete>();
		int foundSame = 0;

		/** 
		 * 1. go through the list
		 * 2. compare i=0 and j=1,
		 * 3. if places are same, then add to the list
		 * 4. when next pair is not equal, then process the list and empty it
		 */
		int score1 = 0;
		int score2 = 0;
		for(int i=0, j=1; j<athletes.size(); i++, j++) {
			Athlete athlete1 = athletes.get(i);
			Athlete athlete2 = athletes.get(j);
			score1 = athlete1.getResult().getTotalScore();
			score2 = athlete2.getResult().getTotalScore();
			if(score1 == score2) {				
				list.add(athletes.get(i));
				list.add(athletes.get(j));
				
				foundSame++;
				//if the end of the list
				if(j == athletes.size()-1) {
					markCustomPlaces(list);
					list = new ArrayList<Athlete>();
					foundSame = 0;
				}
			} else {
				if(foundSame > 0) {
//					List<Athlete> sortedList = new ArrayList<Athlete>(list);
//					Collections.sort(sortedList);
//					markCustomPlaces(sortedList);
					markCustomPlaces(list);
					list = new ArrayList<Athlete>();
					foundSame = 0;
				}
			}
		}
	}
	
	private void markCustomPlaces(List<Athlete> athletes) {
		Set<Athlete> set = new LinkedHashSet<Athlete>();
		for(Athlete athlete : athletes) {
			set.add(athlete);
		}
		//Collections.sort(athletes);
		StringBuilder builder = new StringBuilder();
		for(Athlete a : set) {
			builder.append(a.getPlace()).append("-");
		}
		String customPlace = builder.substring(0, builder.length()-1);
		for(Athlete a : athletes) {
			a.setPlace(customPlace);
		}
	}
}
