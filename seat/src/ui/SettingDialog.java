package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.*;
import compontent.StorageComponent;
import constant.Constant;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SettingDialog extends DialogWrapper {

    private boolean isLock,isMouth;
    private String timeValue = "";
    /**
     * 项目
     */
    private Project project;

    /**
     * 窗内文字
     */
    private String lockText, timeText, mouthText;

    /**
     * 时间备选值
     */
    private String[] timeSelectedValues;
    /**
     * 锁屏备选值
     */
    private String[] lockSelectedValues;

    /**
     * 默认选中时间
     */
    private String defaultTimeValue;

    /**
     * 默认锁屏模式时间
     */
    private String defaultLockValue;


    private InputValidator inputValidator;

    /**
     * 模型和时间选择框
     */
    private ComboBox<String> timeSelector, lockSelector, mouthSelector;

    public SettingDialog(@Nullable Project project, String title, String timeText
            , String[] timeSelectedValues, String lockText, String mouseText
            , InputValidator inputValidator) {
        super(project);
        setTitle(title);
        this.lockText = lockText;
        this.timeText = timeText;
        this.mouthText = mouseText;
        this.lockSelectedValues = initLockSelectValues();
        this.timeSelectedValues = initTimeSelectValues();
        this.defaultTimeValue = readFromStorage(true);
        this.defaultLockValue = readFromStorage(false);
        this.inputValidator = inputValidator;
        this.init();
    }


    public boolean isLock() {
        return isLock;
    }

    public boolean isMouth() {
        return isMouth;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public SettingDialog setProject(Project project) {
        this.project = project;
        return this;
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = this.createIconPanel();

        //选择panel
        JPanel timeSelectorPanel = this.createMessagePanel(this.timeText);
        JPanel lockSelectorPanel = this.createMessagePanel(this.lockText);
        JPanel mouthPanel = this.createMessagePanel(this.mouthText);

        //锁屏
        lockSelector = new ComboBox<>(220);
        lockSelector.setEditable(true);
        lockSelector.setModel(new DefaultComboBoxModel<>(lockSelectedValues));
        lockSelector.getEditor().setItem(this.defaultLockValue);
        lockSelector.setSelectedItem(this.defaultLockValue);

        //时间选择
        timeSelector = new ComboBox<>(220);
        timeSelector.setEditable(true);
        timeSelector.setModel(new DefaultComboBoxModel<>(timeSelectedValues));
        timeSelector.getEditor().setItem(this.defaultTimeValue);
        timeSelector.setSelectedItem(this.defaultTimeValue);

        //选择
        mouthSelector = new ComboBox<>(220);
        mouthSelector.setEditable(true);
        mouthSelector.setModel(new DefaultComboBoxModel<>(lockSelectedValues));
        mouthSelector.getEditor().setItem(this.defaultLockValue);
        mouthSelector.setSelectedItem(this.defaultLockValue);

        timeSelectorPanel.add(timeSelector, "Center");
        lockSelectorPanel.add(lockSelector, "Center");
        mouthPanel.add(mouthSelector, "Center");
        panel.add(timeSelectorPanel, BorderLayout.NORTH);
        panel.add(lockSelectorPanel, BorderLayout.CENTER);
        panel.add(mouthPanel, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * 创建一个带有图标的panel
     *
     * @return 图标panel
     */
    @NotNull
    protected JPanel createIconPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        //JLabel iconLabel = new JLabel(Messages.getQuestionIcon());
        Container container = new Container();
        container.setLayout(new BorderLayout());
        //container.add(iconLabel, "North");
        panel.add(container, "West");
        return panel;
    }

    @NotNull
    protected JPanel createMessagePanel(String text) {
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel textLabel = new JLabel(text);
        textLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        textLabel.setUI(new MultiLineLabelUI());
        messagePanel.add(textLabel, "North");
        return messagePanel;
    }

    /**
     * 获得选择的时间
     *
     * @return 提醒时间
     */
    private List<String> getSelectorValue() {
        ArrayList<String> list = new ArrayList<>();
        Object o = this.timeSelector.getSelectedItem();
        Object lock = this.lockSelector.getSelectedItem();
        Object mouth = this.mouthSelector.getSelectedItem();
        list.add(o != null ? o.toString() : "");
        list.add(lock != null ? lock.toString() : "");
        list.add(mouth != null ? mouth.toString() : "");
        return list;
    }


    /**
     * 创建设置窗口
     *
     * @return 点击 “ok” 按钮后返回所选中的值
     */
    public SettingDialog createSettingDialog() {
        this.show();
        List<String> selectorValue = getSelectorValue();
        saveToStorage(selectorValue.get(0), selectorValue.get(1));
        this.timeValue = selectorValue.get(0);
        this.isLock = selectorValue.get(1).equals("是");
        this.isMouth = selectorValue.get(2).equals("是");
        return this;

    }

    /**
     * 从存储中读取
     *
     * @param isModel time or lock
     * @return 存储的值或默认生成备选的第一项的值
     */
    private String readFromStorage(boolean isModel) {
        if (isModel) {
            return StorageComponent.getValue("lastSelectedMinutes") == null
                    ? isEmpty(timeSelectedValues) ? "" : timeSelectedValues[0]
                    : StorageComponent.getValue("lastSelectedMinutes");
        } else {
            return StorageComponent.getValue("lastSelectedLockMode") == null
                    ? isEmpty(lockSelectedValues) ? "" : lockSelectedValues[0]
                    : StorageComponent.getValue("lastSelectedLockMode");
        }

    }

    /**
     * 存储
     *
     * @param time 选取的时间值
     */
    private void saveToStorage(String time, String isLock) {
        StorageComponent.save("lastSelectedMinutes", time);
        StorageComponent.save("lastSelectedLockMode", isLock);
    }

    /**
     * 判断数组是否为空
     *
     * @param array 数组
     * @return 是/否
     */
    private boolean isEmpty(String[] array) {
        return array == null || array.length == 0;
    }


    /**
     * 初始化时间下拉框选择
     *
     * @return initValues
     */
    private String[] initTimeSelectValues() {
        return Constant.Settings.TIME_SELECT_ARRAY;
    }

    /**
     * 初始化锁屏下拉框选择
     *
     * @return initValues
     */
    private String[] initLockSelectValues() {
        return Constant.Settings.LOCK_SELECT_ARRAY;
    }


}
