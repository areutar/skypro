package pro.sky.telegrambot.command;

public enum CommandNames {
    START("/start", 6),
    STOP("/stop", 5),
    HELP("/help", 5),
    UNKNOWN("/unknown", 7),
    CREATE("/create", 7),
    EDIT("/edit", 5),
    FIND("/find", 5),
    DELETE("/delete", 7),
    ALL("/all", 4);

    private final String commandName;
    private final Integer length;

    CommandNames(String commandName, Integer length) {
        this.commandName = commandName;
        this.length = length;
    }

    public String getCommandName() {
        return commandName;
    }

    public Integer getLength() {
        return length;
    }

    public static CommandNames valueOfCommandName(String name) {
        for (CommandNames value : values()) {
            if (value.commandName.equals(name)) {
                return value;
            }
        }
        return CommandNames.UNKNOWN;
    }

}
