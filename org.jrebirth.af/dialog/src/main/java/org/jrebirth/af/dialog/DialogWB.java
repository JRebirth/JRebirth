package org.jrebirth.af.dialog;

import org.jrebirth.af.api.wave.WaveBean;

public class DialogWB implements WaveBean {

    public enum DialogType {

        Info,
        Warning,
        Error

    }

    /** The type of dialog to open. */
    private DialogType dialogType;

    private String title;

    private String header;
    private String message;

    private Throwable exception;

    /**
     * @return Returns the dialogType.
     */
    public DialogType getDialogType() {
        return dialogType;
    }

    /**
     * @param dialogType The dialogType to set.
     */
    public void setDialogType(DialogType dialogType) {
        this.dialogType = dialogType;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the header.
     */
    public String getHeader() {
        return header;
    }

    /**
     * @param header The header to set.
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * @return Returns the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return Returns the exception.
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * @param exception The exception to set.
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

}
