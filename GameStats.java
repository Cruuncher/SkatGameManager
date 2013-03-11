import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Used to track and log a skat games statistics, including messages and errors..<br>
 */
public class GameStats {
	private List<RoundStats> roundStats;
	
	/**
	 * Our default constructor, initializes the GameStats.
	 */
	public GameStats() {
		// Initialize our list.
		roundStats = new ArrayList<RoundStats>();
	}
	
	/**
	 * Obtains the current or last round to be played, null if no games played.
	 * @return Returns an object used to record round specific stats.
	 */
	public RoundStats createNewRound() {
		// Simply add our new round to our list.
		RoundStats newRound = new RoundStats(this);
		roundStats.add(newRound);
		return newRound;
	}
	
	/**
	 * Records the creation of a new round in our Game.
	 * @return Returns the last round object containing round specific stats.
	 */
	public RoundStats getCurrentRound() {
		// We must have a created round to return one.
		if(roundStats.size() == 0)
			return null;
		
		// Return our last round stats.
		return roundStats.get(roundStats.size() - 1);
	}

	/**
	 * Obtains the duration of the game, in seconds
	 * @return The double representing the game length in seconds, or -1 if the a round hasn't started/ended.
	 */
	private double getGameDuration() {
		// Return nothing if we don't have sufficient information.
		if(this.roundStats.size() == 0)
			return -1;
		
		// Get our round duration
		double seconds = 0;
		for(RoundStats curRoundStats : this.roundStats)
			seconds += curRoundStats.getDuration();
		
		return seconds;
	}
	
	/**
	 * Returns a string representation of our entire game statistics.
	 */
	@Override
	public String toString() {
		// Populate our result with information.
		String result = "";
		String seperator = "-----------------------------\n";
		
		// Print our header
		result += seperator;
		result += "Game Result Statistics\n";
		result += seperator;
		
		// If we have no rounds..
		if(this.roundStats.size() == 0) {
			// Print just this message..
			result += "No Games Played\n";
			result += seperator;
			return result;
		}
		
		// Otherwise print some game info..
		result += "Game Duration: " + this.getGameDuration() + " seconds\n";
		result += "Game Rounds: " + roundStats.size() + "\n";
		result += seperator;
		
		// Print each rounds information
		for(int i = 0; i < roundStats.size(); i++) {
			// Print our round header
			result += "Round " + (i + 1) + ":\n";
			
			// Get our round results and add them tabbed.
			String roundResults = roundStats.get(i).toString();
			String[] roundResultsSplit = roundResults.split("\n");
			roundResults = "";
			for(String s : roundResultsSplit)
				roundResults += "\t" + s + "\n";
			result += roundResults;
		}
		return result;
	}

	/**
	 * Used to track and log a skat rounds statistics, handles the message and error logging.
	 */
	public class RoundStats {
		private GameStats gameStatsParent;
		private Date dateRoundStarted;
		private Date dateRoundEnded;
		private List<String> logData;
		
		/**
		 * Our default constructor, creates a list of string logs we can append to for our statistics.
		 * @param parent The GameStats object who created this round, to be called on in case of a critical error.
		 */
		public RoundStats(GameStats parent) {
			this.gameStatsParent = parent;
			this.logData = new ArrayList<String>();
		}
		
		/**
		 * Marks the start of a new round, and sets the date accordingly.
		 */
		public void setRoundStart() {
			// Set the round start date as now.
			this.dateRoundStarted = new Date();
		}
		/**
		 * Marks the end of a new round, and sets the date accordingly.
		 */
		public void setRoundEnd() {
			// Set the round end date as now.
			this.dateRoundEnded = new Date();
		}

		/**
		 * Logs a non critical error for this round.
		 * @param text The error to log for this round.
		 */
		public void logError(String text) {
			this.logError(text, false);
		}
		/**
		 * Logs an error for this round.
		 * @param text The error to log for this round.
		 * @param critical Defines if the error is critical and should terminate the application.
		 */
		public void logError(String text, boolean critical) {
			this.logError(text, critical, 0);
		}
		/**
		 * Logs a non critical error for this round.
		 * @param text The error to log for this round.
		 * @param indentLevel An integer indicating how deep the text should be indented for formatting.
		 */
		public void logError(String text, int indentLevel) {
			this.logError(text, false, indentLevel);
		}
		/**
		 * Logs an error for this round.
		 * @param text The error to log for this round.
		 * @param critical Defines if the error is critical and should terminate the application.
		 * @param indentLevel An integer indicating how deep the text should be indented for formatting.
		 */
		public void logError(String text, boolean critical, int indentLevel) {
			// Determine our prefix for formatting.
			String prefix = null;
			if(critical)
				prefix = "[CRITICAL ERROR: ";
			else
				prefix = "[ERROR: ";
			
			// Log our error
			log(prefix + text + "]", indentLevel);
			
			// If critical error..
			if(critical) {
				// We want to end the game now.
				this.setRoundEnd();
				
				// We want to print out our statistics and terminate.
				System.out.print(this.gameStatsParent.toString());
				System.exit(0);
			}
		}
		
		/**
		 * Logs a message for this round.
		 * @param text The message to log for this round.
		 */
		public void log(String text) {
			// Add the string to our log data
			logData.add(text);
		}
		/**
		 * Logs a message for this round.
		 * @param text The message to log for this round.
		 * @param indentLevel An integer indicating how deep the text should be indented for formatting.
		 */
		public void log(String text, int indentLevel) {
			// Add our indentation.
			for(int i = 0; i < indentLevel; i++)
				text = "\t" + text;
			// Log our error.
			log(text);
		}
	
		/**
		 * Obtains the duration of the round, in seconds.
		 * @return The double representing the round length in seconds, or -1 if the round hasn't started/ended.
		 */
		public double getDuration() {
			// Return nothing if we don't have sufficient information.
			if(this.dateRoundStarted == null || this.dateRoundEnded == null)
				return -1;
			
			// Get our round duration
			long diffMilliseconds = (this.dateRoundEnded.getTime() - this.dateRoundStarted.getTime());
			double seconds = (double)diffMilliseconds / 1000;
			return seconds;
		}
		
		/**
		 * Returns a string representation of our entire round statistics.
		 */
		@Override
		public String toString() {
			// Populate our result with information.
			String result = "";
			result += "Round Duration: " + getDuration()  + " seconds\n";
			for(String s : logData)
				result += s + "\n";
			return result;
		}
	}
}
