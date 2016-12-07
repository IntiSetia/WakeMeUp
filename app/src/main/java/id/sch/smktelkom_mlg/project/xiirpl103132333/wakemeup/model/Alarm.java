package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

public class Alarm {

    public long id;
    public String hours;
    public String days;
    public String ringtone;
    public int method;
    public int level;
    public String memo;
    public boolean isEnabled;

    public Alarm(long id, String hours, String days, String ringtone, int method, int level, String memo, boolean isEnabled) {
        this.id = id;
        this.hours = hours;
        this.days = days;
        this.ringtone = ringtone;
        this.method = method;
        this.level = level;
        this.memo = memo;
        this.isEnabled = isEnabled;
    }
}
