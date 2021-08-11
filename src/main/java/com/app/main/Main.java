package com.app.main;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author rahul38888
 */
public class Main {

	public static void main(String[] args) throws IOException {
		String inputFilePath = args[0];

		List<String> inputs = FileUtils.readLines(new File(inputFilePath));

		HashMap<String, TreeSet<PersonScore>> roleWise= new HashMap<>();
		HashMap<String, TreeSet<PersonScore>> managerWise = new HashMap<>();
		for (String input : inputs) {
			String[] inputValues = input.split(",");

			PersonScore score = getScore(inputValues);
			if(!roleWise.containsKey(score.role))
				roleWise.put(score.role, new TreeSet<>(
						new Comparator<PersonScore>() {
							@Override
							public int compare(PersonScore score, PersonScore t1) {
								return t1.score.compareTo(score.score);
							}
						}
				));

			if(!managerWise.containsKey(score.manager))
				managerWise.put(score.manager, new TreeSet<>(
						new Comparator<PersonScore>() {
							@Override
							public int compare(PersonScore score, PersonScore t1) {
								return t1.score.compareTo(score.score);
							}
						}
				));

			roleWise.get(score.role).add(score);
			managerWise.get(score.manager).add(score);
		}

		System.out.println("Best QA: "+ roleWise.get("QA").first().name);
		System.out.println("Best DEV: "+ roleWise.get("DEV").first().name);

		TreeSet<PersonScore> managerScores = new TreeSet<>(
				new Comparator<PersonScore>() {
					@Override
					public int compare(PersonScore score, PersonScore t1) {
						return t1.score.compareTo(score.score);
					}
				}
		);

		for(Map.Entry<String, TreeSet<PersonScore>> entry: managerWise.entrySet()){
			Integer scores = 0;
			for (PersonScore personScore:entry.getValue())
				scores += personScore.score;

			managerScores.add(new PersonScore(entry.getKey(), null, null, scores));
		}
		System.out.println("Best DEV: "+ managerScores.first().name);
	}

	private static PersonScore getScore(String[] inputs){
		int score = 0;
		if(inputs[1].equals("DEV")){
			score = Integer.parseInt(inputs[3])*10 - Integer.parseInt(inputs[4])*5;
		}
		else{
			score = Integer.parseInt(inputs[3])*5 + Integer.parseInt(inputs[4])*10 - Integer.parseInt(inputs[5])*10;
		}

		return new PersonScore(inputs[0],inputs[1],inputs[2], score);
	}

}

