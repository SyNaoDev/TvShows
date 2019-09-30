package com.tyler.tvshows.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tyler.tvshows.models.Rating;

public class SortRatings {
	public static int compare(Rating r1, Rating r2) {
		if(r1.getValue() < r2.getValue()) {
			return 1;
		} else if(r1.getValue() > r2.getValue()) {
			return -1;
		}
		return 0;
	}
	public static void sort(List<Rating> ratings) {
		Comparator<Rating> comparator = (r1, r2) -> compare(r1, r2);
		Collections.sort(ratings, comparator);
	}
}