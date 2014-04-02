package views.formdata.leagues;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints;

@Entity
public class LeagueForm {

	@Id
	/*the id number of the league*/
	private long id;
	
	/*the name of the league*/
	@Constraints.Required(message = "League name is required")
    private String leagueName;

	/*A short description of the league*/
    @Constraints.Required(message = "Description is required")
    private String description;
    
    /*the number of teams in the league*/
    @Constraints.Required(message = "Number of teams is required")
    private int numTeams;
    
    /*The start date of the league*/
    @Constraints.Required(message = "Start date is required")
    private int startDate;
    
    /*The end date of the league*/
    private int endDate;
    
    /**
     * no arguments constructor
     */
    public LeagueForm(){
    	
    }

    /**
     * Constructor for defining all the attributes of the league
     * 
     * @param leagueName the name of the league
     * @param description a short description of the league
     * @param numTeams the number of teams in the league
     * @param startDate the starting date of the league
     * @param endDate the ending date of the league
     */
    
    public LeagueForm(String leagueName, String description, int numTeams, int startDate, int endDate)
    {
        this.leagueName = leagueName;
        this.description = description;
        this.numTeams = numTeams;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for defining only the required elements of the league
     * 
     * @param leagueName the name of the league
     * @param description a short description of the league
     * @param numTeams the number of teams in the league
     * @param startDate the starting date of the league
     */
    
    public LeagueForm(String leagueName, String description, int numTeams, int startDate)
    {
        this.leagueName = leagueName;
        this.description = description;
        this.numTeams = numTeams;
        this.startDate = startDate;
    }

    /**
     * gets the id
     * @return id the id of the league
     */
	public long getId() {
		return id;
	}

	/**
	 * set the league id
	 * @param id the new id of the league
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * get the league name
	 * @return leagueName the name of the league
	 */
	public String getLeagueName() {
		return leagueName;
	}

	/**
	 * sets the league name
	 * @param leagueName the new name of the league
	 */
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	/**
	 * get the description of this league
	 * @return description the description of this league
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the league
	 * @param description the new description for this league
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the number of teams in the league
	 * @return numTeams the number of teams in the league
	 */
	public int getNumTeams() {
		return numTeams;
	}

	/**
	 * sets the number of teams in the league
	 * @param numTeams the new number of teams in this league
	 */
	public void setNumTeams(int numTeams) {
		this.numTeams = numTeams;
	}

	/**
	 * gets the start date of the league
	 * @return startDate the starting date of this league
	 */
	public int getStartDate() {
		return startDate;
	}

	/**
	 * set the start date of the league
	 * @param startDate the new start date of the league
	 */
	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	/**
	 * gets the end date of the league
	 * @return endDate the end date of this league
	 */
	public int getEndDate() {
		return endDate;
	}

	/**
	 * set the end date of the league
	 * @param endDate the new end date of the league
	 */
	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}
    
}
