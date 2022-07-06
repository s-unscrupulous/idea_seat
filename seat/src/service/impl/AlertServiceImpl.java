package service.impl;

import com.intellij.notification.*;
import com.intellij.openapi.project.Project;
import compontent.MessageBuilder;
import constant.Constant;
import service.AlertService;
import ui.AlertDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class AlertServiceImpl implements AlertService {

    private long time;

    private final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Groovy DSL errors", NotificationDisplayType.BALLOON, true);

    private final Notification notification = NOTIFICATION_GROUP.createNotification("温馨提醒", "",
            NotificationType.INFORMATION, NotificationListener.URL_OPENING_LISTENER);

    /**
     * 显示弹窗
     *
     * @param project     当前项目上下文
     * @param timeMinutes 时间（分钟）
     * @param isLock
     */
    @Override
    public void showAlertDialog(Project project, int timeMinutes, boolean isLock) {
        if (time == 0) {
            time = System.currentTimeMillis();
        }
        Random random = new Random();
        notification.setContent(getSubtitle(time, System.currentTimeMillis()));
        notification.notify(project);
        AlertDialog alertDialog = new AlertDialog(project,
                Constant.Infor.TITLE,
                new MessageBuilder().
                        setBody(Constant.Infor.HARM[random.nextInt(Constant.Infor.HARM.length)]).
                        setVariables(timeMinutes).build(), isLock);
        alertDialog.show();
    }

    /**
     * 时间计算
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public String getSubtitle(long start, long end) {
        String res = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date parse = format.parse(format.format(new Date(start)));
            Date date = format.parse(format.format(new Date(end)));
            long between = date.getTime() - parse.getTime();
            long day = between / (24 * 60 * 60 * 1000);
            long hour = (between / (60 * 60 * 1000) - day * 24);
            long min = ((between / (60 * 1000)) - day * 24 * 60 - hour * 60);
            res = String.format("你已经累计工作 %d 天 %d 小时 %d 分", day, hour, min);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
