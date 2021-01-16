package compontent;

import com.intellij.ide.util.PropertiesComponent;


public class StorageComponent {
    /**
     * 存储
     *
     * @param key   key
     * @param value value
     */
    public static void save(String key, String value) {
        PropertiesComponent.getInstance().setValue(key, value);
    }

    /**
     * 取值
     *
     * @param key key
     * @return 如果存在则返回值，不存在则返回null
     */
    public static String getValue(String key) {
        return PropertiesComponent.getInstance().isValueSet(key)
                ? PropertiesComponent.getInstance().getValue(key)
                : null;
    }
}
