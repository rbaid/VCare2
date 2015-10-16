package com.dirtybit.model;
public class EmpInfo {

    private Integer EmpId;
    private String EmpMailID;
    private String EmpName;
    private String Department;
    private String Designation;

    /**
     *
     * @return
     * The EmpId
     */
    public Integer getEmpId() {
        return EmpId;
    }

    /**
     *
     * @param EmpId
     * The EmpId
     */
    public void setEmpId(Integer EmpId) {
        this.EmpId = EmpId;
    }

    /**
     *
     * @return
     * The EmpMailID
     */
    public String getEmpMailID() {
        return EmpMailID;
    }

    /**
     *
     * @param EmpMailID
     * The EmpMailID
     */
    public void setEmpMailID(String EmpMailID) {
        this.EmpMailID = EmpMailID;
    }

    /**
     *
     * @return
     * The EmpName
     */
    public String getEmpName() {
        return EmpName;
    }

    /**
     *
     * @param EmpName
     * The EmpName
     */
    public void setEmpName(String EmpName) {
        this.EmpName = EmpName;
    }

    /**
     *
     * @return
     * The Department
     */
    public String getDepartment() {
        return Department;
    }

    /**
     *
     * @param Department
     * The Department
     */
    public void setDepartment(String Department) {
        this.Department = Department;
    }

    /**
     *
     * @return
     * The Designation
     */
    public String getDesignation() {
        return Designation;
    }

    /**
     *
     * @param Designation
     * The Designation
     */
    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

}
