package se.liu.ida.linbe810.tddd78;

/**
 * Highscore-object with the components that's needed
 * to generate a highscore-object.
 */
public class Highscore
{
    private int highscore;
    private String name;

    public Highscore(int highscore, String name) {
	this.highscore = highscore;
	this.name = name;
    }

    public int getHighscore() {
	return highscore;
    }

    // We talked about this.
    @Override public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append(name);
	builder.append(": ");
	builder.append(highscore);
	return builder.toString();
    }
}
