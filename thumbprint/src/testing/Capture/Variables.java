package testing.Capture;

/**
 *
 * @author OGINNI
 */
public class Variables {

    public static String location;
    public static String username;
    public static String sysId;
    public static String state;
    public static String locGvt;
    private static String phone;
    private static String voterID;
    private static String pixPath;
    public static long agentId;
    public static int status;
    public static int fing;
    public static int exist;
    public static int threadStarted;

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param aPhone the phone to set
     */
    public void setPhone(String aPhone) {
        phone = aPhone;
    }

    /**
     * @return the phone
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }
    
    
    public String getVoterID() {
        return voterID;
    }
    /**
     * @param voterID the voterID to set
     */
    public void setVoterID(String voterID) {
        this.voterID = voterID;
    }

    /**
     * @return the password
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * @param password the password to set
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     * @return the id
     */
    public long getAgentId() {
        //getStatus();
        return agentId;
    }

    /**
     * @param id the id to set
     */
    public void setAgentId(long agentId) {
        this.agentId = agentId;
    }
   

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the localGovt
     */
    public String getLocGvt() {
        return locGvt;
    }

    /**
     * @param localGovt the localGovt to set
     */
    public void setLocGvt(String locGvt) {
        this.locGvt = locGvt;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @return the exist
     */
    public int getExist() {
        return exist;
    }

    /**
     * @param status the exist to set
     */
    public void setExist(int exist) {
        this.exist = exist;
    }
    /**
     * @return the threadStarted
     */
    public int getThreadStarted() {
        return threadStarted;
    }

    /**
     * @param status the threadStarted to set
     */
    public void setThreadStarted(int threadStarted) {
        this.threadStarted = threadStarted;
    }
    /**
     * @return the exist
     */
    public int getFing() {
        return fing;
    }

    /**
     * @param status the fing to set
     */
    public void setFing(int fing) {
        this.fing = fing;
    }
    
    public String getPixPath() {
        return pixPath;
    }

    /**
     * @param pixPath the pixPath to set
     */
    public void setPixPath(String pixPath) {
        this.pixPath = pixPath;
    }
}
