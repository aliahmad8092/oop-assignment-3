Remote Patient Monitoring System

Overview

This "Remote Patient Monitoring System" is a healthcare solution designed to allow interaction between patients, doctors, and administrators for medical monitoring, appointments, and emergency alerts. The system supports functionalities like:

1. Patient & Doctor Registration 
2. Appointment Scheduling
3. Health Status Monitoring 
4. Emergency Alerts
5. Panic Button
6. Video Consultations 
7. Reminder Notifications 

The system integrates email and SMS notifications for alerts and reminders, along with emergency management features.

Features

1. Patient Registration
    Allows new patients to be registered with a unique ID and name.

2. Doctor Registration
    Allows new doctors to be registered with a unique ID and name.

3. Appointment Scheduling
    Enables scheduling of appointments between patients and doctors.
    Requires both patient and doctor IDs and appointment date.

4. Health Status Monitoring
    Allows patients to add their health status (e.g., heart rate, blood pressure).
    Triggers emergency alerts based on critical health parameters.

5. View Appointments
    Allows viewing of all scheduled appointments.

6. Panic Button
    Triggers an emergency notification when activated.

7. Send Reminders
    Sends email and SMS reminders to users.

8. Video Consultation
    Generates a link to start a video consultation using a specified platform (Zoom in this case).

Technologies Used

Java (Core Java for implementing the system)
JavaMail API (for sending email notifications)
Twilio API (for sending SMS notifications)

Setup and Installation

Prerequisites

JDK 11 or higher
IDE like Eclipse or IntelliJ IDEA (optional, but recommended)
Twilio account (for sending SMS)
Email provider (Gmail or any SMTP server for email notifications)

Running the System

Step 1: Clone the repository

```bash
git clone https://github.com/your-username/remote-patient-monitoring-system.git
cd remote-patient-monitoring-system
