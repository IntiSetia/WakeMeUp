package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

/**
 * Created by euiko on 11/18/16.
 */

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

@Table(name = "dbAlarm", id = "alarmId")
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
    @Column(name = "isEnabled")
    public boolean isEnabled;

    public dbAlarm() {
        super();
    }

    public dbAlarm(long id, String hours, String days, String ringtone, int method, int level, String memo, boolean isEnabled) {
        super();
        this.id = id;
        this.hours = hours;
        this.days = days;
        this.ringtone = ringtone;
        this.method = method;
        this.level = level;
        this.memo = memo;
        this.isEnabled = isEnabled;
    }

    public static Cursor getAllCursor() {
        String resultRecords = new Select().from(dbAlarm.class).toSql();
        return Cache.openDatabase().rawQuery(resultRecords, null);
    }

    public static List<dbAlarm> getAllEnabled() {
        return new Select().from(dbAlarm.class).where("isEnabled = ?", 1).execute();
    }

    public static List<dbAlarm> getAllDayOn(int dayI) {
        return new Select().from(dbAlarm.class).where("SUBSTR(days, ?, 1) = '1' AND isEnabled = ?", dayI, 1
        ).orderBy("hours ASC").execute();
    }

    public static List<dbAlarm> getAll() {
        return new Select().from(dbAlarm.class).execute();
    }

    public static long getMaxID() {
        return new Select("alarmId").from(dbAlarm.class).orderBy("alarmId DESC").limit(1).executeSingle().getId();
    }

    public void saveAlarm(long id, String hours, String days, String ringtone, int method, int level, String memo, boolean isEnabled) {
        this.id = id;
        this.hours = hours;
        this.days = days;
        this.ringtone = ringtone;
        this.method = method;
        this.level = level;
        this.memo = memo;
        this.isEnabled = isEnabled;
        this.save();
    }

    public List<dbAlarm> getDataAtId(long id) {
        return new Select().from(dbAlarm.class).where("alarmId = ?", id).limit(1).execute();
    }

    public void editAlarm(long id, String hours, String days, String ringtone, int method, int level, String memo) {
        new Update(dbAlarm.class)
                .set("hours = ?, days = ?, ringtone = ?, method = ?, level = ?, memo = ?",
                        hours, days, ringtone, method, level, memo)
                .where("alarmId = ?", id)
                .execute();
    }

    public void enDisAlarm(long id, boolean isEnabled) {
        new Update(dbAlarm.class)
                .set("isEnabled = ?", isEnabled ? 1 : 0)
                .where("alarmId = ?", id)
                .execute();

    }

    public void deleteAlarm(long id) {
        new Delete().from(dbAlarm.class).where("alarmId = ?", id).execute();
    }
}

