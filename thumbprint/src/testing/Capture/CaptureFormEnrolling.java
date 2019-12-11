package testing.Capture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.*;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.*;
import java.net.URL;

public class CaptureFormEnrolling
        extends JDialog {

    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    private JLabel picture = new JLabel();
    private JTextField prompt = new JTextField();
    private JTextArea log = new JTextArea();
    private JTextField status = new JTextField("");
    Image im;
     Variables var;

    public CaptureFormEnrolling(Frame owner) {
       super(owner, true);
         var= new Variables();
        setTitle("Student Finger Print Capturer");
//            URL url = this.getClass().getClassLoader().getResource("ThInc.png");
//            Image im = Toolkit.getDefaultToolkit().getImage(url);
//            setIconImage(im);
        log.setLineWrap(true);

        setLayout(new BorderLayout());
        rootPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        picture.setPreferredSize(new Dimension(120, 140));
        picture.setBorder(BorderFactory.createLoweredBevelBorder());
        prompt.setFont(UIManager.getFont("Panel.font"));
        prompt.setEditable(false);
        prompt.setColumns(25);
        prompt.setMaximumSize(prompt.getPreferredSize());
        prompt.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Prompt:"),
                        BorderFactory.createLoweredBevelBorder()
                ));
        log.setColumns(25);
        log.setEditable(false);
        log.setFont(UIManager.getFont("Panel.font"));
        JScrollPane logpane = new JScrollPane(log);
        logpane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), "Status:"),
                        BorderFactory.createLoweredBevelBorder()
                ));

        status.setEditable(false);
        status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        status.setFont(UIManager.getFont("Panel.font"));
  //      JButton Refresh = new JButton("Refresh");
////        Refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Actions-view-refresh-icon.png")));
//        Refresh.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                log.setText("Fingerprint scanner was reset...");
//                status.setText("SCAN employee RIGHT THUMB!!!!!");
//                if(var.getFing()==1){
//                    prompt.setText("Scan the Voters RIGHT THUMB now.");
//                }else{
//                    prompt.setText("Scan the Voters RIGHT Index Finger now.");
//                }
//                picture.setIcon(null);
//            }
//        });
        JButton quit = new JButton("         Quit           ");
//        quit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Apps-session-logout-icon.png")));

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.getColor("control"));
        right.add(prompt, BorderLayout.PAGE_START);
        right.add(logpane, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.getColor("control"));
        center.add(right, BorderLayout.CENTER);
        center.add(picture, BorderLayout.EAST);
        center.add(status, BorderLayout.PAGE_END);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottom.setBackground(Color.getColor("control"));
        bottom.add(quit);

        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                init();
                start();
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                stop();
            }

        });

        pack();
        setLocationRelativeTo(null);
    }

    protected void init() {
        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        makeReport("Sample Captured.");
                        setPrompt("More Sample Required.");
                        process(e.getSample());
                    }
                });
            }
        });
        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        makeReport("Finger Print Scanner Decteced.");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        makeReport("Finger Print Scanner not Found.");
                    }
                });
            }
        });
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        makeReport("And Attempt to Scan was made.");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        makeReport("Scanning Process not Completed Successful.");
                    }
                });
            }
        });
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                            makeReport("The quality of the fingerprint sample is good.");
                        } else {
                            makeReport("The quality of the fingerprint sample is poor.");
                        }
                    }
                });
            }
        });
    }

    protected void process(DPFPSample sample) {
        // Draw fingerprint sample image.
        drawPicture(convertSampleToBitmap(sample));
    }

    protected void start() {
       // capturer.stopCapture();
       // if(var.getThreadStarted()==0){
          var.setThreadStarted(1);  
         capturer.startCapture();
      //  }
       
         if(var.getFing()==1){
                    prompt.setText("Scan the Student THUMB here.");
                }else{
                    prompt.setText("Scan the Student Index Finger here.");
                }
      //  setPrompt("Using the fingerprint reader, scan the employee RIGHT THUMB only.");
    }

    protected void stop() {
        capturer.stopCapture();
    }

    public void setStatus(String string) {
        status.setText(string);
    }

    public void setPrompt(String string) {
        prompt.setText(string);
    }

    public void makeReport(String string) {
        if (string.equals("Scanning Process not Completed Successful.\n")) {
            // log.append("\n" + string + "\n");
        } else {
            log.setText(string + "\n");
        }
    }

    public void drawPicture(Image image) {
        picture.setIcon(new ImageIcon(
                image.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_DEFAULT)));
    }

    protected Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    public static void main(String[] args) {
         Variables var= new Variables();
         var.setVoterID("");
        CaptureFormEnrolling f = new CaptureFormEnrolling(new javax.swing.JFrame());
        f.setVisible(true);
     }
}
