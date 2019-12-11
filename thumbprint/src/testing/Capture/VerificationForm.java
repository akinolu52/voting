package testing.Capture;

import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.verification.*;
import java.awt.*;
import java.io.IOException;

import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerificationForm extends CaptureForm {

    DbConnect db;
    private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();

    VerificationForm(Frame owner) {
        super(owner);
        db = new DbConnect();
    }

    @Override
    protected void init() {
        //    System.out.println("type " + Main.type);
        super.init();
        setTitle("Verification");
        //    updateStatus(0);
    }
    int one = 0;
    public int whr;
    DPFPVerificationResult result;
    DPFPVerificationResult result2;

    @Override
    protected void process(DPFPSample sample) {
        super.process(sample);
        try {
            byte[] dby;
            DPFPFeatureSet features = null;
            DPFPFeatureSet features2 = null;
            String SQLCommand2 = "select id,matric,fingerprint from users";
            db.rs = db.st.executeQuery(SQLCommand2);

            while (db.rs.next()) {

                try (InputStream stream = db.rs.getBinaryStream(2);) {
                    dby = new byte[stream.available()];
                    stream.read(dby);
                }
                t = DPFPGlobal.getTemplateFactory().createTemplate();
                t.deserialize(dby);
                setTemplate(t);
                features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);
// Check quality of the sample and start verification if it's good
                if (features != null) {
                    // Compare the feature set with our template
                    DPFPTemplate ms = getTemplate();
                    // JOptionPane.showMessageDialog(rootPane, Main.cardno);
                    result = verificator.verify(features, ms);
                    //  System.out.println("na d result b dix " + result.getFalseAcceptRate());

                    setPrompt("VERIFICATION SUCCESSFUL");
                    one = 0;
                    log.setText("");
                    var.setVoterID(db.rs.getString("matric"));
                    makeReport(db.rs.getString("matric"));
                    System.out.println("id " + db.rs.getString(1));
                    System.out.println("matric " + db.rs.getBinaryStream(2));
                    System.out.println("finger " + db.rs.getBinaryStream(3));
                     ReadWriteFile rw = new ReadWriteFile();
                    rw.write("finger.txt", db.rs.getString(1));
                    setVisible(false);
                    break;    //}
                }

            }
            // db.con.close();
        } catch (HeadlessException | IOException | IllegalArgumentException | SQLException v) {
            System.err.println("ok na verification error " + v);
        }
        // Process the sample and create a feature set for the enrollment purpose.

    }
    public static String TEMPLATE_PROPERTY = "template";
    public static DPFPTemplate template;

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    public static DPFPTemplate t;
    public static DPFPTemplate t2;

    public static void main(String[] args) {
        Variables var = new Variables();
        var.setVoterID("");
        VerificationForm f = new VerificationForm(new javax.swing.JFrame());
        f.whr = 0;
        f.setVisible(true);
        System.exit(EXIT_ON_CLOSE);
    }
}
