package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import compontent.QueryListener;
import constant.Constant;
import service.AlertService;
import service.ScheduledService;
import service.impl.ScheduledServiceImpl;
import ui.SettingDialog;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class SettingAction extends AnAction {
    /**
     * 判断是否启动了
     */
    public static boolean isStart = false;

    @Override
    public void actionPerformed(AnActionEvent e) {
        //拿到项目上下文
        final Project p = e.getProject();
        //创建设置窗口
        SettingDialog settingDialog = new SettingDialog(p, Constant.Settings.SETTING_WINDOW_TITLE
                , Constant.Settings.SETTING_TIME_SELECTOR_TEXT
                , Constant.Settings.TIME_SELECT_ARRAY, Constant.Settings.SETTING_LOCK_WINDOWS,Constant.Settings.SETTING_MOUSE_CHECK,
                new InputValidator() {
                    @Override
                    public boolean checkInput(String s) {
                        try {
                            long minute = Long.parseLong(s);
                            //最大值为60分钟
                            return minute > 0 && minute <= 60;
                        } catch (Exception e) {
                            //输入异常
                        }
                        return false;
                    }

                    @Override
                    public boolean canClose(String s) {
                        return false;
                    }
                }).createSettingDialog();
        if (!settingDialog.isOK()) {
            return;
        }

        try {
            int period = Integer.parseInt(settingDialog.getTimeValue());
            ScheduledService.getInstance().addTask(period * 60 * 1000, e1 -> ProgressManager.getInstance()
                    .executeNonCancelableSection(
                            () -> AlertService.getInstance().showAlertDialog(p, period, settingDialog.isLock())));
            //开始运行
            ScheduledService.getInstance().start();
        } catch (Exception ex) {
            //exception
            ex.printStackTrace();
        }
        isStart = true;

        if (settingDialog.isMouth()) {
            EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
            eventMulticaster.addEditorMouseMotionListener(QueryListener.getInstance());
            ScheduledExecutorService service =  ScheduledServiceImpl.getInstance().getService();
            AtomicInteger count = new AtomicInteger();
            QueryListener listener = QueryListener.getInstance();
            listener.setProject(p);
            service.scheduleAtFixedRate(() -> {
                if (listener.flag.get()) {
                    count.set(0);
                    listener.flag.set(false);
                } else {
                    count.getAndIncrement();
                    if (count.get() > 10) {
                        ScheduledServiceImpl.getInstance().removeTask();
                    }
                }
            }, 5, 60, TimeUnit.SECONDS);

        }
    }
}
