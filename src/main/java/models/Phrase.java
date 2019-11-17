package models;

public class Phrase {
    private long Id;
    private String text;
    private boolean isChecked;

    public Phrase() {

    }

    public Phrase(long id, String text, boolean isChecked) {
        Id = id;
        this.text = text;
        this.isChecked = isChecked;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
