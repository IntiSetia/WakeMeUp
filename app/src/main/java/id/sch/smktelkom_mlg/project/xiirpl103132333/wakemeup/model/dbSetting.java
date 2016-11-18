package id.sch.smktelkom_mlg.project.xiirpl103132333.wakemeup.model;

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "dbSetting", id = "_id")
public class dbSetting extends Model {

    @Column(name = "id")
    public long id;
    @Column(name = "volume")
    public long volume;
    @Column(name = "muteDuration")
    public long muteDuration;


    public dbSetting() {
        super();
    }

    public dbSetting(long id, long volume, long muteDuration) {
        super();
        this.id = id;
        this.volume = volume;
        this.muteDuration = muteDuration;
    }

    public static Cursor getAllCursor() {
        String resultRecords = new Select().from(dbSetting.class).toSql();
        return Cache.openDatabase().rawQuery(resultRecords, null);
    }

    public List<dbSetting> getAll() {
        return new Select().from(this.getClass()).execute();
    }
}
