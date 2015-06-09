package se.liu.ida.linbe810.tddd78;

import java.util.Comparator;

/**
 * Compares two highscore-onbjects and sorts them
 * after their scores.
 */
public class ScoreComparator implements Comparator<Highscore>
{
    public int compare(Highscore highscore1, Highscore highscore2) {
	if(highscore1.getHighscore() < highscore2.getHighscore()) {
	    return -1;
	}
	else if (highscore1.getHighscore() > highscore2.getHighscore()) {
	    return 1;
	}
	else {
	    return 0;
	}
    }
}
