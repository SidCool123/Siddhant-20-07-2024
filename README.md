# Siddhant-20-07-2024

# Bus Ticket Reservation System

## Overview
This project is a Bus Ticket Reservation System that allows users to search for buses, view available bus routes and schedules, and reserve seats. The system is built with Spring Boot and includes user authentication, bus searching, and seat reservation features.

## Features

### User Authentication
- **User Repository**: Handles user data storage.
- **User Controller**:
  - **Create User API**: Allows for the creation of new users.
  - **Fetch User API**: Retrieves user information.
  - **Fetch All Users API**: Retrieves information for all users.

### Searching for Buses
- **Bus Repository**: Stores bus data.
- **Bus Controller**:
  - **Search Buses API**: Allows users to search for buses.
  - **View Available Bus Routes and Schedules API**: Displays available bus routes and their schedules.

### Seat Reservation
- **Ticket Controller**:
  - **Seat Reservation API**: Enables users to reserve seats on a bus.

## Getting Started

### Prerequisites
- Java 17
- Maven
- MySQL

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/bus-ticket-reservation-system.git
   cd bus-ticket-reservation-system

