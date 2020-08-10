package edu.ufl.cise.cs1.controllers;

import java.util.ArrayList;
import game.models.*;
import game.controllers.AttackerController;
import java.util.List;

public final class StudentAttackerController implements AttackerController
{
	//Initialize static variables
	public static int level;
	public static List<Node> pills;
	public static List<Node> powerPills;
	public static List<Node> junctions;

	public static List<Node> junctionUpdate(Game game)
	{
		return new ArrayList<>(game.getCurMaze().getJunctionNodes());
	}

	public static List<Node> pillUpdate(Game game)
	{

		return new ArrayList<>(game.getCurMaze().getPillNodes());
	}

	public static List<Node> powerPillUpdate(Game game)
	{
		return new ArrayList<>(game.getCurMaze().getPowerPillNodes());
	}

	//Initializes the game
	public void init(Game game)
	{
		//Initialize the level
		StudentAttackerController.level = 1;

		//Initialize list of pills, powerPills, and junctions
		StudentAttackerController.pills = pillUpdate(game);
		StudentAttackerController.powerPills = powerPillUpdate(game);
		StudentAttackerController.junctions = junctionUpdate(game);
	}

	public void shutdown(Game game) { }

	//Updates the game each tick
	public int update(Game game, long timeDue)
	{
		//Updates the attacker and its location
		Attacker attacker = game.getAttacker();
		Node attackerLocation = attacker.getLocation();

		//Finds the nearest pill, powerPill, and junction
		boolean noPills = pills.isEmpty();
		boolean noPowerPills = powerPills.isEmpty();
		Node closestPill = null;
		if(!noPills)
		{
			closestPill = attacker.getTargetNode(pills, true);
		}
		Node closestPowerPill = null;
		if(!noPowerPills)
		{
			closestPowerPill = attacker.getTargetNode(powerPills, true);
		}

		//Updates lists for defender locations
		List<Defender> defendList = game.getDefenders();
		List<Node> defenderLocations = new ArrayList<>();
		List<Node> normalDefenderLocations = new ArrayList<>();
		List<Node> vulnerableDefenderLocations = new ArrayList<>();
		for(Defender defender : defendList)
		{
			//Add to defender list
			Node location = defender.getLocation();
			defenderLocations.add(location);

			//Add to normal and vulnerable lists
			boolean vulnerable = defender.isVulnerable();
			if(!vulnerable)
			{
				normalDefenderLocations.add(location);
			}
			else
			{
				vulnerableDefenderLocations.add(location);
			}

		}

		//Finds nearest defender
		Node closestDefender = attacker.getTargetNode(defenderLocations, true);

		//Finds nearest Vulnerable defender
		boolean noVulnerableDefenders = vulnerableDefenderLocations.isEmpty();
		Node closestVulnerableDefender = null;
		if(!noVulnerableDefenders)
		{
			closestVulnerableDefender = attacker.getTargetNode(vulnerableDefenderLocations, true);
		}

		//Finds nearest Normal defender(s)
		boolean noNormalDefenders = normalDefenderLocations.isEmpty();
		List<Node> closestNormalDefenders = new ArrayList<>();
		if(!noNormalDefenders)
		{
			for(Node defender : normalDefenderLocations)
			{
				defender = attacker.getTargetNode(normalDefenderLocations, true);
				closestNormalDefenders.add(defender);
			}
		}

		//Checks Normal defender distance
		int normalDistance;
		boolean runAway = false;
		if(!noNormalDefenders)
		{
			normalDistance = attackerLocation.getPathDistance(closestNormalDefenders.get(0));
			if(normalDistance < 5 && normalDistance != -1)
			{
				runAway = true;
			}
		}

		//Checks Vulnerable defender distance
		int vulnerableDistance = -1;
		if(!noVulnerableDefenders)
		{
			vulnerableDistance = attackerLocation.getPathDistance(closestVulnerableDefender);
		}

		//Removes pills and powerPills if attacker is on top of them
		pills.remove(attackerLocation);
		powerPills.remove(attackerLocation);

		//Update the game to the next level
		if(game.getLevel() != level)
		{
			level = game.getLevel();

			//Updates the pill and powerPill locations
			pills = pillUpdate(game);
			powerPills = powerPillUpdate(game);
			junctions = junctionUpdate(game);
		}

		//POSSIBLE ATTACKER OPTIONS
		int nextAction;
		int defenderAction = attacker.getNextDir(closestDefender, true);
		int runAwayAction = attacker.getNextDir(closestDefender, false);
		int pillAction = 0;
		if(!noPills)
		{
			pillAction = attacker.getNextDir(closestPill, true);
		}
		int powerPillAction = 0;
		if(!noPowerPills)
		{
			powerPillAction = attacker.getNextDir(closestPowerPill, true);
		}

		//OPTION 1: Try to chase closest Vulnerable defenders
		for(Defender defender: defendList)
		{
			Node location = defender.getLocation();
			int vulnerableTime = defender.getVulnerableTime();
			/*
			Checks if the Vulnerable defender is closest and is close enough
			Checks if vulnerable time is not about to end
			Checks if closest Normal defender is not nearby or is in the box
			 */
			if(closestVulnerableDefender != null && location == closestVulnerableDefender && vulnerableDistance < 70 &&  vulnerableTime > 30 && !runAway)
			{
				nextAction = attacker.getNextDir(location, true);
				return nextAction;
			}
		}

		//OPTION 2: Run away from closest Normal defender
		if(runAway)
		{
			//Case 1: Move to a powerPill
			if(!noPowerPills && powerPillAction != defenderAction)
			{
				nextAction = powerPillAction;
				return nextAction;
			}
			//Case 2: Move to a pill
			if(!noPills && pillAction != defenderAction)
			{
				nextAction = pillAction;
			}
			//Case 3: Run Away
			else
			{
				nextAction = runAwayAction;
			}

			return nextAction;
		}

		//OPTION 3: All Normal defenders, get powerPills
		if(noVulnerableDefenders && !noPowerPills)
		{
			nextAction = powerPillAction;
			return nextAction;
		}
		//Default to getting pills
		else
		{
			nextAction = pillAction;
		}

		return nextAction;

	}
}