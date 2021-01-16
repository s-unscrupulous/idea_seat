package compontent;

import action.SettingAction;
import com.intellij.openapi.editor.event.*;
import constant.Constant;
import org.jetbrains.annotations.NotNull;
import service.ScheduledService;


public class QueryListener implements EditorMouseMotionListener {
    private long beforeMouse = Long.MAX_VALUE;

    public static boolean flag = false;

    @Override
    public void mouseMoved(@NotNull EditorMouseEvent e) {
        long now = System.currentTimeMillis();
        flag = true;
        if (SettingAction.isStart && now - beforeMouse > Constant.Infor.STOP_TIME && !ScheduledService.getInstance().isTaskRunning()) {
            ScheduledService.getInstance().addTask(0, true);
        }
        beforeMouse = now;
    }
}
