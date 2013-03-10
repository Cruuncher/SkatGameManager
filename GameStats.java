import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class GameStats {
	private List<RoundStats> roundStats;
	
	public GameStats() {
		// Initialize our list.
		roundStats = new ArrayList<RoundStats>();
	}
	
	public RoundStats createNewRound() {
		// Simply add our new round to our list.
		RoundStats newRound = new RoundStats(this);
		roundStats.add(newRound);
		return newRound;
	}
	public RoundStats getCurrentRound() {
		// We must have a created round to return one.
		if(roundStats.size() == 0)
			return null;
		
		// Return our last round stats.
		return roundStats.get(roundStats.size() - 1);
	}

	@Override
	public String toString() {
		// Populate our result with information.
		String result = "";
		
		// Print our header
		result += "Game Result Statistics\n";
		result += "----------------------\n";
		result += "Round Count: " + roundStats.size() + "\n";
		result += "----------------------\n";
		
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

	public class RoundStats {
		private GameStats gameStatsParent;
		private Date dateRoundStarted;
		private Date dateRoundEnded;
		private List<String> logData;
		
		public RoundStats(GameStats parent) {
			this.gameStatsParent = parent;
			this.logData = new ArrayList<String>();
		}
		
		public void setRoundStart() {
			// Set the round start date as now.
			this.dateRoundStarted = new Date();
		}
		public void setRoundEnd() {
			// Set the round end date as now.
			this.dateRoundEnded = new Date();
		}


		public void logError(String text) {
			this.logError(text, false);
		}
		public void logError(String text, boolean critical) {
			this.logError(text, critical, 0);
		}
		public void logError(String text, int indentLevel) {
			this.logError(text, false, indentLevel);
		}
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
				// We want to print out our statistics and terminate.
				System.out.print(this.gameStatsParent.toString());
				System.exit(0);
			}
		}
		public void log(String text) {
			// Add the string to our log data
			logData.add(text);
		}
		public void log(String text, int indentLevel) {
			// Add our indentation.
			for(int i = 0; i < indentLevel; i++)
				text = "\t" + text;
			// Log our error.
			log(text);
		}
	
		private String getDurationString() {
			
			// Get our round duration
			long diffMilliseconds = (this.dateRoundEnded.getTime() - this.dateRoundStarted.getTime());
			double seconds = (double)diffMilliseconds / 1000;
			
			String result = "Round Duration: " + String.valueOf(seconds)  + " seconds";
			return result;
		}
		
		@Override
		public String toString() {
			// Populate our result with information.
			String result = "";
			result += getDurationString() + "\n";
			for(String s : logData)
				result += s + "\n";
			return result;
		}
	}
}
