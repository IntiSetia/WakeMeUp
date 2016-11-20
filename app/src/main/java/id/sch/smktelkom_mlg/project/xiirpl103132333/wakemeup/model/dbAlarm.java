package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

/**
 * Created by euiko on 11/18/16.
 */

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "dbAlarm", id = "_id")
public class dbAlarm extends Model {
    @Column(name = "id")
    public long id;
    @Column(name = "hours")
    public String hours;
    @Column(name = "days")
    public String days;
    @Column(name = "ringtone")
    public String ringtone;
    @Column(name = "method")
    public int method;
    @Column(name = "level")
    public int level;
    @Column(name = "memo")
    public String memo;

    public dbAlarm() {
        super();
    }

    public dbAlarm(long id, String hours, String days, String ringtone, int method, int level, String memo) {
        super();
        this.id = id;
        this.hours = hours;
        this.days = days;
        this.ringtone = ringtone;
        this.method = method;
        this.level = level;
        this.memo = memo;
    }

    public static Cursor getAllCursor() {
        String resultRecords = new Select().from(dbAlarm.class).toSql();
        return Cache.openDatabase().rawQuery(resultRecords, null);
    }

    public List<dbAlarm> getAll() {
        return new Select().from(this.getClass()).execute();
    }

    public void saveAlarm(String hours, String days, String ringtone, int method, int level, String memo) {
        this.hours = hours;
        this.days = days;
        this.ringtone = ringtone;
        this.method = method;
        this.level = level;
        this.memo = memo;
        this.save();
    }
}

