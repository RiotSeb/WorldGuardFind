package de.riotseb.worldguardzones.handler;

public enum MessageKey {
	NO_PERMISSIONS("no perms"),
	USAGE("usage"),
	USAGE_HOVER("usage hover"),
	FIND_ENABLED("find enabled"),
	FIND_DISABLED("find disabled"),
	NO_REGION_FOUND("no region found"),
	REGION_FOUND("region found");

	private String key;

	MessageKey(String key) {
		this.key = key.toLowerCase();
	}

	public String getMessage() {
		return MessageHandler.getInstance().getMessage(key);
	}

	public String getReplacedMessage(String expression, String replacement) {
		return MessageHandler.getInstance().getMessage(key).replace(expression, replacement);
	}

}

