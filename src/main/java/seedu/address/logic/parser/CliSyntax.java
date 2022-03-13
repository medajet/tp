package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_ID = new Prefix("i/");
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_MODULE_CODE = new Prefix("m/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_TELEGRAM_HANDLE = new Prefix("h/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/"); // @Brandon: Delete this field
    public static final Prefix PREFIX_TAG = new Prefix("t/"); // @Brandon: Delete this field
    public static final Prefix PREFIX_TASK_NAME = new Prefix("tn/");
}
