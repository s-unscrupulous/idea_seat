package compontent;

import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;

public class SampleDialogWrapper implements BaseComponent {

    private QueryListener queryListener;

    @Override
    public void initComponent() {
        queryListener = new QueryListener();
        System.out.println("component初始化--------");
        EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
        eventMulticaster.addEditorMouseMotionListener(queryListener);
    }

}
