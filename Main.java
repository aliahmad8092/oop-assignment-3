import java.util.*;

// Base class
class User {
    private String id;
    private String name;
    private String role;

    public User(String id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
}

// Patient subclass
class Patient extends User {
    private List<HealthStatus> Healths;

    public Patient(String id, String name) {
        super(id, name, "Patient");
        this.Healths = new ArrayList<>();
    }

    public void addHealth(HealthStatus health) {
        Healths.add(health);
    }

    public List<HealthStatus> getHealths() { return Healths; }
}

// Doctor subclass
class Doctor extends User {
    private List<Patient> patients;

    public Doctor(String id, String name) {
        super(id, name, "Doctor");
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public List<Patient> getPatients() { return patients; }
}

// Admin subclass
class Admin extends User {
    public Admin(String id, String name) {
        super(id, name, "Admin");
    }
}

// Health status class
class HealthStatus {
    private String type;
    private double value;

    public HealthStatus(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public String getType() { return type; }
    public double getValue() { return value; }
}

// Appointment class
class Appointment {
    private String patientId;
    private String doctorId;
    private String date;

    public Appointment(String patientId, String doctorId, String date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    public String getDetails() {
        return "Appointment - Patient: " + patientId + ", Doctor: " + doctorId + ", Date: " + date;
    }
}

// Appointment manager class
class AppointmentManager {
    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Appointment> getAppointments() { return appointments; }
}

// Feedback class
class Feedback {
    private String patientId;
    private String doctorId;
    private String comment;

    public Feedback(String patientId, String doctorId, String comment) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.comment = comment;
    }

    public String getFeedback() {
        return "Feedback from Doctor " + doctorId + " to Patient " + patientId + ": " + comment;
    }
}

// Interface for Notification
interface Notifiable {
    void sendNotification(String message);
}

// Email notification class
class EmailNotification implements Notifiable {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// SMS notification class
class SmsNotification implements Notifiable {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// Notification service class
class NotificationService {
    private Notifiable emailNotifier;
    private Notifiable smsNotifier;

    public NotificationService(Notifiable emailNotifier, Notifiable smsNotifier) {
        this.emailNotifier = emailNotifier;
        this.smsNotifier = smsNotifier;
    }

    public void sendEmail(String message) {
        emailNotifier.sendNotification(message);
    }

    public void sendSms(String message) {
        smsNotifier.sendNotification(message);
    }
}

// Emergency alert class
class EmergencyAlert {
    private NotificationService notificationService;

    public EmergencyAlert(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void checkVitals(double heartRate, double bp, double temp, double respRate) {
        if (heartRate < 60 || heartRate > 100) {
            notificationService.sendSms("Emergency Alert: Abnormal heart rate detected.");
        }
        if (bp > 180 || bp < 90) {
            notificationService.sendEmail("Emergency Alert: Abnormal blood pressure detected.");
        }
        if (temp > 39) {
            notificationService.sendSms("Emergency Alert: High temperature detected.");
        }
    }
}

// Panic button class
class PanicButton {
    private NotificationService notificationService;

    public PanicButton(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void triggerEmergency() {
        notificationService.sendEmail("Panic button triggered! Immediate assistance required.");
        notificationService.sendSms("Panic button triggered! Immediate assistance required.");
    }
}

// Video call class
class VideoCall {
    private String platform;

    public VideoCall(String platform) {
        this.platform = platform;
    }

    public String startConsultation() {
        return platform + " link: https://example.com/meeting";
    }

    public void endConsultation() {
        System.out.println(platform + " meeting ended.");
    }
}

// Reminder service class
class ReminderService {
    private NotificationService notificationService;

    public ReminderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendReminder(String message) {
        notificationService.sendEmail(message);
        notificationService.sendSms(message);
    }
}

// Main class
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static AppointmentManager appointmentManager = new AppointmentManager();
    private static NotificationService notificationService;
    private static EmergencyAlert emergencyAlert;
    private static PanicButton panicButton;
    private static ReminderService reminderService;
    private static VideoCall videoCall = new VideoCall("Zoom");

    public static void main(String[] args) {
        Notifiable emailNotification = new EmailNotification();
        Notifiable smsNotification = new SmsNotification();
        notificationService = new NotificationService(emailNotification, smsNotification);

        emergencyAlert = new EmergencyAlert(notificationService);
        panicButton = new PanicButton(notificationService);
        reminderService = new ReminderService(notificationService);

        while (true) {
            System.out.println("\nRemote Patient Monitoring System");
            System.out.println("1. Patient Registration");
            System.out.println("2. Doctor Registration");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Add Health Sign");
            System.out.println("5. View Appointments");
            System.out.println("6. Trigger Panic Button");
            System.out.println("7. Send Reminder");
            System.out.println("8. Start Video Consultation");
            System.out.println("9. End Video Consultation");
            System.out.println("10. Close");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> registerPatient();
                case 2 -> registerDoctor();
                case 3 -> scheduleAppointment();
                case 4 -> addHealthStatus();
                case 5 -> viewAppointments();
                case 6 -> triggerPanicButton();
                case 7 -> sendReminder();
                case 8 -> startVideoCall();
                case 9 -> endVideoCall();
                case 10 -> {
                    System.out.println("Closing system...");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void registerPatient() {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        patients.add(new Patient(id, name));
        System.out.println("Patient registered successfully!");
    }

    private static void registerDoctor() {
        System.out.print("Enter Doctor ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        doctors.add(new Doctor(id, name));
        System.out.println("Doctor registered successfully!");
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Doctor ID: ");
        String doctorId = scanner.nextLine();

        String date;
        String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";

        while (true) {
            System.out.print("Enter Appointment Date (yyyy-MM-dd): ");
            date = scanner.nextLine();

            if (date.matches(datePattern)) {
                break;
            } else {
                System.out.println("Invalid date format! Please enter the date in yyyy-MM-dd format.");
            }
        }

        appointmentManager.addAppointment(new Appointment(patientId, doctorId, date));
        System.out.println("Appointment scheduled!");
    }

    private static void addHealthStatus() {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();
        System.out.print("Enter Health Status Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Health Status Value: ");
        double value = scanner.nextDouble();
        scanner.nextLine();

        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                patient.addHealth(new HealthStatus(type, value));
                emergencyAlert.checkVitals(value, value, value, value); // Placeholder for actual health status checks
                System.out.println("Health status added!");
                return;
            }
        }

        System.out.println("Patient not found!");
    }

    private static void viewAppointments() {
        List<Appointment> appointments = appointmentManager.getAppointments();
        for (Appointment appointment : appointments) {
            System.out.println(appointment.getDetails());
        }
    }

    private static void triggerPanicButton() {
        panicButton.triggerEmergency();
    }

    private static void sendReminder() {
        System.out.print("Enter reminder message: ");
        String message = scanner.nextLine();
        reminderService.sendReminder(message);
        System.out.println("Reminder sent!");
    }

    private static void startVideoCall() {
        System.out.println(videoCall.startConsultation());
    }

    private static void endVideoCall() {
        videoCall.endConsultation();
    }
}
