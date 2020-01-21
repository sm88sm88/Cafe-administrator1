import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Main {

    JFrame frame, countFrame;
    getServer mGetServer;
    static String text,text2;
    public static void main(String[] args) {


        new Main();
    }

    public Main() {
       mGetServer = new getServer(){
            @Override
            public void LockPc() {
                super.LockPc();
                if(!value.LockedStatus.equals(value.LockedClient))
                makeLockGui();
            }

           @Override
           public void unLockPc() {
               super.unLockPc();
               if(!value.LockedStatus.equals(value.unLockedClient))
               makeUnlock();
           }

           @Override
           public void ShutdownPc() {
               super.ShutdownPc();
               MakeShutDown();
           }

           @Override
           public void UpdateUIText() {
               super.UpdateUIText();
               Jtext.setText(text);
               Jtext2.setText(text2);

           }
       };
       mGetServer.start();
       makeLockGui();
    }

    private void MakeShutDown() {
        String shutdownCmd = "shutdown -s";
        try {
            Process child = Runtime.getRuntime().exec(shutdownCmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    JLabel Jtext,Jtext2;
    private void makeUnlock() {
        value.time = 0;
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        getServer.startCount = true;
        frame.setVisible(false);
        value.LockedStatus = value.unLockedClient;

        JFrame countFrame;
        countFrame = new JFrame();
        Jtext = new JLabel();
        Jtext2 = new JLabel();
        Jtext.setBounds(10,10,400,30);
        Jtext2.setBounds(10,40,400,30);
        countFrame.add(Jtext);
        countFrame.add(Jtext2);
        countFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        countFrame.setSize(250, 150);
        countFrame.setLocation(100, 150);
        countFrame.setLayout(null);
        countFrame.setVisible(true);

    }

    private void makeLockGui() {
        getServer.startCount = false;
        frame = new JFrame();
//        JButton btn = new JButton();
//
//        btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                frame.setVisible(   false);
//            }
//        });
//        frame.add(btn);
//

        frame.add(new JLabel(new ImageIcon("assert/lock.png")));


        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent arg0) {
                frame__windowStateChanged(arg0);
            }
        });
        frame.setAlwaysOnTop(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
        value.LockedStatus = value.LockedClient;
    }


    public void frame__windowStateChanged(WindowEvent e){
        // minimized
        if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED){
            UserShouldStoped();
        }
    }

    private void UserShouldStoped()
    {
        frame.setVisible(false);
        makeLockGui();
    }
}