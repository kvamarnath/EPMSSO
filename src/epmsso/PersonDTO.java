package epmsso;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import java.util.List;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

//	private Log logger = LogFactory.getLog(this.getClass());
    /**
     * status holds status of the user logged in.
     */
    private String status;
    /**
     * personId holds personId of the user logged in.
     */
    private Long personId;
    /**
     * firstName holds firstName of the user logged in.
     */
    private String firstName;
    /**
     * lastName holds lastName of the user logged in.
     */
    private String lastName;
    /**
     * personType holds personType of the user logged in.
     */
    private String personType;
    /**
     * employeeId holds the employee Id of the user logged in.
     */
    private String employeeId;
    /**
     * nickName contains nickName of the person.
     */
    private String nickName;
    /**
     * divisionNum contains divisionNum of the person belongs to.
     */
    private String divisionNum;
    /**
     * departmentNum contains departmentNum of the person.
     */
    private String departmentNum;
    /**
     * isManager holds true or false depends on the user.
     */
    private String isManager;
    /**
     * emailAddress holds the email address of person.
     */
    private String emailAddress;
    /**
     * allGroups contains list of all groups, person belongs to.
     */
    private List<Long> allGroups;
    /**
     * sessionId contains sessionId of the corresponding person.
     */
    private String sessionId;

    /* to capture the ds authentication exceptions */
    private String reason;

    /**
     * @return the serialVersionUID
     */
    public static final long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @return the allGroups
     */
    public final List<Long> getAllGroups() {
        return allGroups;
    }

    /**
     * @param allGroupsVal the allGroups to set
     */
    public final void setAllGroups(List<Long> allGroupsVal) {
        this.allGroups = allGroupsVal;
    }

    /**
     * @return the departmentNum
     */
    public final String getDepartmentNum() {
        return departmentNum;
    }

    /**
     * @param departmentNumVal the departmentNum to set
     */
    public final void setDepartmentNum(String departmentNumVal) {
        this.departmentNum = departmentNumVal;
    }

    /**
     * @return the divisionNum
     */
    public final String getDivisionNum() {
        return divisionNum;
    }

    /**
     * @param divisionNumVal the divisionNum to set
     */
    public final void setDivisionNum(String divisionNumVal) {
        this.divisionNum = divisionNumVal;
    }

    /**
     * @return the emailAddress
     */
    public final String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddressVal the emailAddress to set
     */
    public final void setEmailAddress(String emailAddressVal) {
        this.emailAddress = emailAddressVal;
    }

    /**
     * @return the employeeId
     */
    public final String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeIdVal the employeeId to set
     */
    public final void setEmployeeId(String employeeIdVal) {
        this.employeeId = employeeIdVal;
    }

    /**
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }

    /**
     * @param firstNameVal the firstName to set
     */
    public final void setFirstName(String firstNameVal) {
        this.firstName = firstNameVal;
    }

    /**
     * @return the isManager
     */
    public final String getIsManager() {
        return isManager;
    }

    /**
     * @param isManagerVal the isManager to set
     */
    public final void setIsManager(String isManagerVal) {
        this.isManager = isManagerVal;
    }

    /**
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }

    /**
     * @param lastNameVal the lastName to set
     */
    public final void setLastName(String lastNameVal) {
        this.lastName = lastNameVal;
    }

    /**
     * @return the nickName
     */
    public final String getNickName() {
        return nickName;
    }

    /**
     * @param nickNameVal the nickName to set
     */
    public final void setNickName(String nickNameVal) {
        this.nickName = nickNameVal;
    }

    /**
     * @return the personId
     */
    public final Long getPersonId() {
        return personId;
    }

    /**
     * @param personIdVal the personId to set
     */
    public final void setPersonId(Long personIdVal) {
        this.personId = personIdVal;
    }

    /**
     * @return the personType
     */
    public final String getPersonType() {
        return personType;
    }

    /**
     * @param personTypeVal the personType to set
     */
    public final void setPersonType(String personTypeVal) {
        this.personType = personTypeVal;
    }

    /**
     * @return the sessionId
     */
    public final String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionIdVal the sessionId to set
     */
    public final void setSessionId(String sessionIdVal) {
        this.sessionId = sessionIdVal;
    }

    /**
     * @return the status
     */
    public final String getStatus() {
        return status;
    }

    /**
     * @param statusVal the status to set
     */
    public final void setStatus(String statusVal) {
        this.status = statusVal;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the status to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
