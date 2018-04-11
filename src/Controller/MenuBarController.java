/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Core.Controller;
import Entity.Notification;
import IService.INotificationService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToolbar;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author hero
 */
public class MenuBarController extends Controller implements Initializable {

    private final INotificationService notificationService = this.getService().getNotificationService();
    @FXML
    private JFXButton messageNotifButton;
    @FXML
    private JFXToolbar toolBar;
    @FXML
    private Label messageNotifLabel;
    @FXML
    private JFXButton demandeNotifButton;
    @FXML
    private Label demandeNotifLabel;
    private int lastNotificationId = Integer.MAX_VALUE;
    private Timer timer;
    private List<Notification> notifications;
    private int messageNotifCount;
    private int demandeNotifCount;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initTimer();
        //Controller.getNotificationController().refreshUnseenNotifications(notificationService.getUnseenNotifications(this.getUser()));
    }

    private void refreshCounts()
    {
        notifications = notificationService.getUnseenNotifications(this.getUser());
        if(!notifications.isEmpty())
        {
            if(notifications.get(0).getId() > lastNotificationId)
            {
                Notifications.create().text("Il y'a des nouveaux notifications  !!!").position(Pos.CENTER).hideAfter(Duration.seconds(3)).showConfirm();
                Controller.getNotificationController().refreshUnseenNotifications(notifications);
            }
            lastNotificationId = notifications.get(0).getId();
        }
        messageNotifCount = 0;
        demandeNotifCount = 0;
        for(Notification notification:notifications)
        {
            switch(notification.getSubject())
            {
                case"Accept":break;
                case"Demande":demandeNotifCount++;break;
                case"Message":messageNotifCount++;break;
            }
        }
        messageNotifLabel.setText(String.valueOf(messageNotifCount));
        demandeNotifLabel.setText(String.valueOf(demandeNotifCount));
        System.gc();
    }
    
    private void initTimer()
    {
        this.timer = new Timer(true);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    refreshCounts();
                });
            }
        };
        timer.schedule(timerTask,0,5000);
    }
    
    @FXML
    private void messageNotification(ActionEvent event) {
        Controller.getNotificationController().showWindow("Message");
    }

    @FXML
    private void demandeNotification(ActionEvent event) {
        Controller.getNotificationController().showWindow("Demande");
    }
    
}
