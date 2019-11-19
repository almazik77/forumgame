package models;

public class Phrase {
    private long id;
    private String text;
    private boolean isChecked;
    private Long userId;

    public Phrase() {

    }

    public Phrase(long id, String text, boolean isChecked, Long userId) {
        this.id = id;
        this.text = text;
        this.isChecked = isChecked;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isChecked=" + isChecked +
                ", userId=" + userId +
                '}';
    }
}
