package io.realm;


import android.util.JsonReader;
import android.util.JsonToken;
import com.example.bahroel.spk.model.WarehouseSource;
import io.realm.RealmFieldType;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.LinkView;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Table;
import io.realm.internal.TableOrView;
import io.realm.internal.android.JsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WarehouseSourceRealmProxy extends WarehouseSource
    implements RealmObjectProxy {

    static final class WarehouseSourceColumnInfo extends ColumnInfo {

        public final long idIndex;
        public final long SourceNameIndex;
        public final long SourceAmountIndex;

        WarehouseSourceColumnInfo(String path, Table table) {
            final Map<String, Long> indicesMap = new HashMap<String, Long>(3);
            this.idIndex = getValidColumnIndex(path, table, "WarehouseSource", "id");
            indicesMap.put("id", this.idIndex);

            this.SourceNameIndex = getValidColumnIndex(path, table, "WarehouseSource", "SourceName");
            indicesMap.put("SourceName", this.SourceNameIndex);

            this.SourceAmountIndex = getValidColumnIndex(path, table, "WarehouseSource", "SourceAmount");
            indicesMap.put("SourceAmount", this.SourceAmountIndex);

            setIndicesMap(indicesMap);
        }
    }

    private final WarehouseSourceColumnInfo columnInfo;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("SourceName");
        fieldNames.add("SourceAmount");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    WarehouseSourceRealmProxy(ColumnInfo columnInfo) {
        this.columnInfo = (WarehouseSourceColumnInfo) columnInfo;
    }

    @Override
    @SuppressWarnings("cast")
    public long getId() {
        realm.checkIfValid();
        return (long) row.getLong(columnInfo.idIndex);
    }

    @Override
    public void setId(long value) {
        realm.checkIfValid();
        row.setLong(columnInfo.idIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSourceName() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.SourceNameIndex);
    }

    @Override
    public void setSourceName(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.SourceNameIndex);
            return;
        }
        row.setString(columnInfo.SourceNameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String getSourceAmount() {
        realm.checkIfValid();
        return (java.lang.String) row.getString(columnInfo.SourceAmountIndex);
    }

    @Override
    public void setSourceAmount(String value) {
        realm.checkIfValid();
        if (value == null) {
            row.setNull(columnInfo.SourceAmountIndex);
            return;
        }
        row.setString(columnInfo.SourceAmountIndex, value);
    }

    public static Table initTable(ImplicitTransaction transaction) {
        if (!transaction.hasTable("class_WarehouseSource")) {
            Table table = transaction.getTable("class_WarehouseSource");
            table.addColumn(RealmFieldType.INTEGER, "id", Table.NOT_NULLABLE);
            table.addColumn(RealmFieldType.STRING, "SourceName", Table.NULLABLE);
            table.addColumn(RealmFieldType.STRING, "SourceAmount", Table.NULLABLE);
            table.addSearchIndex(table.getColumnIndex("id"));
            table.setPrimaryKey("id");
            return table;
        }
        return transaction.getTable("class_WarehouseSource");
    }

    public static WarehouseSourceColumnInfo validateTable(ImplicitTransaction transaction) {
        if (transaction.hasTable("class_WarehouseSource")) {
            Table table = transaction.getTable("class_WarehouseSource");
            if (table.getColumnCount() != 3) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field count does not match - expected 3 but was " + table.getColumnCount());
            }
            Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
            for (long i = 0; i < 3; i++) {
                columnTypes.put(table.getColumnName(i), table.getColumnType(i));
            }

            final WarehouseSourceColumnInfo columnInfo = new WarehouseSourceColumnInfo(transaction.getPath(), table);

            if (!columnTypes.containsKey("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("id") != RealmFieldType.INTEGER) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'long' for field 'id' in existing Realm file.");
            }
            if (table.isColumnNullable(columnInfo.idIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using io.realm.internal.Table.convertColumnToNotNullable().");
            }
            if (table.getPrimaryKey() != table.getColumnIndex("id")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Primary key not defined for field 'id' in existing Realm file. Add @PrimaryKey.");
            }
            if (!table.hasSearchIndex(table.getColumnIndex("id"))) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Index not defined for field 'id' in existing Realm file. Either set @Index or migrate using io.realm.internal.Table.removeSearchIndex().");
            }
            if (!columnTypes.containsKey("SourceName")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'SourceName' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("SourceName") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'SourceName' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.SourceNameIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'SourceName' is required. Either set @Required to field 'SourceName' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            if (!columnTypes.containsKey("SourceAmount")) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Missing field 'SourceAmount' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
            }
            if (columnTypes.get("SourceAmount") != RealmFieldType.STRING) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Invalid type 'String' for field 'SourceAmount' in existing Realm file.");
            }
            if (!table.isColumnNullable(columnInfo.SourceAmountIndex)) {
                throw new RealmMigrationNeededException(transaction.getPath(), "Field 'SourceAmount' is required. Either set @Required to field 'SourceAmount' or migrate using io.realm.internal.Table.convertColumnToNullable().");
            }
            return columnInfo;
        } else {
            throw new RealmMigrationNeededException(transaction.getPath(), "The WarehouseSource class is missing from the schema for this Realm.");
        }
    }

    public static String getTableName() {
        return "class_WarehouseSource";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static WarehouseSource createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        WarehouseSource obj = null;
        if (update) {
            Table table = realm.getTable(WarehouseSource.class);
            long pkColumnIndex = table.getPrimaryKey();
            if (!json.isNull("id")) {
                long rowIndex = table.findFirstLong(pkColumnIndex, json.getLong("id"));
                if (rowIndex != TableOrView.NO_MATCH) {
                    obj = new WarehouseSourceRealmProxy(realm.schema.getColumnInfo(WarehouseSource.class));
                    obj.realm = realm;
                    obj.row = table.getUncheckedRow(rowIndex);
                }
            }
        }
        if (obj == null) {
            if (json.has("id")) {
                if (json.isNull("id")) {
                    obj = realm.createObject(WarehouseSource.class, null);
                } else {
                    obj = realm.createObject(WarehouseSource.class, json.getLong("id"));
                }
            } else {
                obj = realm.createObject(WarehouseSource.class);
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
            } else {
                obj.setId((long) json.getLong("id"));
            }
        }
        if (json.has("SourceName")) {
            if (json.isNull("SourceName")) {
                obj.setSourceName(null);
            } else {
                obj.setSourceName((String) json.getString("SourceName"));
            }
        }
        if (json.has("SourceAmount")) {
            if (json.isNull("SourceAmount")) {
                obj.setSourceAmount(null);
            } else {
                obj.setSourceAmount((String) json.getString("SourceAmount"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    public static WarehouseSource createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        WarehouseSource obj = realm.createObject(WarehouseSource.class);
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field id to null.");
                } else {
                    obj.setId((long) reader.nextLong());
                }
            } else if (name.equals("SourceName")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSourceName(null);
                } else {
                    obj.setSourceName((String) reader.nextString());
                }
            } else if (name.equals("SourceAmount")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    obj.setSourceAmount(null);
                } else {
                    obj.setSourceAmount((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return obj;
    }

    public static WarehouseSource copyOrUpdate(Realm realm, WarehouseSource object, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        if (object.realm != null && object.realm.getPath().equals(realm.getPath())) {
            return object;
        }
        WarehouseSource realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(WarehouseSource.class);
            long pkColumnIndex = table.getPrimaryKey();
            long rowIndex = table.findFirstLong(pkColumnIndex, object.getId());
            if (rowIndex != TableOrView.NO_MATCH) {
                realmObject = new WarehouseSourceRealmProxy(realm.schema.getColumnInfo(WarehouseSource.class));
                realmObject.realm = realm;
                realmObject.row = table.getUncheckedRow(rowIndex);
                cache.put(object, (RealmObjectProxy) realmObject);
            } else {
                canUpdate = false;
            }
        }

        if (canUpdate) {
            return update(realm, realmObject, object, cache);
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static WarehouseSource copy(Realm realm, WarehouseSource newObject, boolean update, Map<RealmObject,RealmObjectProxy> cache) {
        WarehouseSource realmObject = realm.createObject(WarehouseSource.class, newObject.getId());
        cache.put(newObject, (RealmObjectProxy) realmObject);
        realmObject.setId(newObject.getId());
        realmObject.setSourceName(newObject.getSourceName());
        realmObject.setSourceAmount(newObject.getSourceAmount());
        return realmObject;
    }

    public static WarehouseSource createDetachedCopy(WarehouseSource realmObject, int currentDepth, int maxDepth, Map<RealmObject, CacheData<RealmObject>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<WarehouseSource> cachedObject = (CacheData) cache.get(realmObject);
        WarehouseSource standaloneObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return cachedObject.object;
            } else {
                standaloneObject = cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            standaloneObject = new WarehouseSource();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmObject>(currentDepth, standaloneObject));
        }
        standaloneObject.setId(realmObject.getId());
        standaloneObject.setSourceName(realmObject.getSourceName());
        standaloneObject.setSourceAmount(realmObject.getSourceAmount());
        return standaloneObject;
    }

    static WarehouseSource update(Realm realm, WarehouseSource realmObject, WarehouseSource newObject, Map<RealmObject, RealmObjectProxy> cache) {
        realmObject.setSourceName(newObject.getSourceName());
        realmObject.setSourceAmount(newObject.getSourceAmount());
        return realmObject;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("WarehouseSource = [");
        stringBuilder.append("{id:");
        stringBuilder.append(getId());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{SourceName:");
        stringBuilder.append(getSourceName() != null ? getSourceName() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{SourceAmount:");
        stringBuilder.append(getSourceAmount() != null ? getSourceAmount() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {
        String realmName = realm.getPath();
        String tableName = row.getTable().getName();
        long rowIndex = row.getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseSourceRealmProxy aWarehouseSource = (WarehouseSourceRealmProxy)o;

        String path = realm.getPath();
        String otherPath = aWarehouseSource.realm.getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;;

        String tableName = row.getTable().getName();
        String otherTableName = aWarehouseSource.row.getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (row.getIndex() != aWarehouseSource.row.getIndex()) return false;

        return true;
    }

}
