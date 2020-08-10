public class Pakuri
{
    private String species;
    private int attack, defense, speed;

    /**
     * Constructor method that creates an instance of a Pakuri
     * @param species name of the Pakuri
     */
    public Pakuri(String species)
    {
        this.species = species;
        this.attack = (species.length() * 7) + 9;
        this.defense = (species.length() * 5) + 17;
        this.speed = (species.length() * 6) + 13;
    }

    //Getter methods
    public String getSpecies()
    {
        return this.species;
    }

    public int getAttack()
    {
        return this.attack;
    }

    public int getDefense()
    {
        return this.defense;
    }

    public int getSpeed()
    {
        return this.speed;
    }

    public void setAttack(int attack)
    {
        this.attack = attack;
    }

    //Increases Pakuri stats for Pakuri
    public void evolve()
    {
        this.attack = attack * 2;
        this.defense = defense * 4;
        this.speed = speed * 3;
    }

}
