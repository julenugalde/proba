package eus.julenugalde.thinkinginjava.beans;

public class Pokemon {
	private int generation;
	private int pokedexNumber;
	private String name;
	private int maxCP;
	private Pokemon previousEvolution;
	private Pokemon nextEvolution;
	private boolean isLegendaryOrMythical;
	
	/**
	 * @return the generation
	 */
	public int getGeneration() {
		return generation;
	}
	/**
	 * @param generation the generation to set
	 */
	public void setGeneration(int generation) {
		this.generation = generation;
	}
	/**
	 * @return the pokedexNumber
	 */
	public int getPokedexNumber() {
		return pokedexNumber;
	}
	/**
	 * @param pokedexNumber the pokedexNumber to set
	 */
	public void setPokedexNumber(int pokedexNumber) {
		this.pokedexNumber = pokedexNumber;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the maxCP
	 */
	public int getMaxCP() {
		return maxCP;
	}
	/**
	 * @param maxCP the maxCP to set
	 */
	public void setMaxCP(int maxCP) {
		this.maxCP = maxCP;
	}
	/**
	 * @return the previousEvolution
	 */
	public Pokemon getPreviousEvolution() {
		return previousEvolution;
	}
	/**
	 * @param previousEvolution the previousEvolution to set
	 */
	public void setPreviousEvolution(Pokemon previousEvolution) {
		this.previousEvolution = previousEvolution;
	}
	/**
	 * @return the nextEvolution
	 */
	public Pokemon getNextEvolution() {
		return nextEvolution;
	}
	/**
	 * @param nextEvolution the nextEvolution to set
	 */
	public void setNextEvolution(Pokemon nextEvolution) {
		this.nextEvolution = nextEvolution;
	}
	/**
	 * @return the isLegendaryOrMythical
	 */
	public boolean isLegendaryOrMythical() {
		return isLegendaryOrMythical;
	}
	/**
	 * @param isLegendaryOrMythical the isLegendaryOrMythical to set
	 */
	public void setLegendaryOrMythical(boolean isLegendaryOrMythical) {
		this.isLegendaryOrMythical = isLegendaryOrMythical;
	}
	
	public int attack(Pokemon pokemon) {
		return 0;
	}
	
	public int defend(Pokemon pokemon) {
		return 0;
	}
	
}
