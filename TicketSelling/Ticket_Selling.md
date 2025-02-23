# Ticket Selling Backend

## Overview
This is a backend-only project for a Ticket Selling system, primarily focused on practicing a little advanced CRUD operations, database a little optimization, and implementing basic payment functionalities. The project does not include a frontend.

## Tech Stack
- **Java**: JDK 23
- **Spring Boot**: Backend framework
- **Spring Data JPA**: ORM for database interactions
- **MySQL**: Relational database
- **MapStruct**: Code simplification for object mapping
- **Lombok**: Reducing boilerplate code

## Features
- Manage users and customers
- Manage concerts and halls
- Ticket booking and logging
- Seat management with seat categories
- Payment processing (basic level)
- Optimized database queries

## Database Schema (Entities)
- **User (Customer)**: Stores customer details
- **Concert**: Stores event details
- **Ticket**: Stores ticket details (composite primary key used)
- **Payment**: Manages transaction records
- **BookingLog**: Logs booking history
- **Hall**: Stores information about the event venue
- **Seat**: Stores seat details (for reserved seating)
- **SeatCategory**: Categorizes seats with different pricing
- **Booking**: Handles booking details
- **Band**: Stores band details



## API Endpoints (Examples)
- **Booking Tickets**: `POST /api/bookings`
- **Payment Processing**: `POST /api/payments`
- **Search Concerts**: `GET /api/concerts?keyword=rock`

## Notes
- This project is for backend practice only.
- No frontend is included.
- No authentication/security mechanisms (Spring Security is not used).
- Because of practice, I decided not to clone

