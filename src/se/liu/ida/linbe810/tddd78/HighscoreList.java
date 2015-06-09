package se.liu.ida.linbe810.tddd78;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates a List containing all highscore-objects.
 */
public final class HighscoreList
{
    private static final HighscoreList INSTANCE = new HighscoreList();
    private List<Highscore> highscores;

    private HighscoreList() {
	this.highscores = new ArrayList<Highscore>();
    }

    public static HighscoreList getInstance() {
	return INSTANCE;
    }

    public List<Highscore> getHighscores() {
	return highscores;
    }

    public void addHighscores(Highscore score) {
	highscores.add(score);
    }
}

