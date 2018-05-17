package io.realm;


import android.util.JsonReader;
import io.realm.internal.ColumnInfo;
import io.realm.internal.ImplicitTransaction;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.bahroel.spk.model.Cost;
import com.example.bahroel.spk.model.Generate;
import com.example.bahroel.spk.model.WarehouseDestination;
import com.example.bahroel.spk.model.WarehouseSource;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmObject>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmObject>> modelClasses = new HashSet<Class<? extends RealmObject>>();
        modelClasses.add(Cost.class);
        modelClasses.add(WarehouseSource.class);
        modelClasses.add(Generate.class);
        modelClasses.add(WarehouseDestination.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public Table createTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return CostRealmProxy.initTable(transaction);
        } else if (clazz.equals(WarehouseSource.class)) {
            return WarehouseSourceRealmProxy.initTable(transaction);
        } else if (clazz.equals(Generate.class)) {
            return GenerateRealmProxy.initTable(transaction);
        } else if (clazz.equals(WarehouseDestination.class)) {
            return WarehouseDestinationRealmProxy.initTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmObject> clazz, ImplicitTransaction transaction) {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return CostRealmProxy.validateTable(transaction);
        } else if (clazz.equals(WarehouseSource.class)) {
            return WarehouseSourceRealmProxy.validateTable(transaction);
        } else if (clazz.equals(Generate.class)) {
            return GenerateRealmProxy.validateTable(transaction);
        } else if (clazz.equals(WarehouseDestination.class)) {
            return WarehouseDestinationRealmProxy.validateTable(transaction);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return CostRealmProxy.getFieldNames();
        } else if (clazz.equals(WarehouseSource.class)) {
            return WarehouseSourceRealmProxy.getFieldNames();
        } else if (clazz.equals(Generate.class)) {
            return GenerateRealmProxy.getFieldNames();
        } else if (clazz.equals(WarehouseDestination.class)) {
            return WarehouseDestinationRealmProxy.getFieldNames();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public String getTableName(Class<? extends RealmObject> clazz) {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return CostRealmProxy.getTableName();
        } else if (clazz.equals(WarehouseSource.class)) {
            return WarehouseSourceRealmProxy.getTableName();
        } else if (clazz.equals(Generate.class)) {
            return GenerateRealmProxy.getTableName();
        } else if (clazz.equals(WarehouseDestination.class)) {
            return WarehouseDestinationRealmProxy.getTableName();
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E newInstance(Class<E> clazz, ColumnInfo columnInfo) {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return clazz.cast(new CostRealmProxy(columnInfo));
        } else if (clazz.equals(WarehouseSource.class)) {
            return clazz.cast(new WarehouseSourceRealmProxy(columnInfo));
        } else if (clazz.equals(Generate.class)) {
            return clazz.cast(new GenerateRealmProxy(columnInfo));
        } else if (clazz.equals(WarehouseDestination.class)) {
            return clazz.cast(new WarehouseDestinationRealmProxy(columnInfo));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public Set<Class<? extends RealmObject>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmObject> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmObject, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(Cost.class)) {
            return clazz.cast(CostRealmProxy.copyOrUpdate(realm, (Cost) obj, update, cache));
        } else if (clazz.equals(WarehouseSource.class)) {
            return clazz.cast(WarehouseSourceRealmProxy.copyOrUpdate(realm, (WarehouseSource) obj, update, cache));
        } else if (clazz.equals(Generate.class)) {
            return clazz.cast(GenerateRealmProxy.copyOrUpdate(realm, (Generate) obj, update, cache));
        } else if (clazz.equals(WarehouseDestination.class)) {
            return clazz.cast(WarehouseDestinationRealmProxy.copyOrUpdate(realm, (WarehouseDestination) obj, update, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return clazz.cast(CostRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(WarehouseSource.class)) {
            return clazz.cast(WarehouseSourceRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(Generate.class)) {
            return clazz.cast(GenerateRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else if (clazz.equals(WarehouseDestination.class)) {
            return clazz.cast(WarehouseDestinationRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(Cost.class)) {
            return clazz.cast(CostRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(WarehouseSource.class)) {
            return clazz.cast(WarehouseSourceRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(Generate.class)) {
            return clazz.cast(GenerateRealmProxy.createUsingJsonStream(realm, reader));
        } else if (clazz.equals(WarehouseDestination.class)) {
            return clazz.cast(WarehouseDestinationRealmProxy.createUsingJsonStream(realm, reader));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public <E extends RealmObject> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmObject, RealmObjectProxy.CacheData<RealmObject>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(Cost.class)) {
            return clazz.cast(CostRealmProxy.createDetachedCopy((Cost) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(WarehouseSource.class)) {
            return clazz.cast(WarehouseSourceRealmProxy.createDetachedCopy((WarehouseSource) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(Generate.class)) {
            return clazz.cast(GenerateRealmProxy.createDetachedCopy((Generate) realmObject, 0, maxDepth, cache));
        } else if (clazz.equals(WarehouseDestination.class)) {
            return clazz.cast(WarehouseDestinationRealmProxy.createDetachedCopy((WarehouseDestination) realmObject, 0, maxDepth, cache));
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

}
