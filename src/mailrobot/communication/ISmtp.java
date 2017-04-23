package mailrobot.communication;

/**
 * SMTP Procotol for the communication
 *
 * @author Maxime Guillod
 */
public interface ISmtp {

    public static final String CMD_HELLO = "EHLO Maxime";
    public static final String CMD_MAIL_FROM = "MAIL FROM: ";
    public static final String CMD_MAIL_RCP = "RCPT TO: ";
    public static final String CMD_DATA = "DATA";
    public static final String CMD_END_DATA = ".";

    public static final String HEADER_FROM = "From:";
    public static final String HEADER_TO = "To:";
    public static final String HEADER_SUBJECT = "Subject:";
    public static final String HEADER_END = "";
}
