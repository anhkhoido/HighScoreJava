/**
 * 
 * Description: This class represents the player's score.
 * 
 * @author Anh Khoi Do
 * @version 1.0
 * 
 */


public class Score {
	private double _score;

	/**
	 * The safe constructor.
	 */
	public Score()
	{
		_score = 0;
	}
	
	/**
	 * The overloaded constructor.
	 * 
	 * @param s	The score sent by the user.
	 */
	public Score(double s)
	{
		_score = s;
	}
	
	/**
	 * Mutator: Set the score.
	 * @param s	The score sent by the user.
	 */
	public void setScore(double s)
	{
		_score = s;
	}
	
	/**
	 * Accessor: This method returns the player's score.
	 * @return _score	The player's score.
	 */
	public final double getScore()
	{
		return _score;
	}
}
