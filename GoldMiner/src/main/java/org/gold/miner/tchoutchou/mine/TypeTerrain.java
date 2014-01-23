package org.gold.miner.tchoutchou.mine;

public enum TypeTerrain {

	M("MUD"), S("STONE"), E("EMPTY"), D("DIAMOND"), X("TROLLEY");

	private String type;

	private TypeTerrain(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
