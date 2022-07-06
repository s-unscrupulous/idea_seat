package service;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;


public interface AlertService {
    /**
     * 显示弹窗
     *  @param project     当前项目上下文

     * @param timeMinutes 时间（分钟）
     * @param isLock
     */
    void showAlertDialog(Project project,  int timeMinutes,boolean isLock);

    /**
     * getInstance
     *
     * @return {@link service.impl.AlertServiceImpl}
     */
    static AlertService getInstance() {
        if (ApplicationManager.getApplication() != null) {
            return ServiceManager.getService(AlertService.class);
        } else {
            try {
                return (AlertService) AlertService.class.getClassLoader().loadClass("service.impl.AlertServiceImpl").newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
