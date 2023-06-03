package action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.EditorEventMulticaster;
import com.intellij.openapi.ui.Messages;
import compontent.QueryListener;
import constant.Constant;
import service.ScheduledService;
import service.impl.ScheduledServiceImpl;


public class StopAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        boolean flag = false;
        if (!ScheduledService.getInstance().isEmptyTaskTimer()) {
            ScheduledService.getInstance().removeTask();
            flag = true;
        }
        SettingAction.isStart = false;
        close();
        Messages.showDialog(flag ? Constant.Stop.SUCCESS_TEXT : Constant.Stop.FAIL_TEXT, Constant.Stop.TITLE,
                new String[]{"确定"}, 0,
                Messages.getInformationIcon());
    }

    private void close(){
        //关闭鼠标监听
        EditorEventMulticaster eventMulticaster = EditorFactory.getInstance().getEventMulticaster();
        eventMulticaster.removeEditorMouseMotionListener(QueryListener.getInstance());
        //关闭记时间线程
        if (ScheduledServiceImpl.getInstance().getService() != null) {
            ScheduledServiceImpl.getInstance().getService().shutdown();
        }
    }
}
