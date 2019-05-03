package com.ibt.niramaya.modal.prescription;

public class PTAdvisedModel {

    /*Advised or Given*/
    private String prescriptionTreatmentType;
    /*Image or Text*/
    private String prescriptionContentType;

    private String opdPrescriptionId;
    private String medicineId;
    private String medicineName;
    private String medicineDoes;
    private String opdPrescriptionCreatedDate;

    private String opdPathologyTestId;
    private String pathologyId;
    private String testName;
    private String opdPathologyCreatedDate;

    public String getPrescriptionTreatmentType() {
        return prescriptionTreatmentType;
    }

    public void setPrescriptionTreatmentType(String prescriptionTreatmentType) {
        this.prescriptionTreatmentType = prescriptionTreatmentType;
    }

    public String getPrescriptionContentType() {
        return prescriptionContentType;
    }

    public void setPrescriptionContentType(String prescriptionContentType) {
        this.prescriptionContentType = prescriptionContentType;
    }

    public String getOpdPrescriptionId() {
        return opdPrescriptionId;
    }

    public void setOpdPrescriptionId(String opdPrescriptionId) {
        this.opdPrescriptionId = opdPrescriptionId;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDoes() {
        return medicineDoes;
    }

    public void setMedicineDoes(String medicineDoes) {
        this.medicineDoes = medicineDoes;
    }

    public String getOpdPrescriptionCreatedDate() {
        return opdPrescriptionCreatedDate;
    }

    public void setOpdPrescriptionCreatedDate(String opdPrescriptionCreatedDate) {
        this.opdPrescriptionCreatedDate = opdPrescriptionCreatedDate;
    }

    public String getOpdPathologyTestId() {
        return opdPathologyTestId;
    }

    public void setOpdPathologyTestId(String opdPathologyTestId) {
        this.opdPathologyTestId = opdPathologyTestId;
    }

    public String getPathologyId() {
        return pathologyId;
    }

    public void setPathologyId(String pathologyId) {
        this.pathologyId = pathologyId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getOpdPathologyCreatedDate() {
        return opdPathologyCreatedDate;
    }

    public void setOpdPathologyCreatedDate(String opdPathologyCreatedDate) {
        this.opdPathologyCreatedDate = opdPathologyCreatedDate;
    }
}
