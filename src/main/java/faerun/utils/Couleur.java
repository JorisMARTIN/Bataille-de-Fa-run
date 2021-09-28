package faerun.utils;

public enum Couleur {
	
	ROUGE("Rouge", "\u001B[31m"), BLEU("Bleu", "\u001B[34m"), BLANC("Blanc", "");
	
	private String name;
	private String terminalColorCode;
	
	private Couleur(String name, String terminalColorCode) {
		this.name = name;
		this.terminalColorCode = terminalColorCode;
	}

	public String getName() {
		return name;
	}

	public String getTerminalColorCode() {
		return terminalColorCode;
	}
	
	public static final String TERMINAL_RESET_COLOR_CODE = "\u001B[0m";
}
