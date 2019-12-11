package testing.Capture;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.processing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EnrollmentForm extends CaptureFormEnrolling {

    private DPFPEnrollment enroller = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    public static byte print[];
    public static byte print2[];
    Variables var;

    public EnrollmentForm(Frame owner) {
        super(owner);
        var = new Variables();
    }

    @Override
    protected void init() {
        super.init();
        setTitle("Examiner Print Capturer");
        updateStatus();
    }
    // UserReg m = new UserReg();

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);
        // Process the sample and create a feature set for the enrollment purpose.
        DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
        // Check quality of the sample and add to enroller if it's good
        if (features != null) {
            try {
                makeReport("Student Print Capture Completed.");
                //makeReport("The fingerprint feature set was created.");
                enroller.addFeatures(features);		// Add feature set to template.
            } catch (DPFPImageQualityException ex) {
                //System.out.println("i hia u " + ex);
            } finally {
                updateStatus();
                // Check if template has been created.
                switch (enroller.getTemplateStatus()) {
                    case TEMPLATE_STATUS_READY:	// report success and stop capturing
                        stop();
                        setTemplate(enroller.getTemplate());
                        print = new byte[getTemplate().serialize().length];
                        print = getTemplate().serialize();
                        System.err.println("This is ur thumb print bytes <><<<???? " + print);
                        ReadWriteFile rw = new ReadWriteFile();
                        try {
                            rw.write("finger.txt", print.toString());
                         
                        } catch (IOException ex) {
                            Logger.getLogger(EnrollmentForm.class.getName()).log(Level.SEVERE, null, ex);
                        }


                        setPrompt("Check for Verification");
                        break;

                    case TEMPLATE_STATUS_FAILED:	// report failure and restart capturing
                        enroller.clear();
                        stop();
                        updateStatus();
                        setTemplate(null);
                        // JOptionPane.showMessageDialog(EnrollmentForm.this, "The fingerprint template is not valid. Repeat fingerprint enrollment.", "Fingerprint Enrollment", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(EnrollmentForm.this, "This is not a valid Finger Print.", "Fingerprint Report", JOptionPane.ERROR_MESSAGE);
                        start();
                        break;
                }
            }
        }
    }

    private void updateStatus() {
        // Show number of samples needed.
        //System.out.println("seeeee "+enroller.getFeaturesNeeded());
        setStatus(String.format("This process will be repeated %1$s times", enroller.getFeaturesNeeded()));
        if (enroller.getFeaturesNeeded() == 0) {
            setVisible(false);
        }
        //    setStatus(String.format("Fingerprint samples needed: %1$s", enroller.getFeaturesNeeded()));
    }

    public static void main(String[] args) {
        Variables var = new Variables();
        var.setVoterID("");
        EnrollmentForm f = new EnrollmentForm(new javax.swing.JFrame());
        f.setVisible(true);
      System.exit(EXIT_ON_CLOSE);
    }
    public static String TEMPLATE_PROPERTY = "template";
    public static DPFPTemplate template;
    public static String cardno, company;

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
}
