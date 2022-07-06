package compontent;

import com.intellij.openapi.components.BaseComponent;;

public class SampleDialogWrapper implements BaseComponent {

    @Override
    public void initComponent() {
        System.out.println("component初始化--------");
    }

}
