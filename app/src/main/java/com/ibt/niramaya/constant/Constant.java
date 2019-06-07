package com.ibt.niramaya.constant;

/**
 * Created by Dell on 12/3/2018.
 */

public class Constant {
    public static final String BASE_URL = "http://niramaya.infobitetechnology.tech/api/";
    public static final String USER_LOGIN = "patient/user-login.php";
    public static final String OTP_VERIFICATION = "patient/user-contact-verification.php";
    public static final String CREATE_PATIENT_PROFILE = "patient/create_patient_profile.php";
    public static final String PATIENT_LIST = "patient/select-patient-list.php";
    public static final String PATIENT_PRESCRIPTION_LIST = "patient/select_patient_preception.php";
    public static final String PATIENT_PRESCRIPTION_DETAIL = "patient/select_patient_preception_details.php";
    public static final String HOSPITAL_LIST = "patient/select-hospital-list.php";
    public static final String UPDATE_PATIENNT_PROFILE = "patient/update_patient_profile.php";
    public static final String PHARMACY_INVOICE_LIST = "patient/select_pharmacy_bill.php";
    public static final String PATHOLOGY_INVOICE_LIST = "patient/select_pathology_bill.php";
    public static final String OPD_INVOICE_LIST = "patient/select_opd_bill.php";
    public static final String PATIENT_FINANCE_LIST = "patient/select-patient-finance.php";
    public static final String PATIENT_TOKEN = "patient/select_appointment_token.php";
    public static final String DOCTOR_OPD_LIST = "patient/select-hospital-doctor-opd-list.php";
    public static final String DOCTOR_OPD = "patient/select-hospital-doctor-opd.php";
    public static final String SPECIALIST_DOCTOR_OPD = "patient/select-doctor-specialization-opd-list.php";
    public static final String HOSPITAL_SPECIALIST_DOCTOR_OPD = "patient/select-hospital-doctor-specialization-opd-list.php";
    public static final String BOOK_APPOINTMENT = "patient/create-apointment.php";
    public static final String ADD_PATIENT_FINANCE_REPORT = "patient/add-patient-finance.php";


    // Fragment constant
    public static final String HomeFragment = "Home_Fragment";
    public static final String PrescriptionFragment = "PrescriptionFragment";
    public static final String SignUpFragment = "SignUp_Fragment";
    public static final String Otp_Fragment = "OtpFragment";
    public static final String LoginFragment = "LoginFragment";
    public static final String Verification_Fragment = "Verification_Fragment";
    public static final String ReportsFragment = "ReportsFragment";
    public static final String InvoiceFragment = "InvoiceFragment";
    public static final String PatientFinanceDetailFragment = "PatientFinanceDetailFragment";
    public static final String BedFragment = "BedFragment";
    public static final String HistoryFragment = "HistoryFragment";
    public static final String BloodDonationFragment = "BloodDonationFragment";
    public static final String DocumentsFragment = "DocumentsFragment";
    public static final String SettingsFragment = "SettingsFragment";
    public static final String AddUserFragment = "PatientFragment";

    public static final String FacilitiesFragment = "FacilitiesFragment";
    public static final String DepartmentsFragment = "DepartmentsFragment";
    public static final String DoctorFragment = "DoctorFragment";

    // Preference
    public static final String Is_Login = "Is_Login";
    public static final String USER_CONTACT = "userContact";
    public static final String USER_ID = "userId";

    public static final String CURRENT_PATENT_ID = "currentPatientId";
    public static final String CURRENT_PATENT_NAME = "currentPatientName";

    public static final String PATIENT_ID = "patient_id";

    public static final String IS_PRIVACY_POLICY_ACCEPTED = "policy";

    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    public static final String SHARED_PREF = "noty_firebase";

    public static final String FIREBASE_TOKEN = "firebaseToke";
}
