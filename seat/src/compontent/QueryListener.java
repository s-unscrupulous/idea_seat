package compontent;

import action.SettingAction;
import com.intellij.notification.*;
import com.intellij.openapi.editor.event.*;
import com.intellij.openapi.project.Project;
import constant.Constant;
import org.jetbrains.annotations.NotNull;
import service.ScheduledService;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class QueryListener implements EditorMouseMotionListener {
    private long beforeMouse = Long.MAX_VALUE;
    private Project project;

    private final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Groovy DSL errors", NotificationDisplayType.BALLOON, true);

    private final Notification notification = NOTIFICATION_GROUP.createNotification("温馨提醒", "",
            NotificationType.INFORMATION, NotificationListener.URL_OPENING_LISTENER);

    public AtomicBoolean flag = new AtomicBoolean(false);

    private static QueryListener queryListener;

    private QueryListener() {
    }

    public static synchronized QueryListener getInstance() {
        if (queryListener == null) {
            queryListener = new QueryListener();
        }
        return queryListener;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public void mouseMoved(@NotNull EditorMouseEvent e) {
        long now = System.currentTimeMillis();
        flag.set(true);
        //任务启动且5分钟没有移动鼠标且没有任务运行
        if (SettingAction.isStart && now - beforeMouse > Constant.Infor.STOP_TIME &&
                !ScheduledService.getInstance().isTaskRunning()) {
            ScheduledService.getInstance().addTask(0, true);
            notification.setContent("休息小助手已重新开始");
            notification.notify(project);
        }
        beforeMouse = now;
    }
}
