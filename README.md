# School System – Java Desktop Application

**School System** is a modern and user-friendly desktop application designed to manage school data efficiently.  
Built using **Java (JDK 23)** and **JavaFX**, it provides school administrators with powerful tools to manage students and teachers, all backed by a **MySQL** database for reliable data storage.

---

## Overview

This application simplifies administrative tasks in schools by offering an organized graphical interface that enables real-time data operations and smooth record management.  
It was developed as a personal project to enhance programming skills and implement practical solutions.

---

## Core Functionalities

- Add and delete student and teacher records  
- View data in interactive and responsive tables  
- Perform real-time search by name or ID  
- Connect securely to a MySQL database using JDBC for persistent data storage

---

## Features

- Live Search – Instantly filter student or teacher records as you type  
- Student & Teacher Management – Easily manage entries with intuitive forms  
- Database Integration – Data is stored and fetched via MySQL and JDBC  
- Modern User Interface – Clean layout built with JavaFX and FXML  
- Smooth Navigation – Fast response and seamless screen transitions

---

## Installation & Setup

1. Install **Java JDK 23** and **MySQL Server**.
2. Create a MySQL database and the required tables (`students`, `teachers`).
3. Add your database connection details in the `config.properties` file (already excluded from Git tracking).
4. Open the project in **IntelliJ IDEA** (or any JavaFX-supported IDE).
5. Build and run the application.

---

## Notes

- This project was developed as a personal learning initiative to gain hands-on experience in:
  - Java  
  - JavaFX (with FXML)  
  - JDBC and database integration
- A basic login/logout system is included for secure access.
- Future Enhancements may include:
  - Multi-user account support  
  - Role-based permissions (e.g., admin vs. staff)  
  - Detailed reporting and export features

---

## Screenshots

### Login Page  
![Login]([src/main/resources/images/Login](https://github.com/Mead47c/School_System/blob/main/src/main/resources/images/Login.png))

### Dashboard  
![Dashboard](src/main/resources/images/Dashboard)

### Students Table  
![Students Table](src/main/resources/images/Students)

### Teachers Table  
![Teachers Table](src/main/resources/images/Teachers)

### About Us Page  
![About Us](src/main/resources/images/AboutUs)

### Logout Page  
![Logout](src/main/resources/images/Logout)
