package forms;

import play.data.validation.Constraints;

public class LeagueForm {

	/*the name of the league*/
	@Constraints.Required(message = "League name is required")
    public String leagueName;

	/*A short description of the league*/
    @Constraints.Required(message = "Description is required")
    public String description;
    
    /*the number of teams in the league*/
    @Constraints.Required(message = "Number of teams is required")
    public int numTeams;
    
    /*The start date of the league*/
    @Constraints.Required(message = "Start date is required")
    public int startDate;
    
    /*The end date of the league*/
    public int endDate;
    
    /**
     * no arguments constructor
     */
    public LeagueForm(){
    	
    }

    public LeagueForm(String leagueName, String description, int numTeams, int startDate, int endDate)
    {
        this.leagueName = leagueName;
        this.description = description;
        this.numTeams = numTeams;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public LeagueForm(String leagueName, String description, int numTeams, int startDate)
    {
        this.leagueName = leagueName;
        this.description = description;
        this.numTeams = numTeams;
        this.startDate = startDate;
    }
}
