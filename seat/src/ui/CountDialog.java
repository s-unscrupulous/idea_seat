package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CountDialog extends DialogWrapper {

    /**
     * 倒计时线程池
     */
    private ScheduledThreadPoolExecutor scheduled;

    private long time;

    protected CountDialog(@Nullable Project project, String title, long time) {
        super(project);
        scheduled = new ScheduledThreadPoolExecutor(2);
        this.time = time;
        init();
        setTitle(title);
    }


    @Override
    protected @Nullable
    JComponent createCenterPanel() {
        long gap = System.currentTimeMillis() - time;
        long start = System.currentTimeMillis() + Math.max(0, 5 * 60 * 1000 - gap);
        JLabel text = new JLabel();
        text.setFont(new Font("微软雅黑", Font.BOLD, 15));
        scheduled.scheduleAtFixedRate(() -> {
            long time = (start - 1 - System.currentTimeMillis()) / 1000;
            if (time <= 0) {
                text.setText("休息时间结束");
                this.dispose();
                return;
            }
            long hour = time / 3600;
            long minute = (time - hour * 3600) / 60;
            long seconds = time - hour * 3600 - minute * 60;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><br>距离休息结束还有<br>")
                    .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;").append(minute).append("分 ").append(seconds).append("秒 ")
                    .append("</html>");

            text.setText(stringBuilder.toString());

        }, 0, 1, TimeUnit.SECONDS);
        return text;
    }

    @Override
    public void show() {
        this.setSize(300,90);
        super.show();
    }
}
